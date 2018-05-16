/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn;

import entities.Ficha;
import entities.Info;
import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import static view.ContentAdmin.principalAdmin;
import view.Progreso;
import view.VAbrirFicha1;
import view.VClientes;
import view.VCrearFicha;
import view.VCristales;
import view.VDatosEmpresa;
import view.VDescuentos;
import view.VDoctores;
import view.VHistorialPago;
import view.VInstituciones;
import view.VLentes;
import view.VMisFichas;
import view.VMostrarFichas;
import view.VRegistroBajas;
import view.VReporteFichas;
import view.VTipoPago;
import view.VUsuarios;

/**
 *
 * @author home
 */
public class Boton {
    private int ancho = 1300;
    private int alto = 650;
    private int locat = 5;
    
    public void verHistorialPago(int idFicha) throws SQLException, ClassNotFoundException{
        VHistorialPago p1 = new VHistorialPago();
        p1.setIdFolio(idFicha);
        p1.setSize(ancho, alto);
        p1.setLocation(locat, locat);
        principalAdmin.removeAll();
        principalAdmin.add(p1,BorderLayout.CENTER);
        principalAdmin.revalidate();
        principalAdmin.repaint();
    }
    
    public void nuevaFicha() throws SQLException, ClassNotFoundException{
        crearFicha();
    }
    
    public void crearFicha() throws SQLException, ClassNotFoundException {
        VCrearFicha p1 = new VCrearFicha();
        p1.setSize(ancho, alto);
        p1.setLocation(locat, locat);
        principalAdmin.removeAll();
        principalAdmin.add(p1,BorderLayout.CENTER);
        principalAdmin.revalidate();
        principalAdmin.repaint();
    }
    
    public void abrirFicha(String idFolio) throws SQLException, ClassNotFoundException{
        VAbrirFicha1 p1 = new VAbrirFicha1();
        
        p1.setIdFolio(idFolio);
        p1.setSize(ancho, alto);
        p1.setLocation(locat, locat);
        
        
        principalAdmin.removeAll();
        
        principalAdmin.add(p1,BorderLayout.CENTER);
        principalAdmin.revalidate();
        principalAdmin.repaint();
    }
    
    
    public void descuentos() throws SQLException, ClassNotFoundException{
        VDescuentos p1 = new VDescuentos();
        p1.setSize(ancho, alto);
        p1.setLocation(locat, locat);
        principalAdmin.removeAll();
        principalAdmin.add(p1,BorderLayout.CENTER);
        principalAdmin.revalidate();
        principalAdmin.repaint();
    }
    
    public void tipoPago() throws SQLException, ClassNotFoundException{
        VTipoPago p1 = new VTipoPago();
        p1.setSize(ancho, alto);
        p1.setLocation(locat, locat);
        principalAdmin.removeAll();
        principalAdmin.add(p1,BorderLayout.CENTER);
        principalAdmin.revalidate();
        principalAdmin.repaint();
    }
    
    public void datosEmpresa() throws SQLException, ClassNotFoundException{
        VDatosEmpresa p1 = new VDatosEmpresa();
        p1.setSize(ancho, alto);
        p1.setLocation(locat, locat);
        principalAdmin.removeAll();
        principalAdmin.add(p1,BorderLayout.CENTER);
        principalAdmin.revalidate();
        principalAdmin.repaint();
    }
    
    public void clientes() throws SQLException, ClassNotFoundException{
        VClientes p1 = new VClientes();
        p1.setSize(ancho, alto);
        p1.setLocation(locat, locat);
        principalAdmin.removeAll();
        principalAdmin.add(p1,BorderLayout.CENTER);
        principalAdmin.revalidate();
        principalAdmin.repaint();
    }
    
    public void doctores() throws SQLException, ClassNotFoundException{
        VDoctores p1 = new VDoctores();
        p1.setSize(ancho, alto);
        p1.setLocation(locat, locat);
        principalAdmin.removeAll();
        principalAdmin.add(p1,BorderLayout.CENTER);
        principalAdmin.revalidate();
        principalAdmin.repaint();
    }
    
    public void instituciones() throws SQLException, ClassNotFoundException{
        VInstituciones p1 = new VInstituciones();
        p1.setSize(ancho, alto);
        p1.setLocation(locat, locat);
        principalAdmin.removeAll();
        principalAdmin.add(p1,BorderLayout.CENTER);
        principalAdmin.revalidate();
        principalAdmin.repaint();
    }
    
    public void fichas() throws SQLException, ClassNotFoundException{
        VMostrarFichas p1 = new VMostrarFichas();
        p1.setSize(ancho, alto);
        p1.setLocation(locat, locat);
        principalAdmin.removeAll();
        principalAdmin.add(p1,BorderLayout.CENTER);
        principalAdmin.revalidate();
        principalAdmin.repaint();
    }
    
    public void misFichas() throws SQLException, ClassNotFoundException {
        VMisFichas p1 = new VMisFichas();
        p1.setSize(ancho, alto);
        p1.setLocation(locat, locat);
        principalAdmin.removeAll();
        principalAdmin.add(p1,BorderLayout.CENTER);
        principalAdmin.revalidate();
        principalAdmin.repaint();
    }

    public void barraProgreso(ArrayList<Ficha> lista, Info empresa, int funcion) throws InterruptedException {
        Progreso p1 = new Progreso();
        p1.setLista(lista, empresa,funcion);
        p1.setSize(449, 250);
        p1.setLocation(locat, locat);
        p1.setVisible(true);
    }

    public void cristales() throws SQLException, ClassNotFoundException {
        VCristales p1 = new VCristales();
        p1.setSize(ancho, alto);
        p1.setLocation(locat, locat);
        principalAdmin.removeAll();
        principalAdmin.add(p1,BorderLayout.CENTER);
        principalAdmin.revalidate();
        principalAdmin.repaint();
    }

    public void lentes() throws SQLException, ClassNotFoundException {
        VLentes p1 = new VLentes();
        p1.setSize(ancho, alto);
        p1.setLocation(locat, locat);
        principalAdmin.removeAll();
        principalAdmin.add(p1,BorderLayout.CENTER);
        principalAdmin.revalidate();
        principalAdmin.repaint();
    }

    public void registroBajas() throws SQLException, ClassNotFoundException {
        VRegistroBajas p1 = new VRegistroBajas();
        p1.setSize(ancho, alto);
        p1.setLocation(locat, locat);
        principalAdmin.removeAll();
        principalAdmin.add(p1,BorderLayout.CENTER);
        principalAdmin.revalidate();
        principalAdmin.repaint();
    }

    public void usuarios() throws SQLException, ClassNotFoundException {
        VUsuarios p1 = new VUsuarios();
        p1.setSize(ancho, alto);
        p1.setLocation(locat, locat);
        principalAdmin.removeAll();
        principalAdmin.add(p1,BorderLayout.CENTER);
        principalAdmin.revalidate();
        principalAdmin.repaint();
    }

    public void reporteVentas() throws SQLException, ClassNotFoundException {
        VReporteFichas p1 = new VReporteFichas();
        p1.setSize(ancho, alto);
        p1.setLocation(locat, locat);
        principalAdmin.removeAll();
        principalAdmin.add(p1,BorderLayout.CENTER);
        principalAdmin.revalidate();
        principalAdmin.repaint();
    }

    

    

    
    
}
