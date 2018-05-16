/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Lenovo G470
 */
public class Doctor {
    
    private String rut;
    private String nombre;
    private String telefono;
    private String email;
    private int estado;

    public Doctor() {
    }

    public Doctor(String rut, String nombre, String telefono, String email,int estado) {
        setRut(rut);
        setNombre(nombre);
        setTelefono(telefono);
        setEmail(email);
        setEstado(estado);
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getEstado() {
        return estado;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRut() {
        return rut;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Doctor{" + "rut=" + rut+"\n" 
                + ", nombre=" + nombre+"\n" 
                + ", telefono=" + telefono+"\n" 
                + ", email=" + email + '}';
    }
    
    
    
    
    
    
    
}
