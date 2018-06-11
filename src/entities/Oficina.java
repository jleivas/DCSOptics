/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.abstractclasses.SyncIntId;
import java.util.Date;

/**
 *
 * @author sdx
 */
public class Oficina extends SyncIntId{
    private String nombre;
    private String direccion;
    private String ciudad;
    private String telefono1;
    private String telefono2;
    private String email;
    private String web;

    public Oficina() {
    }

    public Oficina(int id, String nombre, String direccion, String ciudad, String telefono1,
            String telefono2, String email, String web, int estado, Date lastUpdate, int lastHour) {
        setId(id);
        this.nombre = nombre;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.telefono1 = telefono1;
        this.telefono2 = telefono2;
        this.email = email;
        this.web = web;
        setEstado(estado);
        setLastUpdate(lastUpdate);
        setLastHour(lastHour);
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setWeb(String web) {
        this.web = web;
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

    public String getTelefono1() {
        return telefono1;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public String getEmail() {
        return email;
    }

    public String getWeb() {
        return web;
    }

    @Override
    public String toString() {
        return "{ID: "+getId()+",\n NOMBRE: "+getNombre()+",\n DIRECCION: "+getDireccion()+",\n"
                + " CIUDAD: "+getCiudad()+",\n TELEFONO 1: "+getTelefono1()+",\n TELEFONO 2: "+getTelefono2()+",\n"
                + " EMAIL: "+getEmail()+",\n WEB: "+getWeb()+",\n ESTADO: "+getEstado()+",\n LAST UPDATE: "+getLastUpdate()+"}";
    }
    
    
}
