/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.ficha;

import fn.GV;
import java.util.Date;

/**
 *Presenta el resumen de detalle de la ficha en las tablas
 * @author sdx
 */
public class ResF{
    private String folio;
    private Date fecha;
    private int total;
    private int descuento;
    private int saldo;
    private String cliente;
    private String comuna;
    private String ciudad;
    private String vendedor;
    private int estado;

    public ResF() {
    }
    
    public ResF(String folio, Date fecha,int total,int descuento, int saldo, String cliente, String comuna, String ciudad, String vendedor, int estado) {
        setFolio(folio);
        setFecha(fecha);
        setTotal(total);
        setDescuento(descuento);
        setSaldo(saldo);
        setCliente(cliente);
        setComuna(comuna);
        setCiudad(ciudad);
        setVendedor(vendedor);
        setEstado(estado);
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public int getDescuento() {
        return descuento;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setFolio(String folio) {
        this.folio = getStr(folio);
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setCliente(String cliente) {
        this.cliente = getStr(cliente);
    }

    public void setComuna(String comuna) {
        this.comuna = getStr(comuna);
    }

    public void setCiudad(String ciudad) {
        this.ciudad = getStr(ciudad);
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = getStr(vendedor);
    }

    public String getFolio() {
        return folio;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getCliente() {
        return cliente;
    }

    public String getComuna() {
        return comuna;
    }

    public String getCiudad() {
        return ciudad;
    }

    public int getEstado() {
        return estado;
    }

    public int getTotal() {
        return total;
    }

    public String getVendedor() {
        return vendedor;
    }
    
    public String getStr(String arg){
        return GV.getStr(arg);
    }
}
