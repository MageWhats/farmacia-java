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

public class CustomersDao {

    private final ConnectionMySQL cn = new ConnectionMySQL();


    public boolean registerCustomerQuery(Customers customer) {
        String query = "INSERT INTO customers (id, full_name, address, telephone, email, created, updated) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Timestamp dateTime = new Timestamp(new Date().getTime());

        try (Connection conn = cn.getConnection(); PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setInt(1, customer.getId());
            pst.setString(2, customer.getFullName());
            pst.setString(3, customer.getAddress());
            pst.setString(4, customer.getTelephone());
            pst.setString(5, customer.getEmail());
            pst.setTimestamp(6, dateTime);
            pst.setTimestamp(7, dateTime);

            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar cliente: " + e.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // 2. Listar: Ordenado y buscando por la columna 'full_name'
    public List<Customers> listCustomersQuery(String value) {
        List<Customers> listCustomers = new ArrayList<>();
        String queryAll = "SELECT * FROM customers ORDER BY full_name ASC";
        String querySearch = "SELECT * FROM customers WHERE full_name LIKE ? ORDER BY full_name ASC";

        try (Connection conn = cn.getConnection()) {
            if (value == null || value.trim().isEmpty()) {
                try (PreparedStatement pst = conn.prepareStatement(queryAll); ResultSet rs = pst.executeQuery()) {
                    while (rs.next()) {
                        listCustomers.add(mapResultSetToCustomer(rs));
                    }
                }
            } else {
                try (PreparedStatement pst = conn.prepareStatement(querySearch)) {
                    pst.setString(1, "%" + value + "%");
                    try (ResultSet rs = pst.executeQuery()) {
                        while (rs.next()) {
                            listCustomers.add(mapResultSetToCustomer(rs));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar clientes: " + e.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
        }
        return listCustomers;
    }

    // 3. Modificar: Actualizando la columna 'full_name' según el ID único
    public boolean updateCustomerQuery(Customers customer) {
        String query = "UPDATE customers SET full_name=?, address=?, telephone=?, email=?, updated=? WHERE id=?";
        Timestamp dateTime = new Timestamp(new Date().getTime());

        try (Connection conn = cn.getConnection(); PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(2, customer.getFullName());
            pst.setString(3, customer.getAddress());
            pst.setString(4, customer.getTelephone());
            pst.setString(5, customer.getEmail());
            pst.setTimestamp(6, dateTime);
            pst.setInt(7, customer.getId());

            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar cliente: " + e.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // 4. Eliminar Clientes
    public boolean deleteCustomerQuery(int id) {
        String query = "DELETE FROM customers WHERE id = ?";

        try (Connection conn = cn.getConnection(); PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setInt(1, id);
            pst.execute();
            return true;
          
        } catch (SQLException e) {
            if (e.getErrorCode() == 1451) { 
                JOptionPane.showMessageDialog(null,
                        "No se puede eliminar este cliente porque tiene facturas de ventas asociadas en el historial.",
                        "Restricción de Seguridad", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar cliente: " + e.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
            }
            return false;
        }

    }
    
    public Customers searchIdCustomer(int id){
        String query = "Select  full_name from customers where id = ?";
        Customers customer = new Customers();
        
        try (Connection conn = cn.getConnection();
                PreparedStatement pst = conn.prepareStatement(query)){
            pst.setInt(1, id);
            try(ResultSet rs = pst.executeQuery()){
                if(rs.next()){
                    customer.setFullName(rs.getString("full_name"));
                }
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al buscar código: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return customer;
    }

    // 5. Mapeador DRY corregido para leer 'full_name'
    private Customers mapResultSetToCustomer(ResultSet rs) throws SQLException {
        Customers customer = new Customers();
        customer.setId(rs.getInt("id"));
        customer.setFullName(rs.getString("full_name")); 
        customer.setAddress(rs.getString("address"));
        customer.setTelephone(rs.getString("telephone"));
        customer.setEmail(rs.getString("email"));
        customer.setCreated(rs.getTimestamp("created").toString());
        customer.setUpdated(rs.getTimestamp("updated").toString());
        return customer;
    }
}
