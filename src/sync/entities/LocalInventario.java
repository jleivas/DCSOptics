/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sync.entities;

import bd.LcBd;
import entities.Lente;
import entities.context.InternStockDetail;
import fn.GV;
import static fn.GV.dateToString;
import static fn.GV.strToNumber;
import fn.Log;
import fn.OptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jlleivas
 */
public class LocalInventario {
    private static String className="LocalInventario";
    private static List<InternStockDetail> stockTemporalRebajado = new ArrayList<>();
    
    public static boolean insert(String idLente, int cantidad){
        try {
            int id = getMaxId();
            PreparedStatement insert = LcBd.obtener().prepareStatement(
                    "INSERT INTO intern_stock VALUES("
                            + id + ",'"
                            + idLente + "',"
                            + cantidad + ","
                            + 1 + ")"
            );
            if (insert.executeUpdate() != 0) {
                LcBd.cerrar();
                return true;
            }
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LocalInventario.class.getName()).log(Level.SEVERE, null, ex);
            OptionPane.showMsg("Error de excepcion", className + "\n"
                    + ex.getMessage(),3);
        }
        return false;
    }
    
    public static Lente getLente(String id){
        try {
            for (Object object : listarLentes(id)) {
                if(((Lente)object).getCod().equals(id)){
                    return (Lente)object;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(LocalInventario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static ArrayList<Object> listarLentes(String idParam) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
        ArrayList<Object> lista = new ArrayList<>();
        boolean sincronizar = (idParam.equals(getSqlSync()));
        updateStockTemporal();
        String sql = "SELECT * FROM lente WHERE len_id ='" + idParam + "' AND inventario_inv_id = "+GV.getInventarioSeleccionado();
        if (idParam.equals("0") || idParam.equals(GV.sqlLowStock())) {
            sql = "SELECT * FROM lente WHERE len_estado=1 AND inventario_inv_id = "+GV.getInventarioSeleccionado();
        }
        if (idParam.equals("-1")) {
            sql = "SELECT * FROM lente WHERE len_estado=0 AND inventario_inv_id = "+GV.getInventarioSeleccionado();
        }
        if (idParam.equals("-2")) {
            sql = "SELECT * FROM lente WHERE inventario_inv_id = "+GV.getInventarioSeleccionado();
        }
        if (idParam.equals("st")) {
            sql = "SELECT * FROM lente WHERE (len_estado=1 AND len_stock > 0) AND inventario_inv_id = "+GV.getInventarioSeleccionado();
        }
        if (sincronizar) {
            sql = "SELECT * FROM lente";
        }

        PreparedStatement consulta = LcBd.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        while (datos.next()) {
            String idLente = datos.getString("len_id");
            int currentStock = datos.getInt("len_stock");
            int stock = currentStock-getLocalStock(idLente);
            Date lastUpdate = datos.getDate("len_last_update");
            int lastHour = datos.getInt("len_last_hour");
            if(currentStock != stock && sincronizar){
                lastUpdate = new Date();
                lastHour = strToNumber(dateToString(lastUpdate, "hhmmss"));
                lista.add(new Lente(
                    idLente,
                    datos.getString("len_color"),
                    datos.getString("len_tipo"),
                    datos.getString("len_marca"),
                    datos.getString("len_material"),
                    datos.getInt("len_flex"),
                    datos.getInt("len_clasificacion"),
                    datos.getString("len_descripcion"),
                    datos.getInt("len_precio_ref"),
                    datos.getInt("len_precio_act"),
                    stock,
                    datos.getInt("len_stock_min"),
                    datos.getInt("inventario_inv_id"),
                    datos.getInt("len_estado"),
                    lastUpdate,
                    lastHour
                    )
                );
            }
            if(!sincronizar){
                lista.add(new Lente(
                    idLente,
                    datos.getString("len_color"),
                    datos.getString("len_tipo"),
                    datos.getString("len_marca"),
                    datos.getString("len_material"),
                    datos.getInt("len_flex"),
                    datos.getInt("len_clasificacion"),
                    datos.getString("len_descripcion"),
                    datos.getInt("len_precio_ref"),
                    datos.getInt("len_precio_act"),
                    stock,
                    datos.getInt("len_stock_min"),
                    datos.getInt("inventario_inv_id"),
                    datos.getInt("len_estado"),
                    lastUpdate,
                    lastHour
                    )
                );
            }   
        }
        LcBd.cerrar();
        return lista;
    }
    
    public static boolean deleteAllRegistry(String idLente) {
        try{
            String sql = "UPDATE intern_stock set estado = 0 WHERE id_lente = '" + idLente+"' AND estado = 1";
            if(idLente.equals("-2")){
                sql = "UPDATE intern_stock set estado = 0 WHERE estado = 1";
            }
            PreparedStatement insert = LcBd.obtener().prepareStatement(sql);
            insert.executeUpdate();
            LcBd.cerrar();
        }catch( ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex){
            OptionPane.showMsg("Error inesperado", "Ocurrió un error al intentar borrar los registros temporales del stock\n"
                    + "en :"+className+"\n\n"+Log.getLog(), 3);
            updateStockTemporal();
            return false;
        }
        updateStockTemporal();
        return true;
    }

    public static int getMaxId() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        Log.setLog(className, Log.getReg());
        int id = 0;
        String sql = "SELECT MAX(id) as suma FROM intern_stock";
        PreparedStatement consulta = LcBd.obtener().prepareStatement(sql);
            ResultSet datos = consulta.executeQuery();
            while (datos.next()) {
                id = datos.getInt("suma");
            }
            LcBd.cerrar();
        return id + 1;
    }
    
    /**
     * retorna la cantidad total de registros de stock que no han sido eliminados
     * No se actualiza la lista estática porue este método sólo se llama desde listar lentes
     * para evitar problemas de conexion con la base de datos
     * @param idLente
     * @return 
     */
    private static int getLocalStock(String idLente){
        return searchInList(idLente).getCantidad();
    }
    
    /**
     * actualiza lista de stock retorna la cantidad total de registros de stock que no han sido eliminados
     * @param idLente
     * @return 
     */
    public static int stockDescontado(String idLente){
        updateStockTemporal();
        return searchInList(idLente).getCantidad();
    }
    
    private static InternStockDetail searchInList(String idLente) {
        Optional<InternStockDetail> objectFound = stockTemporalRebajado.stream()
            .filter(p -> p.getCod().equals(idLente))
            .findFirst();
        return objectFound.isPresent() ? objectFound.get() : new InternStockDetail();
    }
    
    private static void updateStockTemporal(){
        stockTemporalRebajado = listarStocksTemporales();
    }
    
    public static List<InternStockDetail> listarStocksTemporales(){
        Log.setLog(className, Log.getReg());
        List<InternStockDetail> listaStock = new ArrayList<>();
        try{
            String sql = "SELECT id_lente, SUM(stock) as cantidad FROM intern_stock WHERE estado = 1 GROUP BY id_lente";
            PreparedStatement consulta = LcBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    listaStock.add(new InternStockDetail(datos.getString("id_lente"), datos.getInt("cantidad")));
                }
                LcBd.cerrar();
        }catch(Exception e){
            return new ArrayList<InternStockDetail>();
        }
        return listaStock;
    }
    
    public static ArrayList<Object> listarLentesForSync(){
        try {
            return listarLentes(getSqlSync());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(LocalInventario.class.getName()).log(Level.SEVERE, null, ex);
            OptionPane.showMsg("Error inesperado", className+"\n"
                    + "Ocurrió un error al listar lentes para sincronizar\n"
                    + ex.getMessage(), 3);
        }
        return new ArrayList<>();
    }

    private static String getSqlSync() {
        return "sincronizar";
    }
}
