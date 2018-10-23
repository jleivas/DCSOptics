/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.context;

import entities.ficha.Ficha;
import fn.GV;
import fn.globalValues.GlobalValuesFunctions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import reportes.SalesFichaRecursoDatos;

/**
 *
 * @author jlleivas
 */
public class SalesFichaJasperReport {
    private String companyName;
    private String web;
    private String dir;
    private String companyContacts;
    private String titleFilter;
    private String NO_DETAIL = "Sin abonos";
    
    private int montoTotal;
    private int montoTotalAbonado;
    private int montoTotalPendiente;
    
    private int FILAS;
    
    private List<Vendedor> vendedores = new ArrayList<>();
    private List<DetalleVentas> resumenTotal = new ArrayList<>();
    private String[][] resumenAbonos;

    public class DetalleVentas{
        String detalle;
        int monto;
        
        public DetalleVentas(){};
        
        public String getDetalle(){
            return detalle;
        }
        
        public void setDetalle(String detalle){
            this.detalle = detalle;
        }
        
        public int getMonto(){
            return monto;
        }
        
        public void setMonto(int monto){
            this.monto = monto;
        }
    }
    
    public class Vendedor{
        String fullName;
        int totalVentas;
        List<DetalleVentas> detalle = new ArrayList<>();
        
        public Vendedor(){};
        
        public String getFullName(){
            return fullName;
        }
        
        public void setFullName(String fullName){
            this.fullName = fullName;
        }
        
        public int getTotalVentas(){
            return totalVentas;
        }
        
        public void setTotalVentas(int totalVentas){
            this.totalVentas = totalVentas;
        }
        
        public List<DetalleVentas> getDetalle(){
            return this.detalle;
        }
        
        public void addDetalle(DetalleVentas detalle){
            this.detalle.add(detalle);
        }
    }

    public SalesFichaJasperReport(List<Object> fichas,String titleFilter, String companyName, 
                                  String web, String dir, String companyContacts) 
    {
        FILAS = 0;
        setMontoTotal(0);
        setMontoTotalAbonado(0);
        setMontoTotalPendiente(0);
        setTitleFilter(titleFilter);
        setCompanyName(companyName);
        setWeb(web);
        setDir(dir);
        setCompanyContacts(companyContacts);
        generateReportList(fichas);
        createResumenAbonos();
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getWeb() {
        return web;
    }

    public String getDir() {
        return dir;
    }

    public String getCompanyContacts() {
        return companyContacts;
    }

    public String getTitleFilter() {
        return titleFilter;
    }

    public int getMontoTotal() {
        return montoTotal;
    }

    public int getMontoTotalAbonado() {
        return montoTotalAbonado;
    }

    public int getMontoTotalPendiente() {
        return montoTotalPendiente;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public void setCompanyContacts(String companyContacts) {
        this.companyContacts = companyContacts;
    }

    public void setTitleFilter(String titleFilter) {
        this.titleFilter = titleFilter;
    }

    public void setMontoTotal(int montoTotal) {
        this.montoTotal = montoTotal;
    }

    public void setMontoTotalAbonado(int montoTotalAbonado) {
        this.montoTotalAbonado = montoTotalAbonado;
    }

    public void setMontoTotalPendiente(int montoTotalPendiente) {
        this.montoTotalPendiente = montoTotalPendiente;
    }
    
    public List<Vendedor> getVendedores(){
        return this.vendedores;
    }

    private void generateReportList(List<Object> fichas) {
        int montoTotal = 0;
        int montoPendiente = 0;
        for (Object object : fichas) {
            Ficha ficha = (Ficha)object;
            if(!addIfExist(ficha)){
                addVendedor(ficha);
            }
            montoPendiente = montoPendiente + ficha.getSaldo();
            montoTotal = montoTotal + (ficha.getValorTotal() - ficha.getDescuento());
        }
        setMontoTotal(montoTotal);
        setMontoTotalPendiente(montoPendiente);
    }
    
    private boolean addIfExist(Ficha ficha) {
        for (Vendedor vendedor : vendedores) {
            if(vendedor.getFullName().equals(ficha.getUser().getNombre())){
                int nuevoMonto = ficha.getValorTotal()-ficha.getDescuento();
                vendedor.setTotalVentas(vendedor.getTotalVentas()+nuevoMonto);
                String[][] abonos = (String[][])GlobalValuesFunctions.listarAbonos(ficha.getCod());
                vendedor.detalle = updateDetails(vendedor.getDetalle(),abonos);
                //retorna true si el vendedor existe
                return true;
            }
        }
        //retorna false si no lo encontró
        return false;
    }
    
    
    private void addVendedor(Ficha ficha) {
        Vendedor vendedor = new Vendedor();
        vendedor.setFullName(ficha.getUser().getNombre());
        vendedor.setTotalVentas(ficha.getValorTotal()-ficha.getDescuento());
        
        String[][] abonos = (String[][])GlobalValuesFunctions.listarAbonos(ficha.getCod());
        if(abonos != null){
            for (int i = 0; i < abonos.length; i++) {
                DetalleVentas detalle = new DetalleVentas();
                detalle.setDetalle(abonos[i][1]);
                int montoAbono = GV.strToNumber(abonos[i][0]);
                setMontoTotalAbonado(getMontoTotalAbonado() + montoAbono);
                detalle.setMonto(montoAbono);
                vendedor.addDetalle(detalle);
                generateResumenTotal(detalle.getDetalle(),montoAbono);
                FILAS++;
            }
            vendedores.add(vendedor);
        }
//        else
//        {
//            //Aunque no tenga abonos se debe agregar una fila por vendedor
//            DetalleVentas detalle = new DetalleVentas();
//            detalle.setDetalle(NO_DETAIL);
//            detalle.setMonto(0);
//            vendedor.addDetalle(detalle);
//            FILAS++;
//        }
//        vendedores.add(vendedor);
    }
    
    
    private List<DetalleVentas> updateDetails(List<DetalleVentas> detalles, String[][] abonos) {
        if(abonos == null){
            return detalles;
        }
        for (int i = 0; i < abonos.length; i++) {
            boolean exist = false;
            //recorremos todos los detalles
            for (int j = 0; j < detalles.size(); j++) {
                if(detalles.get(j).getDetalle().equals(abonos[i][1])){
                    int montoAbono = GV.strToNumber(abonos[i][0]);
                    setMontoTotalAbonado(getMontoTotalAbonado() + montoAbono);
                    detalles.get(j).setMonto(detalles.get(j).getMonto() + montoAbono);
                    generateResumenTotal(detalles.get(j).getDetalle(),montoAbono);
                    //tipo de abono encontrado
                    exist = true;
                }
            }
            //si no encontro el tipo de abono, se agrega a la lista
            if(!exist){
                DetalleVentas detalle = new DetalleVentas();
                detalle.setDetalle(abonos[i][1]);
                int montoAbono = GV.strToNumber(abonos[i][0]);
                setMontoTotalAbonado(getMontoTotalAbonado() + montoAbono);
                detalle.setMonto(montoAbono);
                detalles.add(detalle);
                generateResumenTotal(detalle.getDetalle(),montoAbono);
                FILAS++;
            }
        }
        //retorna la lista procesada
        return detalles;
    }

    public int getFilas(){
        return FILAS;
    }
    
    public String[][] getResumenAbonos(){
        return resumenAbonos;
    }
    
    
    private void generateResumenTotal(String name, int montoAbono) {
        boolean added = false;
        for (DetalleVentas detalle : resumenTotal) {
            if(detalle.getDetalle().equals(name)){
                detalle.setMonto(detalle.getMonto() + montoAbono);
                added = true;
            }
        }
        if(!added){
            DetalleVentas newDetail = new DetalleVentas();
            newDetail.setDetalle(name);
            newDetail.setMonto(montoAbono);
            resumenTotal.add(newDetail);
        }
    }
    
    private void createResumenAbonos(){
        //Orden de la lista alfabeticamente
        Collections.sort(resumenTotal, new Comparator<DetalleVentas>() {
            public int compare(DetalleVentas obj1, DetalleVentas obj2) {
               return obj1.getDetalle().compareTo(obj2.getDetalle());
            }
         });
        FILAS = FILAS + resumenTotal.size();
        resumenAbonos = new String[resumenTotal.size()][SalesFichaRecursoDatos.columns];
        for (int i = 0; i < resumenAbonos.length; i++) {
            resumenAbonos[i][SalesFichaRecursoDatos.indexVendedor]="Detalle total abonos";
            resumenAbonos[i][SalesFichaRecursoDatos.indexTotalVentas]="";
            for (int j = 0; j < SalesFichaRecursoDatos.columns; j++) {
                if(j == SalesFichaRecursoDatos.indexNombreAbono)
                    resumenAbonos[i][j] = "Total "+resumenTotal.get(i).getDetalle();
                if(j == SalesFichaRecursoDatos.indexTotalAbonado)
                    resumenAbonos[i][j] = GV.strToPrice(resumenTotal.get(i).getMonto());
            }
        }
    }
}
