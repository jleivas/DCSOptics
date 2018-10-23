/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this objectlate file, choose Tools | Templates
 * and open the objectlate in the editor.
 */
package dao;

import entities.Cliente;
import entities.Convenio;
import entities.Cristal;
import entities.Descuento;
import entities.Doctor;
import entities.Institucion;
import entities.InternMail;
import entities.Inventario;
import entities.Lente;
import entities.Oficina;
import entities.RegistroBaja;
import entities.TipoPago;
import entities.User;
import entities.abstractclasses.SyncStringId;
import entities.abstractclasses.SyncClass;
import entities.abstractclasses.SyncFichaClass;
import entities.abstractclasses.SyncIntId;
import entities.abstractclasses.SyncIntIdValidaName;
import entities.ficha.EtiquetFicha;
import entities.ficha.Ficha;
import entities.ficha.HistorialPago;
import fn.GV;
import fn.Log;
import fn.OptionPane;
import fn.SubProcess;
import fn.date.Cmp;
import static fn.date.Cmp.localIsNewOrEqual;
import fn.globalValues.GlobalValuesVariables;
import fn.mail.Send;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import sync.entities.LocalInventario;

/**
 *
 * @author sdx
 */
public class Dao{
    private static String className="Dao";

    private Send mailSend = new Send();
    /**
     * Sólo crea datos, si ya existen no los puede agregar.
     * Útil solo para registros independientes de la base de datos remota
     * @param object
     * @return 
     */
    public boolean sendMessage (InternMail msg) throws InstantiationException, IllegalAccessException{
        Log.setLog(className,Log.getReg());
        msg.setEstado(1);
        String mail = msg.getDestinatario().getEmail();
        if(mail != null || !mail.isEmpty()){
            mailSend.sendMessageMail(msg.getAsunto(), mail);
        }
        return add(msg);
    }
    
    public boolean addOnInit(Object object) throws InstantiationException, IllegalAccessException {
        Log.setLog(className,Log.getReg());
        if(object == null){
            return false;//ultima modificacion sin verificar en todos los casos de uso
        }
        if(object instanceof SyncClass){
            ((SyncClass)object).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
            ((SyncClass)object).setLastHour(Cmp.hourToInt(new Date()));//solo se actualizan lastuodates para crear objetos
        }
        if(object instanceof SyncFichaClass){
            if(!(object instanceof Ficha)){
                ((SyncFichaClass) object).setCod(getCurrentCod(object));
            }
        }
        if(object instanceof SyncIntId)//se pueden agregar solo si tienen conexion a internet
            ((SyncIntId)object).setId(GV.LOCAL_SYNC.getMaxId(object));

        if(GV.LOCAL_SYNC.exist(object)){
            if(object instanceof SyncIntId){
                if(!GV.isCurrentDate(((SyncIntId)object).getLastUpdate())){
                    OptionPane.showMsg("No se puede crear nuevo registro", "El nombre ya se encuentra utilizado,\n"
                        + "Para poder ingresar un nuevo registro debes cambiar el nombre.", 2);
                }
            }else{
                return update(object);
            }
        }else{
            try {
                return sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, object);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
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
        if(object == null){
            return false;//ultima modificacion sin verificar en todos los casos de uso
        }
        if(object instanceof SyncClass){
            ((SyncClass)object).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
            ((SyncClass)object).setLastHour(Cmp.hourToInt(new Date()));//solo se actualizan lastuodates para crear objetos
        }
        if(object instanceof SyncFichaClass){
            if(!(object instanceof Ficha)){
                ((SyncFichaClass) object).setCod(getCurrentCod(object));
            }
        }
        if(GV.isOnline()){
            if(object instanceof SyncIntId)//se pueden agregar solo si tienen conexion a internet
                if(object instanceof User){
                    if(((User)object).getId() == 1 || ((User)object).getId() == 2){
                        ((SyncIntId)object).setId(((SyncIntId)object).getId());
                    }else{
                        ((SyncIntId)object).setId(GV.REMOTE_SYNC.getMaxId(object));
                    }
                }else{
                    ((SyncIntId)object).setId(GV.REMOTE_SYNC.getMaxId(object));
                }
                
            if(GV.REMOTE_SYNC.exist(object)){
                if(object instanceof SyncIntId){
                    if(!GV.isCurrentDate(((SyncIntId)object).getLastUpdate())){
                        OptionPane.showMsg("No se puede crear nuevo registro", "El nombre ya se encuentra utilizado,\n"
                            + "Para poder ingresar un nuevo registro debes cambiar el nombre.", 2);
                    }
                }else{
                    return update(object);
                }
            }else{
                try {
                    if(object instanceof InternMail){
                        return (sync.Sync.addLocalSync(GV.LOCAL_SYNC, GV.REMOTE_SYNC, object) &&
                                sync.Sync.addRemoteSync(GV.LOCAL_SYNC, GV.REMOTE_SYNC, object));
                    }else{
                        return sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, object);
                    }
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else{
            if(object instanceof SyncStringId){
                if(!GV.LOCAL_SYNC.exist(object)){
                    return update(object);
                }else{
                    try {
                        return sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, object);
                    } catch (SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }else{
                OptionPane.showMsg("No se puede crear nuevo registro", "Para poder ingresar un nuevo registro debes tener acceso a internet."+object.toString(), 2);
            }
        }
        return false;
    }

    public boolean update(Object object) {
        Log.setLog(className,Log.getReg());
        if(object instanceof SyncClass){
            ((SyncClass)object).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
            ((SyncClass)object).setLastHour(Cmp.hourToInt(new Date()));
        }
        try {
            return sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, object);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean updateFromUI(Object object){
        if(validaEntity(object)){
            if(GV.licenciaLocal()){
                OptionPane.showMsg("NO CREATED", "function ist created!", 3);
                return false;
            }else{
                updateRemote(object);
                return true;
            }
        }
        return false;
    }
    
    public boolean decreaseStock(String idLente, int cantidad)  {
        try{
            Lente temp = (Lente) get(idLente, 0, new Lente());
            int newStock = 0;
            if(temp != null){
                newStock = temp.getStock() - cantidad;
                if(newStock >= 0){
                    /**
                     * Se inserta un registro temporal con las cantidades a reducir
                     */
                    if(LocalInventario.insert(idLente,cantidad)){
                        return true;
                    }
                }else{
                    OptionPane.showMsg("No se pudo reducir el stock", "La cantidad a reducir es mayor que el stock disponible", 2);
                }
            }
            return false;
        }catch(IllegalAccessException | InstantiationException | SQLException | ClassNotFoundException ex){
            return false;
        }
    }
    
    public boolean restoreOrDeleteFromUI(Object object){
        if(!validaPrivilegiosParaEstados(object)){
            if(((SyncClass)object).getEstado() == 0){
                OptionPane.showMsg("No tienes privilegios suficientes", "No tienes permiso para restaurar el registro", 2);
                return false;
            }else{
                OptionPane.showMsg("No tienes privilegios suficientes", "No tienes permiso para eliminar el registro", 2);
                return false;
            }
        }
        if(object instanceof Inventario){
            if(((Inventario)object).getNombre().equals(GV.inventarioName())){
                OptionPane.showMsg("No se puede eliminar", "Este inventario se encuentra en uso, no se puede eliminar.",3);
                return false;
            }
        }
        if(object instanceof User){
            if(((User)object).getId() == GV.user().getId()){
                OptionPane.showMsg("No es posible realizar esta operación", "No puedes eliminar tu propio usuario.", 2);
                return false;
            }
        }
        if(GV.licenciaLocal()){
            System.out.println("no function");
            return false;
        }else{
            restoreOrDeleteRemote(object);
            return true;
        }
    }
    
    public boolean delete(String cod,int id, Object type) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Log.setLog(className,Log.getReg());
        Object temp =  null;
        if(GV.isOnline() && !(type instanceof Ficha)){
            temp =  GV.REMOTE_SYNC.getElement(cod,id, type);
            if(temp != null){//valida si ya existe el desname
                if(temp instanceof SyncStringId){
                    if(temp instanceof Ficha){
                        ((SyncStringId)temp).setEstado(((((SyncStringId)temp).getEstado())*-1));
                        ((SyncStringId)temp).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
                        ((SyncStringId)temp).setLastHour(Cmp.hourToInt(new Date()));
                    }else{
                        ((SyncStringId)temp).setEstado(0);
                        ((SyncStringId)temp).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
                        ((SyncStringId)temp).setLastHour(Cmp.hourToInt(new Date()));
                    }  
                } 
                if(temp instanceof SyncIntId){
                    ((SyncIntId)temp).setEstado(0);
                    ((SyncIntId)temp).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
                    ((SyncIntId)temp).setLastHour(Cmp.hourToInt(new Date()));
                }
                try {
                    return sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, temp);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                OptionPane.showMsg("No se puede eliminar registro", "El registro no existe.", 2);
            }
        }else{
            temp =  GV.LOCAL_SYNC.getElement(cod,id,type);
            if(temp != null){//valida si ya existe el desname
                if(temp instanceof SyncStringId){
                    if(temp instanceof Ficha){
                        ((SyncStringId)temp).setEstado(((((SyncStringId)temp).getEstado())*-1));
                        ((SyncStringId)temp).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
                        ((SyncStringId)temp).setLastHour(Cmp.hourToInt(new Date()));
                    }else{
                        ((SyncStringId)temp).setEstado(0);
                        ((SyncStringId)temp).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
                        ((SyncStringId)temp).setLastHour(Cmp.hourToInt(new Date()));
                    }  
                } 
                if(temp instanceof SyncIntId){
                    ((SyncIntId)temp).setEstado(0);
                    ((SyncIntId)temp).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
                    ((SyncIntId)temp).setLastHour(Cmp.hourToInt(new Date()));
                }
                try {
                    return sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, temp);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                OptionPane.showMsg("No se puede eliminar registro", "El registro no existe.", 2);
            }
        }
        return false;
    }

    public boolean restore(String cod,int id,Object type) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Log.setLog(className,Log.getReg());
        Object temp =  null;
        if(GV.isOnline() && !(type instanceof Ficha)){
            temp =  GV.REMOTE_SYNC.getElement(cod,id,type);
            if(temp != null){//valida si ya existe el desname
                if(temp instanceof SyncStringId){
                    ((SyncStringId)temp).setEstado(1);
                    ((SyncStringId)temp).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
                    ((SyncStringId)temp).setLastHour(Cmp.hourToInt(new Date()));
                } 
                if(temp instanceof SyncIntId){
                    ((SyncIntId)temp).setEstado(1);
                    ((SyncIntId)temp).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
                    ((SyncIntId)temp).setLastHour(Cmp.hourToInt(new Date()));
                }
                try {
                    return sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, temp);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                OptionPane.showMsg("No se puede eliminar registro", "El registro no existe o fué modificado\n"
                        + "sincronice los datos para solucionar este error.", 2);
            }
        }else{
            temp =  GV.LOCAL_SYNC.getElement(cod,id,type);
            if(temp != null){//valida si ya existe el desname
                if(temp instanceof SyncStringId){
                    if(type instanceof Ficha){
                        ((SyncStringId)temp).setEstado((((SyncStringId)temp).getEstado())*-1);
                        ((SyncStringId)temp).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
                        ((SyncStringId)temp).setLastHour(Cmp.hourToInt(new Date()));
                    }else{
                        ((SyncStringId)temp).setEstado(1);
                        ((SyncStringId)temp).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
                        ((SyncStringId)temp).setLastHour(Cmp.hourToInt(new Date()));
                    }
                } 
                if(temp instanceof SyncIntId){
                    ((SyncIntId)temp).setEstado(1);
                    ((SyncIntId)temp).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
                    ((SyncIntId)temp).setLastHour(Cmp.hourToInt(new Date()));
                }
                try {
                    return sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, temp);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                OptionPane.showMsg("No se puede eliminar registro", "El registro no existe.", 2);
            }
        }
        return false;
    }
    
    /**
     * Retorna un elemento de la base de datos local
     * @param cod
     * @param id
     * @param type
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException 
     */
    public Object get(String cod,int id, Object type) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Log.setLog(className,Log.getReg());
        if(type instanceof Lente){
            Inventario inv = (Inventario)get(GV.inventarioName(), 0, new Inventario());
            if(inv != null){
                GV.setInventarioSeleccionado(inv.getId());
            }
            Object lente =  LocalInventario.getLente(cod);
            GV.setInventarioSeleccionado(0);
            return lente;
        }
        return GV.LOCAL_SYNC.getElement(cod,id,type);
    }
    
    public Lente getLente(String idLente, String inventarioName){
        Inventario inv = new Inventario();
        try {
            inv = (Inventario)get(inventarioName, 0, new Inventario());
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        GV.setInventarioSeleccionado(inv.getId());
        Lente lente = LocalInventario.getLente(idLente);
        GV.setInventarioSeleccionado(0);
        return lente;
    }

    public static void sincronize(Object type) {
        Log.setLog(className,Log.getReg());
        boolean esLente = (type instanceof Lente);
        if(GV.isCurrentDate(GV.LAST_UPDATE)){//validar plan de licencia
            return;//solo hace una actualizacion por día.
        }
        try {
            if(GV.isOnline()){
                if(type instanceof Ficha){
                    type = new EtiquetFicha();
                }
                
                ArrayList<Object> lista1= GV.REMOTE_SYNC.listar(GV.LAST_UPDATE,type);
                int size1 = lista1.size();
                ArrayList<Object> lista2= GV.LOCAL_SYNC.listar(GV.LAST_UPDATE,type);
                
                int size2 = lista2.size();
                if(size1 > 0){
                    for (Object object : lista1) {
                        GV.porcentajeSubCalcular(size1+size2);
                        sync.Sync.addLocalSync(GV.LOCAL_SYNC, GV.REMOTE_SYNC, object);
                    }
                }
                if(size2 > 0){
                     for (Object object : lista2) {
                        System.out.println(""+object.getClass().getName());
                        if(esLente){
                            System.out.println("es lente");
                        }
                        GV.porcentajeSubCalcular(size1+size2);
                        sync.Sync.addRemoteSync(GV.LOCAL_SYNC, GV.REMOTE_SYNC, object);
                    }
                }
                if(esLente){
                    /**
                     * actualizar stock
                     */
                    //se obtiene una lista recien descargada procesada con los stocks actualizados
                    lista2 = LocalInventario.listarLentesForSync();
                    int tam1 = lista2.size();
                    for (Object object : lista2) {
                        GV.porcentajeSubCalcular(tam1);
                        sync.Sync.addRemoteSync(GV.LOCAL_SYNC, GV.REMOTE_SYNC, object);
                        sync.Sync.addLocalSync(GV.LOCAL_SYNC, GV.REMOTE_SYNC, object);
                    }
                    LocalInventario.deleteAllRegistry("-2");
                }
            } 
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Object> listar(String param, Object type){
        if(type instanceof Lente){
            try {
                return LocalInventario.listarLentes(param);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                return new ArrayList<>();
            }
        }
        return GV.LOCAL_SYNC.listar(param, type);
    }
    
    /**
     * 
     * @param remitente 0 si no se necesita
     * @param destinatario 0 si no se necesita
     * @param estado 0: todos, 1: no leidos, 2 leidos
     * @return 
     */
    public ArrayList<InternMail> mensajes(int remitente, int destinatario, int estado) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
        return GV.LOCAL_SYNC.mensajes(remitente, destinatario, estado);
    }
//    public void sincronize() {
//        Log.setLog(className,Log.getReg());
//        System.out.println(Log.getLog());
//        try {
//            if(GV.isOnline()){
//                /*Usuario*/
//                for (Object object : GV.REMOTE_SYNC.listar(GV.LAST_UPDATE,new User())) {
//                    sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (User)object);
//                }
//                for (Object object : GV.LOCAL_SYNC.listar(GV.LAST_UPDATE,new User())) {
//                    sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (User)object);
//                }
//                /*Cliente*/
//                for (Object object : GV.REMOTE_SYNC.listar(GV.LAST_UPDATE,new Cliente())) {
//                    sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Cliente)object);
//                }
//                for (Object object : GV.LOCAL_SYNC.listar(GV.LAST_UPDATE,new Cliente())) {
//                    sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Cliente)object);
//                }
//                /*Cristal*/
//                for (Object object : GV.REMOTE_SYNC.listar(GV.LAST_UPDATE,new Cristal())) {
//                    sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Cristal)object);
//                }
//                for (Object object : GV.LOCAL_SYNC.listar(GV.LAST_UPDATE,new Cristal())) {
//                    sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Cristal)object);
//                }
//                /*Descuento*/
//                for (Object object : GV.REMOTE_SYNC.listar(GV.LAST_UPDATE,new Descuento())) {
//                    sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Descuento)object);
//                }
//                for (Object object : GV.LOCAL_SYNC.listar(GV.LAST_UPDATE,new Descuento())) {
//                    sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Descuento)object);
//                }
//                /*Doctor*/
//                for (Object object : GV.REMOTE_SYNC.listar(GV.LAST_UPDATE,new Doctor())) {
//                    sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Doctor)object);
//                }
//                for (Object object : GV.LOCAL_SYNC.listar(GV.LAST_UPDATE,new Doctor())) {
//                    sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Doctor)object);
//                }
//                /*Oficina*/
//                for (Object object : GV.REMOTE_SYNC.listar(GV.LAST_UPDATE,new Oficina())) {
//                    sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Oficina)object);
//                }
//                for (Object object : GV.LOCAL_SYNC.listar(GV.LAST_UPDATE,new Oficina())) {
//                    sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Oficina)object);
//                } 
//            }else{
//                for (Object object : GV.LOCAL_SYNC.listar("-2",new User())) {//falta opcion en listar
//                    sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (User)object);
//                }
//                for (Object object : GV.LOCAL_SYNC.listar("-2",new Cliente())) {//falta opcion en listar
//                    sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Cliente)object);
//                }
//                for (Object object : GV.LOCAL_SYNC.listar("-2",new Cristal())) {//falta opcion en listar
//                    sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Cristal)object);
//                }
//                for (Object object : GV.LOCAL_SYNC.listar("-2",new Descuento())) {//falta opcion en listar
//                    sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Descuento)object);
//                }
//                for (Object object : GV.LOCAL_SYNC.listar("-2",new Doctor())) {//falta opcion en listar
//                    sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Doctor)object);
//                }
//                for (Object object : GV.LOCAL_SYNC.listar("-2",new Oficina())) {//falta opcion en listar
//                    sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Oficina)object);
//                }
//            }  
//        } catch (SQLException | ClassNotFoundException ex) {
//            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }


    /**
     * Retorna el id actual de las entidades Armazon, Despacho, Ficha, HistorialPago y RegistroBaja
     * @param type tipo de clase a consultar
     * @return 
     */
    public String getCurrentCod(Object type){
        Log.setLog(className,Log.getReg());
        if(type instanceof SyncFichaClass){
            return GV.LOCAL_SYNC.getMaxId(type)+"-"+GV.LOCAL_SYNC.getIdEquipo();
        }else{
            OptionPane.showMsg("Instancia de datos errónea", "El tipo de datos ingresado no es válido para obtener el identificador.", 3);
            return null;
        }
        
    }

    public void createFicha(Ficha ficha, HistorialPago hp) {
        try {
            SubProcess.suspendConnectionOnline();
            add(ficha.getCliente());
            add(ficha.getCerca());
            add(ficha.getLejos());
            add(ficha.getDespacho());
            add(ficha.getDoctor());
            add(ficha);
            if(ficha.getCerca() != null){
                if(!GV.getFicha().getCerca().getMarca().isEmpty()){
                    decreaseStock(ficha.getCerca().getMarca(), 1);
                }
            }
            if(ficha.getLejos() != null){
                if(!GV.getFicha().getLejos().getMarca().isEmpty()){
                    decreaseStock(ficha.getLejos().getMarca(), 1);
                }
            }
            if(hp != null){
                add(hp);
            }
            SubProcess.activateConnectionOnline();
            GV.sendMailFicha(ficha);
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean usernameYaExiste(String username) {
        if(GV.licenciaTipoPlan() == GlobalValuesVariables.licenciaTipoFree() ||
           GV.licenciaTipoPlan() == GlobalValuesVariables.licenciaTipoLocal()){
            return (GV.LOCAL_SYNC.getElement(username, 0, new User())!=null);
        }else{
            return (GV.REMOTE_SYNC.getElement(username, 0, new User())!=null);
        }
    }

    
    public boolean addFromUI(Object object){
        if(validaEntity(object)){
            if(GV.licenciaLocal()){
                addLocal(object);
            }else{
                addRemote(object);
            }
            return true;
        }
        return false;
    }
    
    private void addLocal(Object object) {
        if(object instanceof SyncIntId){
            ((SyncClass)object).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
            ((SyncClass)object).setLastHour(Cmp.hourToInt(new Date()));//solo se actualizan lastuodates para crear objetos
            if(object instanceof SyncIntIdValidaName){
                addSyncIntIdValidaNameLocal(object);
            }else{
                addSyncIntIdLocal(object);
            }
        }
        if(object instanceof SyncStringId){
            ((SyncClass)object).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
            ((SyncClass)object).setLastHour(Cmp.hourToInt(new Date()));//solo se actualizan lastuodates para crear objetos
            addSyncStringIdLocal(object);
        }
        OptionPane.showMsg("No se puede insertar registro", "La entidad enviada no tiene un formato válido\n"
                + "\n"
                + "Detalle: " + object.getClass().getName(), 2);
    }

    private void addRemote(Object object) {
        if(object instanceof SyncFichaClass){
            if(!(object instanceof Ficha)){
                ((SyncFichaClass) object).setCod(getCurrentCod(object));
            }
        }
        if(object instanceof SyncIntId){
            if(!GV.isOnline()){
                OptionPane.showMsg("No se puede crear nuevo registro", "Para poder ingresar un nuevo registro debes tener acceso a internet.", 2);
                return;
            }
            ((SyncClass)object).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
            ((SyncClass)object).setLastHour(Cmp.hourToInt(new Date()));//solo se actualizan lastuodates para crear objetos
            ((SyncIntId)object).setId(GV.LOCAL_SYNC.getMaxId(object));
            if(((SyncIntId)object).getId() < 0){
                OptionPane.showMsg("Error de conexión", "No se pudo obtener conexión desde la base de datos remota.", 2);
                return;
            }
            if(object instanceof SyncIntIdValidaName){
                addSyncIntIdValidaNameRemote(object);
                return;
            }else{
                addSyncIntIdRemote(object);
                return;
            }
        }
        if(object instanceof SyncStringId){
            ((SyncClass)object).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
            ((SyncClass)object).setLastHour(Cmp.hourToInt(new Date()));//solo se actualizan lastuodates para crear objetos
            addSyncStringIdRemote(object);
            return;
        }
        OptionPane.showMsg("No se puede insertar registro", "La entidad enviada no tiene un formato válido\n"
                + "\n"
                + "Detalle: " + object.getClass().getName(), 2);
    }

    private void addSyncIntIdValidaNameLocal(Object object) {
        SyncIntIdValidaName entity = (SyncIntIdValidaName) GV.LOCAL_SYNC.getElement(((SyncIntIdValidaName)object).getNombre(), 0, object);
        if(entity == null){
            if(GV.LOCAL_SYNC.add(object)){
                msgEntityAdded();
            }
            msgEntityNotAdded();
        }else{
            msgInvalidName(entity.getEstado());
        }
    }

    private void addSyncIntIdLocal(Object object) {
        GV.LOCAL_SYNC.add(object);
    }
    
    private void addSyncIntIdRemote(Object object) {
        if(object instanceof User){
            User entity = (User) GV.REMOTE_SYNC.getElement(((User)object).getUsername(), 0, object);
            if(entity == null){
                if(GV.REMOTE_SYNC.add(object)){
                    GV.LOCAL_SYNC.add(object);
                    msgEntityAdded();
                    return;
                }
                msgEntityNotAdded();
                return;
            }else{
                if(entity.getEstado() == 0){
                    OptionPane.showMsg("No se puede agregar el registro", "Ya existe un usuario con el username ingresado pero se encuentra anulado.", 2);
                    return;
                }else{
                    OptionPane.showMsg("No se puede agregar el registro", "Ya existe un usuario con el username ingresado.", 2);
                    return;
                }
            }
        }else{
            if(GV.REMOTE_SYNC.add(object)){
                GV.LOCAL_SYNC.add(object);
                msgEntityAdded();
                return;
            }
            msgEntityNotAdded();
            return;
        } 
    }
    
    private void msgInvalidName(int status){
        if(status == 0){
            OptionPane.showMsg("Imposible registrar elemento", "Ya existe un registro con el nombre ingresado pero se encuentra elimidado,\n"
                + "debes cambiar el nombre para poder continuar o restaurar el elemento eliminado.", 2);
        }else{
            OptionPane.showMsg("Imposible registrar elemento", "Ya existe un registro con el nombre ingresado,\n"
                + "debes cambiar el nombre para poder continuar.", 2);
        }
    }
    
    private void msgEntityAdded(){
        OptionPane.showMsg("Proceso finalizado", "El registro a sido guardado exitosamene.", 1);
    }
    
    private void msgEntityNotAdded(){
        OptionPane.showMsg("Proceso finalizado", "El registro no se ha podido guardar\n"
                + "ha ocurrido un error inesperado durante la operación.", 2);
    }
    
    private void msgEntityUpdated() {
        OptionPane.showMsg("Proceso finalizado", "El registro a sido modificado exitosamene.", 1);
    }

    private void msgEntityNotUpdated() {
        OptionPane.showMsg("Proceso finalizado", "El registro no se ha podido modificar\n"
                + "ha ocurrido un error inesperado durante la operación.", 2);
    }

    private void addSyncStringIdLocal(Object object) {
        SyncStringId entity = (SyncStringId)GV.LOCAL_SYNC.getElement(((SyncStringId)object).getCod(), 0, object);
        if(entity == null){
            GV.LOCAL_SYNC.add(object);
        }else{
            if(entity.getEstado() == 0){
                if(OptionPane.getConfirmation("El registro ya existe", 
                        "Los datos ingresados corresponden a un registro anulado.\n"
                        + "Los datos se actualizarán y el registro se restaurará si confirmas los cambios.\n"
                        + "¿Deseas actualizar los datos?", 2)){
                    GV.LOCAL_SYNC.update(object);
                }
            }else{
                if(OptionPane.getConfirmation("El registro ya existe", "No se ha podido guardar el registro a menos que \n"
                        + "confirmes los cambios.\n"
                        + "¿Deseas actualizar los datos?", 2)){
                    GV.LOCAL_SYNC.update(object);
                }
            }  
        }
    }
    
    private void addSyncStringIdRemote(Object object) {
        SyncStringId entity = (SyncStringId)GV.LOCAL_SYNC.getElement(((SyncStringId)object).getCod(), 0, object);
        if(entity == null){
            GV.LOCAL_SYNC.add(object);
            msgEntityAdded();
        }else{
            if(entity.getEstado() == 0){
                if(OptionPane.getConfirmation("El registro ya existe", 
                        "Los datos ingresados corresponden a un registro anulado.\n"
                        + "Los datos se actualizarán y el registro se restaurará si confirmas los cambios.\n"
                        + "¿Deseas actualizar los datos?", 2)){
                    GV.LOCAL_SYNC.update(object);
                    msgEntityAdded();
                }
            }else{
                if(OptionPane.getConfirmation("El registro ya existe", "No se ha podido guardar el registro a menos que \n"
                        + "confirmes los cambios.\n"
                        + "¿Deseas actualizar los datos?", 2)){
                    GV.LOCAL_SYNC.update(object);
                    msgEntityAdded();
                }
            }  
        }
    }

    private void addSyncIntIdValidaNameRemote(Object object) {
        SyncIntIdValidaName entity = (SyncIntIdValidaName) GV.LOCAL_SYNC.getElement(((SyncIntIdValidaName)object).getNombre(), 0, object);
        if(entity == null){
            if(GV.REMOTE_SYNC.add(object)){
                GV.LOCAL_SYNC.add(object);
                msgEntityAdded();
            }else{
                msgEntityNotAdded();
            }
        }else{
            msgInvalidName(entity.getEstado());
        }
    }

    private boolean validaEntity(Object object) {
        if(object == null){
            return false;
        }
        if(object instanceof SyncIntIdValidaName){
            SyncIntIdValidaName obj = (SyncIntIdValidaName)object;
            if(obj.getStr(obj.getNombre()).length() <= 3){
                OptionPane.showMsg("Nombre incorrecto", "El registro debe tener un nombre válido.\n"
                        + "Información a considerar:\n"
                        + "- El campo nombre no debe estar vacío.\n"
                        + "- El nombre debe tener más de tres caracteres.\n"
                        + "- El nombre no debe contener caracteres especiales.", 2);
                return false;
            }
        }
        if(object instanceof Descuento){
            Descuento obj = (Descuento)object;
            if(obj.getMonto() == 0 && obj.getPorcetange() == 0){
                OptionPane.showMsg("Descuento inválido", "No ha agregado ningún tipo de descuento.\n"
                        + "Seleccione el tipo de descuento y aplique un monto válido.", 2);
                return false;
            }
            return true;
        }
        if(object instanceof Cliente){
            Cliente obj = (Cliente)object;
            if(obj.getStr(obj.getNombre()).length() <= 3){
                OptionPane.showMsg("Nombre incorrecto", "El registro debe tener un nombre válido.\n"
                        + "Información a considerar:\n"
                        + "- El campo nombre no debe estar vacío.\n"
                        + "- El nombre debe tener más de tres caracteres.\n"
                        + "- El nombre no debe contener caracteres especiales.", 2);
                return false;
            }
            if(obj.getNacimiento() == null){
                OptionPane.showMsg("Fecha no ingresada", "El cliente debe tener una fecha de nacimiento válida.", 2);
                return false;
            }
            if(obj.getTelefono1().isEmpty() && obj.getTelefono2().isEmpty() && obj.getEmail().isEmpty()){
                OptionPane.showMsg("Faltan datos de contacto", "El cliente debe tener al menos un registro de contacto.\n"
                    + "Ingrese un teléfono o correo electrónico.", 2);
                return false;
            }
            return true;
        }
        if(object instanceof Convenio){
            Convenio obj = (Convenio)object;
            if(obj.getFechaCobro() == null || obj.getFechaFin() == null | obj.getFechaInicio() == null){
                OptionPane.showMsg("Fechas mal ingresadas", "Todos los campos de fecha deben tener un dato válido.", 2);
                return false;
            }
            if(localIsNewOrEqual(obj.getFechaInicio(), obj.getFechaFin())){
                if(!GV.dateToString(obj.getFechaInicio(), "ddmmyyyy").equals(GV.dateToString(obj.getFechaFin(), "ddmmyyyy"))){
                    OptionPane.showMsg("Fechas mal ingresadas", "La fecha de término debe ser mayor o igual a la fecha de inicio.", 2);
                    return false;
                }
            }
            if(localIsNewOrEqual(obj.getFechaFin(), obj.getFechaCobro())){
                OptionPane.showMsg("Fechas mal ingresadas", "La fecha de pago debe ser mayor a la fecha de término.", 2);
                return false;
            }
            if(!GV.fechaActualOFutura(obj.getFechaFin())){
                OptionPane.showMsg("Fechas mal ingresadas", "La fecha de inicio debe ser igual o superior a la fecha actual.", 2);
                return false;
            }
            if(GV.fechaActualOPasada(obj.getFechaCobro())){
                OptionPane.showMsg("Fechas mal ingresadas", "La fecha de pago debe ser superior a la fecha actual.", 2);
                return false;
            }
            if(obj.getIdInstitucion() == null){
                OptionPane.showMsg("Institución no existe", "Debe seleccionar una institución registrada y no modificarla.\n"
                    + "Si no aparece la deseada, debe crear un nuevo registro en \"Instituciones\".", 2);
                return false;
            }
            if(obj.getIdInstitucion().isEmpty()){
                OptionPane.showMsg("Institución no existe", "Debe seleccionar una institución registrada y no modificarla.\n"
                    + "Si no aparece la deseada, debe crear un nuevo registro en \"Instituciones\".", 2);
                return false;
            }
            return true;
        }
        if(object instanceof Cristal){
            Cristal obj = (Cristal)object;
            if(obj.getPrecio() <= 0){
                OptionPane.showMsg("Precio incorrecto", "No se puede registrar un lente con precio " + GV.strToPrice(obj.getPrecio()) + ".", 2);
                return false;
            }
            return true;
        }
        if(object instanceof Doctor){
            Doctor obj = (Doctor)object;
            if(obj.getStr(obj.getNombre()).length() <= 3){
                OptionPane.showMsg("Nombre incorrecto", "El registro debe tener un nombre válido.\n"
                        + "Información a considerar:\n"
                        + "- El campo nombre no debe estar vacío.\n"
                        + "- El nombre debe tener más de tres caracteres.\n"
                        + "- El nombre no debe contener caracteres especiales.", 2);
                return false;
            }
            if(obj.getTelefono().isEmpty() && obj.getEmail().isEmpty()){
                OptionPane.showMsg("Faltan datos de contacto", "El nuevo registro debe tener al menos un registro de contacto.\n"
                        + "Ingrese un teléfono o correo electrónico.", 2);
                return false;
            }
            return true;
        }
        if(object instanceof Institucion){
            Institucion obj = (Institucion)object;
            if(obj.getCod().isEmpty()){
                OptionPane.showMsg("Identificador no ingresado", "El nuevo registro debe tener un identificador o rut válido.", 2);
                return false;
            }
            if(obj.getStr(obj.getNombre()).length() <= 3){
                OptionPane.showMsg("Nombre incorrecto", "El registro debe tener un nombre válido.\n"
                        + "Información a considerar:\n"
                        + "- El campo nombre no debe estar vacío.\n"
                        + "- El nombre debe tener más de tres caracteres.\n"
                        + "- El nombre no debe contener caracteres especiales.", 2);
                return false;
            }
            if(obj.getTelefono().isEmpty() && obj.getEmail().isEmpty()){
                OptionPane.showMsg("Faltan datos de contacto", "El nuevo registro debe tener al menos un registro de contacto.\n"
                        + "Ingrese un teléfono o correo electrónico.", 2);
                return false;
            }
            return true;
        }
        if(object instanceof Inventario){
            return true;
        }
        if(object instanceof Lente){
            Lente obj = (Lente)object;
            if(obj.getCod().isEmpty()){
                OptionPane.showMsg("Faltan datos", "El lente debe tener un código válido.", 2);
                return false;
            }
            if(obj.getColor().isEmpty()){
                OptionPane.showMsg("Faltan datos", "El lente debe tener un color válido.", 2);
                return false;
            }
            if(obj.getTipo().isEmpty()){
                OptionPane.showMsg("Faltan datos", "El lente debe tener un tipo válido.", 2);
                return false;
            }
            if(obj.getMarca().isEmpty()){
                OptionPane.showMsg("Faltan datos", "El lente debe tener una marca válida.", 2);
                return false;
            }
            if(obj.getMaterial().isEmpty()){
                OptionPane.showMsg("Faltan datos", "El lente debe tener un material válido.", 2);
                return false;
            }
            return true;
        }
        if(object instanceof Oficina){
            Oficina obj = (Oficina)object;
            if(obj.getTelefono1().isEmpty() && obj.getTelefono2().isEmpty() && obj.getEmail().isEmpty()){
                OptionPane.showMsg("Faltan datos de contacto", "El nuevo registro debe tener al menos un registro de contacto.\n"
                        + "Ingrese un teléfono o correo electrónico.", 2);
                return false;
            }
            return true;
        }
        if(object instanceof RegistroBaja){
            return true;
        }
        if(object instanceof TipoPago){
            return true;
        }
        if(object instanceof User){
            User obj = (User)object;
            if(obj.getNombre().length() <= 3){
                OptionPane.showMsg("Agregar usuario", "No se pudo agregar usuario, debe ingresar un nombre válido,"
                    + "\nlos registros deben tener como mínimo 3 carácteres.", 2);
                return false;
            }
            if(obj.getUsername().length() <= 3){
                OptionPane.showMsg("Agregar usuario", "No se pudo agregar usuario, debe ingresar un username válido,"
                    + "\nlos registros deben tener como mínimo 3 carácteres.", 2);
                return false;
            }
            if(!GV.tipoUserSuperAdmin() && obj.getTipo() == 1){
                OptionPane.showMsg("Agregar usuario", "No se pudo agregar usuario, debe ingresar un tipo de usuario distinto,"
                        + "\nno tienes permisos suficientes para crear un usuario de tipo \"Jefatura\".", 2);
                return false;
            }
            if(obj.getTipo() == 0){
                OptionPane.showMsg("Agregar usuario", "No se pudo agregar usuario, debe ingresar un tipo de usuario válido.", 2);
                return false;
            }
            return true;
        }
        OptionPane.showMsg("Entidad no validada", "No se ha cumplido con las validaciones en esta entidad.", 3);
        return false;
    }

    private void updateRemote(Object object) {
        if(object instanceof SyncClass){
            ((SyncClass)object).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
            ((SyncClass)object).setLastHour(Cmp.hourToInt(new Date()));
            if(object instanceof SyncIntId){
                if(!GV.isOnline()){
                    OptionPane.showMsg("No se puede modificar el registro", "Para poder modificar estos datos debes tener acceso a internet.", 2);
                    return;
                }
                if(object instanceof SyncIntIdValidaName){
                    updateSyncIntIdValidaNameRemote(object);
                    return;
                }else{
                    updateSyncIntIdRemote(object);
                    return;
                }
            }
            if(object instanceof SyncStringId){
                updateSyncStringIdRemote(object);
                return;
            }
        }
        OptionPane.showMsg("No se puede modificar registro", "La entidad enviada no tiene un formato válido\n"
                + "\n"
                + "Detalle: " + object.getClass().getName(), 2);
    }

    private void updateSyncIntIdValidaNameRemote(Object object) {
        SyncIntIdValidaName entity = (SyncIntIdValidaName)GV.REMOTE_SYNC.getElement(((SyncIntIdValidaName)object).getNombre(), 0, object);
        if(entity == null){
            if(GV.REMOTE_SYNC.update(object)){
                GV.LOCAL_SYNC.update(object);
                msgEntityUpdated();
                return;
            }
            msgEntityNotUpdated();
            return;
        }else{
            if(entity.getId() == ((SyncIntIdValidaName)object).getId()){
                if(GV.REMOTE_SYNC.update(object)){
                    GV.LOCAL_SYNC.update(object);
                    msgEntityUpdated();
                    return;
                }
                msgEntityNotUpdated();
            }else{
                msgInvalidName(entity.getEstado());
            }
        }
    }

    private void updateSyncIntIdRemote(Object object) {
        if(object instanceof User){
            User entity = (User)GV.REMOTE_SYNC.getElement(((User)object).getUsername(), 0, new User());
            if(entity == null){
                if(GV.REMOTE_SYNC.update(object)){
                    GV.LOCAL_SYNC.update(object);
                    msgEntityUpdated();
                    return;
                }
                msgEntityNotUpdated();
                return;
            }else{
                if(entity.getId() == ((User)object).getId()){
                    if(GV.REMOTE_SYNC.update(object)){
                        GV.LOCAL_SYNC.update(object);
                        msgEntityUpdated();
                        return;
                    }
                    msgEntityNotUpdated();
                    return;
                }else{
                    if(entity.getEstado() == 0){
                        OptionPane.showMsg("No se puede modificar el registro", "Ya existe un usuario con el username ingresado pero se encuentra anulado.", 2);
                        return;
                    }else{
                        OptionPane.showMsg("No se puede modificar el registro", "Ya existe un usuario con el username ingresado.", 2);
                        return;
                    }
                }
            }
        }
    }

    private void updateSyncStringIdRemote(Object object) {
        if(GV.LOCAL_SYNC.update(object)){
            msgEntityUpdated();
            return;
        }
        msgEntityNotUpdated();
    }

    private void restoreOrDeleteRemote(Object object) {
        if(object instanceof SyncClass){
            ((SyncClass)object).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
            ((SyncClass)object).setLastHour(Cmp.hourToInt(new Date()));
            int estado = ((SyncClass)object).getEstado();
            if(object instanceof Ficha){
                ((SyncClass)object).setEstado(estado*-1);
            }else{
                if(estado > 0){
                    ((SyncClass)object).setEstado(0);
                }else{
                    ((SyncClass)object).setEstado(1);
                }
            }
            if(object instanceof SyncIntId){
                if(!GV.isOnline()){
                    if(((SyncClass)object).getEstado() < 1){
                        OptionPane.showMsg("No se puede eliminar el registro", "Para poder eliminar este elemento debes tener acceso a internet.", 2);
                    }else{
                        OptionPane.showMsg("No se puede restaurar el registro", "Para poder restaurar este elemento debes tener acceso a internet.", 2);
                    }
                    return;
                }
                restoreOrDeleteSyncIntIdRemote(object);
                return;
            }
            if(object instanceof SyncStringId){
                restoreOrDeleteSyncStringIdRemote(object);
                return;
            }
        }
        OptionPane.showMsg("No se puede eliminar registro", "La entidad enviada no tiene un formato válido\n"
                + "\n"
                + "Detalle: " + object.getClass().getName(), 2);
    }

    private void restoreOrDeleteSyncIntIdRemote(Object object) {
        if(GV.REMOTE_SYNC.update(object)){
            GV.LOCAL_SYNC.update(object);
            msgEntityRestoreOrDeleted(((SyncClass)object).getEstado());
            return;
        }
        msgEntityNotRestoreOrDeleted(((SyncClass)object).getEstado());
    }
    
    

    private void restoreOrDeleteSyncStringIdRemote(Object object) {
        if(GV.LOCAL_SYNC.update(object)){
            msgEntityRestoreOrDeleted(((SyncClass)object).getEstado());
            return;
        }
        msgEntityNotRestoreOrDeleted(((SyncClass)object).getEstado());
    }

    private void msgEntityRestoreOrDeleted(int estado) {
        if(estado == 0){
            OptionPane.showMsg("Registro eliminado", "La operación se ejecutó exitosamente", 1);
        }else{
            OptionPane.showMsg("Registro restaurado", "La operación se ejecutó exitosamente", 1);
        }
    }

    private void msgEntityNotRestoreOrDeleted(int estado) {
        if(estado == 0){
            OptionPane.showMsg("Registro no fue eliminado", "La operación no se pudo ejecutar correctamente", 2);
        }else{
            OptionPane.showMsg("Registro no fue restaurado", "La operación no se pudo ejecutar correctamente", 2);
        }
    }

    private boolean validaPrivilegiosParaEstados(Object object) {
        if(object instanceof Cliente){
            return GV.tipoUserIventario();
        }
        if(object instanceof Convenio)
            return GV.tipoUserSuperAdmin();
        if(object instanceof Cristal)
            return GV.tipoUserIventario();
        if(object instanceof Descuento)
            return GV.tipoUserSuperAdmin();
        if(object instanceof Doctor)
            return true;
        if(object instanceof Institucion)
            return true;
        if(object instanceof Inventario)
            return GV.tipoUserIventario();
        if(object instanceof Lente)
            return GV.tipoUserIventario();
        if(object instanceof Oficina)
            return GV.tipoUserSuperAdmin();
        if(object instanceof TipoPago)
            return GV.tipoUserSuperAdmin();
        if(object instanceof User)
            return GV.tipoUserAdmin();
        OptionPane.showMsg("No valid entitie", "isnt possible to validate the user type", 3);
        return false;
    }
}
