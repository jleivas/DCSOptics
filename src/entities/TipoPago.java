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
public class TipoPago extends SyncIntIdValidaName{

    public TipoPago() {
    }

    public TipoPago(int id, String nombre, int estado, Date lastUpdate, int lastHour) {
        setId(id);
        setNombre(nombre);
        setEstado(estado);
        setLastUpdate(lastUpdate);
        setLastHour(lastHour);
    }
    
    public void setNombre(String nombre) {
        super.setNombre(getToName(nombre));
    }

    public String getNombre() {
        return getToName(super.getNombre());
    }

    @Override
    public String toString() {
        return "tipoPago{" + "id=" + getId()+ ", nombre=" + getNombre() + '}';
    }
}
