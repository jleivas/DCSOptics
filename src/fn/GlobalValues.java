/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn;

import entities.Cliente;
import entities.Cristal;
import entities.Descuento;
import entities.Doctor;
import entities.Institucion;
import entities.Lente;
import entities.Oficina;
import entities.RegistroBaja;
import entities.TipoPago;
import entities.User;
import fn.close.Close;
import static fn.xml.ReadXml.readLocalXml;
import static fn.xml.ReadXml.readLocalLastUpdateXml;
import static fn.xml.ReadXml.readRemoteLastUpdateXmlAndCreateValidateXml;
import fn.xml.SaveXml;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import newpackage.NoGit;
import sync.Cmp;
import sync.entities.Global;
import sync.entities.Local;
import sync.entities.Remote;
import view.OPanel;

/**
 *
 * @author sdx
 */
public class GlobalValues {
    /*  Nombres de sistema  */
    public static String PROJECTNAME="";
    public static String VERSION = "4.0.0";
    
    /* Bases de datos*/
    public static String BD_URL_REMOTE = NoGit.URL;
    public static String BD_NAME_REMOTE = NoGit.DB;
    public static String BD_USER_REMOTE = NoGit.USER;
    public static String BD_PASS_REMOTE = NoGit.PASS;
    public static String BD_URL_LOCAL = "localhost:1527";
    public static String BD_NAME_LOCAL = "odm4";
    public static String BD_USER_LOCAL = "odm4";
    public static String BD_PASS_LOCAL = "odm4";
    
    /* Seguridad */
    public static String SALT = "optidataodm4softdirex";
    public static String PASS;
    
    /* LICENCIA */
    public static String COMPANY_NAME;
    public static boolean LICENCE = true;
    public static String EXP_DATE = "00-00-0000";
    public static String API_URI;
    public static String LOCAL_ID;
    
    /* Update */
    public static int ID_UPDATE=0;
    public static String URL_UPDATE;
    
    /* Mail */
    public static String MAIL_ADDRES = "sdx.respaldo.bd@gmail.com";
    public static String MAIL_PASS= "qwpzedzqucvpyjzt";
    public static String MAIL_REPORT= "softdirex@gmail.com";
    public static String MAIL_LOG = "";
    
    /* Direcciones de fichero*/
    public static String FILES_PATH = "";
    public static String LOCAL_PATH = "";
    
    /* Variables del sistema */
    public static String USERNAME = "";
    public static int ID_USER = 0;
    public static String TMP_RUT_DOCTOR;
    public static int TMP_ID_INSTITUCION;
    public static int TMP_ID_DESCUENTO;
    public static Date TMP_DATE_FROM = null;
    public static Date TMP_DATE_TO =null;
    /*  Sincronizacion */
    public static Global GLOBAL_SYNC = new Global();
    public static Local LOCAL_SYNC = new Local();
    public static Remote REMOTE_SYNC = new Remote();
    public static boolean IS_ONLINE = false;
    public static Date LAST_UPDATE;
    
    //LISTAS TEMPORALES
    public static ArrayList<User> TMP_LIST_USERS = new ArrayList<User>();
    public static ArrayList<Cristal> TMP_LIST_CRISTAL = new ArrayList<Cristal>();
    public static ArrayList<Descuento> TMP_LIST_DESCUENTO = new ArrayList<Descuento>();
    public static ArrayList<Cliente> TMP_LIST_CLIENTES = new ArrayList<Cliente>();
    public static ArrayList<Doctor> TMP_LIST_DOCTORES = new ArrayList<Doctor>();
    public static ArrayList<Institucion> TMP_LIST_INSTITUCIONES = new ArrayList<Institucion>();
    public static ArrayList<Lente> TMP_LIST_LENTES = new ArrayList<Lente>();
    public static ArrayList<Oficina> TMP_LIST_OFICINAS = new ArrayList<Oficina>();
    public static ArrayList<RegistroBaja> TMP_LIST_REGISTROS_BAJAS = new ArrayList<RegistroBaja>();
    public static ArrayList<TipoPago> TMP_LIST_TIPOS_PAGO = new ArrayList<TipoPago>();
    
    /* Joption Pane del sistema */
    /* Joption Pane del sistema */
    public static OPanel INFOPANEL = new OPanel();
    public static String PANELTITLE ="";
    public static int MSG_STATUS;
    public static String ICON_INFO = "/icons/show_info_50px.png";
    public static String ICON_WARN = "/icons/show_warning_50px.png";
    public static String ICON_ERROR = "/icons/show_error_50px.png";
    
    
    
    
    public static void initValues(){
        System.out.println("GLOBALVALUES:initValues()");
        LOCAL_PATH = System.getProperty("user.dir")+File.separator;
        FILES_PATH = LOCAL_PATH+"files"+File.separator;
        SubProcess.isOnline();
        loadLastUpdateFromXML();//cargar LAST_UPDATE de fichero xml al iniciar programa
        try{
            setProjectName();
            readLocalXml();
            CalibracionGlobal.cargarCalibracion();
            if(LICENCE){
                readLocalLastUpdateXml();
                if(ConectionValidator.isConnected()){
                    if(readRemoteLastUpdateXmlAndCreateValidateXml()){
                        Close.close(0);
                    }else{
                        OptionPane.showErrorMessage("Los datos ingresados no son válidos", "Error de licencia");
                        createLocalXml();
                        initValues();
                    }
                }
            }
        }catch(Exception e){
            createLocalXml();
            initValues();
        }
    }
    
    private static void createLocalXml(){
        System.out.println("GLOBALVALUES:createLocalXml(String licence,String uri,String port)");
        
        LOCAL_ID = OptionPane.getUserLocalId();
        API_URI = OptionPane.getUserUri();
        PASS = OptionPane.getUserPort();
        
        SaveXml.createLocal();
    }

    private static void setProjectName() {
        int pos = System.getProperty("user.dir").lastIndexOf(File.separator);
        PROJECTNAME = System.getProperty("user.dir").substring(pos+1);
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
    
    public static String getMailSystemName() {
        return MAIL_ADDRES;
    }
    
    public static String getMailSystemPass() {
        return MAIL_PASS;
    }
    
    
    public static boolean isOnline(){
        return IS_ONLINE;
    }

    public static String getEqId() throws UnknownHostException{
        return thisPcAddress()+"/"+thisPcName();
    }
    
    private static String thisPcAddress() throws UnknownHostException{
        InetAddress localHost = InetAddress.getLocalHost();
        return localHost.getHostAddress();
    }
    
    private static String thisPcName() throws UnknownHostException{
        InetAddress localHost = InetAddress.getLocalHost();
        return localHost.getHostName();
    }
    private static void loadLastUpdateFromXML() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Obtiene el numero del equipo desde la licencia actual para insertar en base de datos
     * Ejemplo: "1-7000" donde 1 es el número del equipo y 7000 es el id correlativo
     * @return inicio del Id para asignarlo a la respectiva clase
     */
    public static String getIndexId(){
        try{
            int index = Integer.parseInt(LOCAL_ID.substring((LOCAL_ID.length())-1));
            return index+"-";
        }catch(NumberFormatException ex){
            OptionPane.showMsg("Error Fatal", "Se ha detectado un error fatal en su sistema,"
                    + "\nsi continúa insertando registros puede dañar todos los datos de su empresa."
                    + "\nLe recomendamos contactarse con su proveedor de software inmediatamente."
                    + "\nPuede estar siendo víctima de una adulteración de licencia.", JOptionPane.ERROR_MESSAGE);
        }
        return "Error-";
    }
    
    public static String getPublicIp() throws IOException{
        return Cmp.getPublicIp();
    }
    
    public static int contChar(char toCompare, String toCount){
        char[] arrayChar = toCount.toCharArray();
        int cont=0;
        for(int i=0; i<arrayChar.length; i++){

                if( arrayChar[i] == toCompare){
                    cont++;
                }
        }
        return cont;
    }
    
    public static int hourToInt(Date date){
        DateFormat hourFormat = new SimpleDateFormat("HHmmss");
        String formatHour = hourFormat.format(date);
        int hora = Integer.parseInt(formatHour);
        return hora;
    }

}
