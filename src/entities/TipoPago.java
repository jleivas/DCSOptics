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
public class TipoPago {
    
    private int id;
    private String nombre;
    private int estado;

    public TipoPago() {
    }

    public TipoPago(int id, String nombre, int estado) {
        setId(id);
        setNombre(nombre);
        setEstado(estado);
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getEstado() {
        return estado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "tipoPago{" + "id=" + id + ", nombre=" + nombre + '}';
    }
    
    
    
}
