/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author home
 */
public class Info {
    private int id;
    private String nombre;
    private String direccion;
    private String ciudad;
    private String telefono;
    private String celular;
    private String mail;
    private String web;
    private int folioActual;
    private String adminMail;
    private String pass;
    
    

    public Info(int id, String nombre, String direccion, String ciudad, String telefono, String celular, String mail, String web, int folioActual, String adminMail, String pass) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.telefono = telefono;
        this.celular = celular;
        this.mail = mail;
        this.web = web;
        setFolioActual(folioActual);
        setAdminMail(adminMail);
        setPass(pass);
    }
    
    public String getAdminMail() {
        return adminMail;
    }

//    public Info(int id, String nombre, String direccion, String ciudad, String telefono, String celular, String mail, String web) {
//        this.id = id;
//        this.nombre = nombre;
//        this.direccion = direccion;
//        this.ciudad = ciudad;
//        this.telefono = telefono;
//        this.celular = celular;
//        this.mail = mail;
//        this.web = web;
//    }
    public String getPass() {
        return pass;
    }

    public void setAdminMail(String adminMail) {
        this.adminMail = adminMail;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setFolioActual(int folioActual) {
        this.folioActual = folioActual;
    }

    public int getFolioActual() {
        return folioActual;
    }

    public Info() {
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCelular() {
        return celular;
    }

    public String getMail() {
        return mail;
    }

    public String getWeb() {
        return web;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        if(nombre.length() < 25){
           this.nombre = nombre; 
        }else{
            this.nombre = "Sin datos";
        }
        
    }

    public void setDireccion(String direccion) {
        if(direccion.length() < 45){
           this.direccion = direccion; 
        }else{
            this.direccion = "Sin datos";
        }
    }

    public void setCiudad(String ciudad) {
        if(ciudad.length() < 25){
           this.ciudad = ciudad; 
        }else{
            this.ciudad = "Sin datos";
        }
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setWeb(String web) {
        this.web = web;
    }
    
    
    
}
