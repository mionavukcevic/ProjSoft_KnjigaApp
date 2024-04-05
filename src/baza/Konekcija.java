/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baza;

import java.sql.*;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;
/**
 *
 * @author Miona
 */
public class Konekcija {
    
    private static Konekcija instance;
    private Connection connection;
    
    private Konekcija(){
            try {
                String url = "jdbc:mysql://localhost:3307/ps_sql_1";
                connection = DriverManager.getConnection(url, "root", "PHW#84#jeor");
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                Logger.getLogger(Konekcija.class.getName()).log(Level.SEVERE, null, e);
            }
    }
    
    public static Konekcija getInstance(){
        if(instance == null)
            instance = new Konekcija();
        return instance;
    }
    
    public Connection getConnection(){
        return connection;
    }
    
    
}
