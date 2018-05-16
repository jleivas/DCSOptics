/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import fn.GlobalValues;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author sdx
 */
public class RmBd {
    private static Connection conn = null;
    public static String bdUrl = GlobalValues.getBdUrl();
    public static String bdName = GlobalValues.getBdName();
    public static String user = GlobalValues.getBdUser();
    public static String pass = GlobalValues.getBdPass();
    
    public static Connection obtener() throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://"+bdUrl+"/"+bdName,user,pass);
        if(conn == null)
            System.out.println("bd.RmBd::obtener(): ERROR BD.");
        else
            System.out.println("bd.RmBd::obtener(): ERROR BD.");
       return conn;
    }
    
    public static void cerrar() throws SQLException
    {
        System.out.println("bd.RmBd::cerrar()");
        if(conn!=null)
            conn.close();
    }
}
