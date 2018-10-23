/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn.globalValues;

import fn.Log;
import java.io.IOException;

/**
 *
 * @author jlleivas
 */
public class GlobalValuesNetwork {
    private static String className = "GlobalValuesNetwork";
    
    private static boolean IS_ONLINE = true;
    
    public static void checkIfOnline(){
        Log.setLog(className,Log.getReg());
        String comando = "ping www.softdirex.cl";//ping -c 1 google.com
        boolean value = false;
        try{
            value = (Runtime.getRuntime().exec (comando).waitFor() == 0);
        }catch(IOException | InterruptedException e){
            value = false;
        }
        setIsOnline(value);
    }
    
    public static void setIsOnline(boolean value){
        IS_ONLINE = value;
    }
    
    public static boolean isOnline(){
        return IS_ONLINE;
    }
}
