/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bd.BD;
import entities.Cristal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author home
 */
public class CristalDao {
    
    /**
     * Lista cristales dependiendo del parametro
     * @param id Si es 0 retorna todos los activos; si es -1 retorna los eliminado, si es mayor que cero retorna por id ingresado
     * @return retorna un ArrayList de Cristales
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public ArrayList<Cristal> listar(int id) throws SQLException, ClassNotFoundException{
        String sql="SELECT * FROM `cristal` WHERE `cri_id`="+id+" ";
        if(id==0){
        sql="SELECT * FROM `cristal` WHERE `cri_estado`=1";
        }
        
         if(id==-1){
        sql="SELECT * FROM `cristal` WHERE `cri_estado`=0";
        }
        
        PreparedStatement consulta = BD.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        ArrayList<Cristal> lista = new ArrayList<>();
        while (datos.next()) {
            lista.add(new Cristal(
                    datos.getInt("cri_id")
                    , datos.getString("cri_nombre")
                    , datos.getInt("cri_precio")
                    , datos.getInt("cri_estado")
            ));
        }
        BD.cerrar();
        return lista;
    }
    
    public boolean guardar(Cristal cristal) throws SQLException, ClassNotFoundException {
        ArrayList<Cristal> lista=listarPorNombre(cristal.getNombre());
        if(lista.size()>0){
        return false;
        } 
        
        cristal.setId(obtenerId());
        
        if(cristal != null){
            try {
                PreparedStatement insert = BD.obtener().prepareStatement(
                        "INSERT INTO `cristal`(`cri_id`, `cri_nombre`, `cri_precio`, `cri_estado`)"
                                + " VALUES('"
                                +cristal.getId()+"', '"
                                +cristal.getNombre()+"', '"
                                +cristal.getPrecio()+"', '"
                                +cristal.getEstado()
                                +"')");
                if(insert.executeUpdate()!=0){
                    BD.cerrar();
                    return true;
                }
            } catch (Exception e) {
                BD.cerrar();
                JOptionPane.showMessageDialog(null, "Error inseperado: "+e.getMessage(),"Guardar Cristal",JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
        BD.cerrar();
        return false;
    }

    public ArrayList<Cristal> listarPorNombre(String nombre) throws SQLException, ClassNotFoundException {
        String sql="SELECT * FROM `cristal` WHERE `cri_nombre` LIKE '%"+nombre+"%' AND cri_estado=1 ";
        
        PreparedStatement consulta = BD.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        ArrayList<Cristal> lista = new ArrayList<>();
        while (datos.next()) {
            lista.add(new Cristal(
                    datos.getInt("cri_id")
                    , datos.getString("cri_nombre")
                    , datos.getInt("cri_precio")
                    , datos.getInt("cri_estado")
            ));
        }
        BD.cerrar();
        return lista;
    }

    private int obtenerId() throws SQLException, ClassNotFoundException {
        String sql="select * from cristal order by cri_id desc limit 1";
        
        
        PreparedStatement consulta = BD.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        ArrayList<Cristal> lista = new ArrayList<>();
        while (datos.next()) {
            lista.add(new Cristal(
                    datos.getInt("cri_id")
                    , datos.getString("cri_nombre")
                    , datos.getInt("cri_precio")
                    , datos.getInt("cri_estado")
            ));
        }
        BD.cerrar();
        
        int id =0;
        for (Cristal temp : lista) {
            id=temp.getId();
        }
        id=id+1;
        return id;
    }

    public Cristal cargar(int id) throws SQLException, ClassNotFoundException {
        for (Cristal temp : listar(id)) {
            if(temp.getId() == id)
                return temp;
        }
        return null;
    }

    public boolean eliminar(int id) throws SQLException {
        if(id > 0){
            try {
                PreparedStatement insert = BD.obtener().prepareStatement(
                        "UPDATE `cristal` SET"
                        + "`cri_estado`='0'"
                        + "WHERE `cri_id`='"+id+"'");
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

    public boolean restaurar(int id) throws SQLException {
        if(id > 0){
            try {
                PreparedStatement insert = BD.obtener().prepareStatement(
                        "UPDATE `cristal` SET"
                        + "`cri_estado`='1'"
                        + "WHERE `cri_id`='"+id+"'");
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

    public boolean modificar(Cristal cristal) throws SQLException {
        if(cristal != null){
            try {
                PreparedStatement insert = BD.obtener().prepareStatement(
                        "UPDATE `cristal` SET"
                        + "`cri_nombre`='"+cristal.getNombre()+"'"
                        + ",`cri_precio`='"+cristal.getPrecio()+"'"
                        + "WHERE `cri_id`='"+cristal.getId()+"'");
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

    public ArrayList<Cristal> listarEliminadosPorNombre(String nombre) throws SQLException, ClassNotFoundException {
        String sql="SELECT * FROM `cristal` WHERE `cri_nombre` LIKE '%"+nombre+"%' AND cri_estado=0 ";
        
        PreparedStatement consulta = BD.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        ArrayList<Cristal> lista = new ArrayList<>();
        while (datos.next()) {
            lista.add(new Cristal(
                    datos.getInt("cri_id")
                    , datos.getString("cri_nombre")
                    , datos.getInt("cri_precio")
                    , datos.getInt("cri_estado")
            ));
        }
        BD.cerrar();
        return lista;
    }

    
    
}
