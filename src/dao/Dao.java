/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this objectlate file, choose Tools | Templates
 * and open the objectlate in the editor.
 */
package dao;

import entities.Cliente;
import entities.Convenio;
import entities.Cristal;
import entities.CuotasConvenio;
import entities.Descuento;
import entities.Doctor;
import entities.Equipo;
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
import entities.ficha.Armazon;
import entities.ficha.Despacho;
import entities.ficha.EtiquetFicha;
import entities.ficha.Ficha;
import entities.ficha.HistorialPago;
import fn.GV;
import fn.Log;
import fn.OptionPane;
import fn.date.Cmp;
import static fn.date.Cmp.localIsNewOrEqual;
import fn.globalValues.GlobalValuesFunctions;
import fn.globalValues.GlobalValuesVariables;
import fn.mail.Send;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.nio.cs.ext.GB18030;
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
    
    public boolean increaseStock(String idLente, int cantidad)  {
        if(GV.getStr(idLente).isEmpty()){
            return false;
        }
        if(cantidad < 0){
            return false;
        }
        try{
            Lente temp = (Lente) get(idLente, 0, new Lente());
            int newStock = 0;
            if(temp != null){
                newStock = temp.getStock() + cantidad;
                if(newStock >= 0){
                    return LocalInventario.insert(idLente, (cantidad * -1));
                }else{
                    OptionPane.showMsg("No se pudo modificar el stock", "El nuevo stock no es disponible,\n"
                            + "debe ser mayor que cero", 2);
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
            //System.out.println("no function");
            return false;
        }else{
            restoreOrDeleteRemote(object);
            return true;
        }
    }
    
    public boolean delete(String cod,int id, Object type) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Log.setLog(className,Log.getReg());
        Object temp =  GV.LOCAL_SYNC.getElement(cod,id, type);
        if(temp != null){
            if(type instanceof SyncClass){
                if(type instanceof Ficha){
                    if(GV.fechaPasada(((Ficha)temp).getFecha())){
                        OptionPane.showMsg("No se puede eliminar", "Esta opción no se encuentra disponible,\n"
                                + "solo se pueden anular fichas generadas hoy.", 2);
                        return false;
                    }
                    ((SyncClass)temp).setEstado(((((SyncClass)temp).getEstado())*-1));
                    String idCerca = null;
                    String idLejos = null;
                    if(((Ficha)temp).getCerca()!=null){
                        idCerca = ((Ficha)temp).getCerca().getMarca();
                        increaseStock(idCerca, 1);
                    }
                    if(((Ficha)temp).getLejos()!=null){
                        idLejos = ((Ficha)temp).getLejos().getMarca();
                        increaseStock(idLejos, 1);
                    }
                    if(((Ficha)temp).getConvenio()!=null){
                        int idConv = ((Ficha)temp).getConvenio().getId();
                        if(idConv != 0){
                            Convenio cnvLoad = (Convenio)get(null, idConv, new Convenio());
                            if(cnvLoad.getEstado() == 2){
                                OptionPane.showMsg("No se puede anular", "Este registro se encuentra relacionado con un convenio que ya fué generado,\n"
                                        + " por lo tanto es imposible eliminar", 2);
                                return false;
                            }else{
                                Convenio cnv = new Convenio(((idConv) * -1), null, null, null, 0, null, 0, 0, 0, 0, 0, null, 0, null, 0);
                                ((Ficha)temp).setConvenio(cnv);
                            }
                            
                        }
                    }
                }else{
                    ((SyncClass)temp).setEstado(0);
                }
                ((SyncClass)temp).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
                ((SyncClass)temp).setLastHour(Cmp.hourToInt(new Date()));
                
                if(GV.isOnline()){
                    GV.REMOTE_SYNC.update(temp);
                }
                return GV.LOCAL_SYNC.update(temp);
            }else{
                OptionPane.showMsg("No se puede eliminar", "El registro no tiene tipo válido.", 2);
                return false;
            }
        }else{
            OptionPane.showMsg("No se puede eliminar", "El registro no existe.", 2);
            return false;
        } 
    }

    public boolean restore(String cod,int id,Object type) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Log.setLog(className,Log.getReg());
        Object temp =  GV.LOCAL_SYNC.getElement(cod,id, type);
        if(temp != null){
            if(type instanceof SyncClass){
                if(type instanceof Ficha){
                    OptionPane.showMsg("No se puede restaurar", "Esta opción aún no se encuentra disponible.", 2);
                    return false;
                    //((SyncClass)temp).setEstado(((((SyncClass)temp).getEstado())*-1));
                }else{
                    ((SyncClass)temp).setEstado(1);
                }
                ((SyncClass)temp).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
                ((SyncClass)temp).setLastHour(Cmp.hourToInt(new Date()));
                
                if(GV.isOnline()){
                    GV.REMOTE_SYNC.update(temp);
                }
                return GV.LOCAL_SYNC.update(temp);
            }else{
                OptionPane.showMsg("No se puede restaurar", "El registro no tiene tipo válido.", 2);
                return false;
            }
        }else{
            OptionPane.showMsg("No se puede restaurar", "El registro no existe.", 2);
            return false;
        } 
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
        if(type instanceof Ficha){
            type = new EtiquetFicha();
        }
        boolean esLente = (type instanceof Lente);
            //System.out.println(esLente);
        if(GV.isCurrentDate(GV.LAST_UPDATE)){//validar plan de licencia
            return;//solo hace una actualizacion por día.
        }
        try {
            if(GV.isOnline()){
                if(type instanceof Ficha){
                    type = new EtiquetFicha();
                }
                ArrayList<Object> defaultList= GV.REMOTE_SYNC.listar("-2",type);
                ArrayList<Object> defaultLocalList= GV.LOCAL_SYNC.listar("-2",type);
                /*LISTA1 SE DEBE CARGAR CON UN RETRASO DE DOS MESES PARA RESCATAR ULTIMOS REGISTROS SUBIDOS*/
                ArrayList<Object> lista1= GV.REMOTE_SYNC.listar(GV.dateSumaResta(GV.LAST_UPDATE, -2, "MONTHS"),type);
                int size1 = lista1.size();
                ArrayList<Object> lista2= GV.LOCAL_SYNC.listar(GV.LAST_UPDATE,type);
                
                int size2 = lista2.size();
                if(size1 > 0){
                    String sql1 = "";
                    for (Object object : lista1) {
                        Object local;
                        //System.out.println("lista1");
                        if(object instanceof SyncIntId){
                            //System.out.println("INT");
                            local = GV.buscarPorIdEnLista(""+((SyncIntId)object).getId(), defaultLocalList, type);
                        }else if(object instanceof SyncStringId){
                            //System.out.println("STRING");
                            local = GV.buscarPorIdEnLista(((SyncStringId)object).getCod(), defaultLocalList, type);
                        }else{
                            //System.out.println("XXX");
                            return;
                        }
                        GV.porcentajeSubCalcular(size1+size2);
                        if(local == null){
                            /*CREAR SQL PARA INSERTAR TODOS LOS REGISTROS EN UNA SOLA CONSULTA*/
                            //System.out.println("INSERT");
                            sql1 = getSqlRemoteInsert(sql1, object);
                        }else{
                            /*VALIDAR SI YA ESTÁ INSERTADO PARA UPDATEAR*/
                            if(object instanceof SyncClass){
                                /*VALIDAR SI LA FECHA DEL OBJETO LOCAL ES NUEVA O IGUAL*/
                                if(Cmp.localIsNewOrEqual(((SyncClass)object).getLastUpdate(), ((SyncClass)local).getLastUpdate())){
                                    /*VALIDAR SI LA FECHA ES LA MISMA*/
                                    if(GV.dateToString(((SyncClass)object).getLastUpdate(), "ddmmyyyy").equals(GV.dateToString(((SyncClass)local).getLastUpdate(), "ddmmyyyy"))){
                                        /*VALIDAR SI LA HORA ES MAS RECIENTE*/
                                        if(((SyncClass)object).getLastHour() > ((SyncClass)local).getLastHour()){
                                            GV.LOCAL_SYNC.updateFromDao(object);
                                            //System.out.println("UPD1");
                                        }
                                    }else{
                                        GV.LOCAL_SYNC.updateFromDao(object);
                                        //System.out.println("UPD2");
                                    }
                                }
                            }
                        }
                    }
                    if(!sql1.isEmpty()){
                        //System.out.println("EXE INS");
                        GV.LOCAL_SYNC.insertFromDao(sql1);
                    }
                }
                if(size2 > 0){
                    String sql2 = "";
                    for (Object object : lista2) {
                        //System.out.println("lista2");
                        GV.porcentajeSubCalcular(size1+size2);
                        if(!GV.isOnline()){
                            GV.stopSincronizacion();
                        }
                        if(GV.sincronizacionIsStopped()){
                            return;
                        }
                        Object remote;
                        if(object instanceof SyncIntId){
                            //System.out.println("INT");
                            remote = GV.buscarPorIdEnLista(""+((SyncIntId)object).getId(), defaultList, type);
                        }else if(object instanceof SyncStringId){
                            //System.out.println("STRING");
                            remote = GV.buscarPorIdEnLista(((SyncStringId)object).getCod(), defaultList, type);
                        }else{
                            //System.out.println("XXX");
                            return;
                        }
                        if(remote == null){
                            /*CREAR SQL PARA INSERTAR TODOS LOS REGISTROS EN UNA SOLA CONSULTA*/
                            //System.out.println("INSERT");
                            sql2 = getSqlRemoteInsert(sql2, object);
                        }else{
                            /*VALIDAR SI YA ESTÁ INSERTADO PARA UPDATEAR*/
                            if(object instanceof SyncClass){
                                /*VALIDAR SI LA FECHA DEL OBJETO LOCAL ES NUEVA O IGUAL*/
                                if(Cmp.localIsNewOrEqual(((SyncClass)object).getLastUpdate(), ((SyncClass)remote).getLastUpdate())){
                                    /*VALIDAR SI LA FECHA ES LA MISMA*/
                                    if(GV.dateToString(((SyncClass)object).getLastUpdate(), "ddmmyyyy").equals(GV.dateToString(((SyncClass)remote).getLastUpdate(), "ddmmyyyy"))){
                                        /*VALIDAR SI LA HORA ES MAS RECIENTE*/
                                        if(((SyncClass)object).getLastHour() > ((SyncClass)remote).getLastHour()){
                                            GV.REMOTE_SYNC.updateFromDao(object);
                                            //System.out.println("UPD1");
                                        }
                                    }else{
                                        GV.REMOTE_SYNC.updateFromDao(object);
                                        //System.out.println("UPD2");
                                    }
                                }
                            }
                        }
                    }//TERMINA DE RECORRER LA LISTA LOCAL
                    if(!sql2.isEmpty()){
                        //System.out.println("EXE INS");
                        GV.REMOTE_SYNC.insertFromDao(sql2);
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
                        //System.out.println("lentes");
                        Object tmpLen = GV.buscarPorIdEnLista(((Lente)object).getCod(), defaultList, new Lente());
                        if(tmpLen != null){
                            /*SOLO ACTUALIZA LOS LENTES CON STOCK DISTINTOS*/
                            if(((Lente)object).getStock() != ((Lente)tmpLen).getStock()){
                                //System.out.println("cmbia stock");
                                if(((Lente)object).getStock() >= 0){
                                    if(Cmp.localIsNewOrEqual(((Lente)object).getLastUpdate(), ((Lente)tmpLen).getLastUpdate())){
                                        if(GV.dateToString(((SyncClass)object).getLastUpdate(), "ddmmyyyy").equals(GV.dateToString(((SyncClass)tmpLen).getLastUpdate(), "ddmmyyyy"))){
                                        /*VALIDAR SI LA HORA ES MAS RECIENTE*/
                                            if(((SyncClass)object).getLastHour() < ((SyncClass)tmpLen).getLastHour()){
                                                int stock = ((Lente)object).getStock();
                                                object = tmpLen;
                                                ((Lente)object).setStock(stock);
                                            }
                                        }
                                    }else{
                                        int stock = ((Lente)object).getStock();
                                        object = tmpLen;
                                        ((Lente)object).setStock(stock);
                                    }
                                    ((SyncClass)object).setLastUpdate(new Date());
                                    ((SyncClass)object).setLastHour(GV.strToNumber(GV.dateToString(new Date(), "hhmmss")));
                                    sync.Sync.addRemoteSync(GV.LOCAL_SYNC, GV.REMOTE_SYNC, object);
                                    GV.REMOTE_SYNC.updateFromDao(object);
                                }
                            }
                        }
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

    public boolean createFicha(Ficha ficha, HistorialPago hp) {
        setLastUpdates(hp);
        setLastUpdates(ficha.getCliente());
        setLastUpdates(ficha.getDoctor());
        setLastUpdates(ficha.getCerca());
        setLastUpdates(ficha.getLejos());
        setLastUpdates(ficha);
        
        int maxIdArmazon = GV.LOCAL_SYNC.getMaxId(new Armazon());
        ficha.getLejos().setCod(maxIdArmazon + "-" + GV.getIdEquipo());
        ficha.getCerca().setCod((maxIdArmazon + 1) + "-" + GV.getIdEquipo());
        
        if(ficha.getCliente() != null){
            if(!GV.getStr(ficha.getCliente().getCod()).isEmpty()){
                GV.LOCAL_SYNC.add(ficha.getCliente());
            }  
        }
        if(LocalInventario.addObject(ficha.getCerca())){
            String idLente = ficha.getCerca().getMarca();
            if(!idLente.isEmpty()){
                decreaseStock(idLente, 1);
            }
        }
        if(LocalInventario.addObject(ficha.getLejos())){
            String idLente = ficha.getLejos().getMarca();
            if(!idLente.isEmpty()){
                decreaseStock(idLente, 1);
            }
        }  
        LocalInventario.addObject(ficha);
        if(hp != null){
            if(hp.getAbono() > 0){
                hp.setCod(getCurrentCod(hp));
                LocalInventario.addObject(hp);
            }
        }
        return true;
    }
    
    private void setLastUpdates(SyncClass object){
        Date lastDate = new Date();
        if(object != null){
            (object).setLastUpdate(lastDate);//actualizamos la ultima fecha de modificacion
            (object).setLastHour(Cmp.hourToInt(lastDate));
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
    
    public static String getSqlRemoteInsert(String sql, Object object){
        if(object instanceof SyncClass){
            if(sql.startsWith("INSERT")){
                //System.out.println("getSqlRemoteInsert 1");
                sql = sql + "," + getValuesSqlStatement(object);
            }else{
                //System.out.println("getSqlRemoteInsert 2");
                sql = getInsertSqlStatement(object) + getValuesSqlStatement(object);
            }
        }
        //System.out.println("SQL: "+sql);
        return sql;
    }
    
    private static String getValuesSqlStatement(Object object){
        if(object instanceof Armazon){
            //System.out.println("values arm");
            return ((Armazon)object).getSqlInsertStatement();
        }
        if(object instanceof Cliente){
            //System.out.println("values cli:"+((Cliente)object).getSqlInsertStatement());
            return ((Cliente)object).getSqlInsertStatement();
        }
        if(object instanceof Convenio){
            //System.out.println("values cnv");
            return ((Convenio)object).getSqlInsertStatement();
        }
        if(object instanceof Cristal){
            //System.out.println("values cri");
            return ((Cristal)object).getSqlInsertStatement();
        }
        if(object instanceof CuotasConvenio){
            //System.out.println("values cc");
            return ((CuotasConvenio)object).getSqlInsertStatement();
        }
        if(object instanceof Descuento){
            //System.out.println("values des");
            return ((Descuento)object).getSqlInsertStatement();
        }
        if(object instanceof Despacho){
            //System.out.println("values dsp");
            return ((Despacho)object).getSqlInsertStatement();
        }
        if(object instanceof Doctor){
            //System.out.println("values doc");
            return ((Doctor)object).getSqlInsertStatement();
        }
        if(object instanceof Equipo){
            //System.out.println("values eq");
            return ((Equipo)object).getSqlInsertStatement();
        }
        if(object instanceof EtiquetFicha){
            //System.out.println("values etiq ficha");
            return ((EtiquetFicha)object).getSqlInsertStatement();
        }
        if(object instanceof HistorialPago){
            //System.out.println("values hp");
            return ((HistorialPago)object).getSqlInsertStatement();
        }
        if(object instanceof Institucion){
            //System.out.println("values inst");
            return ((Institucion)object).getSqlInsertStatement();
        }
        if(object instanceof Inventario){
            //System.out.println("values inv");
            return ((Inventario)object).getSqlInsertStatement();
        }
        if(object instanceof Lente){
            //System.out.println("values len");
            return ((Lente)object).getSqlInsertStatement();
        }
        if(object instanceof InternMail){
            //System.out.println("values mail");
            return ((InternMail)object).getSqlInsertStatement();
        }
        if(object instanceof Oficina){
            //System.out.println("values ofi");
            return ((Oficina)object).getSqlInsertStatement();
        }
        if(object instanceof RegistroBaja){
            //System.out.println("values rb");
            return ((RegistroBaja)object).getSqlInsertStatement();
        }
        if(object instanceof TipoPago){
            //System.out.println("values tp");
            return ((TipoPago)object).getSqlInsertStatement();
        }
        if(object instanceof User){
            //System.out.println("values us");
            return ((User)object).getSqlInsertStatement();
        }
        //System.out.println("values nadad");
        return "";
    }
    
    private static String getInsertSqlStatement(Object object){
        if(object instanceof Armazon){
            //System.out.println("Armazon");
            return "INSERT INTO armazon VALUES ";
        }
        if(object instanceof Cliente){
            //System.out.println("cliente");
            return "INSERT INTO cliente VALUES ";
        }
        if(object instanceof Convenio){
            //System.out.println("convenio");
            return "INSERT INTO convenio VALUES ";
        }
        if(object instanceof Cristal){
            //System.out.println("cristal");
            return "INSERT INTO cristal VALUES ";
        }
        if(object instanceof CuotasConvenio){
            //System.out.println("cuotas");
            return "INSERT INTO cuotas_convenio VALUES ";
        }
        if(object instanceof Descuento){
            //System.out.println("descuento");
            return "INSERT INTO descuento VALUES ";
        }
        if(object instanceof Despacho){
            //System.out.println("despacho");
            return "INSERT INTO despacho VALUES ";
        }
        if(object instanceof Doctor){
            //System.out.println("doctor");
            return "INSERT INTO doctor VALUES ";
        }
        if(object instanceof Equipo){
            //System.out.println("equipo");
            return "INSERT INTO equipo VALUES ";
        }
        if(object instanceof EtiquetFicha){
            return "INSERT INTO ficha VALUES ";
        }
        if(object instanceof HistorialPago){
            //System.out.println("historialoaogo");
            return "INSERT INTO historial_pago VALUES ";
        }
        if(object instanceof Institucion){
            //System.out.println("inst");
            return "INSERT INTO institucion VALUES ";
        }
        if(object instanceof Inventario){
            //System.out.println("inv");
            return "INSERT INTO inventario VALUES ";
        }
        if(object instanceof Lente){
            //System.out.println("len");
            return "INSERT INTO lente VALUES ";
        }
        if(object instanceof InternMail){
            //System.out.println("message");
            return "INSERT INTO message VALUES ";
        }
        if(object instanceof Oficina){
            //System.out.println("ofi");
            return "INSERT INTO oficina VALUES ";
        }
        if(object instanceof RegistroBaja){
            //System.out.println("rb");
            return "INSERT INTO registro_bajas VALUES ";
        }
        if(object instanceof TipoPago){
            //System.out.println("tp");
            return "INSERT INTO tipo_pago VALUES ";
        }
        if(object instanceof User){
            //System.out.println("us");
            return "INSERT INTO usuario VALUES ";
        }
        //System.out.println("sql nada");
        return "";
    }
}
