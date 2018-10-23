/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn;

import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import static view.opanel.MPanel.MpanelContent;
import static view.opanel.OPanel.OpanelContent;
import view.opanel.OpanelConfirm;
import view.opanel.OpanelMessage;

/**
 *
 * @author jorge
 */
public class OptionPane {
    private static int ancho = 549;
    private static int alto = 220;
    private static int locat = 5;
    private static String className = "OptionPane";
    private static boolean confirm = false;
    
    private static String CONFIG_TITLE = "Opciones de configuración";
    private static String INVENTARY_TITLE = "Opciones de inventario";
    private static String INVENTARY_CHOOSER_TITLE = "Seleccione inventario";
    private static String MAIL_EXPORT_TITLE = "Exportar correos electrónicos";
    private static String CLIENT_CHOOSER_TITLE = "Seleccione cliente";
    private static String DATE_CHOOSER_TITLE = "Seleccione una fecha";
    private static String USER_CHOOSER_TITLE = "Seleccione usuario";
    private static String TOOL_TITLE = "Opciones de herramienta";
    private static String USER_DATA_TITLE = "Modificar mis datos";
    private static String COMPANY_DATA_TITLE = "Modificar datos de la empresa";
    private static String REGISTRAR_OFICINA = "Modificar datos de la oficina";
    private static String COMPANY_DATA_TITLE_CREATE = "Ingrese los datos de la empresa";
    private static String REGISTRAR_OFICINA_CREATE = "Ingrese los datos de la oficina";
    private static String CONV_CHOOSER_TITLE = "Seleccione un convenio";
    private static String FICHA_GARANTIA_TITLE = "Ingresa datos de entrega para generar garantía";
    private static String DELIVER_DATA_TITLE = "Datos de despacho";
    private static String CONVENY_DATA_RECEPTOR = "Ingrese los datos del receptor";
    private static String PAY_AGREEENT_FEES = "Registro de cuotas pagadas";
    private static String REGISTRAR_LICENCIA = "Ingrese su licencia";
    private static String REGISTRAR_TOKEN = "Ingrese un token";
    
    public static void showOptionPanel(javax.swing.JPanel p1, String title){
        GV.opanel().lblTitle.setText(title);
        GV.opanel().setVisible(true);
        p1.setSize(ancho, alto);
        p1.setLocation(locat, locat);
        OpanelContent.removeAll();
        OpanelContent.add(p1,BorderLayout.CENTER);
        OpanelContent.revalidate();
        OpanelContent.repaint();
    }
    
    /**
     * 
     * @param title
     * @param message
     * @param statusMsg 1: Information, 2: Warning, 3: Error
     */
    public static void showMsg(String title, String message, int statusMsg){
        if(title.length() > 40){
            message = title.toUpperCase()+"\n\n"+message;
            title = title.substring(0,38)+"...";
        }
        GV.setMsgStatus(statusMsg);
        OpanelMessage p1 = new OpanelMessage();
        GV.mpanel().lblTitle.setText(title);
        if(p1 instanceof OpanelMessage){
            ((OpanelMessage) p1).lblTitle.setText(title);
            ((OpanelMessage) p1).updateMsg(title, message,statusMsg);
        }
        
        GV.mpanel().setVisible(true);
        p1.setSize(ancho, alto);
        p1.setLocation(locat, locat);
        
        MpanelContent.removeAll();
        MpanelContent.add(p1,BorderLayout.CENTER);
        MpanelContent.revalidate();
        MpanelContent.repaint();
    }

    public static void closeInfoPanel() {
        GV.mpanel().setVisible(false);
    }
    
    public static void closeOptionPanel() {
        GV.opanel().setVisible(false);
    }

    public static boolean getConfirmation(String title, String message, int statusMsg){
        int resp = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(resp == JOptionPane.YES_OPTION)
            return true;
        return false;
    }
    public static boolean getConfirm(){
        if(confirm){
            setConfirm(false);
            return true;
        }
        return confirm;
    }
    private static void showConfirm(String title, String message, int statusMsg){
        if(title.length() > 40){
            message = title.toUpperCase()+"\n\n"+message;
            title = title.substring(0,38)+"...";
        }
        GV.setMsgStatus(statusMsg);
        OpanelConfirm p1 = new OpanelConfirm();
        GV.mpanel().lblTitle.setText(title);
        if(p1 instanceof OpanelConfirm){
            ((OpanelConfirm) p1).lblTitle.setText(title);
            ((OpanelConfirm) p1).lblMessage.setText(message);
        }
        
        GV.mpanel().setVisible(true);
        p1.setSize(ancho, alto);
        p1.setLocation(locat, locat);
        
        MpanelContent.removeAll();
        MpanelContent.add(p1,BorderLayout.CENTER);
        MpanelContent.revalidate();
        MpanelContent.repaint();
    }
    public static void setConfirm(boolean param){
        confirm = param;
    }

    public static String titleConfig(){
        return CONFIG_TITLE;
    }
    
    public static String titleCompanyData(){
        return COMPANY_DATA_TITLE;
    }
    
    public static String titleCompanyDataCreate(){
        return COMPANY_DATA_TITLE_CREATE;
    }
    
    public static String titleInventary(){
        return INVENTARY_TITLE;
    }
    
    public static String titleInventaryChooser(){
        return INVENTARY_CHOOSER_TITLE;
    }
    
    public static String titleMailExport(){
        return MAIL_EXPORT_TITLE;
    }
    
    public static String titleClientChooser(){
        return CLIENT_CHOOSER_TITLE;
    }
    
    public static String titleConvenyChooser() {
        return CONV_CHOOSER_TITLE;
    }
    
    public static String titleDateChooser(){
        return DATE_CHOOSER_TITLE;
    }
    
    public static String titleUserChooser(){
        return USER_CHOOSER_TITLE;
    }
    
    public static String titleTool(){
        return TOOL_TITLE;
    }
    
    public static String titleUserData(){
        return USER_DATA_TITLE;
    }

    public static String titleFichaGuarantee() {
        return FICHA_GARANTIA_TITLE;
    }

    public static String titleDeliver() {
        return DELIVER_DATA_TITLE;
    }

    public static String titleConvenyDataReceptor() {
        return CONVENY_DATA_RECEPTOR;
    }

    public static String titlePayAgreementFees() {
        return PAY_AGREEENT_FEES;
    }

    public static String titleRegistrarLicencia() {
        return REGISTRAR_LICENCIA;
    }

    public static String titleOfficeData() {
        return REGISTRAR_OFICINA;
    }
    
    public static String titleOfficeDataCreate() {
        return REGISTRAR_OFICINA_CREATE;
    }

    public static String titleRegistrarToken() {
        return REGISTRAR_TOKEN;
    }
}
