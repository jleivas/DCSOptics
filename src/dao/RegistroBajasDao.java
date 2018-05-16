/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bd.BD;
import entities.RegistroBaja;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Lenovo G470
 */
public class RegistroBajasDao {
    
    public ArrayList<RegistroBaja> listar(String idLente) throws SQLException, ClassNotFoundException{
     String sql="SELECT * FROM `registro_bajas` where `lente_len_id`='"+idLente+"' ORDER BY `registro_bajas`.`rb_fecha` DESC ";
     if(idLente.equals("")){sql="SELECT * FROM `registro_bajas` ORDER BY `registro_bajas`.`rb_fecha` DESC ";}
     PreparedStatement consulta = BD.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        ArrayList<RegistroBaja> lista = new ArrayList<>();
        
            
        while (datos.next()) {
            Date fch_fecha= new Date();
            try {
                fch_fecha = datos.getDate("rb_fecha");
            } catch (Exception e) {
                System.out.println("se cayo al intentar convertir la fecha");   
            }
            lista.add(new RegistroBaja(
                                datos.getInt("rb_id")
                               ,fch_fecha
                               ,datos.getString("lente_len_id")
                               ,datos.getInt("rb_cantidad")
                               ,datos.getString("rb_obs")));
        }
        BD.cerrar();
        return lista;
    }
    
}
