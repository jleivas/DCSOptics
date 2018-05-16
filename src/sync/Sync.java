/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sync;

import entities.Lente;
import entities.User;
import fn.GlobalValues;
import java.sql.SQLException;
import java.util.ArrayList;
import sync.lente.GbVlLente;
import sync.lente.LcBdLente;
import sync.lente.RmBdLente;

/**
 *
 * @author sdx
 */
public class Sync {
    public static boolean add(SyncBd localData, SyncBd remoteData, SyncBd globalData, Object object) throws SQLException, ClassNotFoundException{
        if(object instanceof Lente){
            if(sync.Cmp.isOnline()){
                if(remoteData.add(object)){
                    if(localData.add(object)){
                        globalData.add(object);
                        return true;
                    }
                }else{
                    Object temp = remoteData.get(((Lente)object).getCodigo());
                    if(temp != null){
                        if(localData.add(temp)){
                            globalData.addIndex(object, temp);
                            return true;
                        }
                    }
                }
            }else{
                if(localData.add(object)){
                    globalData.add(object);
                    return true;
                }  
            }
        }
        return false;
    }

    
}
