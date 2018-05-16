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
public class Institucion {
    
    private int id;
    private String nombre;
    private String telefono;
    private String email;
    private String direccion;
    private String comuna;
    private String ciudad;
    private int estado;

    public Institucion() {
    }

    public Institucion(int id, String nombre, String telefono, String email, String direccion, String comuna, String ciudad, int estado) {
        setId(id);
        setNombre(nombre);
        setTelefono(telefono);
        setEmail(email);
        setDireccion(direccion);
        setComuna(comuna);
        setCiudad(ciudad);
        setEstado(estado);
    }

    public void setId(int id) {
        this.id = id;
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

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    

    public int getId() {
        return id;
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

    public int getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return "Institucion{" + "id=" + id + ", nombre=" + nombre + ", telefono=" + telefono + ", email=" + email + ", direccion=" + direccion + ", comuna=" + comuna + ", ciudad=" + ciudad + ", estado=" + estado + '}';
    }
    
    
    
    
}
