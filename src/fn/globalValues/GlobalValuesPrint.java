/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn.globalValues;

import dao.Dao;
import entities.Cliente;
import entities.Convenio;
import entities.ficha.Ficha;
import fn.CalibracionGlobal;
import fn.GV;
import fn.OptionPane;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.PrintJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import reportes.CuotasConvenioRecursoDatos;
import reportes.FichaDataSource;
import reportes.FichaRecursoDatos;
import reportes.FichasConvenioRecursoDatos;
import reportes.SalesFichaRecursoDatos;
import view.ContentAdmin;

/**
 *
 * @author jlleivas
 */
public class GlobalValuesPrint {
    
    public static void printFichaView(Ficha ficha){
        InputStream is = null;
        JasperPrint jsp = null;
        FichaDataSource dt = new FichaDataSource();
//        for (int i = 0; i < 5; i++) {
        dt.addFicha(ficha);
//        }
        try{
            is = new FileInputStream("src"+File.separator+"reportes"+File.separator+"ficha.jrxml");
        }catch(FileNotFoundException e){
            OptionPane.showMsg("No se puede obtener el recurso", 
                    "Ocurrió un error al intentar abrir el formato de impresión\n"
                            + e.getMessage(), 3);
        }
        
        
        try{
            JasperDesign jsd = JRXmlLoader.load(is);
            JasperReport jsrp = JasperCompileManager.compileReport(jsd);
            jsp = JasperFillManager.fillReport(jsrp, null,dt);
            JasperViewer viewer = new JasperViewer(jsp, false); //Se crea la vista del reportes
            viewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Se declara con dispose_on_close para que no se cierre el programa cuando se cierre el reporte
            viewer.setVisible(true); //Se vizualiza el reporte
        }catch(JRException e){
            OptionPane.showMsg("No se puede visualizar el recurso", 
                    "Ocurrió un error al intentar abrir visualización del formato de impresión\n"
                            + e.getMessage(), 3);
        }
    }
    
    public static void printFichas(List<Object> fichas) {
        InputStream is = null;
        JasperPrint jsp = null;
        FichaRecursoDatos dt = new FichaRecursoDatos();
        dt.addTitle(ContentAdmin.lblTitle.getText(), "Documento generado por "+GV.projectName());
        for (Object ficha : fichas) {
            dt.addFicha((Ficha)ficha);
        }
        try{
            is = new FileInputStream("src"+File.separator+"reportes"+File.separator+"fichas.jrxml");
        }catch(FileNotFoundException e){
            OptionPane.showMsg("No se puede obtener el recurso", 
                    "Ocurrió un error al intentar abrir el formato de impresión\n"
                            + e.getMessage(), 3);
        }
        
        
        try{
            JasperDesign jsd = JRXmlLoader.load(is);
            JasperReport jsrp = JasperCompileManager.compileReport(jsd);
            jsp = JasperFillManager.fillReport(jsrp, null,dt);
            JasperViewer viewer = new JasperViewer(jsp, false); //Se crea la vista del reportes
            viewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Se declara con dispose_on_close para que no se cierre el programa cuando se cierre el reporte
            viewer.setVisible(true); //Se vizualiza el reporte
//            generateReport(jsp, true, "src"+File.separator+"reportes"+File.separator+"fichasConvenio.xls");
        }catch( JRException e){
            OptionPane.showMsg("No se puede visualizar el recurso", 
                    "Ocurrió un error al intentar abrir visualización del formato de impresión\n"
                            + e.getMessage(), 3);
        }
    }
    
    public static void print(Ficha ficha)
	{
            printFichaView(ficha);
//            String formatoAbono = obtenerAbonos(ficha.getCod());
//            ///////////////////////////FECHAS
//            String fecha = GV.dateToString(ficha.getFecha(),"dd/mm/yyyy");
//            String fecha_entrega = GV.dateToString(ficha.getFechaEntrega(),"dd/mm/yyyy");
//            //////////////////////////FIN FECHAs
//            String endurecidoLejos=(ficha.getLejos().getEndurecido()==1)?"X":"";
//            String capaLejos=(ficha.getLejos().getCapa()==1)?"X":"";
//            String plusMaxLejos=(ficha.getLejos().getPlusMax()==1)?"X":"";
//            
//            
//            
//            String endurecidoCerca=(ficha.getCerca().getEndurecido()==1)?"X":"";
//            String capaCerca=(ficha.getCerca().getCapa()==1)?"X":"";
//            String plusMaxCerca=(ficha.getCerca().getPlusMax()==1)?"X":"";
//
//            int precioLente1 = getPrecioLente(ficha.getCerca().getMarca());
//            int precioLente2 = getPrecioLente(ficha.getLejos().getMarca());
//            int precioCristal1 = getPrecioCristal(ficha.getCerca().getCristal());
//            int precioCristal2 = getPrecioCristal(ficha.getLejos().getCristal());
//
//            int descuento = ficha.getDescuento();
//            
//            String impresion[]=
//            {
//             fecha//0
//            ,ficha.getCod()+""//1
//            ,ficha.getLugarEntrega()//2
//            ,fecha_entrega//3
//            ,ficha.getCliente().getNombre()//4
//            ,ficha.getLejos().getCristal()//5
//            ,ficha.getLejos().getOdEsf()//6
//            ,ficha.getLejos().getOiEsf()//7
//            ,ficha.getLejos().getDp()+""//8
//            ,endurecidoLejos//9
//            ,capaLejos//10
//            ,plusMaxLejos//11----fin lejos
//            ,ficha.getCerca().getCristal()//12
//            ,ficha.getCerca().getOdEsf()//13
//            ,ficha.getCerca().getOiEsf()//14
//            ,ficha.getCerca().getDp()+""//15
//            ,endurecidoCerca//16
//            ,capaCerca//17
//            ,plusMaxCerca//18
//            ,ficha.getObservacion()//19
//            ,GV.strToPrice((ficha.getValorTotal()-ficha.getDescuento()))//20
//            ,formatoAbono//21
//            ,GV.strToPrice(ficha.getSaldo())//22
//            ,ficha.getHoraEntrega().replaceAll(" ", "")//23
//            ,ficha.getLejos().getMarca()//24
//            ,ficha.getCerca().getMarca()//25
//            ,GV.companyName()//26
//            ,GV.getOficinaWeb()//27
//            ,GV.getOficinaAddress()+"-"+GV.getOficinaCity()//28
//            ,GV.getOficinaMail()+"/"+GV.getOficinaPhone1()+"-"+GV.getOficinaPhone2()//29
//            ,ficha.getLejos().getOdCil()//30 desde aqui
//            ,ficha.getLejos().getOdA()//31
//            ,ficha.getLejos().getOiCil()//32
//            ,ficha.getLejos().getOiA()//33
//            ,ficha.getCerca().getOdCil()//34
//            ,ficha.getCerca().getOdA()//35
//            ,ficha.getCerca().getOiCil()//36
//            ,ficha.getCerca().getOiA()//37
//            ,ficha.getCerca().getAdd()//38
//            ,obtenerFormatoCliente(ficha.getCliente())//39
//            ,obtenerFormatoDireccionCliente(ficha.getCliente())//40
//            ,"TOTAL: "+GV.strToPrice((ficha.getValorTotal()-ficha.getDescuento()))+", ABONO: "+formatoAbono.replaceAll("_", "")+", SALDO: "+ GV.strToPrice(ficha.getSaldo())//41
//            ,ficha.getLejos().getMarca()//42
//            ,GV.strToPrice(precioLente2)//43
//            ,ficha.getLejos().getCristal()//44
//            ,GV.strToPrice(precioCristal2)//45
//            ,ficha.getCerca().getMarca()//46
//            ,GV.strToPrice(precioLente1)//47
//            ,ficha.getCerca().getCristal()//48
//            ,GV.strToPrice(precioCristal1)//49
//            ,strDescuento(ficha.getDescuento())//50
//            ,strDetalleDescuento(ficha.getDescuento())//51
//            };
//
//            imprimir(impresion); 
	}
 
    private static void imprimir(String[] impresion){
        try {
                            imprimir_Cita(impresion);
        } catch (Exception e) {
            OptionPane.showMsg("No se imprimirá", "La impresion se ha cancelado", 2);
            return;
        }

    }
        
        
        private static void imprimir_Cita(String [] text)
        {
            String fileName = "Folio-"+text[1].replaceFirst("/", "-eq-")+"["+GV.dateToString(new Date(), "dd-mm-yy")+"]";
            int calTitulos1 = CalibracionGlobal.ALT_TITULOS;
            int calLejos2 = CalibracionGlobal.ALT_LENTE_LEJOS;
            int calLCerca3 = CalibracionGlobal.ALT_LENTE_CERCA;
            int calObs4 = CalibracionGlobal.ALT_OBS;
            int calResumenComprobante5 = CalibracionGlobal.ALT_RESUMEN;
            int calDetalleComprobante6 = CalibracionGlobal.ALT_DETALLE;
            int calDatosEntrega6 = CalibracionGlobal.ALT_DATOS_ENTREGA;
            int calValores = CalibracionGlobal.L_VALORES;
        
            
        Frame f = new Frame ("Imprimir Folio "+text[1].replaceFirst("/", " Equipo "));
        f.pack();
        PrintJob pjob = f.getToolkit().getPrintJob(f,fileName,null);
        Graphics pg = pjob.getGraphics();
                            // fuente  ,          , tamaño de la fuente
        pg.setFont(new Font ("Arial", Font.ITALIC, 10)); 
         //dibuja el string guardado en el objeto texto de la clase String, y lo sitúa en la posición cuyas coordenadas vienen dadas por los dos números enteros que le siguen.
                                 //x , y //el aumenta 20 por fila    //81
           pg.drawString(text[0], 105 + CalibracionGlobal.L_FECHA, (80+calTitulos1));//fecha
           pg.drawString(text[1], 465 + CalibracionGlobal.L_FOLIO, (80+calTitulos1));//folio
           pg.drawString(text[2], 150 + CalibracionGlobal.L_LUGAR_ENTREGA_1, (100+calTitulos1));//lugar_entrega
           pg.drawString(text[3]+"-"+text[23], 465 + CalibracionGlobal.L_FECHA_ENTREGA_1, (100+calTitulos1));//fecha_entrega y hora
           pg.drawString(text[39], 123 + CalibracionGlobal.L_DATOS_CLIENTE, (119+calTitulos1));//datos_cliente
           pg.drawString(text[40], 123 + CalibracionGlobal.L_DIRECCION_CLIENTE, (130+calTitulos1));//direccion_cliente
           pg.setFont(new Font ("Arial", Font.BOLD, 10)); 
           pg.drawString(text[41], 123 + CalibracionGlobal.L_VALOR_TOTAL_1, (141+calTitulos1));//valor_total
           pg.setFont(new Font ("Arial", Font.ITALIC, 10)); 
           pg.drawString(text[24], 123 + CalibracionGlobal.L_LEJOS_MARCA, (147+calLejos2));//lejos_marca------inicio lejos
           pg.drawString(text[5], 123 + CalibracionGlobal.L_LEJOS_CRISTAL, (158+calLejos2));//lejos_cristal
           pg.drawString(text[6], 123 + CalibracionGlobal.L_LEJOS_OD_ESF, (169+calLejos2));//OD_ESF
           pg.drawString(text[30], 175 + CalibracionGlobal.L_LEJOS_OD_CIL, (170+calLejos2));//OD_CIL
           pg.drawString(text[31], 290 + CalibracionGlobal.L_LEJOS_OD_A, (170+calLejos2));//OD_A
           pg.drawString(text[7], 123 + CalibracionGlobal.L_LEJOS_OI_ESF, (180+calLejos2));//OI_ESF
           pg.drawString(text[32], 175 + CalibracionGlobal.L_LEJOS_OI_CIL, (180+calLejos2));//OI_CIL
           pg.drawString(text[33], 290 + CalibracionGlobal.L_LEJOS_OI_A, (180+calLejos2));//OI_A
           pg.drawString(text[8], 123 + CalibracionGlobal.L_LEJOS_DP_CRISTAL, (192+calLejos2));//DP_cristal
           pg.setFont(new Font ("Verdana", Font.BOLD, 10));// X
           pg.drawString(text[9], 280 + CalibracionGlobal.L_LEJOS_ENDURECIDO_CRISTAL, (192+calLejos2));//endurecido_cristal
           pg.drawString(text[10],345 + CalibracionGlobal.L_LEJOS_CAPA_CRISTAL, (192+calLejos2));//capa_cristal
           pg.drawString(text[11],430 + CalibracionGlobal.L_LEJOS_PLUS_MAX, (192+calLejos2));//plis_max_cristal ----fin lejos
           pg.setFont(new Font ("Arial", Font.ITALIC, 10));
           pg.drawString(text[25], 123 + CalibracionGlobal.L_CERCA_MARCA, (230+calLCerca3));//cerca_marca------inicio cerca
           pg.drawString(text[12], 123 + CalibracionGlobal.L_CERCA_CRISTAL, (241+calLCerca3));//cerca_cristal
           pg.drawString(text[38], 465 + CalibracionGlobal.L_CERCA_ADD, (241+calLCerca3));//ADD
           pg.drawString(text[13], 123 + CalibracionGlobal.L_CERCA_OD_ESF, (252+calLCerca3));//OD_cristal
           pg.drawString(text[34], 175 + CalibracionGlobal.L_CERCA_OD_CIL, (252+calLCerca3));//OD_CIL
           pg.drawString(text[35], 293 + CalibracionGlobal.L_CERCA_OD_A, (252+calLCerca3));//OD_A
           pg.drawString(text[14], 123 + CalibracionGlobal.L_CERCA_OI_ESF, (263+calLCerca3));//OI_cristal
           pg.drawString(text[36], 175 + CalibracionGlobal.L_CERCA_OI_CIL, (263+calLCerca3));//OI_CIL
           pg.drawString(text[37], 293 + CalibracionGlobal.L_CERCA_OI_A, (263+calLCerca3));//OI_A
           pg.drawString(text[15], 123 + CalibracionGlobal.L_CERCA_DP_CRISTAL, (274+calLCerca3));//DP_cristal
           pg.setFont(new Font ("Verdana", Font.BOLD, 10));// X
           pg.drawString(text[16], 280 + CalibracionGlobal.L_CERCA_ENDURECIDO_CRISTAL, (274+calLCerca3));//endurecido_cristal
           pg.drawString(text[17],345 + CalibracionGlobal.L_CERCA_CAPA_CRISTAL, (274+calLCerca3));//capa_cristal
           pg.drawString(text[18],430 + CalibracionGlobal.L_CERCA_PLUS_MAX, (274+calLCerca3));//plusmax_cristal ----fin cerca
           pg.setFont(new Font ("Arial", Font.ITALIC, 10));
           pg.drawString(text[19],70 + CalibracionGlobal.L_OBS, (327+calObs4));//observacion
           pg.drawString(text[0], 105 + CalibracionGlobal.L_FECHA, (502+calResumenComprobante5));//fecha
           pg.drawString(text[1], 460 + CalibracionGlobal.L_FOLIO, (502+calResumenComprobante5));//folio
           pg.setFont(new Font ("Elephant", Font.BOLD, 15));
           pg.drawString(text[26], 250 + CalibracionGlobal.L_INS_NOMBRE, (532+calResumenComprobante5));//Institucion nombre
           pg.setFont(new Font ("Calibri", Font.ITALIC, 10));
           pg.drawString(text[27], 205 + CalibracionGlobal.L_INS_WEB, (547+calResumenComprobante5));//Institucion web
           pg.drawString(text[28], 205 + CalibracionGlobal.L_INS_DIRECCION, (558+calResumenComprobante5));//Institucion direccion
           pg.drawString(text[29], 205 + CalibracionGlobal.L_INS_CONTACTO, (569+calResumenComprobante5));//Institucion contacto
           pg.setFont(new Font ("Arial", Font.ITALIC, 10));
           pg.drawString(text[4], 270 + CalibracionGlobal.L_NOMBRE_CLIENTE, (585+calResumenComprobante5));//nombre_cliente
           pg.setFont(new Font ("Arial", Font.ITALIC, 7));
           pg.drawString(text[42], 123+CalibracionGlobal.L_DETALLE, 595+calDetalleComprobante6);//Lejos Marca
           pg.setFont(new Font ("Arial", Font.ITALIC, 10));
           pg.drawString((text[43]), 330 + calValores, 595+calDetalleComprobante6);//Lejos Precio
           pg.setFont(new Font ("Arial", Font.ITALIC, 7));
           pg.drawString(text[44], 123+CalibracionGlobal.L_DETALLE, 610+calDetalleComprobante6);//Lejos cristal
           pg.setFont(new Font ("Arial", Font.ITALIC, 10));
           pg.drawString((text[45]), 330 + calValores, 610+calDetalleComprobante6);//Lejos cristal precio
           pg.setFont(new Font ("Arial", Font.ITALIC, 7));
           pg.drawString(text[46], 123+CalibracionGlobal.L_DETALLE, 630+calDetalleComprobante6);//Cerca Marca
           pg.setFont(new Font ("Arial", Font.ITALIC, 10));
           pg.drawString((text[47]), 330 + calValores, 630+calDetalleComprobante6);//Cerca precio
           pg.setFont(new Font ("Arial", Font.ITALIC, 7));
           pg.drawString(text[48], 123+CalibracionGlobal.L_DETALLE, 640+calDetalleComprobante6);//Cerca cristal
           pg.setFont(new Font ("Arial", Font.ITALIC, 10));
           pg.drawString((text[49]), 330 + calValores, 640+calDetalleComprobante6);//Cerca cristal precio
           pg.drawString((text[20]+text[50]), 330 + calValores, (653+calDetalleComprobante6));//valor_total
           pg.drawString((text[21]), 330 + calValores, (664+calDetalleComprobante6));//abono
           pg.setFont(new Font ("Arial", Font.BOLD, 10));
           pg.drawString((text[22]+text[51]), 330 + calValores, (675+calDetalleComprobante6));//saldo
           pg.setFont(new Font ("Elephant", Font.BOLD, 15));
           pg.drawString(text[3], 58 + CalibracionGlobal.L_FECHA_ENTREGA_2, (755+calDatosEntrega6));//fecha_entrega
           pg.setFont(new Font ("Arial", Font.ITALIC, 10));
           pg.drawString(text[2], 190 + CalibracionGlobal.L_LUGAR_ENTREGA_2, (755+calDatosEntrega6));//lugar_entrega
           pg.setFont(new Font ("Elephant", Font.BOLD, 15));
           pg.drawString(text[23], 480 + CalibracionGlobal.L_HORA_ENTREGA, (755+calDatosEntrega6));//hora
          
           pg.dispose();
           pjob.end();
           f.setVisible(false);
        }

    public static String obtenerAbonos(String codFicha){
        String resumen = null;
        String desc = null;
        String[][] abonos = (String[][])GlobalValuesFunctions.listarAbonos(codFicha);
        int monto = 0;
        int i = 0;
        if(abonos != null){
            for (i = 0; i < abonos.length; i++) {
                monto = monto + GV.strToNumber(abonos[i][0]);
                desc = abonos[i][1];
            }
        }
        return (monto > 0)? GV.strToPrice(monto):"No registrado";
    }
    
    

    public static String obtenerFormatoCliente(Cliente cliente) {
        if(cliente != null){
            String telefonos = cliente.getTelefono1()+"/"+cliente.getTelefono2();
            telefonos = (telefonos.startsWith("/"))? cliente.getTelefono2():telefonos;
            telefonos = (telefonos.endsWith("/"))? cliente.getTelefono1():telefonos;
            telefonos = (telefonos.isEmpty())? "No registrados":telefonos;
            String mail = (cliente.getEmail().isEmpty())?"":"\nEmail:"+cliente.getEmail();
            String datosCliente =  "Teléfonos:"+telefonos+mail;
            return datosCliente;
        }else{
            return "Sin datos del cliente";
        }
        
    }

    private static String obtenerFormatoDireccionCliente(Cliente cliente) {
        String datos = "";
        if(cliente != null){
            if(cliente.getDireccion()!=null){
                datos = cliente.getDireccion();
            }
            if(cliente.getComuna()!=null){
                if(cliente.getComuna().length()>3){
                    datos = datos + ", Comuna: "+cliente.getComuna();
                }
                return datos;
            }
            return "No existe información";
        }else{
            return "Sin datos de dirección del cliente";
        }
    }

    private static String strDescuento(int descuento) {
        return (descuento!=0)? " (Descuento aplicado)":"";
    }
    
    public static String descuentoFormatPrint(int descuento) {
        return (descuento!=0)? GV.strToPrice(descuento):"No aplicado";
    }

    private static String strDetalleDescuento(int descuento) {
        return (descuento!=0)? " (Ahorro "+GV.strToPrice(descuento)+")":"";
    }
    
    public static void printSalesReport(List<Object> fichas,String title){
        GV.cursorWAIT();
        InputStream is = null;
            SalesFichaRecursoDatos dt = new SalesFichaRecursoDatos();
            dt.createReport(fichas, title, GV.companyName(), GV.getOficinaWeb(), 
                            GV.getOficinaAddress()+" - "+GV.getOficinaCity(),
                            GV.getOficinaMail());
            if(dt.noGenerated()){
                return;
            }
            try{
                is = new FileInputStream("src"+File.separator+"reportes"+File.separator+"reporteVentas.jrxml");
            }catch(FileNotFoundException e){
                OptionPane.showMsg("No se puede obtener el recurso",
                        "Ocurrió un error al intentar abrir el formato de impresión\n"
                                + e.getMessage(), 3);
            }
            openView(is,dt);
    }

    public static void printConvenio(Convenio cnv) {
        try{
            GV.cursorWAIT();
            String title = "";
            String subtitle = "";
            Dao load = new Dao();
            
            InputStream is = null;
            InputStream is2 = null;
            
            FichasConvenioRecursoDatos dt = new FichasConvenioRecursoDatos();
            CuotasConvenioRecursoDatos dt2 = new CuotasConvenioRecursoDatos();
            Convenio convenio = (Convenio)load.get(null, 3, new Convenio());
            if(!dt.addConvenio(convenio,title, subtitle)){
                OptionPane.showMsg("No se puede generar reporte", "El sistema no admite convenios activos, anulados ni defectuosos para generar reportes.", 2);
                GV.cursorDF();
                return;
            }
            dt2.addConvenio(convenio, title, subtitle);
            try{
                is = new FileInputStream("src"+File.separator+"reportes"+File.separator+"fichasCnv.jrxml");
                is2 = new FileInputStream("src"+File.separator+"reportes"+File.separator+"cuotasCnv.jrxml");
            }catch(FileNotFoundException e){
                OptionPane.showMsg("No se puede obtener el recurso",
                        "Ocurrió un error al intentar abrir el formato de impresión\n"
                                + e.getMessage(), 3);
            }
            
            
            openView(is,dt);
            openView(is2,dt2);
            GV.cursorDF();
        }catch(SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex){
            Logger.getLogger(GlobalValuesPrint.class.getName()).log(Level.SEVERE, null, ex);
            GV.cursorDF();
        }
    }
    
    public static void printCuotasConvenio(Convenio cnv) {
        try{
            GV.cursorWAIT();
            String title = "";
            String subtitle = "";
            Dao load = new Dao();
            
            InputStream is = null;
            
            CuotasConvenioRecursoDatos dt = new CuotasConvenioRecursoDatos();
            Convenio convenio = (Convenio)load.get(null, 3, new Convenio());
            if(!dt.addConvenio(convenio,title, subtitle)){
                OptionPane.showMsg("No se puede generar reporte", "El sistema no admite convenios activos, anulados ni defectuosos para generar reportes.", 2);
                GV.cursorDF();
                return;
            }
            try{
                is = new FileInputStream("src"+File.separator+"reportes"+File.separator+"detalleCuotasCnv.jrxml");
            }catch(FileNotFoundException e){
                OptionPane.showMsg("No se puede obtener el recurso",
                        "Ocurrió un error al intentar abrir el formato de impresión\n"
                                + e.getMessage(), 3);
            }
            
            
            openView(is,dt);
            GV.cursorDF();
        }catch(SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex){
            GV.cursorDF();
            Logger.getLogger(GlobalValuesPrint.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void openView(InputStream is, JRDataSource fcr){
        try{
                JasperDesign jsd = JRXmlLoader.load(is);
                JasperReport jsrp = JasperCompileManager.compileReport(jsd);
                JasperPrint jsp = JasperFillManager.fillReport(jsrp, null,fcr);
                JasperViewer viewer = new JasperViewer(jsp, false); //Se crea la vista del reportes
                viewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Se declara con dispose_on_close para que no se cierre el programa cuando se cierre el reporte
                viewer.setVisible(true); //Se vizualiza el reporte
                GV.cursorDF();
//            generateReport(jsp, true, "src"+File.separator+"reportes"+File.separator+"fichasConvenio.xls");
            }catch( JRException e){
                GV.cursorDF();
                OptionPane.showMsg("No se puede visualizar el recurso",
                        "Ocurrió un error al intentar abrir visualización del formato de impresión\n"
                                + e.getMessage(), 3);
            }
    }
}
