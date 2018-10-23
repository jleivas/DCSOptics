/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.abstractclasses.SyncFichaClass;
import java.util.Date;

/**
 *
 * @author home
 */
public class RegistroBaja extends SyncFichaClass{
    private Date fecha;
    private String idLente;
    private int cantidad;
    private String obs;

    public RegistroBaja() {
    }

    public RegistroBaja(String cod, Date fecha, String idLente, int cantidad,
            String obs, int estado, Date lastUpdate, int lastHour) {
        setCod(cod);
        setFecha(fecha);
        setIdLente(idLente);
        setCantidad(cantidad);
        setObs(obs);
        setEstado(estado);
        setLastUpdate(lastUpdate);
        setLastHour(lastHour);
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setIdLente(String idLente) {
        this.idLente = getStr(idLente);
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setObs(String obs) {
        this.obs = getStr(obs);
    }

    public Date getFecha() {
        return fecha;
    }

    public String getIdLente() {
        return getStr(idLente);
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getObs() {
        return getStr(obs);
    }
}
