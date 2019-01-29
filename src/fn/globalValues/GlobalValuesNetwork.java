/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn.globalValues;

import fn.Log;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author jlleivas
 */
public class GlobalValuesNetwork {
    private static String className = "GlobalValuesNetwork";
    
    private static boolean IS_ONLINE = true;
    
    public static void checkIfOnline(){
        Log.setLog(className,Log.getReg());
        boolean estado; 
        try { 

            URL ruta=new URL("http://www.google.com"); 
            URLConnection rutaC=ruta.openConnection(); 
            rutaC.connect(); 
            estado=true; 
           }catch(Exception e){ 

            estado=false; 
        }
        
        setIsOnline(estado);
    }
    
    public static void setIsOnline(boolean value){
        IS_ONLINE = value;
    }
    
    public static boolean isOnline(){
        return IS_ONLINE;
    }
}
