/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn.globalValues;

import fn.GV;
import view.init.SplashProgress;

/**
 *
 * @author jlleivas
 */
public class GlobalValuesSyncReportStatus {
    private static int PORC = 0;
    private static int SUMA_PORC = 0;
    private static int LIMIT = 0;
    private static int INI = 0;
    private static int FIN = 0;
    private static String REPORT;
    private static int ANT =0;
    private static SplashProgress sp = null;
    
    public static void resetPorc(){
        PORC = 0;
        SUMA_PORC = 0;
        sp = null;
    }
    
    public static void resetAll(){
        PORC = 0;
        SUMA_PORC = 0;
        LIMIT = 0;
        sp = null;
    }
    
    public static void setPorc(int value){
        PORC = (PORC + value)/2;
    }
    
    public static void calcularPorcentaje(int limit, String text){
        INI = PORC;
        LIMIT = limit;
        PORC = ((SUMA_PORC * 100)/LIMIT);
        SUMA_PORC++;
        if(PORC > 100){
            PORC = 100;
        }
        FIN = ((SUMA_PORC)*100)/LIMIT;
        if(sp == null){
            sp =  new SplashProgress();
            sp.setVisible(true);
        }
    }
    
    public static void calcularSubPorcentaje(int subLimite){
        int temp = (FIN-INI)/subLimite;
        PORC = PORC + temp;
        PORC = (PORC>FIN)?FIN:PORC;
        PORC = (PORC>100)?100:PORC;
    }
    
    public static int getPorc(){
        return PORC;
    }
    
    public static String getReport(){
        return GV.getStr(REPORT);
    }
    
    public static void setReport(String report){
        REPORT = GV.getStr(report);
    }
}
