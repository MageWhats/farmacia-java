package Models; // Ajusta a tu paquete real

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import Models.PurchaseDetail; // Asegúrate de importar tu modelo de detalles
import Models.Purchases;

public class PurchasesDao {

    private final ConnectionMySQL cn = new ConnectionMySQL();

    // 1. REGISTRAR COMPRA COMPLETA (Cabecera en 'purchases' + Detalles en 'purchase_details')
    public boolean registerPurchaseQuery(Purchases purchase, List<PurchaseDetail> detailsList) {
        String queryPurchase = "INSERT INTO purchases (total, created, supplier_id, employee_id) VALUES (?, ?, ?, ?)";
        String queryDetail = "INSERT INTO purchase_details (purchase_id, product_id, purchase_amount, purchase_price, purchase_subtotal) VALUES (?, ?, ?, ?, ?)";

        Timestamp dateTime = new Timestamp(new Date().getTime());

        Connection conn = null;
        PreparedStatement pstPurchase = null;
        PreparedStatement pstDetail = null;
        ResultSet rs = null;

        try {
            conn = cn.getConnection();
            conn.setAutoCommit(false); // Activamos transacción para que no guarde a medias si hay errores

            // Guardar la cabecera de la compra
            pstPurchase = conn.prepareStatement(queryPurchase, Statement.RETURN_GENERATED_KEYS);
            pstPurchase.setDouble(1, purchase.getTotal());
            pstPurchase.setTimestamp(2, dateTime);
            pstPurchase.setInt(3, purchase.getSupplierId());
            pstPurchase.setInt(4, purchase.getEmployeeId()); // CORREGIDO: Ahora va el EmployeeID, no el ProductID
            pstPurchase.executeUpdate();

            // Obtener el ID autoincremental que MySQL le asignó a esta compra
            rs = pstPurchase.getGeneratedKeys();
            int purchaseIdGenerated = 0;
            if (rs.next()) {
                purchaseIdGenerated = rs.getInt(1);
                purchase.setId(purchaseIdGenerated); 
            }

            // Guardar uno a uno los productos del carrito en la tabla de detalles
            pstDetail = conn.prepareStatement(queryDetail);
            for (PurchaseDetail detail : detailsList) {
                pstDetail.setInt(1, purchaseIdGenerated); // Llave foránea conectada a purchases(id)
                pstDetail.setInt(2, detail.getProductCode());
                pstDetail.setInt(3, detail.getQuantity());
                pstDetail.setDouble(4, detail.getPrice());
                pstDetail.setDouble(5, detail.getSubtotal());
                pstDetail.addBatch(); // Empaqueta las filas para ejecución masiva eficiente
            }

            pstDetail.executeBatch(); // Ejecuta todas las inserciones de detalles juntas
            conn.commit();            // Aplica los cambios finales en la base de datos de manera segura
            return true;

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); // Cancela todo si algo falló para no corromper datos
                } catch (SQLException ex) {
                    System.out.println("Error en rollback: " + ex.getMessage());
                }
            }
            JOptionPane.showMessageDialog(null, "Error al registrar la compra completa: " + e.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            // Cierre seguro de recursos abiertos
            try {
                if (rs != null) rs.close();
                if (pstPurchase != null) pstPurchase.close();
                if (pstDetail != null) pstDetail.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexiones: " + e.getMessage());
            }
        }
    }

    // 2. LISTAR HISTORIAL DE COMPRAS (Para la tabla visual de "Compras Realizadas")
    // CORREGIDO: Eliminados los campos inexistentes de 'purchases' para evitar el error 'Column not found'
    public List<Purchases> listAllPurchasesQuery() {
        List<Purchases> listPurchases = new ArrayList<>();
        // Trae los campos de la tabla 'purchases' junto con el nombre del proveedor real mediante el JOIN
        String query = "SELECT pur.id, pur.total, pur.created, pur.supplier_id, pur.employee_id, sup.name AS supplier_name "
                     + "FROM purchases pur "
                     + "INNER JOIN suppliers sup ON pur.supplier_id = sup.id ORDER BY pur.id DESC";

        try (Connection conn = cn.getConnection();
             PreparedStatement pst = conn.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Purchases purchase = new Purchases();
                purchase.setId(rs.getInt("id"));
                purchase.setTotal(rs.getDouble("total"));
                purchase.setCreated(rs.getTimestamp("created").toString());
                purchase.setSupplierId(rs.getInt("supplier_id"));
                purchase.setEmployeeId(rs.getInt("employee_id"));
                purchase.setSupplierName(rs.getString("supplier_name"));
                
                listPurchases.add(purchase);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar historial de compras: " + e.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
        }
        return listPurchases;
    }

    // 3. LISTAR DETALLES ESPECÍFICOS DE UNA COMPRA SELECCIONADA
    // CORREGIDO: La consulta ahora extrae la información real de la tabla 'purchase_details' y hace JOIN con productos
    public List<PurchaseDetail> listPurchaseDetailQuery(int id) {
        List<PurchaseDetail> listDetails = new ArrayList<>();
       String query = "SELECT det.id, "
             + "det.purchase_id, "
             + "det.product_id AS product_code, " // <-- Corregido
             + "prod.name AS product_name, "     // <-- Corregido
             + "det.purchase_amount AS quantity, " // <-- Corregido
             + "det.purchase_price AS price, "    // <-- Corregido
             + "det.purchase_subtotal AS subtotal " // <-- Corregido
             + "FROM purchase_details det "
             + "INNER JOIN products prod ON det.product_id = prod.id "
             + "WHERE det.purchase_id = ?";
                     
        try (Connection conn = cn.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
            
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    PurchaseDetail detail = new PurchaseDetail();
                    detail.setId(rs.getInt("id"));
                    detail.setPurchaseId(rs.getInt("purchase_id"));
                    detail.setProductCode(rs.getInt("product_code"));
                    detail.setProductName(rs.getString("product_name")); // Campo auxiliar de nombre traído por el JOIN
                    detail.setQuantity(rs.getInt("quantity"));
                    detail.setPrice(rs.getDouble("price"));
                    detail.setSubtotal(rs.getDouble("subtotal"));
                    
                    listDetails.add(detail);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar los artículos del detalle: " + e.getMessage());
        }
        return listDetails;
    }
}
