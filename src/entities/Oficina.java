/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.abstractclasses.SyncIntIdValidaName;
import java.util.Date;

/**
 *
 * @author sdx
 */
public class Oficina extends SyncIntIdValidaName{
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
        setNombre(nombre);
        setDireccion(direccion);
        setCiudad(ciudad);
        setTelefono1(telefono1);
        setTelefono2(telefono2);
        setEmail(email);
        setWeb(web);
        setEstado(estado);
        setLastUpdate(lastUpdate);
        setLastHour(lastHour);
    }

    public void setNombre(String nombre) {
        super.setNombre(getToName(nombre));
    }

    public void setDireccion(String direccion) {
        this.direccion = getToName(direccion);
    }

    public void setCiudad(String ciudad) {
        this.ciudad = getToName(ciudad);
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = getStr(telefono1);
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = getStr(telefono2);
    }

    public void setEmail(String email) {
        this.email = mailValidate(email);
    }

    public void setWeb(String web) {
        this.web = getStr(web).toLowerCase();
    }

    public String getNombre() {
        return getToName(super.getNombre());
    }

    public String getDireccion() {
        return getToName(direccion);
    }

    public String getCiudad() {
        return getToName(ciudad);
    }

    public String getTelefono1() {
        return getStr(telefono1);
    }

    public String getTelefono2() {
        return getStr(telefono2);
    }

    public String getEmail() {
        return getStr(email);
    }

    public String getWeb() {
        return getStr(web);
    }

    @Override
    public String toString() {
        return "{ID: "+getId()+",\n NOMBRE: "+getNombre()+",\n DIRECCION: "+getDireccion()+",\n"
                + " CIUDAD: "+getCiudad()+",\n TELEFONO 1: "+getTelefono1()+",\n TELEFONO 2: "+getTelefono2()+",\n"
                + " EMAIL: "+getEmail()+",\n WEB: "+getWeb()+",\n ESTADO: "+getEstado()+",\n LAST UPDATE: "+getLastUpdate()+"}";
    }
}
