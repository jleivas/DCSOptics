/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn;

import dao.ArmazonDao;
import dao.ClienteDao;
import dao.CristalDao;
import dao.DescuentoDao;
import dao.DoctorDao;
import dao.FichaDao;
import dao.HistorialPagoDao;
import dao.InfoDao;
import dao.InstitucionDao;
import dao.LenteDao;
import dao.Programa;
import dao.TipoPagoDao;
import entities.Armazon;
import entities.Cliente;
import entities.Cristal;
import entities.Descuento;
import entities.Doctor;
import entities.Ficha;
import entities.HistorialPago;
import entities.Institucion;
import entities.Lente;
import entities.TipoPago;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author home
 */
public class FnNuevaFicha {
    public ArrayList<Institucion> listarInstituciones() throws SQLException, ClassNotFoundException{
        InstitucionDao load = new InstitucionDao();
        return load.listar(0);
    }
    
    public ArrayList<Cliente> listarClientes() throws SQLException, ClassNotFoundException{
        ClienteDao load = new ClienteDao();
        return load.listar("");
    }

    public Cliente cargarCliente(String rutCliente) throws SQLException, ClassNotFoundException {
        ClienteDao load = new ClienteDao();
        for (Cliente temp : load.listar(rutCliente)) {
            if(temp.getRut().equals(rutCliente))
                return temp;
        }
        return null;
    }

    public Institucion cargarInstitucion(String nombre) throws SQLException, ClassNotFoundException {
        InstitucionDao load = new InstitucionDao();
        for (Institucion temp : load.listarNombre(nombre)) {
            if(temp.getNombre().equals(nombre))
                return temp;
        }
        return null;
    }

    public ArrayList<Descuento> listaDescuento() throws SQLException, ClassNotFoundException {
        DescuentoDao load = new DescuentoDao();
        return load.listar(0);
    }

    public ArrayList<TipoPago> listaTipoPago() throws SQLException, ClassNotFoundException {
        TipoPagoDao load = new TipoPagoDao();
        return load.listar(0);
    }

    public Doctor cargarDoctor(String nombre) throws SQLException, ClassNotFoundException {
        DoctorDao load = new DoctorDao();
        for (Doctor temp : load.listarNombre(nombre)) {
            if(temp.getNombre().equals(nombre))
                return temp;
        }
        return null;
    }

    public ArrayList<Doctor> listarDoctores() throws SQLException, ClassNotFoundException {
        DoctorDao load = new DoctorDao();
        return load.listar("");
    }

    public Descuento buscarDescuento(String seleccionado) throws SQLException, ClassNotFoundException {
        DescuentoDao load = new DescuentoDao();
        for (Descuento temp : load.buscar(seleccionado, 1)) {
            if(temp.getNombre().equals(seleccionado))
                return temp;
        }
        return null;
    }

    public TipoPago buscarTipoPago(String nombre) throws SQLException, ClassNotFoundException {
        TipoPagoDao load = new TipoPagoDao();
        for (TipoPago temp : load.buscar(nombre, 1)) {
            if(temp.getNombre().equals(nombre))
                return temp;
        }
        return null;
    }

    public int obtenerFolio() throws SQLException, ClassNotFoundException {
        FnInfo load = new FnInfo();
        return load.obtenerFolio();
    }

    public boolean actualizarFolio(int numFolio) throws SQLException, ClassNotFoundException {
        InfoDao load = new InfoDao();
        numFolio++;
        return load.actualizarFolioBoolean(numFolio);
    }

    public Descuento cargarDescuento(int idDescuento) throws SQLException, ClassNotFoundException {
        DescuentoDao load = new DescuentoDao();
        for (Descuento temp : load.listar(idDescuento)) {
            if(temp.getId() == idDescuento)
                return temp;
        }
        return null;
    }

    public Doctor cargarDoctorRut(String rutDoctor) throws SQLException, ClassNotFoundException {
        DoctorDao load = new DoctorDao();
        for (Doctor temp : load.listar(rutDoctor)) {
            if(temp.getRut().equals(rutDoctor))
                return temp;
        }
        return null;
    }

    public Institucion cargarInstitucionId(int idInstitucion) throws SQLException, ClassNotFoundException {
        InstitucionDao load = new InstitucionDao();
        for (Institucion temp : load.listar(idInstitucion)) {
            if(temp.getId() == idInstitucion)
                return temp;
        }
        return null;
    }

    public boolean guardarFicha(Ficha ficha,int idSesion) throws SQLException, ClassNotFoundException {
        FichaDao load = new FichaDao();
        return load.agregarBoolean(ficha,idSesion);
    }

    public boolean guardarArmazon(Armazon nuevo) throws SQLException, ClassNotFoundException {
        ArmazonDao load = new ArmazonDao();
        return load.agregar(nuevo);
    }

    public boolean guardarHitorialPago(HistorialPago hp) throws SQLException, ClassNotFoundException {
        HistorialPagoDao load = new HistorialPagoDao();
        return load.agregarBoolean(hp);
    }

    public boolean actualizarCliente(Cliente cliente) throws SQLException, ClassNotFoundException {
        ClienteDao load = new ClienteDao();
        return load.modificar(cliente);
    }

    public boolean guardarCliente(Cliente cliente) throws SQLException, ClassNotFoundException {
        ClienteDao load = new ClienteDao();
        return load.agregarBoolean(cliente);
    }

    public void imprimir(Ficha ficha) throws SQLException, ClassNotFoundException {
        int descuento =0;
        if(ficha.getDescuento().getPorcetange() > 0){
            descuento = (ficha.getValorTotal()*ficha.getDescuento().getPorcetange())/100;
            ficha.setValorTotal(ficha.getValorTotal()-descuento);
        }
        Programa load=new Programa(ficha);
    }

    public ArrayList<Cristal> listarCristales() throws SQLException, ClassNotFoundException {
        CristalDao load = new CristalDao();
        return load.listar(0);
    }

    public Cristal cargarCristal(String nombre) throws SQLException, ClassNotFoundException {
        CristalDao load = new CristalDao();
        for (Cristal temp : load.listarPorNombre(nombre)) {
            if(temp.getNombre().equals(nombre))
                return temp;
        }
        return null;
    }

    public ArrayList<Lente> listarLentes() throws SQLException, ClassNotFoundException {
        LenteDao load = new LenteDao();
        return load.listar("activos");
    }
    
    public ArrayList<Lente> listarLentesConStock() throws SQLException, ClassNotFoundException {
        LenteDao load = new LenteDao();
        return load.listarConStock("activos");
    }

    public Lente cargarLente(String codigo) throws SQLException, ClassNotFoundException {
        LenteDao load = new LenteDao();
        if(codigo.length()<1){
            return null;
        }
        String[] id = codigo.split("-");
        for (Lente temp : load.listar(id[0])) {
            System.out.println(temp.getCodigo());
            if(temp.getId().equals(id[0]) && temp.getMarca().equals(id[1]) && temp.getColor().equals(id[2]))
                return temp;
        }
        return null;
    }

    public boolean descontarStock(String codigo) throws SQLException, ClassNotFoundException {
        
        LenteDao load = new LenteDao();
        return load.descontarDesdeFicha(codigo,1);
    }

}
