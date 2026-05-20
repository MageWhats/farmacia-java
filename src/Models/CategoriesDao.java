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
import Views.SystemView;

public class CategoriesDao {
    //Instanciar conexion BD

    ConnectionMySQL cn = new ConnectionMySQL();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    SystemView views;

    // Constructor para recibir y asignar la vista
    public CategoriesDao(SystemView views) {
        this.views = views;
    }

    //Registrar Categoria
    public boolean registerCategoryQuery(Categories categories) {
        String query = "INSERT INTO categories (name,created,updated) VALUES (?,?,?)";
        Timestamp dataTime = new Timestamp(new Date().getTime());

        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.setString(1, categories.getName());
            pst.setTimestamp(2, dataTime);
            pst.setTimestamp(3, dataTime);
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al Registrar la categoria" + e);
            return false;
        }
    }

    //Listar Categoria
    public List listCategoriesQuery(String value) {
        List<Categories> list_categories = new ArrayList();
        String query = "SELECT * FROM categories";
        String query_search_category = "SELECT*FROM categories WHERE name LIKE '%" + value + "%'";

        try {
            conn = cn.getConnection();
            if (value.equalsIgnoreCase("")) {
                pst = conn.prepareStatement(query);
                rs = pst.executeQuery();
            } else {
                pst = conn.prepareStatement(query_search_category);
                rs = pst.executeQuery();
            }
            while (rs.next()) {
                Categories category = new Categories();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                list_categories.add(category);

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return list_categories;
    }

    //Modificar Categoria
    public boolean updateCategoryQuery(Categories categories) {
        String query = "Update categories SET name=?,updated=? WHERE id =?";
        Timestamp dataTime = new Timestamp(new Date().getTime());

        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.setString(1, categories.getName());
            pst.setTimestamp(2, dataTime);
            pst.setInt(3, categories.getId());
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar la categoria" + e);
            return false;
        }
    }

    //Eliminar Categoria
    public boolean deleteCategoryQuery(int id) {
        String query = "DELETE FROM categories WHERE id =" + id;
        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar la categoria" + e);
            return false;
        }
    }

    //Refrescar Categoria en el combobox de productos
    public void refrescarCategoriesQuery() {
        views.cb_product_category.removeAllItems();
        String query = "Select id, name from categories order by id ASC";
        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");

                    // 2. Insertamos el objeto estructurado completo en el JComboBox
                    views.cb_product_category.addItem(new DynamicCb(id, name));
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar categorias" + e.getMessage());
        }
    }
}
