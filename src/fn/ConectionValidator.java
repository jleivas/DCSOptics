/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn;

import java.net.Socket;

/**
 *
 * @author jorge
 */
public class ConectionValidator {
    public static boolean isConnected(){
        System.out.println("CONECTIONVALIDATOR::isConnected()");
        String dirWeb = "www.softdirex.cl";
        int puerto = 80;
        try{
            Socket s = new Socket(dirWeb, puerto);
            if(s.isConnected()){
              System.out.println("Conexión establecida con la dirección: " +  dirWeb + " a travéz del puerto: " + puerto);
            }
        }catch(Exception e){
            System.err.println("No se pudo establecer conexión con: " + dirWeb + " a travez del puerto: " + puerto);
            return false;
        }
        return true;
    }
}
