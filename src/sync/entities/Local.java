/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sync.entities;

import bd.LcBd;

import entities.Cliente;
import entities.Convenio;
import entities.Cristal;
import entities.Descuento;
import entities.Doctor;
import entities.Equipo;
import entities.Institucion;
import entities.Inventario;
import entities.Lente;
import entities.Oficina;
import entities.RegistroBaja;
import entities.TipoPago;
import entities.User;

import fn.Log;
import fn.OptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sync.InterfaceSync;

/**
 *
 * @author sdx
 */
public class Local implements InterfaceSync {

    private static String className = "Local";
    @Override
    public boolean add(Object objectParam) {
        Log.setLog(className, Log.getReg());
        try{
            if(objectParam == null)
                return false;
            if(objectParam instanceof Cliente){
                Cliente object = (Cliente)objectParam;
                if (object != null) {
                    PreparedStatement consulta = LcBd.obtener().prepareStatement("SELECT cli_rut FROM cliente WHERE cli_rut='" + object.getCod() + "'");
                    ResultSet datos = consulta.executeQuery();
                    while (datos.next()) {
                        LcBd.cerrar();
                        return update(object);
                    }
                    PreparedStatement insert = LcBd.obtener().prepareStatement(
                            sqlInsert(object)
                    );
                    if (insert.executeUpdate() != 0) {
                        LcBd.cerrar();
                        return true;
                    }
                }
                OptionPane.showMsg("Error inseperado en la operación", "Cliente: " + object.getNombre()+ "\nId: " + object.getCod()+ "\nNo se pudo insertar.", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if(objectParam instanceof Convenio){
                Convenio object = (Convenio)objectParam;
                if (object != null) {
                    PreparedStatement consulta = LcBd.obtener().prepareStatement("SELECT cnv_id FROM convenio WHERE cnv_id=" + object.getId()+ "");
                    ResultSet datos = consulta.executeQuery();
                    while (datos.next()) {
                        LcBd.cerrar();
                        return update(object);
                    }
                    PreparedStatement insert = LcBd.obtener().prepareStatement(
                            sqlInsert(object)
                    );
                    if (insert.executeUpdate() != 0) {
                        LcBd.cerrar();
                        return true;
                    }
                }
                OptionPane.showMsg("Error inseperado en la operación", "Cliente: " + object.getNombre()+ "\nId: " + object.getId()+ "\nNo se pudo insertar.", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if(objectParam instanceof Cristal){
                Cristal object = (Cristal)objectParam;
                if (object != null) {
                    PreparedStatement consulta = LcBd.obtener().prepareStatement("SELECT cri_id FROM cristal WHERE cri_id=" + object.getId());
                    ResultSet datos = consulta.executeQuery();
                    while (datos.next()) {
                        LcBd.cerrar();
                        return update(object);
                    }
                    PreparedStatement insert = LcBd.obtener().prepareStatement(
                            sqlInsert(object)
                    );
                    if (insert.executeUpdate() != 0) {
                        LcBd.cerrar();

                        return true;
                    }
                }
                OptionPane.showMsg("Error inseperado en la operación", "Cristal: " + object.getNombre() + "\nId: " + object.getId() + "\nNo se pudo insertar.", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if(objectParam instanceof Descuento){
                Descuento object = (Descuento)objectParam;
                if (object != null) {
                    PreparedStatement consulta = LcBd.obtener().prepareStatement("SELECT des_id FROM descuento WHERE des_id=" + object.getId());
                    ResultSet datos = consulta.executeQuery();
                    while (datos.next()) {
                        LcBd.cerrar();
                        return update(object);
                    }
                    PreparedStatement insert = LcBd.obtener().prepareStatement(
                            sqlInsert(object)
                    );
                    if (insert.executeUpdate() != 0) {
                        LcBd.cerrar();

                        return true;
                    }
                }
                OptionPane.showMsg("Error inseperado en la operación", "Descuento: " + object.getNombre() + "\nId: " + object.getId() + "\nNo se pudo insertar.", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if(objectParam instanceof Doctor){
                Doctor object = (Doctor)objectParam;
                if (object != null) {
                    PreparedStatement consulta = LcBd.obtener().prepareStatement("SELECT doc_rut FROM doctor WHERE doc_rut='" + object.getCod() + "'");
                    ResultSet datos = consulta.executeQuery();
                    while (datos.next()) {
                        LcBd.cerrar();
                        return update(object);
                    }
                    PreparedStatement insert = LcBd.obtener().prepareStatement(
                            sqlInsert(object)
                    );
                    if (insert.executeUpdate() != 0) {
                        LcBd.cerrar();
                        return true;
                    }
                }
                OptionPane.showMsg("Error inseperado en la operación", "Profesional: " + object.getNombre()+ "\nId: " + object.getCod()+ "\nNo se pudo insertar.", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if(objectParam instanceof Institucion){
                Institucion object = (Institucion)objectParam;
                if (object != null) {
                    PreparedStatement consulta = LcBd.obtener().prepareStatement("SELECT ins_id FROM institucion WHERE ins_id=" + object.getId()+ "");
                    ResultSet datos = consulta.executeQuery();
                    while (datos.next()) {
                        LcBd.cerrar();
                        return update(object);
                    }
                    PreparedStatement insert = LcBd.obtener().prepareStatement(
                            sqlInsert(object)
                    );
                    if (insert.executeUpdate() != 0) {
                        LcBd.cerrar();
                        return true;
                    }
                }
                OptionPane.showMsg("Error inseperado en la operación", "Institucion: " + object.getNombre()+ "\nId: " + object.getId() + "\nNo se pudo insertar.", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if(objectParam instanceof Lente){
                Lente object = (Lente)objectParam;
                if (object != null) {
                    PreparedStatement consulta = LcBd.obtener().prepareStatement("SELECT len_id FROM lente WHERE len_id='" + object.getCod()+ "'");
                    ResultSet datos = consulta.executeQuery();
                    while (datos.next()) {
                        LcBd.cerrar();
                        return update(object);
                    }
                    PreparedStatement insert = LcBd.obtener().prepareStatement(
                            sqlInsert(object)
                    );
                    if (insert.executeUpdate() != 0) {
                        LcBd.cerrar();
                        return true;
                    }
                }
                OptionPane.showMsg("Error inseperado en la operación", "Lente: " + object.getCod()+ "\nId: " + object.getCod() + "\nNo se pudo insertar.", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if(objectParam instanceof Oficina){
                Oficina object = (Oficina)objectParam;
                if (object != null) {
                    PreparedStatement consulta = LcBd.obtener().prepareStatement("SELECT of_id FROM oficina WHERE of_id=" + object.getId() + "");
                    ResultSet datos = consulta.executeQuery();
                    while (datos.next()) {
                        LcBd.cerrar();
                        return update(object);
                    }
                    PreparedStatement insert = LcBd.obtener().prepareStatement(
                            sqlInsert(object)
                    );
                    if (insert.executeUpdate() != 0) {
                        LcBd.cerrar();

                        return true;
                    }
                }
                OptionPane.showMsg("Error inseperado en la operación", "Oficina: " + object.getNombre()+ "\nId: " + object.getId()+ "\nNo se pudo insertar.", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if(objectParam instanceof RegistroBaja){
                RegistroBaja object = (RegistroBaja)objectParam;
                if (object != null) {
                    PreparedStatement consulta = LcBd.obtener().prepareStatement("SELECT rb_id FROM registro_bajas WHERE rb_id=" + object.getCod()+ "");
                    ResultSet datos = consulta.executeQuery();
                    while (datos.next()) {
                        LcBd.cerrar();
                        return update(object);
                    }
                    PreparedStatement insert = LcBd.obtener().prepareStatement(
                            sqlInsert(object)
                    );
                    if (insert.executeUpdate() != 0) {
                        LcBd.cerrar();

                        return true;
                    }
                }
                OptionPane.showMsg("Error inseperado en la operación", "Registro de Bajas: " + object.getCod()+ "\n\nNo se pudo insertar.", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if(objectParam instanceof TipoPago){
                TipoPago object = (TipoPago)objectParam;
                if (object != null) {
                    PreparedStatement consulta = LcBd.obtener().prepareStatement("SELECT tp_id FROM tipo_pago WHERE tp_id='" + object.getId()+ "'");
                    ResultSet datos = consulta.executeQuery();
                    while (datos.next()) {
                        LcBd.cerrar();
                        return update(object);
                    }
                    PreparedStatement insert = LcBd.obtener().prepareStatement(
                            sqlInsert(object)
                    );
                    if (insert.executeUpdate() != 0) {
                        LcBd.cerrar();

                        return true;
                    }
                }
                OptionPane.showMsg("Error inseperado en la operación", "Tipo de Pago: " + object.getNombre()+ "\nId: " + object.getId()+ "\nNo se pudo insertar.", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if(objectParam instanceof User){
                User object = (User)objectParam;
                if (object != null) {
                    PreparedStatement consulta = LcBd.obtener().prepareStatement("SELECT us_id FROM usuario WHERE us_id=" + object.getId());
                    ResultSet datos = consulta.executeQuery();
                    while (datos.next()) {
                        LcBd.cerrar();
                        return update(object);
                    }
                    PreparedStatement insert = LcBd.obtener().prepareStatement(
                            sqlInsert(object)
                    );
                    if (insert.executeUpdate() != 0) {
                        LcBd.cerrar();
                        //OptionPane.showMsg("Operación realizada correctamente", "Usuario: "+object.getUsername()+"\nId: "+object.getId()+"\nAgregado correctamente.", JOptionPane.INFORMATION_MESSAGE);
                        return true;
                    }
                }
                OptionPane.showMsg("Error inseperado en la operación", "Usuario: " + object.getUsername() + "\nId: " + object.getId() + "\nNo se pudo insertar.", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }catch(SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException ex){
            Logger.getLogger(Local.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    @Override
    public boolean update(Object objectParam) {
        Log.setLog(className, Log.getReg());
        try{
            if(objectParam == null)
                return false;
            if(objectParam instanceof Cliente){
                Cliente object = (Cliente)objectParam;
                PreparedStatement consulta = LcBd.obtener().prepareStatement("SELECT * FROM cliente WHERE cli_rut='" + object.getCod() + "'");
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    Date dsp_fecha = new Date();
                    try {
                        dsp_fecha = datos.getDate("cli_last_update");
                    } catch (SQLException e) {
                        OptionPane.showMsg("Error al convertir fecha", "Se cayó al intentar convertir la fecha.\nDetalle: " + e.getMessage() + "\n" + Log.getLog(), JOptionPane.ERROR_MESSAGE);
                    }
                    if (!fn.date.Cmp.localIsNewOrEqual(object.getLastUpdate(), dsp_fecha)) {
                        LcBd.cerrar();
                        return false;
                    }
                }
                PreparedStatement insert = LcBd.obtener().prepareStatement(
                        sqlUpdate(object));
                insert.executeUpdate();
                LcBd.cerrar();
                return true;
            }if(objectParam instanceof Convenio){
                Convenio object = (Convenio)objectParam;
                PreparedStatement consulta = LcBd.obtener().prepareStatement("SELECT * FROM convenio WHERE cnv_id=" + object.getId()+ "");
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    Date dsp_fecha = new Date();
                    try {
                        dsp_fecha = datos.getDate("cnv_last_update");
                    } catch (SQLException e) {
                        OptionPane.showMsg("Error al convertir fecha", "Se cayó al intentar convertir la fecha.\nDetalle: " + e.getMessage() + "\n" + Log.getLog(), JOptionPane.ERROR_MESSAGE);
                    }
                    if (!fn.date.Cmp.localIsNewOrEqual(object.getLastUpdate(), dsp_fecha)) {
                        LcBd.cerrar();
                        return false;
                    }
                }
                PreparedStatement insert = LcBd.obtener().prepareStatement(
                        sqlUpdate(object));
                insert.executeUpdate();
                LcBd.cerrar();
                return true;
            }
            if(objectParam instanceof Cristal){
                Cristal object = (Cristal)objectParam;
                PreparedStatement consulta = LcBd.obtener().prepareStatement("SELECT * FROM cristal WHERE cri_id=" + object.getId());
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    Date dsp_fecha = new Date();
                    try {
                        dsp_fecha = datos.getDate("cri_last_update");
                    } catch (Exception e) {
                        OptionPane.showMsg("Error al convertir fecha", "Se cayó al intentar convertir la fecha.\nDetalle:\n" + Log.getLog(), JOptionPane.ERROR_MESSAGE);
                    }
                    if (!fn.date.Cmp.localIsNewOrEqual(object.getLastUpdate(), dsp_fecha)) {
                        LcBd.cerrar();
                        return false;
                    }
                }
                PreparedStatement insert = LcBd.obtener().prepareStatement(
                        sqlUpdate(object)
                );
                insert.executeUpdate();
                LcBd.cerrar();
                return true;
            }
            if(objectParam instanceof Descuento){
                Descuento object = (Descuento)objectParam;
                PreparedStatement consulta = LcBd.obtener().prepareStatement("SELECT * FROM descuento WHERE des_id=" + object.getId());
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    Date dsp_fecha = new Date();
                    try {
                        dsp_fecha = datos.getDate("des_last_update");
                    } catch (Exception e) {
                        OptionPane.showMsg("Error al convertir fecha", "Se cayó al intentar convertir la fecha.\nDetalle:\n" + Log.getLog(), JOptionPane.ERROR_MESSAGE);
                    }
                    if (!fn.date.Cmp.localIsNewOrEqual(object.getLastUpdate(), dsp_fecha)) {
                        LcBd.cerrar();
                        return false;
                    }
                }
                PreparedStatement insert = LcBd.obtener().prepareStatement(
                        sqlUpdate(object));
                insert.executeUpdate();
                LcBd.cerrar();
                return true;
            }
            if(objectParam instanceof Doctor){
                Doctor object = (Doctor)objectParam;
                PreparedStatement consulta = LcBd.obtener().prepareStatement("SELECT * FROM doctor WHERE doc_rut='" + object.getCod() + "'");
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    Date dsp_fecha = new Date();
                    try {
                        dsp_fecha = datos.getDate("doc_last_update");
                    } catch (SQLException e) {
                        OptionPane.showMsg("Error al convertir fecha", "Se cayó al intentar convertir la fecha.\nDetalle: " + e.getMessage() + "\n" + Log.getLog(), JOptionPane.ERROR_MESSAGE);
                    }
                    if (!fn.date.Cmp.localIsNewOrEqual(object.getLastUpdate(), dsp_fecha)) {
                        LcBd.cerrar();
                        return false;
                    }
                }
                PreparedStatement insert = LcBd.obtener().prepareStatement(
                        sqlUpdate(object));
                insert.executeUpdate();
                LcBd.cerrar();
                return true;
            }
            if(objectParam instanceof Equipo){
                Equipo object = (Equipo)objectParam;
                PreparedStatement consulta = LcBd.obtener().prepareStatement("SELECT * FROM equipo WHERE eq_id=" + object.getId()+ "");
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    Date dsp_fecha = new Date();
                    try {
                        dsp_fecha = datos.getDate("eq_last_update");
                    } catch (SQLException e) {
                        OptionPane.showMsg("Error al convertir fecha", "Se cayó al intentar convertir la fecha.\nDetalle: " + e.getMessage() + "\n" + Log.getLog(), JOptionPane.ERROR_MESSAGE);
                    }
                    if (!fn.date.Cmp.localIsNewOrEqual(object.getLastUpdate(), dsp_fecha)) {
                        LcBd.cerrar();
                        return false;
                    }
                }
                PreparedStatement insert = LcBd.obtener().prepareStatement(
                        sqlUpdate(object));
                insert.executeUpdate();
                LcBd.cerrar();
                return true;
            }
            if(objectParam instanceof Institucion){
                Institucion object = (Institucion)objectParam;
                PreparedStatement consulta = LcBd.obtener().prepareStatement("SELECT * FROM institucion WHERE ins_id=" + object.getId()+ "");
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    Date dsp_fecha = new Date();
                    try {
                        dsp_fecha = datos.getDate("ins_last_update");
                    } catch (SQLException e) {
                        OptionPane.showMsg("Error al convertir fecha", "Se cayó al intentar convertir la fecha.\nDetalle: " + e.getMessage() + "\n" + Log.getLog(), JOptionPane.ERROR_MESSAGE);
                    }
                    if (!fn.date.Cmp.localIsNewOrEqual(object.getLastUpdate(), dsp_fecha)) {
                        LcBd.cerrar();
                        return false;
                    }
                }
                PreparedStatement insert = LcBd.obtener().prepareStatement(
                        sqlUpdate(object));
                insert.executeUpdate();
                LcBd.cerrar();
                return true;
            }
            if(objectParam instanceof Inventario){
                Inventario object = (Inventario)objectParam;
                PreparedStatement consulta = LcBd.obtener().prepareStatement("SELECT * FROM inventario WHERE inv_id=" + object.getId()+ "");
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    Date dsp_fecha = new Date();
                    try {
                        dsp_fecha = datos.getDate("inv_last_update");
                    } catch (SQLException e) {
                        OptionPane.showMsg("Error al convertir fecha", "Se cayó al intentar convertir la fecha.\nDetalle: " + e.getMessage() + "\n" + Log.getLog(), JOptionPane.ERROR_MESSAGE);
                    }
                    if (!fn.date.Cmp.localIsNewOrEqual(object.getLastUpdate(), dsp_fecha)) {
                        LcBd.cerrar();
                        return false;
                    }
                }
                PreparedStatement insert = LcBd.obtener().prepareStatement(
                        sqlUpdate(object));
                insert.executeUpdate();
                LcBd.cerrar();
                return true;
            }
            if(objectParam instanceof Lente){
                Lente object = (Lente)objectParam;
                PreparedStatement consulta = LcBd.obtener().prepareStatement("SELECT * FROM lente WHERE len_id='" + object.getCod()+ "'");
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    Date dsp_fecha = new Date();
                    try {
                        dsp_fecha = datos.getDate("len_last_update");
                    } catch (SQLException e) {
                        OptionPane.showMsg("Error al convertir fecha", "Se cayó al intentar convertir la fecha.\nDetalle: " + e.getMessage() + "\n" + Log.getLog(), JOptionPane.ERROR_MESSAGE);
                    }
                    if (!fn.date.Cmp.localIsNewOrEqual(object.getLastUpdate(), dsp_fecha)) {
                        LcBd.cerrar();
                        return false;
                    }
                }
                PreparedStatement insert = LcBd.obtener().prepareStatement(
                        sqlUpdate(object));
                insert.executeUpdate();
                LcBd.cerrar();
                return true;
            }
            if(objectParam instanceof Oficina){
                Oficina object = (Oficina)objectParam;
                PreparedStatement consulta = LcBd.obtener().prepareStatement("SELECT * FROM oficina WHERE of_id=" + object.getId() + "");
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    Date dsp_fecha = new Date();
                    try {
                        dsp_fecha = datos.getDate("of_last_update");
                    } catch (SQLException e) {
                        OptionPane.showMsg("Error al convertir fecha", "Se cayó al intentar convertir la fecha.\nDetalle: " + e.getMessage() + "\n" + Log.getLog(), JOptionPane.ERROR_MESSAGE);
                    }
                    if (!fn.date.Cmp.localIsNewOrEqual(object.getLastUpdate(), dsp_fecha)) {
                        LcBd.cerrar();
                        return false;
                    }
                }
                PreparedStatement insert = LcBd.obtener().prepareStatement(
                        sqlUpdate(object));
                insert.executeUpdate();
                LcBd.cerrar();
                return true;
            }
            if(objectParam instanceof RegistroBaja){
                RegistroBaja object = (RegistroBaja)objectParam;
                PreparedStatement consulta = LcBd.obtener().prepareStatement("SELECT * FROM registro_bajas WHERE rb_id='" + object.getCod()+ "'");
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    Date dsp_fecha = new Date();
                    try {
                        dsp_fecha = datos.getDate("rb_last_update");
                    } catch (SQLException e) {
                        OptionPane.showMsg("Error al convertir fecha", "Se cayó al intentar convertir la fecha.\nDetalle: " + e.getMessage() + "\n" + Log.getLog(), JOptionPane.ERROR_MESSAGE);
                    }
                    if (!fn.date.Cmp.localIsNewOrEqual(object.getLastUpdate(), dsp_fecha)) {
                        LcBd.cerrar();
                        return false;
                    }
                }
                PreparedStatement insert = LcBd.obtener().prepareStatement(
                        sqlUpdate(object));
                insert.executeUpdate();
                LcBd.cerrar();
                return true;
            }
            if(objectParam instanceof TipoPago){
                TipoPago object = (TipoPago)objectParam;
                PreparedStatement consulta = LcBd.obtener().prepareStatement("SELECT * FROM tipo_pago WHERE tp_id=" + object.getId()+ "");
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    Date dsp_fecha = new Date();
                    try {
                        dsp_fecha = datos.getDate("tp_last_update");
                    } catch (SQLException e) {
                        OptionPane.showMsg("Error al convertir fecha", "Se cayó al intentar convertir la fecha.\nDetalle: " + e.getMessage() + "\n" + Log.getLog(), JOptionPane.ERROR_MESSAGE);
                    }
                    if (!fn.date.Cmp.localIsNewOrEqual(object.getLastUpdate(), dsp_fecha)) {
                        LcBd.cerrar();
                        return false;
                    }
                }
                PreparedStatement insert = LcBd.obtener().prepareStatement(
                        sqlUpdate(object));
                insert.executeUpdate();
                LcBd.cerrar();
                return true;
            }
            if(objectParam instanceof User){
                User object = (User)objectParam;
                if (object == null) {
                    return false;
                }
                PreparedStatement consulta = LcBd.obtener().prepareStatement("SELECT * FROM usuario WHERE us_id=" + object.getId());
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    Date dsp_fecha = new Date();
                    try {
                        dsp_fecha = datos.getDate("us_last_update");
                    } catch (Exception e) {
                        OptionPane.showMsg("Error al convertir fecha", "Se cayó al intentar convertir la fecha.\nDetalle:\n" + Log.getLog(), JOptionPane.ERROR_MESSAGE);
                    }
                    if (dsp_fecha == null) {
                        dsp_fecha = new Date();
                    }
                    if (!fn.date.Cmp.localIsNewOrEqual(object.getLastUpdate(), dsp_fecha)) {
                        return false;
                    }
                }
                PreparedStatement insert = LcBd.obtener().prepareStatement(
                        sqlUpdate(object));
                insert.executeUpdate();
                LcBd.cerrar();
                return true;
            }
        }catch(SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex){
            Logger.getLogger(Local.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    @Override
    public int getMaxId(Object type) {
        Log.setLog(className, Log.getReg());
        int id = 0;
        try{
            String sql = "";
            if(type instanceof Cristal){
                sql = "SELECT MAX(cri_id) as id FROM cristal";
            }
            if(type instanceof Convenio){
                sql = "SELECT MAX(cnv_id) as id FROM convenio";
            }
            if(type instanceof Descuento){
                sql = "SELECT MAX(des_id) as id FROM descuento";
            }
            if(type instanceof Equipo){
                sql = "SELECT MAX(eq_id) as id FROM equipo";
            }
            if(type instanceof Institucion){
                sql = "SELECT MAX(ins_id) as id FROM institucion";
            }
            if(type instanceof Inventario){
                sql = "SELECT MAX(inv_id) as id FROM inventario";
            }
            if (type instanceof Oficina) {
                sql = "SELECT MAX(of_id) as id FROM oficina";
            }
            if (type instanceof TipoPago) {
                sql = "SELECT MAX(tp_id) as id FROM tipo_pago";
            }
            if(type instanceof User){
                sql = "SELECT MAX(us_id) as id FROM usuario";
            }
            if(sql.length()>2){
                PreparedStatement consulta = LcBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    id = datos.getInt("id");
                }
                LcBd.cerrar();
            }
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Local.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id + 1;
    }
    @Override
    public ArrayList<Object> listar(String idParam, Object type) {
        //Falta ordenar y agregar clases
        Log.setLog(className, Log.getReg());
        ArrayList<Object> lista = new ArrayList<>();
        try {
            if(type instanceof Cliente){
                String sql = "SELECT * FROM cliente WHERE cli_rut='" + idParam + "'";
                if (idParam.equals("0")) {
                    sql = "SELECT * FROM cliente WHERE cli_estado=1";
                }
                if (idParam.equals("-1")) {
                    sql = "SELECT * FROM cliente WHERE cli_estado=0";
                }
                if (idParam.equals("-2")) {
                    sql = "SELECT * FROM cliente";
                }

                PreparedStatement consulta = LcBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new Cliente(
                        datos.getString("cli_rut"),
                        datos.getString("cli_nombre"),
                        datos.getString("cli_telefono1"),
                        datos.getString("cli_telefono2"),
                        datos.getString("cli_email"),
                        datos.getString("cli_direccion"),
                        datos.getString("cli_comuna"),
                        datos.getString("cli_ciudad"),
                        datos.getInt("cli_sexo"),
                        datos.getInt("cli_edad"),
                        datos.getInt("cli_estado"),
                        datos.getDate("cli_last_update"),
                        datos.getInt("cli_last_hour")
                        )
                    );
                }
                LcBd.cerrar();
                return lista;
            }
            if(type instanceof Convenio){
                String sql = "SELECT * FROM convenio WHERE cnv_id=" + idParam + "";
                if (idParam.equals("0")) {
                    sql = "SELECT * FROM convenio WHERE cnv_estado=1";
                }
                if (idParam.equals("-1")) {
                    sql = "SELECT * FROM convenio WHERE cnv_estado=0";
                }
                if (idParam.equals("-2")) {
                    sql = "SELECT * FROM convenio";
                }

                PreparedStatement consulta = LcBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new Convenio(
                        datos.getInt("cnv_id"),
                        datos.getString("cnv_nombre"),
                        datos.getDate("cnv_fecha_inicio"),
                        datos.getDate("cnv_fecha_fin"),
                        datos.getInt("cnv_cuotas"),
                        datos.getInt("cnv_monto_maximo"),
                        datos.getInt("cnv_monto_pp"),
                        datos.getInt("cnv_maximo_clientes"),
                        datos.getInt("descuento_des_id"),
                        datos.getInt("institucion_ins_id"),
                        datos.getInt("cnv_estado"),
                        datos.getDate("cnv_last_update"),
                        datos.getInt("cnv_last_hour")
                        )
                    );
                }
                LcBd.cerrar();
                return lista;
            }
            if(type instanceof Cristal){
                String sql = "SELECT * FROM cristal WHERE cri_nombre='" + idParam + "'";
                if (idParam.equals("0")) {
                    sql = "SELECT * FROM cristal WHERE cri_estado=1";
                }
                if (idParam.equals("-1")) {
                    sql = "SELECT * FROM cristal WHERE cri_estado=0";
                }
                if (idParam.equals("-2")) {
                    sql = "SELECT * FROM cristal";
                }

                PreparedStatement consulta = LcBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new Cristal(
                        datos.getInt("cri_id"),
                        datos.getString("cri_nombre"),
                        datos.getInt("cri_precio"),
                        datos.getInt("cri_estado"),
                        datos.getDate("cri_last_update"),
                        datos.getInt("cri_last_hour")
                        )
                    );
                }
                LcBd.cerrar();
                return lista;
            }
            if(type instanceof Descuento){
                String sql = "SELECT * FROM descuento WHERE des_nombre='" + idParam + "'";
                if (idParam.equals("0")) {
                    sql = "SELECT * FROM descuento WHERE des_estado=1";
                }
                if (idParam.equals("-1")) {
                    sql = "SELECT * FROM descuento WHERE des_estado=0";
                }
                if (idParam.equals("-2")) {
                    sql = "SELECT * FROM descuento";
                }

                PreparedStatement consulta = LcBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new Descuento(
                        datos.getInt("des_id"),
                        datos.getString("des_nombre"),
                        datos.getString("des_descripcion"),
                        datos.getInt("des_porc"),
                        datos.getInt("des_monto"),
                        datos.getInt("des_estado"),
                        datos.getDate("des_last_update"),
                        datos.getInt("des_last_hour")
                        )
                    );
                }
                LcBd.cerrar();
                return lista;
            }
            if (type instanceof Doctor){
                String sql = "SELECT * FROM doctor WHERE doc_rut='" + idParam + "'";
                if (idParam.equals("0")) {
                    sql = "SELECT * FROM doctor WHERE doc_estado=1";
                }
                if (idParam.equals("-1")) {
                    sql = "SELECT * FROM doctor WHERE doc_estado=0";
                }
                if (idParam.equals("-2")) {
                    sql = "SELECT * FROM doctor";
                }

                PreparedStatement consulta = LcBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new Doctor(
                        datos.getString("doc_rut"),
                        datos.getString("doc_nombre"),
                        datos.getString("doc_telefono"),
                        datos.getString("doc_mail"),
                        datos.getInt("doc_estado"),
                        datos.getDate("doc_last_update"),
                        datos.getInt("doc_last_hour")
                        )
                    );
                }
                LcBd.cerrar();
                return lista;
            }
            if (type instanceof Equipo){
                String sql = "SELECT * FROM equipo WHERE eq_id =" + idParam + "";
                if (idParam.equals("0")) {
                    sql = "SELECT * FROM equipo WHERE eq_estado=1";
                }
                if (idParam.equals("-1")) {
                    sql = "SELECT * FROM equipo WHERE eq_estado=0";
                }
                if (idParam.equals("-2")) {
                    sql = "SELECT * FROM equipo";
                }

                PreparedStatement consulta = LcBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new Equipo(
                        datos.getInt("eq_id"),
                        datos.getString("eq_nombre"),
                        datos.getString("eq_licencia"),
                        datos.getInt("eq_estado"),
                        datos.getDate("eq_last_update"),
                        datos.getInt("eq_last_hour")
                        )
                    );
                }
                LcBd.cerrar();
                return lista;
            }
            if (type instanceof Institucion){
                String sql = "SELECT * FROM institucion WHERE ins_id =" + idParam + "";
                if (idParam.equals("0")) {
                    sql = "SELECT * FROM institucion WHERE ins_estado=1";
                }
                if (idParam.equals("-1")) {
                    sql = "SELECT * FROM institucion WHERE ins_estado=0";
                }
                if (idParam.equals("-2")) {
                    sql = "SELECT * FROM institucion";
                }

                PreparedStatement consulta = LcBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new Institucion(
                        datos.getInt("ins_id"),
                        datos.getString("ins_nombre"),
                        datos.getString("ins_telefono"),
                        datos.getString("ins_mail"),
                        datos.getString("ins_direccion"),
                        datos.getString("ins_comuna"),
                        datos.getString("ins_ciudad"),
                        datos.getInt("ins_estado"),
                        datos.getDate("ins_last_update"),
                        datos.getInt("ins_last_hour")
                        )
                    );
                }
                LcBd.cerrar();
                return lista;
            }
            if (type instanceof Inventario){
                String sql = "SELECT * FROM inventario WHERE inv_id =" + idParam + "";
                if (idParam.equals("0")) {
                    sql = "SELECT * FROM inventario WHERE inv_estado=1";
                }
                if (idParam.equals("-1")) {
                    sql = "SELECT * FROM inventario WHERE inv_estado=0";
                }
                if (idParam.equals("-2")) {
                    sql = "SELECT * FROM inventario";
                }

                PreparedStatement consulta = LcBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new Inventario(
                        datos.getInt("inv_id"),
                        datos.getString("inv_nombre"),
                        datos.getString("inv_descripcion"),
                        datos.getInt("inv_estado"),
                        datos.getDate("inv_last_update"),
                        datos.getInt("inv_last_hour")
                        )
                    );
                }
                LcBd.cerrar();
                return lista;
            }
            if (type instanceof Lente){
                String sql = "SELECT * FROM lente WHERE len_id ='" + idParam + "'";
                if (idParam.equals("0")) {
                    sql = "SELECT * FROM lente WHERE len_estado=1";
                }
                if (idParam.equals("-1")) {
                    sql = "SELECT * FROM lente WHERE len_estado=0";
                }
                if (idParam.equals("-2")) {
                    sql = "SELECT * FROM lente";
                }

                PreparedStatement consulta = LcBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new Lente(
                        datos.getString("len_id"),
                        datos.getString("len_color"),
                        datos.getString("len_tipo"),
                        datos.getString("len_marca"),
                        datos.getString("len_material"),
                        datos.getInt("len_flex"),
                        datos.getInt("len_clasificacion"),
                        datos.getString("len_descripcion"),
                        datos.getInt("len_precio_ref"),
                        datos.getInt("len_precio_act"),
                        datos.getInt("len_stock"),
                        datos.getInt("len_stock_min"),
                        datos.getString("inventario_inv_id"),
                        datos.getInt("len_estado"),
                        datos.getDate("len_last_update"),
                        datos.getInt("len_last_hour")
                        )
                    );
                }
                LcBd.cerrar();
                return lista;
            }
            if (type instanceof Oficina) {
                String sql = "SELECT * FROM oficina WHERE of_nombre='" + idParam + "'";
                if (idParam.equals("0")) {
                    sql = "SELECT * FROM oficina WHERE of_estado=1";
                }
                if (idParam.equals("-1")) {
                    sql = "SELECT * FROM oficina WHERE of_estado=0";
                }
                if (idParam.equals("-2")) {
                    sql = "SELECT * FROM oficina";
                }

                PreparedStatement consulta = LcBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new Oficina(
                            datos.getInt("of_id"),
                             datos.getString("of_nombre"),
                             datos.getString("of_direccion"),
                             datos.getString("of_ciudad"),
                             datos.getString("of_telefono1"),
                             datos.getString("of_telefono2"),
                             datos.getString("of_email"),
                             datos.getString("of_web"),
                             datos.getInt("of_estado"),
                             datos.getDate("of_last_update"),
                             datos.getInt("of_last_hour")
                    )
                    );
                }
                LcBd.cerrar();
                return lista;
            }
            if (type instanceof RegistroBaja) {
                String sql = "SELECT * FROM registro_bajas WHERE rb_id='" + idParam + "'";
                if (idParam.equals("0")) {
                    sql = "SELECT * FROM registro_bajas WHERE rb_estado=1";
                }
                if (idParam.equals("-1")) {
                    sql = "SELECT * FROM registro_bajas WHERE rb_estado=0";
                }
                if (idParam.equals("-2")) {
                    sql = "SELECT * FROM registro_bajas";
                }

                PreparedStatement consulta = LcBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new RegistroBaja(
                            datos.getString("rb_id"),
                            datos.getDate("rb_fecha"),
                            datos.getString("lente_len_id"),
                            datos.getInt("rb_cantidad"),
                            datos.getString("rb_obs"),
                            datos.getInt("rb_estado"),
                            datos.getDate("rb_last_update"),
                            datos.getInt("rb_last_hour")
                        )
                    );
                }
                LcBd.cerrar();
                return lista;
            }
            if (type instanceof TipoPago) {
                String sql = "SELECT * FROM tipo_pago WHERE tp_id=" + idParam + "";
                if (idParam.equals("0")) {
                    sql = "SELECT * FROM tipo_pago WHERE tp_estado=1";
                }
                if (idParam.equals("-1")) {
                    sql = "SELECT * FROM tipo_pago WHERE tp_estado=0";
                }
                if (idParam.equals("-2")) {
                    sql = "SELECT * FROM tipo_pago";
                }

                PreparedStatement consulta = LcBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new TipoPago(
                            datos.getInt("tp_id"),
                            datos.getString("tp_nombre"),
                            datos.getInt("tp_estado"),
                            datos.getDate("tp_last_update"),
                            datos.getInt("tp_last_hour")
                        )
                    );
                }
                LcBd.cerrar();
                return lista;
            }
            if(type instanceof User){
                String sql = "SELECT * FROM usuario WHERE usu_username='" + idParam + "'";
                if (idParam.equals("0")) {
                    sql = "SELECT * FROM usuario WHERE usu_estado=1";
                }
                if (idParam.equals("-1")) {
                    sql = "SELECT * FROM usuario WHERE usu_estado=0";
                }
                if (idParam.equals("-2")) {
                    sql = "SELECT * FROM usuario";
                }

                PreparedStatement consulta = LcBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new User(
                        datos.getInt("us_id"),
                        datos.getString("us_nombre"),
                        datos.getString("us_username"),
                        datos.getString("us_email"),
                        datos.getString("us_pass"),
                        datos.getInt("us_tipo"),
                        datos.getInt("us_estado"),
                        datos.getDate("us_last_update"),
                        datos.getInt("us_last_hour")
                        )
                    );
                }
                LcBd.cerrar();
                return lista;
            }
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Local.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    /**
     * Retorna una lista de objetos comparndo por la ultima fecha de actualización con
     * paramDate y tipo de objeto con type
     * @param paramDate
     * @param type
     * @return 
     */
    @Override
    public ArrayList<Object> listar(Date paramDate, Object type) {
        Log.setLog(className, Log.getReg());
        java.sql.Date param = new java.sql.Date(paramDate.getTime());
        ArrayList<Object> lista = new ArrayList<>();
        try {
            if(type instanceof Cliente){
                String sql = "SELECT * FROM cliente WHERE cli_last_update >='" + param + "'";

                PreparedStatement consulta = LcBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new Cliente(
                        datos.getString("cli_rut"),
                        datos.getString("cli_nombre"),
                        datos.getString("cli_telefono1"),
                        datos.getString("cli_telefono2"),
                        datos.getString("cli_email"),
                        datos.getString("cli_direccion"),
                        datos.getString("cli_comuna"),
                        datos.getString("cli_ciudad"),
                        datos.getInt("cli_sexo"),
                        datos.getInt("cli_edad"),
                        datos.getInt("cli_estado"),
                        datos.getDate("cli_last_update"),
                        datos.getInt("cli_last_hour")
                        )
                    );
                }
                LcBd.cerrar();
                return lista;
            }
            if(type instanceof Convenio){
                String sql = "SELECT * FROM convenio WHERE cnv_last_update >='" + param + "'";

                PreparedStatement consulta = LcBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new Convenio(
                        datos.getInt("cnv_id"),
                        datos.getString("cnv_nombre"),
                        datos.getDate("cnv_fecha_inicio"),
                        datos.getDate("cnv_fecha_fin"),
                        datos.getInt("cnv_cuotas"),
                        datos.getInt("cnv_monto_maximo"),
                        datos.getInt("cnv_monto_pp"),
                        datos.getInt("cnv_maximo_clientes"),
                        datos.getInt("descuento_des_id"),
                        datos.getInt("institucion_ins_id"),
                        datos.getInt("cnv_estado"),
                        datos.getDate("cnv_last_update"),
                        datos.getInt("cnv_last_hour")
                        )
                    );
                }
                LcBd.cerrar();
                return lista;
            }
            if(type instanceof Cristal){
                String sql = "SELECT * FROM cristal WHERE cri_last_update >='" + param + "'";

                PreparedStatement consulta = LcBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new Cristal(
                        datos.getInt("cri_id"),
                        datos.getString("cri_nombre"),
                        datos.getInt("cri_precio"),
                        datos.getInt("cri_estado"),
                        datos.getDate("cri_last_update"),
                        datos.getInt("cri_last_hour")
                        )
                    );
                }
                LcBd.cerrar();
                return lista;
            }
            if(type instanceof Descuento){
                String sql = "SELECT * FROM descuento WHERE des_last_update >='" + param + "'";

                PreparedStatement consulta = LcBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new Descuento(
                        datos.getInt("des_id"),
                        datos.getString("des_nombre"),
                        datos.getString("des_descripcion"),
                        datos.getInt("des_porc"),
                        datos.getInt("des_monto"),
                        datos.getInt("des_estado"),
                        datos.getDate("des_last_update"),
                        datos.getInt("des_last_hour")
                        )
                    );
                }
                LcBd.cerrar();
                return lista;
            }
            if(type instanceof Doctor){
                String sql = "SELECT * FROM doctor WHERE doc_last_update >='" + param + "'";

                PreparedStatement consulta = LcBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new Doctor(
                        datos.getString("doc_rut"),
                        datos.getString("doc_nombre"),
                        datos.getString("doc_telefono"),
                        datos.getString("doc_mail"),
                        datos.getInt("doc_estado"),
                        datos.getDate("doc_last_update"),
                        datos.getInt("doc_last_hour")
                        )
                    );
                }
                LcBd.cerrar();
                return lista;
            }
            if(type instanceof Equipo){
                String sql = "SELECT * FROM equipo WHERE eq_last_update >='" + param + "'";

                PreparedStatement consulta = LcBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new Equipo(
                        datos.getInt("eq_id"),
                        datos.getString("eq_nombre"),
                        datos.getString("eq_licencia"),
                        datos.getInt("eq_estado"),
                        datos.getDate("eq_last_update"),
                        datos.getInt("eq_last_hour")
                        )
                    );
                }
                LcBd.cerrar();
                return lista;
            }
            if(type instanceof Institucion){
                String sql = "SELECT * FROM institucion WHERE ins_last_update >='" + param + "'";

                PreparedStatement consulta = LcBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new Institucion(
                        datos.getInt("ins_id"),
                        datos.getString("ins_nombre"),
                        datos.getString("ins_telefono"),
                        datos.getString("ins_mail"),
                        datos.getString("ins_direccion"),
                        datos.getString("ins_comuna"),
                        datos.getString("ins_ciudad"),
                        datos.getInt("ins_estado"),
                        datos.getDate("ins_last_update"),
                        datos.getInt("ins_last_hour")
                        )
                    );
                }
                LcBd.cerrar();
                return lista;
            }
            if(type instanceof Inventario){
                String sql = "SELECT * FROM inventario WHERE inv_last_update >='" + param + "'";

                PreparedStatement consulta = LcBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new Inventario(
                        datos.getInt("inv_id"),
                        datos.getString("inv_nombre"),
                        datos.getString("inv_descripcion"),
                        datos.getInt("inv_estado"),
                        datos.getDate("inv_last_update"),
                        datos.getInt("inv_last_hour")
                        )
                    );
                }
                LcBd.cerrar();
                return lista;
            }
            if(type instanceof Lente){
                String sql = "SELECT * FROM lente WHERE len_last_update >='" + param + "'";

                PreparedStatement consulta = LcBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new Lente(
                        datos.getString("len_id"),
                        datos.getString("len_color"),
                        datos.getString("len_tipo"),
                        datos.getString("len_marca"),
                        datos.getString("len_material"),
                        datos.getInt("len_flex"),
                        datos.getInt("len_clasificacion"),
                        datos.getString("len_descripcion"),
                        datos.getInt("len_precio_ref"),
                        datos.getInt("len_precio_act"),
                        datos.getInt("len_stock"),
                        datos.getInt("len_stock_min"),
                        datos.getString("inventario_inv_id"),
                        datos.getInt("len_estado"),
                        datos.getDate("len_last_update"),
                        datos.getInt("len_last_hour")
                        )
                    );
                }
                LcBd.cerrar();
                return lista;
            }
            if (type instanceof Oficina) {
                String sql = "SELECT * FROM oficina WHERE of_last_update >='" + param + "'";

                PreparedStatement consulta = LcBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new Oficina(
                        datos.getInt("of_id"),
                        datos.getString("of_nombre"),
                        datos.getString("of_direccion"),
                        datos.getString("of_ciudad"),
                        datos.getString("of_telefono1"),
                        datos.getString("of_telefono2"),
                        datos.getString("of_email"),
                        datos.getString("of_web"),
                        datos.getInt("of_estado"),
                        datos.getDate("of_last_update"),
                        datos.getInt("of_last_hour")
                    )
                    );
                }
                LcBd.cerrar();
                return lista;
            }
            if (type instanceof RegistroBaja) {
                String sql = "SELECT * FROM registro_bajas WHERE rb_last_update >='" + param + "'";

                PreparedStatement consulta = LcBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new RegistroBaja(
                            datos.getString("rb_id"),
                            datos.getDate("rb_fecha"),
                            datos.getString("lente_len_id"),
                            datos.getInt("rb_cantidad"),
                            datos.getString("rb_obs"),
                            datos.getInt("rb_estado"),
                            datos.getDate("rb_last_update"),
                            datos.getInt("rb_last_hour")
                        )
                    );
                }
                LcBd.cerrar();
                return lista;
            }
            if (type instanceof TipoPago) {
                String sql = "SELECT * FROM tipo_pago WHERE tp_last_update >='" + param + "'";

                PreparedStatement consulta = LcBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new TipoPago(
                            datos.getInt("tp_id"),
                            datos.getString("tp_nombre"),
                            datos.getInt("tp_estado"),
                            datos.getDate("tp_last_update"),
                            datos.getInt("tp_last_hour")
                        )
                    );
                }
                LcBd.cerrar();
                return lista;
            }
            if(type instanceof User){
                String sql = "SELECT * FROM usuario WHERE us_last_update >='" + param + "'";

                PreparedStatement consulta = LcBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new User(
                        datos.getInt("us_id"),
                        datos.getString("us_nombre"),
                        datos.getString("us_username"),
                        datos.getString("us_email"),
                        datos.getString("us_pass"),
                        datos.getInt("us_tipo"),
                        datos.getInt("us_estado"),
                        datos.getDate("us_last_update"),
                        datos.getInt("us_last_hour")
                        )
                    );
                }
                LcBd.cerrar();
                return lista;
            }
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Local.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    /**
     * @param cod 
     * Cliente=>rut,
     * Cristal=>nombre,
     * Descuento=>nombre,
     * Doctor=>rut,
     * Lente=>cod,
     * Oficina=>nombre,
     * RegistroBaja=>cod,
     * User=>username
     * @param id
     * Institucion=>id,
     * TipoPago=>id
     * @param type
     * Tipo de clase que se desea retornar
     * @return Retorna la clase de tipo Object, luego sólo se debe parsear.
     */
    @Override
    public Object getElement(String cod,int id, Object type) {
        Log.setLog(className, Log.getReg());
        try{
            if(type instanceof Cliente){
                for (Object object : listar(cod, type)) {//id debe ser el rut del cliente
                    if (((Cliente) object).getCod().equals(cod)) {
                        return object;
                    }
                }
            }
            if(type instanceof Convenio){
                for (Object object : listar(""+id, type)) {//id debe ser el rut del cliente
                    if (((Convenio) object).getId() == id ) {
                        return object;
                    }
                }
            }
            if(type instanceof Cristal){
                for (Object object : listar(cod, type)) {//id debe ser el nombre del cristal
                    if (((Cristal) object).getNombre().toLowerCase().equals(cod.toLowerCase())) {
                        return object;
                    }
                }
            }
            if(type instanceof Descuento){
                for (Object object : listar(cod, type)) {//id debe ser el nombre del descuento
                    if (((Descuento) object).getNombre().toLowerCase().equals(cod.toLowerCase())) {
                        return object;
                    }
                }
            }
            if(type instanceof Doctor){
                for (Object object : listar(cod, type)) {//id debe ser el rut del doctor
                    if (((Doctor) object).getCod().equals(cod)) {
                        return object;
                    }
                }
            }
            if(type instanceof Equipo){
                for (Object object : listar(""+id, type)) {//id debe ser el rut del doctor
                    if (((Equipo) object).getId() == id) {
                        return object;
                    }
                }
            }
            if(type instanceof Institucion){
                for (Object object : listar(""+id, type)) {//id debe ser el id de la institucion
                    if (((Institucion) object).getId() == id) {
                        return object;
                    }
                }
            }
            if(type instanceof Inventario){
                for (Object object : listar(""+id, type)) {//id debe ser el rut del doctor
                    if (((Inventario) object).getId() == id) {
                        return object;
                    }
                }
            }
            if(type instanceof Lente){
                for (Object object : listar(cod, type)) {//id debe ser el id de la institucion
                    if (((Lente) object).getCod().equals(cod)) {
                        return object;
                    }
                }
            }
            if (type instanceof Oficina) {
                for (Object object : listar(cod, type)) {//id debe ser el id de la ficha
                    if (((Oficina) object).getNombre().toLowerCase().equals(cod.toLowerCase())) {
                        return object;
                    }
                }
            }
            if (type instanceof RegistroBaja) {
                for (Object object : listar(cod, type)) {//id debe ser el id de la ficha
                    if (((RegistroBaja) object).getCod().equals(cod)) {
                        return object;
                    }
                }
            }
            if (type instanceof TipoPago) {
                for (Object object : listar(""+id, type)) {//id debe ser el id de la ficha
                    if (((TipoPago) object).getId() == id) {
                        return object;
                    }
                }
            }
            if(type instanceof User){
                for (Object object : listar(cod, type)) {//idParam debe ser el rut
                    if (((User) object).getUsername().toLowerCase().equals(cod.toLowerCase())) {
                        return object;
                    }
                }
            }
        }catch(Exception ex){
            Logger.getLogger(Local.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    /**
     * Compara por los siguientes atributos:
     * Cliente=>rut,
     * Cristal=>nombre,
     * Descuento=>nombre,
     * Doctor=>rut,
     * Lente=>cod,
     * Oficina=>nombre,
     * RegistroBaja=>cod,
     * User=>username,
     * Institucion=>id,
     * TipoPago=>id
     * @param object
     * @return 
     */
    @Override
    public boolean exist(Object object) {
        Log.setLog(className, Log.getReg());
        if (object instanceof Cliente) {
            Log.setLog(className, Log.getReg());
            if (getElement(((Cliente) object).getCod(),0,object) != null) {
                return true;
            }
        }
        if (object instanceof Convenio) {
            Log.setLog(className, Log.getReg());
            if (getElement(null,((Convenio) object).getId(),object) != null) {
                return true;
            }
        }
        if (object instanceof Cristal) {
            Log.setLog(className, Log.getReg());
            if (getElement(((Cristal) object).getNombre(),0,object) != null) {
                return true;
            }
        }
        if (object instanceof Descuento) {
            Log.setLog(className, Log.getReg());
            if (getElement(((Descuento) object).getNombre(),0,object) != null) {
                return true;
            }
        }
        if (object instanceof Doctor) {
            if (getElement(((Doctor) object).getCod(),0,object) != null) {
                return true;
            }
        }
        if (object instanceof Equipo) {
            if (getElement(null,((Equipo) object).getId(),object) != null) {
                return true;
            }
        }
        if (object instanceof Institucion) {
            if (getElement(null,((Institucion) object).getId(),object) != null) {
                return true;
            }
        }
        if (object instanceof Inventario) {
            if (getElement(null,((Inventario) object).getId(),object) != null) {
                return true;
            }
        }
        if (object instanceof Lente) {
            if (getElement(((Lente) object).getCod(),0,object) != null) {
                return true;
            }
        }
        if (object instanceof Oficina) {
            if (getElement(((Oficina) object).getNombre(),0, object) != null) {
                return true;
            }
        }
        if (object instanceof RegistroBaja) {
            if (getElement(((RegistroBaja) object).getCod(),0, object) != null) {
                return true;
            }
        }
        if (object instanceof TipoPago) {
            if (getElement(null,((TipoPago) object).getId(), object) != null) {
                return true;
            }
        }
        if (object instanceof User) {
            if (getElement(((User) object).getUsername(),0,object) != null) {
                return true;
            }
        }
        return false;
    }
    private String sqlInsert(Object objectParam){
        
        if(objectParam instanceof Cliente){
            Cliente object = (Cliente)objectParam;
            java.sql.Date sqlfecha = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "INSERT INTO cliente VALUES('"
                    + object.getCod() + "','"
                    + object.getNombre() + "','"
                    + object.getTelefono1() + "','"
                    + object.getTelefono2() + "','"
                    + object.getEmail() + "','"
                    + object.getDireccion() + "','"
                    + object.getComuna() + "','"
                    + object.getCiudad() + "',"
                    + object.getSexo() + ","
                    + object.getEdad() + ","
                    + object.getEstado() + ",'"
                    + sqlfecha + "',"
                    + object.getLastHour() + ")";
        }
        if(objectParam instanceof Convenio){
            Convenio object = (Convenio)objectParam;
            java.sql.Date sqlfecha1 = new java.sql.Date(object.getFechaInicio().getTime());//la transforma a sql.Date
            java.sql.Date sqlfecha2 = new java.sql.Date(object.getFechaFin().getTime());//la transforma a sql.Date
            java.sql.Date sqlfecha3 = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "INSERT INTO convenio VALUES("
                    + object.getId()+ ",'"
                    + object.getNombre() + "','"
                    + sqlfecha1+ "','"
                    + sqlfecha2+ "','"
                    + object.getCuotas()+ "','"
                    + object.getMontoMaximo()+ "','"
                    + object.getMontoPp()+ "','"
                    + object.getMaximoClientes()+ "',"
                    + object.getIdDescuento()+ ","
                    + object.getIdInstitucion()+ ","
                    + object.getEstado() + ",'"
                    + sqlfecha3 + "',"
                    + object.getLastHour() + ")";
        }
        if(objectParam instanceof Cristal){
            Cristal object = (Cristal)objectParam;
            java.sql.Date sqlfecha = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "INSERT INTO cristal VALUES("
                    + object.getId() + ",'"
                    + object.getNombre() + "',"
                    + object.getPrecio() + ","
                    + object.getEstado() + ",'"
                    + sqlfecha + "',"
                    + object.getLastHour() + ")";
        }
        if(objectParam instanceof Descuento){
            Descuento object = (Descuento)objectParam;
            java.sql.Date sqlfecha = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "INSERT INTO descuento VALUES("
                    + object.getId() + ",'"
                    + object.getNombre() + "','"
                    + object.getDescripcion() + "',"
                    + object.getPorcetange() + ","
                    + object.getMonto() + ","
                    + object.getEstado() + ",'"
                    + sqlfecha + "',"
                    + object.getLastHour() + ")";
        }
        if(objectParam instanceof Doctor){
            Doctor object = (Doctor)objectParam;
            java.sql.Date sqlfecha = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "INSERT INTO doctor VALUES('"
                            + object.getCod() + "','"
                            + object.getNombre() + "','"
                            + object.getTelefono() + "','"
                            + object.getEmail() + "',"
                            + object.getEstado() + ",'"
                            + sqlfecha + "',"
                            + object.getLastHour() + ")";
        }
        if(objectParam instanceof Equipo){
            Equipo object = (Equipo)objectParam;
            java.sql.Date sqlfecha = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "INSERT INTO equipo VALUES("
                            + object.getId()+ ",'"
                            + object.getNombre() + "','"
                            + object.getLicencia()+ "',"
                            + object.getEstado() + ",'"
                            + sqlfecha + "',"
                            + object.getLastHour() + ")";
        }
        if(objectParam instanceof Institucion){
            Institucion object = (Institucion)objectParam;
            java.sql.Date sqlfecha = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "INSERT INTO institucion VALUES("
                            + object.getId()+ ",'"
                            + object.getNombre() + "','"
                            + object.getTelefono() + "','"
                            + object.getEmail() + "','"
                            + object.getDireccion()+ "','"
                            + object.getComuna()+ "','"
                            + object.getCiudad()+ "',"
                            + object.getEstado() + ",'"
                            + sqlfecha + "',"
                            + object.getLastHour() + ")";
        }
        if(objectParam instanceof Inventario){
            Inventario object = (Inventario)objectParam;
            java.sql.Date sqlfecha = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "INSERT INTO inventario VALUES("
                            + object.getId()+ ",'"
                            + object.getNombre() + "','"
                            + object.getDescripcion()+ "',"
                            + object.getEstado() + ",'"
                            + sqlfecha + "',"
                            + object.getLastHour() + ")";
        }
        if(objectParam instanceof Lente){
            Lente object = (Lente)objectParam;
            java.sql.Date sqlfecha = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "INSERT INTO lente VALUES('"
                            + object.getCod()+ "','"
                            + object.getColor()+ "','"
                            + object.getTipo()+ "','"
                            + object.getMarca()+ "','"
                            + object.getMaterial()+ "',"
                            + object.getFlex()+ ","
                            + object.getClasificacion()+ ",'"
                            + object.getDescripcion()+ "',"
                            + object.getPrecioRef()+ ","
                            + object.getPrecioAct()+ ","
                            + object.getStock()+ ","
                            + object.getStockMin()+ ",'"
                            + object.getInventario()+ "',"
                            + object.getEstado()+ ",'"
                            + sqlfecha + "',"
                            + object.getLastHour() + ")";
        }
        if(objectParam instanceof Oficina){
            Oficina object = (Oficina)objectParam;
            java.sql.Date sqlfecha = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "INSERT INTO oficina VALUES("
                            + object.getId() + ",'"
                            + object.getNombre() + "','"
                            + object.getDireccion() + "','"
                            + object.getCiudad() + "','"
                            + object.getTelefono1() + "','"
                            + object.getTelefono2() + "','"
                            + object.getEmail() + "','"
                            + object.getWeb() + "',"
                            + object.getEstado() + ",'"
                            + sqlfecha + "',"
                            + object.getLastHour() + ")";
        }
        if(objectParam instanceof RegistroBaja){
            RegistroBaja object = (RegistroBaja)objectParam;
            java.sql.Date sqlfecha1 = new java.sql.Date(object.getFecha().getTime());//la transforma a sql.Date
            java.sql.Date sqlfecha2 = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "INSERT INTO registro_bajas VALUES("
                            + object.getCod()+ ",'"
                            + sqlfecha1 + "','"
                            + object.getIdLente()+ "',"
                            + object.getCantidad()+ ",'"
                            + object.getObs()+ "',"
                            + object.getEstado() + ",'"
                            + sqlfecha2 + "')";
        }
        if(objectParam instanceof TipoPago){
            TipoPago object = (TipoPago)objectParam;
            java.sql.Date sqlfecha = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "INSERT INTO tipo_pago VALUES("
                            + object.getId()+ ",'"
                            + object.getNombre() + "',"
                            + object.getEstado() + ",'"
                            + sqlfecha + "')";
        }
        if(objectParam instanceof User){
            User object = (User)objectParam;
            java.sql.Date sqlfecha = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "INSERT INTO usuario VALUES("
                            + object.getId() + ",'"
                            + object.getNombre() + "','"
                            + object.getUsername() + "','"
                            + object.getEmail() + "','"
                            + object.getPass() + "',"
                            + object.getTipo() + ","
                            + object.getEstado() + ",'"
                            + sqlfecha + "')";
        }
        return null;
    }

    private String sqlUpdate(Object objectParam) {
        if(objectParam instanceof Cliente){
            Cliente object = (Cliente)objectParam;
            java.sql.Date sqlfecha = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return "UPDATE cliente set cli_nombre = '" + object.getNombre()
                        + "', cli_telefono1 = '" + object.getTelefono1()
                        + "', cli_telefono2 = '" + object.getTelefono2()
                        + "', cli_email = '" + object.getEmail()
                        + "', cli_direccion = '" + object.getDireccion()
                        + "', cli_comuna = '" + object.getComuna()
                        + "', cli_ciudad = '" + object.getCiudad()
                        + "', cli_sexo = " + object.getSexo()
                        + ", cli_edad = " + object.getEdad()
                        + ", cli_estado = " + object.getEstado()
                        + ", cli_last_update = '" + sqlfecha
                        + "', cli_last_hour = " + object.getLastHour()
                        + " WHERE cli_rut = '" + object.getCod() + "' AND (cli_last_update <= '" 
                        + sqlfecha + "' AND cli_last_hour < "+object.getLastHour()+")";
        }
        if(objectParam instanceof Convenio){
            Convenio object = (Convenio)objectParam;
            java.sql.Date sqlfecha1 = new java.sql.Date(object.getFechaInicio().getTime());//la transforma a sql.Date
            java.sql.Date sqlfecha2 = new java.sql.Date(object.getFechaFin().getTime());//la transforma a sql.Date
            java.sql.Date sqlfecha3 = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return "UPDATE convenio set cnv_nombre = '" + object.getNombre()
                        + "', cnv_fecha_inicio = '" + sqlfecha1
                        + "', cnv_fecha_fin = '" + sqlfecha2
                        + "', cnv_cuotas = " + object.getCuotas()
                        + ", cnv_monto_maximo = " + object.getMontoMaximo()
                        + ", cnv_monto_pp = " + object.getMontoPp()
                        + ", cnv_maximo_clientes = " + object.getMaximoClientes()
                        + ", descuento_des_id = " + object.getIdDescuento()
                        + ", institucion_ins_id = " + object.getIdInstitucion()
                        + ", cnv_estado = " + object.getEstado()
                        + ", cnv_last_update = '" + sqlfecha3
                        + "', cnv_last_hour = " + object.getLastHour()
                        + " WHERE cnv_id = '" + object.getId()+ "' AND (cnv_last_update <= '" 
                        + sqlfecha3 + "' AND cnv_last_hour < "+object.getLastHour()+")";
        }
        if(objectParam instanceof Cristal){
            Cristal object = (Cristal)objectParam;
            java.sql.Date sqlfecha = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "UPDATE cristal set cri_nombre = '" + object.getNombre()
                        + "', cri_precio = " + object.getPrecio()
                        + ", cri_estado = " + object.getEstado()
                        + ", cri_last_update = '" + sqlfecha
                        + "', cri_last_hour = " + object.getLastHour()
                        + " WHERE cri_id = " + object.getId() + 
                        " AND (cri_last_update <= '" + sqlfecha + "' AND cri_last_hour < "+object.getLastHour()+")";
        }
        if(objectParam instanceof Descuento){
            Descuento object = (Descuento)objectParam;
            java.sql.Date sqlfecha = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "UPDATE descuento set des_nombre = '" + object.getNombre()
                        + "', des_descripcion = '" + object.getDescripcion()
                        + "', des_porc = " + object.getPorcetange()
                        + ", des_monto = " + object.getMonto()
                        + ", des_estado = " + object.getEstado()
                        + ", des_last_update = '" + sqlfecha
                        + "', des_last_hour = " + object.getLastHour()
                        + " WHERE des_id = " + object.getId() + " AND (des_last_update <= '" + sqlfecha 
                        + "' AND des_last_hour < "+object.getLastHour()+")";
        }
        if(objectParam instanceof Doctor){
            Doctor object = (Doctor)objectParam;
            java.sql.Date sqlfecha = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "UPDATE doctor set doc_nombre = '" + object.getNombre()
                        + "', doc_telefono = '" + object.getTelefono()
                        + "', doc_mail = '" + object.getEmail()
                        + "', doc_estado = " + object.getEstado()
                        + ", doc_last_update = '" + sqlfecha
                        + "', doc_last_hour = " + object.getLastHour()
                        + " WHERE doc_rut = '" + object.getCod() + "' AND (doc_last_update <= '" 
                        + sqlfecha + "' AND doc_last_hour < "+object.getLastHour()+")";
        }
        if(objectParam instanceof Equipo){
            Equipo object = (Equipo)objectParam;
            java.sql.Date sqlfecha = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "UPDATE equipo set eq_nombre = '" + object.getNombre()
                        + "', eq_licencia = '" + object.getLicencia()
                        + "', eq_estado = " + object.getEstado()
                        + ", eq_last_update = '" + sqlfecha
                        + "', eq_last_hour = " + object.getLastHour()
                        + " WHERE eq_id = " + object.getId()+ " AND (eq_last_update <= '" 
                        + sqlfecha + "' AND eq_last_hour < "+object.getLastHour()+")";
        }
        if(objectParam instanceof Institucion){
            Institucion object = (Institucion)objectParam;
            java.sql.Date sqlfecha = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "UPDATE institucion set ins_nombre = '" + object.getNombre()
                        + "', ins_telefono = '" + object.getTelefono()
                        + "', ins_mail = '" + object.getEmail()
                        + "', ins_direccion = '" + object.getDireccion()
                        + "', ins_comuna = '" + object.getComuna()
                        + "', ins_ciudad = '" + object.getCiudad()
                        + "', ins_estado = " + object.getEstado()
                        + ", ins_last_update = '" + sqlfecha
                        + "', ins_last_hour = " + object.getLastHour()
                        + " WHERE ins_id = " + object.getId()+ " AND (ins_last_update <= '" 
                        + sqlfecha + "' AND ins_last_hour < "+object.getLastHour()+")";
        }
        if(objectParam instanceof Inventario){
            Inventario object = (Inventario)objectParam;
            java.sql.Date sqlfecha = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "UPDATE inventario set inv_nombre = '" + object.getNombre()
                        + "', inv_descripcion = '" + object.getDescripcion()
                        + "', inv_estado = " + object.getEstado()
                        + ", inv_last_update = '" + sqlfecha
                        + "', inv_last_hour = " + object.getLastHour()
                        + " WHERE inv_id = " + object.getId()+ " AND (inv_last_update <= '" 
                        + sqlfecha + "' AND inv_last_hour < "+object.getLastHour()+")";
        }
        if(objectParam instanceof Lente){
            Lente object = (Lente)objectParam;
            java.sql.Date sqlfecha = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "UPDATE lente set len_color = '" + object.getColor()
                        + "', len_tipo = '" + object.getTipo()
                        + "', len_marca = '" + object.getMarca()
                        + "', len_material = '" + object.getMaterial()
                        + "', len_flex = " + object.getFlex()
                        + ", len_clasificacion = " + object.getClasificacion()
                        + ", len_descripcion = '" + object.getDescripcion()
                        + "', len_precio_ref = " + object.getPrecioRef()
                        + ", len_precio_act = " + object.getPrecioAct()
                        + ", len_stock = " + object.getStock()
                        + ", len_stock_min = " + object.getStockMin()
                        + ", inventario_inv_id = '" + object.getInventario()
                        + "', len_estado = " + object.getEstado()
                        + ", len_last_update = '" + sqlfecha
                        + "', len_last_hour = " + object.getLastHour()
                        + " WHERE len_id = '" + object.getCod()+ "' AND (len_last_update <= '" 
                        + sqlfecha + "' AND len_last_hour < "+object.getLastHour()+")";
        }
        if(objectParam instanceof Oficina){
            Oficina object = (Oficina)objectParam;
            java.sql.Date sqlfecha = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "UPDATE oficina set of_nombre = '" + object.getNombre()
                        + "', of_direccion = '" + object.getDireccion()
                        + "', of_ciudad = '" + object.getCiudad()
                        + "', of_telefono1 = '" + object.getTelefono1()
                        + "', of_telefono2 = '" + object.getTelefono2()
                        + "', of_email = '" + object.getEmail()
                        + "', of_web = '" + object.getWeb()
                        + "', of_estado = " + object.getEstado()
                        + ", of_last_update = '" + sqlfecha
                        + "', of_last_hour = " + object.getLastHour()
                        + " WHERE of_id = " + object.getId() + " AND (of_last_update <= '" 
                        + sqlfecha + "' AND of_last_hour < "+object.getLastHour()+")";
        }
        if(objectParam instanceof RegistroBaja){
            RegistroBaja object = (RegistroBaja)objectParam;
            java.sql.Date sqlfecha1 = new java.sql.Date(object.getFecha().getTime());//la transforma a sql.Date
            java.sql.Date sqlfecha2 = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "UPDATE registro_bajas set rb_fecha = '" + sqlfecha1
                        + "', lente_len_id = '" + object.getIdLente()
                        + "', rb_cantidad = " + object.getCantidad()
                        + ", rb_obs = '" + object.getObs()
                        + "', rb_estado = " + object.getEstado()
                        + ", rb_last_update = '" + sqlfecha2
                        + "', rb_last_hour = " + object.getLastHour()
                        + " WHERE rb_id = '" + object.getCod()+ "' AND (rb_last_update <= '" 
                        + sqlfecha2 + "' AND rb_last_hour < "+object.getLastHour()+")";
        }
        if(objectParam instanceof TipoPago){
            TipoPago object = (TipoPago)objectParam;
            java.sql.Date sqlfecha = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "UPDATE registro_bajas set tp_nombre = '" + sqlfecha
                        + "', tp_estado = " + object.getEstado()
                        + ", tp_last_update = '" + sqlfecha
                        + "', tp_last_hour = " + object.getLastHour()
                        + " WHERE tp_id = " + object.getId()+ " AND (tp_last_update <= '" 
                        + sqlfecha + "' AND tp_last_hour < "+object.getLastHour()+")";
        }
        if(objectParam instanceof User){
            User object = (User)objectParam;
            java.sql.Date sqlfecha = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "UPDATE usuario set us_nombre = '" + object.getNombre()
                        + "', us_username = '" + object.getUsername()
                        + "', us_email = '" + object.getEmail()
                        + "', us_pass = '" + object.getPass()
                        + "', us_tipo = " + object.getTipo()
                        + ", us_estado = " + object.getEstado()
                        + ", us_last_update = '" + sqlfecha
                        + "', us_last_hour = " + object.getLastHour()
                        + " WHERE us_id = " + object.getId() + " AND (us_last_update <= '" 
                        + sqlfecha + "' AND us_last_hour < "+object.getLastHour()+")";
        }
        return null;
    }
}
