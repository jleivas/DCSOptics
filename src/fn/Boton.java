/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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

/**
 *
 * @author home
 */
public class Boton {
    private int ancho = 1300;
    private int alto = 650;
    private int locat = 5;
    
    public void crearFicha() throws SQLException, ClassNotFoundException {
        openView(new VCrearFicha());
    }
    
    public void cristales() throws SQLException, ClassNotFoundException {
        if(GV.tipoUserIventario()){
            GV.cursorWAIT();
            openView(new VCristales());
        }else{
            accesDenied();
        }
    }
    
    public void clientes() throws SQLException, ClassNotFoundException{
            GV.cursorWAIT();
            openView(new VClientes());
    }
    
    public void convenios() throws SQLException, ClassNotFoundException {
        if(GV.tipoUserAdmin()){
            GV.cursorWAIT();
            openView(new VConvenios());
        }else{
            accesDenied();
        }
    }
    
    public void descuentos() throws SQLException, ClassNotFoundException{
         if(GV.tipoUserAdmin()){
            GV.cursorWAIT();
            openView(new VDescuentos());
         }else{
             accesDenied();
         }   
    }
    
    public void doctores() throws SQLException, ClassNotFoundException{
        GV.cursorWAIT();
        openView(new VDoctores());
    }
    
    public void ficha() throws SQLException, ClassNotFoundException {
        GV.cursorWAIT();
        openView(new VFicha());
    }
    
    public void fichas(int filter) throws SQLException, ClassNotFoundException {
        GV.cursorWAIT();
        openView(new VFichas(filter));
    }
    
    public void instituciones() throws SQLException, ClassNotFoundException{
        GV.cursorWAIT();
        openView(new VInstituciones());
    }
    
    public void inventarios() throws SQLException, ClassNotFoundException {
        if(GV.tipoUserIventario()){
            GV.cursorWAIT();
            openView(new VInventarios());
        }else{
            accesDenied();
        }
    }
    
    public void lentes() throws SQLException, ClassNotFoundException {
        if(GV.tipoUserIventario()){
            GV.cursorWAIT();
            openView(new VLentes());
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
            openView(new VOficinas());
        }else{
            accesDenied();
        }
    }
    
    public void registroBajas() {
        if(GV.tipoUserAdmin()){
            GV.cursorWAIT();
            openView(new VRegistroBajas());
        }else{
            accesDenied();
        }
    }
    
    public void tipoPagos() throws SQLException, ClassNotFoundException{
        if(GV.tipoUserAdmin()){
            GV.cursorWAIT();
            openView(new VTipoPagos());
        }else{
            accesDenied();
        }
    }
    
    public void usuarios() throws SQLException, ClassNotFoundException {
        if(GV.tipoUserAdmin()){
            GV.cursorWAIT();
            openView(new VUsuarios());
        }else{
            accesDenied();
        }
    }
    
    private void openView(JPanel p1){
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
    }

    private void accesDenied() {
        OptionPane.showMsg("Acceso denegado", "No tienes permiso suficiente para acceder a estas opciones.", 2);
        GV.cursorDF();
    }

    public void salesReport() {
        OptionPane.showMsg("crear ventana de reporte", "reporte de ventas", 3);
    }
}
