/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn;

import bd.BD;
import correo.FnEnviar;
import dao.CalibracionGlobal;
import dao.InfoDao;
import dao.RegistroGlobal;
import dao.UserDao;
import entities.Info;
import entities.Lente;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.apache.commons.codec.binary.Base64;
import org.xml.sax.SAXException;

/**
 *
 * @author home
 */
public class FnInfo {
    public boolean guardar(Info info) throws SQLException, ClassNotFoundException{
        if(info != null){
            InfoDao load = new InfoDao();
            if(load.agregar(info))
                return true;
        }
        return false;
    }
    
    public void loadXml() throws ParserConfigurationException, SAXException, IOException, TransformerException{
        RegistroGlobal.initDataId();//carga el id del producto y la uri de la api
        CalibracionGlobal.cargarCalibracion();//carga valores de calibracion para la impresion
        RegistroGlobal.cargarRegistroGlobal();
        if(isActive()){
            RegistroGlobal.validateUpdate();
            if(isConnected()){
                if(RegistroGlobal.readXMLUpdateOnline()){
                    System.exit(0);
                }
            }
        }
    }
    
    public boolean modificar(Info info) throws SQLException, ClassNotFoundException{
        if(info != null){
            InfoDao load = new InfoDao();
            if(load.modificar(info))
                return true;
        }
        return false;
    }
    
    public boolean buscar(int id) throws SQLException, ClassNotFoundException{
        InfoDao load = new InfoDao();
        if(load.existe(id))
            return true;
        return false;
    }
    
    public Info cargar(int id) throws SQLException, ClassNotFoundException{
        InfoDao load = new InfoDao();
        Info temp = load.cargar(id);
        return temp;
    }
    
    public String actualizarFolio(String folio, int actual){
        try{
            int numFolio = Integer.parseInt(folio);
            if(numFolio > actual+1000)
                return "El Folio ingresado es muy alto, ingrese un valor mas pequeño.";
            if(numFolio < actual)
                return "El Folio ingresado es muy pequeño, ingrese un valor mas alto.";
            InfoDao load = new InfoDao();
            return load.actualizarFolio(numFolio);
        }catch(Exception e){
            return "Error, El valor debe ser numérico, Detalle: "+e.getMessage();
        }
        
    }
    
    
    public boolean actualizarFolioBoolean(String folio){
        try{
            int numFolio = Integer.parseInt(folio);
            InfoDao load = new InfoDao();
            return load.actualizarFolioBoolean(numFolio);
        }catch(Exception e){
            return false;
        }
        
    }
    
    public int obtenerFolio() throws SQLException, ClassNotFoundException{
        InfoDao load = new InfoDao();
        return load.obtenerFolio();
    }
    
    public String getPass() throws SQLException, ClassNotFoundException{
        InfoDao load = new InfoDao();
        return load.obtenerPass();
    }
    
    public void crearBackUp(String dataBase, String user, String pass) throws SQLException, ClassNotFoundException {
        if(!esLocal()){
            JOptionPane.showMessageDialog(null, "No se puede respaldar desde este equipo, utilize el servidor","Error de servidor",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        try{
            Runtime runtime = Runtime.getRuntime();
            File bkfile = new File(System.getProperty("user.dir")+"\\files\\respaldoBD.sql");
            if(System.getProperty("os.name").equals("Linux")){
                bkfile = new File(System.getProperty("user.dir")+"/files/respaldoBD.sql");
                JOptionPane.showMessageDialog(null, "No se puede respaldar desde este equipo, MysqlDump no configurado en Linux","Error de configuración de S.O.",JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            FileWriter fw = new FileWriter(bkfile);
            Process child;
            if(System.getProperty("os.name").equals("Linux")){
                child = runtime.exec(System.getProperty("user.dir")+"/files/mysqldump --opt --password="+pass+" --user="+user+" --databases "+dataBase+" -R");
            }else{
                child = runtime.exec(System.getProperty("user.dir")+"\\files\\mysqldump --opt --password="+pass+" --user="+user+" --databases "+dataBase+" -R");
            }
            InputStreamReader irs = new InputStreamReader(child.getInputStream());
            BufferedReader bf = new BufferedReader(irs);

            String line;
            while((line = bf.readLine()) != null){
                fw.write(line+"\n");
            }
            fw.close();
            irs.close();
            bf.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error, no se pudo realizar el BackUp.["+ex.getMessage()+"]");
        }
        FnEnviar send = new FnEnviar();
        InfoDao load = new InfoDao();
        Info empresa = load.cargar(1);
        send.enviarRespaldoFromFile(empresa);
    }

    public void probarConexion() {
        BD bd = new BD();
        try {
            bd.obtener();//prueba con el servidor local
        } catch (Exception e1) {
            bd.setBd("192.168.0.1");//cambia el servidor local a la ip dada validado solo en windows
        } 
    }

    public boolean esLocal() {
        BD bd = new BD();
        return bd.esLocal();
    }

    public boolean setAdminMail(String adminMail) throws SQLException, ClassNotFoundException {
        InfoDao load = new InfoDao();
        return load.setAdminMail(adminMail,1);
    }

    public boolean setPass(String pass) throws SQLException, ClassNotFoundException {
        InfoDao load = new InfoDao();
        return load.setPass(pass,1);
    }


    public boolean setPassInventario(String pass) throws SQLException, ClassNotFoundException {
        UserDao load = new UserDao();
        return load.cambiarPass(pass);
    }

    public void generarOrdenCompra() throws SQLException, ClassNotFoundException {
        FnLentes load = new FnLentes();
        ArrayList<Lente> lista = load.stockBajo();
        JFileChooser archivo = new JFileChooser();
        Fechas process = new Fechas();
        //DATOS DE LA EMPRESA
        InfoDao loadEmpresa = new InfoDao();
        Info empresa = new Info();
        empresa = loadEmpresa.cargar(1);//el id siempre sera uno
        //***************
        int resp;
        if(lista.size()<1){
            JOptionPane.showMessageDialog(null, "No existen productos con stock bajo para generar la orden de compra.");
            return;
        }
        resp = archivo.showSaveDialog(archivo);
        if(resp == JFileChooser.APPROVE_OPTION){
            if(lista.size()>0){
                //[filas][columnas]
                int filas = lista.size()+6;
                String [][] entrada = new String[filas][4];
                entrada[0][1] = "Orden de compra generada el "+process.imprimirFechaActual()+"";
                entrada[1][0] = "Empresa:";
                entrada[1][1] = empresa.getNombre();
                entrada[2][0] = "Sistema:";
                entrada[2][1] = "Optidata 2017";
                entrada[3][0] = "Soporte:";
                entrada[3][1] = "www.softdirex.cl";
                entrada[5][0] = "Codigo";
                entrada[5][1] = "Marca";
                entrada[5][2] = "Color";
                entrada[5][3] = "Cantidad";
                int contFilas = 6;
                for (Lente temp : lista) {
                    for(int i = 0;i< 7; i++){
                        if(i==0)
                            entrada[contFilas][i] = temp.getId();
                        else if(i == 1)
                            entrada[contFilas][i] = temp.getMarca();
                        else if(i == 2)
                            entrada[contFilas][i] = temp.getColor();
                        else if(i == 3)
                            entrada[contFilas][i] = ""+0;
                    }
                    contFilas++;
                }
                String ruta = String.valueOf(archivo.getSelectedFile().toString())+"-["+process.imprimirFechaActual()+"].xls";
                GuardarPlanilla saveXls = new GuardarPlanilla();
                saveXls.generarExcel(entrada, ruta);
                JOptionPane.showMessageDialog(null, "Archivo creado con exito.");
                return;
            }
        }else if(resp == JFileChooser.CANCEL_OPTION){
            JOptionPane.showMessageDialog(null, "La operacion ha sido cancelada.");
        }
        return;
    }

    public void exportarInventario() throws SQLException, ClassNotFoundException {
        FnLentes load = new FnLentes();
        ArrayList<Lente> lista = load.activos();
        JFileChooser archivo = new JFileChooser();
        Fechas process = new Fechas();
        //DATOS DE LA EMPRESA
        InfoDao loadEmpresa = new InfoDao();
        Info empresa = new Info();
        empresa = loadEmpresa.cargar(1);//el id siempre sera uno
        //***************
        int resp;
        if(lista.size()<1){
            JOptionPane.showMessageDialog(null, "No existen productos registrados.");
            return;
        }
        resp = archivo.showSaveDialog(archivo);
        if(resp == JFileChooser.APPROVE_OPTION){
            if(lista.size()>0){
                //[filas][columnas]
                int filas = lista.size()+6;
                String [][] entrada = new String[filas][6];
                entrada[0][1] = "Registro de inventario generado el "+process.imprimirFechaActual()+"";
                entrada[1][0] = "Empresa:";
                entrada[1][1] = empresa.getNombre();
                entrada[2][0] = "Sistema:";
                entrada[2][1] = "Optidata 2017";
                entrada[3][0] = "Soporte:";
                entrada[3][1] = "www.softdirex.cl";
                entrada[5][0] = "Codigo";
                entrada[5][1] = "Marca";
                entrada[5][2] = "Color";
                entrada[5][3] = "Cantidad";
                entrada[5][4] = "Valor ref.";
                entrada[5][5] = "Precio";
                int contFilas = 6;
                for (Lente temp : lista) {
                    for(int i = 0;i< 7; i++){
                        if(i==0)
                            entrada[contFilas][i] = temp.getId();
                        else if(i == 1)
                            entrada[contFilas][i] = temp.getMarca();
                        else if(i == 2)
                            entrada[contFilas][i] = temp.getColor();
                        else if(i == 3)
                            entrada[contFilas][i] = ""+temp.getStock();
                        else if(i == 4)
                            entrada[contFilas][i] = ""+temp.getPrecioRef();
                        else if(i == 5)
                            entrada[contFilas][i] = ""+temp.getPrecioAct();
                    }
                    contFilas++;
                }
                String ruta = String.valueOf(archivo.getSelectedFile().toString())+"-["+process.imprimirFechaActual()+"].xls";
                GuardarPlanilla saveXls = new GuardarPlanilla();
                saveXls.generarExcel(entrada, ruta);
                JOptionPane.showMessageDialog(null, "Archivo creado con exito.");
                return;
            }
        }else if(resp == JFileChooser.CANCEL_OPTION){
            JOptionPane.showMessageDialog(null, "La operacion ha sido cancelada.");
        }
        return;
    }

    public void setStaticUser(String user) {
        RegistroGlobal.setUser(user);
    }

    public String getStaticUser() {
        return RegistroGlobal.USERNAME;
    }

    public boolean isActive() {
        return RegistroGlobal.LICENCIA;
    }

    public String cargarFechaLicencia() {
        return RegistroGlobal.EXP_DATE;
    }

    public String desencriptar(String textContent) {
        String secretKey = "qualityinfosolutions"; //llave para desenciptar datos
        String base64EncryptedString = "";
 
        try {
            byte[] message = Base64.decodeBase64(textContent.getBytes("utf-8"));
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
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

    public String getDbPass() {
        return RegistroGlobal.PASS;
    }

    public boolean isConnected(){
        String dirWeb = "www.softdirex.cl";
        int puerto = 80;
        try{
            Socket s = new Socket(dirWeb, puerto);
            if(s.isConnected()){
              System.out.println("Conexión establecida con la dirección: " +  dirWeb + " a travéz del puerto: " + puerto);
            }
        }catch(Exception e){
            System.err.println("No se pudo establecer conexión con: " + dirWeb + " a travéz del puerto: " + puerto);
            return false;
        }
        return true;
    }

    public boolean isLocal() {
        BD bd = new BD();
        return bd.esLocal();
    }

    public int getPrecio(String option, String value) throws SQLException, ClassNotFoundException {
        InfoDao load = new InfoDao();
        switch(option){
            case "lente":
                return load.getPrecioLente(value);
            case "cristal":
                return load.getPrecioCristal(value);
            default:
                return 0;
        }
    }

    public String encriptar(String texto) {
        String secretKey = "qualityinfosolutions"; //llave para encriptar datos
        String base64EncryptedString = "";
 
        try {
 
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
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
}
