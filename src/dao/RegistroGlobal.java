/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import fn.FnInfo;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.zip.ZipInputStream;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author sdx
 */
public class RegistroGlobal {
    public static String USERNAME = "";
    public static boolean LICENCIA = true;
    public static String EXP_DATE = "";
    public static String API_URI;
    public static String LOCAL_ID;
    public static String PASS;
    public static int ID_UPDATE=0;
    public static String URL_UPDATE;
    
    public static void initDataId() throws ParserConfigurationException, SAXException, IOException{
        try{
            File archivo = new File(System.getProperty("user.dir")+"\\files\\xml\\local.xml");
            if(System.getProperty("os.name").equals("Linux")){
                archivo = new File(System.getProperty("user.dir")+"/files/xml/local.xml");
            }
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document documento = db.parse(archivo);

            documento.getDocumentElement().normalize();
            NodeList filas = documento.getElementsByTagName("detalle");
            for (int temp = 0; temp < filas.getLength(); temp++) {
                Node nodo = filas.item(temp);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;
                    LOCAL_ID = element.getElementsByTagName("id").item(0).getTextContent();
                    API_URI = dscrypt(element.getElementsByTagName("uri").item(0).getTextContent());
                    PASS = dscrypt(element.getElementsByTagName("port").item(0).getTextContent());
                }
            }
        } catch (Exception e) {
            JTextField id = new JTextField();
            int res = JOptionPane.showConfirmDialog(null,id, "Ingrese un licencia válida",JOptionPane.OK_CANCEL_OPTION);
            if(res < 0){
                return;
            }else{
                if(res == 2)
                    return;
                String respuesta = id.getText();
                crearLocal(respuesta);
                initDataId();
            }
        }
    }
    
    public static void validateUpdate(){
        try{
            File archivo = new File(System.getProperty("user.dir")+"\\files\\xml\\lastUpdate.xml");
            if(System.getProperty("os.name").equals("Linux")){
                return;
//                archivo = new File(System.getProperty("user.dir")+"/files/xml/lastUpdate.xml");
            }
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document documento = db.parse(archivo);

            documento.getDocumentElement().normalize();
            NodeList filas = documento.getElementsByTagName("update");
            for (int temp = 0; temp < filas.getLength(); temp++) {
                Node nodo = filas.item(temp);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;
                    ID_UPDATE = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
                    URL_UPDATE = element.getElementsByTagName("url").item(0).getTextContent();
                }
            }
        } catch (Exception e) {
            System.out.println("Class RegistroGlobal: Error al cargar ID_UPDATE");
            return;
        }
    }
    
    public static void cargarRegistroGlobal() throws ParserConfigurationException, SAXException, IOException{
        try {
            
            File archivo = new File(System.getProperty("user.dir")+"\\files\\xml\\reg.xml");
            if(System.getProperty("os.name").equals("Linux")){
                archivo = new File(System.getProperty("user.dir")+"/files/xml/reg.xml");
            }
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document documento = db.parse(archivo);
            
            documento.getDocumentElement().normalize();
            NodeList filas = documento.getElementsByTagName("usr");
            for (int temp = 0; temp < filas.getLength(); temp++) {
                Node nodo = filas.item(temp);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;
                    USERNAME = element.getElementsByTagName("name").item(0).getTextContent();
                }
            }
            
            filas = documento.getElementsByTagName("lic");
            for (int temp = 0; temp < filas.getLength(); temp++) {
                Node nodo = filas.item(temp);
                if(nodo.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) nodo;
                    int st;
                    try{
                        st = Integer.parseInt(element.getElementsByTagName("st").item(0).getTextContent());
                    }catch(Exception e){
                        st=0;
                    }
                    
                    if(st == 1)
                        LICENCIA=true;
                    else
                        LICENCIA=false;
                    EXP_DATE = element.getElementsByTagName("date").item(0).getTextContent();
                }
            }
            if(isConnected()){
                if(readXMLOnline())
                    crearRegistroGlobal();
            }
            crearRegistroGlobal();
            int expDias = diasRestantes(EXP_DATE);
            if(expDias <= 5){
                if(expDias == 1){
                    JOptionPane.showMessageDialog(null, "Renueve su licencia, mañana expirará", "Falta un día", JOptionPane.INFORMATION_MESSAGE);
                }else if(expDias == 0){
                    JOptionPane.showMessageDialog(null, "Renueve su licencia, hoy expirará", "Último día", JOptionPane.WARNING_MESSAGE);
                }else if(expDias > 1){
                    JOptionPane.showMessageDialog(null, "Su licencia expirará dentro de "+expDias+" días", "Licencia a punto de caducar", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            System.out.println("Class RegistroGlobal: Registros cargados exitosamente.\nUSERNAME="+USERNAME+", LICENCIA="+LICENCIA+", EXP_DATE="+EXP_DATE);
        } catch (Exception e) {
            JTextField id = new JTextField();
            int res = JOptionPane.showConfirmDialog(null,id, "Ingrese un licencia válida",JOptionPane.OK_CANCEL_OPTION);
            if(res < 0){
                System.exit(0);
            }else{
                if(res == 2)
                    System.exit(0);
                String respuesta = id.getText();
                crearLocal(respuesta);
                initDataId();
                cargarRegistroGlobal();
            }
        }
    }
    
    public static void crearRegistroGlobal(){
         
         Document document = null;
         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         try{
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            document = implementation.createDocument(null, "reg", null);

            //Creación de elementos
            //creamos el elemento principal usr
            Element user = document.createElement("usr"); 
            //creamos un nuevo elemento. usr contiene name
            Element name= document.createElement("name");
            Text valorName = document.createTextNode(USERNAME); 
            
            Element lic = document.createElement("lic"); 
            //creamos un nuevo elemento. lic contiene st y date
            Element st= document.createElement("st");
            Element date= document.createElement("date");
            //Ingresamos la info. 
            Text valorSt;
            if(diasRestantes(EXP_DATE)<0)
                LICENCIA=false;
            if(LICENCIA)
                valorSt = document.createTextNode("1");
            else
                valorSt = document.createTextNode("0");
            Text valorDate = document.createTextNode(EXP_DATE); 

            //Asignamos la versión de nuestro XML
            document.setXmlVersion("1.0"); 
            //Añadimos al usr al documento
            document.getDocumentElement().appendChild(user); 
            user.appendChild(name); 
            //Añadimos valor
            name.appendChild(valorName); 
            
            document.getDocumentElement().appendChild(lic);
            //Añadimos el elemento hijo a la raíz
            lic.appendChild(st);
            st.appendChild(valorSt);
            //Añadimos valor
            lic.appendChild(date); 
            date.appendChild(valorDate);
            
            if(System.getProperty("os.name").equals("Linux")){
                guardaConFormato(document,System.getProperty("user.dir")+"/files/xml/reg.xml");
            }else{
                guardaConFormato(document,System.getProperty("user.dir")+"\\files\\xml\\reg.xml");
            }
            
         }catch(Exception e){
             System.err.println("Class RegistroGlobal: Error");
         }
    }
    
    public static void crearLocal(String licencia){
         String strUri = "";
         String strPort = "";
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
            
            JTextField jtfUri = new JTextField();
            int res = JOptionPane.showConfirmDialog(null,jtfUri, "Ingrese un puerto de validación",JOptionPane.OK_CANCEL_OPTION);
            if(res < 0){
                System.exit(0);
            }else{
                if(res == 2)
                    System.exit(0);
                strUri = jtfUri.getText();
                if(strUri.startsWith("https://")){
                    System.out.println(strUri);
                    strUri = strUri.replaceAll("https://", "http://");
                    System.out.println("cambio de https a http: "+strUri);
                }
                if(!strUri.startsWith("http://")){
                    if(!strUri.startsWith("www.")){
                        strUri = "www."+strUri;
                    }
                    strUri = "http://"+strUri;
                }
                if(!strUri.endsWith(".xml")){
                    if(!strUri.endsWith("/validate")){
                        strUri = strUri+"/validate";
                    }
                    strUri = strUri+".xml";
                }
                strUri = encrypt(strUri);
            }
            
            JPasswordField pwd = new JPasswordField(10);
            int rsp = JOptionPane.showConfirmDialog(null,pwd, "Ingrese clave de validación",JOptionPane.OK_CANCEL_OPTION);
            if(res < 0){
                System.exit(0);
            }else{
                if(res == 2)
                    System.exit(0);
                strPort = pwd.getText();
                strPort = encrypt(strPort);
            }
            
        
            
            Text licence = document.createTextNode(licencia); 
            Text uriData = document.createTextNode(strUri);
            Text portData = document.createTextNode(strPort);

            //Asignamos la versión de nuestro XML
            document.setXmlVersion("1.0"); 
            //Añadimos al detalle al documento
            document.getDocumentElement().appendChild(detalle); 
            detalle.appendChild(id); 
            detalle.appendChild(uri); 
            detalle.appendChild(port); 
            
            document.getDocumentElement().appendChild(detalle);
            
            id.appendChild(licence);
            uri.appendChild(uriData);
            port.appendChild(portData);
            
            
            
            if(System.getProperty("os.name").equals("Linux")){
                guardaConFormato(document,System.getProperty("user.dir")+"/files/xml/local.xml");
            }else{
                guardaConFormato(document,System.getProperty("user.dir")+"\\files\\xml\\local.xml");
            }
            
         }catch(Exception e){
             System.err.println("Class RegistroGlobal: Error");
         }
    }
    
    private static int diasRestantes(String stFecha) throws ParseException{ 
            stFecha = stFecha.replaceAll("-", "/");
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
    }
    
    // Volcamos XML al fichero
public static void guardaConFormato(Document document, String URI){
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
            PrintWriter writer = new PrintWriter(new FileWriter(URI));

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

    private static boolean isConnected(){
        String dirWeb = "www.softdirex.cl";
        int puerto = 80;
        try{
            Socket s = new Socket(dirWeb, puerto);
            if(s.isConnected()){
              System.out.println("Conexión establecida con la dirección: " +  dirWeb + " a travéz del puerto: " + puerto);
            }
        }catch(Exception e){
            System.err.println("No se pudo establecer conexión con: " + dirWeb + " a travez del puerto: " + puerto);
            return false;
        }
        return true;
    }
    
    private static boolean readXMLOnline() throws IOException, ParserConfigurationException, SAXException, TransformerConfigurationException, TransformerException {
        try{
            int cont=0;
            URL url = new URL(API_URI);
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

            NodeList nodeLista = documento.getElementsByTagName("lic");

            for (int s = 0; s < nodeLista.getLength(); s++) {

                Node primerNodo = nodeLista.item(s);
                String id;
                int st;

                if (primerNodo.getNodeType() == Node.ELEMENT_NODE) {

                    Element primerElemento = (Element) primerNodo;

                    NodeList primerNombreElementoLista =
                                    primerElemento.getElementsByTagName("id");
                    Element primerNombreElemento =
                                    (Element) primerNombreElementoLista.item(0);
                    NodeList primerNombre = primerNombreElemento.getChildNodes();
                    id = ((Node) primerNombre.item(0)).getNodeValue().toString();
                    if(id.equals(LOCAL_ID)){
                        cont++;
                        NodeList segundoNombreElementoLista =
                                    primerElemento.getElementsByTagName("st");
                        Element segundoNombreElemento =
                                    (Element) segundoNombreElementoLista.item(0);
                        NodeList segundoNombre = segundoNombreElemento.getChildNodes();

                        st = Integer.parseInt(((Node) segundoNombre.item(0)).getNodeValue().toString());
                        if(st == 1)
                            LICENCIA = true;
                        else
                            LICENCIA = false;
                        NodeList tercerNombreElementoLista =
                                    primerElemento.getElementsByTagName("date");
                        Element tercerNombreElemento =
                                        (Element) tercerNombreElementoLista.item(0);
                        NodeList tercerNombre = tercerNombreElemento.getChildNodes();
                        EXP_DATE = ((Node) tercerNombre.item(0)).getNodeValue().toString();
                    }
                }
            }
            if(cont == 0){
                JOptionPane.showMessageDialog(null, "Esta es una copia fraudulenta del software original.","Error de licencia",JOptionPane.WARNING_MESSAGE);
                System.exit(0);
            }
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
    
    
    public static boolean readXMLUpdateOnline() throws IOException, ParserConfigurationException, SAXException, TransformerConfigurationException, TransformerException {
        int opcion = JOptionPane.NO_OPTION;
        String botones1[] = {"Actualizar ahora","Cancelar"};
        try{
            if(!API_URI.endsWith("validate.xml")){
               return false; 
            }
            String apiUri = API_URI.replaceAll("validate.xml", "lastUpdate.xml");
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
                    urlUpdate1 = dscrypt(urlUpdate1);
                    NodeList tercerNombreElementoLista =
                                    primerElemento.getElementsByTagName("url2");
                    Element tercerNombreElemento =
                                    (Element) tercerNombreElementoLista.item(0);
                    NodeList tercerNombre = tercerNombreElemento.getChildNodes();
                    urlUpdate2 =  ((Node) tercerNombre.item(0)).getNodeValue().toString();
                    urlUpdate2 = dscrypt(urlUpdate2);
                    if(id > ID_UPDATE){
                        opcion = JOptionPane.showOptionDialog(null, "Existe una nueva versión disponible, ¿Desea actualizar el sistema?", "Actualización disponible", 0, 0, null, botones1, null);
                            if(opcion == JOptionPane.YES_OPTION){
                            if(downloadFileUpdate(urlUpdate1,urlUpdate2)){
                                ID_UPDATE = id;
                                URL_UPDATE = encrypt(urlUpdate1);
                                updateLastUpdateXml();
                                JOptionPane.showMessageDialog(null,"Actualización completada exitosamente, porfavor, vuelva a abrir el sistema.","Actualización completa",JOptionPane.INFORMATION_MESSAGE);
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
            System.out.println("Exception:"+e.getMessage());
            return false;
        }
    }

    public static void setUser(String user) {
        USERNAME = user;
        crearRegistroGlobal();
    }

    private static String dscrypt(String textContent) {
        FnInfo load = new FnInfo();
        return load.desencriptar(textContent);
    }
    
    private static String encrypt(String textContent) {
        FnInfo load = new FnInfo();
        return load.encriptar(textContent);
    }

    private static boolean downloadFileUpdate(String url1, String url2) throws MalformedURLException, IOException {
        String fileName = "ODM3.2";
        try{
            URL website = new URL(url1);
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream(fileName+".jar");
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            URL website2 = new URL(url2);
            ReadableByteChannel rbc2 = Channels.newChannel(website2.openStream());
            FileOutputStream fos2 = new FileOutputStream(fileName+".zip");
            fos.getChannel().transferFrom(rbc2, 0, Long.MAX_VALUE);
            descomprimir(System.getProperty("user.dir")+"\\"+fileName+".zip", System.getProperty("user.dir")+"\\"+fileName+".exe");
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    public static void descomprimir(String archivoZIP, String archivoDescomprimido) {
		int BUFFER_SIZE = 1024;
		BufferedOutputStream bos = null;
		FileInputStream fis = null;
		ZipInputStream zipis = null;
		FileOutputStream fos = null;

		try {
			fis = new FileInputStream(archivoZIP);
			zipis = new ZipInputStream(new BufferedInputStream(fis));
			if (zipis.getNextEntry() != null) {
				int len = 0;
				byte[] buffer = new byte[BUFFER_SIZE];
				fos = new FileOutputStream(archivoDescomprimido);
				bos = new BufferedOutputStream(fos, BUFFER_SIZE);

				while ((len = zipis.read(buffer, 0, BUFFER_SIZE)) != -1)
					bos.write(buffer, 0, len);
				bos.flush();
			} else {
				throw new Exception("Zip Vacio");
			}
		} catch (Exception e) {
			System.out.println("Ocurrió un error al descomprimir");
		} finally {
			try{
				bos.close();
				zipis.close();
				fos.close();
				fis.close();
			}
			catch(Exception e){}
		}
   }

    private static void updateLastUpdateXml() {
         Document document = null;
         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         try{
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            document = implementation.createDocument(null, "root", null);

  
            
            Element detalle = document.createElement("update"); 
            Element id= document.createElement("id");
            Element uri= document.createElement("url");
            
            
            Text idData = document.createTextNode(""+ID_UPDATE); 
            Text uriData = document.createTextNode(URL_UPDATE);

            //Asignamos la versión de nuestro XML
            document.setXmlVersion("1.0"); 
            //Añadimos al detalle al documento
            document.getDocumentElement().appendChild(detalle); 
            detalle.appendChild(id); 
            detalle.appendChild(uri); 
            
            document.getDocumentElement().appendChild(detalle);
            
            id.appendChild(idData);
            uri.appendChild(uriData);
            
            if(System.getProperty("os.name").equals("Linux")){
                guardaConFormato(document,System.getProperty("user.dir")+"/files/xml/lastUpdate.xml");
            }else{
                guardaConFormato(document,System.getProperty("user.dir")+"\\files\\xml\\lastUpdate.xml");
            }
            
         }catch(Exception e){
             System.err.println("Class RegistroGlobal: Error al guardar XML LastUpdate");
         }
    }
}
