package Controllers;

import Models.DynamicCb;
import Models.EmployeesDao;
import static Models.EmployeesDao.id_user;
import static Models.EmployeesDao.rol_user;
import Models.ProductDao;
import Models.Products;
import Models.Purchases;
import Models.PurchasesDao;
import Models.Suppliers;
import Models.SuppliersDao;
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

public class PurchasesController implements ActionListener, MouseListener, KeyListener {

    private Purchases purchase;
    private PurchasesDao purchaseDao;
    private SuppliersDao supplierDao;
    private SystemView views;
    Products products = new Products();
    ProductDao productDao = new ProductDao();
    String rol = rol_user;
    private int getIdSupplier = 0;
    private int item = 0;
    DefaultTableModel model = new DefaultTableModel();
    DefaultTableModel temp;

    public PurchasesController(Purchases purchase, PurchasesDao purchaseDao, SystemView views, SuppliersDao supplierDao) {
        this.purchase = purchase;
        this.purchaseDao = purchaseDao;
        this.views = views;
        this.supplierDao = supplierDao;

        this.views.txt_purchase_productCode.addKeyListener(this);
        this.views.txt_purchase_precioCompra.addKeyListener(this);
        //Agregar
        this.views.btn_purchase_add_product_buy.addActionListener(this);
        //Comprar
        this.views.btn_purchase_confirm.addActionListener(this);
        getSupplierName();
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if (e.getSource() == views.btn_purchase_add_product_buy) {
    
    // 1. Obtener el índice seleccionado en el ComboBox
    int selectedIndex = views.cb_purchase_proveedor.getSelectedIndex();

    // 2. Validar que realmente haya un elemento seleccionado (índice 0 o superior)
    if (selectedIndex >= 0) {
        
        // 3. Extraer el objeto real directamente desde el modelo del ComboBox
        Object selectSupplier = views.cb_purchase_proveedor.getItemAt(selectedIndex);
        
        // Impresiones de depuración seguras en consola
        if (selectSupplier != null) {
            System.out.println("TIPO DE OBJETO DETECTADO: " + selectSupplier.getClass().getName());
            System.out.println("VALOR REAL DENTRO: " + selectSupplier.toString());
        }

        // 4. Validar si el objeto recuperado es una instancia de tu clase DynamicCb
        if (selectSupplier instanceof DynamicCb) {
            DynamicCb supplier_cb = (DynamicCb) selectSupplier;
            int supplier_id = supplier_cb.getId();
            
            if (getIdSupplier == 0) {
                getIdSupplier = supplier_id;
            } else {
                if (getIdSupplier != supplier_id) {
                    JOptionPane.showMessageDialog(null, "No puede realizar una misma compra con varios proveedores");
                    return;
                }
            }

            int amount = Integer.parseInt(views.txt_purchase_cantidad.getText());
            String product_name = views.txt_purchase_productName.getText();
            double price = Double.parseDouble(views.txt_purchase_precioCompra.getText());
            int purchase_id = Integer.parseInt(views.txt_purchase_id.getText());

            String supplier_name = supplier_cb.getName();

            if (amount > 0) {
                temp = (DefaultTableModel) views.tb_purchases.getModel();
                for (int i = 0; i < views.tb_purchases.getRowCount(); i++) {
                    if (views.tb_purchases.getValueAt(i, 1).equals(views.txt_purchase_productName.getText())) {
                        JOptionPane.showMessageDialog(null, "El producto ya esta registrado en la tabla de compras");
                        return;
                    }
                }

                ArrayList list = new ArrayList();
                item = 1;
                list.add(item);
                list.add(purchase_id);
                list.add(product_name);
                list.add(amount);
                list.add(price);
                list.add(amount * price);
                list.add(supplier_name);

                Object[] obj = new Object[6];
                obj[0] = list.get(1);
                obj[1] = list.get(2);
                obj[2] = list.get(3);
                obj[3] = list.get(4);
                obj[4] = list.get(5);
                obj[5] = list.get(6);

                temp.addRow(obj);
                views.tb_purchases.setModel(temp);
                cleanFieldsPurchases();
                views.cb_purchase_proveedor.setEditable(false);
                views.txt_purchase_productCode.requestFocus();
                caculatePurchase();
            }
        } else {
            // Se ejecuta si el objeto dentro del ComboBox no es un DynamicCb
            JOptionPane.showMessageDialog(null, "Por favor, seleccione un proveedor valido");
        }
    } else {
        // Se ejecuta si el ComboBox está vacío o no hay nada seleccionado (índice -1)
        JOptionPane.showMessageDialog(null, "Por favor, seleccione un proveedor de la lista");
    }
}
 else if (e.getSource() == views.btn_purchase_confirm) {
            insertPurchase();
        }
    }

    //
    public void cleanTableTemp() {
        for (int i = 0; i < temp.getRowCount(); i++) {
            temp.removeRow(i);
            i = i - 1;
        }

    }

    //
    private void insertPurchase() {
        double total = Double.parseDouble(views.txt_purchase_totalPagar.getText());
        int employee_id = id_user;
        if (purchaseDao.registerPurchaseQuery(getIdSupplier, employee_id, total)) {
            int purchase_id = purchaseDao.purchaseId();
            for (int i = 0; i < views.tb_purchases.getRowCount(); i++) {
                int product_id = Integer.parseInt(views.tb_purchases.getValueAt(i, 0).toString());
                int purchase_amount = Integer.parseInt(views.tb_purchases.getValueAt(i, 2).toString());
                double purchase_price = Double.parseDouble(views.tb_purchases.getValueAt(i, 3).toString());
                double purchase_subTotal = purchase_price * purchase_amount;
                purchaseDao.registerPurchaseDetailQuery(purchase_id, purchase_price, purchase_amount, purchase_subTotal, product_id);

            }
            cleanTableTemp();
            JOptionPane.showMessageDialog(null, "Compra generada con exito");
            cleanFieldsPurchases();
        }

    }

    //Limpiar cuadros de texto
    public void cleanFieldsPurchases() {
        views.txt_purchase_productName.setText("");
        views.txt_purchase_precioCompra.setText("");
        views.txt_purchase_cantidad.setText("");
        views.txt_purchase_productCode.setText("");
        views.txt_purchase_subTotal.setText("");
        views.txt_purchase_id.setText("");
        views.txt_purchase_totalPagar.setText("");
    }

    //valor precio total compra
    public void caculatePurchase() {

        double total = 0;
        int numRow = views.tb_purchases.getRowCount();
        for (int i = 0; i < numRow; i++) {
            total = total + Double.parseDouble(String.valueOf(views.tb_purchases.getValueAt(i, 4)));
        }
        views.txt_purchase_totalPagar.setText("" + total);

    }
    
        //Mostrar nombre de los proveedores
    public void getSupplierName(){
        views.cb_purchase_proveedor.removeAllItems();
        List<Suppliers> list= supplierDao.listSuppliersQuery((views.txt_suplimers_search.getText()));
        for (int i = 0 ; i< list.size();i++){
            int id = list.get(i).getId();
            String name = list.get(i).getName();
            views.cb_purchase_proveedor.addItem(new DynamicCb(id, name));
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
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
        if (e.getSource() == views.txt_purchase_productCode) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (views.txt_purchase_productCode.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Ingrese el codigo del producto");
                } else {
                    int id = Integer.parseInt(views.txt_purchase_productCode.getText());
                    products = productDao.searchCode(id);
                    views.txt_purchase_productName.setText("" + products.getName());
                    views.txt_purchase_id.setText("" + products.getId());
                    views.txt_purchase_cantidad.requestFocus();

                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getSource() == views.txt_purchase_precioCompra) {
            int quatity;
            double price = 0.0;
            if (views.txt_purchase_cantidad.getText().equals("")) {
                quatity = 1;
                views.txt_purchase_precioCompra.setText("" + price);
            } else {
                quatity = Integer.parseInt(views.txt_purchase_cantidad.getText());
                price = Double.parseDouble(views.txt_purchase_precioCompra.getText());
                views.txt_purchase_subTotal.setText("" + quatity * price);
            }
        }
    }

}
