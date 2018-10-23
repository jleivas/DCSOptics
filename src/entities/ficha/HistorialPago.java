/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.ficha;

import entities.abstractclasses.SyncFichaClass;
import java.util.Date;

/**
 *
 * @author Lenovo G470
 */
public class HistorialPago extends SyncFichaClass{
    
    private Date fecha;
    private int abono;
    private int idTipoPago;
    private String idFicha;
    

    public HistorialPago() {
    }

    public HistorialPago(String cod, Date fecha, int abono, 
            int tipoPago, String idFicha,int estado, Date lastUpdate, int lastHour) {
        setCod(cod);
        setFecha(fecha);
        setAbono(abono);
        setIdFicha(idFicha);
        setIdTipoPago(tipoPago);
        setEstado(estado);
        setLastUpdate(lastUpdate);
        setLastHour(lastHour);
    }

    

    public void setIdTipoPago(int idTipoPago) {
        this.idTipoPago = idTipoPago;
    }

    public void setIdFicha(String idFicha) {
        this.idFicha = getStr(idFicha);
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setAbono(int abono) {
        this.abono = abono;
    }

    public Date getFecha() {
        return fecha;
    }

    public int getAbono() {
        return abono;
    }

    public int getIdTipoPago() {
        return idTipoPago;
    }  
    
    public String getIdFicha() {
        return idFicha;
    }
    
    @Override
    public String toString() {
        return "Despacho{" + "cod=" + getCod()
                + ", fecha=" + fecha 
                + ", abono=" + abono 
                + ", idTipoPago=" + idTipoPago 
                + ", idFicha=" + idFicha 
                + ", estado=" + getEstado() 
                + ", lastUpdate=" + getLastUpdate() + '}';
    }
}
