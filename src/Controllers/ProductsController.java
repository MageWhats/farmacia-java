package Controllers;

import Models.CategoriesDao;
import Models.DynamicCb;
import Models.EmployeesDao;
import static Models.EmployeesDao.rol_user;
import Models.ProductDao;
import Models.Products;
import Views.SystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ProductsController implements ActionListener, MouseListener, KeyListener {

    private final Products product;
    private final ProductDao productDao;
    private final SystemView views;
    private final String rol = rol_user;
    private DefaultTableModel model = new DefaultTableModel();

    public ProductsController(Products product, ProductDao productDao, SystemView views) {
        this.product = product;
        this.productDao = productDao;
        this.views = views;

        // Registrar escuchadores de eventos
        this.views.btn_product_register.addActionListener(this);
        this.views.txt_product_search.addKeyListener(this);
        this.views.tb_product.addMouseListener(this);
        this.views.btn_product_update.addActionListener(this);
        this.views.btn_product_delete.addActionListener(this);
        this.views.btn_product_cancel.addActionListener(this);
        this.views.jLabelProducts.addMouseListener(this);
        
        cargarCategoriesProducts();
        listAllProducts();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == views.btn_product_register) {
            executeRegister();
        } else if (e.getSource() == views.btn_product_update) {
            executeUpdate();
        } else if (e.getSource() == views.btn_product_delete) {
            executeDelete();
        } else if (e.getSource() == views.btn_product_cancel) {
            cleanFields();
            views.btn_product_register.setEnabled(true);
        }
    }

    // Lógica de registro aislada y segura
    private void executeRegister() {
        if (areFieldsEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            product.setCode(Integer.parseInt(views.txt_product_code.getText().trim()));
            product.setUnitPrice(Double.parseDouble(views.txt_product_price_sale.getText().trim()));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "El código y el precio deben ser valores numéricos válidos.", "Error de formato", JOptionPane.ERROR_MESSAGE);
            return;
        }

        product.setName(views.txt_product_name.getText().trim());
        product.setDescription(views.txt_product_description.getText().trim());

        Object selectedItem = views.cb_product_category.getSelectedItem();
        if (selectedItem instanceof DynamicCb) {
            DynamicCb category = (DynamicCb) selectedItem;
            product.setCategoryId(category.getId());
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una categoría válida.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (productDao.registerProductsQuery(product)) {
            JOptionPane.showMessageDialog(null, "Producto registrado con éxito");
            cleanFields();
            listAllProducts();
        }
    }

    // Lógica de actualización aislada y segura
    private void executeUpdate() {
        if (views.txt_product_id.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Selecciona una fila de la tabla para continuar", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (areFieldsEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            product.setId(Integer.parseInt(views.txt_product_id.getText().trim()));
            product.setCode(Integer.parseInt(views.txt_product_code.getText().trim()));
            product.setUnitPrice(Double.parseDouble(views.txt_product_price_sale.getText().trim()));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Verifique los datos numéricos (Código/Precio).", "Error de formato", JOptionPane.ERROR_MESSAGE);
            return;
        }

        product.setName(views.txt_product_name.getText().trim());
        product.setDescription(views.txt_product_description.getText().trim());

        Object selectedItem = views.cb_product_category.getSelectedItem();
        if (selectedItem instanceof DynamicCb) {
            DynamicCb category = (DynamicCb) selectedItem;
            product.setCategoryId(category.getId());
        }

        if (productDao.updateProductQuery(product)) {
            cleanFields();
            listAllProducts();
            JOptionPane.showMessageDialog(null, "El producto se modificó correctamente");
        }
    }

    // Lógica de eliminación aislada
    private void executeDelete() {
        int row = views.tb_product.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un producto para poder eliminarlo", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(views.tb_product.getValueAt(row, 0).toString());
            int question = JOptionPane.showConfirmDialog(null, "¿Seguro que quieres eliminar este producto?", "Confirmar", JOptionPane.YES_NO_OPTION);

            if (question == JOptionPane.YES_OPTION && productDao.deleteProductQuery(id)) {
                cleanFields();
                listAllProducts();
                views.btn_product_register.setEnabled(true);
                JOptionPane.showMessageDialog(null, "Producto eliminado con éxito");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Error al procesar el ID del producto.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Centralizador para verificar si hay campos vacíos (Evita repetir código)
    private boolean areFieldsEmpty() {
        return views.txt_product_code.getText().trim().isEmpty()
                || views.txt_product_name.getText().trim().isEmpty()
                || views.txt_product_description.getText().trim().isEmpty()
                || views.txt_product_price_sale.getText().trim().isEmpty()
                || views.cb_product_category.getSelectedItem() == null
                || views.cb_product_category.getSelectedItem().toString().trim().isEmpty();
    }

    // Limpiar Campos
    public void cleanFields() {
        views.txt_product_id.setText("");
        views.txt_product_code.setText("");
        views.txt_product_name.setText("");
        views.txt_product_description.setText("");
        views.txt_product_price_sale.setText("");
        if (views.cb_product_category.getItemCount() > 0) {
            views.cb_product_category.setSelectedIndex(0);
        }
    }

    // Listar todos los productos filtrados u ordenados
    public void listAllProducts() {
        if (rol.equals("Administrador") || rol.equals("Auxiliar")) {
            List<Products> list = productDao.listProductsQuery(views.txt_product_search.getText().trim());
            model = (DefaultTableModel) views.tb_product.getModel();
            model.setRowCount(0); // Limpia la tabla eficientemente sin métodos externos redundantes

            // Uso de For-Each moderno
            for (Products prod : list) {
                Object[] row = new Object[7];
                row[0] = prod.getId();
                row[1] = prod.getCode();
                row[2] = prod.getName();
                row[3] = prod.getDescription();
                row[4] = prod.getUnitPrice();
                row[5] = prod.getProductQuantity();
                row[6] = prod.getCategoryName();
                model.addRow(row);
            }
            views.tb_product.setModel(model);

            // Control de restricciones para rol Auxiliar
            if (rol.equals("Auxiliar")) {
                views.btn_product_register.setEnabled(false);
                views.btn_product_update.setEnabled(false);
                views.btn_product_delete.setEnabled(false);
                views.btn_product_cancel.setEnabled(false);
                views.txt_product_code.setEditable(false);
                views.txt_product_description.setEditable(false);
                views.txt_product_name.setEditable(false);
                views.txt_product_price_sale.setEditable(false);
                views.cb_product_category.setEnabled(false);
            }
        }
    }

    // Coloca este método profesional al final de tu ProductsController.java
    public void cargarCategoriesProducts() {
        // 1. Instanciamos el DAO de categorías para consultar la base de datos
        CategoriesDao categoryDao = new Models.CategoriesDao();

        // 2. Limpiamos el ComboBox de la interfaz visual para vaciar elementos antiguos
        views.cb_product_category.removeAllItems();

        // 3. Traemos la lista fresca de categorías directamente de MySQL (enviando "" para traerlas todas)
        List<Models.Categories> listaCategorias = categoryDao.listCategoriesQuery("");

        // 4. Recorremos la lista con un bucle For-Each moderno
        for (Models.Categories cat : listaCategorias) {
            // Envolvemos el ID y el Nombre de la categoría dentro de tu objeto DynamicCb
            DynamicCb itemCombo = new DynamicCb(cat.getId(), cat.getName());

            // Agregamos el objeto dinámico directamente al ComboBox de tu SystemView
            views.cb_product_category.addItem(itemCombo);
        }
    }

    // Implementaciones requeridas por las interfaces (MouseListener / KeyListener)
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == views.tb_product) {
            int row = views.tb_product.getSelectedRow();
            if (row != -1) {
                views.txt_product_id.setText(views.tb_product.getValueAt(row, 0).toString());
                views.txt_product_code.setText(views.tb_product.getValueAt(row, 1).toString());
                views.txt_product_name.setText(views.tb_product.getValueAt(row, 2).toString());
                views.txt_product_description.setText(views.tb_product.getValueAt(row, 3).toString());
                views.txt_product_price_sale.setText(views.tb_product.getValueAt(row, 4).toString());

                // Deshabilitar botón de registro al seleccionar una fila para modificar
                if (!rol.equals("Auxiliar")) {
                    views.btn_product_register.setEnabled(false);
                }
            }
        } else if (e.getSource() == views.jLabelProducts) {
            views.jTabbedPane1.setSelectedIndex(0); // Reemplaza por el índice correcto de tu pestaña de productos
            listAllProducts();
            cargarCategoriesProducts();
            
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == views.txt_product_search) {
            listAllProducts(); // Busca dinámicamente mientras el usuario escribe
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }
}
