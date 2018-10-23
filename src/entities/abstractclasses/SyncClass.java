/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.abstractclasses;

import fn.date.Cmp;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author sdx
 */
public abstract class SyncClass {
    private int estado;
    private Date lastUpdate;
    private int lastHour;
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
                this.lastHour = Cmp.hourToInt(lastUpdate);
            else{
                this.lastHour = 0;
            }
        }else{
            this.lastHour = hour;
        }
    }
    
    public String getToName(String param){
        if(getStr(param).isEmpty()){
            return "";
        }
        String[] str = getStr(param).toLowerCase().split(" ");
        StringBuffer value = new StringBuffer();
        for (String temp : str) {
            if(temp.length() > 1){
                value.append(Character.toUpperCase(temp.charAt(0))).append(temp.substring(1)).append(" ");
            }else{
                value.append(temp.toUpperCase()).append(" ");
            }
        }
        return value.toString().trim();
    }
    
    public String getStr(String arg){
        if(arg == null || arg.replaceAll(" ", "").isEmpty())
            return "";
        else{
            return arg.trim();
        }
    }
    
    public String mailValidate(String email){
        email = getStr(email).toLowerCase();
        // Patrón para validar el email
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
 
        // El email a validar
 
        Matcher mather = pattern.matcher(email);
        if(mather.find()){
            return email;
        }
        return "";
    }
}
