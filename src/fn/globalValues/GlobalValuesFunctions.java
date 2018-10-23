/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn.globalValues;

import com.toedter.calendar.JDateChooser;
import dao.Dao;
import entities.Convenio;
import entities.CuotasConvenio;
import entities.Descuento;
import entities.Equipo;
import entities.TipoPago;
import entities.User;
import entities.abstractclasses.SyncIntId;
import entities.abstractclasses.SyncStringId;
import entities.context.SalesReportFicha;
import entities.ficha.Despacho;
import entities.ficha.Ficha;
import entities.ficha.HistorialPago;
import fn.GV;
import static fn.GV.sincronizarTodo;
import fn.OptionPane;
import static fn.ValidaRut.validarRut;
import fn.date.Cmp;
import fn.mail.Send;
import java.security.MessageDigest;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import org.apache.commons.codec.binary.Base64;
import view.opanel.OpanelCompanyData;
import view.opanel.OpanelConvenyReceptor;
import view.opanel.OpanelOfficeData;
import view.opanel.OpanelSetLicencia;
import view.opanel.OpanelSetToken;

/**
 *
 * @author jlleivas
 */
public class GlobalValuesFunctions {
    private static String TIPO_PAGO_NO_REGISTRADO = "No registrado";
    private static int POS_MONTO = 0;
    private static int POS_NAME = 1;
    private static int POS_DATE = 2;
    private static int TAMANO_COLUMN_ABONOS = 3;//la columna dos corresponde a la descripcion del tipo de pago, se usa en funciones para imprimir
    private static Dao load = new Dao();
    
    public static String dateToString(Date date,String format){
        return Cmp.dateToString(date, format);
    }
    
    public static String getToName(String param){
        String[] str = getStr(param).toLowerCase().split(" ");
        StringBuffer value = new StringBuffer();
        for (String temp : str) {
            if(temp.length() > 1){
                value.append(Character.toUpperCase(temp.charAt(0))).append(temp.substring(1)).append(" ");
            }else{
                value.append(temp.toUpperCase()).append(" ");
            }
        }
        return value.toString().trim();
    }
    
    public static String getClassName(Object type){
        if(type == null) return "[...]";
        String name = (type.getClass().getName().contains("."))?
                type.getClass().getName().substring(type.getClass().getName().lastIndexOf(".")+1):type.getClass().getName();
        return name;
    }
    
    public static String getStr(String arg){
        if(arg == null || arg.replaceAll(" ", "").isEmpty())
            return "";
        else{
            return arg.trim();
        }
    }
    
    public static String getFilterString(String arg){
        if(arg == null || arg.replaceAll(" ", "").isEmpty())
            return "";
        else{
            String value = arg.replaceAll("[+^‘´'{}]","");
            return (value.startsWith(" "))?value.replaceFirst(" ", "").trim():value.trim();
        }
    }
    
    public static void emptyTable(JComboBox cbo, JTextField txt, String registry){
        String end = "os";
        registry = (!registry.endsWith("s"))? registry+"s":registry;
        end = (registry.endsWith("as"))? "as":end;
        if(txt.getText().length() > 1){
            OptionPane.showMsg("No existen "+registry, "No existen registros disponibles que contengan la palabra \""+txt.getText()+"\"",1);
        }else{
            if(registry.toLowerCase().contains("fichas")){
                if(cbo.getSelectedIndex() == 5){
                    OptionPane.showMsg("No existen "+registry, "No existen "+registry+" eliminad"+end+".",1);
                }else{
                    OptionPane.showMsg("No existen "+registry, "No existen "+registry+" registrad"+end+".",1);
                }
            }else{
                if(cbo.getSelectedIndex() == 0){
                    OptionPane.showMsg("No existen "+registry, "No existen "+registry+" registrad"+end+".",1);
                }else{
                    OptionPane.showMsg("No existen "+registry, "No existen "+registry+" eliminad"+end+".",1);
                }
            }
        }
    }
    
    public static String strToPrice(int monto){
        DecimalFormat formateador = new DecimalFormat("###,###,###");
        return "$ "+formateador.format (monto);
    }
    
    public static int strToNumber(String arg){
        arg = GV.getStr(arg).replaceAll("[^0-9-]", "");
        boolean isNegative = (arg.startsWith("-"))? true:false;
        arg = arg.replaceAll("-", "").trim();
        if(arg.isEmpty())
            return 0;
        if(isNegative){
            return -Integer.parseInt(arg);
        }
        return Integer.parseInt(arg);
    }

    public static boolean strCompare(String str1, String str2) {
        str1 = str1.toLowerCase();
        str1 = str1.replaceAll(" ", "");
        str2 = str2.toLowerCase();
        str2 = str2.replaceAll(" ", "");
        return str1.equals(str2);
    }
    public static String formatoHora(int hora, int min) {
        String h = "";
        String m = "";
        if(hora < 10)
        h = "0";
        if(min < 10)
        m = "0";
            
        return h+hora+":"+m+min;
    }
    
    public static boolean containIntegrs(String arg){
        String temp = arg.trim().toLowerCase().replaceAll("[0-9]", "");
        if(!temp.equals(arg.toLowerCase()))
            return true;
        return false;
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
    
    public static int diasRestantes(String stFecha) throws ParseException{ 
            stFecha = getStr(stFecha).replaceAll("-", "/");
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            if(!stFecha.isEmpty()){
                cal.setTime(sdf.parse(stFecha));
            }
            int dias = 0;
            if(cal.compareTo(Calendar.getInstance())>=0){
                Date fecha=cal.getTime();
                DateFormat dd = new SimpleDateFormat("dd/MM/yyyy");       
                boolean activo = false;
                Calendar calendar; Date aux;
                do{       
                    calendar = Calendar.getInstance();           
                    calendar.add(Calendar.DAY_OF_YEAR, dias);
                    aux = calendar.getTime();
                        if(dd.format(aux).equals(dd.format(fecha)))
                            activo = true; 
                        else
                            dias++;
                }while(activo != true);
            }else{
                Date fecha=cal.getTime();
                DateFormat dd = new SimpleDateFormat("dd/MM/yyyy");       
                boolean activo = false;
                Calendar calendar; Date aux;
                do{      
                    calendar = Calendar.getInstance();           
                    calendar.add(Calendar.DAY_OF_YEAR, dias);
                    aux = calendar.getTime();
                        if(dd.format(aux).equals(dd.format(fecha)))
                            activo = true; 
                        else
                            dias--;
                }while(activo != true);
            }

            return dias; 
    }
    
    public static Date strToDate(String strFecha){
        //formato valido
        strFecha = getStr(strFecha);
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
        Date fecha = null;
        try {

        fecha = formatoDelTexto.parse(strFecha);

        } catch (ParseException ex) {

            ex.printStackTrace();
            fecha = GV.fechaPorDefectoDate();

        }
        
        return fecha;
    }
    
    public static Date getFechaDefault(){
        return GV.strToDate(GlobalValuesVariables.getFechaDefault());
    }

    public static boolean isCurrentDate(Date date) {
        if(GV.dateToString(date, "ddmmyyyy").equals(GV.dateToString(new Date(), "ddmmyyyy"))){
            return true;
        }
        return false;
    }

    public static int obtenerDescuentoFicha(Descuento descuento, int total) {
        int porc = 0;
        int dscto = 0;
        if(descuento != null){
            if(descuento.getPorcetange() > 0){
                porc = descuento.getPorcetange();
                dscto = (total * porc)/100;
            }else{
                dscto = descuento.getMonto();
            }
        }
        return GV.roundPrice(dscto);
    }

    public static void compileJCalendar(JDateChooser jDate) {
        if(GV.getStr(jDate.getDateFormatString().replaceAll("[0-9-]", "")).isEmpty()){
            return;
        }else{
            jDate.setDate(null);
        }
//        jDate.setDateFormatString(jDate.getDateFormatString().replaceAll("[^0-9-]", ""));//filtra sólo numeros y guiones
    }

    public static String toStrHour(int hora, int min) {
        hora = (hora >24)? -1:hora;
        min = (min > 60)? -1:min;
        if(hora < 0 || min < 0){
            return "--:--";
        }
        String h = (hora < 10)? "0": "";
        String m = (min < 10)? "0":"";
        return h+hora+":"+m+min;
    }

    public static void setHourToFicha(JSpinner txth1, JSpinner txtm1, JSpinner txth2, JSpinner txtm2) {
        int h1 = GV.strToNumber(GV.getStr(txth1.getValue().toString()));
        int m1 = GV.strToNumber(GV.getStr(txtm1.getValue().toString()));
        
        int h2 = GV.strToNumber(GV.getStr(txth2.getValue().toString()));
        int m2 = GV.strToNumber(GV.getStr(txtm2.getValue().toString()));
        
        int temp = 0;
        if(h2 < h1){
            temp = h1;
            h1 = h2;
            h2=temp;
        }
        if(h1 == h2 && m2 < m1){
            temp = m1;
            m1=m2;
            m2=temp;
        }
        
        String arg = toStrHour(h1, m1);
        arg = (arg.equals("--:--"))? "":arg+" - ";
        arg = (arg + toStrHour(h2, m2));
        GV.getFicha().setHoraEntrega(GV.getStr(arg.replaceAll("- --:--", "").trim()));
    }

    public static boolean isNumeric(String cadena) {

        boolean resultado;

        try {
            Integer.parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }

        return resultado;
    }
    
    /**
     * debuelbe un arreglo de objetos de tipo string donde [monto][tipoPago][fecha]
     * @param codFicha
     * @return 
     */
    public static Object[][] listarAbonos(String codFicha) {
        
        List<Object> lista = load.listar(GV.convertFichaIdParamToListAbonos(codFicha), new HistorialPago());
        if(lista.size() == 0){return null;}
        String[][] abonos = new String[lista.size()][TAMANO_COLUMN_ABONOS];
        int i = 0;
        
        TipoPago tp = null;
        
        for (Object object : lista) {
            HistorialPago temp = (HistorialPago)object;
            try {
                tp = (TipoPago)load.get(null, temp.getIdTipoPago(), new TipoPago());
            } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(GlobalValuesPrint.class.getName()).log(Level.SEVERE, null, ex);
                tp = null;
            }
            abonos[i][POS_MONTO] = GV.strToPrice(temp.getAbono());
            
            abonos[i][POS_NAME] = (tp!=null)? tp.getNombre():TIPO_PAGO_NO_REGISTRADO;
            
            abonos[i][POS_DATE] = GV.dateToString(temp.getFecha(), "dd/mm/yyyy");
            i++;
        }
        return abonos;
    }
    
    public static int abonoMontoFromArray(int index,Object[][] arrayAbonos){
        return(arrayAbonos.length >index)?strToNumber((String)arrayAbonos[index][POS_MONTO]):0;
    }
    
    public static Date abonoDateFromArray(int index,Object[][] arrayAbonos){
        return(arrayAbonos.length >index)?strToDate((String)arrayAbonos[index][POS_DATE]):null;
    }
    
    public static String abonoNameFromArray(int index,Object[][] arrayAbonos){
        return(arrayAbonos.length >index)?((String)arrayAbonos[index][POS_NAME]):"";
    }

    public static int roundPrice(int price) {
        String temp = ""+price;
        int lastN = GV.strToNumber(temp.substring(temp.length()-1));
        return (lastN > 5)? (price-lastN)+10:price-lastN;
    }
    
    public static SalesReportFicha reportSalesObtain(List<Object> listFicha){
        SalesReportFicha srf = new SalesReportFicha(listFicha);
        return srf;
    }
    
    public static Object searchByIdInList(String code , List<Object> list, Object classType) {
        if(classType instanceof SyncIntId){
            Optional<Object> objectFound = list.stream()
            .filter(p -> ((SyncIntId)p).getId() == GV.strToNumber(code))
            .findFirst();
            return objectFound.isPresent() ? objectFound.get() : null;
        }
        if(classType instanceof SyncStringId){
            Optional<Object> objectFound = list.stream()
            .filter(p -> ((SyncStringId)p).getCod().equals(code))
            .findFirst();
            return objectFound.isPresent() ? objectFound.get() : null;
        }
        return null;
    }

    public static void spinnerNumberDisable(JSpinner spinnerNumber, int currentValue) {
        spinnerNumber.setModel(new SpinnerNumberModel(currentValue, currentValue, currentValue, 1));
        spinnerNumber.setValue(currentValue);
    }
    
    public static String formatRut(String rut) {
        if(rut == null || rut.isEmpty()){
            return "";
        }
        int cont = 0;
        String format;
        rut = rut.replace(".", "");
        rut = rut.replace("-", "");
        format = "-" + rut.substring(rut.length() - 1);
        for (int i = rut.length() - 2; i >= 0; i--) {
            format = rut.substring(i, i + 1) + format;
            cont++;
            if (cont == 3 && i != 0) {
                format = "." + format;
                cont = 0;
            }
        }
        return format;
    }
    
    /**
     * Retorna un where en sql para listar fichas y todos sus datos, si userId y clientCod son null,
     * buscara por fecha, 
     * de lo contratrio validara los userId o clientId
     * @param dateFrom
     * @param dateTo
     * @param idUser
     * @param codClient
     * @param idFicha 
     * @return 
     */
    public static String getWhereFromFicha(Date dateTo, Date dateFrom,String idUser, String codClient, String idFicha){
        if(idFicha!=null){
            return "where f.fch_id = '"+idFicha+"'";
        }
        if(dateTo==null && dateFrom==null){
            if(idUser != null){
                return "where f.usuario_us_id = "+idUser+" and f.fch_estado <> 0 ORDER BY f.fch_fecha DESC";
            }
            if(codClient != null){
                return "where f.cliente_cli_rut = '"+codClient+"' and f.fch_estado <> 0 ORDER BY f.fch_fecha DESC";
            }
            return "where f.fch_estado <> 0 ORDER BY f.fch_fecha DESC";
        }
        dateTo=(dateTo==null)?dateFrom:dateTo;
        dateFrom=(dateFrom==null)?dateTo:dateFrom;
        if(Cmp.localIsNewOrEqual(dateTo, dateFrom)){
            Date aux = dateFrom;
            dateFrom=dateTo;
            dateTo=aux;
        }
        String d1 = (!GV.dateToString(dateTo, "yyyy-mm-dd").equals("date-error"))?GV.dateToString(dateTo, "yyyy-mm-dd"):GV.dateToString(new Date(), "yyyy-mm-dd");
        String d2 = (!GV.dateToString(dateFrom, "yyyy-mm-dd").equals("date-error"))?GV.dateToString(dateFrom, "yyyy-mm-dd"):GV.dateToString(new Date(), "yyyy-mm-dd");
        return "where f.fch_fecha BETWEEN '"+d1+"' and '"+d2+"' and f.fch_estado <> 0 ORDER BY f.fch_fecha DESC";
    }
    
    /**
     * Retorna un where en sql para listar todas las fichas incluidas las anuladas
     * y todos sus datos, si userId y clientCod son null,
     * buscara por fecha, 
     * de lo contratrio validara los userId o clientId
     * @param dateFrom
     * @param dateTo
     * @param idUser
     * @param codClient
     * @param idFicha 
     * @return 
     */
    public static String getWhereFromAllFichas(Date dateTo, Date dateFrom,String idUser, String codClient,String idConvenio, String idFicha){
        if(idFicha!=null){
            return "where f.fch_id = '"+idFicha+"'";
        }
        if(idConvenio != null){
            return "where f.convenio_cnv_id = "+idConvenio+" ORDER BY f.fch_fecha DESC";
        }
        if(dateTo==null && dateFrom==null){
            if(idUser != null){
                return "where f.usuario_us_id = "+idUser+" ORDER BY f.fch_fecha DESC";
            }
            if(codClient != null){
                return "where f.cliente_cli_rut = '"+codClient+"' ORDER BY f.fch_fecha DESC";
            }
            return "where f.fch_fecha ='"+GV.dateToString(new Date(), "yyyy-mm-dd")+"' ORDER BY f.fch_fecha DESC";
        }
        dateTo=(dateTo==null)?dateFrom:dateTo;
        dateFrom=(dateFrom==null)?dateTo:dateFrom;
        if(Cmp.localIsNewOrEqual(dateTo, dateFrom)){
            Date aux = dateFrom;
            dateFrom=dateTo;
            dateTo=aux;
        }
        String d1 = (!GV.dateToString(dateTo, "yyyy-mm-dd").equals("date-error"))?GV.dateToString(dateTo, "yyyy-mm-dd"):GV.dateToString(new Date(), "yyyy-mm-dd");
        String d2 = (!GV.dateToString(dateFrom, "yyyy-mm-dd").equals("date-error"))?GV.dateToString(dateFrom, "yyyy-mm-dd"):GV.dateToString(new Date(), "yyyy-mm-dd");
        return "where f.fch_fecha BETWEEN '"+d1+"' and '"+d2+"' ORDER BY f.fch_fecha DESC";
    }
    
    public static void sendReportSalesMail(SalesReportFicha report, String email, String title){
        Send mail = new Send();
        mail.sendReportSalesMail(report, email, title);
    }
    
    /**
     * Devuelde una fecha de tipo Date con el resultado según parámetro sumaresta ingresado
     * @param fecha
     * @param sumaresta
     * @param opcion Parámetros validos: DAYS, MONTHS o YEARS.
     * @return 
     */
    public static Date SumaRestarFecha(Date fecha, int sumaresta, String opcion){
        if(fecha == null)return null;
        if(fecha instanceof java.sql.Date){
            fecha = new Date(fecha.getTime());
        }
        LocalDate date = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        //Con Java9
        //LocalDate date = LocalDate.ofInstant(input.toInstant(), ZoneId.systemDefault());
        TemporalUnit unidadTemporal = null;
        switch(opcion){
            case "DAYS":
                unidadTemporal = ChronoUnit.DAYS;
                break;
            case "MONTHS":
                unidadTemporal = ChronoUnit.MONTHS;
                break;
            case "YEARS":
                unidadTemporal = ChronoUnit.YEARS;
                break;
            default:
                //Controlar error
        }
        LocalDate dateResultado = date.plus(sumaresta, unidadTemporal);
        return Date.from(dateResultado.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static void createGuaranteeFicha(Ficha ficha) {
        load.createFicha(ficha, null);
        OptionPane.showMsg("Operación finalizada", "Se ha registrado una nueva garantía", 1);
    }

    public static boolean validaRut(String rut) {
        return validarRut(rut);
    }
    
    /**
     * retorna true si la fecha ingresada por parametros es igual
     * o superior a la fecha actual
     * @param date
     * @return 
     */
    public static boolean fechaActualOFutura(Date date){
        if(GV.dateToString(date, "ddmmyyyy")
                .equals(GV.dateToString(new Date(), "ddmmyyyy"))){
            return true;
        }
        return fechaFutura(date);
    }
    
    /**
     * retorna true si la fecha ingresada por parametros es
     * superior a la fecha actual
     * @param date
     * @return 
     */
    public static boolean fechaFutura(Date date){
        return date.after(new Date());
    }
    
    /**
     * retorna true si la fecha ingresada por parametros es inferior
     * a la fecha actual
     * @param date
     * @return 
     */
    public static boolean fechaPasada(Date date){
        if(GV.dateToString(date, "ddmmyyyy")
                .equals(GV.dateToString(new Date(), "ddmmyyyy"))){
            return false;
        }
        return fechaActualOPasada(date);
    }
    
    /**
     * retorna true si la fecha ingresada por parametros es 
     * pasada o igual a la fecha actual
     * @param date
     * @return 
     */
    public static boolean fechaActualOPasada(Date date){
        return date.before(new Date());
    }
    
    public static void updateBDConvenioValidado(Convenio convenio){
        if(convenio.validate()){
            load.update(convenio);
            List<CuotasConvenio> listCuotas = convenio.getCuotasConvenio();
            if(listCuotas.size()>0){
                for (CuotasConvenio cc : listCuotas) {
                    try {
                        load.add(cc);
                    } catch (InstantiationException | IllegalAccessException ex) {
                        Logger.getLogger(GlobalValuesFunctions.class.getName()).log(Level.SEVERE, null, ex);
                        OptionPane.showMsg("Error en BD", "Ocurrió un error al intentar guardar entidades\n"
                                + "CuotasConvenio de la clase Convenio ID:"+convenio.getId(), 3);
                    }
                }
            }
        }
    }
    
    public static boolean cuotasFechaPagoPendiente(Date fechaPagado) {
        return (GV.dateToString(fechaPagado, "dd-mm-yyyy").equals(GlobalValuesVariables.cuotasFechaPagoPendienteDefault()));
    }

    public static void convenioGenerateReport(Convenio cnv) {
        OptionPane.showOptionPanel(new OpanelConvenyReceptor(cnv), OptionPane.titleConvenyDataReceptor());
    }

    public static void sendMailFicha(Ficha ficha) {
        Send send = new Send();
        send.sendFichaMail(ficha);
    }

    public static User validar(String username, String pass) {
        User user = null;
        try {
            user = (User)load.get(username, 0, new User());
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(GlobalValuesFunctions.class.getName()).log(Level.SEVERE, null, ex);
            OptionPane.showMsg("Error al cargar usuario", "Ocurrió un error inesperado al validar usuario:\n"
                    + ex.getMessage(), 3);
        }
        if(user!=null){
            if(user.getEstado() == 0){
                OptionPane.showMsg("Acceso denegado", "El usuario se encuentra anulado", 2);
                return null;
            }
            if(GV.dsC(user.getPass()).equals(pass)){
                return user;
            }else{
                OptionPane.showMsg("Acceso denegado", "Clave de acceso inválida", 2);
            }
        }else{
            OptionPane.showMsg("Acceso denegado", "El usuario no existe", 2);
        }
        return null;
    }
    
    public static void showRegistroLicencia(){
        OptionPane.showOptionPanel(new OpanelSetLicencia(), OptionPane.titleRegistrarLicencia());
    }
    
    public static void showRegistroLicenciaPaso2() {
        OptionPane.showOptionPanel(new OpanelCompanyData(1), OptionPane.titleCompanyDataCreate());
    }
    
    public static void showRegistroLicenciaPaso3() {
        OptionPane.showOptionPanel(new OpanelOfficeData(1), OptionPane.titleOfficeDataCreate());
    }

    public static boolean licenciaComprobateOnline(String arg) {
        if(!KeyValid(arg)){licMsg("Debe ingresar una licencia válida.",2);return false;}
        if(!GV.isOnline()){licMsg("No tienes conexión, debes conectarte a internet primero.", 2);return false;}
        String licencia = GlobalValuesXmlFiles.getLicenciaOnline(keyGetLicencia(keyResolve(arg)),keyGetUrl(keyResolve(arg)));
        if(licencia == null){licMsg("Los datos ingresados son erróneos, consulte con su proveedor.", 2);return false;}
        try {
            if(load.get(licencia, 0, new Equipo())!=null){licMsg("Los datos ingresados son erróneos, Licencia duplicada.", 3);return false;}
                
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(GlobalValuesFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        validaToken(GlobalValuesXmlFiles.getTipoPlanOnline(keyGetLicencia(keyResolve(arg)),keyGetUrl(keyResolve(arg))),licencia,arg);
        
        return true;
    }
    
    private static void setLicenciaAsignarValoresPaso1(String licencia,String arg){
        GV.setSyncCount(0);
        
        GV.username("admin");
        GV.licenciaTipoPlan(GlobalValuesXmlFiles.getTipoPlanOnline(keyGetLicencia(keyResolve(arg)),keyGetUrl(keyResolve(arg))));
        
        GV.setLicenceCode(licencia);
        GV.expDate(GlobalValuesXmlFiles.getExpDateOnline(keyGetLicencia(keyResolve(arg)),keyGetUrl(keyResolve(arg))));
        GV.setCurrentEquipo(licencia+"_"+GV.dateToString(new Date(), "yyyymmddhhmmss"));
        GV.setUri(keyGetUrl(keyResolve(arg)));
        GV.setPort(keyGetPass(keyResolve(arg)));
        GV.setLastUpdateFromXml(GV.strToDate(GlobalValuesVariables.getFechaDefault()));
        OptionPane.closeOptionPanel();
        GV.licenciaRegistroPaso2();
    }

    private static void licMsg(String msg,int status) {
        OptionPane.showMsg("Error de licencia", msg, status);
    }

    public static void licenciaRegistroPasoFinished() {
        GlobalValuesXmlFiles.crearRegistroLocal();
        
        System.exit(0);
    }

    private static void validaToken(int tipoPlan,String licencia,String key) {
        if(tipoPlan != GlobalValuesVariables.licenciaTipoFree() && 
           tipoPlan != GlobalValuesVariables.licenciaTipoLocal()){
            OptionPane.showOptionPanel(new OpanelSetToken(key), OptionPane.titleRegistrarToken());
        }else{
            setLicenciaAsignarValoresPaso1(licencia, key);
        }
    }
    
    public static void asignarToken(String token,String key){
        if(GV.getStr(token).isEmpty())return;
        String licencia = keyGetLicencia(keyResolve(key));
        String bd = tokenGetBd(keyResolve(token));
        String bdUser = tokenGetBdUser(keyResolve(token));
        String bdPass = tokenGetBdPass(keyResolve(token));
        String bdUrl = tokenGetBdUrl(keyResolve(token));
        GlobalValuesBD.BD_NAME_REMOTE = bd;
        GlobalValuesBD.BD_USER_REMOTE = bdUser;
        GlobalValuesBD.BD_PASS_REMOTE = bdPass;
        GlobalValuesBD.BD_URL_REMOTE = bdUrl;
        setLicenciaAsignarValoresPaso1(licencia, key);
    }

    public static boolean licenciaIsEnableToSendMails() {
        return (GV.licenciaTipoPlan() != GlobalValuesVariables.licenciaTipoFree() && 
                GV.licenciaTipoPlan() != GlobalValuesVariables.licenciaTipo2X());
    }

    public static boolean licenciaIsEnableToSendInternMessages() {
        return (GV.licenciaTipoPlan() != GlobalValuesVariables.licenciaTipo2X() && 
                GV.licenciaTipoPlan() != GlobalValuesVariables.licenciaTipoFree());
    }

    /**
     * Obliga al usuario a sincronizar datos para evitar perdida importante de información
     */
    public static void sincronizeOrClose() {
        if(OptionPane.getConfirmation("Sincronización inicial", "Todos los datos deben ser sincronizados para que el sistema "
                + "funcione correctamente,\n el tiempo de espera puede ser largo dependiendo de los registros "
                + "almacenados\n en la base de datos remota.\n"
                + "Asegúrese de que su conexión a internet sea rápida para evitar posibles problemas de registro\n"
                + "de lo contrario inicie el sistema mas tarde."
                + "\n ¿Desea sincronizar los datos ahora?", 2)){
            GV.setSyncCount(0);
            sincronizarTodo();
        }else{
            OptionPane.showMsg("Operación cancelada", "El sistema no puede iniciar sin la sincronización,\n"
                    + "vuelva a intentarlo mas tarde.", 2);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(GV.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.exit(0);
        }
    }
    
    public static void licenciaComprobarValidez(){
        int expDias = fechaDiferencia(GV.strToDate(GV.expDate())); 
        if(expDias <= 5){
            if(expDias > 1){
                OptionPane.showMsg("Renueve su licencia", "Su licencia expirará dentro de "+expDias+" días", 2);
            }
            if(expDias == 1){
                OptionPane.showMsg("Renueve su licencia", "Su licencia expirará mañana", 2);
            }
            if(expDias == 0){
                OptionPane.showMsg("Renueve su licencia", "Su licencia expirará hoy", 2);
            }
            if(expDias < 0){
                OptionPane.showMsg("Renueve su licencia", "Su licencia ha caducado", 2);
            }
        }
    }
    
    public static boolean licenciaExpirada(){
        return GV.fechaPasada(GV.strToDate(GV.expDate()));
    }

    public static int fechaDiferencia(Date date) {
        try {
            if(date == null) return 0;
            String stFecha = dateToString(date, "dd/mm/yyyy");
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            cal.setTime(sdf.parse(stFecha));
            int dias = 0;
            if(cal.compareTo(Calendar.getInstance())>=0){
                Date fecha=cal.getTime();
                DateFormat dd = new SimpleDateFormat("dd/MM/yyyy");       
                boolean activo = false;
                Calendar calendar; Date aux;
                do{
                    calendar = Calendar.getInstance();           
                    calendar.add(Calendar.DAY_OF_YEAR, dias);
                    aux = calendar.getTime();
                    if(dd.format(aux).equals(dd.format(fecha)))
                        activo = true;
                    else
                        dias++;
                }while(activo != true);
            }else{
                Date fecha=cal.getTime();
                DateFormat dd = new SimpleDateFormat("dd/MM/yyyy");       
                boolean activo = false;
                Calendar calendar; Date aux;
                do{
                    calendar = Calendar.getInstance();           
                    calendar.add(Calendar.DAY_OF_YEAR, dias);
                    aux = calendar.getTime();
                    if(dd.format(aux).equals(dd.format(fecha)))
                        activo = true;
                    else
                        dias--;
                }while(activo != true);
            }
            
            return dias; 
        } catch (ParseException ex) {
            Logger.getLogger(GlobalValuesFunctions.class.getName()).log(Level.SEVERE, null, ex);
            OptionPane.showMsg("Error al calcular dias", "Ocurrió un error inesperado...", 3);
        }
        return 0;
    }

    public static boolean usernameYaExiste(String username) {
        return load.usernameYaExiste(username);
    }
    
    
    public void convenioGenerarReporte(Convenio cnv){
        if(cnv.getEstado() == 2){
            
        }else{
            OptionPane.showMsg("No se puede generar el reporte", "El convenio debe estar generado,\n"
                    + "el sistema no adminte convenios anulados ni activos.", 2);
        }
    }
    
    /**
     * registra en la base de datos la cantidad de cuotas ingresadas por parametros,
     * se ejecutan validaciones, si guarda los datos, retornará true.
     * @param cnv
     * @param cuotas
     * @param fechaRegistro
     * @return 
     */
    public static boolean convenioPagarCuotas(Convenio cnv,int cuotas,Date fechaRegistro, int tipoPago){
        if(OptionPane.getConfirmation("Confirmar datos", "Si confirmas los datos, no se podrán revertir.\n"
                + "¿Estás seguro que los datos son correctos?", 2)){
            int suma = 0;
            if(tipoPago == 0){OptionPane.showMsg("Error de datos", "Debes ingresar un tipo de pago válido", 2);return false;}
            if(cnv == null){OptionPane.showMsg("Error de datos", "El convenio no se puede procesar", 2);return false;}
            if(cnv.getEstado() < 2){OptionPane.showMsg("Error de datos", "El convenio no se puede procesar", 2);return false;}
            if(cnv.getEstado() > 2){OptionPane.showMsg("Error de datos", "El convenio no se puede procesar ya que no posee saldos pendientes", 2);return false;}
            if(!Cmp.localIsNewOrEqual(fechaRegistro, cnv.getFechaCobro())){OptionPane.showMsg("Error de datos", "El convenio no se puede procesar, la fecha ingresada no puede ser inferior a la primera fecha de pago", 2);return false;}
            if(!GV.fechaActualOPasada(fechaRegistro)){OptionPane.showMsg("Error de datos", "El convenio no se puede procesar, la fecha ingresada debe ser actual o pasada", 2);return false;}
            if(cuotas > cnv.getCuotas()){OptionPane.showMsg("Error de datos", "El convenio no se puede procesar, la cantidad de cuotas ingresada no es correcta", 2);return false;}
            
            int cont = 0;
            for (CuotasConvenio cc : cnv.getCuotasConvenio()) {
                if(cc.getEstado() == 1 && suma < cuotas){
                    cc.setEstado(2);
                    cc.setFechaPagado(fechaRegistro);
                    cc.setIdTipoPago(tipoPago);
                    load.update(cc);
                    suma++;
                }
                cont++;
                //valido si todas las cuotas están pagadas
                if(cont == cnv.getCuotas() && cc.getEstado() == 2){
                    cnv.setEstado(3);
                    
                    load.update(cnv);
                    
                    fichasPagarTodo(GlobalValuesBD.listarAllFichas(null, null, null, null, ""+cnv.getId(), null),fechaRegistro,1);
                }
            }
            return true;
        }else
        {OptionPane.showMsg("Operación cancelada", "El convenio no se ha procesado porque se anuló la operación", 1);return false;}
    }
    
    /**
     * Marca todas las fichas que recibe por parametros como pagadas
     * y almacena los cambios en la base de datos
     * @param lista 
     */
    public static void fichasPagarTodo(List<Object> lista,Date fechaPago,int tipoPago){
        HistorialPago hp = null;
        for (Object object : lista) {
            try {
                Ficha fch = (Ficha)object;
                hp = new HistorialPago(null, fechaPago, fch.getSaldo(), tipoPago, fch.getCod(), 1, null, 0);
                fch.setSaldo(0);
                if(fch.getEstado() == GV.estadoFichaPending()){
                    fch.setEstado(GV.estadoFichaPaid());
                }
                
                load.add(hp);
                load.update(fch);
            } catch (InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(GlobalValuesFunctions.class.getName()).log(Level.SEVERE, null, ex);
                OptionPane.showMsg("Error", "Ocurrió un error inesperado al intentar registrar una lista de fichas como pagadas\n"
                        + ex.getMessage(), 3);
            }
        }
    }
    
    
    public static void licenciaComprobate(){
        boolean activa = (GV.licenciaTipoPlan() > 0 && 
                GV.fechaActualOFutura
            (
                    GV.strToDate(
                            GV.expDate()
                    )
            )
        )?true:false;
        GlobalValuesVariables.licencaActiva(activa);
    }
    
    public static String licenciaEstado(){
        String status = "Cambiate a premium";
        if(GV.licenciaTipoPlan() > 0){
            status = (GV.fechaActualOFutura(GV.strToDate(GV.expDate())))?
                    "Bajo licencia hasta el "+GV.expDate().replaceAll("-", "."):
                    "La licencia ha caducado";
            status = (GV.isCurrentDate(GV.strToDate(GV.expDate())))?"Expirará hoy":status;
            status = (GV.isCurrentDate(GV.dateSumaResta(GV.strToDate(GV.expDate()), -1, "DAYS")))?"Expirará mañana":status;
        }
        if(GV.licenciaTipoPlan()==GlobalValuesVariables.licenciaTipoFree()){
            status = "Licencia gratuita: "+status;
        }
        if(GV.licenciaTipoPlan()==GlobalValuesVariables.licenciaTipo2X()){
            status = "Licencia 2x: "+status;
        }
        if(GV.licenciaTipoPlan()==GlobalValuesVariables.licenciaTipo4X()){
            status = "Licencia 4x: "+status;
        }
        if(GV.licenciaTipoPlan()==GlobalValuesVariables.licenciaTipo6X()){
            status = "Licencia 6x: "+status;
        }
        if(GV.licenciaTipoPlan()==GlobalValuesVariables.licenciaTipoFullData()){
            status = "Licencia FullData: "+status;
        }
        return status;
    }
    
    public static String dsCrypt(String textContent) {
        String base64EncryptedString = "";
        if(GV.getStr(textContent).isEmpty()){
            return "";
        }
        try {
            byte[] message = Base64.decodeBase64(textContent.getBytes("utf-8"));
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(GV.salt().getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
 
            Cipher decipher = Cipher.getInstance("DESede");
            decipher.init(Cipher.DECRYPT_MODE, key);
 
            byte[] plainText = decipher.doFinal(message);
 
            base64EncryptedString = new String(plainText, "UTF-8");
 
        } catch (Exception ex) {
        }
        return base64EncryptedString;
    }
    
    public static String enCrypt(String texto) {
        if(GV.getStr(texto).isEmpty()){
            return "";
        }
        String base64EncryptedString = "";
 
        try {
 
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(GV.salt().getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
 
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);
 
            byte[] plainTextBytes = texto.getBytes("utf-8");
            byte[] buf = cipher.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buf);
            base64EncryptedString = new String(base64Bytes);
 
        } catch (Exception ex) {
        }
        return base64EncryptedString;
    }
    
    public static int currentSyncCount(){
        GlobalValuesXmlFiles.loadSyncCount();
        return GlobalValuesVariables.getSyncCount();
    }
    
    public static boolean syncEnabled(){
        int tp = GV.licenciaTipoPlan();
        int count = GV.getSyncCount();
        if(tp==GlobalValuesVariables.licenciaTipoFullData())return true;
        if(count<0){
            OptionPane.showMsg("Registos adulterados", "No es posible continuar porque los archivos del sistema\n"
                    + " se encuentran corrompidos, esto puede causar daños irreversibles en el sistema", 3);
            GV.setSyncCount(GlobalValuesVariables.TP_6X_MS*1000);
            return false;
        }
        if(tp==GlobalValuesVariables.licenciaTipo2X()){
            return (count < GlobalValuesVariables.TP_2X_MS);
        }
        if(tp==GlobalValuesVariables.licenciaTipo4X()){
            return (count < GlobalValuesVariables.TP_4X_MS);
        }
        if(tp==GlobalValuesVariables.licenciaTipo6X()){
            return (count < GlobalValuesVariables.TP_6X_MS);
        }
        return false;
    }
    
    public static void currentSyncCount(int value){
        GlobalValuesVariables.setSyncCount(value);
        GlobalValuesXmlFiles.saveSyncCount();
    }
    
    private static boolean KeyValid(String key){
        String unKey = keyResolve(key);
        String[] parts = unKey.split("=");
        int cont = 0;
        for (String part : parts) {
            cont++;
            if(GV.getStr(part).isEmpty())
                return false;
        }
        return (cont == 3)?true:false;
    }
    
    public static String keyGenerate(String url,String licencia, String pass){
        return GV.enC(url+"="+licencia+"="+pass);
    }
    
    public static String tokenGenerate(String bd,String bdUser, String bdPass, String bdUrl){
        return GV.enC(bd+"="+bdUser+"="+bdPass+"<"+bdUrl);
    }
    
    private static String keyResolve(String key){
        return GV.dsC(key);
    }
    
    private static String keyGetUrl(String unKey){
        return unKey.substring(0,unKey.indexOf("="));
    }
    
    public static String tokenGetBd(String unKey){
        return unKey.substring(0,unKey.indexOf("="));
    }
    
    private static String keyGetLicencia(String unKey){
        return unKey.substring(unKey.indexOf("=")+1,unKey.lastIndexOf("="));
    }
    
    public static String tokenGetBdUser(String unKey){
        return unKey.substring(unKey.indexOf("=")+1,unKey.lastIndexOf("="));
    }
    
    private static String keyGetPass(String unKey){
        return unKey.substring(unKey.lastIndexOf("=")+1);
    }
    
    public static String tokenGetBdPass(String unKey){
        return unKey.substring(unKey.lastIndexOf("=")+1,unKey.lastIndexOf("<"));
    }
    
    public static String tokenGetBdUrl(String unKey){
        return unKey.substring(unKey.lastIndexOf("<")+1);
    }
    
    public static void fichasToDelivery(List<Object> fichas){
        if(GV.tipoUserSuperAdmin()){
            if(OptionPane.getConfirmation("Confirmar modificación", "¿Estás seguro que deseas despachar todos los registros filtrados?", 2)){
                for (Object ficha : fichas) {
                    if(((Ficha)ficha).getEstado() != GV.estadoFichaDeleted() && 
                       ((Ficha)ficha).getEstado() != GV.estadoFichaDelivered()){
                        try {
                            ((Ficha)ficha).setEstado(GV.estadoFichaDelivered());
                            ((Ficha)ficha).setObservacion(((Ficha)ficha).getObservacion()+"\n"
                                    + "==Despacho generado por defecto en el sistema=Autor: "+GV.user().getNombre()+"==");
                            Despacho d = new Despacho(null, ((Ficha)ficha).getCliente().getCod(),
                                    ((Ficha)ficha).getCliente().getNombre(), ((Ficha)ficha).getFechaEntrega(),
                                    ((Ficha)ficha).getCod(), 1, null, 0);
                            load.update(ficha);
                            load.add(d);
                        } catch (InstantiationException | IllegalAccessException ex) {
                            Logger.getLogger(GlobalValuesFunctions.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }
    }
}
