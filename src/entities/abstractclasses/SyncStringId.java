/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.abstractclasses;


/**
 *
 * @author sdx
 */
public abstract class SyncStringId extends SyncClass{
    private String cod;

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getCod() {
        return cod;
    }
}
