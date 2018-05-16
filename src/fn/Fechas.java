/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author home
 */
public class Fechas {
    public String imprimirFechaActual() {
        Calendar f = new GregorianCalendar();
        String dia = Integer.toString(f.get(Calendar.DATE));
        String mes = Integer.toString(f.get(Calendar.MONTH)+1);
        String anio = Integer.toString(f.get(Calendar.YEAR));
        if(f.get(Calendar.DATE)<10){
                dia="0".concat(dia);
        }
        if(f.get(Calendar.MONTH)+1 < 10){
                mes="0".concat(mes);
        }
        return dia+"-"+mes+"-"+anio;
    }
}
