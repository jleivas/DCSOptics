/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn;

import fn.close.Close;
import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import static view.OPanel.OpanelContent;
import view.opanel.OpanelMessage;

/**
 *
 * @author jorge
 */
public class OptionPane {
    private static int ancho = 549;
    private static int alto = 220;
    private static int locat = 5;
    
    public static void showPanel(javax.swing.JPanel p1, String title){
        GlobalValues.INFOPANEL.lblTitle.setText(title);
        GlobalValues.INFOPANEL.setVisible(true);
        p1.setSize(ancho, alto);
        p1.setLocation(locat, locat);
        OpanelContent.removeAll();
        OpanelContent.add(p1,BorderLayout.CENTER);
        OpanelContent.revalidate();
        OpanelContent.repaint();
    }
    
    public static void showMsg(String title, String message, int statusMsg){
        if(title.length() > 40){
            message = title.toUpperCase()+"\n\n"+message;
            title = title.substring(0,38)+"...";
        }
        GlobalValues.MSG_STATUS = statusMsg;
        OpanelMessage p1 = new OpanelMessage();
        GlobalValues.INFOPANEL.lblTitle.setText(title);
        if(p1 instanceof OpanelMessage){
            ((OpanelMessage) p1).lblTitle.setText(title);
            ((OpanelMessage) p1).lblMessage.setText(message);
        }
        
        GlobalValues.INFOPANEL.setVisible(true);
        p1.setSize(ancho, alto);
        p1.setLocation(locat, locat);
        
        OpanelContent.removeAll();
        OpanelContent.add(p1,BorderLayout.CENTER);
        OpanelContent.revalidate();
        OpanelContent.repaint();
    }

    public void closeOPanel() {
        GlobalValues.INFOPANEL.setVisible(false);
    }

    
    static String getUserLocalId() {
        System.out.println("OPTIONPANE:getUserLocalId()");
        JTextField id = new JTextField();
            int res = JOptionPane.showConfirmDialog(null,id, "Ingrese un licencia válida",JOptionPane.OK_CANCEL_OPTION);
            if(res < 0){
                Close.close(0);
            }else{
                if(res == 2)
                    Close.close(0);
                return id.getText();
            }
        return null;
    }

    static String getUserUri() {
        System.out.println("OPTIONPANE:getUserUri()");
        String strUri = "";
        JTextField jtfUri = new JTextField();
            int res = JOptionPane.showConfirmDialog(null,jtfUri, "Ingrese un puerto de validación",JOptionPane.OK_CANCEL_OPTION);
            if(res < 0){
                Close.close(0);
            }else{
                if(res == 2)
                    Close.close(0);
                strUri = jtfUri.getText();
                if(strUri.startsWith("https://")){
                    strUri = strUri.replaceAll("https://", "http://");
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
                return strUri;
            }
        return null;
    }

    static String getUserPort() {
        JPasswordField pwd = new JPasswordField(10);
            int res = JOptionPane.showConfirmDialog(null,pwd, "Ingrese clave de validación",JOptionPane.OK_CANCEL_OPTION);
            if(res < 0){
                Close.close(0);
            }else{
                if(res == 2)
                    Close.close(0);
                return pwd.getText();
            }
        return null;
    }
    
    public static boolean getUpdateConfirmation(){
        int opcion = JOptionPane.NO_OPTION;
        String botones1[] = {"Actualizar ahora","Cancelar"};
        opcion = JOptionPane.showOptionDialog(null, "Existe una nueva versión disponible, ¿Desea actualizar el sistema?", "Actualización disponible", 0, 0, null, botones1, null);
        if(opcion == JOptionPane.YES_OPTION){
            return true;
        }
        return false;
    }

    public static void showInfoMessage(String message, String title) {
        JOptionPane.showMessageDialog(null,message,title,JOptionPane.INFORMATION_MESSAGE);
    }
    public static void showErrorMessage(String message, String title) {
        JOptionPane.showMessageDialog(null,message,title,JOptionPane.ERROR_MESSAGE);
    }
    public static void showWarningMessage(String message, String title) {
        JOptionPane.showMessageDialog(null,message,title,JOptionPane.WARNING_MESSAGE);
    }
}
