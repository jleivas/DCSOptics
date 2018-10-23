/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sync;

import fn.GV;
import fn.Log;
import java.sql.SQLException;

/**
 *
 * @author sdx
 */
public class Sync {
    private static String className="Sync";
    /**
     * Add or update new objct in static variables, local data base and remote data base at the sincronize.
     * @param localData
     * @param remoteData
     * @param object
     * @return true if insert in static variables and local data base or remote data base, false if don't insert in static variables
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public static boolean addLocalSync(InterfaceSync localData, InterfaceSync remoteData, Object object) throws SQLException, ClassNotFoundException{        
        Log.setLog(className,Log.getReg());
        return localData.add(object);
    }
    
    public static boolean addRemoteSync(InterfaceSync localData, InterfaceSync remoteData, Object object) throws SQLException, ClassNotFoundException{        
        if(GV.isOnline()){
            remoteData.add(object);
            return true;
        }
        return false;
    }
    
    public static boolean add(InterfaceSync localData, InterfaceSync remoteData, Object object) throws SQLException, ClassNotFoundException{        
        Log.setLog(className,Log.getReg());
        localData.add(object);
        return true;
    }
    /**
     * Add or update new object in static variables, local data base and remote data base.
     * @param localData
     * @param remoteData
     * @param object
     * @return true if insert in static variables and local data base or remote data base, false if don't insert in static variables
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
//    public static boolean add(InterfaceSyncFicha localData, InterfaceSyncFicha remoteData, Object object) throws SQLException, ClassNotFoundException{        
//        Log.setLog(className,Log.getReg());
//        localData.add(object);
//        if(GV.isOnline()){
//            remoteData.add(object);
//        }
//        return true;
//    }

    
}
