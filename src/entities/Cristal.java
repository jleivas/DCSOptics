/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.abstractclasses.SyncIntIdValidaName;
import java.util.Date;

/**
 *
 * @author home
 */
public class Cristal extends SyncIntIdValidaName{
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
        super.setNombre(getStr(nombre).toUpperCase());
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getPrecio() {
        return precio;
    }
    
    @Override
    public String toString() {
        return "\nID: "+getId()+
                " - Nombre: "+getNombre()+
                " - Precio: "+this.precio+
                " - LAST_UPDATE:"+fn.date.Cmp.dateToString(getLastUpdate(), "dd-mm-yyyy")+
                " - Estado:"+getEstado();
    }
    
}
