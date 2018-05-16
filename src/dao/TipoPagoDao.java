/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bd.BD;
import entities.TipoPago;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author home
 */
public class TipoPagoDao {
    public ArrayList<TipoPago> listar(int id) throws SQLException, ClassNotFoundException{
        String sql="SELECT * FROM `tipo_pago` WHERE `tp_id`='"+id+"' ";
        if(id==0){
        sql="SELECT * FROM `tipo_pago` WHERE `tp_estado`='1'";
        }
        
         if(id==-1){
        sql="SELECT * FROM `tipo_pago` WHERE `tp_estado`='0'";
        }
        
        PreparedStatement consulta = BD.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        ArrayList<TipoPago> lista = new ArrayList<>();
        while (datos.next()) {
            lista.add(new TipoPago(
                    datos.getInt("tp_id")
                    , datos.getString("tp_nombre")
                    , datos.getInt("tp_estado")
            ));
        }
        BD.cerrar();
        return lista;
    }
    
    public ArrayList<TipoPago> buscar(String valor, int estado) throws SQLException, ClassNotFoundException{
        String sql="SELECT * FROM `tipo_pago` WHERE `tp_nombre` LIKE '%"+valor+"%' AND tp_estado='"+estado+"'";
        
        
        PreparedStatement consulta = BD.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        ArrayList<TipoPago> lista = new ArrayList<>();
        while (datos.next()) {
            lista.add(new TipoPago(
                    datos.getInt("tp_id")
                    , datos.getString("tp_nombre")
                    , datos.getInt("tp_estado")
            ));
        }
        BD.cerrar();
        return lista;
    }
    
    
    public int obtenerId() throws SQLException, ClassNotFoundException{
        //
        String sql="select * from tipo_pago order by tp_id desc limit 1";
        
        
        PreparedStatement consulta = BD.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        ArrayList<TipoPago> lista = new ArrayList<>();
        while (datos.next()) {
            lista.add(new TipoPago(
                    datos.getInt("tp_id")
                    , datos.getString("tp_nombre")
                    , datos.getInt("tp_estado")
            ));
        }
        BD.cerrar();
        
        int id =0;
        for (TipoPago temp : lista) {
            id=temp.getId();
        }
        id=id+1;
        return id;
    }
    
    public int encontrar(String nombre) throws SQLException, ClassNotFoundException{
        for (TipoPago temp : listar(0)) {
            if(temp.getNombre().equals(nombre))
                return -1;
        }
        for (TipoPago temp : listar(-1)) {
            if(temp.getNombre().equals(nombre))
                return -2;//eliminada
        }
        return 0;
    }
     
    public String agregar(TipoPago nuevo) throws SQLException, ClassNotFoundException{
        nuevo.setId(obtenerId());
        ArrayList<TipoPago> lista=listar(nuevo.getId());
        if(lista.size()>0){
        return "Valor ya se encuentra registrado";
        } 
        
        if(encontrar(nuevo.getNombre())==-1){
            return "Existe un registro con el mismo nombre.";
        }
        
        if(encontrar(nuevo.getNombre())==-2){
            return "Existe un registro con el mismo nombre pero ha sido eliminada.";
        }
        
        if(nuevo != null){
            try {
                PreparedStatement insert = BD.obtener().prepareStatement(
                        "INSERT INTO `tipo_pago`(`tp_id`, `tp_nombre`, `tp_estado`)"
                                + " VALUES('"
                                +nuevo.getId()+"', '"
                                +nuevo.getNombre()+"', '"
                                +nuevo.getEstado()
                                +"')");
                if(insert.executeUpdate()!=0){
                    BD.cerrar();
                    return "Se ha agregado el registro "+nuevo.getNombre()+" correctamente";
                }
            } catch (Exception e) {
                BD.cerrar();
                return "ERROR :"+e;
            }
        }
        BD.cerrar();
        return "El registro tiene datos vacios";
    }
    
    public boolean modificar(TipoPago tipoPago) throws SQLException, ClassNotFoundException{
        if(tipoPago != null){
            try {
                PreparedStatement insert = BD.obtener().prepareStatement(
                        "UPDATE `tipo_pago` SET"
                        + "`tp_nombre`='"+tipoPago.getNombre()+"'"
                        + ",`tp_estado`='"+tipoPago.getEstado()+"'"
                        + "WHERE `tp_id`='"+tipoPago.getId()+"'");
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
    
    public boolean eliminar(int id) throws SQLException, ClassNotFoundException{
        if(id > 0){
            try {
                PreparedStatement insert = BD.obtener().prepareStatement(
                        "UPDATE `tipo_pago` SET"
                        + "`tp_estado`='0'"
                        + "WHERE `tp_id`='"+id+"'");
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
    
    public boolean restaurar(int id) throws SQLException, ClassNotFoundException{
        if(id > 0){
            try {
                PreparedStatement insert = BD.obtener().prepareStatement(
                        "UPDATE `tipo_pago` SET"
                        + "`tp_estado`='1'"
                        + "WHERE `tp_id`='"+id+"'");
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
