package dao;


import entities.Cliente;
import entities.Ficha;
import entities.HistorialPago;
import entities.Info;
import fn.FnInfo;
import fn.FnTipoPago;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.PrintJob;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JOptionPane;





public class Programa extends JFrame
{
	
        private Info institucion;
        
	//CONSTRUCTOR DE LA CLASE
	public Programa(Ficha ficha) throws SQLException, ClassNotFoundException
	{
            FnInfo load = new FnInfo();
            DecimalFormat formateador = new DecimalFormat("###,###,###"); 
                                InfoDao dao=new InfoDao();
                                Info info=new Info();
                                
                                
                            try {
                               info=dao.getInstitucion();
                                //////espacios
                            } catch (SQLException ex) {
                                info.setId(0);
                                info.setNombre("");
                                info.setDireccion("");
                                info.setCiudad("");
                                info.setTelefono("");
                                info.setCelular(""); 
                                info.setMail("");
                                info.setWeb("");
                            } catch (ClassNotFoundException ex) {
                                info.setId(0);
                                info.setNombre("");
                                info.setDireccion("");
                                info.setCiudad("");
                                info.setTelefono("");
                                info.setCelular(""); 
                                info.setMail("");
                                info.setWeb("");
                            }
                                String formatoAbono = obtenerFormatoAbono(ficha.getId());
                                ///////////////////////////FECHAS
                                DateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                                String fecha = date.format(ficha.getFecha());
                                String fecha_entrga=date.format(ficha.getFechaEntrega());
                                //////////////////////////FIN FECHAs
                                String endurecidoLejos="";String capaLejos="";String plusMaxLejos="";
                                if(ficha.getLejos().getEndurecido()==1){endurecidoLejos="X";}
                                if(ficha.getLejos().getCapa()==1){capaLejos="X";}
                                if(ficha.getLejos().getPlusMax()==1){plusMaxLejos="X";}
                                String endurecidoCerca="";String capaCerca="";String plusMaxCerca="";
                                if(ficha.getCerca().getEndurecido()==1){endurecidoCerca="X";}
                                if(ficha.getCerca().getCapa()==1){capaCerca="X";}
                                if(ficha.getCerca().getPlusMax()==1){plusMaxCerca="X";}
                                
                                int precioLente1 = 0;
                                int precioLente2 = 0;
                                int precioCristal1 = 0;
                                int precioCristal2 = 0;
                                
                                int descuento = 0;
                                
                                if(ficha.getCerca().getMarca().length() > 0){
                                    try{
                                        precioLente1 = load.getPrecio("lente",ficha.getCerca().getMarca());
                                        if(ficha.getDescuento()!=null){
                                            try{
                                                descuento = (precioLente1*ficha.getDescuento().getPorcetange())/100;
                                                precioLente1 = precioLente1 - descuento;
                                            }catch(Exception e){
                                                System.out.println("Error en clase Programa(Ficha)");
                                            }
                                        }
                                    }catch(Exception e){
                                        precioLente1 = 0;
                                    }
                                }
                                if(ficha.getCerca().getCristal().length() > 0){
                                    try{
                                        precioCristal1 = load.getPrecio("cristal", ficha.getCerca().getCristal());
                                        if(ficha.getDescuento()!=null){
                                            try{
                                                descuento = (precioCristal1*ficha.getDescuento().getPorcetange())/100;
                                                precioCristal1 = precioCristal1 - descuento;
                                            }catch(Exception e){
                                                System.out.println("Error en clase Programa(Ficha)");
                                            }
                                        }
                                    }catch(Exception e){
                                        precioCristal1 = 0;
                                    }
                                }
                                
                                if(ficha.getLejos().getMarca().length() > 0){
                                    try{
                                        precioLente2 = load.getPrecio("lente",ficha.getLejos().getMarca());
                                        if(ficha.getDescuento()!=null){
                                            try{
                                                descuento = (precioLente2*ficha.getDescuento().getPorcetange())/100;
                                                precioLente2 = precioLente2 - descuento;
                                            }catch(Exception e){
                                                System.out.println("Error en clase Programa(Ficha)");
                                            }
                                        }
                                    }catch(Exception e){
                                        precioLente2 = 0;
                                    }
                                }
                                if(ficha.getLejos().getCristal().length() > 0){
                                    try{
                                        precioCristal2 = load.getPrecio("cristal", ficha.getLejos().getCristal());
                                        if(ficha.getDescuento()!=null){
                                            try{
                                                descuento = (precioCristal2*ficha.getDescuento().getPorcetange())/100;
                                                precioCristal2 = precioCristal2 - descuento;
                                            }catch(Exception e){
                                                System.out.println("Error en clase Programa(Ficha)");
                                            }
                                        }
                                    }catch(Exception e){
                                        precioCristal2 = 0;
                                    }
                                }
                                
                                
                                String impresion[]=
                                {
                                 fecha//0
                                ,ficha.getId()+""//1
                                ,ficha.getLugarEntrega()//2
                                ,fecha_entrga//3
                                ,ficha.getCliente().getNombre()//4
                                ,ficha.getLejos().getCristal()//5
                                ,ficha.getLejos().getOdEsf()//6
                                ,ficha.getLejos().getOiEsf()//7
                                ,ficha.getLejos().getDp()+""//8
                                ,endurecidoLejos//9
                                ,capaLejos//10
                                ,plusMaxLejos//11----fin lejos
                                ,ficha.getCerca().getCristal()//12
                                ,ficha.getCerca().getOdEsf()//13
                                ,ficha.getCerca().getOiEsf()//14
                                ,ficha.getCerca().getDp()+""//15
                                ,endurecidoCerca//16
                                ,capaCerca//17
                                ,plusMaxCerca//18
                                ,ficha.getObservacion()//19
                                ,"$_"+formateador.format (ficha.getValorTotal())//20
                                ,formatoAbono//21
                                ,"$_"+ formateador.format (ficha.getSaldo())//22
                                ,ficha.getHoraEntrega()//23
                                ,ficha.getLejos().getMarca()//24
                                ,ficha.getCerca().getMarca()//25
                                ,info.getNombre()//26
                                ,info.getWeb()//27
                                ,info.getDireccion()+"-"+info.getCiudad()//28
                                ,info.getMail()+"/"+info.getTelefono()+"-"+info.getCelular()//29
                                ,ficha.getLejos().getOdCil()//30 desde aqui
                                ,ficha.getLejos().getOdA()//31
                                ,ficha.getLejos().getOiCil()//32
                                ,ficha.getLejos().getOiA()//33
                                ,ficha.getCerca().getOdCil()//34
                                ,ficha.getCerca().getOdA()//35
                                ,ficha.getCerca().getOiCil()//36
                                ,ficha.getCerca().getOiA()//37
                                ,ficha.getCerca().getAdd()//38
                                ,obtenerFormatoCliente(ficha.getCliente())//39
                                ,obtenerFormatoDireccionCliente(ficha.getCliente())//40
                                ,"TOTAL: $"+formateador.format (ficha.getValorTotal())+", ABONO: "+formatoAbono+", SALDO: $"+ formateador.format (ficha.getSaldo())//41
                                ,ficha.getLejos().getMarca()//42
                                ,"$_"+formateador.format(precioLente2)//43
                                ,ficha.getLejos().getCristal()//44
                                ,"$_"+formateador.format(precioCristal2)//45
                                ,ficha.getCerca().getMarca()//46
                                ,"$_"+formateador.format(precioLente1)//47
                                ,ficha.getCerca().getCristal()//48
                                ,"$_"+formateador.format(precioCristal1)//49
                                };
                                
                                imprimir(impresion); 
                                
                     
 
 
 
	}//FIN DEL CONSTRUCTOR
 
	//FIN DEL MAIN
        public void imprimir(String[] impresion){
             try {
                                imprimir_Cita(impresion);
                                
                                

                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, "La impresion se ha cancelado", "", JOptionPane.WARNING_MESSAGE);
                                return;
                            }
        
        }
        
        
        public void imprimir_Cita(String [] text)
        {
            int calTitulos1 = CalibracionGlobal.ALT_TITULOS;
            int calLejos2 = CalibracionGlobal.ALT_LENTE_LEJOS;
            int calLCerca3 = CalibracionGlobal.ALT_LENTE_CERCA;
            int calObs4 = CalibracionGlobal.ALT_OBS;
            int calResumenComprobante5 = CalibracionGlobal.ALT_RESUMEN;
            int calDatosEntrega6 = CalibracionGlobal.ALT_DATOS_ENTREGA;
            int calValores = CalibracionGlobal.L_VALORES;
        
            
        Frame f = new Frame ("Imprimir");
        f.pack();
        PrintJob pjob = f.getToolkit().getPrintJob(f,"Impresion del Registro",null);
        Graphics pg = pjob.getGraphics();
                            // fuente  ,          , tamaño de la fuente
        pg.setFont(new Font ("Arial", Font.ITALIC, 10)); 
         //dibuja el string guardado en el objeto texto de la clase String, y lo sitúa en la posición cuyas coordenadas vienen dadas por los dos números enteros que le siguen.
                                 //x , y //el aumenta 20 por fila    //81
           pg.drawString(text[0], 105 + CalibracionGlobal.L_FECHA, (80+calTitulos1));//fecha
           pg.drawString(text[1], 465 + CalibracionGlobal.L_FOLIO, (80+calTitulos1));//folio
           pg.drawString(text[2], 150 + CalibracionGlobal.L_LUGAR_ENTREGA_1, (100+calTitulos1));//lugar_entrega
           pg.drawString(text[3], 465 + CalibracionGlobal.L_FECHA_ENTREGA_1, (100+calTitulos1));//fecha_entrega
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
           pg.drawString(text[42], 123+CalibracionGlobal.L_DETALLE, 595+calResumenComprobante5);//Lejos Marca
           pg.setFont(new Font ("Arial", Font.ITALIC, 10));
           pg.drawString(rellenarEspacios(text[43]), 330 + calValores, 595+calResumenComprobante5);//Lejos Precio
           pg.setFont(new Font ("Arial", Font.ITALIC, 7));
           pg.drawString(text[44], 123+CalibracionGlobal.L_DETALLE, 610+calResumenComprobante5);//Lejos cristal
           pg.setFont(new Font ("Arial", Font.ITALIC, 10));
           pg.drawString(rellenarEspacios(text[45]), 330 + calValores, 610+calResumenComprobante5);//Lejos cristal precio
           pg.setFont(new Font ("Arial", Font.ITALIC, 7));
           pg.drawString(text[46], 123+CalibracionGlobal.L_DETALLE, 630+calResumenComprobante5);//Cerca Marca
           pg.setFont(new Font ("Arial", Font.ITALIC, 10));
           pg.drawString(rellenarEspacios(text[47]), 330 + calValores, 630+calResumenComprobante5);//Cerca precio
           pg.setFont(new Font ("Arial", Font.ITALIC, 7));
           pg.drawString(text[48], 123+CalibracionGlobal.L_DETALLE, 640+calResumenComprobante5);//Cerca cristal
           pg.setFont(new Font ("Arial", Font.ITALIC, 10));
           pg.drawString(rellenarEspacios(text[49]), 330 + calValores, 640+calResumenComprobante5);//Cerca cristal precio
           pg.drawString(rellenarEspacios(text[20]), 330 + calValores, (653+calResumenComprobante5));//valor_total
           pg.drawString(rellenarEspacios(text[21]), 330 + calValores, (664+calResumenComprobante5));//abono
           pg.setFont(new Font ("Arial", Font.BOLD, 10));
           pg.drawString(rellenarEspacios(text[22]), 330 + calValores, (675+calResumenComprobante5));//saldo
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

    private String obtenerFormatoAbono(int idFicha) throws SQLException, ClassNotFoundException {
        DecimalFormat formateador = new DecimalFormat("###,###,###"); 
        HistorialPagoDao hpd = new HistorialPagoDao();
        FnTipoPago tp = new FnTipoPago();
        int largo = hpd.listar(idFicha).size();
        int monto = 0;
        int idTipoPago = 0;
        if(largo==0)
            return "$_0 (No existen abonos registrados)";
        
        for (HistorialPago temp : hpd.listar(idFicha)) {
            monto =  monto + temp.getAbono();
            idTipoPago =  temp.getIdTipoPago();
        }
        if(largo > 1){
            return "$_"+formateador.format (monto)+" ("+largo+" Abonos registrados)";
        }
        return "$_"+formateador.format (monto)+" ("+tp.cargar(idTipoPago).getNombre()+")";
    }

    private String obtenerFormatoCliente(Cliente cliente) {
        if(cliente != null){
            String datosCliente = cliente.getNombre();
            if(cliente.getTelefono() != null){
                if(cliente.getTelefono().length()>7){
                    datosCliente =  datosCliente + " - Teléfono: "+cliente.getTelefono();
                }
            }
            return datosCliente;
        }else{
            return "Sin datos del cliente";
        }
        
    }

    private String obtenerFormatoDireccionCliente(Cliente cliente) {
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

    private String rellenarEspacios(String precio) {
        try{
            precio = precio.trim();
            String aux2 = "";
            if(precio.length() > 12){
                aux2 = precio.substring(precio.indexOf("("), precio.length());
                precio = precio.substring(0, precio.indexOf("("));

            }
            String aux =  precio.substring(0,precio.indexOf("_"));
            precio = precio.substring(precio.indexOf("_"), precio.length()).replace("_", "");
            int spaces = precio.trim().length();
            spaces = 9-spaces;
            String temp = "";
            if(spaces > 0){
                for (int i = 0; i < spaces; i++) {
                    temp = temp + " ";
                }
            }
            if(aux2.length() > 2){
                return aux+temp+precio+aux2;
            }
            return aux+temp+precio;
        }catch(Exception e){
            System.out.println(""+e.getMessage());
            return "ERROR";
        }
    }
 
 
}//FIN DE LA CLASE PRINCIPAL
