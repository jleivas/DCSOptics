/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author Lenovo G470
 */
public class Despacho {
    
    private int id;
    private String rut;
    private String nombre;
    private Date fecha;
    private int idFicha;

    public Despacho() {
    }

    public Despacho(int id, String rut, String nombre, Date fecha, int idFicha) {
        this.id = id;
        this.rut = rut;
        this.nombre = nombre;
        this.fecha = fecha;
        this.idFicha = idFicha;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setIdFicha(int idFicha) {
        this.idFicha = idFicha;
    }

    public int getId() {
        return id;
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

    public int getIdFicha() {
        return idFicha;
    }

    @Override
    public String toString() {
        return "Despacho{" + "id=" + id 
                + ", rut=" + rut 
                + ", nombre=" + nombre 
                + ", fecha=" + fecha 
                + ", idFicha=" + idFicha + '}';
    }
    
    
    
}
