/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.abstractclasses.SyncStringId;
import fn.GlobalValues;
import java.util.Date;

/**
 *
 * @author home
 */
public class Lente extends SyncStringId{
    //debe incluir el id del inventario en el idString solo por bd remota, no se debe mostrar despues de -
    private String color;
    private String tipo;
    private String marca;
    private String material;
    private int flex;
    private int clasificacion;
    private String descripcion;
    private int precioRef;
    private int precioAct;
    private int stock;
    private int stockMin;
    private String inventario;

    public Lente() {
    }

    /**
     * 
     * @param cod Debe incluir el id del inventario al cual está asignado, solo para bd
     * @param color
     * @param tipo
     * @param marca
     * @param material
     * @param flex
     * @param clasificacion
     * @param descripcion
     * @param precioRef
     * @param precioAct
     * @param stock
     * @param stockMin
     * @param inventario
     * @param estado
     * @param lastUpdate 
     */
    public Lente(String cod, String color,String tipo, String marca, String material, int flex, int clasificacion, String descripcion, int precioRef, int precioAct, int stock, int stockMin,String inventario, int estado, Date lastUpdate, int lastHour) {
        this.color = color;
        this.tipo = tipo;
        this.marca = marca;
        this.material = material;
        this.flex = flex;
        this.clasificacion = clasificacion;
        this.descripcion = descripcion;
        this.precioRef = precioRef;
        this.precioAct = precioAct;
        this.stock = stock;
        this.stockMin = stockMin;
        setInventario(inventario);
        setCod(cod);
        setEstado(estado);
        setLastUpdate(lastUpdate);
        setLastHour(lastHour);
    }

    @Override
    public void setCod(String cod) {
        if(GlobalValues.contChar('-', cod) == 2){
            super.setCod(cod);
        }else{
            if(cod == null || cod.equals(""))
                cod = "00";
            if(marca == null || marca.equals(""))
                marca = "00";
            if(color == null || color.equals(""))
                color = "00";
            super.setCod(cod.trim().replaceAll("-", "") + "-" + marca.trim().replaceAll("-", "") + "-" + color.trim().replaceAll("-", ""));
        }
    }
    public void setInventario(String inventario) {
        this.inventario = inventario;
    }

    public String getInventario() {
        return inventario;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public void setFlex(int flex) {
        this.flex = flex;
    }

    public void setClasificacion(int clasificacion) {
        this.clasificacion = clasificacion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecioRef(int precioRef) {
        this.precioRef = precioRef;
    }

    public void setPrecioAct(int precioAct) {
        this.precioAct = precioAct;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setStockMin(int stockMin) {
        this.stockMin = stockMin;
    }

    public String getColor() {
        return color;
    }

    public String getMarca() {
        return marca;
    }

    public String getMaterial() {
        return material;
    }

    public int getFlex() {
        return flex;
    }

    public int getClasificacion() {
        return clasificacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getPrecioRef() {
        return precioRef;
    }

    public int getPrecioAct() {
        return precioAct;
    }

    public int getStock() {
        return stock;
    }

    public int getStockMin() {
        return stockMin;
    }

    @Override
    public String toString() {
        return "\n -cod: "+getCod()+
                " - color: "+this.color+
                " - tipo: "+this.tipo+
                " - marca:"+this.marca+
                " - lastUpdate:"+getLastUpdate()+
                " - Estado:"+getEstado();
    }
    
    
}
