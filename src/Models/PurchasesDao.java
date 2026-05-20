package Models;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

public class PurchasesDao {
    //Instanciar conexion BD

    ConnectionMySQL cn = new ConnectionMySQL();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;

    //Registrar compra
    public boolean registerPurchaseQuery(int supplier_id, int employee_id,
            double total) {

        String query = "INSERT INTO purchases (supplier_id, employee_id, total, created) VALUES(?,?,?,?)";

        Timestamp dateTime = new Timestamp(new Date().getTime());

        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.setInt(1, supplier_id);
            pst.setInt(2, employee_id);
            pst.setDouble(3, total);
            pst.setTimestamp(4, dateTime);
            pst.execute();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al ingresar la compra" + e);
            return false;
        }
    }

    //Registrar detalles de la compra
    public boolean registerPurchaseDetailQuery(int purchase_id, double purchase_price, int purchase_amount, double purchase_subtotal, int product_id) {

        String query = "INSERT INTO purchase_details (purchase_id, purchase_price, purchase_amount, purchase_subtotal, product_id)VALUES(?,?,?,?,?)";
        Timestamp dateTime = new Timestamp(new Date().getTime());

        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.setInt(1, purchase_id);
            pst.setDouble(2, purchase_price);
            pst.setInt(3, purchase_amount);
            pst.setDouble(4, purchase_subtotal);
            pst.setInt(5, product_id);
            

            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar detalles de la compra" + e);
            return false;
        }
    }

    //Obtener ID de la compra
    public int purchaseId() {
        int id = 0;
        String query = "Select MAX(id) AS id FROM purchases";
        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }
        return id;
    }

    //Listar todas las compras realizadas
    public List listAllPurchasesQuery() {
        List<Purchases> list_purchases = new ArrayList();
        String query = "select pu.*, su.name as supplier_name from purchases pu, suppliers su where pu.supplier_id= su.id order by pu.id asc";
        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                Purchases purchase = new Purchases();
                purchase.setId(rs.getInt("id"));
                purchase.setSupplier_name_product(rs.getString("supplier_name"));
                purchase.setTotal(rs.getInt("total"));
                purchase.setCreated(rs.getString("created"));
                list_purchases.add(purchase);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return list_purchases;
    }

    //Listar compras para imprimir factura
    public List listPurchaseDetailQuery(int id) {

        List<Purchases> list_purchases = new ArrayList();
        String query = "select pu.created, pude.purchase_price, pude.purchase_amount, pude.purchase_subtotal, su.name as supplier_name, pro.name as product_name, em.full_name from purchases pu inner join purchase_details pude on pu.id = pude.purchase_id inner join products pro on pude.product_id = pro.id inner join suppliers su on pu.supplier_id=su.id inner join employees em on pu.employee_id = em.id where pu.id=?";

        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {

                Purchases purchases = new Purchases();
                purchases.setProduct_name(rs.getString("product_name"));
                purchases.setPurchase_amount(rs.getInt("purchase_amount"));
                purchases.setPurchase_price(rs.getDouble("purchase_price"));
                purchases.setPurchase_subtotal(rs.getDouble("purchase_subtotal"));
                purchases.setSupplier_name_product(rs.getString("supplier_name"));
                purchases.setCreated(rs.getString("created"));
                purchases.setPurchase(rs.getString("full_name"));

                list_purchases.add(purchases);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);

        }
        return list_purchases;
    }

}
