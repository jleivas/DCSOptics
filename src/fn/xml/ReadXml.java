/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn.xml;

import fn.CalibracionGlobal;
import fn.GlobalValues;
import fn.Crypt;
import fn.OnLineOperation;
import fn.OptionPane;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author jorge
 */
public class ReadXml {
    public static void readCalibracionGlobalXml(){
        System.out.println("ReadXml:readCalibracionGlobalXml()");
        try {
            File archivo = new File(GlobalValues.FILES_PATH+"calPrint.xml");
            
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document documento = db.parse(archivo);
            
            documento.getDocumentElement().normalize();
            NodeList filas = documento.getElementsByTagName("fila");
            for (int temp = 0; temp < filas.getLength(); temp++) {
                Node nodo = filas.item(temp);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;
                    CalibracionGlobal.ALT_TITULOS = Integer.parseInt(element.getElementsByTagName("titulos").item(0).getTextContent());
                    CalibracionGlobal.ALT_LENTE_LEJOS = Integer.parseInt(element.getElementsByTagName("lejos").item(0).getTextContent());
                    CalibracionGlobal.ALT_LENTE_CERCA = Integer.parseInt(element.getElementsByTagName("cerca").item(0).getTextContent());
                    CalibracionGlobal.ALT_OBS = Integer.parseInt(element.getElementsByTagName("obs").item(0).getTextContent());
                    CalibracionGlobal.ALT_RESUMEN = Integer.parseInt(element.getElementsByTagName("resumen").item(0).getTextContent());
                    CalibracionGlobal.ALT_DATOS_ENTREGA = Integer.parseInt(element.getElementsByTagName("entrega").item(0).getTextContent());
                }
            }
            
            filas = documento.getElementsByTagName("columna");
            for (int temp = 0; temp < filas.getLength(); temp++) {
                Node nodo = filas.item(temp);
                if(nodo.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) nodo;
                    CalibracionGlobal.L_FECHA                 = Integer.parseInt(element.getElementsByTagName("fecha").item(0).getTextContent());
                    CalibracionGlobal.L_FOLIO                 = Integer.parseInt(element.getElementsByTagName("folio").item(0).getTextContent());
                    CalibracionGlobal.L_LUGAR_ENTREGA_1       = Integer.parseInt(element.getElementsByTagName("lugar_entrega_1").item(0).getTextContent());
                    CalibracionGlobal.L_FECHA_ENTREGA_1       = Integer.parseInt(element.getElementsByTagName("fecha_entrega_1").item(0).getTextContent());
                    CalibracionGlobal.L_DATOS_CLIENTE         = Integer.parseInt(element.getElementsByTagName("datos_cliente").item(0).getTextContent());
                    CalibracionGlobal.L_DIRECCION_CLIENTE     = Integer.parseInt(element.getElementsByTagName("direccion_cliente").item(0).getTextContent());
                    CalibracionGlobal.L_VALOR_TOTAL_1         = Integer.parseInt(element.getElementsByTagName("valor_total_1").item(0).getTextContent());
                    
                    CalibracionGlobal.L_LEJOS_MARCA           = Integer.parseInt(element.getElementsByTagName("lejos_marca").item(0).getTextContent());
                    CalibracionGlobal.L_LEJOS_CRISTAL         = Integer.parseInt(element.getElementsByTagName("lejos_cristal").item(0).getTextContent());
                    CalibracionGlobal.L_LEJOS_OD_ESF          = Integer.parseInt(element.getElementsByTagName("lejos_od_esf").item(0).getTextContent());
                    CalibracionGlobal.L_LEJOS_OD_CIL          = Integer.parseInt(element.getElementsByTagName("lejos_od_cil").item(0).getTextContent());
                    CalibracionGlobal.L_LEJOS_OD_A            = Integer.parseInt(element.getElementsByTagName("lejos_od_a").item(0).getTextContent());
                    CalibracionGlobal.L_LEJOS_OI_ESF          = Integer.parseInt(element.getElementsByTagName("lejos_oi_esf").item(0).getTextContent());
                    CalibracionGlobal.L_LEJOS_OI_CIL          = Integer.parseInt(element.getElementsByTagName("lejos_oi_cil").item(0).getTextContent());
                    CalibracionGlobal.L_LEJOS_OI_A            = Integer.parseInt(element.getElementsByTagName("lejos_oi_a").item(0).getTextContent());
                    CalibracionGlobal.L_LEJOS_DP_CRISTAL      = Integer.parseInt(element.getElementsByTagName("lejos_dp_cristal").item(0).getTextContent());
                    CalibracionGlobal.L_LEJOS_ENDURECIDO_CRISTAL = Integer.parseInt(element.getElementsByTagName("lejos_endurecido_cristal").item(0).getTextContent());
                    CalibracionGlobal.L_LEJOS_CAPA_CRISTAL    = Integer.parseInt(element.getElementsByTagName("lejos_capa_cristal").item(0).getTextContent());
                    CalibracionGlobal.L_LEJOS_PLUS_MAX        = Integer.parseInt(element.getElementsByTagName("lejos_plus_max").item(0).getTextContent());
                    
                    CalibracionGlobal.L_CERCA_MARCA           = Integer.parseInt(element.getElementsByTagName("cerca_marca").item(0).getTextContent());
                    CalibracionGlobal.L_CERCA_CRISTAL         = Integer.parseInt(element.getElementsByTagName("cerca_cristal").item(0).getTextContent());
                    CalibracionGlobal.L_CERCA_ADD             = Integer.parseInt(element.getElementsByTagName("cerca_add").item(0).getTextContent());
                    CalibracionGlobal.L_CERCA_OD_ESF          = Integer.parseInt(element.getElementsByTagName("cerca_od_esf").item(0).getTextContent());
                    CalibracionGlobal.L_CERCA_OD_CIL          = Integer.parseInt(element.getElementsByTagName("cerca_od_cil").item(0).getTextContent());
                    CalibracionGlobal.L_CERCA_OD_A            = Integer.parseInt(element.getElementsByTagName("cerca_od_a").item(0).getTextContent());
                    CalibracionGlobal.L_CERCA_OI_ESF          = Integer.parseInt(element.getElementsByTagName("cerca_oi_esf").item(0).getTextContent());
                    CalibracionGlobal.L_CERCA_OI_CIL          = Integer.parseInt(element.getElementsByTagName("cerca_oi_cil").item(0).getTextContent());
                    CalibracionGlobal.L_CERCA_OI_A            = Integer.parseInt(element.getElementsByTagName("cerca_oi_a").item(0).getTextContent());
                    CalibracionGlobal.L_CERCA_DP_CRISTAL      = Integer.parseInt(element.getElementsByTagName("cerca_dp_cristal").item(0).getTextContent());
                    CalibracionGlobal.L_CERCA_ENDURECIDO_CRISTAL = Integer.parseInt(element.getElementsByTagName("cerca_endurecido_cristal").item(0).getTextContent());
                    CalibracionGlobal.L_CERCA_CAPA_CRISTAL    = Integer.parseInt(element.getElementsByTagName("cerca_capa_cristal").item(0).getTextContent());
                    CalibracionGlobal.L_CERCA_PLUS_MAX        = Integer.parseInt(element.getElementsByTagName("cerca_plus_max").item(0).getTextContent());
                    
                    CalibracionGlobal.L_OBS                   = Integer.parseInt(element.getElementsByTagName("obs").item(0).getTextContent());
                    
                    CalibracionGlobal.L_INS_NOMBRE            = Integer.parseInt(element.getElementsByTagName("ins_nombre").item(0).getTextContent());
                    CalibracionGlobal.L_INS_WEB               = Integer.parseInt(element.getElementsByTagName("ins_web").item(0).getTextContent());
                    CalibracionGlobal.L_INS_DIRECCION         = Integer.parseInt(element.getElementsByTagName("ins_direccion").item(0).getTextContent());
                    CalibracionGlobal.L_INS_CONTACTO          = Integer.parseInt(element.getElementsByTagName("ins_contacto").item(0).getTextContent());
                    CalibracionGlobal.L_NOMBRE_CLIENTE        = Integer.parseInt(element.getElementsByTagName("nombre_cliente").item(0).getTextContent());
                    CalibracionGlobal.L_DETALLE         = Integer.parseInt(element.getElementsByTagName("detalle").item(0).getTextContent());
                    CalibracionGlobal.L_VALORES                 = Integer.parseInt(element.getElementsByTagName("valores").item(0).getTextContent());
                    CalibracionGlobal.L_FECHA_ENTREGA_2       = Integer.parseInt(element.getElementsByTagName("fecha_entrega_2").item(0).getTextContent());
                    CalibracionGlobal.L_LUGAR_ENTREGA_2       = Integer.parseInt(element.getElementsByTagName("lugar_entrega_2").item(0).getTextContent());
                    CalibracionGlobal.L_HORA_ENTREGA          = Integer.parseInt(element.getElementsByTagName("hora_entrega").item(0).getTextContent());
                }
            }
            System.out.println("Class CalibracionGlobal: Calibracion cargada exitosamente.");
        } catch (Exception e) {
            System.out.println("Class CalibracionGlobal: Error al cargar Calibraciones");
            CalibracionGlobal.ALT_TITULOS = -30;
            CalibracionGlobal.ALT_LENTE_LEJOS = -18;
            CalibracionGlobal.ALT_LENTE_CERCA = -13;
            CalibracionGlobal.ALT_OBS = 0;
            CalibracionGlobal.ALT_RESUMEN = -3;
            CalibracionGlobal.ALT_DATOS_ENTREGA = -50;
        }
    }
    
    
    public static void readLocalXml() throws ParserConfigurationException, SAXException, IOException{
        System.out.println("ReadXml:readLocalXml()");
        File archivo = new File(GlobalValues.FILES_PATH+"local.xml");
            
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            
            Document documento = db.parse(archivo);

            documento.getDocumentElement().normalize();
            NodeList filas = documento.getElementsByTagName("detalle");
            for (int temp = 0; temp < filas.getLength(); temp++) {
                Node nodo = filas.item(temp);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;
                    GlobalValues.LOCAL_ID = element.getElementsByTagName("id").item(0).getTextContent();
                    GlobalValues.API_URI = Crypt.ds(element.getElementsByTagName("uri").item(0).getTextContent());
                    GlobalValues.PASS = Crypt.ds(element.getElementsByTagName("port").item(0).getTextContent());
                    GlobalValues.USERNAME = element.getElementsByTagName("name").item(0).getTextContent();
                    int st = 0;
                    try{
                        st = Integer.parseInt(element.getElementsByTagName("st").item(0).getTextContent());
                    }catch(Exception e){
                        st=0;
                    }
                    if(st == 1)
                        GlobalValues.LICENCE = true;
                    else 
                        GlobalValues.LICENCE = false;
                    GlobalValues.EXP_DATE = element.getElementsByTagName("date").item(0).getTextContent();
                }
            }
    }
    
    public static boolean readRemoteLastUpdateXmlAndCreateValidateXml(){
        System.out.println("ReadXml:readRemoteLastUpdateXmlAndCreateValidateXml()");
        try{
            if(!GlobalValues.API_URI.endsWith("validate.xml")){
               return false; 
            }
            String apiUri = GlobalValues.API_URI.replaceAll("validate.xml", "lastUpdate.xml");
            URL url = new URL(apiUri);
            //URLConnection conn = url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

            String entrada;
            String cadena="";

            while ((entrada = br.readLine()) != null){
                    cadena = cadena + entrada;
            }

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            //Document documento = db.parse(conn.getInputStream());
            InputSource archivo = new InputSource();
            archivo.setCharacterStream(new StringReader(cadena)); 

            Document documento = db.parse(archivo);
            documento.getDocumentElement().normalize();
            documento.getDocumentElement().normalize();

            NodeList nodeLista = documento.getElementsByTagName("update");

            for (int s = 0; s < nodeLista.getLength(); s++) {

                Node primerNodo = nodeLista.item(s);
                int id;
                String urlUpdate1;
                String urlUpdate2;

                if (primerNodo.getNodeType() == Node.ELEMENT_NODE) {

                    Element primerElemento = (Element) primerNodo;

                    NodeList primerNombreElementoLista =
                                    primerElemento.getElementsByTagName("id");
                    Element primerNombreElemento =
                                    (Element) primerNombreElementoLista.item(0);
                    NodeList primerNombre = primerNombreElemento.getChildNodes();
                    id = Integer.parseInt(((Node) primerNombre.item(0)).getNodeValue().toString());
                    NodeList segundoNombreElementoLista =
                                    primerElemento.getElementsByTagName("url1");
                    Element segundoNombreElemento =
                                    (Element) segundoNombreElementoLista.item(0);
                    NodeList segundoNombre = segundoNombreElemento.getChildNodes();
                    urlUpdate1 =  ((Node) segundoNombre.item(0)).getNodeValue().toString();
                    urlUpdate1 = Crypt.ds(urlUpdate1);
                    NodeList tercerNombreElementoLista =
                                    primerElemento.getElementsByTagName("url2");
                    Element tercerNombreElemento =
                                    (Element) tercerNombreElementoLista.item(0);
                    NodeList tercerNombre = tercerNombreElemento.getChildNodes();
                    urlUpdate2 = Crypt.ds(((Node) tercerNombre.item(0)).getNodeValue().toString());
                    if(id > GlobalValues.ID_UPDATE){
                        
                        if(OptionPane.getUpdateConfirmation()){
                            if(OnLineOperation.downloadFileUpdate(urlUpdate1,urlUpdate2)){
                                GlobalValues.ID_UPDATE = id;
                                GlobalValues.URL_UPDATE = Crypt.en(urlUpdate1);
                                SaveXml.updateLastUpdateXml();
                                OptionPane.showInfoMessage("Actualización completada exitosamente, porfavor, vuelva a abrir el sistema.","Actualización completa");
                                
                                return true;
                            }
                        }else{
                            return false;
                        }
                    }
                }
            }
            return false;
        }
        catch (Exception e) {
            System.out.println("readRemoteLastUpdateXmlAndCreateValidateXml() Exception:"+e.getMessage());
            return false;
        }
    }
            
    public static void readLocalLastUpdateXml(){
        System.out.println("ReadXml:readLocalLastUpdateXml()");
        try{
            File archivo = new File(GlobalValues.FILES_PATH+"lastUpdate.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document documento = db.parse(archivo);

            documento.getDocumentElement().normalize();
            NodeList filas = documento.getElementsByTagName("update");
            for (int temp = 0; temp < filas.getLength(); temp++) {
                Node nodo = filas.item(temp);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;
                    GlobalValues.ID_UPDATE = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
                    GlobalValues.URL_UPDATE = Crypt.ds(element.getElementsByTagName("url").item(0).getTextContent());
                }
            }
        } catch (Exception e) {
            System.out.println("Class RegistroGlobal: Error al cargar ID_UPDATE");
            return;
        }
    
    }
}
