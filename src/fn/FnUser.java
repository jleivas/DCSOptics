/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn;

import dao.UserDao;
import entities.User;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author home
 */
public class FnUser {
    UserDao load= new UserDao();
    public User cargar(int id) throws SQLException, ClassNotFoundException {
        for (User temp : load.listar(id)) {
            if(temp.getId()==id)
                return temp;
        }
        return null;
    }

    public boolean eliminar(int id) throws SQLException {
        return load.eliminar(id);
    }

    public ArrayList<User> listar(int listar) throws SQLException, ClassNotFoundException {
        return load.listar(listar);
    }

    public int obtenerId() throws SQLException, ClassNotFoundException {
        return load.obtenerId();
    }

    public boolean guardar(User user) throws SQLException, ClassNotFoundException {
        return load.guardar(user);
    }

    public boolean modificar(User user) throws SQLException, ClassNotFoundException {
        return load.modificar(user);
    }

    public boolean restaurar(int id) throws SQLException, ClassNotFoundException {
        User mod = null;
        for (User temp : load.listar(id)) {
            if(temp.getId()==id){
                mod=temp;
            }
        }
        if(mod==null)
            return false;
        else
            mod.setEstado(1);
        return load.modificar(mod);
    }

    public ArrayList<User> buscar(String buscar, int estado) throws SQLException, ClassNotFoundException {
        return load.buscar(buscar,estado);
    }

    public User validar(String user, String pass) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        return load.validar(user,pass);
    }
    
}
