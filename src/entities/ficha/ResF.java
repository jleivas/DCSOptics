/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.ficha;

/**
 *Presenta el resumen de detalle de la ficha en las tablas
 * @author sdx
 */
public class ResF{
    private String folio;
    private String fecha;
    private String cliente;
    private String comuna;
    private String ciudad;
    private int estado;
    private int total;
    private String vendedor;

    public ResF() {
    }

    public ResF(String folio, String fecha, String cliente, String comuna, String ciudad, int estado, int total, String vendedor) {
        this.folio = folio;
        this.fecha = fecha;
        this.cliente = cliente;
        this.comuna = comuna;
        this.ciudad = ciudad;
        this.estado = estado;
        this.total = total;
        this.vendedor = vendedor;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getFolio() {
        return folio;
    }

    public String getFecha() {
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
    
    
}
