/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sync;

import fn.GlobalValues;
import fn.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import sync.entities.Global;

/**
 *
 * @author sdx
 */
public class Cmp {
    private static String className = "sync.Cmp";
    public static Global global(){
        return new Global();
    }
    
    public static void isOnline(){
        Log.setLog(className,Log.getReg());
        String comando = "ping www.softdirex.cl";//ping -c 1 google.com
        try{
            GlobalValues.IS_ONLINE = (Runtime.getRuntime().exec (comando).waitFor() == 0);
        }catch(IOException | InterruptedException e){
            GlobalValues.IS_ONLINE = false;
        }
    }
    
    public static String getPublicIp() throws MalformedURLException, IOException{
        URL whatismyip = new URL("http://checkip.amazonaws.com");
        String ip = "0.0.0.0";
        BufferedReader in = new BufferedReader(new InputStreamReader(
                        whatismyip.openStream()));
        ip = in.readLine(); 
        System.out.println("My Public ip is = "+ip);
        in.close();
        return ip;
    }
}
