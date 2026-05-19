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

public class SuppliersDao {
    //Instanciar conexion BD

    ConnectionMySQL cn = new ConnectionMySQL();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    SystemView views;

    // Constructor para recibir y asignar la vista
    public SuppliersDao(SystemView views) {
        this.views = views;
    }

    
    //Registrar Proveedor
    public boolean registerSuppliersQuery(Suppliers suppliers) {
        String query = "INSERT INTO suppliers(name,description,address,telephone,email,"
                + "city,created,updated) VALUES (?,?,?,?,?,?,?,?)";
        Timestamp dataTime = new Timestamp(new Date().getTime());

        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            
            pst.setString(1, suppliers.getName());
            pst.setString(2, suppliers.getDescription());
            pst.setString(3, suppliers.getAddress());
            pst.setString(4, suppliers.getTelephone());
            pst.setString(5, suppliers.getEmail());
            pst.setString(6, suppliers.getCity());
            pst.setTimestamp(7, dataTime);
            pst.setTimestamp(8, dataTime);
            pst.execute();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error a registrar proveedores: " + e);
            return false;
        }
    }

    //Listar Proveedor
    public List listSuppliersQuery(String value) {
        List<Suppliers> list_suppliers = new ArrayList();
        String query = "SELECT*FROM suppliers";
        String query_search_suppliers = "SELECT * FROM suppliers WHERE name LIKE '%" + value + "%'";

        try {
            conn = cn.getConnection();
            if (value.equalsIgnoreCase("")) {
                pst = conn.prepareStatement(query);
                rs = pst.executeQuery();
            } else {
                pst = conn.prepareStatement(query_search_suppliers);
                rs = pst.executeQuery();
            }
            while (rs.next()) {
                Suppliers suppliers = new Suppliers();
                suppliers.setId(rs.getInt("id"));
                suppliers.setName(rs.getString("name"));
                suppliers.setDescription(rs.getString("description"));
                suppliers.setAddress(rs.getString("address"));
                suppliers.setTelephone(rs.getString("telephone"));
                suppliers.setEmail(rs.getString("email"));
                suppliers.setCity(rs.getString("city"));
                list_suppliers.add(suppliers);

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

        }
        return list_suppliers;
    }
    
    //Modificar Proveedor
    public boolean updateSuppliersQuery(Suppliers suppliers){
        String query = "UPDATE suppliers SET name =?, description=?, address=?,"+
                "telephone=?,email=?,city=?,updated=? WHERE id=?";
        
        
        Timestamp dataTime = new Timestamp(new Date().getTime());
        try{
            conn=cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.setString(1, suppliers.getName());
            pst.setString(2, suppliers.getDescription());
            pst.setString(3, suppliers.getAddress());
            pst.setString(4, suppliers.getTelephone());
            pst.setString(5, suppliers.getEmail());
            pst.setString(6, suppliers.getCity());
            pst.setTimestamp(7, dataTime);
            pst.setInt(8, suppliers.getId());
            
            pst.execute();
            return true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error al modiificar datos del proveedor"+ e);
            return false;
        }
    }
    
    //Eliminar Proveedor
    public boolean deleteSuppliersQuery(int id){
        String query = "DELETE FROM suppliers WHERE id="+id;
        try {
            conn = cn.getConnection();
            pst= conn.prepareStatement(query);
            pst.execute();
            return true;
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al eliminar Proveedor"+e);
            return false;
        }
    }
    
    //Refrescar proveedores en Combobox compras
    public void refrescarSuppliersQuery(){
     views.cb_purchase_proveedor.removeAllItems();
     String query = "Select name from suppliers order by name ASC";
     
     try
     {
         conn = cn.getConnection();
         pst = conn.prepareStatement(query);
         rs=pst.executeQuery();
         
         while(rs.next()){
             views.cb_purchase_proveedor.addItem(rs.getString("name"));
         }
     }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar el proveedor" + e.getMessage());
        }
    }
    
}
