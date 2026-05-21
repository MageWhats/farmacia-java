package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.sql.Timestamp;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class CustomersDao {
    //Instanciar conexion BD

    ConnectionMySQL cn = new ConnectionMySQL();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;

    //Registrar Clientes
    public boolean registerCutomersQuery(Customers customer) {
        String query = "INSERT INTO customers(id,full_name,address,telephone,email,"
                + "created,updated)VALUES(?,?,?,?,?,?,?)";

        Timestamp dateTime = new Timestamp(new Date().getTime());

        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.setInt(1, customer.getId());
            pst.setString(2, customer.getFull_name());
            pst.setString(3, customer.getAddress());
            pst.setString(4, customer.getTelephone());
            pst.setString(5, customer.getEmail());
            pst.setTimestamp(6, dateTime);
            pst.setTimestamp(7, dateTime);

            pst.execute();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar cliente" + e);
            return false;
        }
    }

    //Listar Clientes
    public List listCustomersQuery(String value) {
        List<Customers> list_customers = new ArrayList();

        String query = "SELECT * FROM customers";
        String query_search_customer = "SELECT * FROM customers WHERE id LIKE '%" + value + "%'";

        try {
            conn = cn.getConnection();
            if (value.equalsIgnoreCase("")) {
                pst = conn.prepareCall(query);
                rs = pst.executeQuery();
            } else {
                pst = conn.prepareStatement(query_search_customer);
                rs = pst.executeQuery();
            }
            while (rs.next()) {
                Customers customer = new Customers();
                customer.setId(rs.getInt("id"));
                customer.setFull_name(rs.getString("full_name"));
                customer.setAddress(rs.getString("address"));
                customer.setTelephone(rs.getString("telephone"));
                customer.setEmail(rs.getString("email"));
                list_customers.add(customer);

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return list_customers;
    }

    //Modificar Cliente
    public boolean updateCustomersQuery(Customers customer) {
        String query = "UPDATE customers SET full_name = ?, address = ?, telephone = ?, "
                + "email = ?, updated = ? WHERE id = ?";

        Timestamp dateTime = new Timestamp(new Date().getTime());
        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);

            pst.setString(1, customer.getFull_name());
            pst.setString(2, customer.getAddress());
            pst.setString(3, customer.getTelephone());
            pst.setString(4, customer.getEmail());
            pst.setTimestamp(5, dateTime);
            pst.setInt(6, customer.getId());

            pst.execute();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar datos del cliente " + e);
            return false;
        }
    }

    //Eliminar Cliente
    public boolean deleteCustomersQuery(int id) {
        String query = "DELETE FROM customers WHERE id= " + id;
        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);

            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se puede eliminar cliente que "
                    + "tenga relacion con otra tabla" + e);
            return false;
        }
    }

    //Buscar cliente
    public Customers searchCustomer(int id) {
        String query = "Select cu.id cu.full_name From customers cu Where cu.id=?";
        Customers customer = new Customers();
        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                customer.setId(rs.getInt("id"));
                customer.setFull_name(rs.getString("full_name"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return customer;
    }
}
