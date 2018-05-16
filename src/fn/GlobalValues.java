/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn;

import entities.Ficha;
import entities.Lente;
import entities.User;
import java.util.ArrayList;
import java.util.Date;
import view.OPanel;

/**
 *
 * @author sdx
 */
public class GlobalValues {
    public static String VERSION = "4.0.0";
    public static String USERNAME = "";
    public static String TITLE = "";
    public static int ID_USER = 0;
    public static boolean LICENCIA = true;
    public static String EXP_DATE = "";
    public static String API_URI;
    public static String LOCAL_ID;
    public static String PASS;
    public static int ID_UPDATE=0;
    public static String URL_UPDATE;
    public static String TMP_RUT_DOCTOR;
    public static int TMP_ID_INSTITUCION;
    public static int TMP_ID_DESCUENTO;
    public static Date TMP_DATE_FROM = null;
    public static Date TMP_DATE_TO =null;
    public static OPanel INFOPANEL = new OPanel();
    public static String PANELTITLE ="";
    //LISTAS TEMPORALES
    public static ArrayList<Ficha> TMP_LIST_FICHAS = new ArrayList<Ficha>();
    public static ArrayList<Lente> TMP_LIST_LENTES = new ArrayList<Lente>();
    public static ArrayList<User> TMP_LIST_USERS = new ArrayList<User>();
    
    //BD REMOTA
    private static String BD_USER="root";
    private static String BD_PASS="20075321818";
    private static String BD_URL="localhost";
    private static String BD_NAME="odmbd";

    public static String getBdUser() {
        return BD_USER;
    }

    public static String getBdPass() {
        return BD_PASS;
    }

    public static String getBdUrl() {
        return BD_URL;
    }

    public static String getBdName() {
        return BD_NAME;
    }

}
