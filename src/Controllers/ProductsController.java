package Controllers;

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

    private Products product;
    private ProductDao productDao;
    private SystemView views;
    String rol = rol_user;
    DefaultTableModel model = new DefaultTableModel();

    public ProductsController(Products product, ProductDao productDao, SystemView views) {
        this.product = product;
        this.productDao = productDao;
        this.views = views;

        //Registrar
        this.views.btn_product_register.addActionListener(this);
        // txt Buscar productos
        this.views.txt_product_search.addKeyListener(this);
        //Tabla
        this.views.tb_product.addMouseListener(this);
        //Modificar
        this.views.btn_product_update.addActionListener(this);
        //Eliminar
        this.views.btn_product_delete.addActionListener(this);
        //Cancelar
        this.views.btn_product_cancel.addActionListener(this);
        //Label product
        this.views.jLabelProducts.addMouseListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == views.btn_product_register) {
            if (views.txt_product_code.getText().equals("")
                    || views.txt_product_name.getText().equals("")
                    || views.txt_product_description.getText().equals("")
                    || views.txt_product_price_sale.getText().equals("")
                    || views.cb_product_category.getSelectedItem().toString().equals("")) {
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
            } else {
                product.setCode(Integer.parseInt(views.txt_product_code.getText()));
                product.setName(views.txt_product_name.getText().trim());
                product.setDescription(views.txt_product_description.getText().trim());
                product.setUnit_price(Double.parseDouble(views.txt_product_price_sale.getText()));
              
                Object selectedItem = views.cb_product_category.getSelectedItem();
                if(selectedItem instanceof DynamicCb){
                    DynamicCb category_id = (DynamicCb) selectedItem;
                    product.setCategory_id(category_id.getId());
                }else{
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione una categoria valida. ");
                }
                
                /* DynamicCb category_id = (DynamicCb) views.cb_product_category.getSelectedItem();
                product.setCategory_id(category_id.getId());*/
                
                if (productDao.registerProductsQuery(product)) {
                    JOptionPane.showMessageDialog(null, "Producto registrado con exito");
                    cleanTable();
                    cleanFields();
                    listAllProducts();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al registrar un producto");
                }
            }

        } else if (e.getSource() == views.btn_product_update) {
            if (views.txt_product_id.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Selecciona una fila para continuar");
            } else {
                if (views.txt_product_code.getText().equals("")
                        || views.txt_product_name.getText().equals("")
                        || views.txt_product_description.getText().equals("")
                        || views.txt_product_price_sale.getText().equals("")
                        || views.cb_product_category.getSelectedItem().toString().equals("")) {
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
                } else {
                    product.setCode(Integer.parseInt(views.txt_product_code.getText()));
                    product.setName(views.txt_product_name.getText().trim());
                    product.setUnit_price(Double.parseDouble(views.txt_product_price_sale.getText()));
                    product.setDescription(views.txt_product_description.getText().trim());
                    DynamicCb category_id = (DynamicCb) views.cb_product_category.getSelectedItem();
                    product.setCategory_id(category_id.getId());
                    product.setId(Integer.parseInt(views.txt_product_id.getText()));
                    if (productDao.updateProductQuery(product)) {
                        cleanTable();
                        cleanFields();
                        listAllProducts();
                        JOptionPane.showMessageDialog(null, "El producto se modificó correctamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "Hubo un error al momento de modificar el producto");
                    }

                }
            }
        }else if (e.getSource()==views.btn_product_delete){
            int  row = views.tb_product.getSelectedRow();
            if(row==-1){
                JOptionPane.showMessageDialog(null, "Debe seleccionar un producto para poder eliminarlo");
            }else{
                int id = Integer.parseInt(views.tb_product.getValueAt(row, 0).toString());
                int question = JOptionPane.showConfirmDialog(null, "En realidad quieres eliminar este producto");
                if(question==JOptionPane.YES_OPTION && productDao.deleteProductQuery(id)){
                    cleanFields();
                    cleanTable();
                    listAllProducts();
                    views.btn_product_register.setEnabled(true);
                    JOptionPane.showMessageDialog(null, "Producto eliminado con exito");
                }else{
                     JOptionPane.showMessageDialog(null, "Ha ocurrido un error al eliminar producto");
                }
            }
            
        }else if(e.getSource()==views.btn_product_cancel){
            cleanFields();
            views.btn_product_register.setEnabled(true);
        }
    }

    //Limpiar Campos
    public void cleanFields() {
        views.txt_product_id.setText("");
        views.txt_product_code.setText("");
        views.txt_product_name.setText("");
        views.txt_product_description.setText("");
        views.txt_product_price_sale.setText("");

    }

    //Listar todos los productos
    public void listAllProducts() {
        if (rol.equals("Administrador") || rol.equals("Auxiliar")) {
            List<Products> list = productDao.listProductsQuery(views.txt_product_search.getText().trim());
            model = (DefaultTableModel) views.tb_product.getModel();
            model.setRowCount(0);

            for (int i = 0; i < list.size(); i++) {
                Object[] row = new Object[7];
                row[0] = list.get(i).getId();
                row[1] = list.get(i).getCode();
                row[2] = list.get(i).getName();
                row[3] = list.get(i).getDescription();
                row[4] = list.get(i).getUnit_price();
                row[5] = list.get(i).getProduct_quantity();
                row[6] = list.get(i).getCategory_name();
                model.addRow(row);

            }
            views.tb_product.setModel(model);
            if (rol.equals("Auxiliar")) {
                views.btn_product_register.setEnabled(false);
                views.btn_product_update.setEnabled(false);
                views.btn_product_delete.setEnabled(false);
                views.btn_product_cancel.setEnabled(false);
                views.txt_product_code.setEditable(false);
                views.txt_product_description.setEditable(false);
                views.txt_product_name.setEditable(false);
                views.txt_product_price_sale.setEditable(false);
            }
        }
    }

    //Limpiar tabla
    public void cleanTable() {
        for (int i = 0; i < model.getRowCount(); i++) {
            model.removeRow(i);
            i = i - 1;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == views.tb_product) {
            int row = views.tb_product.rowAtPoint(e.getPoint());
            views.txt_product_id.setText(views.tb_product.getValueAt(row, 0).toString());
            product = productDao.searchProduct(Integer.parseInt(views.txt_product_id.getText()));
            views.txt_product_code.setText("" + product.getCode());
            views.txt_product_name.setText("" + product.getName());
            views.txt_product_description.setText("" + product.getDescription());
            views.txt_product_price_sale.setText("" + product.getUnit_price());
            views.cb_product_category.setSelectedItem(new DynamicCb(product.getCategory_id(), product.getCategory_name()));
            views.btn_product_register.setEnabled(false);
        }else if(e.getSource()==views.jLabelProducts){
            views.jTabbedPane1.setSelectedIndex(0);
            cleanFields();
            cleanTable();
            listAllProducts();
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
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == views.txt_product_search) {
            listAllProducts();
        }
    }

}
