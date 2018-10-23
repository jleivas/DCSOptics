/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.abstractclasses.SyncFichaClass;
import fn.GV;
import java.util.Date;

/**
 *
 * @author jlleivas
 */
public class CuotasConvenio extends SyncFichaClass{
    private Date fecha;
    private Date fechaPagado;
    private int monto;
    private int idTipoPago;
    private int idConvenio;

    public CuotasConvenio() {
    }

    public CuotasConvenio(String cod,Date fecha, Date fechaPagado, int monto,int idTipoPago, int idConvenio, int estado, Date lastUpdate,int lastHour) {
        setCod(cod);
        setFecha(fecha);
        setFechaPagado(fechaPagado);
        setMonto(monto);
        setIdTipoPago(idTipoPago);
        setIdConvenio(idConvenio);
        setEstado(estado);
        setLastUpdate(lastUpdate);
        setLastHour(lastHour);
    }

    public void setIdTipoPago(int idTipoPago) {
        this.idTipoPago = idTipoPago;
    }

    public void setFecha(Date fecha) {
        this.fecha = getDate(fecha);
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public int getMonto() {
        return monto;
    }

    public void setIdConvenio(int idConvenio) {
        this.idConvenio = idConvenio;
    }

    public int getIdTipoPago() {
        return idTipoPago;
    }

    public int getIdConvenio() {
        return idConvenio;
    }

    public Date getFechaPagado() {
        return fechaPagado;
    }

    public void setFechaPagado(Date fechaPagado) {
        this.fechaPagado = getDate(fechaPagado);
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
        return "ID:"+getCod()+"\n"+
        "fecha:"+getFecha()+"\n"+
        "fecha pagado:"+getFechaPagado()+"\n"+
        "monto:"+getMonto()+"\n"+
        "idConvenio:"+getIdConvenio()+"\n"+
        "estado:"+getEstado(); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String imprimir(){
        return  "_________________\n"+
                "Fecha vencimiento: "+GV.dateToString(getFecha(), "dd.mm.yyyy")+"\n"+
                "Monto            : "+GV.strToPrice(getMonto())+"\n"+
                "Estado           : "+((getEstado() == GV.cuotaConvenioPagada())?"Pagada":"Pendidente")+"\n"+
                "Fecha de pago    : "+((getEstado() == GV.cuotaConvenioPagada())?GV.dateToString(getFechaPagado(), "dd.mm.yyyy"):
                                        "Sin registro")+"\n"+
                "_________________";
    }
}
