/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.abstractclasses.SyncIntIdValidaName;
import entities.ficha.Ficha;
import fn.GV;
import fn.date.Cmp;
import fn.globalValues.GlobalValuesBD;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author sdx
 */
public class Convenio extends SyncIntIdValidaName{
    private Date fechaInicio;
    private Date fechaFin;
    private int cuotas;
    private Date fechaCobro;
    private int montoMaximo;
    private int montoPp;
    private int maximoClientes;
    private int idDescuento;
    private int porcentajeAdicion;
    private String idInstitucion;
    private List<CuotasConvenio> listCuotas = new ArrayList<CuotasConvenio>();

    public Convenio() {
    }

    public Convenio(int id,String nombre, Date fechaInicio, Date fechaFin, int cuotas,Date fechaCobro, int montoMaximo, int montoPp, int maximoClientes, int idDescuento, int porcentajeAdicion,String idInstitucion,int estado, Date lastUpdate,int lastHour) {
        setId(id);
        setNombre(nombre);
        setFechaInicio(fechaInicio);
        setFechaFin(fechaFin);
        setCuotas(cuotas);
        setFechaCobro(fechaCobro);
        setMontoMaximo(montoMaximo);
        setMontoPp(montoPp);
        setMaximoClientes(maximoClientes);
        setIdDescuento(idDescuento);
        setPorcentajeAdicion(porcentajeAdicion);
        setIdInstitucion(idInstitucion);
        setEstado(estado);
        setLastUpdate(lastUpdate);
        setLastHour(lastHour);
    }
    
    public void setNombre(String nombre){
        super.setNombre(getToName(nombre));
    }
    
    public String getNombre(){
        return getToName(super.getNombre());
    }

    public void addCuotaConvenio(CuotasConvenio cuota){
        this.listCuotas.add(cuota);
    }
    
    public List<CuotasConvenio> getCuotasConvenio(){
        return this.listCuotas;
    }
    
    public Date getFechaCobro() {
        return fechaCobro;
    }

    public void setFechaCobro(Date fechaCobro) {
        this.fechaCobro = getDate(fechaCobro);
    }

    
    
    /**
     * retorna true si se encuentra activo para seguir
     * generando mas recetas oftalmologicas
     * @return 
     */
    public boolean activo(){
        if(getFechaInicio() == null || getFechaFin() == null)
            return false;
        if(GV.fechaActualOPasada(getFechaInicio()) && 
                GV.fechaActualOFutura(getFechaFin()))
            return true;
        return false;
    }

    public void setPorcentajeAdicion(int porcentajeAdicion) {
        this.porcentajeAdicion = porcentajeAdicion;
    }

    public int getPorcentajeAdicion() {
        return porcentajeAdicion;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = getDate(fechaInicio);
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = getDate(fechaFin);
    }

    public void setCuotas(int cuotas) {
        this.cuotas = cuotas;
    }

    public void setMontoMaximo(int montoMaximo) {
        this.montoMaximo = montoMaximo;
    }

    public void setMontoPp(int montoPp) {
        this.montoPp = montoPp;
    }

    public void setMaximoClientes(int maximoClientes) {
        this.maximoClientes = maximoClientes;
    }

    public void setIdDescuento(int idDescuento) {
        this.idDescuento = idDescuento;
    }

    public void setIdInstitucion(String idInstitucion) {
        this.idInstitucion = getStr(idInstitucion);
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public int getCuotas() {
        return cuotas;
    }

    public int getMontoMaximo() {
        return montoMaximo;
    }

    public int getMontoPp() {
        return montoPp;
    }

    public int getMaximoClientes() {
        return maximoClientes;
    }

    public int getIdDescuento() {
        return idDescuento;
    }

    public String getIdInstitucion() {
        return idInstitucion;
    }
    
    private Date getDate(Date date){
        java.util.Date javaDate = null;
        if (date != null) {
            javaDate = (date instanceof  java.sql.Date)?new Date(date.getTime()):date;
        }
        return javaDate;
    }

    @Override
    public String toString() {
        return "ID:"+getId()+
                "\nnombre:"+getNombre()+
                "\nfecha inicio:"+getFechaInicio()+
                "\nfecha fin:"+getFechaFin()+
                "\nFecha cobro:"+getFechaCobro()+
                "\ncuotas:"+getCuotas()+
                "\nporcentaje adicion:"+getPorcentajeAdicion()+
                "\nestado:"+getEstado(); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Validar fecha de término, Si la fecha de término caduca el convenio debe pasar a estado 2.
     * Validar si existen fichas registradas con el convenio a generar, si no existen, 
     * se debe modificar la fecha de termino en un día más, si la fecha de termino es 
     * igual a la fecha de cobro, se debe sumar un mes a la fecha de cobro y dejar el convenio en estado 1.
     * @return true si sufre modificaciones(Guardar en BD), false si no.
     */
    public boolean validate(){
        if(getEstado() == 1){
            if(GV.fechaPasada(getFechaFin())){
                List<Object> lista = GlobalValuesBD.getFichasByConveny(getId());
                if(lista.isEmpty()){
                    //se debe modificar la fecha de término en el día actual
                    setFechaFin(new Date());
                    //si la fecha de término es igual o superior a la fecha de cobro, se debe sumar un 
                    //mes a la fecha de cobro y dejar el convenio en estado 1
                    if(Cmp.localIsNewOrEqual(getFechaFin(), getFechaCobro()) ||
                            GV.dateToString(getFechaFin(), "ddmmyyyy")
                            .equals(GV.dateToString(getFechaCobro(), "ddmmyyyy"))){
                        setFechaCobro(GV.dateSumaResta(new Date(), 1, "DAYS"));
                        setEstado(1);
                    }
                }else{
                    int totalPendiente = 0;
                    for (Object object : lista) {
                        totalPendiente = totalPendiente + ((Ficha)object).getSaldo();
                    }
                    int valorCuotas = 0;
                    int valorCuota = GV.roundPrice((totalPendiente/getCuotas()));
                    int cuota = getCuotas();
                    for (int i = 0; i < cuota; i++) {
                        valorCuotas = valorCuotas + valorCuota;
                        //ajuste de valor en la ultima cuota
                        valorCuota = ((i == (cuota-1)) && (valorCuotas < totalPendiente))?
                                (valorCuota + (totalPendiente - valorCuotas)):valorCuota;
                        
                        addCuotaConvenio(
                                new CuotasConvenio(null, GV.dateSumaResta(getFechaCobro(), i, "MONTHS"),
                                GV.cuotasFechaPagoDefault(), valorCuota, 0,getId(),
                                1, null, 0)
                        );
                    }
                    setEstado(2);
                }
                //si sufrio modificaciones
                return true;
            }
        }
        //no sufrio modificaciones
        return false;
    }
    
}
