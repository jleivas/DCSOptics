/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static view.ContentAdmin.principalAdmin;
import view.VClientes;
import view.VOficinas;
import view.VConvenios;
import view.VCrearFicha;
import view.VCristales;
import view.VDescuentos;
import view.VDoctores;
import view.VFicha;
import view.VFichas;
import view.VInstituciones;
import view.VInventarios;
import view.VLentes;
import view.VMessages;
import view.VRegistroBajas;
import view.VTipoPagos;
import view.VUsuarios;
import static viewMac.ContentAdminMac.principalAdminMac;
import viewMac.VClientesMac;
import viewMac.VConveniosMac;
import viewMac.VCrearFichaMac;
import viewMac.VCristalesMac;
import viewMac.VDescuentosMac;
import viewMac.VDoctoresMac;
import viewMac.VFichaMac;
import viewMac.VFichasMac;
import viewMac.VInstitucionesMac;
import viewMac.VInventariosMac;
import viewMac.VLentesMac;
import viewMac.VOficinasMac;
import viewMac.VRegistroBajasMac;
import viewMac.VTipoPagosMac;
import viewMac.VUsuariosMac;

/**
 *
 * @author home
 */
public class Boton {
    private int ancho = 1300;
    private int anchoMac = 1500;
    private int alto = 650;
    private int locat = 5;
    
    public void crearFicha() throws SQLException, ClassNotFoundException {
        if(isWin()){
            openView(new VCrearFicha());
        }else{
            openView(new VCrearFichaMac());
        }
        
    }
    
    public void cristales() throws SQLException, ClassNotFoundException {
        if(GV.tipoUserIventario()){
            GV.cursorWAIT();
            if(isWin()){
                openView(new VCristales());
            }else{
                openView(new VCristalesMac());
            }
        }else{
            accesDenied();
        }
    }
    
    public void clientes() throws SQLException, ClassNotFoundException{
            GV.cursorWAIT();
            if(isWin()){
                openView(new VClientes());
            }else{
                openView(new VClientesMac());
            }
    }
    
    public void convenios() throws SQLException, ClassNotFoundException {
        if(GV.tipoUserAdmin()){
            GV.cursorWAIT();
            if(isWin()){
                openView(new VConvenios());
            }else{
                openView(new VConveniosMac());
            }
        }else{
            accesDenied();
        }
    }
    
    public void descuentos() throws SQLException, ClassNotFoundException{
         if(GV.tipoUserAdmin()){
            GV.cursorWAIT();
            if(isWin()){
                openView(new VDescuentos());
            }else{
                openView(new VDescuentosMac());
            }
         }else{
             accesDenied();
         }   
    }
    
    public void doctores() throws SQLException, ClassNotFoundException{
        GV.cursorWAIT();
        if(isWin()){
            openView(new VDoctores());
        }else{
            openView(new VDoctoresMac());
        }
    }
    
    public void ficha() throws SQLException, ClassNotFoundException {
        GV.cursorWAIT();
        if(isWin()){
            openView(new VFicha());
        }else{
            openView(new VFichaMac());
        }
    }
    
    public void fichas(int filter) throws SQLException, ClassNotFoundException {
        GV.cursorWAIT();
        if(isWin()){
            openView(new VFichas(filter));
        }else{
            openView(new VFichasMac(filter));
        }
    }
    
    public void instituciones() throws SQLException, ClassNotFoundException{
        GV.cursorWAIT();
        if(isWin()){
            openView(new VInstituciones());
        }else{
            openView(new VInstitucionesMac());
        }
    }
    
    public void inventarios() throws SQLException, ClassNotFoundException {
        if(GV.tipoUserIventario()){
            GV.cursorWAIT();
            if(isWin()){
                openView(new VInventarios());
            }else{
                openView(new VInventariosMac());
            }
        }else{
            accesDenied();
        }
    }
    
    public void lentes() throws SQLException, ClassNotFoundException {
        if(GV.tipoUserIventario()){
            GV.cursorWAIT();
            if(isWin()){
                openView(new VLentes());
            }else{
                openView(new VLentesMac());
            }
        }else{
            accesDenied();
        }
    }
    
    public void mensajes() {
        GV.cursorWAIT();
        openView(new VMessages());
    }
    
    public void oficinas() throws SQLException, ClassNotFoundException {
        if(GV.tipoUserAdmin()){
            GV.cursorWAIT();
            if(isWin()){
                openView(new VOficinas());
            }else{
                openView(new VOficinasMac());
            }
        }else{
            accesDenied();
        }
    }
    
    public void registroBajas() {
        if(GV.tipoUserAdmin()){
            GV.cursorWAIT();
            if(isWin()){
                openView(new VRegistroBajas());
            }else{
                openView(new VRegistroBajasMac());
            }
        }else{
            accesDenied();
        }
    }
    
    public void tipoPagos() throws SQLException, ClassNotFoundException{
        if(GV.tipoUserAdmin()){
            GV.cursorWAIT();
            if(isWin()){
                openView(new VTipoPagos());
            }else{
                openView(new VTipoPagosMac());
            }
        }else{
            accesDenied();
        }
    }
    
    public void usuarios() throws SQLException, ClassNotFoundException {
        if(GV.tipoUserAdmin()){
            GV.cursorWAIT();
            if(isWin()){
                openView(new VUsuarios());
            }else{
                openView(new VUsuariosMac());
            }
        }else{
            accesDenied();
        }
    }
    
    private void openView(JPanel p1){
        if(isWin()){
            try{
                p1.setSize(ancho, alto);
                p1.setLocation(locat, locat);
                principalAdmin.removeAll();
                principalAdmin.add(p1,BorderLayout.CENTER);
                principalAdmin.revalidate();
                principalAdmin.repaint();
            }catch(Exception ex){
                OptionPane.showMsg("Error inesperado", "No se ha podido abrir la ventana solicitada, \n"
                        + "se enviará un reporte para solucionar este problema,\n"
                        + "póngase en contacto con su proveedor de software.", JOptionPane.ERROR_MESSAGE);
            }
            principalAdmin.setCursor(Cursor.getDefaultCursor());
        }else{
            try{
                p1.setSize(anchoMac, alto);
                p1.setLocation(locat, locat);
                principalAdminMac.removeAll();
                principalAdminMac.add(p1,BorderLayout.CENTER);
                principalAdminMac.revalidate();
                principalAdminMac.repaint();
            }catch(Exception ex){
                OptionPane.showMsg("Error inesperado", "No se ha podido abrir la ventana solicitada, \n"
                        + "se enviará un reporte para solucionar este problema,\n"
                        + "póngase en contacto con su proveedor de software.", JOptionPane.ERROR_MESSAGE);
            }
            principalAdminMac.setCursor(Cursor.getDefaultCursor());
        }
    }

    private void accesDenied() {
        OptionPane.showMsg("Acceso denegado", "No tienes permiso suficiente para acceder a estas opciones.", 2);
        GV.cursorDF();
    }

    public void salesReport() {
        OptionPane.showMsg("crear ventana de reporte", "reporte de ventas", 3);
    }
    
    private boolean isWin(){
        return GV.isWindowsOs();
    }
}
