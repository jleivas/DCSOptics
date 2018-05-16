/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import dao.RegistroGlobal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author home
 */
public class BD {
    private static Connection conn = null;
    public static String bdNombre = "localhost";
    public static String user = "root";
    public static String pass = RegistroGlobal.PASS;
    
    public static Connection obtener() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        //jdbc:derby://localhost:1527/myDB;create=true;user=me;password=mine
        //jdbc:derby://localhost:1527/odm4 [odm4 on ODM4]
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");//Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:derby://localhost:1527/odm4","odm4","odm4");//"jdbc:mysql://"+bdNombre+"/sdxod",user,pass
        if(conn == null)
            System.out.println("ERROR BD"); 
        else
            System.out.println("EXITO BD");
       return conn;
    }
    
    public boolean esLocal(){
        if(this.bdNombre.equals("localhost"))
            return true;
        else
            return false;
    }
    
    public static void cerrar() throws SQLException
    {
        if(conn!=null)
            conn.close();
    }
    
    public static void setBd(String nombre){
        bdNombre = nombre;
        user = "root2";
        pass = RegistroGlobal.PASS;
    }
}
