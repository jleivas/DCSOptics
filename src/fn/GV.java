/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn;

import bd.LcBd;
import com.toedter.calendar.JDateChooser;
import dao.Dao;
import entities.Convenio;
import entities.Descuento;
import entities.Oficina;
import entities.User;
import entities.context.SalesReportFicha;
import entities.ficha.Ficha;
import fn.globalValues.GlobalValuesBD;
import fn.globalValues.GlobalValuesCursor;
import fn.globalValues.GlobalValuesDirectories;
import fn.globalValues.GlobalValuesFunctions;
import fn.globalValues.GlobalValuesVariables;
import fn.globalValues.GlobalValuesEntities;
import fn.globalValues.GlobalValuesMailProperties;
import fn.globalValues.GlobalValuesNetwork;
import fn.globalValues.GlobalValuesPrint;
import fn.globalValues.GlobalValuesSaveXls;
import fn.globalValues.GlobalValuesSyncReportStatus;
import fn.globalValues.GlobalValuesUI;
import fn.globalValues.GlobalValuesXmlFiles;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import sync.entities.Local;
import sync.entities.Remote;
import view.init.Acceso;
import view.opanel.MPanel;
import view.opanel.OPanel;
import view.opanel.OpanelSelectAdminToSendMail;

/**
 *s
 * @author sdx
 */
public class GV extends GlobalValuesCursor{
    private static String className="GlobalValues";
    
    
    
    /*  Sincronizacion */
    public static Local LOCAL_SYNC = new Local();
    public static Remote REMOTE_SYNC = new Remote();
    
    public static Date LAST_UPDATE;
    
    public static void startSystem(){
        initDB();
        boolean error = false;
        try{GlobalValuesXmlFiles.checkXmlFiles();}catch(Exception e){error = true;licenciaRegistrar();}
        if(!error){
            initValues();
        }
    }
    
    public static void initValues(){
        Log.setLog(className,Log.getReg());
        SubProcess.isOnline();
        loadLastUpdateFromXML();//cargar LAST_UPDATE de fichero xml al iniciar programa
        SubProcess.licenciaComprobarOnline();
        validaBD();
        Acceso init = new Acceso();
        init.setVisible(true);
    }
    
    private static void validaBD(){
        Dao load = new Dao();
        try {    
            if(!GV.licenciaLocal()){
                //Comprueba si existe una base de datos remota con el usuario root
                if(load.get("root", 0, new User())==null){
                    GlobalValuesFunctions.sincronizeOrClose();
                }

            }else{
                load.addOnInit(new User(2, "Sistema", "root", "contacto@softdirex.cl", GV.enC("softdirex"), 7, 1, null, 0));
            }
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(GV.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public static void validaDBLocal(){
        try {
            LcBd.obtener();
            LcBd.cerrar();
        } catch (Exception ex) {
            initDB();
        }
    }
    /*********************BEGIN PORCENTAJE SYNC***************************/
    public static void porcentajeCalcular(int limit,String text){
        GlobalValuesSyncReportStatus.calcularPorcentaje(limit, text);
    }
    
    public static void porcentajeSubCalcular(int subLimit){
        GlobalValuesSyncReportStatus.calcularSubPorcentaje(subLimit);
    }
    
    public static int porcentaje(){
        return GlobalValuesSyncReportStatus.getPorc();
    }
    
    public static String getReporte(){
        return GlobalValuesSyncReportStatus.getReport();
    }
    
    public static void setReporte(String reporte){
        GlobalValuesSyncReportStatus.setReport(reporte);
    }
    
    public static void porcentajeReset(){
        GlobalValuesSyncReportStatus.resetPorc();
    }
    
    public static void resetAllPorcentaje(){
        GlobalValuesSyncReportStatus.resetAll();
    }
    /*********************END PORCENTAJE SYNC***************************/
    /*********************BEGIN UI PROPERTIES****************************/
    public static int msgStatus(){
        return GlobalValuesUI.getMsgStatus();
    }
    
    public static void setMsgStatus(int msgStatus){
        GlobalValuesUI.setMsgStatus(msgStatus);
    }
    
    public static String iconInfo(){
        return GlobalValuesUI.iconInfo();
    }
    
    public static String iconWarn(){
        return GlobalValuesUI.iconWarn();
    }
    
    public static String iconError(){
        return GlobalValuesUI.iconError();
    }
    
    public static OPanel opanel(){
        return GlobalValuesUI.opanel();
    }
    
    public static MPanel mpanel(){
        return GlobalValuesUI.mpanel();
    }
    /*********************ENG UI PROPERTIES****************************/
    /*********************BEGIN CHECK ONLINE*****************************/
    public static boolean isOnline(){
        return GlobalValuesNetwork.isOnline();
    }
    
    public static void isOnline(boolean value){
        GlobalValuesNetwork.setIsOnline(value);
    }
    
    public static void chekOnline(){
        GlobalValuesNetwork.checkIfOnline();
    }
    /*********************END CHECK ONLINE*****************************/
    /*********************BEGIN MAIL*****************************/
    public static String getMailSystemName() {
        return GlobalValuesMailProperties.getMailSystemName();
    }
    
    public static String getMailSystemPass() {
        return GlobalValuesMailProperties.getMailSystemPass();
    }
    
    public static void setMailLog(String className, String registry){
        GlobalValuesMailProperties.setMailLog(className, registry);
    }
    
    public static String mailLog(){
        return GlobalValuesMailProperties.getMailLog();
    }
    
    public static String mailReport(){
        return GlobalValuesMailProperties.getMailReport();
    }
    /*********************END MAIL*****************************/
     /**************************BEGIN PRINT**********************************/
    public static void printFichaConvenio(){
        GlobalValuesPrint.print(getFichaConvenio());
    }
    
    public static void printFichas(List<Object> fichas) {
        GlobalValuesPrint.printFichas(fichas);
    }
    
    public static void printConvenio(Convenio cnv) {
        GlobalValuesPrint.printConvenio(cnv);
    }
    
    public static void printCuotasConvenio(Convenio cnv) {
        GlobalValuesPrint.printCuotasConvenio(cnv);
    }
    
    public static void printSalesReport(List<Object> fichas,String title){
        GlobalValuesPrint.printSalesReport(fichas, title);
    }
    public static void printFicha(){
        GlobalValuesPrint.print(getFicha());
    }
    
    public static void printFicha(Ficha ficha){
        GlobalValuesPrint.print(ficha);
    }
     /**************************END PRINT************************************/
    /**************************BEGIN FUNTIONS**********************************/
    public static boolean usernameYaExiste(String username) {
        return GlobalValuesFunctions.usernameYaExiste(username);
    }
    
    public static int getSyncCount(){
        return GlobalValuesFunctions.currentSyncCount();
    }
    
    public static boolean licenciaExpirada(){
        return GlobalValuesFunctions.licenciaExpirada();
    }
    
    public static int fechaDiferencia(Date date) {
        return GlobalValuesFunctions.fechaDiferencia(date);
    }
    
    public static void fichasToDelivery(List<Object> fichas){
        GlobalValuesFunctions.fichasToDelivery(fichas);
    }
    
    public static boolean licenciaComprobarOnline(String arg) {
        return GlobalValuesFunctions.licenciaComprobateOnline(arg);
    }
    
    public static void asignarToken(String arg,String key) {
        GlobalValuesFunctions.asignarToken(arg,key);
    }
    
    public static User validar(String username, String pass) {
        return GlobalValuesFunctions.validar(username,pass);
    }
    
    public static void sendMailFicha(Ficha ficha) {
        GlobalValuesFunctions.sendMailFicha(ficha);
    }
    
    public static boolean syncEnabled(){
        return GlobalValuesFunctions.syncEnabled();
    }
    
    public static Date fechaPorDefectoDate(){
        return GlobalValuesFunctions.getFechaDefault();
    }
    
    public static void setSyncCount(int value){
        GlobalValuesFunctions.currentSyncCount(value);
    }
    
    public static String enC(String arg){
        return GlobalValuesFunctions.enCrypt(arg);
    }
    
    public static String dsC(String arg){
        return GlobalValuesFunctions.dsCrypt(arg);
    }
    
    public static String strToRut(String rut){
        return GlobalValuesFunctions.formatRut(rut);
    }
    
    public static boolean convenioPagarCuotas(Convenio cnv,int cuotas, Date fechaRegistro,int tipoPago){
        return GlobalValuesFunctions.convenioPagarCuotas(cnv, cuotas, fechaRegistro,tipoPago);
    }
    
    public static boolean cuotasFechaPagoPendiente(Date fechaPagado) {
        return GlobalValuesFunctions.cuotasFechaPagoPendiente(fechaPagado);
    }
    
    public static boolean validaRut(String rut) {
        return GlobalValuesFunctions.validaRut(rut);
    }
    /**
     * Envia un reporte de ventas por correo
     * @param report 
     */
    public static void mailSendSalesReport(SalesReportFicha report){
        setSalesReportFicha(report);
        OptionPane.showOptionPanel(new OpanelSelectAdminToSendMail(), OptionPane.titleUserChooser());
    }
            
    public static SalesReportFicha obtenerReporteVentas(List<Object> listFicha){
        return GlobalValuesFunctions.reportSalesObtain(listFicha);
    }
    
    public static String dateToString(Date date, String format){
        return GlobalValuesFunctions.dateToString(date,format);
    }
    
    public static int roundPrice(int price) {
        return GlobalValuesFunctions.roundPrice(price);
    }
    
    public static int contChar(char toCompare, String toCount){
        return GlobalValuesFunctions.contChar(toCompare, toCount);
    }
    
    public static String getStr(String arg){
        return GlobalValuesFunctions.getStr(arg);
    }
    
    public static String getFilterString(String arg){
        return GlobalValuesFunctions.getFilterString(arg);
    }
    
    public static String getStToName(String arg){
        return GlobalValuesFunctions.getToName(arg);
    }
    
    public static String className(Object type){
        return GlobalValuesFunctions.getClassName(type);
    }
    
    public static String getToName(String param){
        return GlobalValuesFunctions.getToName(param);
    }
    
    public static void emptyTable(JComboBox cbo, JTextField txt, String registry){
        GlobalValuesFunctions.emptyTable(cbo, txt, registry);
    }
    
    public static String strToPrice(int monto){
        return GlobalValuesFunctions.strToPrice(monto);
    }
    
    public static int strToNumber(String arg){
        return GlobalValuesFunctions.strToNumber(arg);
    }
    
    public static String toHour(int hora, int min){
        return GlobalValuesFunctions.toStrHour(hora,min);
    }

    public static boolean strCompare(String str1, String str2) {
        return GlobalValuesFunctions.strCompare(str1, str2);
    }
    public static String formatoHora(int hora, int min) {
        return GlobalValuesFunctions.formatoHora(hora, min);
    }
    
    public static boolean containIntegrs(String arg){
        return GlobalValuesFunctions.containIntegrs(arg);
    }
    
    public static int diasRestatntes(String strExpDate){
        try {
            return GlobalValuesFunctions.diasRestantes(strExpDate);
        } catch (ParseException ex) {
            Logger.getLogger(GV.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public static Date strToDate(String strFecha){
        return GlobalValuesFunctions.strToDate(strFecha);
    }
    
    /**
     * retorna un booleano indicando si la fecha entregada por parametro 
     * corresponde al dia de hoy, compara solo el día, mes y año
     * @param date
     * @return 
     */
    public static boolean isCurrentDate(Date date) {
        return GlobalValuesFunctions.isCurrentDate(date);
    }
    
    public static int obtenerDescuentoFicha(Descuento descuento, int total) {
        return GlobalValuesFunctions.obtenerDescuentoFicha(descuento,total);
    }
    
    public static void compileJCalendar(JDateChooser jDate) {
        GlobalValuesFunctions.compileJCalendar(jDate);
    }
    
    public static void setHourToFicha(JSpinner txth1, JSpinner txtm1, JSpinner txth2, JSpinner txtm2) {
        GlobalValuesFunctions.setHourToFicha(txth1,txtm1,txth2,txtm2);
    }
    
    public static boolean isNumeric(String arg){
        return GlobalValuesFunctions.isNumeric(arg);
    }
    
    public static int abonosMonto(int index,Object[][] abonos){
        return GlobalValuesFunctions.abonoMontoFromArray(index,abonos);
    }
    
    public static Date abonosDate(int index,Object[][] abonos){
        return GlobalValuesFunctions.abonoDateFromArray(index,abonos);
    }
    
    public static String abonosName(int index,Object[][] abonos){
        return GlobalValuesFunctions.abonoNameFromArray(index,abonos);
    }
    
    public static Object[][] abonosListArrayFromFicha(String codFicha){
        return GlobalValuesFunctions.listarAbonos(codFicha);
    }
    /**
     * retorna un objeto de la lista enviada por parametros
     * @param code
     * @param list
     * @param classType
     * @return 
     */
    public static Object buscarPorIdEnLista(String code,List<Object> list,Object classType){
        return GlobalValuesFunctions.searchByIdInList(code, list, classType);
    }
    
    public static void spinnerNumberDisable(JSpinner spinnerNumber,int currentValue){
        GlobalValuesFunctions.spinnerNumberDisable(spinnerNumber,currentValue);
    }
    
    
    /**
     * Devuelde una fecha de tipo Date con el resultado según parámetro sumaresta ingresado
     * @param fecha
     * @param sumaresta
     * @param opcion Parámetros validos: DAYS, MONTHS o YEARS.
     * @return 
     */
    public static Date dateSumaResta(Date fecha,int sumaresta,String opcion){
        return GlobalValuesFunctions.SumaRestarFecha(fecha, sumaresta, opcion);
    }
    
    public static void crearGarantia(Ficha ficha) {
        GlobalValuesFunctions.createGuaranteeFicha(ficha);
    }
    
    /**
     * retorna true si la fecha ingresada por parametros es igual
     * o superior a la fecha actual
     * @param date
     * @return 
     */
    public static boolean fechaActualOFutura(Date date){
        return GlobalValuesFunctions.fechaActualOFutura(date);
    }
    
    /**
     * retorna true si la fecha ingresada por parametros es
     * superior a la fecha actual
     * @param date
     * @return 
     */
    public static boolean fechaFutura(Date date){
        return GlobalValuesFunctions.fechaFutura(date);
    }
    
    /**
     * retorna true si la fecha ingresada por parametros es inferior
     * a la fecha actual
     * @param date
     * @return 
     */
    public static boolean fechaPasada(Date date){
        return GlobalValuesFunctions.fechaPasada(date);
    }
    
    /**
     * retorna true si la fecha ingresada por parametros es 
     * pasada o igual a la fecha actual
     * @param date
     * @return 
     */
    public static boolean fechaActualOPasada(Date date){
        return GlobalValuesFunctions.fechaActualOPasada(date);
    }
    
    public static void licenciaRegistroPaso2() {
        GlobalValuesFunctions.showRegistroLicenciaPaso2();
    }
    
    public static void licenciaRegistroPaso3() {
        GlobalValuesFunctions.showRegistroLicenciaPaso3();
    }
    
    public static void licenciaRegistroPasoFinished(){
        GlobalValuesFunctions.licenciaRegistroPasoFinished();
    }
    
    public static void licenciaRegistrar(){
        GlobalValuesFunctions.showRegistroLicencia();
    }
    
    /**
     * Para usar esta funcion no se debe validar el convenio antes de enviarlo por
     * parametros ya que el método lo valiada dentro de el.
     * @param convenio 
     */
    public static void convenioUpdateBDIfValidated(Convenio convenio){
        GlobalValuesFunctions.updateBDConvenioValidado(convenio);
    }
    
    public static void convenioGenerateReport(Convenio cnv) {
        GlobalValuesFunctions.convenioGenerateReport(cnv);
    }
    
    public static String licenciaEstadoStr(){
        return GlobalValuesFunctions.licenciaEstado();
    }
    /**************************END FUNTIONS**********************************/
    /*****************************BEGIN VARIABLES DEL SISTEMA***************************************/
    public static SalesReportFicha getSalesReportFicha(){
        return GlobalValuesVariables.getSalesReportFicha();
    }
    
    public static String fechaPorDefectoString(){
        return GlobalValuesVariables.getFechaDefault();
    }
    
    public static void rutClientSelected(String rut) {
        GlobalValuesVariables.setRutClienteSelected(rut);
    }
    
    public static String rutClientSelected() {
        return GlobalValuesVariables.getRutClienteSelected();
    }
    
    public static void userIdSelected(String isUser) {
        GlobalValuesVariables.setIdUserSelected(isUser);
    }
    
    public static String userIdSelected() {
        return GlobalValuesVariables.getIdUserSelected();
    }
    
    public static void convenioIdSelected(String isUser) {
        GlobalValuesVariables.setIdCovenioSelected(isUser);
    }
    
    public static String convenioIdSelected() {
        return GlobalValuesVariables.getIdCovenioSelected();
    }
    
    /**
     * Retorna una fecha por defecto, esta fecha indica que no ha sido registrado
     * un pago de cuotas
     * @return 01-01-2001
     */
    public static Date cuotasFechaPagoDefault() {
        return GV.strToDate(GlobalValuesVariables.cuotasFechaPagoPendienteDefault());
    }
    
    public static void setSalesReportFicha(SalesReportFicha report){
        GlobalValuesVariables.setSalesReportFicha(report);
    }
    
    public static String getCompanyDescription(){
        return GlobalValuesVariables.getCompanyDescription();
    }
    
    public static void setCompanyDescription(String companyDescription){
        GlobalValuesVariables.setCompanyDescription(companyDescription);
        saveXMLProperties();
    }
    
    public static void setCompanyDescriptionFromXml(String companyDescription){
        GlobalValuesVariables.setCompanyDescription(companyDescription);
    }
    
    public static String getCompanyRut(){
        return GlobalValuesVariables.getCompanyRut();
    }
    
    public static void setCompanyRut(String companyRut){
        GlobalValuesVariables.setCompanyRut(companyRut);
        saveXMLProperties();
    }
    
    public static void setCompanyRutFromXml(String companyRut){
        GlobalValuesVariables.setCompanyRut(companyRut);
    }
    
    public static String getCompanyGiro(){
        return GlobalValuesVariables.getCompanyGiro();
    }
    
    public static void setCompanyGiro(String companyGiro){
        GlobalValuesVariables.setCompanyGiro(companyGiro);
        saveXMLProperties();
    }
    
    public static void setCompanyGiroFromXml(String companyGiro){
        GlobalValuesVariables.setCompanyGiro(companyGiro);
    }
    
    public static String getMessageFile(){
        return GlobalValuesVariables.getMessageFile();
    }
    
    public static void setMessageFile(String messageFile){
        GlobalValuesVariables.setMessageFile(messageFile);
        saveXMLProperties();
    }
    
    public static void setMessageFileFromXml(String messageFile){
        GlobalValuesVariables.setMessageFile(messageFile);
    }
    
    public static void username(String userName) {
        GlobalValuesVariables.setUserName(userName);
    }
            
    public static String sqlLowStock() {
        return GlobalValuesVariables.getSqlLowStock();
    }
    
    public static int cboFichasFilter(){
        return GlobalValuesVariables.cboFichasFilter();
    }
    
    public static void setCboFichasFilter(int filter){
        GlobalValuesVariables.setCboFichasFilter(filter);
    }
    
    public static String username() {
        return GlobalValuesVariables.getUserName();
    }
    
    public static String salt(){
        return GlobalValuesVariables.getSalt();
    }
    public static Date dateFrom(){
        return GlobalValuesVariables.getDateFrom();
    }
    
    public static Date dateTo(){
        return GlobalValuesVariables.getDateTo();
    }
    
    public static void setDateFrom(Date date){
        GlobalValuesVariables.setDateFrom(date);
    }
    
    public static void setDateTo(Date date){
        GlobalValuesVariables.setDateTo(date);
    }
    
    public static void setInventarioLocal(String inventario){
        GlobalValuesVariables.setInventarioLocal(inventario);
        saveXMLProperties();
    }
    
    public static void setInventarioSeleccionado(int idInventario){
        GlobalValuesVariables.setInventaryChooser(idInventario);
    }
    
    public static int getInventarioSeleccionado(){
        return GlobalValuesVariables.getInventaryChooser();
    }
    
    public static void setInventarioLocalFromXml(String inventario){
        GlobalValuesVariables.setInventarioLocal(inventario);
    }
    
    public static String inventarioName(){
        return GlobalValuesVariables.getInventarioName();
    }
    
    public static void setCompanyName(String nombre) {
        GlobalValuesVariables.setCompanyName(nombre);
        saveXMLProperties();
    }
    
    public static void setCompanyNameFromXml(String nombre) {
        GlobalValuesVariables.setCompanyName(nombre);
    }
    
    public static String companyName(){
        return GlobalValuesVariables.getCompanyName();
    }
    
    public static String projectName(){
        return GlobalValuesVariables.getProjectName();
    }
    
    public static String version(){
        return GlobalValuesVariables.getVersion();
    }
    
    public static int licenciaTipoPlan(){
        return GlobalValuesVariables.getLicenciaTipoPlan();
    }
    
    public static String licenceCode(){
        return GlobalValuesVariables.getLicenceCode();
    }
    
    public static void setLicenceCode(String licenceCode){
        GlobalValuesVariables.setLicenceCode(licenceCode);
    }
    
    public static void licenciaTipoPlan(int value){
        GlobalValuesVariables.setLicenciaTipoPlan(value);
    }
    
    public static String expDate(){
        return GlobalValuesVariables.getExpDate();
    }
    
    public static void expDate(String date){
        GlobalValuesVariables.setExpDate(date);
    }
    
    public static String equipo(){
        return getStr(GlobalValuesVariables.getEquipo());
    }
    
    public static void setCurrentEquipo(String name){
        GlobalValuesVariables.setCurrentEquipo(name);
    }
    
    public static void setLicenciaTipoPlan(int value) {
        GlobalValuesVariables.setLicenciaTipoPlan(value);
    }
    
    public static String uri(){
        return getStr(GlobalValuesVariables.apiUriLicence());
    }
    
    public static void setUri(String uri){
        GlobalValuesVariables.setApiUriLicence(uri);
    }
    
    public static String port(){
        return GlobalValuesVariables.urlUriPort();
    }
    
    public static void setPort(String port){
        GlobalValuesVariables.setApiUriPort(port);
    }
    
    public static String cleanIdParam(String arg){
        return GlobalValuesVariables.cleanIdParam(arg);
    }
    
    public static boolean fichaIdParamIsFichaList(String arg) {
        return GlobalValuesVariables.fichaIdParamIsFichaList(arg);
    }
    
    public static boolean fichaIdParamIsIdFicha(String arg){
        return GlobalValuesVariables.fichaIdParamIsIdFicha(arg);
    }
    
    public static boolean fichaIdParamIsUser(String arg){
        return GlobalValuesVariables.fichaIdParamIsUser(arg);
    }
    
    public static boolean fichaIdParamIsTableList(String arg){
        return GlobalValuesVariables.fichaIdParamIsTableList(arg);
    }
    
    public static boolean fichaIdParamIsDateList(String arg) {
        return GlobalValuesVariables.fichaIdParamIsDateList(arg);
    }
    
    public static boolean fichaIdParamIsClient(String arg){
        return GlobalValuesVariables.fichaIdParamIsClient(arg);
    }
    
    public static String convertFichaIdParamToUSer(String arg){
        return GlobalValuesVariables.convertFichaIdParamToUSer(arg);
    }
    
    public static String convertFichaIdParamToClient(String arg){
        return GlobalValuesVariables.convertFichaIdParamToClient(arg);
    }
    
    public static String convertFichaIdParamToListAbonos(String arg){
        return GlobalValuesVariables.convertFichaIdParamToListAbonos(arg);
    }
    
    public static String convertFichaIdParamToTableList(String arg){
        return GlobalValuesVariables.convertFichaIdParamToTableList(arg);
    }
    
    public static String convertFichaIdParamToDateList(String arg){
        return GlobalValuesVariables.convertFichaIdParamToDateList(arg);
    }
    
    /*tansforma el parametro de entrada de la funcion listar para que retorune una 
        lista de objetos de tipo ficha con todos sus datos
    */
    public static String convertFichaIdToFichaList(String arg){
        return GlobalValuesVariables.convertFichaIdParamToFichaList(arg);
    }
    
    public static String estadoFicha(int status){
        return GlobalValuesVariables.estadoFicha(status);
    }
    
    public static int estadoFichaDeleted(){
        return GlobalValuesVariables.estadoFichaDeleted();
    }
    
    public static int estadoFichaPending(){
        return GlobalValuesVariables.estadoFichaPending();
    }
    
    public static int estadoFichaPaid(){
        return GlobalValuesVariables.estadoFichaPaid();
    }
    
    public static int estadoFichaDelivered(){
        return GlobalValuesVariables.estadoFichaDelivered();
    }
    
    public static int estadoFichaWarranty(){
        return GlobalValuesVariables.estadoFichaWarranty();
    }
    
    public static void setConvenio(Convenio convenio) {
        GlobalValuesVariables.setConvenio(convenio);
    }
    
    public static Convenio getConvenio() {
        return GlobalValuesVariables.getConvenio();
    }
    /*****************************END VARIABLES DEL SISTEMA***************************************/
    /*****************************BEGIN BD***************************************/
    public static void backUpLocalBd(){
        GlobalValuesBD.backUpLocalBd();
    }
    
    public static void resetBD(){
        GlobalValuesBD.resetAllDataSource();
    }
    
    public static void sincronizarTodo(){
        GlobalValuesBD.sincronizarTodo();
    }
    
    public static void stopSincronizacion(){
        GlobalValuesBD.setSincronizar(false);
    }
    
    public static void startSincronizacion() {
        GlobalValuesBD.setSincronizar(true);
    }
    
    public static boolean sincronizacionIsStopped() {
        return !GlobalValuesBD.sincronizacion();
    }
    
    public static Connection initDB(){
        return GlobalValuesBD.initDB();
    }
    
    public static String getLocalBdUser() {
        return GlobalValuesBD.getLocalBdUser();
    }

    public static String getLocalBdPass() {
        return GlobalValuesBD.getLocalBdPass();
    }

    public static String getLocalBdUrl() {
        return GlobalValuesBD.getLocalBdUrl();
    }

    public static String getLocalBdName() {
        return GlobalValuesBD.getLocalBdName();
    }
    
    public static String getRemoteBdUser() {
        return GlobalValuesBD.getRemoteBdUser();
    }

    public static String getRemoteBdPass() {
        return GlobalValuesBD.getRemoteBdPass();
    }

    public static String getRemoteBdUrl() {
        return GlobalValuesBD.getRemoteBdUrl();
    }

    public static String getRemoteBdName() {
        return GlobalValuesBD.getRemoteBdName();
    }
    
    public static void listarFichasByDate(Date date1, Date date2) {
        GlobalValuesBD.listarFichasByDate(date1,date2);
    }
    
    public static void listarFichasByClient(String rut) {
        GlobalValuesBD.listarFichasByClient(rut);
    }
    
    public static void listarFichasByUser(String idUser) {
        GlobalValuesBD.listarFichasByUser(idUser);
    }
    
    public static void listarFichasByConveny(String idCnv) {
        GlobalValuesBD.listarFichasByConveny(idCnv);
    }
    
    public static List<Object> getFichas() {
        return GlobalValuesBD.getFichas();
    }
    
    /**
     * Retorna una lista de entidades tipo ficha con todos sus datos, si userId y clientCod son null,
     * buscara por fecha, 
     * de lo contratrio validara un codFicha
     * @param dateFrom
     * @param dateTo
     * @param idUser
     * @param codCliente
     * @param codFicha
     * @return 
     */
    public static List<Object> listarFichas(Date dateFrom, Date dateTo, int idUser, String codCliente, String codFicha){
        return GlobalValuesBD.listarFichas(dateTo, dateFrom,""+idUser, codCliente, codFicha);
    }
    /*****************************END BD***************************************/
    
    //******************************** BEGIN CURSOR ***************************************************
    public static void cursorDF(){
        GlobalValuesCursor.cursorDF();
    }
    
    public static void cursorWAIT(){
        GlobalValuesCursor.cursorWAIT();
    }
    
    public static void cursorDF(JPanel panel){
        GlobalValuesCursor.cursorDF(panel);
    }
    
    public static void cursorWAIT(JPanel panel){
        GlobalValuesCursor.cursorWAIT(panel);
    }
    
    //******************************** END CURSOR ***************************************************/
    /********************************** BEGIN ENTITIES **************************************/
    public static void clearFicha(){
        GlobalValuesEntities.clearFicha();
    }
    
    public static Ficha getFicha(){
        return GlobalValuesEntities.getFicha();
    }
    
    public static void setFicha(Ficha ficha){
        GlobalValuesEntities.setFicha(ficha);
    }
    
    public static Ficha getFichaConvenio(){
        return GlobalValuesEntities.getFichaConvenio();
    }
    
    public static void setFichaConvenio(Ficha ficha){
        GlobalValuesEntities.setFichaConvenio(ficha);
    }
    
    /**
     * retorna la ficha seleccionada para mostrar los datos
     * @return 
     */
    public static Ficha getOpenFicha(){
        return GlobalValuesEntities.getOpenFicha();
    }
    
    /**
     * Asigna una ficha para dejarla estática y mostrar sus datos en una nueva ventana
     * @param ficha 
     */
    public static void setOpenFicha(Ficha ficha){
        GlobalValuesEntities.setOpenFicha(ficha);
    }
    
    public static User user(){
        return GlobalValuesEntities.getSessionUser();
    }
    
    public static void setUser(User user){
        GlobalValuesEntities.setSessionUser(user);
    }
    
    public static void setOficina(Oficina oficina){
        GlobalValuesEntities.setOficina(oficina);
        saveXMLProperties();
    }
    
    public static void setOficina(String nombre){
        if(GlobalValuesEntities.setOficina(nombre)){
            saveXMLProperties();
        }
    }
    
    public static void setOficinaFromXml(String nombre){
        GlobalValuesEntities.setOficina(nombre);
    }

    public static String getLblNombreOficina() {
        return GlobalValuesEntities.getLblNombreOficina();
    }
    
    public static String getNombreOficina() {
        return GlobalValuesEntities.getNombreOficina();
    }
    
    public static String getOficinaWeb() {
        return GlobalValuesEntities.getOficinaWeb();
    }
    
    public static String getOficinaAddress() {
        return GlobalValuesEntities.getOficinaAddress();
    }
    
    public static String getOficinaCity() {
        return GlobalValuesEntities.getOficinaCity();
    }
    
    public static String getOficinaMail(){
        return GlobalValuesEntities.getOficinaMail();
    }
    
    public static String getOficinaPhone1(){
        return GlobalValuesEntities.getOficinaPhone1();
    }
    
    public static String getOficinaPhone2(){
        return GlobalValuesEntities.getOficinaPhone2();
    }
    
    /**
     * Retorna true si es Jefe administrativo o Sistema
     * @return 
     */
    public static boolean tipoUserSuperAdmin(){
        return GlobalValuesEntities.tipoUserSuperAdmin();
    }
    
    /**
     * Retorna true si es Jefe administrativo, Administrador o Sistema
     * @return 
     */
    public static boolean tipoUserAdmin(){
        return GlobalValuesEntities.tipoUserAdmin();
    }
    
    /**
     * Retorna true si es Jefe administrativo, Administrador, inventario o Sistema
     * @return 
     */
    public static boolean tipoUserIventario(){
        return GlobalValuesEntities.tipoUserIventario();
    }
    /********************************** END ENTITIES **************************************/
    /*****************************BEGIN DIRECTORY***************************************/
    public static String directoryFilesPath(){
        return GlobalValuesDirectories.getFilesPath();
    }
    
    public static String directoryLocalPath(){
        return GlobalValuesDirectories.getLocalPath();
    }
    
    public static String directoryReportViewPath(){
        return GlobalValuesDirectories.getReportViewPath();
    }
    
    public static String directoryReportExcelPath(){
        return GlobalValuesDirectories.getReportExcelPath();
    }
    
    /*****************************END DIRECTORY***************************************/
    /*****************************BEGIN XML***************************************/
    private static void saveXMLProperties() {
        GlobalValuesXmlFiles.crearRegistroLocal();
    }
    
    public static void loadLastUpdateFromXML() {
        GlobalValuesXmlFiles.cargarRegistroLocal();
    }
    
    public static void loadXmlOnline(){
        GlobalValuesXmlFiles.readXMLOnline();
    }
    /*****************************END XML***************************************/
    /*****************************BEGIN XSL***************************************/
    public static void excelAllMails() {
        GlobalValuesSaveXls.saveAllMails();
    }
    
    public static void excelUnDeliveredMails() {
        GlobalValuesSaveXls.saveUnDeliveredMails();
    }
    
    public static void excelExportFichas(List<Object> fichas) {
        GlobalValuesSaveXls.exportarFichasAExcel(fichas);
    }
    
    public static void excelLowStockBuyOrder() {
        GlobalValuesSaveXls.saveInventaryLowStock();
    }
    
    public static void excelDebtMails() {
        GlobalValuesSaveXls.saveDebtMails();
    }
    
    public static void excelExportInventarySelected(){
        GlobalValuesSaveXls.saveInventary();
    }
    /*****************************END XSL***************************************/
    /**
     * Actualiza archivo local de propiedades del sistema con los valores estaticos
     */
    

    public static void setLastUpdate(Date date) {
        LAST_UPDATE = date;
        saveXMLProperties();
    }
    
    public static void setLastUpdateFromXml(Date date) {
        LAST_UPDATE = date;
    }
    
    public static Date getLastUpdate(){
        return LAST_UPDATE;
    }

    public static String mailValidate(String email) {
        email = getStr(email).toLowerCase();
        // Patrón para validar el email
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
 
        // El email a validar
 
        Matcher mather = pattern.matcher(email);
        if(mather.find()){
            return email;
        }
        return "";
    }

    public static int cuotaConvenioPagada() {
        return 2;
    }

    public static void mensajeAccessDenied() {
        OptionPane.showMsg("Acceso denegado", "No tienes permisos suficientes para realizar esta operación", 2);
    }

    public static void mensajeLicenceAccessDenied() {
        OptionPane.showMsg("Cambie su licencia", "La versión de su licencia no tiene esta opción disponible", 2);
    }
    
    public static void mensajeLicenceExpired() {
        OptionPane.showMsg("Renueve su licencia", "La versión de su licencia se encuentra expirada", 2);
    }

    public static boolean licenciaLocal() {
        return (licenciaTipoPlan() == GlobalValuesVariables.licenciaTipoFree() ||
                licenciaTipoPlan() == GlobalValuesVariables.licenciaTipoLocal());
    }

    public static void mensajeExcepcion(String error, int status) {
        OptionPane.showMsg("Error critico", "Ocurrió un error inesperado:\n"+error, status);
    }
}
