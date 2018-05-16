/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bd.BD;
import entities.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

/**
 *
 * @author Lenovo G470
 */
public class UserDao {
    
    public boolean ingresar(String contraseña) throws SQLException, ClassNotFoundException{
     PreparedStatement consulta = BD.obtener().prepareStatement("SELECT `us_pass` FROM `user` where us_tipo=1 AND us_estado=1");
        ResultSet datos = consulta.executeQuery();
        String pass;
        while (datos.next()) {
           pass=datos.getString("us_pass");
           
           if(contraseña.equals(pass)){
              BD.cerrar();
             return true;
           }
        }
        BD.cerrar();
        JOptionPane.showMessageDialog(null, "Acceso denegado! \n La contraseña no coincide", "Error", JOptionPane.WARNING_MESSAGE);
        return false;
    }
    
    public boolean cambiarPass(String pass2) throws SQLException, ClassNotFoundException{
        JPasswordField pwd = new JPasswordField(10);
        int res = JOptionPane.showConfirmDialog(null,pwd, "Ingrese clave de inventario",JOptionPane.OK_CANCEL_OPTION);
        if(res < 0){
            return false;
        }else{
                String respuesta = pwd.getText();
                PreparedStatement consulta = BD.obtener().prepareStatement("SELECT `us_pass` FROM `user`");
                ResultSet datos = consulta.executeQuery();
                String pass="";
                while (datos.next()) {
                   pass=datos.getString("us_pass");

                   if(!respuesta.equals(pass)){
                   JOptionPane.showMessageDialog(null, "La operación ha sido cancelada. \nLa contraseña no coincide con la actual", "Error", JOptionPane.WARNING_MESSAGE);
                   BD.cerrar();
                   return false;
                   }

                consulta = BD.obtener().prepareStatement("UPDATE `user` set `us_pass` = '"+pass2+"' WHERE `us_id` = '1'");

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

                BD.cerrar();
                JOptionPane.showMessageDialog(null, "Ha ocurrido un error al intentar consultar al servidor", "Error", JOptionPane.WARNING_MESSAGE);
                return false;
            }
    }

    public ArrayList<User> listar(int id) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        String sql="SELECT * FROM usuario WHERE us_id='"+id+"' ";
        if(id==0){
        sql="SELECT * FROM usuario WHERE us_estado=1 ";
        }
         if(id==-1){
        sql="SELECT * FROM `usuario` WHERE `us_estado`='0'";
        }
         if(id==-2){
        sql="SELECT * FROM `usuario`";
        }
        
        PreparedStatement consulta = BD.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        ArrayList<User> lista = new ArrayList<>();
        while (datos.next()) {
            lista.add(new User(
                    datos.getInt("us_id")
                    , datos.getString("us_nombre")
                    , datos.getString("us_pass")
                    , datos.getInt("us_tipo")
                    , datos.getInt("us_estado")
                    )
            );
        }
        BD.cerrar();
        return lista;
    }

    public boolean eliminar(int id) throws SQLException {
        if(id>0){
            try {
                PreparedStatement insert = BD.obtener().prepareStatement(
                        "UPDATE `user` SET"
                        + "`us_estado`='0'"
                        + "WHERE `us_id`='"+id+"'");
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

    public int obtenerId() throws SQLException, ClassNotFoundException {
        
        String sql="SELECT MAX(`us_id`) as id_user FROM `user`";
        
        PreparedStatement consulta = BD.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        int id=0;
        while (datos.next()) {
            id=datos.getInt("id_user");
        }
        BD.cerrar();
        return id+1;
    }

    public boolean guardar(User user) throws SQLException, ClassNotFoundException {
        ArrayList<User> lista=listar(user.getId());//llama al metodo listar e ingresa el rut para buscar por ese rut 
        if(lista.size()>0){
        return false;
        }
        lista= buscar(user.getNombre(), 1);
        if(lista.size()>0){
        return false;
        }
        
        if(user != null){
            try {
                PreparedStatement insert = BD.obtener().prepareStatement(
                        "INSERT INTO `user`(`us_id`, `us_nombre`, `us_pass`, `us_tipo`, `us_estado`)"
                                + " VALUES('"
                                +user.getId()+"', '"
                                +user.getNombre()+"', '"
                                +user.getPass()+"', '"
                                +user.getTipo()+"','"
                                +user.getEstado()
                                +"')");
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

    public boolean modificar(User user) throws SQLException, ClassNotFoundException {
        if(user != null){
            ArrayList<User> lista= buscar(user.getNombre(), 1);
            if(lista.size()>0){
                for (User user1 : lista) {
                    if(user1.getId()!=user.getId())
                        return false;
                }
            }
            try {
                PreparedStatement insert = BD.obtener().prepareStatement(
                        "UPDATE `user` SET"
                        + "`us_nombre`='"+user.getNombre()+"'"
                        + ",`us_pass`='"+user.getPass()+"'"
                        + ",`us_tipo`='"+user.getTipo()+"'"
                        + ",`us_estado`='"+user.getEstado()+"'"
                        + "WHERE `us_id`='"+user.getId()+"'");
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

    public ArrayList<User> buscar(String buscar, int estado) throws SQLException, ClassNotFoundException {
        String sql="SELECT * FROM `user` WHERE `us_nombre`  LIKE '%"+buscar+"%' AND us_estado= '"+estado+"'";
        
        
        PreparedStatement consulta = BD.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        ArrayList<User> lista = new ArrayList<>();
        while (datos.next()) {
            lista.add(new User(
                    datos.getInt("us_id")
                    , datos.getString("us_nombre")
                    , datos.getString("us_pass")
                    , datos.getInt("us_tipo")
                    , datos.getInt("us_estado")
                    )
            );
        }
        BD.cerrar();
        return lista;
    }

    public User validar(String user, String pass) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        for (User temp : listar(0)) {
            if(temp.getNombre().toUpperCase().equals(user.toUpperCase()) && temp.getPass().equals(pass))
                return temp;
        }
        return null;
    }
    
    
    
}
