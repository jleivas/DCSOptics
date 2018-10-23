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
public class Doctor extends SyncStringId{
    
    private String nombre;
    private String telefono;
    private String email;

    public Doctor() {
    }

    public Doctor(String rut, String nombre, String telefono, String email,int estado, Date lastUpdate, int lastHour) {
        setCod(rut);
        setNombre(nombre);
        setTelefono(telefono);
        setEmail(email);
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

    public String getNombre() {
        return getToName(nombre);
    }

    public String getTelefono() {
        return getStr(telefono);
    }

    public String getEmail() {
        return getStr(email);
    }

    @Override
    public String toString() {
        return "Doctor{" + "rut=" + getCod()+"\n" 
                + ", nombre=" + nombre+"\n" 
                + ", telefono=" + telefono+"\n" 
                + ", email=" + email+"\n" 
                + ", estado=" + getEstado()+"\n" 
                + ", lastUpdate=" + getLastUpdate() + '}';
    } 
}
