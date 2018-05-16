/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn;

import static fn.xml.ReadXml.readCalibracionGlobalXml;

/**
 *
 * @author sdx
 */
public class CalibracionGlobal {
    public static int ALT_TITULOS = 0;
    public static int ALT_LENTE_LEJOS = 0;
    public static int ALT_LENTE_CERCA = 0;
    public static int ALT_OBS = 0;
    public static int ALT_RESUMEN = 0;
    public static int ALT_DATOS_ENTREGA = 0;
    
    public static int L_FECHA = 0;
    public static int L_FOLIO = 0;
    public static int L_LUGAR_ENTREGA_1 = 0;
    public static int L_FECHA_ENTREGA_1 = 0;
    public static int L_DATOS_CLIENTE = 0;
    public static int L_DIRECCION_CLIENTE = 0;
    public static int L_VALOR_TOTAL_1 = 0;
    
    public static int L_LEJOS_MARCA = 0;
    public static int L_LEJOS_CRISTAL = 0;
    public static int L_LEJOS_OD_ESF = 0;
    public static int L_LEJOS_OD_CIL = 0;
    public static int L_LEJOS_OD_A = 0;
    public static int L_LEJOS_OI_ESF = 0;
    public static int L_LEJOS_OI_CIL = 0;
    public static int L_LEJOS_OI_A=0;
    public static int L_LEJOS_DP_CRISTAL=0;
    public static int L_LEJOS_ENDURECIDO_CRISTAL=0;
    public static int L_LEJOS_CAPA_CRISTAL=0;
    public static int L_LEJOS_PLUS_MAX=0;
    
    public static int L_CERCA_MARCA = 0;
    public static int L_CERCA_CRISTAL = 0;
    public static int L_CERCA_ADD = 0;
    public static int L_CERCA_OD_ESF = 0;
    public static int L_CERCA_OD_CIL = 0;
    public static int L_CERCA_OD_A = 0;
    public static int L_CERCA_OI_ESF = 0;
    public static int L_CERCA_OI_CIL = 0;
    public static int L_CERCA_OI_A=0;
    public static int L_CERCA_DP_CRISTAL=0;
    public static int L_CERCA_ENDURECIDO_CRISTAL=0;
    public static int L_CERCA_CAPA_CRISTAL=0;
    public static int L_CERCA_PLUS_MAX=0;
    
    public static int L_OBS=0;
    
    public static int L_INS_NOMBRE = 0;
    public static int L_INS_WEB=0;
    public static int L_INS_DIRECCION = 0;
    public static int L_INS_CONTACTO = 0;
    
    public static int  L_NOMBRE_CLIENTE = 0;
    public static int L_DETALLE=0;
    public static int L_ABONO=0;
    public static int L_VALORES=0;
    public static int L_FECHA_ENTREGA_2=0;
    public static int L_LUGAR_ENTREGA_2=0;
    public static int L_HORA_ENTREGA=0;
    
    
    public static void cargarCalibracion(){
        System.out.println("CalibracionGlobal:cargarCalibracion()");
        readCalibracionGlobalXml();
     }
    
}
