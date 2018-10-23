/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.ficha;

import entities.abstractclasses.SyncStringId;
import java.util.Date;

/**
 *
 * @author sdx
 */
public class EtiquetFicha extends SyncStringId{
    private Date fecha;
    private Date fechaEntrega;
    private String lugarEntrega;
    private String horaEntrega;
    private String observacion;
    private int valorTotal;
    private int descuento;
    private int saldo;
    
    // referencias
    private String rutCliente;
    private String rutDoctor;
    private String idInstitucion;
    private String idDespacho;
    private int idUser;
    private int idConvenio;

    public EtiquetFicha(String cod, Date fecha, Date fechaEntrega, String lugarEntrega, String horaEntrega, String observacion, int valorTotal, int descuento, int saldo, String rutCliente, String rutDoctor, String idInstitucion, String idDespacho, int idUser, int idConvenio, int estado, Date lastUpdate, int lastHour) {
        setCod(cod);
        setFecha(fecha);
        setFechaEntrega(fechaEntrega);
        setLugarEntrega(lugarEntrega);
        setHoraEntrega(horaEntrega);
        setObservacion(observacion);
        setValorTotal(valorTotal);
        setDescuento(descuento);
        setSaldo(saldo);
        setRutCliente(rutCliente);
        setRutDoctor(rutDoctor);
        setIdInstitucion(idInstitucion);
        setIdDespacho(idDespacho);
        setIdUser(idUser);
        setIdConvenio(idConvenio);
        setEstado(estado);
        setLastUpdate(lastUpdate);
        setLastHour(lastHour);
    }

    public EtiquetFicha() {
    }

    public EtiquetFicha(Ficha ficha) {
        setCod(ficha.getCod());
        setFecha(ficha.getFecha());
        setFechaEntrega(ficha.getFechaEntrega());
        setLugarEntrega(ficha.getLugarEntrega());
        setHoraEntrega(ficha.getHoraEntrega());
        setObservacion(ficha.getObservacion());
        setValorTotal(ficha.getValorTotal());
        setDescuento(ficha.getDescuento());
        setSaldo(ficha.getSaldo());
        String rutCliente = (ficha.getCliente() != null) ? ficha.getCliente().getCod():"";
        setRutCliente(rutCliente);
        String rutDoctor = (ficha.getDoctor() != null) ? ficha.getDoctor().getCod():"";
        setRutDoctor(rutDoctor);
        String idInstitucion = (ficha.getInstitucion() != null) ? ficha.getInstitucion().getCod():"";
        setIdInstitucion(idInstitucion);
        String idDespacho = (ficha.getDespacho() != null) ? ficha.getDespacho().getCod():"";
        setIdDespacho(idDespacho);
        int idUser = (ficha.getUser() != null) ? ficha.getUser().getId():0;
        setIdUser(idUser);
        int idConvenio = (ficha.getConvenio() != null) ? ficha.getConvenio().getId():0;
        setIdConvenio(idConvenio);
        setEstado(ficha.getEstado());
        setLastUpdate(ficha.getLastUpdate());
        setLastHour(ficha.getLastHour());
    }

    public Date getFecha() {
        return fecha;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public String getLugarEntrega() {
        return lugarEntrega;
    }

    public String getHoraEntrega() {
        return horaEntrega;
    }

    public String getObservacion() {
        return observacion;
    }

    public int getValorTotal() {
        return valorTotal;
    }

    public int getDescuento() {
        return descuento;
    }

    public int getSaldo() {
        return saldo;
    }

    public String getRutCliente() {
        return rutCliente;
    }

    public String getRutDoctor() {
        return rutDoctor;
    }

    public String getIdInstitucion() {
        return idInstitucion;
    }

    public String getIdDespacho() {
        return idDespacho;
    }

    public int getIdUser() {
        return idUser;
    }

    public int getIdConvenio() {
        return idConvenio;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public void setLugarEntrega(String lugarEntrega) {
        this.lugarEntrega = getStr(lugarEntrega);
    }

    public void setHoraEntrega(String horaEntrega) {
        this.horaEntrega = getStr(horaEntrega);
    }

    public void setObservacion(String observacion) {
        this.observacion = getStr(observacion);
    }

    public void setValorTotal(int valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public void setRutCliente(String rutCliente) {
        this.rutCliente = getStr(rutCliente);
    }

    public void setRutDoctor(String rutDoctor) {
        this.rutDoctor = getStr(rutDoctor);
    }

    public void setIdInstitucion(String idInstitucion) {
        this.idInstitucion = idInstitucion;
    }

    public void setIdDespacho(String idDespacho) {
        this.idDespacho = getStr(idDespacho);
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setIdConvenio(int idConvenio) {
        this.idConvenio = idConvenio;
    }
    
    
}
