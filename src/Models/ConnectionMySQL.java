package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConnectionMySQL {
    private final String databaseName = "pharmacy_database";
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String user = "root";
    private final String password = "root";
    
    // Mejora Senior: Forzamos el uso de UTF-8 (caracteres especiales) y corregimos el ServerTimezone para evitar caídas
    private final String url = "jdbc:mysql://localhost:3306/" + databaseName 
                             + "?useSSL=false&serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8";

    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, 
                "No se encontró el driver de conexión de MySQL. Verifique las librerías del proyecto.\nDetalle: " + e.getMessage(), 
                "Error de Driver", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "No se pudo establecer la conexión con la base de datos MySQL.\nVerifique que el servidor local esté encendido.\nDetalle: " + e.getMessage(), 
                "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
        }
        return conn;
    }   
}
