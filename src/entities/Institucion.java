/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.abstractclasses.SyncStringId;
import java.util.Date;

/**
 *
 * @author Lenovo G470
 */
public class Institucion extends SyncStringId{
    
    private String nombre;
    private String telefono;
    private String email;
    private String web;
    private String direccion;
    private String comuna;
    private String ciudad;

    public Institucion() {
    }

    public Institucion(String rut, String nombre, String telefono, String email, String web, String direccion, String comuna, String ciudad, int estado, Date lastUpdate, int lastHour) {
        setCod(rut);
        setNombre(nombre);
        setTelefono(telefono);
        setEmail(email);
        setWeb(web);
        setDireccion(direccion);
        setComuna(comuna);
        setCiudad(ciudad);
        setEstado(estado);
        setLastUpdate(lastUpdate);
        setLastHour(lastHour);
    }

    public void setNombre(String nombre) {
        this.nombre = getToName(nombre);
    }

    public void setTelefono(String telefono) {
        this.telefono = getStr(telefono);
    }

    public void setEmail(String email) {
        this.email = mailValidate(email);
    }
    
    public void setWeb(String web){
        this.web = getStr(web).toLowerCase();
    }
    
    public void setDireccion(String direccion) {
        this.direccion = getToName(direccion);
    }

    public void setComuna(String comuna) {
        this.comuna = getToName(comuna);
    }

    public void setCiudad(String ciudad) {
        this.ciudad = getToName(ciudad);
    }

    public String getNombre() {
        return getToName(nombre);
    }

    public String getTelefono() {
        return getStr(telefono);
    }

    public String getEmail() {
        return getStr(email);
    }
    
    public String getWeb(){
        return getStr(web);
    }
    public String getDireccion() {
        return getToName(direccion);
    }

    public String getComuna() {
        return getToName(comuna);
    }

    public String getCiudad() {
        return getToName(ciudad);
    }

    @Override
    public String toString() {
        return "Institucion{" + "id=" + getCod()+ ", nombre=" + nombre + ", telefono=" + telefono + ", email=" + email + ", direccion=" + direccion + ", comuna=" + comuna + ", ciudad=" + ciudad + ", estado=" + getEstado() + '}';
    }
}
