/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sync;

import entities.ficha.Armazon;
import entities.ficha.Despacho;
import entities.ficha.HistorialPago;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author sdx
 */
public interface InterfaceSyncFicha {
    public boolean add(Object object);
    public boolean update(Object object);
    public Despacho getLastDespacho(String idFicha);
    public ArrayList<HistorialPago> getPagos(String idFicha);
    public String getId(String strParam, int intParam,Object type);
    public ArrayList<Object> listar(String idParam, Object type);
    public ArrayList<Object> listar(Date param, Object type);
    public Armazon getLejos(String idFicha);
    public Armazon getCerca(String idFicha);
}
