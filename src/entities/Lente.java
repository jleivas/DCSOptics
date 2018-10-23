/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.abstractclasses.SyncStringId;
import fn.GV;
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
    private int inventario;

    public Lente() {
    }

    /**
     * 
     * @param cod 
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
    public Lente(String cod, String color,String tipo, String marca, String material, int flex, int clasificacion, String descripcion, int precioRef, int precioAct, int stock, int stockMin,int inventario, int estado, Date lastUpdate, int lastHour) {
        setColor(color);
        setTipo(tipo);
        setMarca(marca);
        setMaterial(material);
        setFlex(flex);
        setClasificacion(clasificacion);
        setDescripcion(descripcion);
        setPrecioRef(precioRef);
        setPrecioAct(precioAct);
        setStock(stock);
        setStockMin(stockMin);
        setInventario(inventario);
        setCod(cod);
        setEstado(estado);
        setLastUpdate(lastUpdate);
        setLastHour(lastHour);
    }

    @Override
    public void setCod(String cod) {
        if(GV.contChar('-', cod) == 2){
            super.setCod(cod);
        }else{
            if(cod == null || cod.equals(""))
                cod = "00";
            if(marca == null || marca.equals(""))
                marca = "00";
            if(color == null || color.equals(""))
                color = "00";
            super.setCod(cod.trim().replaceAll("-", "") + "-" + marca.trim().replaceAll("-", "") + "-" + color.trim().replaceAll("-", "") + "[" + inventario + "]");
        }
    }
    public void setInventario(int inventario) {
        this.inventario = inventario;
    }

    public int getInventario() {
        return inventario;
    }

    public void setTipo(String tipo) {
        this.tipo = getStr(tipo);
    }

    public String getTipo() {
        return getStr(tipo);
    }

    public void setColor(String color) {
        this.color = getStr(color);
    }

    public void setMarca(String marca) {
        this.marca = getStr(marca);
    }

    public void setMaterial(String material) {
        this.material = getStr(material);
    }

    public void setFlex(int flex) {
        this.flex = flex;
    }

    public void setClasificacion(int clasificacion) {
        this.clasificacion = clasificacion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = getStr(descripcion);
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
        return getStr(color);
    }

    public String getMarca() {
        return getStr(marca);
    }

    public String getMaterial() {
        return getStr(material);
    }

    public int getFlex() {
        return flex;
    }

    public int getClasificacion() {
        return clasificacion;
    }

    public String getDescripcion() {
        return getStr(descripcion);
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
                " - Stock:"+getStock()+
                " - Stock minimo:"+getStockMin()+
                " - Estado:"+getEstado()+
                " - lastUpdate:"+getLastUpdate()+
                " - LastHour:"+getLastHour();
    }
}
