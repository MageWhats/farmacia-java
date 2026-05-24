package Controllers;

import Models.DynamicCb;
import Models.ProductDao;
import Models.Products;
import Models.PurchasesDao;
import Models.Suppliers;
import Models.SuppliersDao;
import Views.SystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Models.PurchaseDetail;
import Models.Purchases;
import Views.Print;

public class PurchasesController implements ActionListener, MouseListener {

    private final Purchases purchase;
    private final PurchasesDao purchaseDao;
    private final ProductDao productDao;
    private final SystemView views;

    // Modelo para controlar el carrito de compras en tu JTable
    private List<PurchaseDetail> carList = new ArrayList<>();

    private DefaultTableModel tempModel;
    private double totalPay = 0.00;

    public PurchasesController(Purchases purchase, PurchasesDao purchaseDao, ProductDao productDao, SystemView views) {
        this.purchase = purchase;
        this.purchaseDao = purchaseDao;
        this.productDao = productDao;
        this.views = views;

        // Escuchadores para los 4 botones de acción de tu vista
        this.views.btn_purchase_add.addActionListener(this);       // Botón "Agregar"
        this.views.btn_purchase_buy.addActionListener(this);       // Botón "Comprar"
        this.views.btn_purchase_delete.addActionListener(this);    // Botón "Eliminar"
        this.views.btn_purchase_new.addActionListener(this);       // Botón "Nuevo"

        // Escuchador para buscar al dar ENTER en la caja de código de producto
        this.views.txt_purchase_productCode.addActionListener(this);

        // ¡Mejora!: Carga los proveedores apenas arranca la pantalla
        cargarSupplierPurchase();
        calcularSubtotalDinamico();

        this.views.jLabelPurchases.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == views.txt_purchase_productCode) {
            searchProductByCode();
        } else if (e.getSource() == views.btn_purchase_add) {
            addProductToTable();
        } else if (e.getSource() == views.btn_purchase_delete) {
            removeProductFromTable();
        } else if (e.getSource() == views.btn_purchase_buy) {
            processFinalPurchase();
        } else if (e.getSource() == views.btn_purchase_new) {
            cleanFields();
            cleanTemporaryTable();
        }
    }

    public void calcularSubtotalDinamico() {
        java.awt.event.KeyAdapter calcularEvent = new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                try {
                    String qtyStr = views.txt_purchase_quantity.getText().trim();
                    String priceStr = views.txt_purchase_price.getText().trim();

                    // Si ambos campos tienen números, calculamos de inmediato
                    if (!qtyStr.isEmpty() && !priceStr.isEmpty()) {
                        int qty = Integer.parseInt(qtyStr);
                        double price = Double.parseDouble(priceStr);
                        double subtotal = qty * price;

                        // Pintar el resultado en el campo gris de la interfaz
                        views.txt_purchase_subtotal.setText(String.valueOf(subtotal));
                    } else {
                        views.txt_purchase_subtotal.setText(""); // Limpiar si borran los datos
                    }
                } catch (NumberFormatException ex) {
                    views.txt_purchase_subtotal.setText(""); // Evitar que explote si escriben letras
                }
            }
        };

        // Amarrar el evento a los dos cuadros de texto de la vista
        views.txt_purchase_quantity.addKeyListener(calcularEvent);
        views.txt_purchase_price.addKeyListener(calcularEvent);
    }

    // 1. Busca el medicamento automáticamente al dar ENTER en el código
    private void searchProductByCode() {
        if (views.txt_purchase_productCode.getText().trim().isEmpty()) {
            return;
        }

        try {
            int code = Integer.parseInt(views.txt_purchase_productCode.getText().trim());
            Products prod = productDao.searchCode(code);
            if (prod.getName() != null) {
                views.txt_purchase_productName.setText(prod.getName());
                views.txt_purchase_id_product.setText(String.valueOf(prod.getId()));
                views.txt_purchase_quantity.requestFocus(); // Pasa el cursor al precio de compra
            } else {
                JOptionPane.showMessageDialog(null, "El producto no está registrado en el inventario.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "El código debe ser un valor numérico.", "Error de formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 2. Botón "Agregar": Calcula el subtotal en caliente y monta la fila en la tabla visual
    private void addProductToTable() {
        if (areFieldsEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios para agregar un producto.", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int code = Integer.parseInt(views.txt_purchase_productCode.getText().trim());
            int id = Integer.parseInt(views.txt_purchase_id_product.getText().trim());
            String name = views.txt_purchase_productName.getText().trim();
            int qty = Integer.parseInt(views.txt_purchase_quantity.getText().trim());
            double price = Double.parseDouble(views.txt_purchase_price.getText().trim());

            // Captura del ComboBox dinámico de proveedores
            DynamicCb supplierCb = (DynamicCb) views.cb_purchase_supplier.getSelectedItem();
            String supplierName = supplierCb.getName();

            // Lógica Limpia: Cálculo del Subtotal de la fila
            double subTotal = qty * price;
            views.txt_purchase_subtotal.setText(String.valueOf(subTotal));

            PurchaseDetail detail = new PurchaseDetail();
            detail.setProductCode(code);   // Usamos el código único del producto (barcode)
            detail.setProductName(name);   // Nombre auxiliar
            detail.setQuantity(qty);       // Cantidad a comprar
            detail.setPrice(price);         // Precio pactado
            detail.setSubtotal(subTotal);   // Subtotal de la fila
            carList.add(detail);           // Se guarda en el carrito temporal en memoria

            tempModel = (DefaultTableModel) views.tb_purchase.getModel();

            // Agregar la fila al JTable (Id, Nombre, Cantidad, Precio, SubTotal, Proveedor)
            Object[] row = new Object[6];
            row[0] = id;
            row[1] = name;
            row[2] = qty;
            row[3] = price;
            row[4] = subTotal;
            row[5] = supplierName;
            tempModel.addRow(row);

            // Recalcular dinámicamente el "Total a Pagar" acumulado abajo
            calculateTotalPay();
            cleanFields();
            views.txt_purchase_productCode.requestFocus();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Verifique los datos numéricos de cantidad o precio.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 3. Botón "Eliminar": Remueve la fila que el usuario seleccionó con el mouse
    private void removeProductFromTable() {
        tempModel = (DefaultTableModel) views.tb_purchase.getModel();
        int row = views.tb_purchase.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione un producto de la tabla para eliminarlo.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        tempModel.removeRow(row);
        calculateTotalPay(); // Resta el subtotal eliminado del "Total a Pagar"
    }

    // 4. Botón "Comprar": Procesa de forma masiva todas las filas de la tabla en MySQL con camelCase estricto
    private void processFinalPurchase() {
        int rowsCount = views.tb_purchase.getRowCount();
        if (rowsCount == 0) {
            JOptionPane.showMessageDialog(null, "No hay productos en la tabla para procesar la compra.", "Carrito Vacío", JOptionPane.WARNING_MESSAGE);
            return;
        }

        DynamicCb supplierCb = (DynamicCb) views.cb_purchase_supplier.getSelectedItem();
        int supplierId = supplierCb.getId();

        int employeeId = Integer.parseInt(views.txt_profile_id.getText().trim());

        double totalGeneral = 0;
        List<PurchaseDetail> detailsList = new ArrayList<>();

        // Bucle para recorrer la lista de compras temporal de tu vista
        for (int i = 0; i < rowsCount; i++) {
            int productId = Integer.parseInt(views.tb_purchase.getValueAt(i, 0).toString());
            String productName = views.tb_purchase.getValueAt(i, 1).toString();
            int qty = Integer.parseInt(views.tb_purchase.getValueAt(i, 2).toString());
            double price = Double.parseDouble(views.tb_purchase.getValueAt(i, 3).toString());
            double subTotal = Double.parseDouble(views.tb_purchase.getValueAt(i, 4).toString());

            totalGeneral += subTotal;

            // Instancia limpia mapeada con la nomenclatura camelCase de tu Purchases.java
            PurchaseDetail detail = new PurchaseDetail();
            detail.setProductCode(productId);
            detail.setProductName(productName);
            detail.setQuantity(qty);
            detail.setPrice(price);
            detail.setSubtotal(subTotal);

            detailsList.add(detail);

        }

        Purchases pur = new Purchases();
        pur.setTotal(totalGeneral);
        pur.setSupplierId(supplierId);
        pur.setEmployeeId(employeeId);

        // 1. Registrar histórico en la tabla 'purchases'
        if (purchaseDao.registerPurchaseQuery(pur, detailsList)) {

            for (PurchaseDetail detail : detailsList) {
                Products currenProd = productDao.searchId(detail.getProductCode());
                if (currenProd != null) {
                    int updatedStock = currenProd.getProductQuantity() + detail.getQuantity();
                    productDao.updateStockQuery(updatedStock, detail.getProductCode());
                }
            }
            pur.setSupplierName(views.cb_purchase_supplier.getSelectedItem().toString());
            pur.setEmployeeName(views.txt_profile_name.getText()); // <-- Reemplaza por el JTextField real que tenga el NOMBRE del empleado
            pur.setCreated(new java.sql.Timestamp(new java.util.Date().getTime()).toString());
            JOptionPane.showMessageDialog(null, "¡La compra masiva se procesó con éxito y el inventario fue actualizado!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cleanFields();
            cleanTemporaryTable();
            Print print = new Print(pur.getId(), pur);
            print.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(null, "No se pudo procesar la compra. Transacción cancelada.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
    // Método de soporte matemático para totalizar la columna de subtotales

    private void calculateTotalPay() {
        totalPay = 0.00;
        int rowsCount = views.tb_purchase.getRowCount();
        for (int i = 0; i < rowsCount; i++) {
            totalPay += Double.parseDouble(views.tb_purchase.getValueAt(i, 4).toString());
        }
        views.txt_purchase_total_to_pay.setText(String.valueOf(totalPay));
    }

    // Validador con .trim().isEmpty() optimizado
    private boolean areFieldsEmpty() {
        return views.txt_purchase_productCode.getText().trim().isEmpty()
                || views.txt_purchase_quantity.getText().trim().isEmpty()
                || views.txt_purchase_price.getText().trim().isEmpty()
                || views.cb_purchase_supplier.getSelectedItem() == null;
    }

    public void cleanFields() {
        views.txt_purchase_productCode.setText("");
        views.txt_purchase_productName.setText("");
        views.txt_purchase_quantity.setText("");
        views.txt_purchase_price.setText("");
        views.txt_purchase_subtotal.setText("");
        views.txt_purchase_id_product.setText("");
    }

    public void cleanTemporaryTable() {
        tempModel = (DefaultTableModel) views.tb_purchase.getModel();
        tempModel.setRowCount(0);
        views.txt_purchase_total_to_pay.setText("0.0");
    }

    public void cargarSupplierPurchase() {
        // 1. Instanciamos el DAO de proveedores para consultar la base de datos
        Models.SuppliersDao supplierDao = new Models.SuppliersDao();

        // 2. Limpiamos el ComboBox de la interfaz visual para no duplicar elementos antiguos
        views.cb_purchase_supplier.removeAllItems();

        // 3. Traemos la lista fresca de proveedores directamente de MySQL (enviando "" para traerlos todos)
        List<Suppliers> listaProveedores = supplierDao.listSuppliersQuery("");

        // 4. Recorremos la lista con un bucle For-Each moderno
        for (Suppliers sup : listaProveedores) {
            // Envolvemos el ID y el Nombre del proveedor dentro de tu objeto DynamicCb
            DynamicCb itemCombo = new DynamicCb(sup.getId(), sup.getName());

            // Agregamos el objeto dinámico directamente al ComboBox de tu SystemView
            views.cb_purchase_supplier.addItem(itemCombo);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == views.jLabelPurchases) {
            views.jTabbedPane1.setSelectedIndex(1); // Cambia al índice de tu pestaña de compras

            // ¡Mejora!: Vuelve a consultar MySQL para traer proveedores nuevos en caliente
            cargarSupplierPurchase();
            cleanTemporaryTable();

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
}
