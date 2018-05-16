/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bd.BD;
import entities.Institucion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Softdirex
 */

public class InstitucionDao {
    
    /**
     * Devuelve una lista de instituciones dado el parametro id
     * @param id si es igual a cero, listara todos los datos activos, si es igual a menos uno listara todos los datos eliminados
     * @return Devuelve una lista de instituciones
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public ArrayList<Institucion> listar(int id) throws SQLException, ClassNotFoundException{
        String sql="SELECT * FROM `institucion` WHERE `ins_id`="+id+" ";
        if(id==0){
        sql="SELECT * FROM `institucion` WHERE `ins_estado`=1";
        }
        
         if(id==-1){
        sql="SELECT * FROM `institucion` WHERE `ins_estado`=0";
        }
        
        PreparedStatement consulta = BD.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        ArrayList<Institucion> lista = new ArrayList<>();
        while (datos.next()) {
            lista.add(new Institucion(
                    datos.getInt("ins_id")
                    , datos.getString("ins_nombre")
                    , datos.getString("ins_telefono")
                    , datos.getString("ins_mail")
                    , datos.getString("ins_direccion")
                    , datos.getString("ins_comuna")
                    , datos.getString("ins_ciudad")
                    , datos.getInt("ins_estado")
            ));
        }
        BD.cerrar();
        return lista;
    }
    
    public ArrayList<Institucion> listarNombre(String nombre) throws SQLException, ClassNotFoundException{
        String sql="SELECT * FROM `institucion` WHERE `ins_nombre` LIKE '%"+nombre+"%' AND ins_estado=1 ";
        
        PreparedStatement consulta = BD.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        ArrayList<Institucion> lista = new ArrayList<>();
        while (datos.next()) {
            lista.add(new Institucion(
                    datos.getInt("ins_id")
                    , datos.getString("ins_nombre")
                    , datos.getString("ins_telefono")
                    , datos.getString("ins_mail")
                    , datos.getString("ins_direccion")
                    , datos.getString("ins_comuna")
                    , datos.getString("ins_ciudad")
                    , datos.getInt("ins_estado")
            ));
        }
        BD.cerrar();
        return lista;
    }
    
    public ArrayList<Institucion> buscar(String valor, int estado) throws SQLException, ClassNotFoundException{
        String sql="SELECT * FROM `institucion` WHERE (`ins_nombre` LIKE '%"+valor+"%' OR ins_comuna LIKE '%"+valor+"%' OR ins_ciudad LIKE '%"+valor+"%') AND ins_estado="+estado+"";
        
        
        PreparedStatement consulta = BD.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        ArrayList<Institucion> lista = new ArrayList<>();
        while (datos.next()) {
            lista.add(new Institucion(
                    datos.getInt("ins_id")
                    , datos.getString("ins_nombre")
                    , datos.getString("ins_telefono")
                    , datos.getString("ins_mail")
                    , datos.getString("ins_direccion")
                    , datos.getString("ins_comuna")
                    , datos.getString("ins_ciudad")
                    , datos.getInt("ins_estado")
            ));
        }
        BD.cerrar();
        return lista;
    }
    
    public int encontrar(String nombre) throws SQLException, ClassNotFoundException{
        for (Institucion temp : listar(0)) {
            if(temp.getNombre().equals(nombre))
                return -1;
        }
        for (Institucion temp : listar(-1)) {
            if(temp.getNombre().equals(nombre))
                return -2;//eliminada
        }
        return 0;
    }
    
    public int obtenerId() throws SQLException, ClassNotFoundException{
        //
        String sql="select * from institucion order by ins_id desc limit 1";
        
        
        PreparedStatement consulta = BD.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        ArrayList<Institucion> lista = new ArrayList<>();
        while (datos.next()) {
            lista.add(new Institucion(
                    datos.getInt("ins_id")
                    , datos.getString("ins_nombre")
                    , datos.getString("ins_telefono")
                    , datos.getString("ins_mail")
                    , datos.getString("ins_direccion")
                    , datos.getString("ins_comuna")
                    , datos.getString("ins_ciudad")
                    , datos.getInt("ins_estado")
            ));
        }
        BD.cerrar();
        
        int id =0;
        for (Institucion temp : lista) {
            id=temp.getId();
        }
        id=id+1;
        return id;
    }
    
    public String agregar(Institucion nueva) throws SQLException, ClassNotFoundException{
        nueva.setId(obtenerId());
        ArrayList<Institucion> lista=listar(nueva.getId());
        if(lista.size()>0){
        return nueva.getNombre()+" ya se encuentra registrado.";
        }
        
        if(encontrar(nueva.getNombre())==-1){
            return "Existe una institución con el mismo nombre.";
        }
        
        if(encontrar(nueva.getNombre())==-2){
            return "Existe una institución con el mismo nombre pero ha sido eliminada.";
        }
        
        if(nueva != null){
            try {
                PreparedStatement insert = BD.obtener().prepareStatement(
                        "INSERT INTO `institucion`(`ins_id`, `ins_nombre`, `ins_telefono`, `ins_mail`, `ins_direccion`, `ins_comuna`, `ins_ciudad`, `ins_estado`)"
                                + " VALUES('"
                                +nueva.getId()+"', '"
                                +nueva.getNombre()+"', '"
                                +nueva.getTelefono()+"', '"
                                +nueva.getEmail()+"', '"
                                +nueva.getDireccion()+"', '"
                                +nueva.getComuna()+"', '"
                                +nueva.getCiudad()+"', '"
                                +nueva.getEstado()
                                +"')");
                if(insert.executeUpdate()!=0){
                    BD.cerrar();
                    return "Se ha agregado "+nueva.getNombre()+" correctamente";
                }
            } catch (Exception e) {
                BD.cerrar();
                return "ERROR :"+e;
            }
        }
        BD.cerrar();
        return "La Institución tiene datos vacios";
    }
    
    public boolean modificar(Institucion temp) throws SQLException, ClassNotFoundException{
        if(temp != null){
            try {
                PreparedStatement insert = BD.obtener().prepareStatement(
                        "UPDATE `institucion` SET"
                        + "`ins_nombre`='"+temp.getNombre()+"'"
                        + ",`ins_telefono`='"+temp.getTelefono()+"'"
                        + ",`ins_mail`='"+temp.getEmail()+"'"
                        + ",`ins_direccion`='"+temp.getDireccion()+"'"
                        + ",`ins_comuna`='"+temp.getComuna()+"'"
                        + ",`ins_ciudad`='"+temp.getCiudad()+"'"
                        + ",`ins_estado`='"+temp.getEstado()+"'"
                        + "WHERE `ins_id`='"+temp.getId()+"'");
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
                        "UPDATE `institucion` SET"
                        + "`ins_estado`='0'"
                        + "WHERE `ins_id`='"+id+"'");
                System.out.println("lineas afectadas"+insert.executeUpdate());
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
                        "UPDATE `institucion` SET"
                        + "`ins_estado`='1'"
                        + "WHERE `ins_id`='"+id+"'");
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
