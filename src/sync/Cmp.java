/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sync;

import java.net.Socket;

/**
 *
 * @author sdx
 */
public class Cmp {
    public static boolean isOnline(){
        try{
            if(new Socket("www.softdirex.cl", 80).isConnected()){
              return true;
            }
        }catch(Exception e){
            return false;
        }
        return false;
    }
}
