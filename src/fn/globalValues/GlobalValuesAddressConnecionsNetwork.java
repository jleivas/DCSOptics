/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn.globalValues;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

/**
 *
 * @author jlleivas
 */
public class GlobalValuesAddressConnecionsNetwork {
    public static String getEqId() throws UnknownHostException{
        return thisPcAddress()+"/"+thisPcName();
    }
    
    private static String thisPcAddress() throws UnknownHostException{
        InetAddress localHost = InetAddress.getLocalHost();
        return localHost.getHostAddress();
    }
    
    private static String thisPcName() throws UnknownHostException{
        InetAddress localHost = InetAddress.getLocalHost();
        return localHost.getHostName();
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
