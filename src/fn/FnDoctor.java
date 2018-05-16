/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn;

import dao.DoctorDao;
import entities.Doctor;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author home
 */
public class FnDoctor {
    public ArrayList<Doctor> listar(String rutDoctor) throws SQLException, ClassNotFoundException{
        DoctorDao load = new DoctorDao();
        return load.listar(rutDoctor);
    }
    
    public ArrayList<Doctor> buscar(String valor,int estado) throws SQLException, ClassNotFoundException{
        DoctorDao load = new DoctorDao();
        return load.buscar(valor,estado);
    }
    
    public String guardar(Doctor doctor) throws SQLException, ClassNotFoundException{
        DoctorDao load = new DoctorDao();
        return load.agregar(doctor);
    }
    
    public Doctor cargar(String rut) throws SQLException, ClassNotFoundException{
        DoctorDao load = new DoctorDao();
        for (Doctor temp : load.listar(rut)) {
            if(temp.getRut().equals(rut))
                return temp;
        }
        return null;
    }
    
    public boolean modificar(Doctor doctor) throws SQLException, ClassNotFoundException{
        DoctorDao load = new DoctorDao();
        return load.modificar(doctor);
    }
    
    public boolean eliminar(String rut) throws SQLException, ClassNotFoundException{
        DoctorDao load = new DoctorDao();
        return load.eliminar(rut);
    }
    
    public boolean restaurar(String rut) throws SQLException, ClassNotFoundException{
        DoctorDao load = new DoctorDao();
        return load.restaurar(rut);
    }
}
