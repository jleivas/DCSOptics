/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bd.BD;
import entities.Descuento;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Lenovo G470
 */
public class DescuentoDao {
    
     public ArrayList<Descuento> listar(int idDescuento) throws SQLException, ClassNotFoundException{
        String sql="SELECT * FROM `descuento` WHERE `des_id`='"+idDescuento+"' ";
        if(idDescuento==0){
        sql="SELECT * FROM `descuento` WHERE des_estado='1'";
        }
        if(idDescuento==-1){
        sql="SELECT * FROM `descuento` WHERE des_estado='0'";
        }
        
        PreparedStatement consulta = BD.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        ArrayList<Descuento> lista = new ArrayList<>();
        while (datos.next()) {
            lista.add(new Descuento(
                    Integer.parseInt(datos.getString("des_id"))
                    , datos.getString("des_nombre")
                    , datos.getString("des_descripcion")
                    , Integer.parseInt(datos.getString("des_porc"))
                    , Integer.parseInt(datos.getString("des_estado"))
            ));
        }
        BD.cerrar();
        
        return lista;
    }
     
     public ArrayList<Descuento> buscar(String valor, int estado) throws SQLException, ClassNotFoundException{
        String sql="SELECT * FROM `descuento` WHERE (`des_nombre` LIKE '%"+valor+"%' OR `des_descripcion` LIKE '%"+valor+"%' OR `des_porc` LIKE '%"+valor+"%') AND des_estado="+estado+"";
        
        
        PreparedStatement consulta = BD.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        ArrayList<Descuento> lista = new ArrayList<>();
        while (datos.next()) {
            lista.add(new Descuento(
                    Integer.parseInt(datos.getString("des_id"))
                    , datos.getString("des_nombre")
                    , datos.getString("des_descripcion")
                    , Integer.parseInt(datos.getString("des_porc"))
                    , Integer.parseInt(datos.getString("des_estado"))
            ));
        }
        BD.cerrar();
        return lista;
    }
    
     public int obtenerId() throws SQLException, ClassNotFoundException{
        //
        String sql="select * from descuento order by des_id desc limit 1";
        
        
        PreparedStatement consulta = BD.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        ArrayList<Descuento> lista = new ArrayList<>();
        while (datos.next()) {
            lista.add(new Descuento(
                    Integer.parseInt(datos.getString("des_id"))
                    , datos.getString("des_nombre")
                    , datos.getString("des_descripcion")
                    , Integer.parseInt(datos.getString("des_porc"))
                    , Integer.parseInt(datos.getString("des_estado"))
            ));
        }
        BD.cerrar();
        
        int id =0;
        for (Descuento temp : lista) {
            id=temp.getId();
        }
        id=id+1;
        return id;
    }
     
//    public int encontrar(String nombre) throws SQLException, ClassNotFoundException{
//        for (Descuento temp : listar(0)) {
//            if(temp.getNombre().equals(nombre))
//                return -1;
//        }
//        for (Descuento temp : listar(-1)) {
//            if(temp.getNombre().equals(nombre))
//                return -2;//eliminada
//        }
//        return 0;
//    }
     
     public int encontrar( String nombre ) throws SQLException, ClassNotFoundException{
        String sql="SELECT COUNT(`des_nombre`) as 'inactivo'"
                + ",(SELECT COUNT(`des_nombre`) FROM `descuento` WHERE `des_nombre`='"+nombre+"'  and `des_estado`=1) as 'activo'"
                + "FROM `descuento` WHERE `des_nombre`='"+nombre+"'  and `des_estado`=0";
        
        PreparedStatement consulta = BD.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        int activo=0;
        int inactivo=0;
        while (datos.next()) {
            activo=datos.getInt("activo");
            inactivo=datos.getInt("inactivo");
        }
        BD.cerrar();
        if(activo>0){return 1;}
        if(inactivo>0){return 2;}
        return 0;
    }
     
    public String agregar(Descuento descuento) throws SQLException, ClassNotFoundException{
        descuento.setId(obtenerId());
        ArrayList<Descuento> lista=listar(descuento.getId());
        if(lista.size()>0){
        return "Ya se encuentra registrado el descuento "+descuento.getNombre();
        } 
        
        if(encontrar(descuento.getNombre())==1){
            return "Existe un registro con el mismo nombre.";
        }
        
        if(encontrar(descuento.getNombre())==2){
            return "Existe un registro con el mismo nombre pero ha sido desactivado.";
        }
        
        if(descuento != null){
            try {
                PreparedStatement insert = BD.obtener().prepareStatement(
                        "INSERT INTO `descuento`(`des_id`, `des_nombre`, `des_descripcion`, `des_porc`, `des_estado`)"
                                + " VALUES('"
                                +descuento.getId()+"', '"
                                +descuento.getNombre()+"', '"
                                +descuento.getDescripcion()+"', '"
                                +descuento.getPorcetange()+"','"
                                +descuento.getEstado()
                                +"')");
                if(insert.executeUpdate()!=0){
                    BD.cerrar();
                    return "Se ha agregado el descuento "+descuento.getNombre()+" correctamente";
                }
            } catch (Exception e) {
                BD.cerrar();
                return "Error :"+e;
            }
        }
        BD.cerrar();
        return "El descuento tiene datos vacios";
    }
    
    public boolean modificar(Descuento descuento) throws SQLException, ClassNotFoundException{
        if(encontrar(descuento.getNombre())==0){
            return false;
        }
        
        if(encontrar(descuento.getNombre())==2){
            return false;
        }
        if(descuento != null){
            try {
                PreparedStatement insert = BD.obtener().prepareStatement(
                        "UPDATE `descuento` SET"
                        + "`des_nombre`='"+descuento.getNombre()+"'"
                        + ",`des_descripcion`='"+descuento.getDescripcion()+"'"
                        + ",`des_porc`='"+descuento.getPorcetange()+"'"
                        + ",`des_estado`='"+descuento.getEstado()+"'"
                        + "WHERE `des_id`='"+descuento.getId()+"'");
                if(insert.executeUpdate()!=0){
                    BD.cerrar();
                    return true;
                }
            } catch (Exception e) {
                BD.cerrar();
                return false;
            }
        }
        BD.cerrar();
        return false;
    }
    
     public boolean eliminar(int idDescuento) throws SQLException, ClassNotFoundException{
        if(idDescuento>0){
            try {
                PreparedStatement insert = BD.obtener().prepareStatement(
                        "UPDATE `descuento` SET"
                        + "`des_estado`='0'"
                        + "WHERE `des_id`='"+idDescuento+"'");
                
                if(insert.executeUpdate()!=0){
                    BD.cerrar();
                    return true;
                }
            } catch (Exception e) {
                BD.cerrar();
                return false;
            }
        }
        BD.cerrar();
        return false;
    }
     
    
     public boolean restaurar(int idDescuento) throws SQLException, ClassNotFoundException{
        if(idDescuento>0){
            try {
                PreparedStatement insert = BD.obtener().prepareStatement(
                        "UPDATE `descuento` SET"
                        + "`des_estado`='1'"
                        + "WHERE `des_id`='"+idDescuento+"'");
                
                if(insert.executeUpdate()!=0){
                    BD.cerrar();
                    return true;
                }
            } catch (Exception e) {
                BD.cerrar();
                return false;
            }
        }
        BD.cerrar();
        return false;
    }
    
}
