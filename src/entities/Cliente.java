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
public class Cliente extends SyncStringId{
    private String nombre;
    private String telefono1;
    private String telefono2;
    private String email;
    private String direccion;
    private String comuna;
    private String ciudad;
    private int sexo;
    private Date nacimiento;

    public Cliente() {
    }

    public Cliente(String rut, String nombre, 
            String telefono1, String telefono2, 
            String email, String direccion, String comuna, 
            String ciudad, int sexo, Date nacimiento, 
            int estado, Date lastUpdate, int lastHour) {
        setCod(rut);
        setNombre(nombre);
        setTelefono1(telefono1);
        setTelefono2(telefono2);
        setEmail(email);
        setDireccion(direccion);
        setComuna(comuna);
        setCiudad(ciudad);
        setSexo(sexo);
        setNacimiento(nacimiento);
        setEstado(estado);
        setLastUpdate(lastUpdate);
        setLastHour(lastHour);
    }

    public void setNombre(String nombre) {
        this.nombre = getToName(nombre);
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

    public void setDireccion(String direccion) {
        this.direccion = getToName(direccion);
    }

    public void setComuna(String comuna) {
        this.comuna = getToName(comuna);
    }

    public void setCiudad(String ciudad) {
        this.ciudad = getToName(ciudad);
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getNombre() {
        return getToName(nombre);
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

    public String getDireccion() {
        return getToName(direccion);
    }

    public String getComuna() {
        return getToName(comuna);
    }

    public String getCiudad() {
        return getToName(ciudad);
    }

    public int getSexo() {
        return sexo;
    }

    public Date getNacimiento() {
        return nacimiento;
    }

    @Override
    public String toString() {
        return "Cliente{" + "rut=" + getCod()+"\n" 
                + ", nombre=" + nombre+"\n"
                + ", telefonos=" + telefono1+" / "+telefono2+"\n" 
                + ", email=" + email+"\n"
                + ", direccion="+ direccion+"\n"
                + ", comuna="+ comuna +"\n"
                + ", ciudad=" + ciudad+"\n" 
                + ", sexo=" + sexo+"\n" 
                + ", fecha nac.=" + nacimiento+"\n" 
                + ", estado=" + getEstado()+"\n" 
                + ", lastUpdate=" + getLastUpdate() + '}';
    }
    
    
    
    
    
    
}
