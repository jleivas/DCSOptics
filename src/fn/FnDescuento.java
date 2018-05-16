/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn;

import dao.DescuentoDao;
import entities.Descuento;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author home
 */
public class FnDescuento {
    public ArrayList<Descuento> listar(int id) throws SQLException, ClassNotFoundException{
        DescuentoDao load = new DescuentoDao();
        return load.listar(id);
    }
    
    public ArrayList<Descuento> buscar(String valor,int estado) throws SQLException, ClassNotFoundException{
        DescuentoDao load = new DescuentoDao();
        return load.buscar(valor,estado);
    }
    
    public String guardar(Descuento nueva) throws SQLException, ClassNotFoundException{
        DescuentoDao load = new DescuentoDao();
        return load.agregar(nueva);
    }
    
    public Descuento cargar(int id) throws SQLException, ClassNotFoundException{
        DescuentoDao load = new DescuentoDao();
        for (Descuento temp : load.listar(id)) {
            if(temp.getId()==id)
                return temp;
        }
        return null;
    }
    
    public boolean modificar(Descuento temp) throws SQLException, ClassNotFoundException{
        DescuentoDao load = new DescuentoDao();
        return load.modificar(temp);
    }
    
    public boolean eliminar(int id) throws SQLException, ClassNotFoundException{
        DescuentoDao load = new DescuentoDao();
        return load.eliminar(id);
    }
    
    public boolean restaurar(int id) throws SQLException, ClassNotFoundException{
        DescuentoDao load = new DescuentoDao();
        return load.restaurar(id);
    }
}
