package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

public class SalesDao {

    private final ConnectionMySQL cn = new ConnectionMySQL();

    // 1. Registrar el encabezado de la venta (La Factura General)
    public boolean registerSaleQuery(Sales sale) {
        String query = "INSERT INTO sales (customer_id, employee_id, total, sale_date) VALUES (?, ?, ?, ?)";
        Timestamp dateTime = new Timestamp(new Date().getTime());

        try (Connection conn = cn.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setInt(1, sale.getCustomerId());
            pst.setInt(2, sale.getEmployeeId());
            pst.setDouble(3, sale.getTotalToPay());
            pst.setTimestamp(4, dateTime);

            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar la cabecera de la venta: " + e.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // 2. Registrar el Detalle de la Venta (Fila por fila del carrito de compras temporal)
    // El 'saleId' se obtiene consultando el último ID de factura generado en la tabla 'sales'
    public boolean registerSaleDetailQuery(int saleId, int productId, int quantity, double price, double subtotal) {
        String query = "INSERT INTO sales_details (sale_id, product_id, sale_quantity, sale_price, sale_subtotal) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = cn.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setInt(1, saleId);
            pst.setInt(2, productId);
            pst.setInt(3, quantity);
            pst.setDouble(4, price);
            pst.setDouble(5, subtotal);

            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar el detalle del medicamento: " + e.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // 3. Obtener el ID de la última venta realizada (Para poder enlazarla con sus detalles correspondientes)
    public int getLastSaleId() {
        String query = "SELECT MAX(id) AS id FROM sales";
        int lastId = 0;

        try (Connection conn = cn.getConnection();
             PreparedStatement pst = conn.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            if (rs.next()) {
                lastId = rs.getInt("id");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener identificador de factura: " + e.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
        }
        return lastId;
    }

    // 4. Listar Historial Completo de Ventas (Con INNER JOIN para traer los nombres reales del Cliente y Cajero)
    public List<Sales> listAllSalesQuery() {
        List<Sales> listSales = new ArrayList<>();
        String query = "SELECT s.*, c.full_name AS customer_name, e.full_name AS employee_name FROM sales s "
                     + "INNER JOIN customers c ON s.customer_id = c.id "
                     + "INNER JOIN employees e ON s.employee_id = e.id ORDER BY s.id DESC";

        try (Connection conn = cn.getConnection();
             PreparedStatement pst = conn.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Sales sale = new Sales();
                sale.setId(rs.getInt("id"));
                sale.setCustomerId(rs.getInt("customer_id"));
                sale.setEmployeeId(rs.getInt("employee_id"));
                sale.setTotalToPay(rs.getDouble("total"));
                sale.setSaleDate(rs.getTimestamp("sale_date").toString());
                sale.setCustomerName(rs.getString("customer_name"));
                sale.setEmployeeName(rs.getString("employee_name"));
                
                listSales.add(sale);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al desplegar el historial de facturación: " + e.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
        }
        return listSales;
    }
}
