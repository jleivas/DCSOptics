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
 * @author sdx
 */
public class Equipo extends SyncIntId{
    private String nombre;
    private String licencia;
    private String bd;
    private String bdUser;
    private String bdPass;
    private String bdUrl;

    public Equipo(int id,String nombre, String licencia,String bd,String bdUser,String bdPass,String bdUrl, int estado, Date lastUpdate, int lastHour) {
        setId(id);
        setNombre(nombre);
        setLicencia(licencia);
        setBd(bd);
        setBdUser(bdUser);
        setBdPass(bdPass);
        setBdUrl(bdUrl);
        setEstado(estado);
        setLastUpdate(lastUpdate);
        setLastHour(lastHour);
    }

    public Equipo(){
        
    }
    public void setNombre(String nombre) {
        this.nombre = getToName(nombre);
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public String getNombre() {
        return getToName(nombre);
    }

    public String getLicencia() {
        return licencia;
    }

    public void setBd(String bd) {
        this.bd = bd;
    }

    public void setBdUser(String bdUser) {
        this.bdUser = bdUser;
    }

    public void setBdPass(String bdPass) {
        this.bdPass = bdPass;
    }

    public String getBd() {
        return bd;
    }

    public String getBdUser() {
        return bdUser;
    }

    public String getBdPass() {
        return bdPass;
    }

    public String getBdUrl() {
        return bdUrl;
    }

    public void setBdUrl(String bdUrl) {
        this.bdUrl = bdUrl;
    }
}
