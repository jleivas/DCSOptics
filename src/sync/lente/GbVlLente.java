/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sync.lente;

import entities.Lente;
import fn.GlobalValues;
import sync.SyncBd;

/**
 *
 * @author sdx
 */
public class GbVlLente implements SyncBd{
//    public static boolean add(Lente lente) throws SQLException, ClassNotFoundException{
//        if(sync.Cmp.isOnline()){
//            if(RmBdLente.add(lente)){
//                if(LcBdLente.add(lente)){
//                    GlobalValues.TMP_LIST_LENTES.
//                    return true;
//                }
//            }else{
//                lente = RmBdLente.get(lente.getCodigo());
//                if(lente != null){
//                    if(LcBdLente.add(lente)){
//                        GlobalValues.TMP_LIST_LENTES.add(lente);
//                        return true;
//                    }
//                }
//            }
//        }else{
//            if(LcBdLente.add(lente)){
//                GlobalValues.TMP_LIST_LENTES.add(lente);
//                return true;
//            }  
//        }
//    return false;
//    }

    @Override
    public boolean add(Object object) {
        GlobalValues.TMP_LIST_LENTES.add((Lente)object);
        return true;
    }

    @Override
    public Object get(String objectId) {
        System.out.println(""+objectId);
        return null;
    }

    @Override
    public Object get(int objectId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addIndex(Object oldObject, Object newObject) {
        int index = GlobalValues.TMP_LIST_LENTES.indexOf((Lente)oldObject);
        if(index >= 0){
            GlobalValues.TMP_LIST_LENTES.add(index, (Lente)newObject);
            GlobalValues.TMP_LIST_LENTES.remove((Lente)oldObject);
        }else{
            GlobalValues.TMP_LIST_LENTES.add((Lente)newObject);
        }
        return true;
    }
}
