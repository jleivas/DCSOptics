/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.abstractclasses.SyncIntId;

/**
 *
 * @author jlleivas
 */
public class InternStock extends SyncIntId{
    private String idLente;
    private int stock;

    public InternStock() {
    }

    public InternStock(int id, String idLente, int stock, int estado) {
        setId(id);
        setIdLente(idLente);
        setStock(stock);
        setEstado(estado);
    }

    public void setIdLente(String idLente) {
        this.idLente = idLente;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getIdLente() {
        return idLente;
    }

    public int getStock() {
        return stock;
    }
    
    
}
