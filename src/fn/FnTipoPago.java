/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn;

import dao.TipoPagoDao;
import entities.TipoPago;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author home
 */
public class FnTipoPago {
    public ArrayList<TipoPago> listar(int id) throws SQLException, ClassNotFoundException{
        TipoPagoDao load = new TipoPagoDao();
        return load.listar(id);
    }
    
    public ArrayList<TipoPago> buscar(String valor,int estado) throws SQLException, ClassNotFoundException{
        TipoPagoDao load = new TipoPagoDao();
        return load.buscar(valor,estado);
    }
    
    public String guardar(TipoPago nueva) throws SQLException, ClassNotFoundException{
        TipoPagoDao load = new TipoPagoDao();
        return load.agregar(nueva);
    }
    
    public TipoPago cargar(int id) throws SQLException, ClassNotFoundException{
        TipoPagoDao load = new TipoPagoDao();
        for (TipoPago temp : load.listar(id)) {
            if(temp.getId()==id)
                return temp;
        }
        return null;
    }
    
    public boolean modificar(TipoPago temp) throws SQLException, ClassNotFoundException{
        TipoPagoDao load = new TipoPagoDao();
        return load.modificar(temp);
    }
    
    public boolean eliminar(int id) throws SQLException, ClassNotFoundException{
        TipoPagoDao load = new TipoPagoDao();
        return load.eliminar(id);
    }
    
    public boolean restaurar(int id) throws SQLException, ClassNotFoundException{
        TipoPagoDao load = new TipoPagoDao();
        return load.restaurar(id);
    }
}
