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

public class SuppliersDao {

    private final ConnectionMySQL cn = new ConnectionMySQL();

    // 1. Registrar Proveedor (MySQL genera el ID solo)
    public boolean registerSupplierQuery(Suppliers supplier) {
        String query = "INSERT INTO suppliers (name, description, address, telephone, email, city, created, updated) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Timestamp dateTime = new Timestamp(new Date().getTime());

        try (Connection conn = cn.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(1, supplier.getName());
            pst.setString(2, supplier.getDescription());
            pst.setString(3, supplier.getAddress());
            pst.setString(4, supplier.getTelephone());
            pst.setString(5, supplier.getEmail());
            pst.setString(6, supplier.getCity());
            pst.setTimestamp(7, dateTime);
            pst.setTimestamp(8, dateTime);

            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar proveedor: " + e.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // 2. Listar y buscar dinámicamente sin riesgo de hackeos
    public List<Suppliers> listSuppliersQuery(String value) {
        List<Suppliers> listSuppliers = new ArrayList<>();
        String queryAll = "SELECT * FROM suppliers ORDER BY name ASC";
        String querySearch = "SELECT * FROM suppliers WHERE name LIKE ? ORDER BY name ASC";

        try (Connection conn = cn.getConnection()) {
            if (value == null || value.trim().isEmpty()) {
                try (PreparedStatement pst = conn.prepareStatement(queryAll);
                     ResultSet rs = pst.executeQuery()) {
                    while (rs.next()) {
                        listSuppliers.add(mapResultSetToSupplier(rs));
                    }
                }
            } else {
                try (PreparedStatement pst = conn.prepareStatement(querySearch)) {
                    pst.setString(1, "%" + value + "%");
                    try (ResultSet rs = pst.executeQuery()) {
                        while (rs.next()) {
                            listSuppliers.add(mapResultSetToSupplier(rs));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al desplegar proveedores: " + e.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
        }
        return listSuppliers;
    }

    // 3. Modificar Proveedor
    public boolean updateSupplierQuery(Suppliers supplier) {
        String query = "UPDATE suppliers SET name=?, description=?, address=?, telephone=?, email=?, city=?, updated=? WHERE id=?";
        Timestamp dateTime = new Timestamp(new Date().getTime());

        try (Connection conn = cn.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(1, supplier.getName());
            pst.setString(2, supplier.getDescription());
            pst.setString(3, supplier.getAddress());
            pst.setString(4, supplier.getTelephone());
            pst.setString(5, supplier.getEmail());
            pst.setString(6, supplier.getCity());
            pst.setTimestamp(7, dateTime);
            pst.setInt(8, supplier.getId());

            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar proveedor: " + e.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // 4. Eliminar Proveedor
    public boolean deleteSupplierQuery(int id) {
        String query = "DELETE FROM suppliers WHERE id = ?";

        try (Connection conn = cn.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setInt(1, id);
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar proveedor: " + e.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // Mapeador DRY centralizado
    private Suppliers mapResultSetToSupplier(ResultSet rs) throws SQLException {
        Suppliers supplier = new Suppliers();
        supplier.setId(rs.getInt("id"));
        supplier.setName(rs.getString("name"));
        supplier.setDescription(rs.getString("description"));
        supplier.setAddress(rs.getString("address"));
        supplier.setTelephone(rs.getString("telephone"));
        supplier.setEmail(rs.getString("email"));
        supplier.setCity(rs.getString("city"));
        supplier.setCreated(rs.getTimestamp("created").toString());
        supplier.setUpdated(rs.getTimestamp("updated").toString());
        return supplier;
    }
}
