package Controllers;

import Models.Customers;
import Models.CustomersDao;
import Models.Employees;
import Models.ProductDao;
import Models.Products;
import Models.Sales;
import Models.SalesDao;
import Views.SystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class SalesController implements ActionListener, KeyListener, MouseListener {

    private final Sales sale;
    private final SalesDao saleDao;
    private final ProductDao productDao;
    private final SystemView views;
    private final CustomersDao customerDao;
    
    private DefaultTableModel tempModel;
    private double totalPay = 0.00;

    public SalesController(Sales sale, SalesDao saleDao, ProductDao productDao, SystemView views,CustomersDao customerDao) {
        this.sale = sale;
        this.saleDao = saleDao;
        this.productDao = productDao;
        this.views = views;
        this.customerDao = customerDao;

        // Escuchadores de botones de tu interfaz gráfica para ventas
        this.views.btn_sale_add.addActionListener(this);      
        this.views.btn_sale_generate.addActionListener(this);  
        this.views.btn_sale_delete.addActionListener(this);    
        this.views.btn_sale_new.addActionListener(this);       
        
        // Escuchador para cargar datos al presionar ENTER en el código del medicamento
        this.views.txt_sale_productCode.addActionListener(this);
        this.views.txt_sale_idCustomer.addActionListener(this);
        this.views.jLabelSales.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        
        if (e.getSource() == views.txt_sale_productCode) {
            searchProductByCode();
            
        }else if(e.getSource()==views.txt_sale_idCustomer){
            searchCustomerByIdCard();
        }else if (e.getSource() == views.btn_sale_add) {
            addProductToCart();
        } else if (e.getSource() == views.btn_sale_delete) {
            removeProductFromCart();
        } else if (e.getSource() == views.btn_sale_generate) {
            processFinalSale();
        } else if (e.getSource() == views.btn_sale_new) {
            cleanFields();
            cleanTemporaryTable();
        }
    }
    
    
    private void searchCustomerByIdCard(){
        if(views.txt_sale_idCustomer.getText().trim().isEmpty())
            return;
        try{
            int id = Integer.parseInt(views.txt_sale_idCustomer.getText().trim());
            Customers cust = customerDao.searchIdCustomer(id);
            
            views.txt_sale_nameCustomer.setText(cust.getFullName());
            
            
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(null, "El código debe ser estrictamente numérico.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
                    }
    }

    // 1. Busca el producto en MySQL e indica cuántas existencias quedan disponibles
    private void searchProductByCode() {
        if (views.txt_sale_productCode.getText().trim().isEmpty()) 
            return;
        try {
            int code = Integer.parseInt(views.txt_sale_productCode.getText().trim());
            Products prod = productDao.searchCode(code);
            
            if (prod.getName() != null) {
                views.txt_sale_nameProduct.setText(prod.getName());
                views.txt_sale_product_id.setText(String.valueOf(prod.getId()));
                views.txt_sale_price_product.setText(String.valueOf(prod.getUnitPrice()));
                views.txt_sale_stock.setText(String.valueOf(prod.getProductQuantity())); // Muestra stock disponible
                views.txt_sale_quantity.requestFocus(); // Pasa foco a la casilla de cantidad
            } else {
                JOptionPane.showMessageDialog(null, "El medicamento no existe en el catálogo.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "El código debe ser estrictamente numérico.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }

 
    private void addProductToCart() {
        if (areFieldsEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor complete todos los datos del medicamento.", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(views.txt_sale_product_id.getText().trim());
            String name = views.txt_sale_nameProduct.getText().trim();
            int qty = Integer.parseInt(views.txt_sale_quantity.getText().trim());
            double price = Double.parseDouble(views.txt_sale_price_product.getText().trim());
            int currentStock = Integer.parseInt(views.txt_sale_stock.getText().trim());
            String nameCustomer = views.txt_sale_nameCustomer.getText().trim();

          
            if (qty > currentStock) {
                JOptionPane.showMessageDialog(null, "Stock insuficiente. Solo quedan " + currentStock + " unidades disponibles.", "Inventario Agotado", JOptionPane.ERROR_MESSAGE);
                return;
            }

           
            double subTotal = qty * price;

            tempModel = (DefaultTableModel) views.tb_sale.getModel();
          
            Object[] row = new Object[6];
            row[0] = id;
            row[1] = name;
            row[2] = qty;
            row[3] = price;
            row[4] = subTotal;
            row[5] = nameCustomer;
            
            tempModel.addRow(row);

            calculateTotalToPay();
            cleanFields();
            views.txt_sale_productCode.requestFocus();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Verifique los datos numéricos de cantidad o precio.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 3. Botón "Eliminar": Saca la fila seleccionada del carrito y recalcula el monto
    private void removeProductFromCart() {
        tempModel = (DefaultTableModel) views.tb_sale.getModel();
        int row = views.tb_sale.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione un medicamento del carrito para sacarlo.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        tempModel.removeRow(row);
        calculateTotalToPay();
    }

    // 4. Botón "Generar Venta": Procesa la boleta definitiva y resta el inventario en MySQL por lotes
    private void processFinalSale() {
        int rowsCount = views.tb_sale.getRowCount();
        if (rowsCount == 0) {
            JOptionPane.showMessageDialog(null, "El carrito de ventas se encuentra vacío.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (views.txt_sale_idCustomer.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe asociar un identificador de cliente para facturar.", "Cliente Requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int customerId = Integer.parseInt(views.txt_sale_idCustomer.getText().trim());
            int employeeId = Integer.parseInt(views.txt_profile_id.getText().trim()); // ID del cajero logueado

            // Configurar cabecera en el modelo Sales
            sale.setCustomerId(customerId);
            sale.setEmployeeId(employeeId);
            sale.setTotalToPay(totalPay);

            // 1. Insertar cabecera de la factura en MySQL
            if (saleDao.registerSaleQuery(sale)) {
                // 2. Obtener el ID autogenerado de esta última factura
                int saleId = saleDao.getLastSaleId();
                boolean detailSuccess = true;

                // Recorrer el carrito de compras fila por fila
                for (int i = 0; i < rowsCount; i++) {
                    int productId = Integer.parseInt(views.tb_sale.getValueAt(i, 0).toString());
                    int qty = Integer.parseInt(views.tb_sale.getValueAt(i, 2).toString());
                    double price = Double.parseDouble(views.tb_sale.getValueAt(i, 3).toString());
                    double subTotal = Double.parseDouble(views.tb_sale.getValueAt(i, 4).toString());

                    // 3. Registrar el detalle del medicamento comprado
                    if (saleDao.registerSaleDetailQuery(saleId, productId, qty, price, subTotal)) {
                        // 4. Traer existencias remanentes y RESTAR el stock en el inventario MySQL
                        Products currentProd = productDao.searchId(productId);
                        int netStock = currentProd.getProductQuantity() - qty; // Resta matemática
                        productDao.updateStockQuery(netStock, productId);
                    } else {
                        detailSuccess = false;
                    }
                }

                if (detailSuccess) {
                    JOptionPane.showMessageDialog(null, "¡La venta fue facturada con éxito y el stock fue descontado!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    cleanFields();
                    cleanTemporaryTable();
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de identidad de cliente/cajero.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Calcula de forma automatizada la sumatoria total a cobrarle al cliente
    private void calculateTotalToPay() {
        totalPay = 0.00;
        int rowsCount = views.tb_sale.getRowCount();
        for (int i = 0; i < rowsCount; i++) {
            totalPay += Double.parseDouble(views.tb_sale.getValueAt(i, 4).toString());
        }
        views.txt_sale_total_to_pay.setText(String.valueOf(totalPay));
    }

    private boolean areFieldsEmpty() {
        return views.txt_sale_productCode.getText().trim().isEmpty()
                || views.txt_sale_quantity.getText().trim().isEmpty()
                || views.txt_sale_price_product.getText().trim().isEmpty()
                || views.txt_sale_product_id.getText().trim().isEmpty();
    }

    public void cleanFields() {
        views.txt_sale_productCode.setText("");
        views.txt_sale_nameProduct.setText("");
        views.txt_sale_quantity.setText("");
        views.txt_sale_price_product.setText("");
        views.txt_sale_stock.setText("");
        views.txt_sale_product_id.setText("");
    }

    public void cleanTemporaryTable() {
        tempModel = (DefaultTableModel) views.tb_sale.getModel();
        tempModel.setRowCount(0);
        views.txt_sale_total_to_pay.setText("0.0");
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyPressed(KeyEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==views.jLabelSales){
        views.jTabbedPane1.setSelectedIndex(2);    
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
