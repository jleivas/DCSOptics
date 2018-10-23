/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.context;

import dao.Dao;
import entities.Convenio;
import entities.CuotasConvenio;
import entities.Institucion;
import fn.GV;
import static fn.GV.companyName;
import static fn.GV.dateSumaResta;
import static fn.GV.dateToString;
import static fn.GV.fechaActualOFutura;
import static fn.GV.fechaPasada;
import static fn.GV.getOficinaAddress;
import static fn.GV.getOficinaMail;
import static fn.GV.getOficinaPhone1;
import static fn.GV.getOficinaPhone2;
import static fn.GV.getOficinaWeb;
import static fn.GV.getStr;
import fn.globalValues.GlobalValuesBD;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jlleivas
 */
public class ConvenioJasperReport {
    private List<Object> fichas = new ArrayList<Object>();
    private String title;
    private String subtitle;
    private String companyName;
    private String web;
    private String dir;
    private String companyContacts;
    private Institucion cliente = new Institucion();
    private Convenio convenio = new Convenio();
    private java.util.Date nextFechaCobro;
    
    public class Receptor{
        String name;
        String dir;
        String ct1;
        String ct2;
        
        public Receptor(){
        }
        
        public String getName(){
            return name;
        }
        
        public String getDir(){
            return dir;
        }
        
        public String getCt1(){
            return ct1;
        }
        
        public String getCt2(){
            return ct2;
        }
        
        public void setName(String name){
            this.name = name;
        }
        
        public void setDir(String dir){
            this.dir = dir;
        }
        
        public void setCt1(String ct1){
            this.ct1 = ct1;
        }
        
        public void setCt2(String ct2){
            this.ct2 = ct2;
        }
    }
    
    public class Resumen{
        int montoTotal=0;
        int cuotas=0;
        int totalAbono=0;
        int totalPendiente=0;
        String diasVencimiento = "";

        public Resumen() {
        }

        
        public int getMontoTotal() {
            return montoTotal;
        }

        public int getCuotas() {
            return cuotas;
        }

        public int getTotalAbono() {
            return totalAbono;
        }

        public int getTotalPendiente() {
            return totalPendiente;
        }

        public String getDiasVencimiento() {
            return diasVencimiento;
        }

        public void setMontoTotal(int montoTotal) {
            this.montoTotal = montoTotal;
        }

        public void setCuotas(int cuotas) {
            this.cuotas = cuotas;
        }

        public void setTotalAbono(int totalAbono) {
            this.totalAbono = totalAbono;
        }

        public void setTotalPendiente(int totalPendiente) {
            this.totalPendiente = totalPendiente;
        }

        public void setDiasVencimiento(String diasVencimiento) {
            this.diasVencimiento = diasVencimiento;
        }
        
        
    }
    private Resumen resumen = new Resumen();
    private Receptor receptor = new Receptor();
    
    /**
     * PRECAUCION: para generar un ConvenioJasperReport se debe hacer sin tener
     * sesiones abuertas con la BD embebida.
     * NOTA: El resumen comprende sólo reporte de los montos pendientes a cancelar
     * no se contarán los montos cancelados en las fichas relacionadas puesto
     * que ya se encuentran pagadas
     * @param cnv
     * @param reportTitle
     * @param reportSubtitle 
     */
    public ConvenioJasperReport(Convenio cnv, String reportTitle, String reportSubtitle) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        if(cnv != null){
            Dao load = new Dao();
            setTitle(reportTitle);
            setSubtitle(reportSubtitle);
            //En este metodo se calcula el resumen
            setConvenio(cnv);
            setCliente(
                    (Institucion)load.get(getConvenio().getIdInstitucion(), 0, new Institucion())
            );
            setCompanyName(companyName());
            setWeb(getOficinaWeb());
            setDir(getOficinaAddress());
            setCompanyContacts(getOficinaMail()+", "+getOficinaPhone1()+" "+getOficinaPhone2());
            setNextFechaCobro(getConvenio().getFechaCobro());
            this.fichas = GlobalValuesBD.getFichasByConveny(getConvenio().getId());
        }
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getWeb() {
        return web;
    }

    public Resumen getResumen() {
        return resumen;
    }

    public Convenio getConvenio() {
        return convenio;
    }

    public Institucion getCliente() {
        return cliente;
    }

    public String getDir() {
        return dir;
    }

    public String getCompanyContacts() {
        return companyContacts;
    }

    public Date getNextFechaCobro() {
        return nextFechaCobro;
    }
    
    public Receptor getReceptor(){
        return this.receptor;
    }

    public void setTitle(String title) {
        this.title = getStr(title);
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = getStr(subtitle);
    }

    public void setCompanyName(String companyName) {
        this.companyName = getStr(companyName);
    }

    /**
     * El resumen comprende sólo reporte de los montos pendientes a cancelar
     * no se contarán los montos cancelados en las fichas relacionadas puesto
     * que ya se encuentran pagadas
     * @param cnv Convenio a calcular
     */
    private void setResumen(Convenio cnv) {
        this.resumen = new Resumen();
        this.resumen.setCuotas(cnv.getCuotas());
        this.resumen.setDiasVencimiento("día "+dateToString(cnv.getFechaCobro(), "dd")+" de cada mes");
        int montoTotal = 0;
        int totalAbonado = 0;
        for (CuotasConvenio cc : cnv.getCuotasConvenio()) {
            montoTotal = montoTotal + cc.getMonto();
            if(cc.getEstado() == GV.cuotaConvenioPagada()){
                totalAbonado = totalAbonado + cc.getMonto();
            }
        }
        this.resumen.setMontoTotal(montoTotal);
        this.resumen.setTotalAbono(totalAbonado);
        this.resumen.setTotalPendiente(montoTotal - totalAbonado);
        
    }

    public void setConvenio(Convenio convenio) {
        this.convenio = convenio;
        setResumen(convenio);
    }

    public void setCliente(Institucion cliente) {
        this.cliente = cliente;
    }

    public void setWeb(String web) {
        this.web = getStr(web);
    }

    public void setDir(String dir) {
        this.dir = getStr(dir);
    }

    public void setCompanyContacts(String companyContacts) {
        this.companyContacts = getStr(companyContacts);
    }
    
    public void setReceptor(String name,String dir, String ct1, String ct2){
        this.receptor = new Receptor();
        this.receptor.setName(getStr(name));
        this.receptor.setCt1(getStr(ct1));
        this.receptor.setCt2(getStr(ct2));
        this.receptor.setDir(getStr(dir));
    }

    public void setNextFechaCobro(Date nextFechaCobro) {
        if(fechaActualOFutura(nextFechaCobro)){
            this.nextFechaCobro = nextFechaCobro;
            if(this.nextFechaCobro.after(dateSumaResta(getConvenio().getFechaCobro(), getConvenio().getCuotas(), "MONTHS"))){
                this.nextFechaCobro = new java.util.Date();
            }
            return;
        }
        if(fechaPasada(nextFechaCobro)){
            setNextFechaCobro(dateSumaResta(nextFechaCobro, 1, "MONTHS"));
        }
    }
    
    public List<Object> getFichas() {
        return this.fichas; //To change body of generated methods, choose Tools | Templates.
    }
}
