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
        return local.compareTo(remote) >= 0;
    }
    
    public static String dateToString(Object date,String strOrder){
        Log.setLog(className,Log.getReg());
        strOrder = strOrder.toLowerCase();
        DateFormat fmt = new SimpleDateFormat(strOrder.replaceAll("m","M"));
        if(date instanceof Date)
            return fmt.format((Date)date);
        if(date instanceof java.sql.Date)
            return fmt.format((java.sql.Date)date);
        return "date-error";
    }
}
