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

public class ProductDao {

    //Instanciar conexion BD
    ConnectionMySQL cn = new ConnectionMySQL();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;

    //Registrar Productos
    public boolean registerProductsQuery(Products product) {
        String query = "INSERT INTO products(code, name, description, unit_price, created, updated, category_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        Timestamp dataTime = new Timestamp(new Date().getTime());
        
        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            
            pst.setInt(1, product.getCode());
            pst.setString(2, product.getName());
            pst.setString(3, product.getDescription());
            pst.setDouble(4, product.getUnit_price());
            pst.setTimestamp(5, dataTime);
            pst.setTimestamp(6, dataTime);
            pst.setInt(7, product.getCategory_id());
            
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar producto" + e);
            return false;
        }
        
    }

    //Listar Productos

    public List listProductsQuery(String value) {
        List<Products> list_products = new ArrayList();
        String query = "select pro.*, ca.name AS category_name FROM products pro, categories ca where pro.category_id = ca.id";
        
        String query_search_products = "SELECT pro.*, ca.name AS category_name FROM products pro "
                + "INNER JOIN categories ca on pro.category_id = ca.id "
                + "WHERE pro.name like '%" + value + "%'";
        
        try {
            conn = cn.getConnection();
            
            if (value.equalsIgnoreCase("")) {
                
                pst = conn.prepareStatement(query);
                rs = pst.executeQuery();
                
            } else {
                pst = conn.prepareStatement(query_search_products);
                //pst.setString(1, "%" + value + "%");
                rs = pst.executeQuery();
                
            }
            
            while (rs.next()) {
                Products product = new Products();
                product.setId(rs.getInt("id"));
                product.setCode(rs.getInt("code"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setUnit_price(rs.getDouble("unit_price"));
                product.setProduct_quantity(rs.getInt("product_quantity"));
                product.setCategory_name(rs.getString("category_name"));
                
                list_products.add(product);
                
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return list_products;
        
    }

    //Modificar Productos
    public boolean updateProductQuery(Products product) {
        String query = "UPDATE products SET code=?, name=?, description=?,"
                + "unit_price =?, updated=?, category_id = ?  WHERE id =?";
        
        Timestamp dateTime = new Timestamp(new Date().getTime());
        
        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            
            pst.setInt(1, product.getCode());
            pst.setString(2, product.getName());
            pst.setString(3, product.getDescription());
            pst.setDouble(4, product.getUnit_price());
            pst.setTimestamp(5, dateTime);
            
            pst.setInt(6, product.getCategory_id());
            pst.setInt(7, product.getId());
            
            pst.execute();
            return true;
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, "Error al modificar producto" + e);
            return false;
            
        }
        
    }

    //Eliminar Productos
    public boolean deleteProductQuery(int id) {
        String query = "DELETE FROM products WHERE id = " + id;
        
        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            
            pst.execute();
            return true;
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, "No se puede eliminar el producto" + e);
            return false;
            
        }
    }

    //Buscar Productos
    public Products searchProduct(int id) {
        String query = "SELECT pro.*, ca.name as category_name from products pro inner join "
                + "categories ca on pro.category_id = ca.id where pro.id=?";
        
        Products product = new Products();
        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            
            pst.setInt(1, id);
            rs = pst.executeQuery();
            
            if (rs.next()) {
                product.setId(rs.getInt("id"));
                product.setCode(rs.getInt("code"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setUnit_price(rs.getDouble("unit_price"));
                product.setCategory_id(rs.getInt("category_id"));
                product.setCategory_name(rs.getString("category_name"));
                
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return product;
    }

    //Buscar Producto por COD
    public Products searchCode(int code) {
        String query = "select pro.id, pro.name, pro.unit_price, pro.product_quantity from products pro where pro.code = ?";
        Products product = new Products();
        
        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            
            pst.setInt(1, code);
            rs = pst.executeQuery();
            if (rs.next()) {
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setUnit_price(rs.getDouble("unit_price"));
                product.setProduct_quantity(rs.getInt("product_quantity"));
                
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return product;
    }

    //Traer Cantidad de producto
    public Products searchId(int id) {
        String query = "SELECT pro.product_quantity from products pro where pro.id=?";
        Products product = new Products();
        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            
            pst.setInt(1, id);
            rs = pst.executeQuery();
            
            if (rs.next()) {
                product.setProduct_quantity(rs.getInt("product_quantity"));
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return product;
    }

    //Actualizar Stock
    public boolean updateStockQuery(int amount, int product_id) {
        String query = "UPDATE products set product_quantity = ? where id =?";
        
        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.setInt(1, amount);
            pst.setInt(2, product_id);
            pst.execute();
            return true;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }
}
