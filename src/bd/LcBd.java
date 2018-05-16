/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author sdx
 */
public class LcBd {
    private static Connection conn = null;
    
    public static Connection obtener() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
    {
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        conn = DriverManager.getConnection("jdbc:derby://localhost:1527/odm4","odm4","odm4");
        if(conn == null)
            System.out.println("bd.LcBd::obtener(): ERROR BD."); 
        else
            System.out.println("bd.LcBd::obtener(): EXITO BD.");
        return conn;
    }
    
    public static void cerrar() throws SQLException
    {
        System.out.println("bd.LcBd::cerrar()");
        if(conn!=null)
            conn.close();
    }
}
