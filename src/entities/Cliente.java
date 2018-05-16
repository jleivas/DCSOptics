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
public class Cliente {
    
    private String rut;
    private String nombre;
    private String telefono;
    private String email;
    private String direccion;
    private String comuna;
    private String ciudad;
    private int sexo;
    private int edad;
    private int estado;

    public Cliente() {
    }

    public Cliente(String rut, String nombre, String telefono, String email, String direccion, String comuna, String ciudad, int sexo, int edad, int estado) {
        setRut(rut);
        setNombre(nombre);
        setTelefono(telefono);
        setEmail(email);
        setDireccion(direccion);
        setComuna(comuna);
        setCiudad(ciudad);
        setSexo(sexo);
        setEdad(edad);
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

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public void setEdad(int edad) {
        this.edad = edad;
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

    public String getDireccion() {
        return direccion;
    }

    public String getComuna() {
        return comuna;
    }

    public String getCiudad() {
        return ciudad;
    }

    public int getSexo() {
        return sexo;
    }

    public int getEdad() {
        return edad;
    }

    @Override
    public String toString() {
        return "Cliente{" + "rut=" + rut+"\n" 
                + ", nombre=" + nombre+"\n"
                + ", telefono=" + telefono+"\n" 
                + ", email=" + email+"\n"
                + ", direccion="+ direccion+"\n"
                + ", comuna="+ comuna +"\n"
                + ", ciudad=" + ciudad+"\n" 
                + ", sexo=" + sexo+"\n" 
                + ", edad=" + edad + '}';
    }
    
    
    
    
    
    
}
