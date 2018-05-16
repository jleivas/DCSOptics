/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bd.BD;
import entities.HistorialPago;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Lenovo G470
 */
public class HistorialPagoDao {
    
    public ArrayList<HistorialPago> listar(int idFicha) throws SQLException, ClassNotFoundException{
        String sql="SELECT * FROM historial_pago "
                + "WHERE `ficha_fch_id`='"+idFicha+"' ";
//        String sql="SELECT * ,(SELECT tipo_pago.tp_id from tipo_pago where historial_pago.tipo_pago_tp_id=tipo_pago.tp_id) as 'tp_id',"
//                + "(SELECT tipo_pago.tp_nombre from tipo_pago where historial_pago.tipo_pago_tp_id=tipo_pago.tp_id) as 'tp_nombre' "
//                + "FROM historial_pago "
//                + "WHERE `ficha_fch_id`='"+idFicha+"' ";
//        if(idFicha==0){
//        sql="SELECT * ,(SELECT tipo_pago.tp_id from tipo_pago where historial_pago.tipo_pago_tp_id=tipo_pago.tp_id) as 'tp_id',"
//                + "(SELECT tipo_pago.tp_nombre from tipo_pago where historial_pago.tipo_pago_tp_id=tipo_pago.tp_id) as 'tp_nombre' "
//                + "FROM historial_pago";
//        }
         
        
        PreparedStatement consulta = BD.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        ArrayList<HistorialPago> lista = new ArrayList<>();
            Date hp_fecha= new Date();
            SimpleDateFormat parseador = new SimpleDateFormat("dd-MM-yy");
            
        
        while (datos.next()) {
            try {
                hp_fecha = datos.getDate("hp_fecha");
            } catch (Exception e) {
                hp_fecha = new Date();
                System.out.println("se cayo al intentar convertir la fecha "+hp_fecha);   
            }
            lista.add(new HistorialPago(
                      datos.getInt("hp_id")
                    , hp_fecha
                    , datos.getInt("hp_abono")
                    , datos.getInt("hp_estado")
                    , datos.getInt("tipo_pago_tp_id")
                    ,datos.getInt("ficha_fch_id")
            ));
        }
        BD.cerrar();
        return lista;
    }
    
    private int generarId() throws SQLException, ClassNotFoundException {
        String sql="select * from historial_pago order by hp_id desc limit 1";
        
        
        PreparedStatement consulta = BD.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        ArrayList<HistorialPago> lista = new ArrayList<>();
        int id = 0;
        while (datos.next()) {
            id = datos.getInt("hp_id");
        }
        BD.cerrar();
        
        return id+1;
    }
    
    public String agregar(HistorialPago historial) throws SQLException, ClassNotFoundException{
        
        if(historial != null){
            historial.setId(generarId());
            try {
                java.util.Date fecha = historial.getFecha();// crea una variable tipo Date
                java.sql.Date sqlfecha = new java.sql.Date(fecha.getTime());//la transforma a sql.Date
                PreparedStatement insert = BD.obtener().prepareStatement(
                        "INSERT INTO `historial_pago`(`hp_id`, `hp_fecha`, `hp_abono`, `hp_estado`, `tipo_pago_tp_id`, `ficha_fch_id`)"
                                + " VALUES('"
                                +historial.getId()+"', '"
                                +sqlfecha+"', '"
                                +historial.getAbono()+"', '"
                                +historial.getEstado()+"', '"
                                +historial.getIdTipoPago()+"','"
                                +historial.getIdFicha()
                                +"')");
                if(insert.executeUpdate()!=0){
                    BD.cerrar();
                    return "Se ha agregado un nuevo pago correctamente";
                }
            } catch (Exception e) {
                BD.cerrar();
                return "ERROR :"+e;
            }
        }
        BD.cerrar();
        return "El historial tiene datos vacios";
    }

    public boolean agregarBoolean(HistorialPago historial) throws SQLException, ClassNotFoundException {
        if(historial != null){
            historial.setId(generarId());
            try {
                java.util.Date fecha = historial.getFecha();// crea una variable tipo Date
                java.sql.Date sqlfecha = new java.sql.Date(fecha.getTime());//la transforma a sql.Date
                PreparedStatement insert = BD.obtener().prepareStatement(
                        "INSERT INTO `historial_pago`(`hp_id`, `hp_fecha`, `hp_abono`, `hp_estado`, `tipo_pago_tp_id`, `ficha_fch_id`)"
                                + " VALUES('"
                                +historial.getId()+"', '"
                                +sqlfecha+"', '"
                                +historial.getAbono()+"', '"
                                +historial.getEstado()+"', '"
                                +historial.getIdTipoPago()+"','"
                                +historial.getIdFicha()
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
