/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sync.entities;

import bd.RmBd;
import entities.ficha.Armazon;
import entities.ficha.Despacho;
import entities.ficha.Ficha;
import entities.ficha.HistorialPago;
import fn.GlobalValues;
import fn.Log;
import fn.OptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sync.InterfaceSyncFicha;

/**
 *
 * @author sdx
 */
public class RemoteFicha implements InterfaceSyncFicha{
    private static String className = "RemoteFicha";
    @Override
    public boolean add(Object objectParam) {
        Log.setLog(className,Log.getReg());
        if(objectParam == null)
            return false;
        try{
            if(objectParam instanceof Armazon){
                Armazon object = (Armazon)objectParam;
                PreparedStatement consulta = RmBd.obtener().prepareStatement("SELECT arm_id FROM armazon WHERE arm_id='"+object.getCod()+"'");
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    RmBd.cerrar();
                    return update(object);
                }
                PreparedStatement insert = RmBd.obtener().prepareStatement(
                        sqlInsert(object)
                               );
                if(insert.executeUpdate()!=0){
                    RmBd.cerrar();

                    return true;
                }
            }
            if(objectParam instanceof Despacho){
                Despacho object = (Despacho)objectParam;
                PreparedStatement consulta = RmBd.obtener().prepareStatement("SELECT dsp_id FROM despacho WHERE dsp_id='"+object.getCod()+"'");
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    RmBd.cerrar();
                    return update(object);
                }
                PreparedStatement insert = RmBd.obtener().prepareStatement(
                        sqlInsert(object)
                               );
                if(insert.executeUpdate()!=0){
                    RmBd.cerrar();

                    return true;
                }
            }
            if(objectParam instanceof HistorialPago){
                HistorialPago object = (HistorialPago)objectParam;
                PreparedStatement consulta = RmBd.obtener().prepareStatement("SELECT hp_id FROM historial_pago WHERE hp_id='"+object.getCod()+"'");
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    RmBd.cerrar();
                    return update(object);
                }  
                PreparedStatement insert = RmBd.obtener().prepareStatement(
                        sqlInsert(object)
                               );
                if(insert.executeUpdate()!=0){
                    RmBd.cerrar();

                    return true;
                }
            }
            if(objectParam instanceof Ficha){
                Ficha object = (Ficha)objectParam;
                
                PreparedStatement consulta = RmBd.obtener().prepareStatement("SELECT fch_id FROM ficha WHERE fch_id='"+object.getCod()+"'");
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    RmBd.cerrar();
                    return update(object);
                }  
                
                PreparedStatement insert = RmBd.obtener().prepareStatement(
                        sqlInsert(object)
                               );
                if(insert.executeUpdate()!=0){
                    RmBd.cerrar();

                    return true;
                }
            }
        }   catch ( ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LocalFicha.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    @Override
    public boolean update(Object objectParam) {
        Log.setLog(className,Log.getReg());
        if(objectParam == null)
            return false;
        try{
            if(objectParam instanceof Armazon){
                Armazon object = (Armazon)objectParam;
                PreparedStatement consulta = RmBd.obtener().prepareStatement("SELECT * FROM armazon WHERE arm_id='"+object.getCod()+"'");
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    Date dsp_fecha= new Date();
                    try {
                        dsp_fecha = datos.getDate("arm_last_update");
                    } catch (SQLException e) {
                        OptionPane.showMsg("Error al convertir fecha","Se cayó al intentar convertir la fecha.\nDetalle:"+e.getMessage()+"\n"+Log.getLog(), JOptionPane.ERROR_MESSAGE);
                    }
                    if(!fn.date.Cmp.localIsNewOrEqual(object.getLastUpdate(), dsp_fecha)){
                        return false;
                    }
                }
                PreparedStatement insert = RmBd.obtener().prepareStatement(
                        sqlUpdate(object));
                if(insert.executeUpdate()!=0){
                    RmBd.cerrar();

                    return true;
                }
                else{
                    RmBd.cerrar();
                    return true;
                }
            }
            if(objectParam instanceof Despacho){
                Despacho object = (Despacho)objectParam;
                PreparedStatement consulta = RmBd.obtener().prepareStatement("SELECT * FROM despacho WHERE dsp_id='"+object.getCod()+"'");
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    Date dsp_fecha= new Date();
                    try {
                        dsp_fecha = datos.getDate("dsp_last_update");
                    } catch (SQLException e) {
                        OptionPane.showMsg("Error al convertir fecha","Se cayó al intentar convertir la fecha.\nDetalle:"+e.getMessage()+"\n"+Log.getLog(), JOptionPane.ERROR_MESSAGE);
                    }
                    if(!fn.date.Cmp.localIsNewOrEqual(object.getLastUpdate(), dsp_fecha)){
                        return false;
                    }
                }
                PreparedStatement insert = RmBd.obtener().prepareStatement(
                        sqlUpdate(object));
                if(insert.executeUpdate()!=0){
                    RmBd.cerrar();

                    return true;
                }
                else{
                    RmBd.cerrar();
                    return true;
                }
            }
            if(objectParam instanceof HistorialPago){
                HistorialPago object = (HistorialPago)objectParam;
                PreparedStatement consulta = RmBd.obtener().prepareStatement("SELECT * FROM historial_pago WHERE hp_id='"+object.getCod()+"'");
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    Date dsp_fecha= new Date();
                    try {
                        dsp_fecha = datos.getDate("hp_last_update");
                    } catch (SQLException e) {
                        OptionPane.showMsg("Error al convertir fecha","Se cayó al intentar convertir la fecha.\nDetalle:"+e.getMessage()+"\n"+Log.getLog(), JOptionPane.ERROR_MESSAGE);
                    }
                    if(!fn.date.Cmp.localIsNewOrEqual(object.getLastUpdate(), dsp_fecha)){
                        return false;
                    }
                }
                PreparedStatement insert = RmBd.obtener().prepareStatement(
                        sqlUpdate(object));
                if(insert.executeUpdate()!=0){
                    RmBd.cerrar();

                    return true;
                }
                else{
                    RmBd.cerrar();
                    return true;
                }
            }
            if(objectParam instanceof Ficha){
                Ficha object = (Ficha)objectParam;
                PreparedStatement consulta = RmBd.obtener().prepareStatement("SELECT * FROM ficha WHERE fch_id='"+object.getCod()+"'");
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    Date dsp_fecha= new Date();
                    try {
                        dsp_fecha = datos.getDate("fch_last_update");
                    } catch (SQLException e) {
                        OptionPane.showMsg("Error al convertir fecha","Se cayó al intentar convertir la fecha.\nDetalle:"+e.getMessage()+"\n"+Log.getLog(), JOptionPane.ERROR_MESSAGE);
                    }
                    if(!fn.date.Cmp.localIsNewOrEqual(object.getLastUpdate(), dsp_fecha)){
                        return false;
                    }
                }
                PreparedStatement insert = RmBd.obtener().prepareStatement(
                        sqlUpdate(object));
                if(insert.executeUpdate()!=0){
                    RmBd.cerrar();

                    return true;
                }
                else{
                    RmBd.cerrar();
                    return true;
                }
            }
        }catch(SQLException | ClassNotFoundException ex){
            Logger.getLogger(Remote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    /**
     * Retorna el elemento activo según la clase asignada al type
     * @param idFicha debe ser el id de la ficha donde se encuentra el objeto
     * @return Clase encontrada, se debe parsear para su uso correcto.
     */
    @Override
    public Despacho getLastDespacho(String idFicha) {
        Log.setLog(className,Log.getReg());
        Date aux = null;
        Object element = null;
        ArrayList<Object> list = listar(idFicha,new Despacho());
        if(list.size() == 1)
            return (Despacho)list.get(0);
        for (Object object : list) {
            if(aux == null)
                element = object;
            if(aux != null){
                if(fn.date.Cmp.localIsNewOrEqual(((Despacho)object).getLastUpdate(), aux))
                    element = object;
            }
            aux = ((Despacho)object).getLastUpdate();
        }
        return (Despacho)element;
    }
    @Override
    public ArrayList<HistorialPago> getPagos(String idFicha) {
        Log.setLog(className,Log.getReg());
        ArrayList<HistorialPago> list = new ArrayList<>();
        for (Object object : listar(idFicha, new HistorialPago())) {
            list.add((HistorialPago)object);
        }
        return list;
    }
    @Override
    public ArrayList<Object> listar(String idParam, Object type) {
        Log.setLog(className,Log.getReg());
        ArrayList<Object> lista = new ArrayList<>();
        try{
            if(type instanceof Armazon){
                String sql="SELECT * FROM armazon WHERE ficha_fch_id='"+idParam+"' AND arm_estado = 1";
        
                PreparedStatement consulta = RmBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new Armazon(
                            datos.getString("arm_id")
                            , datos.getInt("arm_tipo")
                            , datos.getString("arm_marca")
                            , datos.getString("arm_cristal")
                            , datos.getString("arm_add")
                            , datos.getString("arm_od_a")
                            , datos.getString("arm_od_esf")
                            , datos.getString("arm_od_cil")
                            , datos.getString("arm_oi_a")
                            , datos.getString("arm_oi_esf")
                            , datos.getString("arm_oi_cil")
                            , datos.getInt("arm_dp")
                            , datos.getInt("arm_endurecido")
                            , datos.getInt("arm_capa")
                            , datos.getInt("arm_plus_max")
                            , datos.getString("ficha_fch_id")
                            , datos.getInt("arm_estado")
                            , datos.getDate("arm_last_update")
                            , datos.getInt("arm_last_hour")
                            )
                    );
                }
                RmBd.cerrar();
                return lista;
            }
            if(type instanceof Despacho){
                String sql="SELECT * FROM despacho WHERE ficha_fch_id='"+idParam+"' AND dsp_estado = 1";
        
                PreparedStatement consulta = RmBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new Despacho(
                            datos.getString("dsp_id")
                            , datos.getString("dsp_rut")
                            , datos.getString("dsp_nombre")
                            , datos.getDate("dsp_fecha")
                            , datos.getString("ficha_fch_id")
                            , datos.getInt("dsp_estado")
                            , datos.getDate("dsp_last_update")
                            , datos.getInt("dsp_last_hour")
                            )
                    );
                }
                RmBd.cerrar();
                return lista;
            }
            if(type instanceof HistorialPago){
                String sql="SELECT * FROM historial_pago WHERE ficha_fch_id='"+idParam+"' AND hp_estado = 1";
        
                PreparedStatement consulta = RmBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new HistorialPago(
                            datos.getString("hp_id")
                            , datos.getDate("hp_fecha")
                            , datos.getInt("hp_abono")
                            , datos.getInt("hp_estado")
                            , datos.getInt("tipo_pago_tp_id")
                            , datos.getString("ficha_fch_id")
                            , datos.getDate("hp_last_update")
                            , datos.getInt("hp_last_hour")
                            )
                    );
                }
                RmBd.cerrar();
                return lista;
            }
        }catch(ClassNotFoundException | SQLException ex){
            Logger.getLogger(RemoteFicha.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    @Override
    public ArrayList<Object> listar(Date paramDate, Object type) {
        Log.setLog(className,Log.getReg());
        java.sql.Date param = new java.sql.Date(paramDate.getTime());
        ArrayList<Object> lista = new ArrayList<>();
        try{
            if(type instanceof Armazon){
                String sql="SELECT * FROM armazon WHERE arm_last_update >='"+param+"'";

                PreparedStatement consulta = RmBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new Armazon(
                            datos.getString("arm_id")
                            , datos.getInt("arm_tipo")
                            , datos.getString("arm_marca")
                            , datos.getString("arm_cristal")
                            , datos.getString("arm_add")
                            , datos.getString("arm_od_a")
                            , datos.getString("arm_od_esf")
                            , datos.getString("arm_od_cil")
                            , datos.getString("arm_oi_a")
                            , datos.getString("arm_oi_esf")
                            , datos.getString("arm_oi_cil")
                            , datos.getInt("arm_dp")
                            , datos.getInt("arm_endurecido")
                            , datos.getInt("arm_capa")
                            , datos.getInt("arm_plus_max")
                            , datos.getString("ficha_fch_id")
                            , datos.getInt("arm_estado")
                            , datos.getDate("arm_last_update")
                            , datos.getInt("arm_last_hour")
                            )
                    );
                }
                RmBd.cerrar();
                return lista;
            }
            if(type instanceof Despacho){
                String sql="SELECT * FROM despacho WHERE dsp_last_update >='"+param+"'";

                PreparedStatement consulta = RmBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new Despacho(
                            datos.getString("dsp_id")
                            , datos.getString("dsp_rut")
                            , datos.getString("dsp_nombre")
                            , datos.getDate("dsp_fecha")
                            , datos.getString("ficha_fch_id")
                            , datos.getInt("dsp_estado")
                            , datos.getDate("dsp_last_update")
                            , datos.getInt("dsp_last_hour")
                            )
                    );
                }
                RmBd.cerrar();
                return lista;
            }
            if(type instanceof HistorialPago){
                String sql="SELECT * FROM historial_pago WHERE hp_last_update >='"+param+"'";

                PreparedStatement consulta = RmBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new HistorialPago(
                            datos.getString("hp_id")
                            , datos.getDate("hp_fecha")
                            , datos.getInt("hp_abono")
                            , datos.getInt("hp_estado")
                            , datos.getInt("tipo_pago_tp_id")
                            , datos.getString("ficha_fch_id")
                            , datos.getDate("hp_last_update")
                            , datos.getInt("arm_last_hour")
                            )
                    );
                }
                RmBd.cerrar();
                return lista;
            }
        }catch(ClassNotFoundException | SQLException ex){
            Logger.getLogger(RemoteFicha.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    @Override
    public Armazon getLejos(String idFicha) {
        Log.setLog(className,Log.getReg());
        ArrayList<Object> lista;
        lista = listar(idFicha,new Armazon());
        if(lista.size() > 2){
            OptionPane.showMsg("Error de archivos.", "Este registro a tenido un error al momento de ser guardado,"
                    + "\nconsulte en soporte al cliente para solucionar este problema."
                    + "\nError de registro en documento numero: "+idFicha, JOptionPane.ERROR_MESSAGE);
            return null;
        }
        for (Object object : lista) {
            if(object instanceof Armazon)
                if(((Armazon)object).getTipo() == 1)
                    return (Armazon)object;
        }
        return null;
    }
    @Override
    public Armazon getCerca(String idFicha) {
        Log.setLog(className,Log.getReg());
        ArrayList<Object> lista;
        lista = listar(idFicha,new Armazon());
        if(lista.size() > 2){
            OptionPane.showMsg("Error de archivos.", "Este registro a tenido un error al momento de ser guardado,"
                    + "\nconsulte en soporte al cliente para solucionar este problema."
                    + "\nError de registro en documento numero: "+idFicha, JOptionPane.ERROR_MESSAGE);
            return null;
        }
        for (Object object : lista) {
            if(object instanceof Armazon)
                if(((Armazon)object).getTipo() == 0)
                    return (Armazon)object;
        }
        return null;
    }
    /**
     * Obtiene el maximo id
     * @param strParam
     * @param intParam
     * @param objParam
     * @return 
     */
    @Override
    public int getMaxId(String strParam, int intParam, Object objParam) {
        Log.setLog(className,Log.getReg());
        String sql = "";
        try {
            if(objParam instanceof Armazon){
                if(armIsValid(strParam,intParam)){
                    sql="SELECT COUNT(*) as id FROM armazon";
                }else{
                    OptionPane.showMsg("No se puede insertar registro", "Ocurrió un error de duplicación de datos"
                            + "\nPor favor intente nuevamente."
                            + "\n\nSi el problema persiste póngase en contacto con el soporte al cliente.", JOptionPane.ERROR_MESSAGE);
                    return -1;
                }
            }
            if(objParam instanceof Despacho){
                sql="SELECT COUNT(*) as id FROM despacho";
            }
            if(objParam instanceof HistorialPago){
                sql="SELECT COUNT(*) as id FROM historial_pago";
            }
            if(sql.contains("SELECT")){
                PreparedStatement consulta = RmBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                int id=0;
                while (datos.next()) {
                    id=datos.getInt("id");
                }
                RmBd.cerrar();
                return id+1;
            }else 
                return -1;
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(RemoteFicha.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    private boolean armIsValid(String idFicha, int tipo) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        Log.setLog(className,Log.getReg());
        String sql="SELECT COUNT(*) as cantidad FROM armazon WHERE ficha_fch_id = '"+idFicha+"' and arm_tipo = "+tipo+"";

        PreparedStatement consulta = RmBd.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        int cant=0;
        while (datos.next()) {
            cant=datos.getInt("cantidad");
        }
        RmBd.cerrar();
        return cant == 0;
    }
    /**
     * Retorna el id de la clase que se está solicitando con el type
     * @param strParam válido solo para Armazon
     * @param intParam válido solo para Armazon
     * @param type Debe ir el tipo de Clase que se necesita
     * @return Id del nuevo registro correlativo con el numero de 
     * equipo registrado. si es "Error-" o "-0" no se debe continuar con la inserción
     */
    @Override
    public String getId(String strParam, int intParam, Object type) {
        String value = GlobalValues.getIndexId();
        if(type instanceof Armazon){
            return value+getMaxId(strParam, intParam, new Armazon());
        }
        if(type instanceof Despacho){
            return value+getMaxId(strParam, intParam, new Despacho());
        }
        if(type instanceof HistorialPago){
            return value+getMaxId(strParam, intParam, new HistorialPago());
        }
        return value+"0";
    }

    private String sqlInsert(Object objectParam) {
        if(objectParam instanceof Armazon){
            Armazon object = (Armazon)objectParam;
            java.sql.Date sqlfecha = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return "INSERT INTO armazon VALUES('"
                                +object.getCod()+"', "
                                +object.getTipo()+", '"
                                +object.getMarca()+"', '"
                                +object.getCristal()+"', '"
                                +object.getAdd()+"', '"
                                +object.getOdA()+"', '"
                                +object.getOdEsf()+"', '"
                                +object.getOdCil()+"', '"
                                +object.getOiA()+"', '"
                                +object.getOiEsf()+"', '"
                                +object.getOiCil()+"', "
                                +object.getDp()+", "
                                +object.getEndurecido()+", "
                                +object.getCapa()+", "
                                +object.getPlusMax()+", '"
                                +object.getIdFicha()+"', "
                                +object.getEstado()+", '"
                                +sqlfecha+"', "
                                +object.getLastHour()+")";
        }
        if(objectParam instanceof Despacho){
            Despacho object = (Despacho)objectParam;
            java.sql.Date sqlfecha = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return "INSERT INTO despacho VALUES('"
                                +object.getCod()+"', '"
                                +object.getRut()+"', '"
                                +object.getNombre()+"', '"
                                +object.getFecha()+"', '"
                                +object.getIdFicha()+"', "
                                +object.getEstado()+", '"
                                +sqlfecha+"', "
                                +object.getLastHour()+")";
        }
        if(objectParam instanceof HistorialPago){
            HistorialPago object = (HistorialPago)objectParam;
            java.sql.Date sqlfecha1 = new java.sql.Date(object.getFecha().getTime());//la transforma a sql.Date
            java.sql.Date sqlfecha2 = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return "INSERT INTO historial_pago VALUES('"
                                +object.getCod()+"', '"
                                +sqlfecha1+"', "
                                +object.getAbono()+", "
                                +object.getEstado()+", "
                                +object.getIdTipoPago()+", '"
                                +object.getIdFicha()+"', '"
                                +sqlfecha2+"', "
                                +object.getLastHour()+")";
        }
        if(objectParam instanceof Ficha){
            Ficha object = (Ficha)objectParam;
            java.sql.Date fecha  = new java.sql.Date(object.getFecha().getTime());//la transforma a sql.Date
            java.sql.Date fechaEntrega = new java.sql.Date(object.getFechaEntrega().getTime());//la transforma a sql.Date
            java.sql.Date lastUpdate = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date

            String rutCliente = null;
            String rutDoctor = null;
            int idDescuento = 0;
            int idInstitucion = 0;
            String idDespacho = null;
            int idUser = 0;
            if(object.getCliente()!= null)
                rutCliente = object.getCliente().getCod();
            if(object.getDoctor()!= null)
                rutCliente = object.getDoctor().getCod();
            if(object.getDescuento()!= null)
                idDescuento=object.getDescuento().getId();
            if(object.getDespacho()!= null)
                idDespacho=object.getDespacho().getCod();
            if(object.getUser()!= null)
                idUser=object.getUser().getId();
            return "INSERT INTO ficha VALUES('"
                                +object.getCod()+"', '"
                                +fecha+"', '"
                                +fechaEntrega+"', '"
                                +object.getLugarEntrega()+"', '"
                                +object.getHoraEntrega()+"', "
                                +object.getValorTotal()+", "
                                +object.getSaldo()+", '"
                                +object.getObservacion()+"', '"
                                +rutCliente+"', '"
                                +rutDoctor+"', "
                                +idDescuento+", "
                                +idInstitucion+", '"
                                +idDespacho+"', "
                                +idUser+", "
                                +object.getEstado()+", '"
                                +lastUpdate+"', "
                                +object.getLastHour()+")";
        }
        return null;
    }

    private String sqlUpdate(Object objectParam) {
        if(objectParam instanceof Armazon){
            Armazon object = (Armazon)objectParam;
            java.sql.Date sqlfecha = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return "UPDATE armazon set arm_tipo = "+object.getTipo()
                                +", arm_marca = '"+object.getMarca()
                                +"', arm_cristal = '"+object.getCristal()
                                +"', arm_add = '"+object.getAdd()
                                +"', arm_od_a = '"+object.getOdA()
                                +"', arm_od_esf = '"+object.getOdEsf()
                                +"', arm_od_cil = '"+object.getOdCil()
                                +"', arm_oi_a = '"+object.getOiA()
                                +"', arm_oi_esf = '"+object.getOiEsf()
                                +"', arm_oi_cil = '"+object.getOiCil()
                                +"', arm_dp = "+object.getDp()
                                +", arm_endurecido = "+object.getEndurecido()
                                +", arm_capa = "+object.getCapa()
                                +", arm_plus_max = "+object.getPlusMax()
                                +", ficha_fch_id = '"+object.getIdFicha()
                                +"', arm_estado = "+object.getEstado()
                                +", arm_last_update = '"+sqlfecha
                                +"',arm_last_hour = "+object.getLastHour()
                                +" WHERE arm_id = '"+object.getCod()+"' AND (arm_last_update <= '"
                                +sqlfecha+"' AND arm_last_hour < "+object.getLastHour()+")";
        }
        if(objectParam instanceof Despacho){
            Despacho object = (Despacho)objectParam;
            java.sql.Date sqlfecha1 = new java.sql.Date(object.getFecha().getTime());//la transforma a sql.Date
            java.sql.Date sqlfecha2 = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return "UPDATE despacho set dsp_rut = '"+object.getRut()
                                +"', dsp_nombre = '"+object.getNombre()
                                +"', dsp_fecha = '"+sqlfecha1
                                +"', ficha_fch_id = '"+object.getIdFicha()
                                +"', dsp_estado = "+object.getEstado()
                                +", dsp_last_update = '"+sqlfecha2
                                +"', dsp_last_hour = "+object.getLastHour()
                                +" WHERE dsp_id = '"+object.getCod()+"' AND (dsp_last_update <= '"
                                +sqlfecha2+"' AND dsp_last_hour < "+object.getLastHour()+")";
        }
        if(objectParam instanceof HistorialPago){
            HistorialPago object = (HistorialPago)objectParam;
            java.sql.Date sqlfecha1 = new java.sql.Date(object.getFecha().getTime());//la transforma a sql.Date
            java.sql.Date sqlfecha2 = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return "UPDATE historial_pago set hp_fecha = '"+sqlfecha1
                                +"', hp_abono = "+object.getAbono()
                                +", hp_estado = "+object.getEstado()
                                +", tipo_pago_tp_id = "+object.getIdTipoPago()
                                +", ficha_fch_id = '"+object.getIdFicha()
                                +"', hp_last_update = '"+sqlfecha2
                                +"', hp_last_hour ="+object.getLastHour()
                                +" WHERE hp_id = '"+object.getCod()+"' AND (hp_last_update <= '"
                                +sqlfecha2+"' AND hp_last_hour < "+object.getLastHour()+")";
        }
        if(objectParam instanceof Ficha){
            Ficha object = (Ficha)objectParam;
            java.sql.Date fecha  = new java.sql.Date(object.getFecha().getTime());//la transforma a sql.Date
            java.sql.Date fechaEntrega = new java.sql.Date(object.getFechaEntrega().getTime());//la transforma a sql.Date
            java.sql.Date lastUpdate = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date

            String rutCliente = null;
            String rutDoctor = null;
            int idDescuento = 0;
            int idInstitucion = 0;
            String idDespacho = null;
            int idUser = 0;
            if(object.getCliente()!= null)
                rutCliente = object.getCliente().getCod();
            if(object.getDoctor()!= null)
                rutCliente = object.getDoctor().getCod();
            if(object.getDescuento()!= null)
                idDescuento=object.getDescuento().getId();
            if(object.getDespacho()!= null)
                idDespacho=object.getDespacho().getCod();
            if(object.getUser()!= null)
                idUser=object.getUser().getId();
            return "UPDATE ficha set fch_fecha = '"+fecha
                                +"', fch_fecha_entrega = '"+fechaEntrega
                                +"', fch_lugar_entrega = '"+object.getLugarEntrega()
                                +"', fch_hora_entrega = '"+object.getHoraEntrega()
                                +"', fch_valor_total = "+object.getValorTotal()
                                +", fch_saldo = "+object.getSaldo()
                                +", fch_obs = '"+object.getObservacion()
                                +"', cliente_cli_rut = '"+rutCliente
                                +"', doctor_doc_rut = '"+rutDoctor
                                +"', descuento_des_id = "+idDescuento
                                +", institucion_ins_id = "+idInstitucion
                                +", despacho_dsp_id = '"+idDespacho
                                +"', usuario_us_id = "+idUser
                                +"', fch_estado = "+object.getEstado()
                                +", fch_last_update = '"+lastUpdate
                                +"', fch_last_hour = "+object.getLastHour()
                                +" WHERE fch_id = '"+object.getCod()+"' AND (fch_last_update <= '"
                                +lastUpdate+"' AND fch_last_hour < "+object.getLastHour()+")";
        }
        return null;
    }
}
