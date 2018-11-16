/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.context;

import entities.ficha.Ficha;
import fn.GV;
import static fn.GV.getStr;
import fn.globalValues.GlobalValuesFunctions;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import reportes.SalesFichaRecursoDatos;

/**
 *
 * @author jlleivas
 */
public class SalesFichaJasperReport {
    /*************************VARIABLES DE FORMATO************************/
    private DecimalFormat formateador = new DecimalFormat("###,###,###");
    private String HTML_BR1 = "<td colspan=\"2\">\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr valign=\"top\" style=\"height:4px\">\n" +
                "<td colspan=\"17\">\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr valign=\"top\" style=\"height:20px\">\n" +
                "<td colspan=\"7\">\n" +
                "</td>\n";
    private String HTML_BR2 = "<td colspan=\"2\">\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr valign=\"top\" style=\"height:9px\">\n" +
                "<td colspan=\"17\">\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr valign=\"top\" style=\"height:20px\">\n" +
                "<td colspan=\"2\">\n" +
                "</td>\n";
    private String HTML_BR3 = "<td colspan=\"2\">\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr valign=\"top\" style=\"height:4px\">\n" +
                "<td colspan=\"17\">\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr valign=\"top\" style=\"height:1px\">\n" +
                "<td colspan=\"2\">\n" +
                "</td>\n";
    private String HTML_BR4 = "<td colspan=\"8\">\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr valign=\"top\" style=\"height:1px\">\n" +
                "<td colspan=\"17\">\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr valign=\"top\" style=\"height:20px\">\n" +
                "<td colspan=\"7\">\n" +
                "</td>\n";
    private String HTML_LINE = "<td colspan=\"13\" style=\"border-top: 1px solid #043069; \">\n" +
                        "</td>\n";
    /*********************************************************************/
    private String companyName;
    private String web;
    private String dir;
    private String companyContacts;
    private String titleFilter;
    
    private int sumaTotalLentes = 0;
    private int sumaTotalVentas = 0;
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
        int totalVentas = 0;
        int cantVentas = 0;
        int totalLentes = 0;//Cantidad de lentes vendidos
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
        
        public int getCantVentas(){
            return cantVentas;
        }
        
        public int getTotalLentes(){
            return totalLentes;
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
    
    public int getSumaTotalVentas(){
        return sumaTotalVentas;
    }

    public int getMontoTotal() {
        return montoTotal;
    }
    
    public int getSumTotalLentes(){
        return sumaTotalLentes;
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
            if(ficha.getEstado()>0){
                if(!addIfExist(ficha)){
                    addVendedor(ficha);
                }
                montoPendiente = montoPendiente + ficha.getSaldo();
                montoTotal = montoTotal + (ficha.getValorTotal() - ficha.getDescuento());
            }
        }
        setMontoTotal(montoTotal);
        setMontoTotalPendiente(montoPendiente);
    }
    
    private boolean addIfExist(Ficha ficha) {
        for (Vendedor vendedor : vendedores) {
            if(vendedor.getFullName().equals(ficha.getUser().getNombre())){
                int sumLentes = obtenerTotalLentes(ficha);
                vendedor.totalLentes = vendedor.totalLentes + sumLentes;
                vendedor.cantVentas++;
                sumaTotalVentas++;
                sumaTotalLentes = sumaTotalLentes + sumLentes;
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
        int sumLentes = obtenerTotalLentes(ficha);
        sumaTotalLentes = sumaTotalLentes + sumLentes;
        vendedor.totalLentes = sumLentes;
        vendedor.cantVentas++;
        sumaTotalVentas++;
        String[][] abonos = (String[][])GlobalValuesFunctions.listarAbonos(ficha.getCod());
        if(abonos == null){
            abonos = new String[1][3];
            abonos[0][0] = "";
            
            abonos[0][1] = "Sin abonos";
            
            abonos[0][2] = "";
        }
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
    }
    
    private int obtenerTotalLentes(Ficha ficha){
        int totalLentes = 0;
        if(ficha.getLejos()!=null){
            if(!getStr(ficha.getLejos().getMarca()).isEmpty()){
                totalLentes++;
            }
        }
        if(ficha.getCerca()!=null){
            if(!getStr(ficha.getCerca().getMarca()).isEmpty()){
                totalLentes++;
            }
        }
        return totalLentes;
    }
    
    private List<DetalleVentas> updateDetails(List<DetalleVentas> detalles, String[][] abonos) {
        if(abonos == null){
            return detalles;
        }
        for (int i = 0; i < abonos.length; i++) {
            boolean exist = false;
            int indexToRemove = -1;
            //recorremos todos los detalles
            for (int j = 0; j < detalles.size(); j++) {
                if(detalles.get(j).getMonto() == 0){
                    indexToRemove = j;
                }
                if(detalles.get(j).getDetalle().equals(abonos[i][1])){
                    int montoAbono = GV.strToNumber(abonos[i][0]);
                    setMontoTotalAbonado(getMontoTotalAbonado() + montoAbono);
                    detalles.get(j).setMonto(detalles.get(j).getMonto() + montoAbono);
                    generateResumenTotal(detalles.get(j).getDetalle(),montoAbono);
                    //tipo de abono encontrado
                    exist = true;
                }
            }
            if(indexToRemove >= 0){
                detalles.remove(indexToRemove);
                FILAS--;
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
        if(!added && montoAbono > 0){
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
    
    public String generateHtml(String title){
        return 
                "<div text=\"#000000\" link=\"#000000\" alink=\"#000000\" vlink=\"#000000\">\n" +
                "<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "<tr><td width=\"50%\">&nbsp;</td><td align=\"center\">\n" +
                "\n" +
                "<a name=\"JR_PAGE_ANCHOR_0_1\"></a>\n" +
                "<table class=\"jrPage\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"empty-cells: show; width: 595px; border-collapse: collapse; background-color: white;\">\n" +
                "<tr valign=\"top\" style=\"height:0\">\n" +
                "<td style=\"width:20px\"></td>\n" +
                "<td style=\"width:1px\"></td>\n" +
                "<td style=\"width:169px\"></td>\n" +
                "<td style=\"width:10px\"></td>\n" +
                "<td style=\"width:25px\"></td>\n" +
                "<td style=\"width:5px\"></td>\n" +
                "<td style=\"width:51px\"></td>\n" +
                "<td style=\"width:3px\"></td>\n" +
                "<td style=\"width:86px\"></td>\n" +
                "<td style=\"width:30px\"></td>\n" +
                "<td style=\"width:22px\"></td>\n" +
                "<td style=\"width:48px\"></td>\n" +
                "<td style=\"width:15px\"></td>\n" +
                "<td style=\"width:84px\"></td>\n" +
                "<td style=\"width:1px\"></td>\n" +
                "<td style=\"width:6px\"></td>\n" +
                "<td style=\"width:19px\"></td>\n" +
                "</tr>\n" +
                "<tr valign=\"top\" style=\"height:20px\">\n" +
                "<td colspan=\"17\">\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr valign=\"top\" style=\"height:10px\">\n" +
                "<td>\n" +
                "</td>\n" +
                "<td colspan=\"4\" rowspan=\"2\" style=\"text-indent: 0px;  vertical-align: middle;text-align: left;\">\n" +
                "<span style=\"font-family: 'DejaVu Sans', Arial, Helvetica, sans-serif; color: #043069; font-size: 14px; line-height: 1.2578125; font-weight: bold;\">"+GV.companyName()+"</span></td>\n" +
                "<td colspan=\"12\">\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr valign=\"top\" style=\"height:15px\">\n" +
                "<td>\n" +
                "</td>\n" +
                "<td colspan=\"5\">\n" +
                "</td>\n" +
                "<td colspan=\"3\" rowspan=\"2\" style=\"padding-right: 4px; text-indent: 0px;  vertical-align: middle;text-align: right;\">\n" +
                "<span style=\"font-family: 'DejaVu Sans', Arial, Helvetica, sans-serif; color: #000000; font-size: 8px; line-height: 1.2578125;\">Fecha de emisión</span></td>\n" +
                "<td rowspan=\"2\" style=\"padding-right: 3px; text-indent: 0px;  vertical-align: middle;text-align: right;\">\n" +
                "<span style=\"font-family: 'DejaVu Sans', Arial, Helvetica, sans-serif; color: #000000; font-size: 10px; line-height: 1.2578125;\">"+GV.dateToString(new Date(), "dd.mm.yyyy")+"</span></td>\n" +
                "<td colspan=\"3\">\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr valign=\"top\" style=\"height:5px\">\n" +
                "<td>\n" +
                "</td>\n" +
                "<td colspan=\"4\" rowspan=\"2\" style=\"text-indent: 0px; text-align: left;\">\n" +
                "<span style=\"font-family: 'DejaVu Sans', Arial, Helvetica, sans-serif; color: #000000; font-size: 10px; line-height: 1.2578125;\">"+GV.getOficinaWeb()+"</span></td>\n" +
                "<td colspan=\"5\">\n" +
                "</td>\n" +
                "<td colspan=\"3\">\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr valign=\"top\" style=\"height:10px\">\n" +
                "<td>\n" +
                "</td>\n" +
                "<td colspan=\"12\">\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr valign=\"top\" style=\"height:15px\">\n" +
                "<td>\n" +
                "</td>\n" +
                "<td colspan=\"4\" style=\"text-indent: 0px; text-align: left;\">\n" +
                "<span style=\"font-family: 'DejaVu Sans', Arial, Helvetica, sans-serif; color: #000000; font-size: 10px; line-height: 1.2578125;\">"+GV.getOficinaAddress()+"</span></td>\n" +
                "<td colspan=\"12\">\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr valign=\"top\" style=\"height:15px\">\n" +
                "<td>\n" +
                "</td>\n" +
                "<td colspan=\"4\" style=\"text-indent: 0px; text-align: left;\">\n" +
                "<span style=\"font-family: 'DejaVu Sans', Arial, Helvetica, sans-serif; color: #000000; font-size: 10px; line-height: 1.2578125;\">"+GV.getOficinaMail()+"</span></td>\n" +
                "<td>\n" +
                "</td>\n" +
                "<td colspan=\"8\" style=\"text-indent: 0px;  vertical-align: middle;text-align: right;\">\n" +
                "<span style=\"font-family: 'DejaVu Sans', Arial, Helvetica, sans-serif; color: #000000; font-size: 11px; line-height: 1.2578125; font-weight: bold;\">"+title+"</span></td>\n" +
                "<td colspan=\"3\">\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr valign=\"top\" style=\"height:10px\">\n" +
                "<td colspan=\"17\">\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr valign=\"top\" style=\"height:25px\">\n" +
                "<td>\n" +
                "</td>\n" +
                "<td colspan=\"15\">\n" +
                "<div style=\"width: 100%; height: 100%; position: relative;\">\n" +
                "<div style=\"position: absolute; overflow: hidden; width: 100%; height: 100%; \">\n" +
                "<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"empty-cells: show; width: 100%; border-collapse: collapse;\">\n" +
                "<tr valign=\"top\" style=\"height:0\">\n" +
                "<td style=\"width:170px\"></td>\n" +
                "<td style=\"width:386px\"></td>\n" +
                "</tr>\n" +
                "<tr valign=\"top\" style=\"height:25px\">\n" +
                "<td style=\"pointer-events: auto; text-indent: 0px; text-align: left;\">\n" +
                "<span style=\"font-family: 'DejaVu Sans', Arial, Helvetica, sans-serif; color: #000000; font-size: 16px; line-height: 1.2578125; font-weight: bold;\">Generado por</span></td>\n" +
                "<td>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "</div>\n" +
                "<div style=\"position: relative; width: 100%; height: 100%; pointer-events: none; \">\n" +
                "<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"empty-cells: show; width: 100%; border-collapse: collapse;\">\n" +
                "<tr valign=\"top\" style=\"height:0\">\n" +
                "<td style=\"width:180px\"></td>\n" +
                "<td style=\"width:222px\"></td>\n" +
                "<td style=\"width:154px\"></td>\n" +
                "</tr>\n" +
                "<tr valign=\"top\" style=\"height:1px\">\n" +
                "<td colspan=\"3\" style=\"pointer-events: auto; background-color: #FFFFFF; border-top: 1px solid #000000; \">\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr valign=\"top\" style=\"height:24px\">\n" +
                "<td>\n" +
                "</td>\n" +
                "<td style=\"pointer-events: auto; border: 1px solid #043069; text-indent: 0px; text-align: left;\">\n" +
                "<span style=\"font-family: 'DejaVu Sans', Arial, Helvetica, sans-serif; color: #043069; font-size: 16px; line-height: 1.2578125; font-weight: bold;\">Resumen</span></td>\n" +
                "<td>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "</div>\n" +
                "</div>\n" +
                "</td>\n" +
                "<td>\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr valign=\"top\" style=\"height:15px\">\n" +
                "<td>\n" +
                "</td>\n" +
                "<td colspan=\"2\" style=\"text-indent: 0px;  vertical-align: middle;text-align: left;\">\n" +
                "<span style=\"font-family: 'DejaVu Sans', Arial, Helvetica, sans-serif; color: #000000; font-size: 10px; line-height: 1.2578125;\">"+GV.user().getNombre()+"</span></td>\n" +
                "<td>\n" +
                "</td>\n" +
                "<td colspan=\"4\" style=\"border: 1px solid #043069; padding-left: 3px; text-indent: 0px;  vertical-align: middle;text-align: left;\">\n" +
                "<span style=\"font-family: 'DejaVu Sans', Arial, Helvetica, sans-serif; color: #043069; font-size: 9px; line-height: 1.2578125; font-weight: bold;\">Registros</span></td>\n" +
                "<td colspan=\"3\" style=\"border: 1px solid #043069; padding-right: 3px; text-indent: 0px; text-align: right;\">\n" +
                "<span style=\"font-family: 'DejaVu Sans', Arial, Helvetica, sans-serif; color: #043069; font-size: 10px; line-height: 1.2578125; font-weight: bold;\">"+formateador.format(getSumaTotalVentas())+"</span></td>\n" +
                "<td colspan=\"6\">\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr valign=\"top\" style=\"height:15px\">\n" +
                "<td colspan=\"4\">\n" +
                "</td>\n" +
                "<td colspan=\"4\" style=\"border: 1px solid #043069; padding-left: 3px; text-indent: 0px;  vertical-align: middle;text-align: left;\">\n" +
                "<span style=\"font-family: 'DejaVu Sans', Arial, Helvetica, sans-serif; color: #043069; font-size: 9px; line-height: 1.2578125; font-weight: bold;\">Lentes vendidos</span></td>\n" +
                "<td colspan=\"3\" style=\"border: 1px solid #043069; padding-right: 3px; text-indent: 0px; text-align: right;\">\n" +
                "<span style=\"font-family: 'DejaVu Sans', Arial, Helvetica, sans-serif; color: #043069; font-size: 10px; line-height: 1.2578125; font-weight: bold;\">"+formateador.format(getSumTotalLentes())+"</span></td>\n" +
                "<td colspan=\"6\">\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr valign=\"top\" style=\"height:15px\">\n" +
                "<td colspan=\"4\">\n" +
                "</td>\n" +
                "<td colspan=\"4\" style=\"border: 1px solid #043069; padding-left: 3px; text-indent: 0px;  vertical-align: middle;text-align: left;\">\n" +
                "<span style=\"font-family: 'DejaVu Sans', Arial, Helvetica, sans-serif; color: #043069; font-size: 9px; line-height: 1.2578125; font-weight: bold;\">Abonado</span></td>\n" +
                "<td colspan=\"3\" style=\"border: 1px solid #000000; padding-right: 3px; text-indent: 0px; text-align: right;\">\n" +
                "<span style=\"font-family: 'DejaVu Sans', Arial, Helvetica, sans-serif; color: #000000; font-size: 10px; line-height: 1.2578125;\">"+GV.strToPrice(getMontoTotalAbonado())+"</span></td>\n" +
                "<td colspan=\"6\">\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr valign=\"top\" style=\"height:15px\">\n" +
                "<td colspan=\"4\">\n" +
                "</td>\n" +
                "<td colspan=\"4\" style=\"border: 1px solid #043069; padding-left: 3px; text-indent: 0px;  vertical-align: middle;text-align: left;\">\n" +
                "<span style=\"font-family: 'DejaVu Sans', Arial, Helvetica, sans-serif; color: #043069; font-size: 9px; line-height: 1.2578125; font-weight: bold;\">Saldos pendientes</span></td>\n" +
                "<td colspan=\"3\" style=\"border: 1px solid #000000; padding-right: 3px; text-indent: 0px; text-align: right;\">\n" +
                "<span style=\"font-family: 'DejaVu Sans', Arial, Helvetica, sans-serif; color: #000000; font-size: 10px; line-height: 1.2578125;\">"+GV.strToPrice(getMontoTotalPendiente())+"</span></td>\n" +
                "<td colspan=\"6\">\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr valign=\"top\" style=\"height:15px\">\n" +
                "<td colspan=\"4\">\n" +
                "</td>\n" +
                "<td colspan=\"4\" style=\"border: 1px solid #043069; padding-left: 3px; text-indent: 0px;  vertical-align: middle;text-align: left;\">\n" +
                "<span style=\"font-family: 'DejaVu Sans', Arial, Helvetica, sans-serif; color: #043069; font-size: 9px; line-height: 1.2578125; font-weight: bold;\">Monto total</span></td>\n" +
                "<td colspan=\"3\" style=\"border: 1px solid #000000; padding-right: 3px; text-indent: 0px;  vertical-align: middle;text-align: right;\">\n" +
                "<span style=\"font-family: 'DejaVu Sans', Arial, Helvetica, sans-serif; color: #000000; font-size: 10px; line-height: 1.2578125; font-weight: bold;\">"+GV.strToPrice(getMontoTotal())+"</span></td>\n" +
                "<td colspan=\"6\">\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr valign=\"top\" style=\"height:2px\">\n" +
                "<td colspan=\"17\">\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr valign=\"top\" style=\"height:20px\">\n" +
                "<td colspan=\"2\">\n" +
                "</td>\n" +
                "<td style=\"border: 1px solid #043069; text-indent: 0px;  vertical-align: middle;text-align: center;\">\n" +
                "<span style=\"font-family: 'DejaVu Sans', Arial, Helvetica, sans-serif; color: #043069; font-size: 10px; line-height: 1.2578125; font-weight: bold;\">Vendedor</span></td>\n" +
                "<td colspan=\"4\" style=\"border: 1px solid #043069; text-indent: 0px;  vertical-align: middle;text-align: center;\">\n" +
                "<span style=\"font-family: 'DejaVu Sans', Arial, Helvetica, sans-serif; color: #043069; font-size: 10px; line-height: 1.2578125; font-weight: bold;\">Lentes entregados</span></td>\n" +
                "<td colspan=\"2\" style=\"border: 1px solid #043069; text-indent: 0px;  vertical-align: middle;text-align: center;\">\n" +
                "<span style=\"font-family: 'DejaVu Sans', Arial, Helvetica, sans-serif; color: #043069; font-size: 10px; line-height: 1.2578125; font-weight: bold;\">Total registros</span></td>\n" +
                "<td colspan=\"3\" style=\"border: 1px solid #043069; text-indent: 0px;  vertical-align: middle;text-align: center;\">\n" +
                "<span style=\"font-family: 'DejaVu Sans', Arial, Helvetica, sans-serif; color: #043069; font-size: 10px; line-height: 1.2578125; font-weight: bold;\">Tipo abono</span></td>\n" +
                "<td colspan=\"3\" style=\"border: 1px solid #043069; text-indent: 0px;  vertical-align: middle;text-align: center;\">\n" +
                "<span style=\"font-family: 'DejaVu Sans', Arial, Helvetica, sans-serif; color: #043069; font-size: 10px; line-height: 1.2578125; font-weight: bold;\">Total abonado</span></td>\n" +
                getHTMLResumenVendedores()+
                getHTMLresumenAbonos()+
                "<tr valign=\"top\" style=\"height:30px\">\n" +
                "<td>\n" +
                "</td>\n" +
                "<td colspan=\"15\" style=\"background-color: #E6E8E9; text-indent: 0px;  vertical-align: middle;text-align: left;\">\n" +
                "</td>\n" +
                "<td>\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr valign=\"top\" style=\"height:33px\">\n" +
                "<td colspan=\"17\">\n" +
                "</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "\n" +
                "</td><td width=\"50%\">&nbsp;</td></tr>\n" +
                "</table>\n" +
                "</div>";
    }
    
    private String getHTMLResumenVendedores() {
        String html = "";
        for (Vendedor vendedor : vendedores) {
            html = html +
                HTML_BR2 +
                "<td style=\"padding-left: 3px; text-indent: 0px;  vertical-align: middle;text-align: left;\">\n" +
                "<span style=\"font-family: 'DejaVu Sans', Arial, Helvetica, sans-serif; color: #043069; font-size: 11px; line-height: 1.2578125; font-weight: bold; font-style: italic;\">"+vendedor.getFullName()+"</span></td>\n" +
                "<td colspan=\"4\" style=\"text-indent: 0px;  vertical-align: middle;text-align: right;\">\n" +
                "<span style=\"font-family: 'DejaVu Sans', Arial, Helvetica, sans-serif; color: #000000; font-size: 8px; line-height: 1.2578125; font-weight: bold;\">"+vendedor.getTotalLentes()+"</span></td>\n" +
                "<td colspan=\"2\" style=\"text-indent: 0px;  vertical-align: middle;text-align: right;\">\n" +
                "<span style=\"font-family: 'DejaVu Sans', Arial, Helvetica, sans-serif; color: #000000; font-size: 8px; line-height: 1.2578125; font-weight: bold;\">"+vendedor.getCantVentas()+" fichas</span></td>\n" +
                HTML_BR4;
            int index = 0;
            for (DetalleVentas detalle : vendedor.getDetalle()) {
                if(index == 0){
                    html = html +
                            "<td colspan=\"2\" style=\"padding-right: 3px; text-indent: 0px;  vertical-align: middle;text-align: right;\">\n" +
                                "<span style=\"font-family: 'DejaVu Sans', Arial, Helvetica, sans-serif; color: #000000; font-size: 8px; line-height: 1.2578125; font-weight: bold;\">"+GV.strToPrice(vendedor.getTotalVentas())+
                                "</span>" +
                            "</td>\n" +
                            "<td colspan=\"3\" style=\"padding-right: 3px; text-indent: 0px;  vertical-align: middle;text-align: right;\">\n" +
                                "<span style=\"font-family: 'DejaVu Sans', Arial, Helvetica, sans-serif; color: #000000; font-size: 8px; line-height: 1.2578125;\">"+detalle.getDetalle()+
                                "</span>" +
                            "</td>\n" +
                            "<td colspan=\"3\" style=\"padding-right: 3px; text-indent: 0px;  vertical-align: middle;text-align: right;\">\n" +
                                "<span style=\"font-family: 'DejaVu Sans', Arial, Helvetica, sans-serif; color: #000000; font-size: 8px; line-height: 1.2578125;\">"+GV.strToPrice(detalle.getMonto())+
                                "</span>" +
                            "</td>\n";
                }else{
                    html = html +
                            HTML_BR1 +
                            "<td colspan=\"2\" style=\"padding-right: 3px; text-indent: 0px;  vertical-align: middle;text-align: left;\">\n" +
                            "</td>\n" +
                            "<td colspan=\"3\" style=\"padding-right: 3px; text-indent: 0px;  vertical-align: middle;text-align: right;\">\n" +
                                "<span style=\"font-family: 'DejaVu Sans', Arial, Helvetica, sans-serif; color: #000000; font-size: 8px; line-height: 1.2578125;\">"+detalle.getDetalle()+
                                "</span>" +
                            "</td>\n" +
                            "<td colspan=\"3\" style=\"padding-right: 3px; text-indent: 0px;  vertical-align: middle;text-align: right;\">\n" +
                                "<span style=\"font-family: 'DejaVu Sans', Arial, Helvetica, sans-serif; color: #000000; font-size: 8px; line-height: 1.2578125;\">"+GV.strToPrice(detalle.getMonto())+
                                "</span>"+
                            "</td>\n"+
                            HTML_BR3;
                }
                index++;
            }
            if(index == 1){
                html = html + HTML_BR3 + HTML_LINE;
            }else{
                html = html + HTML_LINE;
            }
        }
        return  html;
    }
    
    private String getHTMLresumenAbonos() {
        String html = "";
        html = html + HTML_BR2 + "<td style=\"padding-left: 3px; text-indent: 0px;  vertical-align: middle;text-align: left;\">\n" +
                "<span style=\"font-family: 'DejaVu Sans', Arial, Helvetica, sans-serif; color: #043069; font-size: 11px; line-height: 1.2578125; font-weight: bold; font-style: italic;\">Detalle total abonos</span></td>\n" +
                "<td colspan=\"4\" style=\"text-indent: 0px;  vertical-align: middle;text-align: left;\">\n" +
                "</td>\n" +
                "<td colspan=\"2\" style=\"text-indent: 0px;  vertical-align: middle;text-align: left;\">\n" +
                "</td>\n" +
                HTML_BR4;
        int index = 0;
        for (String[] resumenAbono : resumenAbonos) {
            if(index == 0){
                html = html + "<td colspan=\"2\" style=\"padding-right: 3px; text-indent: 0px;  vertical-align: middle;text-align: left;\">\n" +
                "</td>\n" +
                "<td colspan=\"3\" style=\"padding-right: 3px; text-indent: 0px;  vertical-align: middle;text-align: right;\">\n" +
                "<span style=\"font-family: 'DejaVu Sans', Arial, Helvetica, sans-serif; color: #000000; font-size: 8px; line-height: 1.2578125;\">"+resumenAbono[SalesFichaRecursoDatos.indexNombreAbono]+"</span></td>\n" +
                "<td colspan=\"3\" style=\"padding-right: 3px; text-indent: 0px;  vertical-align: middle;text-align: right;\">\n" +
                "<span style=\"font-family: 'DejaVu Sans', Arial, Helvetica, sans-serif; color: #000000; font-size: 8px; line-height: 1.2578125;\">"+resumenAbono[SalesFichaRecursoDatos.indexTotalAbonado]+"</span></td>\n";
            }else{
                html = html + HTML_BR1 +
                "<td colspan=\"2\" style=\"padding-right: 3px; text-indent: 0px;  vertical-align: middle;text-align: left;\">\n" +
                "</td>\n" +
                "<td colspan=\"3\" style=\"padding-right: 3px; text-indent: 0px;  vertical-align: middle;text-align: right;\">\n" +
                "<span style=\"font-family: 'DejaVu Sans', Arial, Helvetica, sans-serif; color: #000000; font-size: 8px; line-height: 1.2578125;\">"+resumenAbono[SalesFichaRecursoDatos.indexNombreAbono]+"</span></td>\n" +
                "<td colspan=\"3\" style=\"padding-right: 3px; text-indent: 0px;  vertical-align: middle;text-align: right;\">\n" +
                "<span style=\"font-family: 'DejaVu Sans', Arial, Helvetica, sans-serif; color: #000000; font-size: 8px; line-height: 1.2578125;\">"+resumenAbono[SalesFichaRecursoDatos.indexTotalAbonado]+"</span></td>\n" +
                HTML_BR3;
            }
            index++;
        }
        return html + HTML_LINE;
    }

}
