/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Cliente;
import bd.BD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Lenovo G470
 */
public class ClienteDao {
    
    public ArrayList<Cliente> listar(String rutCliente) throws SQLException, ClassNotFoundException{
        String sql="SELECT * FROM `cliente` WHERE `cli_rut`='"+rutCliente+"' ";
        if(rutCliente==""){
        sql="SELECT * FROM `cliente` WHERE `cli_estado`='1' ";
        }
         if(rutCliente=="eliminados"){
        sql="SELECT * FROM `cliente` WHERE `cli_estado`='0'";
        }
        
        PreparedStatement consulta = BD.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        ArrayList<Cliente> lista = new ArrayList<>();
        while (datos.next()) {
            lista.add(new Cliente(
                    datos.getString("cli_rut")
                    , datos.getString("cli_nombre")
                    , datos.getString("cli_telefono")
                    , datos.getString("cli_email")
                    , datos.getString("cli_direccion")
                    , datos.getString("cli_comuna")
                    , datos.getString("cli_ciudad")
                    ,Integer.parseInt(datos.getString("cli_sexo"))
                    , Integer.parseInt(datos.getString("cli_edad"))
                    , Integer.parseInt(datos.getString("cli_estado")
                    )
            ));
        }
        BD.cerrar();
        return lista;
    }
    
    public ArrayList<Cliente> buscar(String valor, int estado) throws SQLException, ClassNotFoundException{
        String sql="SELECT * FROM `cliente` WHERE (`cli_rut` LIKE '%"+valor+"%' OR cli_nombre LIKE '%"+valor+"%' OR cli_comuna LIKE '%"+valor+"%' OR cli_ciudad LIKE '%"+valor+"%') AND cli_estado="+estado+"";
        
        
        PreparedStatement consulta = BD.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        ArrayList<Cliente> lista = new ArrayList<>();
        while (datos.next()) {
            lista.add(new Cliente(
                    datos.getString("cli_rut")
                    , datos.getString("cli_nombre")
                    , datos.getString("cli_telefono")
                    , datos.getString("cli_email")
                    , datos.getString("cli_direccion")
                    , datos.getString("cli_comuna")
                    , datos.getString("cli_ciudad")
                    ,Integer.parseInt(datos.getString("cli_sexo"))
                    , Integer.parseInt(datos.getString("cli_edad"))
                    , Integer.parseInt(datos.getString("cli_estado")
                    )
            ));
        }
        BD.cerrar();
        return lista;
    }
    
    public String agregar(Cliente cliente) throws SQLException, ClassNotFoundException{
        
        ArrayList<Cliente> lista=listar(cliente.getRut());//llama al metodo listar e ingresa el rut para buscar por ese rut 
        if(lista.size()>0){
        return "Ya se encuentra registrado el cliente "+cliente.getNombre();
        } 
        
        if(cliente != null){
            try {
                PreparedStatement insert = BD.obtener().prepareStatement(
                        "INSERT INTO `cliente`(`cli_rut`, `cli_nombre`, `cli_telefono`, `cli_email`, `cli_direccion`, `cli_comuna`, `cli_ciudad`, `cli_sexo`, `cli_edad`, `cli_estado`)"
                                + " VALUES('"
                                +cliente.getRut()+"', '"
                                +cliente.getNombre()+"', '"
                                +cliente.getTelefono()+"', '"
                                +cliente.getEmail()+"','"
                                +cliente.getDireccion()+"','"
                                +cliente.getComuna()+"','"
                                +cliente.getCiudad()+"','"
                                +cliente.getSexo()+"','"
                                +cliente.getEdad()+"','"
                                +cliente.getEstado()
                                +"')");
                if(insert.executeUpdate()!=0){
                    BD.cerrar();
                    return "Se ha agregado el cliente "+cliente.getNombre()+" correctamente";
                }
            } catch (Exception e) {
                BD.cerrar();
                return "ERROR :"+e;
            }
        }
        BD.cerrar();
        return "El cliente tiene datos vacios";
    }
    
    public boolean modificar(Cliente cliente) throws SQLException, ClassNotFoundException{
        if(cliente != null){
            try {
                PreparedStatement insert = BD.obtener().prepareStatement(
                        "UPDATE `cliente` SET"
                        + "`cli_nombre`='"+cliente.getNombre()+"'"
                        + ",`cli_telefono`='"+cliente.getTelefono()+"'"
                        + ",`cli_email`='"+cliente.getEmail()+"'"
                        + ",`cli_direccion`='"+cliente.getDireccion()+"'"
                        + ",`cli_comuna`='"+cliente.getComuna()+"'"
                        + ",`cli_ciudad`='"+cliente.getCiudad()+"'"
                        + ",`cli_sexo`='"+cliente.getSexo()+"'"
                        + ",`cli_edad`='"+cliente.getEdad()+"'"
                        + "WHERE `cli_rut`='"+cliente.getRut()+"'");
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
    
     public boolean eliminar(String rutCliente) throws SQLException, ClassNotFoundException{
        if(rutCliente.length()>8){
            try {
                PreparedStatement insert = BD.obtener().prepareStatement(
                        "UPDATE `cliente` SET"
                        + "`cli_estado`='0'"
                        + "WHERE `cli_rut`='"+rutCliente+"'");
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
     
    public boolean restaurar(String rutCliente) throws SQLException, ClassNotFoundException{
        if(rutCliente.length()>8){
            try {
                PreparedStatement insert = BD.obtener().prepareStatement(
                        "UPDATE `cliente` SET"
                        + "`cli_estado`='1'"
                        + "WHERE `cli_rut`='"+rutCliente+"'");
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

    public boolean agregarBoolean(Cliente cliente) throws SQLException, ClassNotFoundException {
        ArrayList<Cliente> lista=listar(cliente.getRut());//llama al metodo listar e ingresa el rut para buscar por ese rut 
        if(lista.size()>0){
        return false;
        } 
        
        if(cliente != null){
            try {
                PreparedStatement insert = BD.obtener().prepareStatement(
                        "INSERT INTO `cliente`(`cli_rut`, `cli_nombre`, `cli_telefono`, `cli_email`, `cli_direccion`, `cli_comuna`, `cli_ciudad`, `cli_sexo`, `cli_edad`, `cli_estado`)"
                                + " VALUES('"
                                +cliente.getRut()+"', '"
                                +cliente.getNombre()+"', '"
                                +cliente.getTelefono()+"', '"
                                +cliente.getEmail()+"','"
                                +cliente.getDireccion()+"','"
                                +cliente.getComuna()+"','"
                                +cliente.getCiudad()+"','"
                                +cliente.getSexo()+"','"
                                +cliente.getEdad()+"','"
                                +cliente.getEstado()
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
    
    public ArrayList<Cliente> morosos() throws SQLException, ClassNotFoundException{
        Date hoy=new Date();
        DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = fechaHora.format(hoy);
        
        String sql=" SELECT `fch_id`, "
                + "(SELECT cliente.cli_rut from cliente where cliente.cli_rut=ficha.cliente_cli_rut) as 'rut' , "
                + "(SELECT cliente.cli_nombre from cliente where cliente.cli_rut=ficha.cliente_cli_rut) as 'nombre', "
                + "(SELECT cliente.cli_telefono from cliente where cliente.cli_rut=ficha.cliente_cli_rut) as 'telefono' , "
                + "(SELECT cliente.cli_email from cliente where cliente.cli_rut=ficha.cliente_cli_rut) as 'email' , "
                + "(SELECT cliente.cli_direccion from cliente where cliente.cli_rut=ficha.cliente_cli_rut) as 'direccion' , "
                + "(SELECT cliente.cli_comuna from cliente where cliente.cli_rut=ficha.cliente_cli_rut) as 'comuna' , "
                + "(SELECT cliente.cli_ciudad from cliente where cliente.cli_rut=ficha.cliente_cli_rut) as 'ciudad' , "
                + "(SELECT cliente.cli_sexo from cliente where cliente.cli_rut=ficha.cliente_cli_rut) as 'sexo' , "
                + "(SELECT cliente.cli_edad from cliente where cliente.cli_rut=ficha.cliente_cli_rut) as 'edad' , "
                + "(SELECT cliente.cli_estado from cliente where cliente.cli_rut=ficha.cliente_cli_rut) as 'estado' "
                + "from ficha where `fch_estado`='1' AND fch_fecha_entrega < '"+fecha+"'  ORDER BY `rut` ASC";
       
        
        PreparedStatement consulta = BD.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        ArrayList<Cliente> lista = new ArrayList<>();
        String anterior="";
        while (datos.next()) {
            if(!datos.getString("rut").equals(anterior)){
            Cliente cliente = new Cliente(
                    datos.getString("rut")
                    , datos.getString("nombre")
                    , datos.getString("telefono")
                    , datos.getString("email")
                    , datos.getString("direccion")
                    , datos.getString("comuna")
                    , datos.getString("ciudad")
                    ,Integer.parseInt(datos.getString("sexo"))
                    , Integer.parseInt(datos.getString("edad"))
                    , Integer.parseInt(datos.getString("estado")
                    )
            );
                lista.add(cliente);}
                anterior=datos.getString("rut");
        }
        BD.cerrar();
        return lista;
    }
    
    public ArrayList<Cliente> sinRetirar() throws SQLException, ClassNotFoundException{
        Date hoy=new Date();
        DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = fechaHora.format(hoy);
        
        String sql=" SELECT `fch_id`, "
                + "(SELECT cliente.cli_rut from cliente where cliente.cli_rut=ficha.cliente_cli_rut) as 'rut' , "
                + "(SELECT cliente.cli_nombre from cliente where cliente.cli_rut=ficha.cliente_cli_rut) as 'nombre', "
                + "(SELECT cliente.cli_telefono from cliente where cliente.cli_rut=ficha.cliente_cli_rut) as 'telefono' , "
                + "(SELECT cliente.cli_email from cliente where cliente.cli_rut=ficha.cliente_cli_rut) as 'email' , "
                + "(SELECT cliente.cli_direccion from cliente where cliente.cli_rut=ficha.cliente_cli_rut) as 'direccion' , "
                + "(SELECT cliente.cli_comuna from cliente where cliente.cli_rut=ficha.cliente_cli_rut) as 'comuna' , "
                + "(SELECT cliente.cli_ciudad from cliente where cliente.cli_rut=ficha.cliente_cli_rut) as 'ciudad' , "
                + "(SELECT cliente.cli_sexo from cliente where cliente.cli_rut=ficha.cliente_cli_rut) as 'sexo' , "
                + "(SELECT cliente.cli_edad from cliente where cliente.cli_rut=ficha.cliente_cli_rut) as 'edad' , "
                + "(SELECT cliente.cli_estado from cliente where cliente.cli_rut=ficha.cliente_cli_rut) as 'estado' "
                + "from ficha where `fch_estado`='2' AND fch_fecha_entrega < '"+fecha+"'";
       
        
        PreparedStatement consulta = BD.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        ArrayList<Cliente> lista = new ArrayList<>();
        String anterior = "";
        while (datos.next()) {
            if(!datos.getString("rut").equals(anterior)){
            lista.add(new Cliente(
                    datos.getString("rut")
                    , datos.getString("nombre")
                    , datos.getString("telefono")
                    , datos.getString("email")
                    , datos.getString("direccion")
                    , datos.getString("comuna")
                    , datos.getString("ciudad")
                    ,Integer.parseInt(datos.getString("sexo"))
                    , Integer.parseInt(datos.getString("edad"))
                    , Integer.parseInt(datos.getString("estado")
                    )
            ));}
            anterior = datos.getString("rut");
        }
        BD.cerrar();
        return lista;
    }

    
}
