
package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMySQL {
 private String database_name = "pharmacy_database";
 private String DRIVER = "com.mysql.cj.jdbc.Driver";
 private String user = "root";
 private String password = "root";
 private String url = "jdbc:mysql://localhost:3306/"+database_name;


public Connection getConnection(){
    Connection conn = null;
    try{
        Class.forName(DRIVER);
        conn = DriverManager.getConnection(url, user, password);
    }catch(ClassNotFoundException e){
        System.err.println("Ha ocurrido un ClassNotFoundException "+e.getMessage());
    }catch(SQLException e){
        System.err.println(" Ha ocurrido un SQLException "+e.getMessage());
    }
    return conn;
  }   
}
