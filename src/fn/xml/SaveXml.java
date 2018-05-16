/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn.xml;

import fn.GlobalValues;
import fn.Crypt;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 *
 * @author jorge
 */
public class SaveXml {
    
    

    public static void createLocal() {
        System.out.println("SAVEXML:createLocal(String strLicence, String strUri, String strPort)");
         Document document = null;
         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         try{
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            document = implementation.createDocument(null, "local", null);

  
            
            Element detalle = document.createElement("detalle"); 
            Element id= document.createElement("id");//licencia
            Element uri= document.createElement("uri");//url de validacion
            Element port= document.createElement("port");//****
            
            Element name= document.createElement("name");
            Element st= document.createElement("st");
            Element date= document.createElement("date");
            
            
            
            Text licence = document.createTextNode(GlobalValues.LOCAL_ID); 
            Text uriData = document.createTextNode(Crypt.en(GlobalValues.API_URI));
            Text portData = document.createTextNode(Crypt.en(GlobalValues.PASS));
            
            Text nameData = document.createTextNode(GlobalValues.USERNAME);
            int stLicence=0;
            if(GlobalValues.LICENCE)
                stLicence = 1;
            Text stData = document.createTextNode(""+stLicence); 
            Text dateData = document.createTextNode(GlobalValues.EXP_DATE); 

            //Asignamos la versión de nuestro XML
            document.setXmlVersion("1.0"); 
            //Añadimos al detalle al documento
            document.getDocumentElement().appendChild(detalle); 
            detalle.appendChild(id); 
            detalle.appendChild(uri); 
            detalle.appendChild(port);
            
            detalle.appendChild(name);
            detalle.appendChild(st);
            detalle.appendChild(date);
            
            document.getDocumentElement().appendChild(detalle);
            
            id.appendChild(licence);
            uri.appendChild(uriData);
            port.appendChild(portData);
            
            name.appendChild(nameData);
            st.appendChild(stData);
            date.appendChild(dateData);
            
            save(document,"local.xml");
            
            
         }catch(Exception e){
             System.err.println("Class RegistroGlobal: Error");
         }
    }
    
    private static void save(Document document, String fileName){
        System.out.println("SAVEXML:save(Document document, String fileName)");
    try {
        TransformerFactory transFact = TransformerFactory.newInstance();

        //Formateamos el fichero. Añadimos sangrado y la cabecera de XML
        transFact.setAttribute("indent-number", new Integer(3));
        Transformer trans = transFact.newTransformer();
        trans.setOutputProperty(OutputKeys.INDENT, "yes");
        trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");

        //Hacemos la transformación
        StringWriter sw = new StringWriter();
        StreamResult sr = new StreamResult(sw);
        DOMSource domSource = new DOMSource(document);
        trans.transform(domSource, sr);

        //Mostrar información a guardar por consola (opcional)
        //Result console= new StreamResult(System.out);
        //trans.transform(domSource, console);
        try {
            //Creamos fichero para escribir en modo texto
            PrintWriter writer = new PrintWriter(new FileWriter(GlobalValues.FILES_PATH+fileName));
            //Escribimos todo el árbol en el fichero
            writer.println(sw.toString());

            //Cerramos el fichero
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } catch(Exception ex) {
        ex.printStackTrace();
    }
    }

    public static void updateLastUpdateXml() {
        System.out.println("SAVEXML:updateLastUpdateXml()");
         Document document = null;
         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         try{
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            document = implementation.createDocument(null, "root", null);

  
            
            Element detalle = document.createElement("update"); 
            Element id= document.createElement("id");
            Element uri= document.createElement("url");
            
            
            Text idData = document.createTextNode(""+GlobalValues.ID_UPDATE); 
            Text uriData = document.createTextNode(Crypt.en(GlobalValues.URL_UPDATE));

            //Asignamos la versión de nuestro XML
            document.setXmlVersion("1.0"); 
            //Añadimos al detalle al documento
            document.getDocumentElement().appendChild(detalle); 
            detalle.appendChild(id); 
            detalle.appendChild(uri); 
            
            document.getDocumentElement().appendChild(detalle);
            
            id.appendChild(idData);
            uri.appendChild(uriData);
            
            save(document, "lastUpdate.xml");
            
         }catch(Exception e){
             System.err.println("Class SAVEXML: Error al guardar XML LastUpdate");
         }
    }
    
}
