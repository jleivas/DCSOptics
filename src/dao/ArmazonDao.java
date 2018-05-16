/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bd.BD;
import entities.Armazon;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author home
 */
public class ArmazonDao {
    /**
     * Retorna los dos armazones guardados en la ficha, si el tipo de armazon es uno corresponde al armazon de cerca, 
     * si el tipo es 0 corresponde al de lejos.
     * @param folioFicha
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public ArrayList<Armazon> listar(int folioFicha) throws SQLException, ClassNotFoundException{
        String sql="SELECT * FROM `armazon` WHERE `ficha_fch_id`='"+folioFicha+"' ";
        
        PreparedStatement consulta = BD.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        ArrayList<Armazon> lista = new ArrayList<>();
        while (datos.next()) {
            lista.add(new Armazon(
                                   datos.getInt("arm_id_cerca")
                                  ,datos.getInt("arm_tipo_cerca")
                                  ,datos.getString("arm_matca")
                                  ,datos.getString("arm_cristal_cerca")
                                  ,datos.getString("arm_add_cerca")
                                  ,datos.getString("arm_od_cerca")
                                  ,datos.getString("arm_od_esf_cerca")
                                  ,datos.getString("arm_od_cil_a_cerca")
                                  ,datos.getString("arm_oi_cerca")
                                  ,datos.getString("arm_oi_esf_cerca")
                                  ,datos.getString("arm_oi_cil_a_cerca")
                                  ,datos.getInt("arm_dp_cerca")
                                  ,datos.getInt("arm_endurecido_cerca")
                                  ,datos.getInt("arm_capa_cerca")
                                  ,datos.getInt("arm_plus_max_cerca")
                                  ,datos.getInt("fch_id")
                                ));
        }
        BD.cerrar();
        return lista;
    }
    
    private int generarId() throws SQLException, ClassNotFoundException {
        String sql="select * from armazon order by arm_id desc limit 1";
        
        
        PreparedStatement consulta = BD.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        ArrayList<Armazon> lista = new ArrayList<>();
        int id = 0;
        while (datos.next()) {
            id = datos.getInt("arm_id");
        }
        BD.cerrar();
        
        return id+1;
    }
    
    public boolean agregar(Armazon nuevo) throws SQLException, ClassNotFoundException{
        
        
        if(nuevo != null){
            
            nuevo.setId(generarId());
            try {
                PreparedStatement insert = BD.obtener().prepareStatement(
                        "INSERT INTO `armazon`(`arm_id`, `arm_tipo`, `arm_marca`, `arm_cristal`, `arm_add`, `arm_od_a`, `arm_od_esf`, `arm_od_cil`, `arm_oi_a`, `arm_oi_esf`, `arm_oi_cil`, `arm_dp`, `arm_endurecido`, `arm_capa`, `arm_plus_max`, `ficha_fch_id`)"
                                + " VALUES('"
                                +nuevo.getId()+"', '"
                                +nuevo.getTipo()+"', '"
                                +nuevo.getMarca()+"', '"
                                +nuevo.getCristal()+"', '"
                                +nuevo.getAdd()+"','"
                                +nuevo.getOdA()+"','"
                                +nuevo.getOdEsf()+"','"
                                +nuevo.getOdCil()+"','"
                                +nuevo.getOiA()+"','"
                                +nuevo.getOiEsf()+"','"
                                +nuevo.getOiCil()+"','"
                                +nuevo.getDp()+"','"
                                +nuevo.getEndurecido()+"','"
                                +nuevo.getCapa()+"','"
                                +nuevo.getPlusMax()+"','"
                                +nuevo.getIdFicha()
                                +"')");
                if(insert.executeUpdate()!=0){
                    BD.cerrar();
                    return true;
                }
            } catch (SQLException e) {
                BD.cerrar();
                return false;
            }
        }
        BD.cerrar();
        return false;
    }
}
