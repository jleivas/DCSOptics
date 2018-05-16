/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bd.BD;
import entities.Info;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author home
 */
public class InfoDao {
    
    public int getPrecioLente(String value) throws SQLException, ClassNotFoundException{
        String color = value.substring(value.lastIndexOf("-"), value.length()).replaceAll("-", "").trim();
        value = value.substring(0, value.lastIndexOf("-"));
        String marca = value.substring(value.lastIndexOf("-"), value.length()).replaceAll("-", "").trim();
        String id = value.substring(0, value.lastIndexOf("-")).replaceAll("-", "").trim();
        PreparedStatement consulta = BD.obtener().prepareStatement("SELECT len_precio_act FROM lente WHERE (len_id='"+id+"' AND len_color='"+color+"') AND len_marca='"+marca+"'");
        ResultSet datos = consulta.executeQuery();
        int precio=0;
        while (datos.next()) {
           precio =datos.getInt("len_precio_act");
           BD.cerrar();
           return precio;
        }
        BD.cerrar();
        return precio;  
    }
    
    public int getPrecioCristal(String value) throws SQLException, ClassNotFoundException{
        PreparedStatement consulta = BD.obtener().prepareStatement("SELECT cri_precio FROM cristal WHERE cri_nombre='"+value+"'");
        ResultSet datos = consulta.executeQuery();
        int precio=0;
        while (datos.next()) {
           precio =datos.getInt("cri_precio");
           BD.cerrar();
           return precio;
        }
        BD.cerrar();
        return precio;  
    }
    
    
    public Info getInstitucion() throws SQLException, ClassNotFoundException{
     PreparedStatement consulta = BD.obtener().prepareStatement("SELECT * FROM info");
        ResultSet datos = consulta.executeQuery();
        ArrayList<Info> lista = new ArrayList<>();
        Info info = new Info(0, "", "", "", "", "", "", "",0,"","");
        while (datos.next()) {
           info.setId(datos.getInt("id"));
           info.setNombre(datos.getString("nombre"));
           info.setDireccion(datos.getString("direccion"));
           info.setCiudad(datos.getString("ciudad"));
           info.setTelefono(datos.getString("telefono"));
           info.setCelular(datos.getString("celular")); 
           info.setMail(datos.getString("mail"));
           info.setWeb(datos.getString("web"));
           BD.cerrar();
           return info;
        }
        BD.cerrar();
        return info;  
    }
    
    
    public ArrayList<Info> listar() throws SQLException, ClassNotFoundException{
     PreparedStatement consulta = BD.obtener().prepareStatement("SELECT * FROM info");
        ResultSet datos = consulta.executeQuery();
        ArrayList<Info> lista = new ArrayList<>();
        while (datos.next()) {
            lista.add(new Info(datos.getInt("id"), datos.getString("nombre"), datos.getString("direccion"), datos.getString("ciudad"), datos.getString("telefono"), datos.getString("celular"), datos.getString("mail"), datos.getString("web"),datos.getInt("folio_actual"), datos.getString("admin_mail"),datos.getString("pass")));
        }
        BD.cerrar();
        return lista;
    }
    
    public Info cargar(int id) throws SQLException, ClassNotFoundException{
        for (Info data : listar()) {
            if(data.getId() == id)
                return data;
        }
        return null;
    }
    
    public boolean modificar(Info info) throws SQLException, ClassNotFoundException{
        
        PreparedStatement insert = BD.obtener().prepareStatement("UPDATE info set nombre = '"+info.getNombre()+"', direccion = '"+info.getDireccion()+"', ciudad = '"+info.getCiudad()+"', telefono = '"+info.getTelefono()+"', celular = '"+info.getCelular()+"', mail = '"+info.getMail()+"', web = '"+info.getWeb()+"' WHERE id = "+info.getId()+"");
        if(insert.executeUpdate()!=0){
            BD.cerrar();
            return true;
        }
        else{
            BD.cerrar();
            return false;
        }
    }
    
    public String actualizarFolio(int folio) throws SQLException, ClassNotFoundException{
        FichaDao load = new FichaDao();
        int ultimoId = load.obtenerUltimoId();
        if(ultimoId < folio){
            PreparedStatement insert = BD.obtener().prepareStatement("UPDATE info set folio_actual = '"+folio+"' WHERE id = 1");
            if(insert.executeUpdate()!=0){
                BD.cerrar();
                return "Número de folio: "+folio+" registrado correctamente";
            }
            else{
                BD.cerrar();
                return "Error, no se pudo registrar el valor ingresado.";
            }
        }else{
            return "El número de folio ingresado no es válido, debe ser mayor que el último registro.";
        }
        
            
    }
    
    public boolean actualizarFolioBoolean(int folio) throws SQLException, ClassNotFoundException{
        FichaDao load = new FichaDao();
        int ultimoId = load.obtenerUltimoId();
        if(ultimoId < folio){
            PreparedStatement insert = BD.obtener().prepareStatement("UPDATE info set folio_actual = '"+folio+"' WHERE id = 1");
            if(insert.executeUpdate()!=0){
                BD.cerrar();
                return true;
            }
            else{
                BD.cerrar();
                return false;
            }
        }else{
            return false;
        }
        
            
    }
    
    public boolean existe(int id) throws SQLException, ClassNotFoundException{
        for (Info temp : listar()) {
            if(temp.getId()==id)
                return true;
        }
        return false;
    }
    
    public boolean agregar(Info nueva) throws SQLException, ClassNotFoundException{
        if(nueva != null){
                PreparedStatement insert = BD.obtener().prepareStatement("INSERT INTO info VALUES('"+nueva.getNombre()+"', '"+nueva.getDireccion()+"', '"+nueva.getCiudad()+"', '"+nueva.getTelefono()+"', '"+nueva.getCelular()+"', '"+nueva.getMail()+"', '"+nueva.getWeb()+"',0, "+nueva.getId()+", '"+nueva.getAdminMail()+"', '"+nueva.getPass()+"')");
                if(insert.executeUpdate()!=0){
                    BD.cerrar();
                    return true;
                }
        }
        return false;
    }

    public int obtenerFolio() throws SQLException, ClassNotFoundException {
        PreparedStatement consulta = BD.obtener().prepareStatement("SELECT folio_actual FROM info");
        ResultSet datos = consulta.executeQuery();
        int folio = 0;
        while (datos.next()) {
            folio = datos.getInt("folio_actual");
        }
        BD.cerrar();
        return folio;
    }
    
    public String obtenerPass() throws SQLException, ClassNotFoundException {
        PreparedStatement consulta = BD.obtener().prepareStatement("SELECT pass FROM info");
        ResultSet datos = consulta.executeQuery();
        String pass = "";
        while (datos.next()) {
            pass = datos.getString("pass");
        }
        BD.cerrar();
        return pass;
    }

    public boolean setAdminMail(String adminMail, int id) throws SQLException, ClassNotFoundException {
        PreparedStatement insert = BD.obtener().prepareStatement("UPDATE info set admin_mail = '"+adminMail+"' WHERE id = "+id+"");
        if(insert.executeUpdate()!=0){
            BD.cerrar();
            return true;
        }
        else{
            BD.cerrar();
            return false;
        }
    }

    public boolean setPass(String pass,int id) throws SQLException, ClassNotFoundException {
        PreparedStatement insert = BD.obtener().prepareStatement("UPDATE info set pass = '"+pass+"' WHERE id = "+id+"");
        if(insert.executeUpdate()!=0){
            BD.cerrar();
            return true;
        }
        else{
            BD.cerrar();
            return false;
        }
    }
    
    
}
