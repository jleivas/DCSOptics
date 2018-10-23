/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testsync;

import bd.LcBd;
import fn.print.jcPrint;
import dao.Dao;
import entities.Cliente;
import entities.Convenio;
import entities.Cristal;
import entities.CuotasConvenio;
import entities.Descuento;
import entities.Doctor;
import entities.Equipo;
import entities.Institucion;
import entities.Inventario;
import entities.Lente;
import entities.Oficina;
import entities.RegistroBaja;
import entities.TipoPago;
import entities.User;
import entities.context.SalesReportFicha;
import entities.ficha.Armazon;
import entities.ficha.Despacho;
import entities.ficha.EtiquetFicha;
import entities.ficha.Ficha;
import entities.ficha.HistorialPago;
import fn.GV;
import static fn.GV.dateToString;
import fn.OptionPane;
import fn.SubProcess;
import fn.date.Cmp;
import fn.globalValues.GlobalValuesBD;
import fn.globalValues.GlobalValuesFunctions;
import fn.globalValues.GlobalValuesSaveXls;
import fn.globalValues.GlobalValuesSyncReportStatus;
import fn.globalValues.GlobalValuesVariables;
import fn.globalValues.GlobalValuesXmlFiles;
import fn.mail.Send;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jxl.write.WriteException;
import sync.entities.Local;
import sync.entities.LocalInventario;
import sync.entities.Migrar;
import sync.entities.Remote;
import view.init.SplashProgress;

/**
 *
 * @author jorge
 */
public class TestSync {

    private static String ID_PARAM_IS_USER = "USER/";
    private static String ID_PARAM_IS_CLIENT = "CLIENT/";
    private static Dao load = new Dao();
    private static List<Object> lista = null;
    private static Migrar mig = new Migrar();
    private static Remote rem = new Remote();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException, UnknownHostException, IOException, InstantiationException, IllegalAccessException{
       //todas las fichas tendran id con guion uno, no se deberá utilizar mas
//       GV.loadLastUpdateFromXML();
       String id = "las-sda-ads[1]";
        System.out.println(id.substring(0,id.indexOf("[")));
        //rosa MAR123
    }
    
    public static String getStr(String arg){
        if(arg == null || arg.replaceAll(" ", "").isEmpty())
            return "";
        else{
            String value = arg.replaceAll("[-+^:‘´'{}]","");
            return (value.startsWith(" "))?value.replaceFirst(" ", "").trim():value.trim();
        }  
    }
    
    private static void testStr(){
        String tel = " / 282372332";
        System.out.println("*"+mig.getTel1(tel)+"*");
        System.out.println("*"+mig.getTel2(tel)+"*");
   }
    
    private static void licencias(){
        //        String key = GlobalValuesFunctions.keyGenerate("http://softdirex.cl/optidata/validate.xml", "test2", "20075321818");
//        System.out.println(key);
        LcBd.crear();
//        GlobalValuesFunctions.tokenGenerate(bd, bdusr, bdpass, bdurl);
//KEY:   KVoKmAZvJrJR51HdmmBEOdzxVtpV39pwsBKQSZblXzXw1g7D0JzTt+hVBZzGiA5/0Iz6egkvfl2oFXoAN/WDKA==
//TOKEN: w04gbOCdBE3jkNUYjIGGfXHds2l2IQJihmr2W+vxY8LuTI5u6fHUsg2M2rdgZ9lCZ2Z+4osHNNUVW0gZkUEIY5QH79dzbglp
    }
   
    private static void reiniciar(){
        GlobalValuesBD.dropDB();
//        LcBd.crear();
        
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
        int maxId = rem.getMaxId(new Cristal());
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
        int maxId = rem.getMaxId(new Descuento());
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

    private static void migrarLentes() {
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
