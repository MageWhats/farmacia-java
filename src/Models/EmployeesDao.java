
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

public class EmployeesDao {

    private final ConnectionMySQL cn = new ConnectionMySQL();
    
    public static int id_user = 0;
    public static String fullName_user = "";
    public static String username_user = "";
    public static String rol_user = "";

    public Employees loginQuery(String user, String password) {
        String query = "SELECT * FROM employees WHERE user_name = ? AND password = ?";
        Employees employee = new Employees();
        try (Connection conn = cn.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, user);
            pst.setString(2, password);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    employee = mapResultSetToEmployee(rs);
                    id_user = employee.getId();
                    fullName_user = employee.getFullName();
                    username_user = employee.getUsername();
                    rol_user = employee.getRol();
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en login: " + e.getMessage());
        }
        return employee;
    }
    
   /* public Employees searchIdCard(int id_card){
        String query = "Select id, full_name FROM employees WHERE id_card = ? ";
        Employees employees = new Employees();
        
        try (Connection conn = cn.getConnection();
                PreparedStatement pst = conn.prepareStatement(query)){
            
            pst.setInt(1, id_card);
            try(ResultSet rs = pst.executeQuery()){
                 if(rs.next()){
                     employees.setId(rs.getInt("id"));
                     employees.setFullName("full_name");
                 }
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al buscar código: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return employees;
        }
*/
    public boolean registerEmployeeQuery(Employees employee) {
        String query = "INSERT INTO employees (id_card, full_name, user_name, address, telephone, email, password, rol, created, updated) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Timestamp dateTime = new Timestamp(new Date().getTime());
        try (Connection conn = cn.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, employee.getIdCard());
            pst.setString(2, employee.getFullName());
            pst.setString(3, employee.getUsername());
            pst.setString(4, employee.getAddress());
            pst.setString(5, employee.getTelephone());
            pst.setString(6, employee.getEmail());
            pst.setString(7, employee.getPassword());
            pst.setString(8, employee.getRol());
            pst.setTimestamp(9, dateTime);
            pst.setTimestamp(10, dateTime);
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar: " + e.getMessage());
            return false;
        }
    }

    public List<Employees> listEmployeesQuery(String value) {
        List<Employees> listEmployees = new ArrayList<>();
        String queryAll = "SELECT * FROM employees ORDER BY full_name ASC";
        String querySearch = "SELECT * FROM employees WHERE full_name LIKE ? ORDER BY full_name ASC";
        try (Connection conn = cn.getConnection()) {
            if (value == null || value.trim().isEmpty()) {
                try (PreparedStatement pst = conn.prepareStatement(queryAll);
                     ResultSet rs = pst.executeQuery()) {
                    while (rs.next()) { listEmployees.add(mapResultSetToEmployee(rs)); }
                }
            } else {
                try (PreparedStatement pst = conn.prepareStatement(querySearch)) {
                    pst.setString(1, "%" + value + "%");
                    try (ResultSet rs = pst.executeQuery()) {
                        while (rs.next()) { listEmployees.add(mapResultSetToEmployee(rs)); }
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar: " + e.getMessage());
        }
        return listEmployees;
    }

    public boolean updateEmployeeQuery(Employees employee) {
        String query = "UPDATE employees SET id_card=?, full_name=?, user_name=?, address=?, telephone=?, email=?, password=?, rol=?, updated=? WHERE id=?";
        Timestamp dateTime = new Timestamp(new Date().getTime());
        try (Connection conn = cn.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, employee.getIdCard());
            pst.setString(2, employee.getFullName());
            pst.setString(3, employee.getUsername());
            pst.setString(4, employee.getAddress());
            pst.setString(5, employee.getTelephone());
            pst.setString(6, employee.getEmail());
            pst.setString(7, employee.getPassword());
            pst.setString(8, employee.getRol());
            pst.setTimestamp(9, dateTime);
            pst.setInt(10, employee.getId());
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteEmployeeQuery(int id) {
        String query = "DELETE FROM employees WHERE id = ?";
        try (Connection conn = cn.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, id);
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar: " + e.getMessage());
            return false;
        }
    }

    private Employees mapResultSetToEmployee(ResultSet rs) throws SQLException {
        Employees employee = new Employees();
        employee.setId(rs.getInt("id"));
        employee.setIdCard(rs.getInt("id_card")); // ¡Mapeado correctamente de la base de datos!
        employee.setFullName(rs.getString("full_name"));
        employee.setUsername(rs.getString("user_name"));
        employee.setAddress(rs.getString("address"));
        employee.setTelephone(rs.getString("telephone"));
        employee.setEmail(rs.getString("email"));
        employee.setPassword(rs.getString("password"));
        employee.setRol(rs.getString("rol"));
        if (rs.getTimestamp("created") != null) {
            employee.setCreated(rs.getTimestamp("created").toString());
        } else {
            employee.setCreated(""); 
        }

        if (rs.getTimestamp("updated") != null) {
            employee.setUpdated(rs.getTimestamp("updated").toString());
        } else {
            employee.setUpdated(""); 
        }
        return employee;
    }
}
