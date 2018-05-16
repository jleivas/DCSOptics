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
public class Descuento {
    
    private int id;
    private String nombre;
    private String descripcion;
    private int porcetange;
    private int estado;
    
    public Descuento (){}

    public Descuento(int id, String nombre, String descripcion, int porcetange, int estado) {
        setId(id);
        setNombre(nombre);
        setDescripcion(descripcion);
        setPorcetange(porcetange);
        setEstado(estado);
    }
    
    

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
       
        this.nombre = nombre;
        
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPorcetange(int porcetange) {
        this.porcetange = porcetange;
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

    public String getDescripcion() {
        return descripcion;
    }

    public int getPorcetange() {
        return porcetange;
    }

    public int getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return "Descuento{" + "id=" + id 
                + ", nombre=" + nombre+"\n"
                + ", descripcion="+"\n" 
                + descripcion + ", porcetange="+"\n" 
                + porcetange + ", estado="+"\n" 
                + estado + '}';
    }
    
    
    
    
    
    
    
}
