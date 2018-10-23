/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import fn.GV;
import fn.Log;
import fn.OptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
        String detail ="Es probable que su conexión a internet sea intermitente\n"
                + "por favor intente nuevamente, si el problema persiste\n"
                + "póngase en contacto con su proveedor de software.";
        try{
        conn = DriverManager.getConnection("jdbc:mysql://"+GV.getRemoteBdUrl()+"/"+GV.getRemoteBdName(),GV.getRemoteBdUser(),GV.getRemoteBdPass());
        }catch(Exception ex){
            OptionPane.showMsg("Error en Base de datos remota", "No se pudo obtener la conexion:\n"+detail+"\nbd.RmBd::obtener(): ERROR BD.\n\nCatch: "+ex.getMessage(), 3);
            cerrar();
            GV.stopSincronizacion();
        }
        if(conn == null){
            OptionPane.showMsg("Error en Base de datos remota", "No se pudo obtener la conexion:\n"+detail+"\nbd.RmBd::obtener(): ERROR BD.\n\nDetalle: "+Log.getLog(), 3);
            cerrar();
            GV.stopSincronizacion();
        }
        return conn;
    }
    
    public static void cerrar() throws SQLException
    {
        Log.setLog(className,Log.getReg());
        if(conn!=null)
            conn.close();
    }
    
    public static boolean isOpen(){
        return (conn!=null)?true:false;
    }
}
