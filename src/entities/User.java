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
public class User extends SyncIntId{
    private String nombre;
    private String username;
    private String email;
    private String pass;
    private int tipo;

    public User() {
    }

    public User(int id, String nombre, String username,String email,
            String pass, int tipo, int estado, Date lastUpdate, int lastHour) {
        setId(id);
        setNombre(nombre);
        setUsername(username);
        setEmail(email);
        setPass(pass);
        setTipo(tipo);
        setEstado(estado);
        setLastUpdate(lastUpdate);
        setLastHour(lastHour);
    }

    public void setEmail(String email) {
        this.email = mailValidate(email);
    }

    public String getEmail() {
        return getStr(email);
    }

    public void setUsername(String username) {
        this.username = getStr(username);
    }

    public String getUsername() {
        return getStr(username);
    }

    public void setNombre(String nombre) {
        this.nombre = getToName(nombre);
    }

    public void setPass(String pass) {
        this.pass = getStr(pass);
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return getToName(nombre);
    }

    public String getPass() {
        return getStr(pass);
    }

    public int getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return "\nID: "+getId()+
                " - Nombre: "+this.nombre+
                " - USERNAME: "+this.username+
                " - EMAIL: "+this.email+
                " - PASS: "+this.pass+
                " - TIPO:"+this.tipo+
                " - LAST_UPDATE:"+fn.date.Cmp.dateToString(getLastUpdate(), "dd-mm-yyyy")+
                " - Estado:"+getEstado();
    }
}
