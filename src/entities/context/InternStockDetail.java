/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.context;

import fn.GV;

/**
 *
 * @author jlleivas
 */
public class InternStockDetail {
    private String cod;
    private int cantidad;

    public InternStockDetail() {
    }

    public InternStockDetail(String cod, int cantidad) {
        setCod(cod);
        setCantidad(cantidad);
    }

    public void setCod(String cod) {
        this.cod = GV.getStr(cod);
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getCod() {
        return cod;
    }

    public int getCantidad() {
        return cantidad;
    }
    
}
