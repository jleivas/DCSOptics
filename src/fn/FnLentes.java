/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn;

import dao.LenteDao;
import entities.Lente;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author home
 */
public class FnLentes {

    private LenteDao load = new LenteDao();
    
    public ArrayList<Lente> activos() throws SQLException, ClassNotFoundException {
        return load.listar("activos");
    }

    public boolean guardar(Lente lente) throws SQLException, ClassNotFoundException {
        return load.agregar(lente);
    }

    public Lente cargar(String codigo) throws SQLException, ClassNotFoundException {
        String[] id = codigo.split("-");
        for (Lente temp : load.listar(id[0])) {
            if(temp.getId().equals(id[0]) && temp.getMarca().equals(id[1]) && temp.getColor().equals(id[2]))
                return temp;
        }
        return null;
    }

    public boolean modificar(Lente lente) throws SQLException, ClassNotFoundException {
        return load.modificar(lente);
    }

    public boolean eliminar(String id) throws SQLException, ClassNotFoundException {
        return load.bloquear(id);
    }

    public ArrayList<Lente> inactivos() throws SQLException, ClassNotFoundException {
        return load.listar("inactivos");
    }

    public boolean restaurar(String id) throws SQLException, ClassNotFoundException {
        return load.desbloquear(id);
    }

    public ArrayList<Lente> buscar(String buscar) throws SQLException, ClassNotFoundException {
        return load.listar(buscar);
    }

    public ArrayList<Lente> buscarEliminados(String buscar) throws SQLException, ClassNotFoundException {
        return load.buscarEliminados(buscar);
    }
    
    public boolean reducir(String idLente,int cantidad,String observacion) throws SQLException, ClassNotFoundException{
        return load.descontar(idLente, cantidad, observacion);
    }

    public ArrayList<Lente> stockBajo() throws SQLException, ClassNotFoundException {
        ArrayList<Lente> stb = new ArrayList<>();
        for (Lente temp : load.listar("activos")) {
            if(temp.getStock() <= temp.getStockMin())
                stb.add(temp);
        }
        return stb;
    }

    public String items() throws SQLException, ClassNotFoundException {
        return ""+load.items();
    }

    public String unids() throws SQLException, ClassNotFoundException {
        return ""+load.unids();
    }

    public String valorTotal() throws SQLException, ClassNotFoundException {
        return "$ "+load.valorTotal();
    }
    
}
