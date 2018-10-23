/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn.globalValues;

import bd.LcBd;
import dao.Dao;
import entities.Convenio;
import entities.Equipo;
import entities.context.SalesReportFicha;
import fn.GV;
import static fn.GV.dateToString;
import static fn.GV.getStr;
import static fn.GV.getToName;
import fn.OptionPane;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jlleivas
 */
public class GlobalValuesVariables {
    
    /*  Nombres de sistema  */
    private static String PROJECTNAME="DCS Optics";
    private static String VERSION = "v4.0.0";
    private static String EQUIPO;//el nombre debe concatenarse con la fecha de instalacion
    private static int EQUIPO_ID = 1;
    private static String INVENTARIO_NAME;
    private static int ID_INVENTARIO_SELECCIONADO = 0;
    private static String COMPANY_DESCRIPTION;
    private static String COMPANY_RUT;
    private static String COMPANY_GIRO;
    private static String MESSAGE_FILE;//texto informativo que se imprimirá en la ficha
    
    /* Seguridad */
    private static String SALT = noGit.NG.SALT;//favorite pet
    private static String PASS;
    
    /* LICENCIA */
    private static String COMPANY_NAME;
    private static int TIPO_PLAN = 0;
    private static String LICENCE_CODE = null;
    private static boolean LICENCE_ACTIVE = false;
    private static String EXP_DATE;
    private static String API_URI;
    private static int SYNC_COUNT = 0;
    
    //TIPO DE PLAN
    private static int TP_FREE=0;
    private static int TP_LOCAL = 1;
    private static int TP_2X = 2;
    private static int TP_4X = 3;
    private static int TP_6X = 4;
    private static int TP_FULL_DATA = 5;
    
    
    //MAXIMO DE SINCRONIZACIONES SEGUN TIPO DE PLAN
    public static int TP_2X_MS = 2;
    public static int TP_4X_MS = 4;
    public static int TP_6X_MS = 6;
    
    /* Update */
    private static int ID_UPDATE=0;
    private static String PORT_KEY;
    
    
    /* Variables del sistema */
    
    private static String USERNAME;
    private static int ID_USER = 0;
    private static Date TMP_DATE_FROM = null;
    private static Date TMP_DATE_TO =null;
    private static String ID_PARAM_IS_USER = "USER/";
    private static String ID_PARAM_IS_CLIENT = "CLIENT/";
    private static String ID_PARAM_IS_TABLE_LIST = "LIST/";
    private static String ID_PARAM_IS_DATE_LIST = "DATE/";
    private static String ID_PARAM_IS_FICHA_LIST = "LISTAR_FICHAS/";
    private static int CBO_FICHA_FILTER=0;
    private static String SQL_LOW_STOCK="lowStock";
    private static SalesReportFicha SALESREPORT = new SalesReportFicha();
    private static Convenio CONVENIO_SELECTED = null;
    private static String CUOTAS_FECHA_PAGO_PENDIENTE = "01-01-2001";
    private static String FECHA_DEFAULT = "01-01-2001";
    private static String RECEPTOR_NAME = null;
    private static String RECEPTOR_DIR = null;
    private static String RECEPTOR_CT1 = null;
    private static String RECEPTOR_CT2 = null;
    private static String RUT_CLIENT_SELECTED="";
    private static String ID_USER_SELECTED="";
    private static String ID_CONVENIO_SELECTED="";
    //used in filterList()
    private static List<String> FILTER_LIST = new ArrayList<>();
    
    /*
        Modificaciones tambien afectan en las consultas a bases de datos
    */
    private static final int DELETED = 0;
    private static final int PENDING = 1;
    private static final int PAID = 2;
    private static final int DELIVERED = 3;
    private static final int WARRANTY = 4;
    
    private static List<String> filterList(){
        FILTER_LIST.clear();
        FILTER_LIST.add("<");
        FILTER_LIST.add(">");
        FILTER_LIST.add(ID_PARAM_IS_USER);
        FILTER_LIST.add(ID_PARAM_IS_CLIENT);
        FILTER_LIST.add(ID_PARAM_IS_TABLE_LIST);
        FILTER_LIST.add(ID_PARAM_IS_DATE_LIST);
        return FILTER_LIST;
    }
    
    public static String getCompanyDescription(){
        return getStr(COMPANY_DESCRIPTION);
    }
    
    public static void setCompanyDescription(String companyDescription){
        COMPANY_DESCRIPTION = companyDescription;
    }
    
    public static String getCompanyRut(){
        return getStr(COMPANY_RUT);
    }
    
    public static void setCompanyRut(String companyRut){
        COMPANY_RUT = companyRut;
    }
    
    public static String getCompanyGiro(){
        return getStr(COMPANY_GIRO);
    }
    
    public static void setCompanyGiro(String companyGiro){
        COMPANY_GIRO = companyGiro;
    }
    
    public static String getMessageFile(){
        return getStr(MESSAGE_FILE);
    }
    
    public static void setMessageFile(String messageFile){
        MESSAGE_FILE = messageFile;
    }
    
    public static void setInventarioLocal(String inventario){
        INVENTARIO_NAME = getStr(inventario);
    }
    
    public static int estadoFichaDeleted(){
        return DELETED;
    }
    
    public static int estadoFichaPending(){
        return PENDING;
    }
    
    public static int estadoFichaPaid(){
        return PAID;
    }
    
    public static int estadoFichaDelivered(){
        return DELIVERED;
    }
    
    public static int estadoFichaWarranty(){
        return WARRANTY;
    }
    
    public static void setCompanyName(String nombre) {
        COMPANY_NAME = getToName(nombre);
    }
    
    public static String getCompanyName(){
        String value = (getStr(COMPANY_NAME).isEmpty())?"[Empresa no ingresada]":getStr(COMPANY_NAME);
        return value;
    }

    public static String getProjectName() {
        return getStr(PROJECTNAME);
    }
    
    public static String getVersion(){
        return getStr(VERSION);
    }
    
    public static int getLicenciaTipoPlan(){
        return TIPO_PLAN;
    }
    
    public static String getLicenceCode(){
        return GV.getStr(LICENCE_CODE);
    }
    
    public static String getExpDate(){
        return getStr(EXP_DATE);
    }

    public static String getInventarioName() {
        return getStr(INVENTARIO_NAME);
    }
    
    public static Date getDateFrom(){
        return TMP_DATE_FROM;
    }
    
    public static void setDateFrom(Date date){
        TMP_DATE_FROM = date;
    }
    
    public static Date getDateTo(){
        return TMP_DATE_TO;
    }
    
    public static void setDateTo(Date date){
        TMP_DATE_TO = date;
    }
    
    public static String getSalt(){
        return getStr(SALT);
    }
    
    public static String getEquipo(){
        return getToName(EQUIPO);
    }
    
    public static void setCurrentEquipo(String equipo){
        try {
            LcBd.cerrar();
            EQUIPO = getToName(equipo);
            
            if(LICENCE_CODE != null){
                Dao load = new Dao();
                Equipo e = (Equipo)load.get(EQUIPO, 0, new Equipo());
                //Si ya existe un equipo con la misma licencia se cierra el programa
                if(e == null || !e.getNombre().equals(EQUIPO)){
                    if(e != null){
                        if(e.getEstado() != 0){
                            OptionPane.showMsg("Licencia duplicada", "Esta licencia ya se encuentra asociada y vigente.\n"
                                    + "Solicite una nueva licencia para este equipo.\n"
                                    + "Conflicto con equipo: "+e.getNombre(), 3);
                            GlobalValuesXmlFiles.deleteXmlFiles();
                            try {
                                Thread.sleep(10000);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(GlobalValuesVariables.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            System.exit(0);
                        }
                    }
                    e = new Equipo(0, EQUIPO, LICENCE_CODE,
                        GV.enC(GlobalValuesBD.BD_NAME_REMOTE),
                        GV.enC(GlobalValuesBD.BD_USER_REMOTE),
                        GV.enC(GlobalValuesBD.BD_PASS_REMOTE),
                        GV.enC(GlobalValuesBD.BD_URL_REMOTE),
                        1, null, 0);
                    load.add(e);
                }else{
                    GlobalValuesBD.BD_NAME_REMOTE = GV.dsC(e.getBd());
                    GlobalValuesBD.BD_USER_REMOTE = GV.dsC(e.getBdUser());
                    GlobalValuesBD.BD_PASS_REMOTE = GV.dsC(e.getBdPass());
                    GlobalValuesBD.BD_URL_REMOTE  = GV.dsC(e.getBdUrl());
                }
            }
        } catch (InstantiationException | IllegalAccessException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(GlobalValuesVariables.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void setEquipo(String equipo){
        EQUIPO = getStr(equipo)+"_"+dateToString(new Date(),"yyyymmddss");
    }

    public static void setLicenciaTipoPlan(int value) {
        TIPO_PLAN = value;
    }
    
    public static void setLicenceCode(String licenceCode){
       LICENCE_CODE = getStr(licenceCode);
    }
    
    public static String apiUriLicence(){
        return API_URI;
    }
    
    public static String urlUriPort(){
        return PORT_KEY;
    }

    public static void setUserName(String userName) {
        USERNAME = getStr(userName);
    }

    public static String getUserName() {
        return getStr(USERNAME);
    }

    public static void setExpDate(String date) {
        EXP_DATE = getStr(date);
    }

    public static void setApiUriLicence(String uri) {
        API_URI = getStr(uri);
    }
    
    public static void setApiUriPort(String port) {
        PORT_KEY = getStr(port);
    }
    
    public static boolean fichaIdParamIsIdFicha(String arg){
        List<String> filter = filterList();
        for (String str : filter) {
            if(arg.contains(str)){
                return false;
            }
        }
        return true;
    }
    
    public static boolean fichaIdParamIsUser(String arg){
        return (GV.getStr(arg).startsWith(ID_PARAM_IS_USER)) ? true:false;
    }
    
    public static boolean fichaIdParamIsClient(String arg){
        return (GV.getStr(arg).startsWith(ID_PARAM_IS_CLIENT)) ? true:false;
    }
    
    public static boolean fichaIdParamIsTableList(String arg) {
        return (GV.getStr(arg).startsWith(ID_PARAM_IS_TABLE_LIST)) ? true:false;
    }
    
    
    public static boolean fichaIdParamIsFichaList(String arg) {
        return GV.getStr(arg).startsWith(ID_PARAM_IS_FICHA_LIST);
    }
    
    public static boolean fichaIdParamIsDateList(String arg) {
        return (GV.getStr(arg).startsWith(ID_PARAM_IS_DATE_LIST)) ? true:false;
    }
    
    public static String convertFichaIdParamToFichaList(String arg){
        return ID_PARAM_IS_FICHA_LIST+arg;
    }
    
    public static String convertFichaIdParamToUSer(String arg){
        return ID_PARAM_IS_USER+cleanIdParam(arg);
    }
    
    public static String convertFichaIdParamToClient(String arg){
        return ID_PARAM_IS_CLIENT+cleanIdParam(arg);
    }
    
    public static String convertFichaIdParamToTableList(String arg){
        return ID_PARAM_IS_TABLE_LIST+cleanIdParam(arg);
    }
    
    public static String convertFichaIdParamToListAbonos(String arg) {
        return "<"+GV.getStr(arg)+">";
    }
    
    public static String convertFichaIdParamToDateList(String arg) {
        return ID_PARAM_IS_DATE_LIST+cleanIdParam(arg);
    }
    
    public static int cboFichasFilter(){
        return CBO_FICHA_FILTER;
    }
    
    public static void setCboFichasFilter(int filter){
        CBO_FICHA_FILTER = filter;
    }
    
    public static String cleanIdParam(String arg){
        if(fichaIdParamIsFichaList(arg)){
        /*
            No se agrega diecto al filterList porque en una validacion del Local.java
            debe pasar inadvertido para listar entidades de tipo Ficha
        */
            return GV.getStr(arg).replaceAll(ID_PARAM_IS_FICHA_LIST, "");
        }
        List<String> filter = filterList();
        for (String clean : filter) {
            arg = GV.getStr(arg).replaceAll(clean, "");
        }
        return GV.getStr(arg).trim();
    }

    public static String estadoFicha(int status) {
        String value = "---";
        if(status < 0){
            return "Eliminada";
        }
        switch(status){
            case DELETED:
                value = "Eliminada";
                break;
            case PENDING:
                value = "Pendiente";
                break;
            case PAID:
                value = "Pagada";
                break;
            case WARRANTY:
                value = "Garantía";
                break;
            case DELIVERED:
                value = "Entregada";
                break;
            default:
                value = "Indefinido";
                break;
        }
        return value;
    }
    
    /**
     * recibe por parametro el id de el inventario seleccionado,
     * se usa para cargar valores desde la base de datos
     * @param idInventario 
     */
    public static void setInventaryChooser(int idInventario){
        ID_INVENTARIO_SELECCIONADO = idInventario;
    }
    /**
     * retorna el id del inventario seleccionado para uso temporal,
     * si el valor es cero es porque no se ha seleccionado un inventario
     * @return 
     */
    public static int getInventaryChooser(){
        return ID_INVENTARIO_SELECCIONADO;
    }

    public static String getSqlLowStock() {
        return SQL_LOW_STOCK;
    }
    
    public static SalesReportFicha getSalesReportFicha(){
        return SALESREPORT;
    }
    
    public static void setSalesReportFicha(SalesReportFicha report){
        SALESREPORT = report;
    }

    public static void setConvenio(Convenio convenio) {
        CONVENIO_SELECTED = convenio;
    }

    public static Convenio getConvenio() {
        return CONVENIO_SELECTED;
    }

    public static String cuotasFechaPagoPendienteDefault() {
        return CUOTAS_FECHA_PAGO_PENDIENTE;
    }
    
    public static void setRececptor(String name,String dir,String ct1,String ct2){
        RECEPTOR_CT1 = ct1;
        RECEPTOR_CT2 = ct2;
        RECEPTOR_DIR = dir;
        RECEPTOR_NAME = name;
    }
    
    public static String getReceptorName(){
        return RECEPTOR_NAME;
    }
    
    public static String getReceptorDir(){
        return RECEPTOR_DIR;
    }
    
    public static String getReceptorCT1(){
        return RECEPTOR_CT1;
    }
    
    public static String getReceptorCT2(){
        return RECEPTOR_CT2;
    }

    public static void setRutClienteSelected(String rut) {
        RUT_CLIENT_SELECTED = rut;
    }

    public static String getRutClienteSelected() {
        return RUT_CLIENT_SELECTED;
    }
    
    public static void setIdUserSelected(String idUser) {
        ID_USER_SELECTED = idUser;
    }

    public static String getIdUserSelected() {
        return ID_USER_SELECTED;
    }
    
    public static void setIdCovenioSelected(String idConvenio) {
        ID_CONVENIO_SELECTED = idConvenio;
    }

    public static String getIdCovenioSelected() {
        return ID_CONVENIO_SELECTED;
    }
    
    public static boolean licenciaActiva(){
        return LICENCE_ACTIVE;
    }
    
    public static void licencaActiva(boolean value){
        LICENCE_ACTIVE = value;
    }
    
    public static int licenciaTipoFree(){
        return TP_FREE;
    }
    
    public static int licenciaTipoLocal(){
        return TP_LOCAL;
    }
    
    public static int licenciaTipo2X(){
        return TP_2X;
    }
    
    public static int licenciaTipo4X(){
        return TP_4X;
    }
        
    public static int licenciaTipo6X(){
        return TP_6X;
    }
    
    public static int licenciaTipoFullData(){
        return TP_FULL_DATA;
    }
    
    public static int getSyncCount(){
        return SYNC_COUNT;
    }
    
    public static void setSyncCount(int value){
        SYNC_COUNT = value;
    }
    
    public static String getFechaDefault(){
        return FECHA_DEFAULT;
    }
}
