/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correo;

import dao.HistorialPagoDao;
import dao.TipoPagoDao;
import entities.Ficha;
import entities.HistorialPago;
import entities.Info;
import entities.TipoPago;
import entities.User;
import fn.Boton;
import fn.FnFicha;
import fn.GlobalValues;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import view.ContentAdmin;
import view.Progreso;

/**
 *
 * @author home
 */
public class FnEnviar {
    
    public void enviarComprobantes(ArrayList<Ficha> lista,Info empresa, int funcion) throws InterruptedException{
        Boton boton = new Boton();
        boton.barraProgreso(lista,empresa,funcion);
    }
    
    public static boolean enviarNotificacionFicha(Ficha ficha, Info empresa){
        if(ficha.getCliente().getEmail() == "")
            return false;
        
        int descuento = 0;
        if(ficha.getDescuento().getPorcetange() > 0){
            descuento = (ficha.getValorTotal()*ficha.getDescuento().getPorcetange())/100;
            ficha.setValorTotal(ficha.getValorTotal()-descuento);
        }
        
        int abono = ficha.getValorTotal()-ficha.getSaldo();
        
        
        Correo c = new Correo();
        c.setPass("qwpzedzqucvpyjzt");
        c.setUsuario("sdx.respaldo.bd@gmail.com");
        c.setAsunto("LENTE SIN RETIRAR - "+empresa.getNombre().toUpperCase());
        c.setMensaje("Usted posee un lente pendiente en nuestras oficinas\n" +
"                    "+empresa.getNombre().toUpperCase()+"\n" +
"\n" +
"Por favor acérquese a nuestras dependencias y verifique esta información:\n\n\n" +
"        Folio: "+ficha.getId()+"\n" +
"        Nombre: "+ficha.getCliente().getNombre()+"\n" +
"        Rut: "+ficha.getCliente().getRut()+"\n\n\n" +
"        Valor total: $ "+ficha.getValorTotal()+"\n" +
"        Abono total: $ "+abono+"\n" +
"        Saldo total: $ "+ficha.getSaldo()+"\n\n\n" +
"        FECHA DE RETIRO: "+ficha.getFechaEntrega()+" HORA: "+ficha.getHoraEntrega()+"    LUGAR: "+ficha.getLugarEntrega()+"\n\n\n\n" +
" Atentamente.\n\n"+
" "+empresa.getNombre().toUpperCase()+"\n" +
" Direccion: "+empresa.getDireccion()+"\n" +
" Sitio web: "+empresa.getWeb()+"\n" +
" Email: "+empresa.getMail()+"\n" +
" Telefono: "+empresa.getTelefono()+"\n" +
"        \n\n\nNo imprimas este correo si no es necesario. Protejamos el medio ambiente"+
"Este mail ha sido generado por el software Optidata, desarrollado por Softdirex: www.softdirex.cl");
        c.setDestino(ficha.getCliente().getEmail());
        
        Controlador ctr = new Controlador();
        if(ctr.enviarCorreo(c))
            return true;
        return false;
    }
    
    public static boolean enviarDatosFicha(Ficha ficha,Info empresa){
        if(ficha.getCliente().getEmail() == "")
            return false;
        
        int descuento = 0;
        if(ficha.getDescuento().getPorcetange() > 0){
            descuento = (ficha.getValorTotal()*ficha.getDescuento().getPorcetange())/100;
            ficha.setValorTotal(ficha.getValorTotal()-descuento);
        }
        
        int abono = ficha.getValorTotal()-ficha.getSaldo();
        
        
        Correo c = new Correo();
        c.setPass(GlobalValues.getMailSystemPass());
        c.setUsuario(GlobalValues.getMailSystemName());
        c.setAsunto("COMPROBANTE DE COMPRA Y RETIRO – "+empresa.getNombre().toUpperCase());
        c.setMensaje("\n" +
"Estimado Cliente, Muchas Gracias por Preferirnos, el detalle de su compra y retiro de sus lentes ópticos es el siguiente:\n\n\n" +
"        Folio: "+ficha.getId()+"\n" +
"        Nombre: "+ficha.getCliente().getNombre()+"\n" +
"        Rut: "+ficha.getCliente().getRut()+"\n\n\n" +
"        Valor total: $ "+ficha.getValorTotal()+"\n" +
"        Abono total: $ "+abono+"\n" +
"        Saldo total: $ "+ficha.getSaldo()+"\n\n\n" +
"        FECHA DE ENTREGA: "+ficha.getFechaEntrega()+" HORA: "+ficha.getHoraEntrega()+"    LUGAR: "+ficha.getLugarEntrega()+"\n\n\n\n" +
" Atentamente.\n\n"+
" "+empresa.getNombre().toUpperCase()+"\n" +
" Direccion: "+empresa.getDireccion()+"\n" +
" Sitio web: "+empresa.getWeb()+"\n" +
" Email: "+empresa.getMail()+"\n" +
" Telefono: "+empresa.getTelefono()+"\n" +
"        \n\n\nNo imprimas este comprobante si no es necesario. Protejamos el medio ambiente"+
"Este mail ha sido generado por el software Optidata, desarrollado por Softdirex: www.softdirex.cl");
        c.setDestino(ficha.getCliente().getEmail());
        
        Controlador ctr = new Controlador();
        if(ctr.enviarCorreo(c))
            return true;
        return false;
    }
    
    public void enviarRespaldoFromFile(Info empresa){
        String version = GlobalValues.VERSION;
        Correo c = new Correo();
        c.setPass("qwpzedzqucvpyjzt");
        c.setUsuario("sdx.respaldo.bd@gmail.com");
        if(empresa != null)
            c.setAsunto("OptiData "+version+" "+empresa.getNombre());
        else
            c.setAsunto("OptiData "+version+" Sin registro de empresa");
        c.setMensaje("Respaldo de base de datos MySql, programa OptiData version "+version+" compilado desde Agosto del 2017");
        c.setDestino("softdirex@gmail.com");
        c.setNombreArchivo("respaldoBD.sql");
        c.setRutaArchivo(System.getProperty("user.dir")+"\\files\\respaldoBD.sql");
        
        Controlador ctr = new Controlador();
        if(ctr.enviarCorreo(c))
            JOptionPane.showMessageDialog(null, "Base de datos respaldada.");
        else
            JOptionPane.showMessageDialog(null, "No se pudo respaldar la base de datos. ");
    }

    public boolean enviarReporte(Date fecha1, Date fecha2,ArrayList<Ficha> listarPorFecha, Info empresa) throws SQLException, ClassNotFoundException {
        String fechas = "";
        DateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        try{
            fechas = "Fechas desde "+date.format(fecha1)+" hasta "+date.format(fecha2);
        }catch(Exception e){
            if(fecha1 == null)
                fechas = "Fecha: "+date.format(fecha2);
            else{
                if(fecha2 == null)
                    fechas = "Fecha: "+date.format(fecha1);
            }
        }
        try{
            if(fecha1.compareTo(fecha2)==0)
            fechas = "Fecha: "+date.format(fecha1);
        }catch(Exception e){
            
        }
        
        
        
        String reporte = obtenerReporte(listarPorFecha);
        String repUser = obtenerReportePorUser(fecha1,fecha2);
        Correo c = new Correo();
        
        c.setPass("qwpzedzqucvpyjzt");
        c.setUsuario("sdx.respaldo.bd@gmail.com");
        c.setAsunto("REPORTE DE SISTEMA OPTIDATA - "+empresa.getNombre().toUpperCase());
        c.setMensaje("Recepcion de reporte " +empresa.getNombre().toUpperCase()+"\n" +
"\n" +
"        "+fechas+"\n\n\n" +
         reporte+
         repUser+
"        \n" +
"        \n\n\nNo imprimas este reporte si no es necesario. Protejamos el medio ambiente."+
"\nEste mail ha sido generado por el software Optidata, desarrollado por Softdirex: www.softdirex.cl");
        c.setDestino(empresa.getAdminMail());
        Controlador ctr = new Controlador();
        if(ctr.enviarCorreo(c))
            return true;
        return false;
    
    }

    public String obtenerReporte(ArrayList<Ficha> listarPorFecha) throws SQLException, ClassNotFoundException {
        DecimalFormat formateador = new DecimalFormat("###,###,###"); 
        
        int totalVentas = 0;
        int montoVentas = 0;
        int montoAbono = 0;
        int abono = 0;
        int lentes =0;
        
        TipoPagoDao tpLoad = new TipoPagoDao();
        
        
        ArrayList<TipoPago> tps = tpLoad.listar(0);//carga todos los tipo de pago activos
        int[] suma = new int[tps.size()];
        int[] idTp = new int[tps.size()];
        String[] tipo = new String[tps.size()];
        
        for (int i = 0; i < tps.size(); i++) {
            suma[i]=0;
            idTp[i]=tps.get(i).getId();
        }
        for (TipoPago temp : tps) {
            for (int i = 0; i < tps.size(); i++) {
                if(temp.getId() == idTp[i])
                tipo[i]=temp.getNombre();//asignamos un nombre correspondiente al mismo id registrado
            }
        }
        
        
        
        HistorialPagoDao hpLoad= new HistorialPagoDao();
        
        int sumaSinTipoPago = 0;
        int descuento =0;
        for (Ficha ficha : listarPorFecha) {
           if(ficha.getCerca()!=null){
               if(ficha.getCerca().getMarca().length()>1){
                   lentes++;
               }
           }
           if(ficha.getLejos()!=null){
               if(ficha.getLejos().getMarca().length()>1){
                   lentes++;
               }
           }
            for (HistorialPago hpTmp : hpLoad.listar(ficha.getId())) {
                if(hpTmp.getIdTipoPago()==0){
                    sumaSinTipoPago = sumaSinTipoPago+hpTmp.getAbono();
                }else{
                    for (int i = 0; i < tps.size(); i++) {
                        if(idTp[i] == hpTmp.getIdTipoPago()){
                            suma[i]=suma[i]+hpTmp.getAbono();
                            
                        }
                    }
                }
            }
            totalVentas++;
            
            if(ficha.getDescuento().getPorcetange() > 0){
            descuento = (ficha.getValorTotal()*ficha.getDescuento().getPorcetange())/100;
            ficha.setValorTotal(ficha.getValorTotal()-descuento);
            }
            
            montoVentas = montoVentas + ficha.getValorTotal();
            abono = ficha.getValorTotal() - ficha.getSaldo();
            montoAbono = montoAbono + abono;
            abono = 0;
        }
        String tipoPagos = "";
        for (int i = 0; i < tps.size(); i++) {
            tipoPagos = tipoPagos +"ABONOS         $" +formateador.format(suma[i])+"    ("+tipo[i].toUpperCase()+")\n";
        }
        
        if(totalVentas == 0){
            return  "\n"+
                
                "NO TIENE VENTAS REGISTRADAS.\n"+
                "\n\n";
        }
        
        return  "------------------------------------------------ \n"+
                
                "CANTIDAD VENTAS   : " +formateador.format(totalVentas)+"\n"+
                "LENTES VENDIDOS: "+formateador.format(lentes)+"     \n"+
                "MONTO TOTAL VENTAS: $" +formateador.format(montoVentas)+"\n"+
                //"TOTAL ABONOS      : $" +formateador.format(montoAbono)+"\n"+
                "------------------------------------------------\n"+
                "DETALLE DE ABONOS:\n"+
                "------------------------------------------------\n\n"+
                tipoPagos+
                "ABONOS         $"+formateador.format(sumaSinTipoPago)+"    (MONTO SIN REGISTRAR TIPO DE PAGO) \n"+
                "------------------------------------------------ \n"+
                "TOTAL ABONOS   $"+formateador.format(montoAbono)+"  \n\n";
    }

    public void enviarNotificaciones(ArrayList<Ficha> morosos, Info empresa) throws InterruptedException {
        Boton boton = new Boton();
        boton.barraProgreso(morosos,empresa,1);
    }

    public String obtenerReportePorUser(Date fecha1, Date fecha2) throws SQLException, ClassNotFoundException {
        FnFicha load = new FnFicha();
        String reporte = "\n\n************************************************\n"+
                         "\nDETALLE POR VENDEDOR\n";
        for (User temp : load.obtenerUser()) {
            reporte = reporte+
                    "************************************************\n"+
                    "************************************************\n"+
                    "\n"+temp.getNombre().toUpperCase()+"\n"+
                    obtenerReporte(load.listarPorFechaSesion(fecha1, fecha2, temp.getId()));
            
        }
        return reporte+"\n************** Fin comentario ***************\n";
    }
    
    public String obtenerReportePorUser2(Date fecha1, Date fecha2) throws SQLException, ClassNotFoundException {
        FnFicha load = new FnFicha();
        String repUser = "";
        String reporte = "\n\n************************************************\n"+
                         "\nDETALLE POR VENDEDOR\n";
        for (User temp : load.obtenerUser()) {
            repUser = obtenerReporte(load.listarPorFechaSesion(fecha1, fecha2, temp.getId()));
            if(!repUser.equals("\nNO TIENE VENTAS REGISTRADAS.\n\n\n")){
                reporte = reporte+
                    
                    "************************************************\n"+
                    "\n"+temp.getNombre().toUpperCase()+"\n"+
                    repUser;
            }
            
            
        }
        return reporte+"\n************** Fin comentario ***************\n";
    }

    
}
