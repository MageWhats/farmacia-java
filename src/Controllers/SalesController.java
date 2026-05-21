package Controllers;

import Models.Customers;
import Models.CustomersDao;
import Models.EmployeesDao;
import static Models.EmployeesDao.rol_user;
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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class SalesController implements ActionListener, MouseListener, KeyListener {

    private Sales sale;
    private SalesDao saleDao;
    private SystemView views;
    Products product = new Products();
    ProductDao productDao = new ProductDao();
    Customers customer = new Customers();
    CustomersDao customerDao = new CustomersDao();

    private int item = 0;
    String rol = rol_user;

    DefaultTableModel model = new DefaultTableModel();
    DefaultTableModel temp;

    public SalesController(Sales sale, SalesDao saleDao, SystemView views) {
        this.sale = sale;
        this.saleDao = saleDao;
        this.views = views;

        //Llamar a la escucha botones
        this.views.btn_sales_add.addActionListener(this);
        this.views.btn_sales_new.addActionListener(this);
        this.views.btn_sales_remove.addActionListener(this);
        this.views.btn_sales_vender.addActionListener(this);

        //Llamar a la escucha txt
        this.views.txt_sales_productCode.addKeyListener(this);
        this.views.txt_sales_price.addKeyListener(this);
        this.views.txt_sales_identifyCliente.addKeyListener(this);
        this.views.txt_sales_nameCliente.addKeyListener(this);
        this.views.txt_sales_cantidad.addKeyListener(this);

        //Llamar a la escucha Label
        this.views.jLabelSales.addMouseListener(this);
        this.views.jLabelReports.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == views.btn_sales_vender) {
            insertSale();
        } else if (e.getSource() == views.btn_sales_new) {
            cleanAllFieldsSales();
            cleanTableTemp();
        } else if (e.getSource() == views.btn_sales_remove) {
            model = (DefaultTableModel) views.tb_sales.getModel();
            model.removeRow(views.tb_sales.getSelectedRow());
            calculateSales();
            views.txt_sales_productCode.requestFocus();
        } else if (e.getSource() == views.btn_sales_add) {
            int amount = Integer.parseInt(views.txt_sales_cantidad.getText());
            String product_name = views.txt_sales_nameProduct.getText();
            double price = Double.parseDouble(views.txt_sales_price.getText());
            int sale_id = Integer.parseInt(views.txt_sales_product_id.getText());
            double subtotal = amount * price;
            int stock = Integer.parseInt(views.txt_sales_stock.getText());
            String full_name = views.txt_sales_nameCliente.getText();
            if (stock >= amount) {
                item = item + 1;
                temp = (DefaultTableModel) views.tb_sales.getModel();
                for (int i = 0; i < views.tb_sales.getRowCount(); i++) {
                    if (views.tb_sales.getValueAt(i, 1).equals(views.txt_sales_nameProduct.getText())) {
                        JOptionPane.showMessageDialog(null, "El producto ya esta registrado en la tabla ventas");
                        return;
                    }

                }
                ArrayList list = new ArrayList();
                list.add(item);
                list.add(sale_id);
                list.add(product_name);
                list.add(amount);
                list.add(price);
                list.add(subtotal);
                list.add(full_name);
                Object[] obj = new Object[6];
                obj[0] = list.get(1);
                obj[1] = list.get(2);
                obj[2] = list.get(3);
                obj[3] = list.get(4);
                obj[4] = list.get(5);
                obj[5] = list.get(6);
                temp.addRow(obj);
                calculateSales();
                cleanFieldsSales();
                views.txt_sales_productCode.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Stock no Disponible");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Ingrese una cantidad");
        }
    }

    public void listAllSales() {
        if (rol.equals("Administrador")) {
            List<Sales> list = saleDao.listAllSalesQuery();
            model = (DefaultTableModel) views.tb_reports_sales.getModel();
            Object[] row = new Object[5];
            for (int i = 0; i < list.size(); i++) {
                row[0] = list.get(i).getId();
                row[1] = list.get(i).getCustomer_name();
                row[2] = list.get(i).getEmployee_name();
                row[3] = list.get(i).getTotal_to_pay();
                row[4] = list.get(i).getSale_date();
                model.addRow(row);
            }
            views.tb_reports_sales.setModel(model);
        }
    }

    private void insertSale() {
        int customer_id = Integer.parseInt(views.txt_sales_identifyCliente.getText());
        int employee_id = EmployeesDao.id_user;
        double total = Double.parseDouble(views.txt_sales_totalPagar.getText());
        if (saleDao.registerSaleQuery(customer_id, employee_id, total)) {
            int sale_id = saleDao.saleID();
            for (int i = 0; i < views.tb_sales.getRowCount(); i++) {
                int product_id = Integer.parseInt(views.tb_sales.getValueAt(i, 0).toString());
                int sale_quantity = Integer.parseInt(views.tb_sales.getValueAt(i, 2).toString());
                double sale_price = Double.parseDouble(views.tb_sales.getValueAt(i, 3).toString());
                double sale_subtotal = sale_quantity * sale_price;
                saleDao.registerSaleDetailsQuery(product_id, sale_id, sale_quantity, sale_price, sale_subtotal);
                product = productDao.searchId(product_id);
                int amount = product.getProduct_quantity() - sale_quantity;
                productDao.updateStockQuery(amount, product_id);
            }
            JOptionPane.showMessageDialog(null, "Venta generada");
            cleanTableTemp();
            cleanAllFieldsSales();
        }
    }

    private void calculateSales() {
        double total = 0.0;
        int numRow = views.tb_sales.getRowCount();
        for (int i = 0; i < numRow; i++) {
            total = total + Double.parseDouble(String.valueOf(views.tb_sales.getValueAt(i, 4)));
        }
        views.txt_sales_totalPagar.setText("" + total);
    }

    private void cleanTable() {
        for (int i = 0; i < model.getRowCount(); i++) {
            model.removeRow(i);
            i = i + 1;
        }
    }

    private void cleanTableTemp() {
        for (int i = 0; i < temp.getRowCount(); i++) {
            temp.removeRow(i);
            i = i - 1;
        }
    }
    
    private void cleanFieldsSales(){
        views.txt_sales_productCode.setText("");
        views.txt_sales_nameProduct.setText("");
        views.txt_sales_cantidad.setText("");
        views.txt_sales_product_id.setText("");
        views.txt_sales_price.setText("");
        views.txt_sales_subTotal.setText("");
        views.txt_sales_stock.setText("");
    }
    
    public void cleanAllFieldsSales(){
         views.txt_sales_productCode.setText("");
        views.txt_sales_nameProduct.setText("");
        views.txt_sales_cantidad.setText("");
        views.txt_sales_product_id.setText("");
        views.txt_sales_price.setText("");
        views.txt_sales_subTotal.setText("");
        views.txt_sales_stock.setText("");
        views.txt_sales_identifyCliente.setText("");
        views.txt_sales_nameCliente.setText("");
        views.txt_sales_totalPagar.setText("");
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == views.jLabelSales) {
            views.jTabbedPane1.setSelectedIndex(2);
        } else if (e.getSource() == views.jLabelReports) {
            if (rol.equals("Administrador")) {
                views.jTabbedPane1.setSelectedIndex(7);
                listAllSales();
            } else {
                views.jTabbedPane1.setEnabledAt(7, false);
                views.jLabelReports.setEnabled(false);
                JOptionPane.showMessageDialog(null, "No tienes persmisos de administrador");
            }
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
        if (e.getSource() == views.txt_sales_productCode) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (!"".equals(views.txt_sales_productCode.getText())) {
                    int code = Integer.parseInt(views.txt_sales_productCode.getText());
                    product = productDao.searchCode(code);
                    if (product.getName() != null) {
                        views.txt_sales_nameProduct.setText(product.getName());
                        views.txt_sales_product_id.setText("" + product.getId());
                        views.txt_sales_stock.setText("" + product.getProduct_quantity());
                        views.txt_sales_price.setText("" + product.getUnit_price());
                        views.txt_sales_cantidad.requestFocus();

                    } else {
                        JOptionPane.showMessageDialog(null, "No existe ningun producto con ese codigo");
                        cleanFieldsSales();
                        views.txt_sales_productCode.requestFocus();
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese el codigo del producto a vender");
                }
            }
        } else if (e.getSource() == views.txt_sales_identifyCliente) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (!"".equals(views.txt_sales_identifyCliente.getText())) {
                    int customer_id = Integer.parseInt(views.txt_sales_identifyCliente.getText());
                    customer = customerDao.searchCustomer(customer_id);
                    if (customer.getFull_name() != null) {
                        views.txt_sales_nameCliente.setText("" + customer.getFull_name());
                    } else {
                        views.txt_sales_identifyCliente.setText("");
                        JOptionPane.showMessageDialog(null, "El cliente no existe");
                    }
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == views.txt_sales_cantidad) {
            int quantity;
            double price = Double.parseDouble(views.txt_sales_price.getText());
            if (views.txt_sales_cantidad.getText().equals("")) {
                quantity = 1;
                views.txt_sales_price.setText("" + price);
            } else {
                quantity = Integer.parseInt(views.txt_sales_cantidad.getText());
                price = Double.parseDouble(views.txt_sales_price.getText());
                views.txt_sales_subTotal.setText("" + (quantity * price));
            }
        }

    }

}
