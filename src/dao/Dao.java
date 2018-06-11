/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this objectlate file, choose Tools | Templates
 * and open the objectlate in the editor.
 */
package dao;

import entities.Cliente;
import entities.Cristal;
import entities.Descuento;
import entities.Doctor;
import entities.Institucion;
import entities.Lente;
import entities.Oficina;
import entities.RegistroBaja;
import entities.TipoPago;
import entities.abstractclasses.SyncStringId;
import entities.User;
import entities.abstractclasses.SyncIntId;
import entities.ficha.Armazon;
import entities.ficha.Despacho;
import entities.ficha.Ficha;
import entities.ficha.HistorialPago;
import fn.GlobalValues;
import fn.Log;
import fn.OptionPane;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author sdx
 */
public class Dao{
    private static String className="Dao";
    /**
     * Sólo crea datos, si ya existen no los puede agregar.
     * Útil solo para registros independientes de la base de datos remota
     * @param object
     * @return 
     */
    public boolean create (SyncStringId object){
        Log.setLog(className,Log.getReg());
        return false;
    }
   /**
    * Agrega registros a la base de datos, si ya existen los actualiza, útil para sincronización de bases de datos.
    * 
    * Comprueba si existe antes de agregar, en User comprueba si ya existe un username igual,
    * en cristal, descuento y oficina compara si ya existe un nombre igual (en estos casos no busca por id).
    * @param object
    * @return
    * @throws InstantiationException
    * @throws IllegalAccessException 
    */
    public boolean add(Object object) throws InstantiationException, IllegalAccessException {
        Log.setLog(className,Log.getReg());
        if(object instanceof SyncStringId)
            ((SyncStringId)object).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
        if(object instanceof SyncIntId)
            ((SyncIntId)object).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
        if(GlobalValues.isOnline()){
            if(object instanceof SyncIntId)//se pueden agregar solo si tienen conexion a internet
                ((SyncIntId)object).setId(GlobalValues.REMOTE_SYNC.getMaxId(object));
            if(GlobalValues.REMOTE_SYNC.exist(object)){
                if(object instanceof SyncIntId){
                    OptionPane.showMsg("No se puede crear nuevo registro", "El nombre ya se encuentra utilizado,\n"
                        + "Para poder ingresar un nuevo registro debes cambiar el nombre.", JOptionPane.WARNING_MESSAGE);
                }else{
                    return update(object);
                }
            }else{
                try {
                    return sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, object);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else{
            if(object instanceof SyncStringId){
                if(!GlobalValues.LOCAL_SYNC.exist(object)){
                    return update(object);
                }else{
                    try {
                        return sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, object);
                    } catch (SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }else{
                OptionPane.showMsg("No se puede crear nuevo registro", "Para poder ingresar un nuevo registro debes tener acceso a internet.", JOptionPane.WARNING_MESSAGE);
            }
        }
        return false;
    }

    public boolean update(Object object) {
        Log.setLog(className,Log.getReg());
        if(object instanceof SyncStringId)
            ((SyncStringId)object).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
        if(object instanceof SyncIntId)
            ((SyncIntId)object).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
        try {
            return sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, object);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    
    public boolean delete(String cod,int id, Object type) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Log.setLog(className,Log.getReg());
        Object temp =  null;
        if(GlobalValues.isOnline()){
            temp =  GlobalValues.REMOTE_SYNC.getElement(cod,id, type);
            if(temp != null){//valida si ya existe el desname
                if(temp instanceof SyncStringId){
                    ((SyncStringId)temp).setEstado(0);
                    ((SyncStringId)temp).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
                } 
                if(temp instanceof SyncIntId){
                    ((SyncIntId)temp).setEstado(0);
                    ((SyncIntId)temp).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
                }
                try {
                    return sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, temp);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                OptionPane.showMsg("No se puede eliminar registro", "El registro no existe.", JOptionPane.WARNING_MESSAGE);
            }
        }else{
            temp =  GlobalValues.LOCAL_SYNC.getElement(cod,id,type);
            if(temp != null){//valida si ya existe el desname
                if(temp instanceof SyncStringId){
                    ((SyncStringId)temp).setEstado(0);
                    ((SyncStringId)temp).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
                } 
                if(temp instanceof SyncIntId){
                    ((SyncIntId)temp).setEstado(0);
                    ((SyncIntId)temp).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
                }
                try {
                    return sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, temp);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                OptionPane.showMsg("No se puede eliminar registro", "El registro no existe.", JOptionPane.WARNING_MESSAGE);
            }
        }
        return false;
    }

    public boolean restore(String cod,int id,Object type) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Log.setLog(className,Log.getReg());
        Object temp =  null;
        if(GlobalValues.isOnline()){
            temp =  GlobalValues.REMOTE_SYNC.getElement(cod,id,type);
            if(temp != null){//valida si ya existe el desname
                if(temp instanceof SyncStringId){
                    ((SyncStringId)temp).setEstado(0);
                    ((SyncStringId)temp).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
                } 
                if(temp instanceof SyncIntId){
                    ((SyncIntId)temp).setEstado(0);
                    ((SyncIntId)temp).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
                }
                try {
                    return sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, temp);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                OptionPane.showMsg("No se puede eliminar registro", "El registro no existe.", JOptionPane.WARNING_MESSAGE);
            }
        }else{
            temp =  GlobalValues.LOCAL_SYNC.getElement(cod,id,type);
            if(temp != null){//valida si ya existe el desname
                if(temp instanceof SyncStringId){
                    ((SyncStringId)temp).setEstado(0);
                    ((SyncStringId)temp).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
                } 
                if(temp instanceof SyncIntId){
                    ((SyncIntId)temp).setEstado(0);
                    ((SyncIntId)temp).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
                }
                try {
                    return sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, temp);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                OptionPane.showMsg("No se puede eliminar registro", "El registro no existe.", JOptionPane.WARNING_MESSAGE);
            }
        }
        return false;
    }

    public Object get(String cod,int id, Object type) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Log.setLog(className,Log.getReg());
        return GlobalValues.LOCAL_SYNC.getElement(cod,id,type);
    }

    public void sincronize(Object type) {
        Log.setLog(className,Log.getReg());
        try {
            if(GlobalValues.isOnline()){
                /*Cliente*/
                if(type instanceof Cliente){
                    for (Object object : GlobalValues.REMOTE_SYNC.listar(GlobalValues.LAST_UPDATE,new Cliente())) {
                        sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, (Cliente)object);
                    }
                    for (Object object : GlobalValues.LOCAL_SYNC.listar(GlobalValues.LAST_UPDATE,new Cliente())) {
                        sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, (Cliente)object);
                    }
                }
                /*Cristal*/
                if(type instanceof Cristal){
                    for (Object object : GlobalValues.REMOTE_SYNC.listar(GlobalValues.LAST_UPDATE,new Cristal())) {
                        sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, (Cristal)object);
                    }
                    for (Object object : GlobalValues.LOCAL_SYNC.listar(GlobalValues.LAST_UPDATE,new Cristal())) {
                        sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, (Cristal)object);
                    }
                }
                /*Descuento*/
                if(type instanceof Descuento){
                    for (Object object : GlobalValues.REMOTE_SYNC.listar(GlobalValues.LAST_UPDATE,new Descuento())) {
                        sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, (Descuento)object);
                    }
                    for (Object object : GlobalValues.LOCAL_SYNC.listar(GlobalValues.LAST_UPDATE,new Descuento())) {
                        sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, (Descuento)object);
                    }
                }
                /*Doctor*/
                if(type instanceof Doctor){
                    for (Object object : GlobalValues.REMOTE_SYNC.listar(GlobalValues.LAST_UPDATE,new Doctor())) {
                        sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, (Doctor)object);
                    }
                    for (Object object : GlobalValues.LOCAL_SYNC.listar(GlobalValues.LAST_UPDATE,new Doctor())) {
                        sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, (Doctor)object);
                    }
                }
                /*Institucion*/
                if(type instanceof Institucion){
                    for (Object object : GlobalValues.REMOTE_SYNC.listar(GlobalValues.LAST_UPDATE,type)) {
                        sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, (Institucion)object);
                    }
                    for (Object object : GlobalValues.LOCAL_SYNC.listar(GlobalValues.LAST_UPDATE,type)) {
                        sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, (Institucion)object);
                    }
                }
                /*Lente*/
                if(type instanceof Lente){
                    for (Object object : GlobalValues.REMOTE_SYNC.listar(GlobalValues.LAST_UPDATE,type)) {
                        sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, (Lente)object);
                    }
                    for (Object object : GlobalValues.LOCAL_SYNC.listar(GlobalValues.LAST_UPDATE,type)) {
                        sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, (Lente)object);
                    }
                }
                /*Oficina*/
                if(type instanceof Oficina){
                    for (Object object : GlobalValues.REMOTE_SYNC.listar(GlobalValues.LAST_UPDATE,new Oficina())) {
                        sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, (Oficina)object);
                    }
                    for (Object object : GlobalValues.LOCAL_SYNC.listar(GlobalValues.LAST_UPDATE,new Oficina())) {
                        sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, (Oficina)object);
                    } 
                }
                /*RegistroBaja*/
                if(type instanceof RegistroBaja){
                    for (Object object : GlobalValues.REMOTE_SYNC.listar(GlobalValues.LAST_UPDATE,type)) {
                        sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, (RegistroBaja)object);
                    }
                    for (Object object : GlobalValues.LOCAL_SYNC.listar(GlobalValues.LAST_UPDATE,type)) {
                        sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, (RegistroBaja)object);
                    }
                }
                /*TipoPago*/
                if(type instanceof TipoPago){
                    for (Object object : GlobalValues.REMOTE_SYNC.listar(GlobalValues.LAST_UPDATE,type)) {
                        sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, (TipoPago)object);
                    }
                    for (Object object : GlobalValues.LOCAL_SYNC.listar(GlobalValues.LAST_UPDATE,type)) {
                        sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, (TipoPago)object);
                    }
                }
                /*Usuario*/
                if(type instanceof User){
                    for (Object object : GlobalValues.REMOTE_SYNC.listar(GlobalValues.LAST_UPDATE,new User())) {
                        sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, (User)object);
                    }
                    for (Object object : GlobalValues.LOCAL_SYNC.listar(GlobalValues.LAST_UPDATE,new User())) {
                        sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, (User)object);
                    }
                }
                /*  ENTIDADES RELACIONADAS CON LAS FICHAS   */
                /*Armazon*/
                if(type instanceof Armazon){
                    for (Object object : GlobalValues.REMOTE_SYNC.listar(GlobalValues.LAST_UPDATE,type)) {
                        sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, (Armazon)object);
                    }
                    for (Object object : GlobalValues.LOCAL_SYNC.listar(GlobalValues.LAST_UPDATE,type)) {
                        sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, (Armazon)object);
                    }
                }
                /*Despacho*/
                if(type instanceof Despacho){
                    for (Object object : GlobalValues.REMOTE_SYNC.listar(GlobalValues.LAST_UPDATE,type)) {
                        sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, (Despacho)object);
                    }
                    for (Object object : GlobalValues.LOCAL_SYNC.listar(GlobalValues.LAST_UPDATE,type)) {
                        sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, (Despacho)object);
                    }
                }
                /*HistorialPago*/
                if(type instanceof HistorialPago){
                    for (Object object : GlobalValues.REMOTE_SYNC.listar(GlobalValues.LAST_UPDATE,type)) {
                        sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, (HistorialPago)object);
                    }
                    for (Object object : GlobalValues.LOCAL_SYNC.listar(GlobalValues.LAST_UPDATE,type)) {
                        sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, (HistorialPago)object);
                    }
                }
                if(type instanceof Ficha){
                    sincronizeFicha();
                }
            }else{
                for (Object object : GlobalValues.LOCAL_SYNC.listar("-2",new User())) {//falta opcion en listar
                    sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, (User)object);
                }
                for (Object object : GlobalValues.LOCAL_SYNC.listar("-2",new Cliente())) {//falta opcion en listar
                    sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, (Cliente)object);
                }
                for (Object object : GlobalValues.LOCAL_SYNC.listar("-2",new Cristal())) {//falta opcion en listar
                    sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, (Cristal)object);
                }
                for (Object object : GlobalValues.LOCAL_SYNC.listar("-2",new Descuento())) {//falta opcion en listar
                    sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, (Descuento)object);
                }
                for (Object object : GlobalValues.LOCAL_SYNC.listar("-2",new Doctor())) {//falta opcion en listar
                    sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, (Doctor)object);
                }
                for (Object object : GlobalValues.LOCAL_SYNC.listar("-2",new Oficina())) {//falta opcion en listar
                    sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, (Oficina)object);
                }
            }  
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//    public void sincronize() {
//        Log.setLog(className,Log.getReg());
//        System.out.println(Log.getLog());
//        try {
//            if(GlobalValues.isOnline()){
//                /*Usuario*/
//                for (Object object : GlobalValues.REMOTE_SYNC.listar(GlobalValues.LAST_UPDATE,new User())) {
//                    sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, (User)object);
//                }
//                for (Object object : GlobalValues.LOCAL_SYNC.listar(GlobalValues.LAST_UPDATE,new User())) {
//                    sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, (User)object);
//                }
//                /*Cliente*/
//                for (Object object : GlobalValues.REMOTE_SYNC.listar(GlobalValues.LAST_UPDATE,new Cliente())) {
//                    sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, (Cliente)object);
//                }
//                for (Object object : GlobalValues.LOCAL_SYNC.listar(GlobalValues.LAST_UPDATE,new Cliente())) {
//                    sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, (Cliente)object);
//                }
//                /*Cristal*/
//                for (Object object : GlobalValues.REMOTE_SYNC.listar(GlobalValues.LAST_UPDATE,new Cristal())) {
//                    sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, (Cristal)object);
//                }
//                for (Object object : GlobalValues.LOCAL_SYNC.listar(GlobalValues.LAST_UPDATE,new Cristal())) {
//                    sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, (Cristal)object);
//                }
//                /*Descuento*/
//                for (Object object : GlobalValues.REMOTE_SYNC.listar(GlobalValues.LAST_UPDATE,new Descuento())) {
//                    sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, (Descuento)object);
//                }
//                for (Object object : GlobalValues.LOCAL_SYNC.listar(GlobalValues.LAST_UPDATE,new Descuento())) {
//                    sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, (Descuento)object);
//                }
//                /*Doctor*/
//                for (Object object : GlobalValues.REMOTE_SYNC.listar(GlobalValues.LAST_UPDATE,new Doctor())) {
//                    sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, (Doctor)object);
//                }
//                for (Object object : GlobalValues.LOCAL_SYNC.listar(GlobalValues.LAST_UPDATE,new Doctor())) {
//                    sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, (Doctor)object);
//                }
//                /*Oficina*/
//                for (Object object : GlobalValues.REMOTE_SYNC.listar(GlobalValues.LAST_UPDATE,new Oficina())) {
//                    sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, (Oficina)object);
//                }
//                for (Object object : GlobalValues.LOCAL_SYNC.listar(GlobalValues.LAST_UPDATE,new Oficina())) {
//                    sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, (Oficina)object);
//                } 
//            }else{
//                for (Object object : GlobalValues.LOCAL_SYNC.listar("-2",new User())) {//falta opcion en listar
//                    sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, (User)object);
//                }
//                for (Object object : GlobalValues.LOCAL_SYNC.listar("-2",new Cliente())) {//falta opcion en listar
//                    sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, (Cliente)object);
//                }
//                for (Object object : GlobalValues.LOCAL_SYNC.listar("-2",new Cristal())) {//falta opcion en listar
//                    sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, (Cristal)object);
//                }
//                for (Object object : GlobalValues.LOCAL_SYNC.listar("-2",new Descuento())) {//falta opcion en listar
//                    sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, (Descuento)object);
//                }
//                for (Object object : GlobalValues.LOCAL_SYNC.listar("-2",new Doctor())) {//falta opcion en listar
//                    sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, (Doctor)object);
//                }
//                for (Object object : GlobalValues.LOCAL_SYNC.listar("-2",new Oficina())) {//falta opcion en listar
//                    sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, (Oficina)object);
//                }
//            }  
//        } catch (SQLException | ClassNotFoundException ex) {
//            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    private void sincronizeFicha() throws SQLException, ClassNotFoundException {
        sincronize(new Armazon());
        sincronize(new Despacho());
        sincronize(new HistorialPago());
        for (Object object : GlobalValues.REMOTE_SYNC.listar(GlobalValues.LAST_UPDATE,new Ficha())) {
            sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, (Ficha)object);
        }
        for (Object object : GlobalValues.LOCAL_SYNC.listar(GlobalValues.LAST_UPDATE,new Ficha())) {
            sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, (Ficha)object);
        }
    }
}
