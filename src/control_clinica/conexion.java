/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control_clinica;

import java.sql.*;
import javax.swing.JOptionPane;
import java.sql.Connection;
/**
 *
 * @author FerdyRCardonaDev
 */
public class conexion {
    
    public conexion(){
        
    }
    public Connection con;
    
    private static final String driver="com.mysql.jdbc.Driver";
    private static final String user = "root";
    private static final String pass = "Emperador573";
    private static final String url = "jdbc:mysql://localhost:3306/control_clinica";
    
    public Connection conectar(){
        
        con = null;
        try{
            con = (Connection) DriverManager.getConnection(url,user,pass);
            if(con != null){
                
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Imposible conectar" + e.toString());
        }
        return con;
    }
}
