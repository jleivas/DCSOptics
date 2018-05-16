/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bd.BD;
import entities.Lente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo G470
 */
public class LenteDao {
    
    public boolean agregar(Lente lente) throws SQLException, ClassNotFoundException{
        if(lente != null){
               PreparedStatement consulta = BD.obtener().prepareStatement("SELECT `len_id` FROM `lente` WHERE `len_id`='"+lente.getId()+"' and `len_marca`='"+lente.getMarca()+"' and `len_color`='"+lente.getColor()+"'");
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    BD.cerrar();
                    JOptionPane.showMessageDialog(null, "Este producto ya se encuentra registrado", "Error", JOptionPane.WARNING_MESSAGE);
                    return false;
                }    
            
                PreparedStatement insert = BD.obtener().prepareStatement(
                        "INSERT INTO lente VALUES('"
                                +lente.getId()+"', '"
                                +lente.getColor()+"', '"
                                +lente.getTipo()+"', '"
                                +lente.getMarca()+"', '"
                                +lente.getMaterial()+"', '"
                                +lente.getFlex()+"', '"
                                +lente.getClasificacion()+"', '"
                                +lente.getDescripcion()+"', '"
                                +lente.getPrecioRef()+"', '"
                                +lente.getPrecioAct()+"', '"
                                +lente.getStock()+"', '"
                                +lente.getStockMin()+"', '"
                                +lente.getEstado()+"')"
                               );
                if(insert.executeUpdate()!=0){
                    BD.cerrar();
                    JOptionPane.showMessageDialog(null, "Operación realizada correctamente");
                    return true;
                }
        }
        JOptionPane.showMessageDialog(null, "Ha ocurrido un error", "Error", JOptionPane.WARNING_MESSAGE);
        return false;
    }
    
    public boolean modificar(Lente lente) throws SQLException, ClassNotFoundException{
        PreparedStatement insert = BD.obtener().prepareStatement(
                "UPDATE `lente` set `len_color` = '"+lente.getColor()
                        +"', `len_marca` = '"+lente.getMarca()
                        +"', `len_tipo` = '"+lente.getTipo()
                        +"', `len_material` = '"+lente.getMaterial()
                        +"', `len_flex` = '"+lente.getFlex()
                        +"', `len_clasificacion` = '"+lente.getClasificacion()
                        +"', `len_descripcion` = '"+lente.getDescripcion()
                        +"', `len_precio_ref` = '"+lente.getPrecioRef()
                        +"', `len_precio_act` = '"+lente.getPrecioAct()
                        +"', `len_stock` = '"+lente.getStock()
                        +"', `len_stock_min` = '"+lente.getStockMin()
                        +"', `len_estado` = '"+lente.getEstado()
                        +"' WHERE `len_id` = '"+lente.getId()+"' and `len_color` = '"+lente.getColor()+"' and `len_marca` = '"+lente.getMarca()+"'");
        if(insert.executeUpdate()!=0){
            BD.cerrar();
            JOptionPane.showMessageDialog(null, "Operación realizada correctamente");
            return true;
        }
        else{
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error", "Error", JOptionPane.WARNING_MESSAGE);
            BD.cerrar();
            return false;
        }
    }
    
    public ArrayList<Lente> listar(String cadena) throws SQLException, ClassNotFoundException{
     String sql="SELECT * FROM `lente` WHERE `len_id`='"+cadena+"' AND `len_estado`=1";
     if(cadena.equals("activos")){sql="SELECT * FROM `lente` WHERE `len_estado`=1";}
     if(cadena.equals("inactivos")){sql="S"
             + "ELECT * FROM `lente` WHERE `len_estado`=0";}
     if(cadena.equals("todos")){sql="SELECT * FROM `lente`";}
     
     PreparedStatement consulta = BD.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        ArrayList<Lente> lista = new ArrayList<>();
        while (datos.next()) {
            lista.add(new Lente(datos.getString("len_id")
                               ,datos.getString("len_color")
                               ,datos.getString("len_tipo")
                               ,datos.getString("len_marca")
                               ,datos.getString("len_material")
                               ,datos.getInt("len_flex")
                               ,datos.getInt("len_clasificacion")
                               ,datos.getString("len_descripcion")
                               ,datos.getInt("len_precio_ref")
                               ,datos.getInt("len_precio_act")
                               ,datos.getInt("len_stock")
                               ,datos.getInt("len_stock_min")
                               ,datos.getInt("len_estado")));
        }
        BD.cerrar();
        return lista;
    }
    
     public ArrayList<Lente> listarConStock(String cadena) throws SQLException, ClassNotFoundException{
     String sql="SELECT * FROM `lente` WHERE `len_id`='"+cadena+"' AND `len_estado`=1";
     if(cadena.equals("activos")){sql="SELECT * FROM `lente` WHERE `len_estado`=1";}
     if(cadena.equals("inactivos")){sql="S"
             + "ELECT * FROM `lente` WHERE `len_estado`=0";}
     if(cadena.equals("todos")){sql="SELECT * FROM `lente`";}
     
     PreparedStatement consulta = BD.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        ArrayList<Lente> lista = new ArrayList<>();
        while (datos.next()) {
            if(datos.getInt("len_stock") > 0){
            lista.add(new Lente(datos.getString("len_id")
                               ,datos.getString("len_color")
                               ,datos.getString("len_tipo")
                               ,datos.getString("len_marca")
                               ,datos.getString("len_material")
                               ,datos.getInt("len_flex")
                               ,datos.getInt("len_clasificacion")
                               ,datos.getString("len_descripcion")
                               ,datos.getInt("len_precio_ref")
                               ,datos.getInt("len_precio_act")
                               ,datos.getInt("len_stock")
                               ,datos.getInt("len_stock_min")
                               ,datos.getInt("len_estado")));
            }
        }
        BD.cerrar();
        return lista;
    }
     
     
     
//     public ArrayList<Lente> cargar(String cadena) throws SQLException, ClassNotFoundException{
//     String sql="SELECT * FROM `lente` WHERE `len_id`='"+cadena+"'";
//     
//     PreparedStatement consulta = BD.obtener().prepareStatement(sql);
//        ResultSet datos = consulta.executeQuery();
//        ArrayList<Lente> lista = new ArrayList<>();
//        while (datos.next()) {
//            lista.add(new Lente(datos.getString("len_id")
//                               ,datos.getString("len_color")
//                               ,datos.getString("len_tipo")
//                               ,datos.getString("len_marca")
//                               ,datos.getString("len_material")
//                               ,datos.getInt("len_flex")
//                               ,datos.getInt("len_clasificacion")
//                               ,datos.getString("len_descripcion")
//                               ,datos.getInt("len_precio_ref")
//                               ,datos.getInt("len_precio_act")
//                               ,datos.getInt("len_stock")
//                               ,datos.getInt("len_stock_min")
//                               ,datos.getInt("len_estado")));
//        }
//        BD.cerrar();
//        return lista;
//    }
     
     public ArrayList<Lente> buscarEliminados(String cadena) throws SQLException, ClassNotFoundException{
     String sql="SELECT * FROM `lente` WHERE `len_id`='"+cadena+"' AND `len_estado`=0";
     
     PreparedStatement consulta = BD.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        ArrayList<Lente> lista = new ArrayList<>();
        while (datos.next()) {
            lista.add(new Lente(datos.getString("len_id")
                               ,datos.getString("len_color")
                               ,datos.getString("len_tipo")
                               ,datos.getString("len_marca")
                               ,datos.getString("len_material")
                               ,datos.getInt("len_flex")
                               ,datos.getInt("len_clasificacion")
                               ,datos.getString("len_descripcion")
                               ,datos.getInt("len_precio_ref")
                               ,datos.getInt("len_precio_act")
                               ,datos.getInt("len_stock")
                               ,datos.getInt("len_stock_min")
                               ,datos.getInt("len_estado")));
        }
        BD.cerrar();
        return lista;
    }
     
    public boolean validar(String id) throws SQLException, ClassNotFoundException{
     PreparedStatement consulta = BD.obtener().prepareStatement("SELECT `len_id` FROM `lente` WHERE `len_id`='"+id+"'");
        ResultSet datos = consulta.executeQuery();
        while (datos.next()) {
            return true;
        }
        BD.cerrar();
        return false;
    }
    
    public boolean descontar(String codigo, int cantidad,String observacion) throws SQLException, ClassNotFoundException{
        String[] idLente = codigo.split("-");
        int descontar=cantidad;
        PreparedStatement consulta = BD.obtener().prepareStatement("SELECT `len_stock`,`len_stock_min`,(SELECT COUNT(`rb_id`) FROM `registro_bajas` )as cantidadRegistros FROM `lente` WHERE `len_id`='"+idLente[0]+"' and `len_marca`='"+idLente[1]+"' and `len_color`='"+idLente[2]+"'");
            ResultSet datos = consulta.executeQuery();
        int stock=0;
        int stockMin=0;
        int cantidadRegistros=0;
            while (datos.next()) {
                stock=datos.getInt("len_stock");
                stockMin=datos.getInt("len_stock_min");
                cantidadRegistros=datos.getInt("cantidadRegistros");
            }
        int resto=stock-cantidad;
        cantidad=stock-cantidad;
        
        if(cantidad<0){JOptionPane.showMessageDialog(null, "No se puede realizar la operación con el producto\n su stock actual es de: "+stock+" unidades.\n Al realizar el descuento el stock queda en "+cantidad+"", "Stock Bajo", JOptionPane.WARNING_MESSAGE);
        BD.cerrar();
        return false;
        }
        
        consulta = BD.obtener().prepareStatement("UPDATE `lente` set `len_stock` = '"+cantidad+"' WHERE `len_id`='"+idLente[0]+"' and `len_marca`='"+idLente[1]+"' and `len_color`='"+idLente[2]+"'");
        
        if(resto<=stockMin){JOptionPane.showMessageDialog(null, "El stock del producto "+codigo+" es bajo", "Stock Bajo", JOptionPane.WARNING_MESSAGE);
        }
        
        if(consulta.executeUpdate()!=0){
            //inserto en el registro_descuento
            cantidadRegistros++;
            java.util.Date fecha = new Date();// crea una variable tipo Date
            java.sql.Date sqlfecha = new java.sql.Date(fecha.getTime());//la transforma a sql.Date
            PreparedStatement insert = BD.obtener().prepareStatement(
                        "INSERT INTO registro_bajas VALUES('"
                                +cantidadRegistros+"', '"//rb_id
                                +sqlfecha+"', '"//rb_fecha
                                +codigo+"', '"//rb_len_id
                                +descontar+"', '"//rb_cantidad
                                +observacion+"')"//rb_obs
                               );
                if(insert.executeUpdate()!=0){
                    BD.cerrar();
                    JOptionPane.showMessageDialog(null, "Operación realizada correctamente");
                    return true;
                }else{
                 BD.cerrar();
                 return false;}
        }
        else{
            BD.cerrar();
            return false;
        }
    }
    
    public boolean descontarDesdeFicha(String codigo, int cantidad) throws SQLException, ClassNotFoundException{
        String[] idLente = codigo.split("-");
        PreparedStatement consulta = BD.obtener().prepareStatement("SELECT `len_stock`,`len_stock_min` FROM `lente` WHERE `len_id`='"+idLente[0]+"' and `len_marca`='"+idLente[1]+"' and `len_color`='"+idLente[2]+"'");
        ResultSet datos = consulta.executeQuery();
        int stock=0;
        int stockMin=0;
            while (datos.next()) {
                stock=datos.getInt("len_stock");
                stockMin=datos.getInt("len_stock_min");
            }
        int resto=stock-cantidad;
        cantidad=stock-cantidad;
        
        if(cantidad<0){JOptionPane.showMessageDialog(null, "No se puede realizar la operación con el producto "+codigo+"\n su stock actual es de: "+stock+" unidades.","Stock Bajo", JOptionPane.WARNING_MESSAGE);
        BD.cerrar();
        return false;
        }
        
        consulta = BD.obtener().prepareStatement("UPDATE `lente` set `len_stock` = '"+cantidad+"' WHERE `len_id`='"+idLente[0]+"' and `len_marca`='"+idLente[1]+"' and `len_color`='"+idLente[2]+"'");
        
        if(resto<=stockMin){JOptionPane.showMessageDialog(null, "El stock del armazón "+codigo+" es bajo", "Stock Bajo", JOptionPane.WARNING_MESSAGE);
        }
        
        if(consulta.executeUpdate()!=0){
            
        BD.cerrar();
        return true;        
        }else{
            BD.cerrar();
            return false;
        }
    }
    
    public boolean aumentar(String id, int cantidad) throws SQLException, ClassNotFoundException{
        
        PreparedStatement consulta = BD.obtener().prepareStatement("SELECT `len_stock`,`len_stock_min` FROM `lente` WHERE `len_id`='"+id+"'");
            ResultSet datos = consulta.executeQuery();
        int stock=0;
        int stockMin=0;
            while (datos.next()) {
                stock=datos.getInt("len_stock");
                stockMin=datos.getInt("len_stock_min");
            }
            
        int cant=stock+cantidad;
        int resp;
        if(cantidad<=1){
        resp = JOptionPane.showConfirmDialog(null, "Estas tratando de ingresar "+cantidad+" unidadad del producto :"+id+"\n El stock actual es de "+stock+" y aumentara a "+cant+" unidadades.\n ¿Estas seguro?", "", JOptionPane.YES_NO_OPTION);
        }else{
         resp= JOptionPane.showConfirmDialog(null, "Estas tratando de ingresar "+cantidad+" unidades del producto :"+id+"\n El stock actual es de "+stock+" y aumentara a "+cant+" unidades.\n ¿Estas seguro?", "", JOptionPane.YES_NO_OPTION);}
        
        if(resp==1){
        BD.cerrar();
        return false;
        }
        
        consulta = BD.obtener().prepareStatement("UPDATE `lente` set `len_stock` = '"+cant+"' WHERE `len_id` = '"+id+"'");
        
        if((stock+cantidad)<=stockMin){JOptionPane.showMessageDialog(null, "El stock del producto "+id+" es bajo", "Stock Bajo", JOptionPane.WARNING_MESSAGE);
        }
        
        if(consulta.executeUpdate()!=0){
            BD.cerrar();
            JOptionPane.showMessageDialog(null, "Operación realizada correctamente");
            return true;
        }else{
            BD.cerrar();
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error", "Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }
    
    public boolean bloquear(String codigo) throws SQLException, ClassNotFoundException{
        String[] idLente = codigo.split("-");
        
        PreparedStatement consulta = BD.obtener().prepareStatement("UPDATE `lente` set `len_estado` = '0' WHERE `len_id`='"+idLente[0]+"' and `len_marca`='"+idLente[1]+"' and `len_color`='"+idLente[2]+"'");
      
        if(consulta.executeUpdate()!=0){
            BD.cerrar();
            return true;
        }else{
            BD.cerrar();
            return false;
        }
    }
    
    public boolean desbloquear(String codigo) throws SQLException, ClassNotFoundException{
        
        String[] idLente = codigo.split("-");
        PreparedStatement consulta = BD.obtener().prepareStatement("UPDATE `lente` set `len_estado` = '1' WHERE `len_id`='"+idLente[0]+"' and `len_marca`='"+idLente[1]+"' and `len_color`='"+idLente[2]+"'");
      
        if(consulta.executeUpdate()!=0){
            BD.cerrar();
            return true;
        }else{
            BD.cerrar();
            return false;
        }
    }

    public int valorTotal() throws SQLException, ClassNotFoundException {
        String sql="SELECT len_stock as stock, len_precio_act as precio FROM `lente` where len_estado=1";
        
        PreparedStatement consulta = BD.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        int st=0;
        int pr=0;
        int total=0;
        while (datos.next()) {
            st=datos.getInt("stock");
            pr=datos.getInt("precio");
            total = total + (st*pr);
        }
        BD.cerrar();
        return total;
    }

    public int unids() throws SQLException, ClassNotFoundException {
        String sql="SELECT len_stock as stock FROM `lente` where len_estado=1";
        
        PreparedStatement consulta = BD.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        int total=0;
        while (datos.next()) {
            total = total + datos.getInt("stock");
        }
        BD.cerrar();
        return total;
    }

    public int items() throws SQLException, ClassNotFoundException {
        String sql="SELECT COUNT(len_id) as items FROM `lente` where len_estado=1";
        
        PreparedStatement consulta = BD.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        int total=0;
        while (datos.next()) {
            total = datos.getInt("items");
        }
        BD.cerrar();
        return total;
    }
    
    
    
    
    
    
    
    
     
    
    
    
}
