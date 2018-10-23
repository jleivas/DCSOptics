/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.abstractclasses;

/**
 *
 * @author jlleivas
 */
public abstract class SyncIntIdValidaName extends SyncIntId{
    private String nombre;
    
    public void setNombre(String nombre) {
        this.nombre = getStr(nombre);
    }
    
    public String getNombre() {
        return getStr(nombre);
    }
}
