/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sync.lente;

import bd.LcBd;
import entities.Lente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import sync.SyncBd;

/**
 *
 * @author sdx
 */
public class LcBdLente implements SyncBd{
    
    /**
     * Agrega o modifica un objeto en la base de datos local
     * @param lente
     * @return true si se insertó o modificó
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public static boolean update(Lente lente) throws   SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        if(lente != null){
                PreparedStatement consulta = LcBd.obtener().prepareStatement("SELECT len_id FROM lente WHERE len_id='"+lente.getId()+"' and len_marca='"+lente.getMarca()+"' and len_color='"+lente.getColor()+"' and inventario_inv_id="+lente.getInventario());
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    LcBd.cerrar();
                    return modificar(lente);
                }    
                //////// dar formato String a fecha
                java.sql.Date sqlfecha = new java.sql.Date(lente.getLastUpdate().getTime());//la transforma a sql.Date
                
                PreparedStatement insert = LcBd.obtener().prepareStatement(
                        "INSERT INTO lente VALUES('"
                                +lente.getId()+"', '"
                                +lente.getColor()+"', '"
                                +lente.getTipo()+"', '"
                                +lente.getMarca()+"', '"
                                +lente.getMaterial()+"', "
                                +lente.getFlex()+", "
                                +lente.getClasificacion()+", '"
                                +lente.getDescripcion()+"', "
                                +lente.getPrecioRef()+", "
                                +lente.getPrecioAct()+", '"
                                +lente.getStock()+", "
                                +lente.getStockMin()+", "
                                +lente.getEstado()+", "
                                +lente.getInventario()+", '"
                                +sqlfecha+"')"
                               );
                if(insert.executeUpdate()!=0){
                    LcBd.cerrar();
                    JOptionPane.showMessageDialog(null, "Operación realizada correctamente");
                    return true;
                }
        }
        JOptionPane.showMessageDialog(null, "Ha ocurrido un error", "Error", JOptionPane.WARNING_MESSAGE);
        return false;
    }
    
    public static boolean modificar(Lente lente) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        java.sql.Date sqlfecha = new java.sql.Date(lente.getLastUpdate().getTime());//la transforma a sql.Date
        PreparedStatement insert = LcBd.obtener().prepareStatement(
                "UPDATE lente set len_color = '"+lente.getColor()
                        +"', len_tipo = '"+lente.getTipo()
                        +"', len_marca = '"+lente.getMarca()
                        +"', len_material = '"+lente.getMaterial()
                        +"', len_flex = "+lente.getFlex()
                        +", len_clasificacion = "+lente.getClasificacion()
                        +", len_descripcion = '"+lente.getDescripcion()
                        +"', len_precio_ref = "+lente.getPrecioRef()
                        +", len_precio_act = "+lente.getPrecioAct()
                        +", len_stock = "+lente.getStock()
                        +", len_stock_min = "+lente.getStockMin()
                        +", len_estado = "+lente.getEstado()
                        +", inventario_inv_id = "+lente.getInventario()
                        +", len_last_update = '"+sqlfecha
                        +"' WHERE len_id = '"+lente.getId()+"' and len_color = '"+lente.getColor()+"' and len_marca = '"+lente.getMarca()+"' and inventario_inv_id = "+lente.getInventario());
        if(insert.executeUpdate()!=0){
            LcBd.cerrar();
            JOptionPane.showMessageDialog(null, "Operación realizada correctamente");
            return true;
        }
        else{
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error", "Error", JOptionPane.WARNING_MESSAGE);
            LcBd.cerrar();
            return true;
        }
    }

    @Override
    public boolean add(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object get(String objectId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object get(int objectId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addIndex(Object oldObject, Object newObject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
