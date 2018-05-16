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
public class Ficha {
    
    private int id;
    private Date fecha;
    private Date fechaEntrega;
    private String lugarEntrega;
    private String horaEntrega;
    private int valorTotal;
    private int saldo;
    private String observacion;
    private int estado;
    // referencias
    private Cliente cliente;
    private Doctor doctor;
    private Descuento descuento;
    private Institucion institucion;
    private Despacho despacho;
    private Armazon lejos;
    private Armazon cerca;
    private User user;

    public Ficha() {
    }

    public Ficha(int id, Date fecha, Date fechaEntrega, String lugarEntrega, String horaEntrega, int valorTotal, int saldo, String observacion, int estado, Cliente cliente, Doctor doctor, Descuento descuento, Institucion institucion, Despacho despacho , Armazon lejos, Armazon cerca, User user) {
        setId(id);
        setFecha(fecha);
        setFechaEntrega(fechaEntrega);
        setLugarEntrega(lugarEntrega);
        setHoraEntrega(horaEntrega);
        setValorTotal(valorTotal);
        setSaldo(saldo);
        setObservacion(observacion);
        setEstado(estado);
        setCliente(cliente);
        setDoctor(doctor);
        setDescuento(descuento);
        setInstitucion(institucion);
        setDespacho(despacho);
        setLejos(lejos);
        setCerca(cerca);
        setUser(user);
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public void setLugarEntrega(String lugarEntrega) {
        this.lugarEntrega = lugarEntrega;
    }

    public void setHoraEntrega(String horaEntrega) {
        this.horaEntrega = horaEntrega;
    }

    public void setValorTotal(int valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setDescuento(Descuento descuento) {
        this.descuento = descuento;
    }

    public void setInstitucion(Institucion institucion) {
        this.institucion = institucion;
    }

    public void setDespacho(Despacho despacho) {
        this.despacho = despacho;
    }

    public void setLejos(Armazon lejos) {
        this.lejos = lejos;
    }

    public void setCerca(Armazon cerca) {
        this.cerca = cerca;
    }
    
    

    public int getId() {
        return id;
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

    public int getValorTotal() {
        return valorTotal;
    }

    public int getSaldo() {
        return saldo;
    }

    public String getObservacion() {
        return observacion;
    }

    public int getEstado() {
        return estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Descuento getDescuento() {
        return descuento;
    }

    public Institucion getInstitucion() {
        return institucion;
    }

    public Despacho getDespacho() {
        return despacho;
    }

    public Armazon getLejos() {
        return lejos;
    }

    public Armazon getCerca() {
        return cerca;
    }
    
    

    @Override
    public String toString() {
        return "Ficha{" + "id=" + id+"\n"
                + ", fecha=" + fecha+"\n" 
                + ", fechaEntrega=" + fechaEntrega+"\n" 
                + ", lugarEntrega=" + lugarEntrega +"\n"
                + ", horaEntrega=" + horaEntrega +"\n"
                + ", valorTotal=" + valorTotal +"\n"
                + ", saldo=" + saldo+"\n" 
                + ", observacion=" + observacion +"\n"
                + ", estado=" + estado+"\n" 
                + ", cliente=" + cliente.toString()+"\n" 
                + ", doctor=" + doctor.toString()+"\n" 
                + ", descuento=" + descuento.toString()+"%"+"\n" 
                + ", institucion=" + institucion.toString()+"\n" 
                + ", despacho=" + despacho.toString()+"\n"
                + ", armazon lejos=" + lejos.toString()+"\n"
                + ", armazon cerca=" + cerca.toString()+"\n"
                +'}';
    }
    
    

   
    
    
    
       
    
    
    
}
