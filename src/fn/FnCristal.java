/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn;

import dao.CristalDao;
import entities.Cristal;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author home
 */
public class FnCristal {
    CristalDao load = new CristalDao();
    
    public boolean guardar(Cristal cristal) throws SQLException, ClassNotFoundException{
        return load.guardar(cristal);
    }

    public Cristal cargar(int id) throws SQLException, ClassNotFoundException {
        return load.cargar(id);
    }

    public boolean eliminar(int id) throws SQLException {
        return load.eliminar(id);
    }

    public ArrayList<Cristal> listar(int id) throws SQLException, ClassNotFoundException {
        return load.listar(id);
    }

    public boolean restaurar(int id) throws SQLException {
        return load.restaurar(id);
    }

    public boolean modificar(Cristal cristal) throws SQLException {
        return load.modificar(cristal);
    }

    public ArrayList<Cristal> buscar(String buscar) throws SQLException, ClassNotFoundException {
        return load.listarPorNombre(buscar);
    }

    public ArrayList<Cristal> buscarEliminados(String buscar) throws SQLException, ClassNotFoundException {
        return load.listarEliminadosPorNombre(buscar);
    }
}
