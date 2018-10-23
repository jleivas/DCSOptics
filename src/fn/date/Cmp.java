/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn.date;

import fn.Log;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author sdx
 */
public class Cmp {
    private static String className="date.Cmp";
    /**
     * Compara las fechas de las ultimas actualizaciones para 
     * realizar la sincronizacion con base de datos remota siempre que 
     * localIsNew(Date local,Date remote) sea verdadero
     * @param local Tipo Date lastUpdate de objeto local
     * @param remote Tipo Date lastUpdate de objeto remoto
     * @return true si el objeto local es mas reciente
     */
    public static boolean localIsNewOrEqual(Date local,Date remote){
        Log.setLog(className,Log.getReg());
        if(local == null || remote == null){
            return false;
        }
        return local.compareTo(remote) >= 0;
    }
    
    public static boolean objectIsNew(Date dateObject, int hourObject,Date dateBd, int hourBd){
        Log.setLog(className,Log.getReg());
        int compare = dateObject.compareTo(dateBd);
        if(compare > 0){
            return true;
        }else if(compare == 0){
            if(hourObject > hourBd){
                return true;
            }
        }
        return false;
    }
    
    public static String dateToString(Object date,String strOrder){
        Log.setLog(className,Log.getReg());
        strOrder = (strOrder==null)?"dd/mm/yyyy":strOrder;
        String firstSeparator = (strOrder.toLowerCase().contains("de"))?"de":null;
        String lastSeparator = (strOrder.toLowerCase().contains("del"))?"del":null;
        if(firstSeparator!=null){
            strOrder = strOrder.toLowerCase().replaceAll(" ", "")
                                         .replaceAll(lastSeparator, "/")
                                         .replaceAll(firstSeparator, "/");
        }
        DateFormat fmt = new SimpleDateFormat(strOrder.replaceAll("m","M"));
        String strDate = "date-error";
        if(date instanceof Date)
            strDate = fmt.format((Date)date);
        if(date instanceof java.sql.Date)
            strDate = fmt.format((java.sql.Date)date);
        if(firstSeparator!=null){
            strDate = strDate.replaceFirst("/", " "+firstSeparator+" ").replace("/", " "+lastSeparator+" ");
        }
        return strDate;
    }
    
    public static int hourToInt(Date date){
        DateFormat hourFormat = new SimpleDateFormat("HHmmss");
        String formatHour = hourFormat.format(date);
        int hora = Integer.parseInt(formatHour);
        return hora;
    }
    public static String DateToStrHour(Date date){
        DateFormat hourFormat = new SimpleDateFormat("HH:mm");
        return hourFormat.format(date);
    }
}
