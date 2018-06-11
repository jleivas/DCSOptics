/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import fn.GlobalValues;
import fn.Log;
import fn.OptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author sdx
 */
public class RmBd {
    private static Connection conn = null;
    private static String className = "RmBd";
    
    public static Connection obtener() throws ClassNotFoundException, SQLException
    {
        Log.setLog(className,Log.getReg());
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://"+GlobalValues.getRemoteBdUrl()+"/"+GlobalValues.getRemoteBdName(),GlobalValues.getRemoteBdUser(),GlobalValues.getRemoteBdPass());
        if(conn == null)
            OptionPane.showMsg("Error en Base de datos remota", "No se pudo obtener la conexion:\nbd.RmBd::obtener(): ERROR BD.\n\nDetalle: "+Log.getLog(), JOptionPane.ERROR_MESSAGE);
        return conn;
    }
    
    public static void cerrar() throws SQLException
    {
        Log.setLog(className,Log.getReg());
        if(conn!=null)
            conn.close();
    }
}
