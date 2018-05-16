/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author home
 */
public class RegistroBaja {
    private int id;
    private Date fecha;
    private String idLente;
    private int cantidad;
    private String obs;

    public RegistroBaja() {
    }

    public RegistroBaja(int id, Date fecha, String idLente, int cantidad, String obs) {
        this.id = id;
        this.fecha = fecha;
        this.idLente = idLente;
        this.cantidad = cantidad;
        this.obs = obs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setIdLente(String idLente) {
        this.idLente = idLente;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getIdLente() {
        return idLente;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getObs() {
        return obs;
    }
    
    
}
