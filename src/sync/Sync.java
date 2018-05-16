/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sync;

import entities.Lente;
import fn.GlobalValues;
import fn.OptionPane;
import java.sql.SQLException;

/**
 *
 * @author sdx
 */
public class Sync {
    public static boolean add(SyncBd localData, SyncBd remoteData, SyncBd globalData, Object object, String objectId) throws SQLException, ClassNotFoundException{        
        System.out.println("Sync::add(SyncBd localData, SyncBd remoteData, SyncBd globalData, Object object)");    
        if(sync.Cmp.isOnline()){
            if(!remoteData.add(object))
                OptionPane.showWarningMessage("No se pudo conectar a Base de datos remota", "Error de conexión remota");
        }
        if(!localData.add(object)){
            OptionPane.showWarningMessage("No se pudo conectar a Base de datos local", "Error de conexión interna");
            return false;
        }
        if(!addGlobal(globalData,localData.get(objectId),object)){
            OptionPane.showErrorMessage("No se pudo agregar el nuevo elemento, porfavor reinicie el sistema.", "Error interno del sistema"); 
        }
        return true;
        }

    
    private static boolean addGlobal(SyncBd globalData,Object oldObject, Object newObject){
        System.out.println("Sync::addGlobal(SyncBd globalData, Object object)");
        int index = -1;
        if(newObject == null)
            return false;
        try{
            if(newObject instanceof Lente){
                if(oldObject == null){
                    GlobalValues.TMP_LIST_LENTES.add((Lente)newObject);
                }else{
                    index = GlobalValues.TMP_LIST_LENTES.indexOf(oldObject);
                    if(index >= 0){
                        GlobalValues.TMP_LIST_LENTES.add(index, (Lente)newObject);
                        GlobalValues.TMP_LIST_LENTES.remove((Lente)oldObject);
                    }else{
                        GlobalValues.TMP_LIST_LENTES.add((Lente)newObject);
                    }
                }   
            }
            return true;
        }catch(Exception e){
            return false;
        }
            
    }
    
}
