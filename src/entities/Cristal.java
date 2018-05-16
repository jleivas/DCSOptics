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
public class Cristal {
    private int id;
    private String nombre;
    private int precio;
    private int estado;

    public Cristal() {
    }

    public Cristal(int id, String nombre, int precio, int estado) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.estado = estado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
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

    public int getPrecio() {
        return precio;
    }

    public int getEstado() {
        return estado;
    }
    
    
}
