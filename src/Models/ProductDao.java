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

public class ProductDao {

    // Único atributo global limpio para obtener conexiones bajo demanda
    private final ConnectionMySQL cn = new ConnectionMySQL();

    // Registrar Productos
    public boolean registerProductsQuery(Products product) {
        String query = "INSERT INTO products(code, name, description, unit_price, created, updated, category_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Timestamp dateTime = new Timestamp(new Date().getTime());
        
        // Try-with-resources: la conexión y el comando se cierran solos automáticamente
        try (Connection conn = cn.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
            
            pst.setInt(1, product.getCode());
            pst.setString(2, product.getName());
            pst.setString(3, product.getDescription());
            pst.setDouble(4, product.getUnitPrice());
            pst.setTimestamp(5, dateTime);
            pst.setTimestamp(6, dateTime);
            pst.setInt(7, product.getCategoryId());
            
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar producto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // Listar y Filtrar Productos (Optimizado y Seguro contra Hackeos)
    public List<Products> listProductsQuery(String value) {
        List<Products> listProducts = new ArrayList<>();
        
        // Consultas optimizadas con INNER JOIN
        String query = "SELECT pro.*, ca.name AS category_name FROM products pro "
                     + "INNER JOIN categories ca ON pro.category_id = ca.id";
        
        String querySearch = "SELECT pro.*, ca.name AS category_name FROM products pro "
                           + "INNER JOIN categories ca ON pro.category_id = ca.id "
                           + "WHERE pro.name LIKE ?"; // Uso de comodín seguro

        try (Connection conn = cn.getConnection()) {
            
            if (value == null || value.trim().isEmpty()) {
                try (PreparedStatement pst = conn.prepareStatement(query);
                     ResultSet rs = pst.executeQuery()) {
                    while (rs.next()) {
                        listProducts.add(mapResultSetToProduct(rs));
                    }
                }
            } else {
                try (PreparedStatement pst = conn.prepareStatement(querySearch)) {
                    pst.setString(1, "%" + value + "%"); // Inyección de parámetro segura
                    try (ResultSet rs = pst.executeQuery()) {
                        while (rs.next()) {
                            listProducts.add(mapResultSetToProduct(rs));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar productos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return listProducts;
    }

    // Modificar Productos
    public boolean updateProductQuery(Products product) {
        String query = "UPDATE products SET code=?, name=?, description=?, unit_price=?, updated=?, category_id=? WHERE id=?";
        Timestamp dateTime = new Timestamp(new Date().getTime());
        
        try (Connection conn = cn.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
            
            pst.setInt(1, product.getCode());
            pst.setString(2, product.getName());
            pst.setString(3, product.getDescription());
            pst.setDouble(4, product.getUnitPrice());
            pst.setTimestamp(5, dateTime);
            pst.setInt(6, product.getCategoryId());
            pst.setInt(7, product.getId());
            
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar producto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // Eliminar Productos (Blindado contra SQL Injection)
    public boolean deleteProductQuery(int id) {
        String query = "DELETE FROM products WHERE id = ?";
        
        try (Connection conn = cn.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
            
            pst.setInt(1, id);
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se puede eliminar el producto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // Buscar Producto por ID
    public Products searchProduct(int id) {
        String query = "SELECT pro.*, ca.name AS category_name FROM products pro "
                     + "INNER JOIN categories ca ON pro.category_id = ca.id WHERE pro.id = ?";
        Products product = new Products();
        
        try (Connection conn = cn.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
            
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    product = mapResultSetToProduct(rs);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar producto por ID: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return product;
    }

    // Buscar Producto por Código de Barras
    public Products searchCode(int code) {
        String query = "SELECT id, name, unit_price, product_quantity FROM products WHERE code = ?";
        Products product = new Products();
        
        try (Connection conn = cn.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
            
            pst.setInt(1, code);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    product.setId(rs.getInt("id"));
                    product.setName(rs.getString("name"));
                    product.setUnitPrice(rs.getDouble("unit_price"));
                    product.setProductQuantity(rs.getInt("product_quantity"));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar código: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return product;
    }

    // Traer Cantidad/Stock de producto por ID
    public Products searchId(int id) {
        String query = "SELECT product_quantity FROM products WHERE id = ?";
        Products product = new Products();
        
        try (Connection conn = cn.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
            
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    product.setProductQuantity(rs.getInt("product_quantity"));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar stock: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return product;
    }

    // Actualizar Stock de Producto
    public boolean updateStockQuery(int amount, int productId) {
        String query = "UPDATE products SET product_quantity = ? WHERE id = ?";
        
        try (Connection conn = cn.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
            
            pst.setInt(1, amount);
            pst.setInt(2, productId);
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar stock: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Mapeo DRY (Don't Repeat Yourself) para evitar repetir la asignación de campos repetidas veces
     */
    private Products mapResultSetToProduct(ResultSet rs) throws SQLException {
        Products product = new Products();
        product.setId(rs.getInt("id"));
        product.setCode(rs.getInt("code"));
        product.setName(rs.getString("name"));
        product.setDescription(rs.getString("description"));
        product.setUnitPrice(rs.getDouble("unit_price"));
        product.setProductQuantity(rs.getInt("product_quantity"));
        
        // Verificación por si la consulta no incluye el alias de la categoría
        try {
            product.setCategoryName(rs.getString("category_name"));
            product.setCategoryId(rs.getInt("category_id"));
        } catch (SQLException ignored) {
            // Ignorado si las columnas de categorías no forman parte del ResultSet
        }
        return product;
    }
}
