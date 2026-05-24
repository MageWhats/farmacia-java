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

public class CategoriesDao {

    private final ConnectionMySQL cn = new ConnectionMySQL();

    // 1. Registrar Categorías: El ID es omitido al ser autoincrementable
    public boolean registerCategoryQuery(Categories category) {
        String query = "INSERT INTO categories (name, created, updated) VALUES (?, ?, ?)";
        Timestamp dateTime = new Timestamp(new Date().getTime());

        try (Connection conn = cn.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(1, category.getName());
            pst.setTimestamp(2, dateTime);
            pst.setTimestamp(3, dateTime);

            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar la categoría: " + e.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // 2. Listar y Buscar categorías con parámetros blindados contra inyecciones SQL
    public List<Categories> listCategoriesQuery(String value) {
        List<Categories> listCategories = new ArrayList<>();
        String queryAll = "SELECT * FROM categories ORDER BY name ASC";
        String querySearch = "SELECT * FROM categories WHERE name LIKE ? ORDER BY name ASC";

        try (Connection conn = cn.getConnection()) {
            if (value == null || value.trim().isEmpty()) {
                try (PreparedStatement pst = conn.prepareStatement(queryAll);
                     ResultSet rs = pst.executeQuery()) {
                    while (rs.next()) {
                        listCategories.add(mapResultSetToCategory(rs));
                    }
                }
            } else {
                try (PreparedStatement pst = conn.prepareStatement(querySearch)) {
                    pst.setString(1, "%" + value + "%");
                    try (ResultSet rs = pst.executeQuery()) {
                        while (rs.next()) {
                            listCategories.add(mapResultSetToCategory(rs));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar las categorías: " + e.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
        }
        return listCategories;
    }

    // 3. Modificar Categorías utilizando la cláusula WHERE vinculada al ID único
    public boolean updateCategoryQuery(Categories category) {
        String query = "UPDATE categories SET name=?, updated=? WHERE id=?";
        Timestamp dateTime = new Timestamp(new Date().getTime());

        try (Connection conn = cn.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(1, category.getName());
            pst.setTimestamp(2, dateTime);
            pst.setInt(3, category.getId());

            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar la categoría: " + e.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // 4. Eliminar Categorías
    public boolean deleteCategoryQuery(int id) {
        String query = "DELETE FROM categories WHERE id = ?";

        try (Connection conn = cn.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setInt(1, id);
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar la categoría seleccionada: " + e.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

  
    private Categories mapResultSetToCategory(ResultSet rs) throws SQLException {
        Categories category = new Categories();
        category.setId(rs.getInt("id"));
        category.setName(rs.getString("name"));
        category.setCreated(rs.getTimestamp("created").toString());
        category.setUpdated(rs.getTimestamp("updated").toString());
        return category;
    }
}
