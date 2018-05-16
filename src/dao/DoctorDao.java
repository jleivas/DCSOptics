/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bd.BD;
import entities.Doctor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Lenovo G470
 */
public class DoctorDao {
    
    public ArrayList<Doctor> listar(String rutDoctor) throws SQLException, ClassNotFoundException{
        String sql="SELECT * FROM `doctor` WHERE `doc_rut`='"+rutDoctor+"' ";
        if(rutDoctor==""){
        sql="SELECT * FROM `doctor` WHERE `doc_estado`='1'";
        }
        
         if(rutDoctor=="eliminados"){
        sql="SELECT * FROM `doctor` WHERE `doc_estado`='0'";
        }
        
        PreparedStatement consulta = BD.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        ArrayList<Doctor> lista = new ArrayList<>();
        while (datos.next()) {
            lista.add(new Doctor(
                    datos.getString("doc_rut")
                    , datos.getString("doc_nombre")
                    , datos.getString("doc_telefono")
                    , datos.getString("doc_mail")
                    , Integer.parseInt(datos.getString("doc_estado"))
            ));
        }
        BD.cerrar();
        return lista;
    }
    
    public ArrayList<Doctor> buscar(String valor, int estado) throws SQLException, ClassNotFoundException{
        String sql="SELECT * FROM `doctor` WHERE (`doc_rut` LIKE '%"+valor+"%' OR doc_nombre LIKE '%"+valor+"%') AND doc_estado="+estado+"";
        
        
        PreparedStatement consulta = BD.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        ArrayList<Doctor> lista = new ArrayList<>();
        while (datos.next()) {
            lista.add(new Doctor(
                    datos.getString("doc_rut")
                    , datos.getString("doc_nombre")
                    , datos.getString("doc_telefono")
                    , datos.getString("doc_mail")
                    , Integer.parseInt(datos.getString("doc_estado")
                    )
            ));
        }
        BD.cerrar();
        return lista;
    }
    
    public String agregar(Doctor doctor) throws SQLException, ClassNotFoundException{
        
        ArrayList<Doctor> lista=listar(doctor.getRut());
        if(lista.size()>0){
        return "Ya se encuentra registrado el doctor "+doctor.getNombre();
        } 
        
        if(doctor != null){
            try {
                PreparedStatement insert = BD.obtener().prepareStatement(
                        "INSERT INTO `doctor`(`doc_rut`, `doc_nombre`, `doc_telefono`, `doc_mail`, `doc_estado`)"
                                + " VALUES('"
                                +doctor.getRut()+"', '"
                                +doctor.getNombre()+"', '"
                                +doctor.getTelefono()+"', '"
                                +doctor.getEmail()+"', '"
                                +doctor.getEstado()
                                +"')");
                if(insert.executeUpdate()!=0){
                    BD.cerrar();
                    return "Se ha agregado el doctor "+doctor.getNombre()+" correctamente";
                }
            } catch (Exception e) {
                BD.cerrar();
                return "ERROR :"+e;
            }
        }
        BD.cerrar();
        return "El doctor tiene datos vacios";
    }
    
    public boolean modificar(Doctor doctor) throws SQLException, ClassNotFoundException{
        if(doctor != null){
            try {
                PreparedStatement insert = BD.obtener().prepareStatement(
                        "UPDATE `doctor` SET"
                        + "`doc_nombre`='"+doctor.getNombre()+"'"
                        + ",`doc_telefono`='"+doctor.getTelefono()+"'"
                        + ",`doc_mail`='"+doctor.getEmail()+"'"
                        + ",`doc_estado`='"+doctor.getEstado()+"'"
                        + "WHERE `doc_rut`='"+doctor.getRut()+"'");
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
    
    public boolean eliminar(String rutDoctor) throws SQLException, ClassNotFoundException{
        if(rutDoctor.length()>8){
            try {
                PreparedStatement insert = BD.obtener().prepareStatement(
                        "UPDATE `doctor` SET"
                        + "`doc_estado`='0'"
                        + "WHERE `doc_rut`='"+rutDoctor+"'");
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
    
    public boolean restaurar(String rutDoctor) throws SQLException, ClassNotFoundException{
        if(rutDoctor.length()>8){
            try {
                PreparedStatement insert = BD.obtener().prepareStatement(
                        "UPDATE `doctor` SET"
                        + "`doc_estado`='1'"
                        + "WHERE `doc_rut`='"+rutDoctor+"'");
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

    public ArrayList<Doctor> listarNombre(String nombre) throws SQLException, ClassNotFoundException {
        String sql="SELECT * FROM `doctor` WHERE `doc_nombre` LIKE '%"+nombre+"%' AND doc_estado=1 ";
        
        PreparedStatement consulta = BD.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        ArrayList<Doctor> lista = new ArrayList<>();
        while (datos.next()) {
            lista.add(new Doctor(
                    datos.getString("doc_rut")
                    , datos.getString("doc_nombre")
                    , datos.getString("doc_telefono")
                    , datos.getString("doc_mail")
                    , Integer.parseInt(datos.getString("doc_estado"))
            ));
        }
        BD.cerrar();
        return lista;
    }
    
}
