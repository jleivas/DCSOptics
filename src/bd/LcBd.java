/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import dao.Dao;
import entities.TipoPago;
import entities.User;
import fn.GV;
import fn.Log;
import fn.OptionPane;
import fn.globalValues.GlobalValuesBD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sdx
 */
public class LcBd{
    private static Connection conn = null;
    private static String className= "LcBd";
    private static String[] T = GlobalValuesBD.tableNamesDB();
    private static String[] C = GlobalValuesBD.tablesDB();
    
    public static Connection crear() {
        Log.setLog(className,Log.getReg());
        if(T.length == C.length){
            for(int i=0;i<T.length;i++){
                System.out.println(T[i]+"=>"+C[i]);
                if(!createTable(T[i], C[i])){
                    System.out.println("La base de datos ya existe");
                    return conn;
                }
            }
        }
        Dao load = new Dao();
        try {
            load.addOnInit(new User(1, "Admin", "admin", "", GV.enC("admin"), 1, 1, null, 0));
            load.addOnInit(new TipoPago(1, "Convenio", 1, null, 0));
            
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(LcBd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
    
    public static Connection deleteAll() {
        Log.setLog(className,Log.getReg());
        if(T.length > 0){
            for(int i=0;i<T.length;i++){
                dropTable(T[i]);
            }
        }
        return conn;
    }
    
    public static Connection truncateAll() {
        Log.setLog(className,Log.getReg());
        if(T.length > 0){
            for(int i=0;i<T.length;i++){
                truncateTable(T[i]);
            }
        }
        return conn;
    }
        
    public static Connection obtener() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
    {
        Log.setLog(className,Log.getReg());
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        
//        conn = DriverManager.getConnection("jdbc:derby://"+GV.getLocalBdUrl()+"/"+GV.getLocalBdName(),GV.getLocalBdUser(),GV.getLocalBdPass());
        
        conn = DriverManager.getConnection("jdbc:derby:"+GV.getLocalBdUrl()+GV.getLocalBdName());
        if(conn == null)
            OptionPane.showMsg("Error en Base de datos local", "No se pudo obtener la conexion:\nbd.RmBd::obtener(): ERROR BD.\n\nDatelle: "+Log.getLog(), 3);
        return conn;
    }
    
    public static boolean isOpen(){
        return (conn!=null)?true:false;
    }
    
    public static void cerrar() throws SQLException
    {
        Log.setLog(className,Log.getReg());
        if(conn!=null)
            conn.close();
    }
    
    private static void truncateTable(String tableName){
        try{
            //obtenemos el driver de para mysql
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            cerrar();
            conn = DriverManager.getConnection("jdbc:derby:"+GV.getLocalBdUrl()+GV.getLocalBdName()+";create=true");
            if (conn!=null){
               String creartabla="TRUNCATE TABLE "+tableName;//(tableName.toLowerCase().equals("lente"))?"drop table lente":
                System.out.println(creartabla);
               try {
                    PreparedStatement pstm = conn.prepareStatement(creartabla);
                    pstm.execute();
                    pstm.close();
                    cerrar();
                } catch (SQLException ex) {
                    System.out.println("\"Error al borrar datos detabla "+tableName+", "+ex.getLocalizedMessage());
//                    OptionPane.showMsg("Error al crear tabla "+tableName, ex.getLocalizedMessage(),3);
                }
            }
        }catch(SQLException | ClassNotFoundException | ExceptionInInitializerError e){
         OptionPane.showMsg("Error al borrar datos de tabla "+tableName,e.getMessage() ,  3);
        }
    }
    
    private static void dropTable(String tableName){
        try{
            //obtenemos el driver de para mysql
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            cerrar();
            conn = DriverManager.getConnection("jdbc:derby:"+GV.getLocalBdUrl()+GV.getLocalBdName()+";create=true");
            if (conn!=null){
               String creartabla="DROP TABLE "+tableName;//(tableName.toLowerCase().equals("lente"))?"drop table lente":
                System.out.println(creartabla);
               try {
                    PreparedStatement pstm = conn.prepareStatement(creartabla);
                    pstm.execute();
                    pstm.close();
                    cerrar();
                } catch (SQLException ex) {
                    System.out.println("\"Error al borrar tabla "+tableName+", "+ex.getLocalizedMessage());
//                    OptionPane.showMsg("Error al crear tabla "+tableName, ex.getLocalizedMessage(),3);
                }
            }
        }catch(SQLException | ClassNotFoundException | ExceptionInInitializerError e){
         OptionPane.showMsg("Error al borrar tabla "+tableName,e.getMessage() ,  3);
        }
    }
    
    private static boolean createTable(String tableName, String columns){
        try{
            //obtenemos el driver de para mysql
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            columns = (columns.startsWith("(")) ? columns.trim():"("+columns.trim();
            columns = (columns.endsWith("))")) ? columns.trim():columns.trim()+")";
            //obtenemos la conexión
            //"jdbc:derby:.\\DB\\Derby.DB;create=true"
            cerrar();
            conn = DriverManager.getConnection("jdbc:derby:"+GV.getLocalBdUrl()+GV.getLocalBdName()+";create=true");
            if (conn!=null){
               String creartabla="create table "+tableName+columns;//(tableName.toLowerCase().equals("lente"))?"drop table lente":
               
//               String desc="disconnect;";
               try {
                    PreparedStatement pstm = conn.prepareStatement(creartabla);
                    pstm.execute();
                    pstm.close();

//                    PreparedStatement pstm2 = conn.prepareStatement(desc);
//                    pstm2.execute();
//                    pstm2.close();
                    cerrar();
                } catch (SQLException ex) {
                    return false;
//                    System.out.println("\"Error al crear tabla "+tableName+", "+ex.getLocalizedMessage());
                }
            }
        }catch(SQLException | ClassNotFoundException | ExceptionInInitializerError e){
         OptionPane.showMsg("Error al crear tabla "+tableName,e.getMessage() ,  3);
        }
        return true;
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
      } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
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
    }   catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            OptionPane.showMsg("Error en LCBD al obtener datos", "Detalle:\n"+ex.getLocalizedMessage(),3);
        }
    return data;
 }
}
