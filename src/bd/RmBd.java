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
    
    public static Connection obtener() throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://"+GlobalValues.getRemoteBdUrl()+"/"+GlobalValues.getRemoteBdName(),GlobalValues.getRemoteBdUser(),GlobalValues.getRemoteBdPass());
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
