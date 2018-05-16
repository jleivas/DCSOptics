/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author home
 */
public class User {
    private int id;
    private String nombre;
    private String pass;
    private int tipo;
    private int estado;

    public User() {
    }

    public User(int id, String nombre, String pass, int tipo, int estado) {
        this.id = id;
        this.nombre = nombre;
        this.pass = pass;
        this.tipo = tipo;
        this.estado = estado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
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

    public String getPass() {
        return pass;
    }

    public int getTipo() {
        return tipo;
    }

    public int getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return "\nID: "+this.id+
                " - Nombre: "+this.nombre+
                " - PASS: "+this.pass+
                " - TIPO:"+this.tipo+
                " - Estado:"+this.estado; //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
