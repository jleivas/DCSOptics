/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.abstractclasses.SyncIntId;
import java.util.Date;

/**
 *
 * @author home
 */
public class Cristal extends SyncIntId{
    private String nombre;
    private int precio;

    public Cristal() {
    }

    public Cristal(int id, String nombre, int precio, int estado, Date lastUpdate, int lastHour) {
        setId(id);
        setNombre(nombre);
        setPrecio(precio);
        setEstado(estado);
        setLastUpdate(lastUpdate);
        setLastHour(lastHour);
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrecio() {
        return precio;
    }
    
    @Override
    public String toString() {
        return "\nID: "+getId()+
                " - Nombre: "+this.nombre+
                " - Precio: "+this.precio+
                " - LAST_UPDATE:"+fn.date.Cmp.dateToString(getLastUpdate(), "dd-mm-yyyy")+
                " - Estado:"+getEstado();
    }
    
}
