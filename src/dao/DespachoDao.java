/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bd.BD;
import entities.Despacho;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author home
 */
public class DespachoDao {
    public ArrayList<Despacho> listar(int idFolio) throws SQLException, ClassNotFoundException{
        String sql="SELECT * FROM `despacho` WHERE `ficha_fch_id`='"+idFolio+"' ";
        PreparedStatement consulta = BD.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        ArrayList<Despacho> lista = new ArrayList<>();
        while (datos.next()) {
            lista.add(new Despacho(
                    datos.getInt("dsp_id")
                    , datos.getString("dsp_rut")
                    , datos.getString("dsp_nombre")
                    , datos.getDate("dsp_fecha")
                    , datos.getInt("ficha_fch_id"))
            );
        }
        BD.cerrar();
        
        return lista;
    }
     
     
    
     public int obtenerId() throws SQLException, ClassNotFoundException{
        //
        String sql="select * from despacho order by dsp_id desc limit 1";
        
        
        PreparedStatement consulta = BD.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        ArrayList<Despacho> lista = new ArrayList<>();
        while (datos.next()) {
            lista.add(new Despacho(
                    datos.getInt("dsp_id")
                    , datos.getString("dsp_rut")
                    , datos.getString("dsp_nombre")
                    , datos.getDate("dsp_fecha")
                    , datos.getInt("ficha_fch_id"))
            );
        }
        BD.cerrar();
        
        int id =0;
        for (Despacho temp : lista) {
            id=temp.getId();
        }
        id=id+1;
        return id;
    }
     
     
     public Despacho encontrar( int idFolio ) throws SQLException, ClassNotFoundException{
         try{
         for (Despacho temp : listar(idFolio)) {
             if(temp.getIdFicha() == idFolio)
                 return temp;
         }
         return null;
         }catch(Exception e){
             return null;
         }
    }
     
    public boolean agregar(Despacho despacho) throws SQLException, ClassNotFoundException{
        despacho.setId(obtenerId());
        
        
        if(despacho != null){
            java.util.Date fecha = despacho.getFecha();// crea una variable tipo Date
            java.sql.Date sqlfecha = new java.sql.Date(fecha.getTime());
            
            ArrayList<Despacho> lista=listar(despacho.getIdFicha());
            if(lista.size()>0){
            return false;
            } 
            try {
                PreparedStatement insert = BD.obtener().prepareStatement(
                        "INSERT INTO `despacho`(`dsp_id`, `dsp_rut`, `dsp_nombre`, `dsp_fecha`, `ficha_fch_id`)"
                                + " VALUES('"
                                +despacho.getId()+"', '"
                                +despacho.getRut()+"', '"
                                +despacho.getNombre()+"', '"
                                +sqlfecha+"','"
                                +despacho.getIdFicha()
                                +"')");
                if(insert.executeUpdate()!=0){
                    BD.cerrar();
                    return true;
                }
            } catch (Exception e) {
                BD.cerrar();
                System.out.println("Error: "+e.getMessage());
                return false;
            }
        }
        BD.cerrar();
        return false;
    }
    
    public boolean modificar(Despacho despacho) throws SQLException, ClassNotFoundException{
        if(despacho != null){
            try {
                PreparedStatement insert = BD.obtener().prepareStatement(
                        "UPDATE `despacho` SET"
                        + "`dsp_nombre`='"+despacho.getNombre()+"'"
                        + ",`dsp_rut`='"+despacho.getRut()+"'"
                        + ",`dsp_fecha`='"+despacho.getFecha()+"'"
                        + "WHERE `dsp_id`='"+despacho.getId()+"'");
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
