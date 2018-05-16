/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn;

import dao.LenteDao;
import dao.RegistroBajasDao;
import entities.Lente;
import entities.RegistroBaja;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author home
 */
public class FnRegistroBajas {
    RegistroBajasDao load = new RegistroBajasDao();
    
    public ArrayList<RegistroBaja> listar(String idLente) throws SQLException, ClassNotFoundException {
        return load.listar(idLente);
    }

//    public String cargarCodigoLente(String idLente) throws SQLException, ClassNotFoundException {
//        LenteDao load = new LenteDao();
//        for (Lente temp : load.cargar(idLente)) {
//            if(temp.getId().equals(idLente))
//                return temp.getCodigo();
//        }
//        return "Sin información";
//    }

    public Lente cargarLente(String codigo) throws SQLException, ClassNotFoundException {
        LenteDao load = new LenteDao();
        String[] id = codigo.split("-");
        for (Lente temp : load.listar(id[0])) {
            if(temp.getId().equals(id[0]))
                return temp;
        }
        return null;
    }

    public ArrayList<Lente> listarLentes() throws SQLException, ClassNotFoundException {
        LenteDao load = new LenteDao();
        return load.listar("todos");
    }
    
}
