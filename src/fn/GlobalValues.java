/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn;

import entities.Ficha;
import entities.Lente;
import entities.User;
import fn.close.Close;
import static fn.xml.ReadXml.readLocalXml;
import static fn.xml.ReadXml.readLocalLastUpdateXml;
import static fn.xml.ReadXml.readRemoteLastUpdateXmlAndCreateValidateXml;
import fn.xml.SaveXml;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
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
    public static String BD_URL_REMOTE = "localhost";
    public static String BD_NAME_REMOTE = "localhost/odmbd";
    public static String BD_USER_REMOTE = "root";
    public static String BD_PASS_REMOTE = "";
    public static String BD_URL_LOCAL = "localhost:1527";
    public static String BD_NAME_LOCAL = "odm4";
    public static String BD_USER_LOCAL = "odm4";
    public static String BD_PASS_LOCAL = "odm4";
    
    /* Seguridad */
    public static String SALT = "optidataodm4softdirex";
    public static String PASS;
    
    /* LICENCIA */
    public static boolean LICENCE = true;
    public static String EXP_DATE = "00-00-0000";
    public static String API_URI;
    public static String LOCAL_ID;
    
    /* Update */
    public static int ID_UPDATE=0;
    public static String URL_UPDATE;
    
    /* Mail */
    public static String MAIL_ADDRES = "qwpzedzqucvpyjzt";
    public static String MAIL_PASS= "sdx.respaldo.bd@gmail.com";
    
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
    //LISTAS TEMPORALES
    public static ArrayList<Ficha> TMP_LIST_FICHAS = new ArrayList<Ficha>();
    public static ArrayList<Lente> TMP_LIST_LENTES = new ArrayList<Lente>();
    public static ArrayList<User> TMP_LIST_USERS = new ArrayList<User>();
    
    /* Joption Pane del sistema */
    public static OPanel INFOPANEL = new OPanel();
    public static String PANELTITLE ="";
    
    
    
    
    public static void initValues(){
        System.out.println("GLOBALVALUES:initValues()");
        LOCAL_PATH = System.getProperty("user.dir")+File.separator;
        FILES_PATH = LOCAL_PATH+"files"+File.separator;
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
    

}
