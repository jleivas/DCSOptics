/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn;

import dao.InstitucionDao;
import entities.Institucion;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author home
 */
public class FnInstitucion {
    public ArrayList<Institucion> listar(int id) throws SQLException, ClassNotFoundException{
        InstitucionDao load = new InstitucionDao();
        return load.listar(id);
    }
    
    public ArrayList<Institucion> buscar(String valor,int estado) throws SQLException, ClassNotFoundException{
        InstitucionDao load = new InstitucionDao();
        return load.buscar(valor,estado);
    }
    
    public String guardar(Institucion nueva) throws SQLException, ClassNotFoundException{
        InstitucionDao load = new InstitucionDao();
        return load.agregar(nueva);
    }
    
    public Institucion cargar(int id) throws SQLException, ClassNotFoundException{
        InstitucionDao load = new InstitucionDao();
        for (Institucion temp : load.listar(id)) {
            if(temp.getId()==id)
                return temp;
        }
        return null;
    }
    
    public boolean modificar(Institucion temp) throws SQLException, ClassNotFoundException{
        InstitucionDao load = new InstitucionDao();
        return load.modificar(temp);
    }
    
    public boolean eliminar(int id) throws SQLException, ClassNotFoundException{
        InstitucionDao load = new InstitucionDao();
        return load.eliminar(id);
    }
    
    public boolean restaurar(int id) throws SQLException, ClassNotFoundException{
        InstitucionDao load = new InstitucionDao();
        return load.restaurar(id);
    }
}
