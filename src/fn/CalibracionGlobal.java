/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
    public static int ALT_DETALLE = 0;
    
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
        try {
            File archivo = new File(System.getProperty("user.dir")+"\\files\\xml\\calPrint.xml");
            if(System.getProperty("os.name").equals("Linux")){
                archivo = new File(System.getProperty("user.dir")+"/files/xml/calPrint.xml");
            }
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document documento = db.parse(archivo);
            
            documento.getDocumentElement().normalize();
            NodeList filas = documento.getElementsByTagName("fila");
            for (int temp = 0; temp < filas.getLength(); temp++) {
                Node nodo = filas.item(temp);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;
                    ALT_TITULOS = Integer.parseInt(element.getElementsByTagName("titulos").item(0).getTextContent());
                    ALT_LENTE_LEJOS = Integer.parseInt(element.getElementsByTagName("lejos").item(0).getTextContent());
                    ALT_LENTE_CERCA = Integer.parseInt(element.getElementsByTagName("cerca").item(0).getTextContent());
                    ALT_OBS = Integer.parseInt(element.getElementsByTagName("obs").item(0).getTextContent());
                    ALT_RESUMEN = Integer.parseInt(element.getElementsByTagName("resumen").item(0).getTextContent());
                    ALT_DETALLE = Integer.parseInt(element.getElementsByTagName("detalle").item(0).getTextContent());
                    ALT_DATOS_ENTREGA = Integer.parseInt(element.getElementsByTagName("entrega").item(0).getTextContent());
                }
            }
            
            filas = documento.getElementsByTagName("columna");
            for (int temp = 0; temp < filas.getLength(); temp++) {
                Node nodo = filas.item(temp);
                if(nodo.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) nodo;
                    L_FECHA                 = Integer.parseInt(element.getElementsByTagName("fecha").item(0).getTextContent());
                    L_FOLIO                 = Integer.parseInt(element.getElementsByTagName("folio").item(0).getTextContent());
                    L_LUGAR_ENTREGA_1       = Integer.parseInt(element.getElementsByTagName("lugar_entrega_1").item(0).getTextContent());
                    L_FECHA_ENTREGA_1       = Integer.parseInt(element.getElementsByTagName("fecha_entrega_1").item(0).getTextContent());
                    L_DATOS_CLIENTE         = Integer.parseInt(element.getElementsByTagName("datos_cliente").item(0).getTextContent());
                    L_DIRECCION_CLIENTE     = Integer.parseInt(element.getElementsByTagName("direccion_cliente").item(0).getTextContent());
                    L_VALOR_TOTAL_1         = Integer.parseInt(element.getElementsByTagName("valor_total_1").item(0).getTextContent());
                    
                    L_LEJOS_MARCA           = Integer.parseInt(element.getElementsByTagName("lejos_marca").item(0).getTextContent());
                    L_LEJOS_CRISTAL         = Integer.parseInt(element.getElementsByTagName("lejos_cristal").item(0).getTextContent());
                    L_LEJOS_OD_ESF          = Integer.parseInt(element.getElementsByTagName("lejos_od_esf").item(0).getTextContent());
                    L_LEJOS_OD_CIL          = Integer.parseInt(element.getElementsByTagName("lejos_od_cil").item(0).getTextContent());
                    L_LEJOS_OD_A            = Integer.parseInt(element.getElementsByTagName("lejos_od_a").item(0).getTextContent());
                    L_LEJOS_OI_ESF          = Integer.parseInt(element.getElementsByTagName("lejos_oi_esf").item(0).getTextContent());
                    L_LEJOS_OI_CIL          = Integer.parseInt(element.getElementsByTagName("lejos_oi_cil").item(0).getTextContent());
                    L_LEJOS_OI_A            = Integer.parseInt(element.getElementsByTagName("lejos_oi_a").item(0).getTextContent());
                    L_LEJOS_DP_CRISTAL      = Integer.parseInt(element.getElementsByTagName("lejos_dp_cristal").item(0).getTextContent());
                    L_LEJOS_ENDURECIDO_CRISTAL = Integer.parseInt(element.getElementsByTagName("lejos_endurecido_cristal").item(0).getTextContent());
                    L_LEJOS_CAPA_CRISTAL    = Integer.parseInt(element.getElementsByTagName("lejos_capa_cristal").item(0).getTextContent());
                    L_LEJOS_PLUS_MAX        = Integer.parseInt(element.getElementsByTagName("lejos_plus_max").item(0).getTextContent());
                    
                    L_CERCA_MARCA           = Integer.parseInt(element.getElementsByTagName("cerca_marca").item(0).getTextContent());
                    L_CERCA_CRISTAL         = Integer.parseInt(element.getElementsByTagName("cerca_cristal").item(0).getTextContent());
                    L_CERCA_ADD             = Integer.parseInt(element.getElementsByTagName("cerca_add").item(0).getTextContent());
                    L_CERCA_OD_ESF          = Integer.parseInt(element.getElementsByTagName("cerca_od_esf").item(0).getTextContent());
                    L_CERCA_OD_CIL          = Integer.parseInt(element.getElementsByTagName("cerca_od_cil").item(0).getTextContent());
                    L_CERCA_OD_A            = Integer.parseInt(element.getElementsByTagName("cerca_od_a").item(0).getTextContent());
                    L_CERCA_OI_ESF          = Integer.parseInt(element.getElementsByTagName("cerca_oi_esf").item(0).getTextContent());
                    L_CERCA_OI_CIL          = Integer.parseInt(element.getElementsByTagName("cerca_oi_cil").item(0).getTextContent());
                    L_CERCA_OI_A            = Integer.parseInt(element.getElementsByTagName("cerca_oi_a").item(0).getTextContent());
                    L_CERCA_DP_CRISTAL      = Integer.parseInt(element.getElementsByTagName("cerca_dp_cristal").item(0).getTextContent());
                    L_CERCA_ENDURECIDO_CRISTAL = Integer.parseInt(element.getElementsByTagName("cerca_endurecido_cristal").item(0).getTextContent());
                    L_CERCA_CAPA_CRISTAL    = Integer.parseInt(element.getElementsByTagName("cerca_capa_cristal").item(0).getTextContent());
                    L_CERCA_PLUS_MAX        = Integer.parseInt(element.getElementsByTagName("cerca_plus_max").item(0).getTextContent());
                    
                    L_OBS                   = Integer.parseInt(element.getElementsByTagName("obs").item(0).getTextContent());
                    
                    L_INS_NOMBRE            = Integer.parseInt(element.getElementsByTagName("ins_nombre").item(0).getTextContent());
                    L_INS_WEB               = Integer.parseInt(element.getElementsByTagName("ins_web").item(0).getTextContent());
                    L_INS_DIRECCION         = Integer.parseInt(element.getElementsByTagName("ins_direccion").item(0).getTextContent());
                    L_INS_CONTACTO          = Integer.parseInt(element.getElementsByTagName("ins_contacto").item(0).getTextContent());
                    L_NOMBRE_CLIENTE        = Integer.parseInt(element.getElementsByTagName("nombre_cliente").item(0).getTextContent());
                    L_DETALLE         = Integer.parseInt(element.getElementsByTagName("detalle").item(0).getTextContent());
                    L_VALORES                 = Integer.parseInt(element.getElementsByTagName("valores").item(0).getTextContent());
                    L_FECHA_ENTREGA_2       = Integer.parseInt(element.getElementsByTagName("fecha_entrega_2").item(0).getTextContent());
                    L_LUGAR_ENTREGA_2       = Integer.parseInt(element.getElementsByTagName("lugar_entrega_2").item(0).getTextContent());
                    L_HORA_ENTREGA          = Integer.parseInt(element.getElementsByTagName("hora_entrega").item(0).getTextContent());
                }
            }
            System.out.println("Class CalibracionGlobal: Calibracion cargada exitosamente.");
        } catch (Exception e) {
            System.out.println("Class CalibracionGlobal: Error al cargar Calibraciones");
            ALT_TITULOS = -30;
            ALT_LENTE_LEJOS = -18;
            ALT_LENTE_CERCA = -13;
            ALT_OBS = 0;
            ALT_RESUMEN = -3;
            ALT_DETALLE = -3;
            ALT_DATOS_ENTREGA = -50;
        }
     }
    
}
