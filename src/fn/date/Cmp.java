/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn.date;

import java.util.Date;

/**
 *
 * @author sdx
 */
public class Cmp {
    /**
     * Compara las fechas de las ultimas actualizaciones para 
     * realizar la sincronizacion con base de datos remota siempre que 
     * localIsNew(Date local,Date remote) sea verdadero
     * @param local Tipo Date lastUpdate de objeto local
     * @param remote Tipo Date lastUpdate de objeto remoto
     * @return true si el objeto local es mas reciente
     */
    public static boolean localIsNew(Date local,Date remote){
         return local.after(remote);//local es mas nuevo
    }
}
