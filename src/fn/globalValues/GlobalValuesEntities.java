/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn.globalValues;


import dao.Dao;
import entities.Oficina;
import entities.User;
import entities.ficha.Ficha;
import fn.GV;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jlleivas
 */
public class GlobalValuesEntities {
    public static User USER;
    public static Oficina OFICINA;
    public static Ficha stFicha = new Ficha();
    public static Ficha stFichaConvenio = new Ficha();
    public static Ficha stOpenFicha = new Ficha();
    
    
    public static Ficha getFicha(){
        return stFicha;
    }
    
    public static void setFicha(Ficha value){
        stFicha = value;
    }
    
    public static Ficha getFichaConvenio(){
        return stFichaConvenio;
    }
    
    public static void setFichaConvenio(Ficha value){
        stFichaConvenio = value;
    }
    
    /**
     * retorna la ficha que se seleccionó para mostrar sus datos
     * @return 
     */
    public static Ficha getOpenFicha(){
        return stOpenFicha;
    }
    /**
     * Recibe una ficha para mostrar los datos en una nueva ventana
     * @param value 
     */
    public static void setOpenFicha(Ficha value){
        stOpenFicha = value;
    }
    
    public static void setOficina(Oficina oficina){
        OFICINA = oficina;
    }
    
    public static boolean setOficina(String nombre){
        Dao load = new Dao();
        try {
            Oficina temp = (Oficina)load.get(GV.getStr(nombre), 0, new Oficina());
            if(temp != null){
                setOficina(temp);
                return true;
            }
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(GlobalValuesEntities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static String getNombreOficina() {
        if(OFICINA != null){
            if(!OFICINA.getNombre().isEmpty()){
                return OFICINA.getNombre();
            }
        }
        return "Ninguna";
    }
    
    public static String getLblNombreOficina() {
        if(OFICINA != null){
            if(!OFICINA.getNombre().isEmpty()){
                return "Asignada a este equipo: "+OFICINA.getNombre();
            }
        }
        return "Asignada a este equipo: Ninguna";
    }

    static int getTipoUsuario() {
        if(USER != null){
            return USER.getTipo();
        }
        return 0;
    }
    
    /**
     * Retorna true si es Jefe administrativo o Sistema
     * @return 
     */
    public static boolean tipoUserSuperAdmin(){
        int tipoUsuario = getTipoUsuario();
        if(tipoUsuario == 1 || tipoUsuario == 7){
            return true;
        }
        return false;
    }
    
    /**
     * Retorna true si es Jefe administrativo, Administrador o Sistema
     * @return 
     */
    public static boolean tipoUserAdmin(){
        int tipoUsuario = getTipoUsuario();
        if(tipoUsuario == 1 || tipoUsuario == 2 || tipoUsuario == 7){
            return true;
        }
        return false;
    }
    
    /**
     * Retorna true si es Jefe administrativo, Administrador, inventario o Sistema
     * @return 
     */
    public static boolean tipoUserIventario(){
        int tipoUsuario = getTipoUsuario();
        if(tipoUsuario == 1 || tipoUsuario == 2 || tipoUsuario == 4 || tipoUsuario == 7){
            return true;
        }
        return false;
    }
    
    public static void setSessionUser(User user){
        USER = user;
    }
    
    public static User getSessionUser(){
        return USER;
    }

    public static void clearFicha() {
        stFicha =  new Ficha();
    }

    public static String getOficinaWeb() {
        if(OFICINA != null){
            if(!OFICINA.getWeb().isEmpty()){
                return OFICINA.getWeb();
            }
        }
        return "Sin dirección web";
    }

    public static String getOficinaAddress() {
        if(OFICINA != null){
            if(!OFICINA.getDireccion().isEmpty()){
                return OFICINA.getDireccion();
            }
        }
        return "no registrada";
    }

    public static String getOficinaCity() {
       if(OFICINA != null){
            if(!OFICINA.getCiudad().isEmpty()){
                return OFICINA.getCiudad();
            }
        }
        return "no registrada";
    }

    public static String getOficinaMail() {
        if(OFICINA != null){
            if(!OFICINA.getEmail().isEmpty()){
                return OFICINA.getEmail();
            }
        }
        return "no registrado";
    }

    public static String getOficinaPhone1() {
        if(OFICINA != null){
            if(!OFICINA.getTelefono1().isEmpty()){
                return OFICINA.getTelefono1();
            }
        }
        return "no registrado";
    }

    public static String getOficinaPhone2() {
        if(OFICINA != null){
            if(!OFICINA.getTelefono2().isEmpty()){
                return OFICINA.getTelefono2();
            }
        }
        return "no registrado";
    }
}
