/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.ficha;

import entities.abstractclasses.SyncStringId;
import java.util.Date;

/**
 *
 * @author Lenovo G470
 */
public class Despacho extends SyncStringId{
    
    private String rut;
    private String nombre;
    private Date fecha;
    private String idFicha;

    public Despacho() {
    }

    public Despacho(String cod, String rut, String nombre, Date fecha, 
            String idFicha, int estado, Date lastUpdate, int lastHour) {
        setCod(cod);
        this.rut = rut;
        this.nombre = nombre;
        this.fecha = fecha;
        this.idFicha = idFicha;
        setEstado(estado);
        setLastUpdate(lastUpdate);
        setLastHour(lastHour);
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setIdFicha(String idFicha) {
        this.idFicha = idFicha;
    }

    public String getRut() {
        return rut;
    }

    public String getNombre() {
        return nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getIdFicha() {
        return idFicha;
    }

    @Override
    public String toString() {
        return "Despacho{" + "cod=" + getCod()
                + ", rut=" + rut 
                + ", nombre=" + nombre 
                + ", fecha=" + fecha 
                + ", idFicha=" + idFicha + '}';
    }
}
