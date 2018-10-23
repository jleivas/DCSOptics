/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;
import fn.OptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author home
 */
public class BD {
    private static Connection conn = null;
    public static String bdNombre = "localhost";
    public static String user = "root";
    public static String pass = "";
    
    public static Connection obtener() throws SQLException, ClassNotFoundException
    {
        
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://"+bdNombre+"/sdxod",user,pass);
          
        return conn;
    }
    
     public static Object [][] select(String table, String fields, String where){
      int registros = 0;      
      String colname[] = fields.split(",");

      //Consultas SQL
      String q ="SELECT " + fields + " FROM " + table;
      String q2 = "SELECT count(*) as total FROM " + table;
      if(where!=null)
      {
          q+= " WHERE " + where;
          q2+= " WHERE " + where;
      }
       try{
         PreparedStatement pstm = obtener().prepareStatement(q2);
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
      }catch(SQLException e){
         OptionPane.showMsg("Error en LCBD", e.getLocalizedMessage(),3);
      } catch (ClassNotFoundException ex) {
            Logger.getLogger(LcBd.class.getName()).log(Level.SEVERE, null, ex);
            OptionPane.showMsg("Error en LCBD", "Detalle:\n"+ex.getLocalizedMessage(),3);
        }
    
    //se crea una matriz con tantas filas y columnas que necesite
    Object[][] data = new String[registros][fields.split(",").length];
    //realizamos la consulta sql y llenamos los datos en la matriz "Object"
      try{
         PreparedStatement pstm = obtener().prepareStatement(q);
         ResultSet res = pstm.executeQuery();
         int i = 0;
         while(res.next()){
            for(int j=0; j<=fields.split(",").length-1;j++){
                data[i][j] = res.getString( colname[j].trim() );
            }
            i++;         }
         res.close();
          }catch(SQLException e){
         OptionPane.showMsg("Error en LCBD al obtener datos", ""+e.getLocalizedMessage(),3);
    }   catch (ClassNotFoundException ex) {
            OptionPane.showMsg("Error en LCBD al obtener datos", "Detalle:\n"+ex.getLocalizedMessage(),3);
        }
    return data;
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
}
