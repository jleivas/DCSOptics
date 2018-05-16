/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author Lenovo G470
 */
public class HistorialPago {
    
    private int id;
    private Date fecha;
    private int abono;
    private int estado;
    private int idTipoPago;
    int idFicha;
    

    public HistorialPago() {
    }

    public HistorialPago(int id, Date fecha, int abono, int estado, int tipoPago, int idFicha) {
        setId(id);
        setFecha(fecha);
        setAbono(abono);
        setIdFicha(idFicha);
        setIdTipoPago(tipoPago);
        setEstado(estado);
    }

    public int getIdFicha() {
        return idFicha;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public void setIdTipoPago(int idTipoPago) {
        this.idTipoPago = idTipoPago;
    }

    public void setIdFicha(int idFicha) {
        this.idFicha = idFicha;
    }
    
    

    public void setId(int id) {
        this.id = id;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setAbono(int abono) {
        this.abono = abono;
    }


    public int getId() {
        return id;
    }

    public Date getFecha() {
        return fecha;
    }

    public int getAbono() {
        return abono;
    }

    public int getEstado() {
        return estado;
    }

    public int getIdTipoPago() {
        return idTipoPago;
    }  
    
}
