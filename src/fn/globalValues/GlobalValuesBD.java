/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn.globalValues;

import bd.LcBd;
import dao.Dao;
import entities.Cliente;
import entities.Convenio;
import entities.Cristal;
import entities.CuotasConvenio;
import entities.Descuento;
import entities.Doctor;
import entities.Equipo;
import entities.Institucion;
import entities.InternMail;
import entities.InternStock;
import entities.Inventario;
import entities.Lente;
import entities.Oficina;
import entities.RegistroBaja;
import entities.TipoPago;
import entities.User;
import entities.ficha.Armazon;
import entities.ficha.Despacho;
import entities.ficha.EtiquetFicha;
import entities.ficha.Ficha;
import entities.ficha.HistorialPago;
import fn.GV;
import fn.OptionPane;
import static fn.globalValues.GlobalValuesFunctions.getWhereFromAllFichas;
import static fn.globalValues.GlobalValuesFunctions.getWhereFromFicha;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.write.WriteException;
import sync.entities.Migrar;
import sync.entities.Remote;

/**
 *
 * @author jlleivas
 */
public class GlobalValuesBD {
    /* Bases de datos*/
    public static String BD_URL_REMOTE;
    public static String BD_NAME_REMOTE;
    public static String BD_USER_REMOTE;
    public static String BD_PASS_REMOTE;
    public static String BD_URL_LOCAL = "."+File.separator+"DB"+File.separator;//"localhost:1527"
    public static String BD_NAME_LOCAL = "Derby.DB";//"odm4"
    public static String BD_USER_LOCAL = "odm4";
    public static String BD_PASS_LOCAL = "odm4";
    
    private static Migrar mig = new Migrar();
    private static Remote rem = new Remote();
    
    private static boolean SINCRONIZAR = false;
    private static boolean error = false;
    
    private static List<Object> LISTA_FICHAS = new ArrayList<>();
    
    private static int arm = 1;
    private static int cli = 2;
    private static int cnv = 3;
    private static int cri = 4;
    private static int cc = 5;
    private static int des = 6;
    private static int dsp = 7;
    private static int doc = 8;
    private static int eq = 9;
    private static int fch = 10;
    private static int hp = 11;
    private static int ins = 12;
    private static int ist = 13;
    private static int inv = 14;
    private static int len = 15;
    private static int msg = 16;
    private static int of = 17;
    private static int rb = 18;
    private static int tp = 19;
    private static int usu = 20;
    
    
    private static String ARMAZON = "ARM_ID VARCHAR(25) not null primary key," +
" ARM_TIPO INTEGER," +
" ARM_MARCA VARCHAR(100)," +
" ARM_PRECIO_MARCA INTEGER," +
" ARM_CRISTAL VARCHAR(45)," +
" ARM_PRECIO_CRISTAL INTEGER," +
" ARM_ADD VARCHAR(45)," +
" ARM_OD_A VARCHAR(45)," +
" ARM_OD_ESF VARCHAR(45)," +
" ARM_OD_CIL VARCHAR(45)," +
" ARM_OI_A VARCHAR(45)," +
" ARM_OI_ESF VARCHAR(45)," +
" ARM_OI_CIL VARCHAR(45)," +
" ARM_DP INTEGER," +
" ARM_ENDURECIDO INTEGER," +
" ARM_CAPA INTEGER," +
" ARM_PLUS_MAX INTEGER," +
" FICHA_FCH_ID VARCHAR(25)," +
" ARM_ESTADO INTEGER," +
" ARM_LAST_UPDATE DATE," +
" ARM_LAST_HOUR INTEGER";
    private static String CLIENTE = "CLI_RUT VARCHAR(25) not null primary key," +
" CLI_NOMBRE VARCHAR(45)," +
" CLI_TELEFONO1 VARCHAR(25)," +
" CLI_TELEFONO2 VARCHAR(25)," +
" CLI_EMAIL VARCHAR(45)," +
" CLI_DIRECCION VARCHAR(45)," +
" CLI_COMUNA VARCHAR(45)," +
" CLI_CIUDAD VARCHAR(45)," +
" CLI_SEXO INTEGER," +
" CLI_NACIMIENTO DATE," +
" CLI_ESTADO INTEGER," +
" CLI_LAST_UPDATE DATE," +
" CLI_LAST_HOUR INTEGER";
    private static String CONVENIO = "CNV_ID INTEGER not null primary key," +
" CNV_NOMBRE VARCHAR(45)," +
" CNV_FECHA_INICIO DATE," +
" CNV_FECHA_FIN DATE," +
" CNV_CUOTAS INTEGER," +
" CNV_FECHA_COBRO DATE," +
" CNV_MONTO_MAXIMO INTEGER," +
" CNV_MONTO_PP INTEGER," +
" CNV_MAXIMO_CLIENTES INTEGER," +
" DESCUENTO_DES_ID INTEGER," +
" CNV_PORC_VALOR_ADICIONAL INTEGER,"+
" INSTITUCION_INS_ID VARCHAR(25)," +
" CNV_ESTADO INTEGER," +
" CNV_LAST_UPDATE DATE," +
" CNV_LAST_HOUR INTEGER";
    private static String CRISTAL = "CRI_ID INTEGER not null primary key," +
" CRI_NOMBRE VARCHAR(45)," +
" CRI_PRECIO INTEGER," +
" CRI_ESTADO INTEGER," +
" CRI_LAST_UPDATE DATE," +
" CRI_LAST_HOUR INTEGER";
    private static String CUOTAS_CONVENIO = "CC_ID VARCHAR(25) not null primary key," +
" CC_FECHA DATE," +
" CC_FECHA_PAGADO DATE," +
" CC_MONTO INTEGER," +
" TIPO_PAGO_TP_ID INTEGER," +
" CONVENIO_CNV_ID INTEGER,"+
" CC_ESTADO INTEGER," +
" CC_LAST_UPDATE DATE," +
" CC_LAST_HOUR INTEGER";
    private static String DESCUENTO = "DES_ID INTEGER not null primary key," +
" DES_NOMBRE VARCHAR(45)," +
" DES_DESCRIPCION LONG VARCHAR," +
" DES_PORC INTEGER," +
" DES_MONTO INTEGER," +
" DES_ESTADO INTEGER," +
" DES_LAST_UPDATE DATE," +
" DES_LAST_HOUR INTEGER";
    private static String DESPACHO = "DSP_ID VARCHAR(25) not null primary key," +
" DSP_RUT VARCHAR(25)," +
" DSP_NOMBRE VARCHAR(45)," +
" DSP_FECHA DATE," +
" FICHA_FCH_ID VARCHAR(25)," +
" DSP_ESTADO INTEGER," +
" DSP_LAST_UPDATE DATE," +
" DSP_LAST_HOUR INTEGER";
    private static String DOCTOR = "DOC_RUT VARCHAR(25)," +
" DOC_NOMBRE VARCHAR(45)," +
" DOC_TELEFONO VARCHAR(25)," +
" DOC_MAIL VARCHAR(45)," +
" DOC_ESTADO INTEGER," +
" DOC_LAST_UPDATE DATE," +
" DOC_LAST_HOUR INTEGER";
    private static String EQUIPO = "EQ_ID INTEGER not null primary key," +
" EQ_NOMBRE VARCHAR(45)," +
" EQ_LICENCIA VARCHAR(45)," +
" EQ_BD VARCHAR(100)," +
" EQ_BD_USER VARCHAR(100)," +
" EQ_BD_PASS VARCHAR(100)," +
" EQ_BD_URL VARCHAR(100)," +
" EQ_ESTADO INTEGER," +
" EQ_LAST_UPDATE DATE," +
" EQ_LAST_HOUR INTEGER";
    private static String FICHA = "FCH_ID VARCHAR(25) not null primary key," +
" FCH_FECHA DATE," +
" FCH_FECHA_ENTREGA DATE," +
" FCH_LUGAR_ENTREGA VARCHAR(45)," +
" FCH_HORA_ENTREGA VARCHAR(15)," +
" FCH_OBS LONG VARCHAR," +
" FCH_VALOR_TOTAL INTEGER," +
" FCH_DESCUENTO INTEGER," +
" FCH_SALDO INTEGER," +
" CLIENTE_CLI_RUT VARCHAR(25)," +
" DOCTOR_DOC_RUT VARCHAR(25)," +
" INSTITUCION_INS_ID VARCHAR(25)," +
" DESPACHO_DSP_ID VARCHAR(25)," +
" USUARIO_US_ID INTEGER," +
" CONVENIO_CNV_ID INTEGER," +
" FCH_ESTADO INTEGER," +
" FCH_LAST_UPDATE DATE," +
" FCH_LAST_HOUR INTEGER";
    private static String HISTORIAL_PAGO = "HP_ID VARCHAR(25)," +
" HP_FECHA DATE," +
" HP_ABONO INTEGER," +
" TIPO_PAGO_TP_ID INTEGER," +
" FICHA_FCH_ID VARCHAR(25)," +
" HP_ESTADO INTEGER," +
" HP_LAST_UPDATE DATE," +
" HP_LAST_HOUR INTEGER";
    private static String INSTITUCION = "INS_ID VARCHAR(25) not null primary key," +
" INS_NOMBRE VARCHAR(45)," +
" INS_TELEFONO VARCHAR(25)," +
" INS_MAIL VARCHAR(45)," +
" INS_WEB VARCHAR(45)," +
" INS_DIRECCION VARCHAR(45)," +
" INS_COMUNA VARCHAR(45)," +
" INS_CIUDAD VARCHAR(45)," +
" INS_ESTADO INTEGER," +
" INS_LAST_UPDATE DATE," +
" INS_LAST_HOUR INTEGER";
    private static String INTERN_STOCK = "ID INTEGER not null primary key," +
" ID_LENTE VARCHAR(100) not null," +
" STOCK INTEGER," +
" ESTADO INTEGER";
    private static String INVENTARIO = "INV_ID INTEGER not null primary key," +
" INV_NOMBRE VARCHAR(45)," +
" INV_DESCRIPCION LONG VARCHAR," +
" INV_ESTADO INTEGER," +
" INV_LAST_UPDATE DATE," +
" INV_LAST_HOUR INTEGER";
    private static String LENTE = "LEN_ID VARCHAR(100) not null primary key," +
" LEN_COLOR VARCHAR(25)," +
" LEN_TIPO VARCHAR(45)," +
" LEN_MARCA VARCHAR(45)," +
" LEN_MATERIAL VARCHAR(45)," +
" LEN_FLEX INTEGER," +
" LEN_CLASIFICACION INTEGER," +
" LEN_DESCRIPCION LONG VARCHAR," +
" LEN_PRECIO_REF INTEGER," +
" LEN_PRECIO_ACT INTEGER," +
" LEN_STOCK INTEGER," +
" LEN_STOCK_MIN INTEGER," +
" INVENTARIO_INV_ID INTEGER," +
" LEN_ESTADO INTEGER," +
" LEN_LAST_UPDATE DATE," +
" LEN_LAST_HOUR INTEGER";
   private static String MESSAGE = "MSG_ID INTEGER not null primary key," +
" US_ID_REMITENTE INTEGER," +
" US_ID_DESTINATARIO INTEGER," +
" MSG_ASUNTO VARCHAR(45)," +
" MSG_CONTENT LONG VARCHAR," +
" MSG_FECHA DATE," +
" MSG_HORA VARCHAR(25)," +
" MSG_ESTADO INTEGER," +
" MSG_LAST_UPDATE DATE," +
" MSG_LAST_HOUR INTEGER";
   private static String OFICINA = "OF_ID INTEGER not null primary key," +
" OF_NOMBRE VARCHAR(45)," +
" OF_DIRECCION VARCHAR(45)," +
" OF_CIUDAD VARCHAR(45)," +
" OF_TELEFONO1 VARCHAR(25)," +
" OF_TELEFONO2 VARCHAR(25)," +
" OF_EMAIL VARCHAR(45)," +
" OF_WEB VARCHAR(100)," +
" OF_ESTADO INTEGER," +
" OF_LAST_UPDATE DATE," +
" OF_LAST_HOUR INTEGER";
    private static String REGISTRO_BAJAS = "RB_ID VARCHAR(25) not null primary key," +
" RB_FECHA DATE," +
" LENTE_LEN_ID VARCHAR(100)," +
" RB_CANTIDAD INTEGER," +
" RB_OBS LONG VARCHAR," +
" RB_ESTADO INTEGER," +
" RB_LAST_UPDATE DATE," +
" RB_LAST_HOUR INTEGER";
    private static String TIPO_PAGO = "TP_ID INTEGER not null primary key," +
" TP_NOMBRE VARCHAR(45)," +
" TP_ESTADO INTEGER," +
" TP_LAST_UPDATE DATE," +
" TP_LAST_HOUR INTEGER";
    private static String USUARIO = "US_ID INTEGER not null primary key," +
" US_NOMBRE VARCHAR(45)," +
" US_USERNAME VARCHAR(45)," +
" US_EMAIL VARCHAR(45)," +
" US_PASS VARCHAR(100)," +
" US_TIPO INTEGER," +
" US_ESTADO INTEGER," +
" US_LAST_UPDATE DATE," +
" US_LAST_HOUR INTEGER";
    private static String COL_ARMAZON = "ARM_ID," +
" ARM_TIPO," +
" ARM_MARCA," +
" ARM_PRECIO_MARCA," +
" ARM_CRISTAL," +
" ARM_PRECIO_CRISTAL," +
" ARM_ADD," +
" ARM_OD_A," +
" ARM_OD_ESF," +
" ARM_OD_CIL," +
" ARM_OI_A," +
" ARM_OI_ESF," +
" ARM_OI_CIL," +
" ARM_DP," +
" ARM_ENDURECIDO," +
" ARM_CAPA," +
" ARM_PLUS_MAX," +
" FICHA_FCH_ID," +
" ARM_ESTADO," +
" ARM_LAST_UPDATE," +
" ARM_LAST_HOUR";
    private static String COL_CLIENTE = "CLI_RUT," +
" CLI_NOMBRE," +
" CLI_TELEFONO1," +
" CLI_TELEFONO2," +
" CLI_EMAIL," +
" CLI_DIRECCION," +
" CLI_COMUNA," +
" CLI_CIUDAD," +
" CLI_SEXO," +
" CLI_NACIMIENTO," +
" CLI_ESTADO," +
" CLI_LAST_UPDATE," +
" CLI_LAST_HOUR";
    private static String COL_CONVENIO = "CNV_ID," +
" CNV_NOMBRE," +
" CNV_FECHA_INICIO," +
" CNV_FECHA_FIN," +
" CNV_CUOTAS," +
" CNV_FECHA_COBRO," +
" CNV_MONTO_MAXIMO," +
" CNV_MONTO_PP," +
" CNV_MAXIMO_CLIENTES," +
" DESCUENTO_DES_ID," +
" CNV_PORC_VALOR_ADICIONAL,"+
" INSTITUCION_INS_ID," +
" CNV_ESTADO," +
" CNV_LAST_UPDATE," +
" CNV_LAST_HOUR";
    private static String COL_CRISTAL = "CRI_ID," +
" CRI_NOMBRE," +
" CRI_PRECIO," +
" CRI_ESTADO," +
" CRI_LAST_UPDATE," +
" CRI_LAST_HOUR";
    private static String COL_CUOTAS_CONVENIO = "CC_ID," +
" CC_FECHA," +
" CC_FECHA_PAGADO," +
" CC_MONTO," +
" TIPO_PAGO_TP_ID," +
" CONVENIO_CNV_ID," +            
" CC_ESTADO," +
" CC_LAST_UPDATE," +
" CC_LAST_HOUR";
    private static String COL_DESCUENTO = "DES_ID," +
" DES_NOMBRE," +
" DES_DESCRIPCION," +
" DES_PORC," +
" DES_MONTO," +
" DES_ESTADO," +
" DES_LAST_UPDATE," +
" DES_LAST_HOUR";
    private static String COL_DESPACHO = "DSP_ID," +
" DSP_RUT," +
" DSP_NOMBRE," +
" DSP_FECHA," +
" FICHA_FCH_ID," +
" DSP_ESTADO," +
" DSP_LAST_UPDATE," +
" DSP_LAST_HOUR";
    private static String COL_DOCTOR = "DOC_RUT," +
" DOC_NOMBRE," +
" DOC_TELEFONO," +
" DOC_MAIL," +
" DOC_ESTADO," +
" DOC_LAST_UPDATE," +
" DOC_LAST_HOUR";
    private static String COL_EQUIPO = "EQ_ID," +
" EQ_NOMBRE," +
" EQ_LICENCIA," +
" EQ_BD," +
" EQ_BD_USER," +
" EQ_BD_PASS," +
" EQ_BD_PASS," +
" EQ_ESTADO," +
" EQ_LAST_UPDATE," +
" EQ_LAST_HOUR";
    private static String COL_FICHA = "FCH_ID," +
" FCH_FECHA," +
" FCH_FECHA_ENTREGA," +
" FCH_LUGAR_ENTREGA," +
" FCH_HORA_ENTREGA," +
" FCH_OBS," +
" FCH_VALOR_TOTAL," +
" FCH_DESCUENTO," +
" FCH_SALDO," +
" CLIENTE_CLI_RUT," +
" DOCTOR_DOC_RUT," +
" INSTITUCION_INS_ID," +
" DESPACHO_DSP_ID," +
" USUARIO_US_ID," +
" CONVENIO_CNV_ID," +
" FCH_ESTADO," +
" FCH_LAST_UPDATE," +
" FCH_LAST_HOUR";
    private static String COL_HISTORIAL_PAGO = "HP_ID," +
" HP_FECHA," +
" HP_ABONO," +
" TIPO_PAGO_TP_ID," +
" FICHA_FCH_ID," +
" HP_ESTADO," +
" HP_LAST_UPDATE," +
" HP_LAST_HOUR";
    private static String COL_INSTITUCION = "INS_ID," +
" INS_NOMBRE," +
" INS_TELEFONO," +
" INS_MAIL," +
" INS_WEB," +
" INS_DIRECCION," +
" INS_COMUNA," +
" INS_CIUDAD," +
" INS_ESTADO," +
" INS_LAST_UPDATE," +
" INS_LAST_HOUR";
    private static String COL_INTERN_STOCK = "ID," +
" ID_LENTE," +
" STOCK," +
" ESTADO";
    private static String COL_INVENTARIO = "INV_ID," +
" INV_NOMBRE," +
" INV_DESCRIPCION," +
" INV_ESTADO," +
" INV_LAST_UPDATE," +
" INV_LAST_HOUR";
    private static String COL_LENTE = "LEN_ID," +
" LEN_COLOR," +
" LEN_TIPO," +
" LEN_MARCA," +
" LEN_MATERIAL," +
" LEN_FLEX," +
" LEN_CLASIFICACION," +
" LEN_DESCRIPCION," +
" LEN_PRECIO_REF," +
" LEN_PRECIO_ACT," +
" LEN_STOCK," +
" LEN_STOCK_MIN," +
" INVENTARIO_INV_ID," +
" LEN_ESTADO," +
" LEN_LAST_UPDATE," +
" LEN_LAST_HOUR";
   private static String COL_MESSAGE = "MSG_ID," +
" US_ID_REMITENTE," +
" US_ID_DESTINATARIO," +
" MSG_ASUNTO," +
" MSG_CONTENT," +
" MSG_FECHA," +
" MSG_HORA," +
" MSG_ESTADO," +
" MSG_LAST_UPDATE," +
" MSG_LAST_HOUR";
   private static String COL_OFICINA = "OF_ID," +
" OF_NOMBRE," +
" OF_DIRECCION," +
" OF_CIUDAD," +
" OF_TELEFONO1," +
" OF_TELEFONO2," +
" OF_EMAIL," +
" OF_WEB," +
" OF_ESTADO," +
" OF_LAST_UPDATE," +
" OF_LAST_HOUR";
    private static String COL_REGISTRO_BAJAS = "RB_ID," +
" RB_FECHA," +
" LENTE_LEN_ID," +
" RB_CANTIDAD," +
" RB_OBS," +
" RB_ESTADO," +
" RB_LAST_UPDATE," +
" RB_LAST_HOUR";
    private static String COL_TIPO_PAGO = "TP_ID," +
" TP_NOMBRE," +
" TP_ESTADO," +
" TP_LAST_UPDATE," +
" TP_LAST_HOUR";
    private static String COL_USUARIO = "US_ID," +
" US_NOMBRE," +
" US_USERNAME," +
" US_EMAIL," +
" US_PASS," +
" US_TIPO," +
" US_ESTADO," +
" US_LAST_UPDATE," +
" US_LAST_HOUR";
    
    public static Connection initDB(){
        return LcBd.crear();
    }
    
    /**
     * Elimina todos los datos y las tablas de la base de datos
     * @return 
     */
    public static Connection dropDB(){
        return LcBd.deleteAll();
    }
    
    public static void sincronizarTodo(){
        if(GV.syncEnabled()){
            //si la ultima fecha de actualizacion corresponde al dia actual
            //restamos un dia a LastUpdate para validar actualización
            GV.resetAllPorcentaje();
            if(GV.isCurrentDate(GV.getLastUpdate())){
                GV.setLastUpdate(GV.dateSumaResta(GV.getLastUpdate(), -1, "DAYS"));
            }
            sincronizar(allEntities());
            GV.setLastUpdate(new Date());
            GV.setSyncCount(GV.getSyncCount()+1);
        }else{
            OptionPane.showMsg("No se puede procesar la solicitud", 
                    "Se ha agotado el limite de sincronizaciones por día", 2);
        } 
    }
    
    public static List<Object> allEntities(){
        List<Object> entities = new ArrayList<>();
        entities.add(new RegistroBaja());
        entities.add(new TipoPago());
        entities.add(new User());
        entities.add(new EtiquetFicha());
        entities.add(new HistorialPago());
        entities.add(new Inventario());
        entities.add(new Armazon());
        entities.add(new Cliente());
        entities.add(new Convenio());
        entities.add(new Lente());
        entities.add(new Oficina());
        entities.add(new Cristal());
        entities.add(new CuotasConvenio());
        entities.add(new Descuento());
        entities.add(new Despacho());
        entities.add(new Doctor());
        entities.add(new Equipo());
        entities.add(new Institucion());
        entities.add(new InternMail());
        
        return entities;
    }
    
    public static void sincronizar(List<Object> listaObjetos){
        GV.startSincronizacion();
//        Boton boton = new Boton();
//        boton.barraProgresoVisible();
        GV.porcentajeCalcular(listaObjetos.size(),"Preparando la sincronización");
        for (Object type : listaObjetos) {
            if(type instanceof Ficha){
                type = new EtiquetFicha();
            }
            if(!sincronizeObject(type)){
                error = true;
                break;
            }
            GV.porcentajeCalcular(listaObjetos.size(), "Sincronizando entidades [Tipo de datos:"+GV.className(type).trim()+"]...");
        }
        GV.resetAllPorcentaje();
        GV.stopSincronizacion();
        if(error){
            OptionPane.showMsg("La sincrconización se ha suspendido", "No se sincronizaron los datos por uno de estos motivos:\n"
                    + "-Se ha cancelado manualmente\n"
                    + "-Ocurrió un error de datos en la red, compruebe su conexion a internet", 2);
        }
    }
    
    public static boolean sincronizeObject(Object object){
        if(GV.isOnline()){
            if(!GV.sincronizacionIsStopped()){
                
                Dao.sincronize(object);
                
                return true;
            }
        }
        GV.setReporte("No se pudo sincronizar la base de datos...");
        return false;
    }
    
    /**
     * leimina todos los datos de las tablas
     * @return 
     */
    public static Connection truncateDB(){
        return LcBd.truncateAll();
    }
    
    public static String getLocalBdUser() {
        return BD_USER_LOCAL;
    }

    public static String getLocalBdPass() {
        return BD_PASS_LOCAL;
    }

    public static String getLocalBdUrl() {
        return BD_URL_LOCAL;
    }

    public static String getLocalBdName() {
        return BD_NAME_LOCAL;
    }
    
    public static String getRemoteBdUser() {
        return BD_USER_REMOTE;
    }

    public static String getRemoteBdPass() {
        return BD_PASS_REMOTE;
    }

    public static String getRemoteBdUrl() {
        return BD_URL_REMOTE;
    }

    public static String getRemoteBdName() {
        return BD_NAME_REMOTE;
    }
    public static String[] tableNamesDB(){
        String[] names = {"ARMAZON","CLIENTE","CONVENIO","CRISTAL",
                           "CUOTAS_CONVENIO","DESCUENTO","DESPACHO","DOCTOR",
                           "EQUIPO","FICHA","HISTORIAL_PAGO","INSTITUCION",
                           "INTERN_STOCK","INVENTARIO","LENTE","MESSAGE",
                           "OFICINA","REGISTRO_BAJAS","TIPO_PAGO","USUARIO"};
        return names;
    }
    public static String[] tablesDB(){
        String[] tables = {ARMAZON,CLIENTE,CONVENIO,CRISTAL,
                           CUOTAS_CONVENIO,DESCUENTO,DESPACHO,DOCTOR,
                           EQUIPO,FICHA,HISTORIAL_PAGO,INSTITUCION,
                           INTERN_STOCK,INVENTARIO,LENTE,MESSAGE,
                           OFICINA,REGISTRO_BAJAS,TIPO_PAGO,USUARIO};
        if(tableNamesDB().length != tables.length){
            OptionPane.showMsg("Error al generar tablas", "Los objetos Array no coinciden\nDetalle: GlobalValuesBD:tablesBD()", 3);
        }
        return tables;
    }
    public static String[] tableDataDB(){
        String[] tables = {COL_ARMAZON,COL_CLIENTE,COL_CONVENIO,COL_CRISTAL,
                           COL_CUOTAS_CONVENIO,COL_DESCUENTO,COL_DESPACHO,COL_DOCTOR,
                           COL_EQUIPO,COL_FICHA,COL_HISTORIAL_PAGO,COL_INSTITUCION,
                           COL_INTERN_STOCK,COL_INVENTARIO,COL_LENTE,COL_MESSAGE,
                           COL_OFICINA,COL_REGISTRO_BAJAS,COL_TIPO_PAGO,COL_USUARIO};
        if(tableNamesDB().length != tables.length){
            OptionPane.showMsg("Error al generar tablas", "Los objetos Array no coinciden\nDetalle: GlobalValuesBD:tablesBD()", 3);
        }
        return tables;
    }
    
    public static void backUpLocalBd(){
        try {
            if(GV.isOnline() && LcBd.obtener() != null){
                LcBd.cerrar();
                createExcel(new Armazon());
                createExcel(new Cliente());
                createExcel(new Convenio());
                createExcel(new Cristal());
                createExcel(new CuotasConvenio());
                createExcel(new Descuento());
                createExcel(new Despacho());
                createExcel(new Doctor());
                createExcel(new Equipo());
                createExcel(new Ficha());
                createExcel(new HistorialPago());
                createExcel(new Institucion());
                createExcel(new Inventario());
                createExcel(new InternStock());
                createExcel(new Lente());
                createExcel(new InternMail());
                createExcel(new Oficina());
                createExcel(new RegistroBaja());
                createExcel(new TipoPago());
                createExcel(new User());

                GlobalValuesZipFiles.zipperBackup();
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(GlobalValuesBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private static boolean createExcel(Object type){
        Object[][] res = listaObjetos(type);
        if(res == null || res.length < 1){
            return false;
        }
        if(res.length > 0){
            try {
                GlobalValuesSaveXls.generarExcelRespaldo((String [][])res, nameObjeto(type));
            } catch (WriteException ex) {
                Logger.getLogger(GlobalValuesBD.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        }
        return false;
    }
    
    private static Object[][] listaObjetos(Object type){
        String[] T = tableNamesDB();
        String[] D = tableDataDB();
        int index = getIndex(type);
        if(T.length == D.length){
            return LcBd.select(T[index], D[index], null);
        }
        return null;
    }
    
    public static String nameObjeto(Object type){
        String[] T = tableNamesDB();
        String[] D = tableDataDB();
        int index = getIndex(type);
        if(T.length == D.length){
            return T[index];
        }
        return null;
    }
    
    private static int getIndex(Object type){
        if(type instanceof Armazon){
            return arm-1;
        }
        if(type instanceof Cliente){
            return cli-1;
        }
        if(type instanceof Convenio){
            return cnv-1;
        }
        if(type instanceof Cristal){
            return cri-1;
        }
        if(type instanceof CuotasConvenio){
            return cc-1;
        }
        if(type instanceof Descuento){
            return des-1;
        }if(type instanceof Despacho){
            return dsp-1;
        }if(type instanceof Doctor){
            return doc-1;
        }if(type instanceof Equipo){
            return eq-1;
        }if(type instanceof Ficha){
            return fch-1;
        }if(type instanceof HistorialPago){
            return hp-1;
        }if(type instanceof Institucion){
            return ins-1;
        }if(type instanceof InternStock){
            return ist-1;
        }if(type instanceof Inventario){
            return inv-1;
        }if(type instanceof Lente){
            return len-1;
        }if(type instanceof InternMail){
            return msg-1;
        }if(type instanceof Oficina){
            return of-1;
        }if(type instanceof RegistroBaja){
            return rb-1;
        }if(type instanceof TipoPago){
            return tp-1;
        }if(type instanceof User){
            return usu-1;
        }
        return 0;
    }

    public static void listarFichasByDate(Date date1, Date date2) {
//        Dao load = new Dao();
//        if(date1==null && date2==null){
//            date1=new Date();
//            OptionPane.showMsg("Datos vacíos","Las fechas ingresadas están vacías,\n"
//                    + "Se tomará como parámetro la fecha actual.", 2);
//        }
//        date1=(date1==null)?date2:date1;
//        date2=(date2==null)?date1:date2;
//        if(Cmp.localIsNewOrEqual(date1, date2)){
//            Date aux = date2;
//            date2=date1;
//            date1=aux;
//        }
//        String d1 = (!GV.dateToString(date1, "yyyy-mm-dd").equals("date-error"))?GV.dateToString(date1, "yyyy-mm-dd"):"";
//        String d2 = (!GV.dateToString(date2, "yyyy-mm-dd").equals("date-error"))?GV.dateToString(date2, "yyyy-mm-dd"):"";
//        String idParam = GV.convertFichaIdParamToDateList(d1+"/"+d2);
//        LISTA_FICHAS = load.listar(idParam, new Ficha());
        LISTA_FICHAS = listarAllFichas(date1, date2, null, null,null, null);
    }
    
    public static void listarFichasByClient(String rut) {
//        Dao load = new Dao();
//        String idParam = GV.convertFichaIdParamToClient(rut);
//        LISTA_FICHAS = load.listar(idParam, new Ficha());
        LISTA_FICHAS = listarAllFichas(null, null, null, rut,null, null);
    }
    
    public static void listarFichasByUser(String user) {
//        Dao load = new Dao();
//        String idParam = GV.convertFichaIdParamToUSer(user);
//        LISTA_FICHAS = load.listar(idParam, new Ficha());
        LISTA_FICHAS = listarAllFichas(null, null, user, null,null, null);
    }
    
    
    public static void listarFichasByConveny(String idCnv) {
        LISTA_FICHAS = listarAllFichas(null, null, null, null,idCnv, null);
    }
    
    public static Object openFichaByCod(String cod){
        List<Object> lista = listarAllFichas(null, null, null, null,null, cod);
        if(lista.size() > 0){
            return lista.get(0);
        }
        return null;
    }
    
    public static List<Object> getFichasByConveny(int idCnv){
        return listarAllFichas(null, null, null, null,""+idCnv, null);
    }
    
    
    /**
     * retorna una lista de objetos que deben ser parseados a ResF
     * @return 
     */
    public static List<Object> getFichas(){
        return LISTA_FICHAS;
    }

    public static void setSincronizar(boolean value) {
        SINCRONIZAR = value;
    }

    public static boolean sincronizacion() {
        return SINCRONIZAR;
    }
    
    /**
     * Retorna una lista de entidades tipo ficha con todos sus datos sin incluir las anuladas, si userId y clientCod son null,
     * buscara por fecha, 
     * de lo contratrio vlidara un idFicha
     * @param dateFrom
     * @param dateTo
     * @param idUser
     * @param codClient
     * @param idFicha 
     * @return 
     */
    public static List<Object> listarFichas(Date dateTo, Date dateFrom,String idUser, String codClient, String idFicha){
        Dao load = new Dao();
        String idParam = getWhereFromFicha(dateTo, dateFrom, idUser, codClient, idFicha);
        idParam = GV.convertFichaIdToFichaList(idParam);
        return load.listar(idParam, new Ficha());
    }
    
    /**
     * Retorna una lista de entidades tipo ficha con todos sus datos incluyendo las anuladas, si userId y clientCod son null,
     * buscara por fecha, 
     * de lo contratrio vlidara un idFicha
     * @param dateFrom
     * @param dateTo
     * @param idUser
     * @param codClient
     * @param idFicha 
     * @return 
     */
    public static List<Object> listarAllFichas(Date dateTo, Date dateFrom,String idUser, String codClient, String idConvenio, String idFicha){
        Dao load = new Dao();
        String idParam = getWhereFromAllFichas(dateTo, dateFrom, idUser, codClient,idConvenio, idFicha);
        idParam = GV.convertFichaIdToFichaList(idParam);
        return load.listar(idParam, new Ficha());
    }
    
    public static void resetAllDataSource(){
//        if(GV.tipoUserSuperAdmin() && 
//                OptionPane.getConfirmation("Confirmación crítica", 
//                "¿Estas seguro que deseas borrar todos los datos?", 2)){
            GV.loadLastUpdateFromXML();
//            backUpLocalBd();
            GV.setLastUpdate(GV.strToDate("01-01-2001"));
            GlobalValuesBD.dropDB();
            GlobalValuesBD.initDB();
            GV.loadLastUpdateFromXML();
            GlobalValuesBD.sincronizarTodo();
//        }
    }
    
    public static void migrarAllOldData(){
        //todas las fichas tendran id con guion uno, no se deberá utilizar mas
        migrarArmazones();
//        migrarClientes();
//        migrarCristales();
//        migrarDescuentos();
//        migrarDespachos();
//        migrarDoctores();
//        migrarFichas();
//        migrarHistorialPago();
//        migrarInstitucion();
//        migrarLentes();
//        migrarRegistroBajas();
//        migrarUsers();
    }
    
    private static void migrarUsers() {
        List<Object> lista = mig.listar("-2", new User());
        List<Object> lista2 = new ArrayList<>();
        for (Object object : lista) {
            User user = (User)object;
                if(user.getTipo()== 2){
                    user.setTipo(4);
                }
                if(user.getTipo() == 1){
                    user.setTipo(2);
                }
                user.setId(user.getId()+2);
                lista2.add(user);
        }
        String [][] stList = (String[][]) transformList(lista2, new User());
        createExcel(stList, new User());
    }
    
    private static void migrarClientes() {
        List<Object> lista = mig.listar("-2", new Cliente());
        List<Object> lista2 = new ArrayList<>();
        int cont = 0;
        for (Object object : lista) {
            Cliente cli = (Cliente)object;
            cli.setComuna(cli.getComuna().replaceAll("ñ", "n"));
            cli.setCiudad(cli.getCiudad().replaceAll("ñ", "n"));
            cli.setDireccion(cli.getDireccion().replaceAll("ñ", "n"));
            if(cli.getCiudad().equals("Chañarañ")){
                cli.setCiudad("Chanaral");
            }
            cli.setNombre(cli.getNombre().replaceAll("ñ", "n"));
            lista2.add(cli);
            cont++;
        }
        String [][] stList = (String[][]) transformList(lista2, new Cliente());
        createExcel(stList, new Cliente());
    }
    
    private static void migrarCristales() {
        int maxId = 1;
        List<Object> lista = mig.listar("-2", new Cristal());
        List<Object> lista2 = new ArrayList<>();
        int cont = 0;
        for (Object object : lista) {
            Cristal cri = (Cristal)object;
            if(cri.getId() != 18){
                cri.setId(maxId);
                lista2.add(cri);
                maxId++;
            }
            cont++;
        }
        String [][] stList = (String[][]) transformList(lista2, new Cristal());
        createExcel(stList, new Cristal());
    }
    
    private static void migrarDescuentos() {
        int maxId = 1;
        List<Object> lista = mig.listar("-2", new Descuento());
        List<Object> lista2 = new ArrayList<>();
        for (Object object : lista) {
            Descuento des = (Descuento)object;
                des.setId(maxId);
                lista2.add(des);
                maxId++;
        }
        String [][] stList = (String[][]) transformList(lista2, new Descuento());
        createExcel(stList, new Descuento());
    }
    
    private static void migrarArmazones() {
        List<Object> lista = mig.listar("-2", new Armazon());
        for (Object object : lista) {
            Armazon ar = (Armazon)object;
            ar.setPrecioCristal(mig.getPrecioCristal(ar.getCristal()));
            ar.setPrecioMarca(mig.getPrecioMarca(ar.getMarca()));
        }
        String [][] stList = (String[][]) transformList(lista, new Armazon());
        createExcel(stList, new Armazon());
    }
    

    private static void migrarDespachos() {
        List<Object> lista = mig.listar("-2", new Despacho());
        List<Object> lista2 = new ArrayList<>();
        for (Object object : lista) {
            Despacho temp = (Despacho)object;
            temp.setNombre(temp.getNombre().replaceAll("ñ", "n").replaceAll("Ñ", "N"));
            lista2.add(temp);
        }
        String [][] stList = (String[][]) transformList(lista2, new Despacho());
        createExcel(stList, new Despacho());
    }

    private static void migrarDoctores() {
        List<Object> lista = mig.listar("-2", new Doctor());
        for (Object object : lista) {
            Doctor doc = (Doctor)object;
        }
        String [][] stList = (String[][]) transformList(lista, new Doctor());
        createExcel(stList, new Doctor());
    }

    private static void migrarHistorialPago() {
        List<Object> lista = mig.listar("-2", new HistorialPago());
        for (Object object : lista) {
            HistorialPago temp = (HistorialPago)object;
            if(temp.getIdTipoPago() == 0){
                temp.setIdTipoPago(2);
            }else{
                temp.setIdTipoPago(temp.getIdTipoPago()+1);
            }
        }
        String [][] stList = (String[][]) transformList(lista, new HistorialPago());
        createExcel(stList, new HistorialPago());
    }

    private static void migrarInstitucion() {
        List<Object> lista = mig.listar("-2", new Institucion());
        String [][] stList = (String[][]) transformList(lista, new Institucion());
        createExcel(stList, new Institucion());
    }

    public static void migrarLentes() {
        List<Object> lista = mig.listar("-2", new Lente());
//        for (Object object : lista) {
//            Lente temp = (Lente)object;
//            temp.setCod(temp.getCod()+"-"+temp.getMarca()+"-"+temp.getColor());
//        }
        String [][] stList = (String[][]) transformList(lista, new Lente());
        createExcel(stList, new Lente());
    }

    private static void migrarRegistroBajas() {
        List<Object> lista = mig.listar("-2", new RegistroBaja());
        String [][] stList = (String[][]) transformList(lista, new RegistroBaja());
        createExcel(stList, new RegistroBaja());
    }

    private static void migrarFichas() {
        List<Object> lista = mig.listar("-2", new EtiquetFicha());
        for (Object object : lista) {
            EtiquetFicha temp = (EtiquetFicha)object;
            if(temp.getIdDespacho().equals("0")){
                temp.setIdDespacho("");
            }else{
                temp.setIdDespacho(temp.getIdDespacho()+"-1");
            }
            if(temp.getRutDoctor().equals("null"))
                temp.setRutDoctor("");
            if(temp.getIdInstitucion().equals("0"))
                temp.setIdInstitucion("");
            temp.setLugarEntrega(temp.getLugarEntrega().replaceAll("Ñ", "N").replaceAll("ñ", "n"));
            temp.setObservacion(temp.getObservacion().replaceAll("Ñ", "N").replaceAll("ñ", "n"));
            temp.setDescuento(mig.getPrecioDescuento(temp.getDescuento(), temp.getValorTotal()));
        }
        String [][] stList = (String[][]) transformList(lista, new EtiquetFicha());
        createExcel(stList, new Ficha());
    }
    
    private static boolean createExcel(Object[][] res, Object type){
        if(res == null || res.length < 1){
            return false;
        }
        if(res.length > 0){
            try {
                GlobalValuesSaveXls.generarExcelRespaldo((String [][])res, GlobalValuesBD.nameObjeto(type));
            } catch (WriteException ex) {
                Logger.getLogger(GlobalValuesBD.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        }
        return false;
    }
    
    public static Object[][] transformList(List<Object> lista, Object type){
        if(type instanceof EtiquetFicha){
            int columnas = 18;
            String[][] stList = new String[lista.size()][columnas];
            int i=0;
            for (Object object : lista) {
                EtiquetFicha temp = (EtiquetFicha)object;
                stList[i][0]=""+temp.getCod();
                stList[i][1]=getSqlDate(temp.getFecha());
                stList[i][2]=getSqlDate(temp.getFechaEntrega());
                stList[i][3]=""+temp.getLugarEntrega();
                stList[i][4]=""+temp.getHoraEntrega();
                stList[i][5]=""+temp.getObservacion().replaceAll("\n", "").replaceAll("\"", "");
                stList[i][6]=""+temp.getValorTotal();
                stList[i][7]=""+temp.getDescuento();
                stList[i][8]=""+temp.getSaldo();
                stList[i][9]=""+temp.getRutCliente();
                stList[i][10]=""+temp.getRutDoctor();
                stList[i][11]=""+temp.getIdInstitucion();
                stList[i][12]=""+temp.getIdDespacho();
                stList[i][13]=""+temp.getIdUser();
                stList[i][14]=""+temp.getIdConvenio();
                stList[i][15]=""+temp.getEstado();
                stList[i][16]=""+getSqlDate(temp.getLastUpdate());
                stList[i][17]=""+temp.getLastHour();
                i++;
            }
            return stList;
        }
        if(type instanceof Armazon){
            int columnas = 21;
            String[][] stList = new String[lista.size()][columnas];
            int i=0;
            for (Object object : lista) {
                Armazon temp = (Armazon)object;
                stList[i][0]=""+temp.getCod();
                stList[i][1]=""+temp.getTipo();
                stList[i][2]=""+temp.getMarca();
                stList[i][3]=""+temp.getPrecioMarca();
                stList[i][4]=""+temp.getCristal();
                stList[i][5]=""+temp.getPrecioCristal();
                stList[i][6]=""+temp.getAdd();
                stList[i][7]=""+temp.getOdA();
                stList[i][8]=""+temp.getOdEsf();
                stList[i][9]=""+temp.getOdCil();
                stList[i][10]=""+temp.getOiA();
                stList[i][11]=""+temp.getOiEsf();
                stList[i][12]=""+temp.getOiCil();
                stList[i][13]=""+temp.getDp();
                stList[i][14]=""+temp.getEndurecido();
                stList[i][15]=""+temp.getCapa();
                stList[i][16]=""+temp.getPlusMax();
                stList[i][17]=""+temp.getIdFicha();
                stList[i][18]=""+temp.getEstado();
                stList[i][19]=""+getSqlDate(temp.getLastUpdate());
                stList[i][20]=""+temp.getLastHour();
                i++;
            }
            return stList;
        }
        if(type instanceof User){
            int columnas = 9;
            String[][] stList = new String[lista.size()][columnas];
            int i=0;
            for (Object object : lista) {
                User temp = (User)object;
                stList[i][0]=""+temp.getId();
                stList[i][1]=""+temp.getNombre();
                stList[i][2]=""+temp.getUsername();
                stList[i][3]=""+temp.getEmail();
                stList[i][4]=""+temp.getPass();
                stList[i][5]=""+temp.getTipo();
                stList[i][6]=""+temp.getEstado();
                stList[i][7]=""+getSqlDate(temp.getLastUpdate());
                stList[i][8]=""+temp.getLastHour();
                i++;
            }
            return stList;
        }
        if(type instanceof Cliente){
            int columnas = 13;
            String[][] stList = new String[lista.size()][columnas];
            int i=0;
            for (Object object : lista) {
                Cliente temp = (Cliente)object;
                stList[i][0]=""+temp.getCod();
                stList[i][1]=""+temp.getNombre();
                stList[i][2]=""+temp.getTelefono1();
                stList[i][3]=""+temp.getTelefono2();
                stList[i][4]=""+temp.getEmail();
                stList[i][5]=""+temp.getDireccion();
                stList[i][6]=""+temp.getComuna();
                stList[i][7]=""+temp.getCiudad();
                stList[i][8]=""+temp.getSexo();
                stList[i][9]=""+getSqlDate(temp.getNacimiento());
                stList[i][10]=""+temp.getEstado();
                stList[i][11]=""+getSqlDate(temp.getLastUpdate());
                stList[i][12]=""+temp.getLastHour();
                i++;
            }
            return stList;
        }
        if(type instanceof Cristal){
            int columnas = 6;
            String[][] stList = new String[lista.size()][columnas];
            int i=0;
            for (Object object : lista) {
                Cristal temp = (Cristal)object;
                stList[i][0]=""+temp.getId();
                stList[i][1]=""+temp.getNombre();
                stList[i][2]=""+temp.getPrecio();
                stList[i][3]=""+temp.getEstado();
                stList[i][4]=""+getSqlDate(temp.getLastUpdate());
                stList[i][5]=""+temp.getLastHour();   
                i++;
            }
            return stList;
        }
        if(type instanceof Descuento){
            int columnas = 8;
            String[][] stList = new String[lista.size()][columnas];
            int i=0;
            for (Object object : lista) {
                Descuento temp = (Descuento)object;
                stList[i][0]=""+temp.getId();
                stList[i][1]=""+temp.getNombre();
                stList[i][2]=""+temp.getDescripcion();
                stList[i][3]=""+temp.getPorcetange();
                stList[i][4]=""+temp.getMonto();
                stList[i][5]=""+temp.getEstado();
                stList[i][6]=""+getSqlDate(temp.getLastUpdate());
                stList[i][7]=""+temp.getLastHour();
                i++;
            }
            return stList;
        }
        if(type instanceof Despacho){
            int columnas = 8;
            String[][] stList = new String[lista.size()][columnas];
            int i=0;
            for (Object object : lista) {
                Despacho temp = (Despacho)object;
                stList[i][0]=""+temp.getCod();
                stList[i][1]=""+temp.getRut();
                stList[i][2]=GV.getStToName(temp.getNombre());
                stList[i][3]=getSqlDate(temp.getFecha());
                stList[i][4]=""+temp.getIdFicha();
                stList[i][5]=""+temp.getEstado();
                stList[i][6]=""+getSqlDate(temp.getLastUpdate());
                stList[i][7]=""+temp.getLastHour();
                i++;
            }
            return stList;
        }
        if(type instanceof Doctor){
            int columnas = 7;
            String[][] stList = new String[lista.size()][columnas];
            int i=0;
            for (Object object : lista) {
                Doctor temp = (Doctor)object;
                stList[i][0]=""+temp.getCod();
                stList[i][1]=""+temp.getNombre();
                stList[i][2]=""+temp.getTelefono();
                stList[i][3]=""+temp.getEmail();
                stList[i][4]=""+temp.getEstado();
                stList[i][5]=""+getSqlDate(temp.getLastUpdate());
                stList[i][6]=""+temp.getLastHour();
                i++;
            }
            return stList;
        }
        if(type instanceof HistorialPago){
            int columnas = 8;
            String[][] stList = new String[lista.size()][columnas];
            int i=0;
            for (Object object : lista) {
                HistorialPago temp = (HistorialPago)object;
                stList[i][0]=""+temp.getCod();
                stList[i][1]=getSqlDate(temp.getFecha());
                stList[i][2]=""+temp.getAbono();
                stList[i][3]=""+temp.getIdTipoPago();
                stList[i][4]=""+temp.getIdFicha();
                stList[i][5]=""+temp.getEstado();
                stList[i][6]=""+getSqlDate(temp.getLastUpdate());
                stList[i][7]=""+temp.getLastHour();
                i++;
            }
            return stList;
        }
        if(type instanceof Institucion){
            int columnas = 11;
            String[][] stList = new String[lista.size()][columnas];
            int i=0;
            for (Object object : lista) {
                Institucion temp = (Institucion)object;
                stList[i][0]=""+temp.getCod();
                stList[i][1]=""+temp.getNombre();
                stList[i][2]=""+temp.getTelefono();
                stList[i][3]=""+temp.getEmail();
                stList[i][4]=""+temp.getWeb();
                stList[i][5]=""+temp.getDireccion();
                stList[i][6]=""+temp.getComuna();
                stList[i][7]=""+temp.getCiudad();
                stList[i][8]=""+temp.getEstado();
                stList[i][9]=""+getSqlDate(temp.getLastUpdate());
                stList[i][10]=""+temp.getLastHour();
                i++;
            }
            return stList;
        }
        if(type instanceof Lente){
            int columnas = 16;
            String[][] stList = new String[lista.size()][columnas];
            int i=0;
            for (Object object : lista) {
                Lente temp = (Lente)object;
                stList[i][0]=""+temp.getCod();
                stList[i][1]=""+temp.getColor();
                stList[i][2]=""+temp.getTipo();
                stList[i][3]=""+temp.getMarca();
                stList[i][4]=""+temp.getMaterial();
                stList[i][5]=""+temp.getFlex();
                stList[i][6]=""+temp.getClasificacion();
                stList[i][7]=""+temp.getDescripcion();
                stList[i][8]=""+temp.getPrecioRef();
                stList[i][9]=""+temp.getPrecioAct();
                stList[i][10]=""+temp.getStock();
                stList[i][11]=""+temp.getStockMin();
                stList[i][12]=""+temp.getInventario();
                stList[i][13]=""+temp.getEstado();
                stList[i][14]=""+getSqlDate(temp.getLastUpdate());
                stList[i][15]=""+temp.getLastHour();
                i++;
            }
            return stList;
        }
        if(type instanceof RegistroBaja){
            int columnas = 8;
            String[][] stList = new String[lista.size()][columnas];
            int i=0;
            for (Object object : lista) {
                RegistroBaja temp = (RegistroBaja)object;
                stList[i][0]=""+temp.getCod();
                stList[i][1]=getSqlDate(temp.getFecha());
                stList[i][2]=""+temp.getIdLente();
                stList[i][3]=""+temp.getCantidad();
                stList[i][4]=""+temp.getObs();
                stList[i][5]=""+temp.getEstado();
                stList[i][6]=""+getSqlDate(temp.getLastUpdate());
                stList[i][7]=""+temp.getLastHour();
                i++;
            }
            return stList;
        }
        return null;
            
    }
    
    private static String getSqlDate(Date date){
        return GV.dateToString(date, "yyyy-mm-dd");
    }
}
