/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.abstractclasses;

import fn.GlobalValues;
import java.util.Date;

/**
 *
 * @author sdx
 */
public abstract class SyncStringId {
    private String cod;
    private int estado;
    private Date lastUpdate;
    private int lastHour;

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getCod() {
        return cod;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        if(lastUpdate == null)
            this.lastUpdate = new Date();
        else
            this.lastUpdate = lastUpdate;
    }

    public int getLastHour() {
        return lastHour;
    }

    public void setLastHour(int hour) {
        if(hour < 1){
            if(lastUpdate != null)
                this.lastHour = GlobalValues.hourToInt(lastUpdate);
            else{
                this.lastHour = 0;
            }
        }else{
            this.lastHour = hour;
        }
    }
}
