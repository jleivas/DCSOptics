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
 * @author Lenovo G470
 */
public class Descuento extends SyncIntIdValidaName{
    
    private String descripcion;
    private int porcetange;
    private int monto;
    
    public Descuento (){}

    public Descuento(int id, String nombre, String descripcion, int porcetange, int monto, int estado, Date lastUpdate, int lastHour) {
        setId(id);
        setNombre(nombre);
        setDescripcion(descripcion);
        setPorcetange(porcetange);
        setMonto(monto);
        setEstado(estado);
        setLastUpdate(lastUpdate);
        setLastHour(lastHour);
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public void setNombre(String nombre) {
       
        super.setNombre(getToName(nombre));
        
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = getStr(descripcion);
    }

    public void setPorcetange(int porcetange) {
        this.porcetange = porcetange;
    }

    public String getNombre() {
        return getToName(super.getNombre());
    }

    public String getDescripcion() {
        return getStr(descripcion);
    }

    public int getPorcetange() {
        return porcetange;
    }
    
    public boolean isNumeric(){
        if(monto == 0)
            return false;
        else
            return true;
    }
    
    @Override
    public String toString() {
        return "Descuento{" + "id=" + getId()
                + ", nombre=" + getNombre() +"\n"
                + ", descripcion="+ descripcion + "\n" 
                +", porcetange="+ porcetange +"\n" 
                + ", monto="+ monto +"\n"
                + ", estado="+ getEstado() +"\n"
                + ", lastUdate="+ getLastUpdate() + '}';
    }
    
    
    
    
    
    
    
}
