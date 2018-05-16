/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn;

import correo.FnEnviar;
import dao.ClienteDao;
import dao.DespachoDao;
import dao.FichaDao;
import dao.HistorialPagoDao;
import dao.InfoDao;
import dao.TipoPagoDao;
import entities.Cliente;
import entities.Despacho;
import entities.Ficha;
import entities.HistorialPago;
import entities.Info;
import entities.TipoPago;
import entities.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import jxl.biff.formula.ParseContext;

/**
 *
 * @author home
 */
public class FnFicha {

    private FichaDao load = new FichaDao();
    public ArrayList<Ficha> listar(int id,int estado) throws SQLException, ClassNotFoundException {
        
        return load.listar(id,estado);
    }
    
    public ArrayList<Ficha> listarPorSesion(int id, int idSesion, int estado) throws SQLException, ClassNotFoundException {
        return load.listarPorSesion(id,idSesion,estado);
    }
    
    public ArrayList<TipoPago> listaTipoPago() throws SQLException, ClassNotFoundException {
        TipoPagoDao load = new TipoPagoDao();
        return load.listar(0);
    }

    public Ficha cargar(int idFicha,int estado) throws SQLException, ClassNotFoundException {
        
        for (Ficha temp : load.listar(idFicha, estado)) {
            if(temp.getId()==idFicha)
                return temp;
        }
        return null;
    }

    public boolean eliminar(int idFicha) throws SQLException {
        if(validarAdmin())
            return load.eliminar(idFicha);
        else
            return false;
    }
    
    public boolean validarAdmin() {
        JPasswordField pwd = new JPasswordField(10);
        int res = JOptionPane.showConfirmDialog(null,pwd, "Ingrese clave de administrador",JOptionPane.OK_CANCEL_OPTION);
        if(res < 0){
            return false;
        }else{
            if(res == 2)
                return false;
            String respuesta = pwd.getText();
            FnInfo load = new FnInfo();
            try {
                if(respuesta.equals(load.getPass())){
                    return true;
                }else
                    JOptionPane.showMessageDialog(null, "La contraseña es incorrecta","Acceso denegado",JOptionPane.ERROR_MESSAGE);
        }catch (SQLException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Error: "+ex.getMessage(),"Error",JOptionPane.WARNING_MESSAGE);
            }
        }
        return false;
    }


    public ArrayList<Ficha> listarPorPalabra(String buscar, int estado) throws SQLException, ClassNotFoundException {
        return load.listarPorPalabra(buscar,estado);
    }
    
    public ArrayList<Ficha> listarPorPalabraSesion(String buscar, int idSesion, int estado) throws SQLException, ClassNotFoundException {
        return load.listarPorPalabraSesion(buscar,idSesion,estado);
    }

    public boolean guardarAbono(HistorialPago hp) throws SQLException, ClassNotFoundException {
        HistorialPagoDao load = new HistorialPagoDao();
        return load.agregarBoolean(hp);
    }

    public boolean actualizar(Ficha ficha) throws SQLException {
        return load.actualizar(ficha);
    }
    
    public boolean actualizarObs(Ficha ficha) throws SQLException {
        return load.actualizarObs(ficha);
    }

    public boolean restaurar(int idFicha) throws SQLException, ClassNotFoundException {
        return load.restaurar(idFicha);
    }
    
    public boolean guardarDespacho(Despacho despacho) throws SQLException, ClassNotFoundException {
        DespachoDao load = new DespachoDao();
        return load.agregar(despacho);
    }

    public ArrayList<Ficha> listarPorFecha(Date fecha1, Date fecha2) throws SQLException, ClassNotFoundException {
        if(fecha1 == null && fecha2 == null){
            fecha1 = new Date();
            fecha2 = fecha1;
        }
        if(fecha1 == null){
            fecha1 = fecha2;
        }
        if(fecha2 == null)
            fecha2=fecha1;
        FichaDao load = new FichaDao();
        return load.listarFecha(fecha1, fecha2);
    }
    
    public ArrayList<Ficha> listarPorRut(String rut) throws SQLException, ClassNotFoundException {
        
        FichaDao load = new FichaDao();
        return load.listarRut(rut);
    }
    
    public ArrayList<Ficha> listarPorFechaSesion(Date fecha1, Date fecha2, int idSesion) throws SQLException, ClassNotFoundException {
        if(fecha1 == null && fecha2 == null){
            fecha1 = new Date();
            fecha2 = fecha1;
        }
        if(fecha1 == null){
            fecha1 = fecha2;
        }
        if(fecha2 == null)
            fecha2=fecha1;
        FichaDao load = new FichaDao();
        return load.listarFechaSesion(fecha1, fecha2,idSesion);
    }

    public ArrayList<HistorialPago> listarHistorial(int idFolio) throws SQLException, ClassNotFoundException {
        HistorialPagoDao load = new HistorialPagoDao();
        return load.listar(idFolio);
    }

    public TipoPago cargarTipoPago(int idTipoPago) throws SQLException, ClassNotFoundException {
       TipoPagoDao load = new TipoPagoDao();
        for (TipoPago temp : load.listar(idTipoPago)) {
            if(temp.getId() == idTipoPago)
                return temp;
        }
        return null;
    }

    public String obtenerNombreDespacho(int idFolio) throws SQLException, ClassNotFoundException {
        DespachoDao load = new DespachoDao();
        Despacho dsp = new Despacho();
        dsp = load.encontrar(idFolio);
        if(dsp != null){
            return "Producto retirado por: "+dsp.getNombre()+" Rut: "+dsp.getRut()+"";
        }
        return "El producto aún no a sido retirado";
    }

    public void enviarComprobantes(ArrayList<Ficha> listarPorFecha) throws SQLException, ClassNotFoundException, InterruptedException {
        Info empresa = new Info();
        InfoDao load = new InfoDao();
        if(listarPorFecha.size() == 0){
            JOptionPane.showMessageDialog(null, "No existen registros para enviar","Sin registros",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        empresa = load.cargar(1);
        
        FnEnviar send = new FnEnviar();
        send.enviarComprobantes(listarPorFecha, empresa,0);
    }

    public void enviarReporte(Date fecha1,Date fecha2,ArrayList<Ficha> listarPorFecha) throws SQLException, ClassNotFoundException {
        Info empresa = new Info();
        InfoDao load = new InfoDao();
        if(listarPorFecha.size() == 0){
            JOptionPane.showMessageDialog(null, "No existen registros para enviar","Sin registros",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        empresa = load.cargar(1);
        
        FnEnviar send = new FnEnviar();
        if(send.enviarReporte(fecha1,fecha2,listarPorFecha, empresa))
            JOptionPane.showMessageDialog(null, "Reporte enviado.","Reporte",JOptionPane.INFORMATION_MESSAGE);
        else
            JOptionPane.showMessageDialog(null, "No se pudo enviar el reporte.","Reporte",JOptionPane.WARNING_MESSAGE);
    }

    public ArrayList<Ficha> morosos() throws SQLException, ClassNotFoundException {
        ClienteDao cli = new ClienteDao();
        ArrayList<String> rutClientes = new ArrayList<>();
        for (Cliente temp : cli.morosos()) {
            rutClientes.add(temp.getRut());
        }
        return load.morosos(rutClientes);
    }

    public void enviarNotificacion(ArrayList<Ficha> morosos) throws SQLException, ClassNotFoundException, InterruptedException {
        Info empresa = new Info();
        InfoDao load = new InfoDao();
        if(morosos.size() == 0){
            JOptionPane.showMessageDialog(null, "No existen registros para enviar","Sin registros",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        empresa = load.cargar(1);
        
        FnEnviar send = new FnEnviar();
        send.enviarNotificaciones(morosos, empresa);
    }

    public boolean garantia(String observacion, String idFolio,Date fecha, String lugar, String hora) throws SQLException, ClassNotFoundException {
        int id=Integer.parseInt(idFolio);
        FichaDao load = new FichaDao();
        return load.garantia(id,observacion,fecha,lugar,hora);
    }

    public ArrayList<Ficha> garantias() throws SQLException, ClassNotFoundException {
        return load.garantias();
    }

    public ArrayList<Ficha> garantiasSesion(int idSesion) throws SQLException, ClassNotFoundException {
        return load.garantiasSesion(idSesion);
    }

    public ArrayList<User> obtenerUser() throws SQLException, ClassNotFoundException {
        FnUser load = new FnUser();
        return load.listar(0);
    }

    public boolean exportarExcel(ArrayList<Ficha> lista) throws SQLException, ClassNotFoundException {
        JFileChooser archivo = new JFileChooser();
        Fechas process = new Fechas();
        //DATOS DE LA EMPRESA
        InfoDao loadEmpresa = new InfoDao();
        Info empresa  = loadEmpresa.cargar(1);//el id siempre sera uno
        //***************
        int resp;
        if(lista.size()<1){
            JOptionPane.showMessageDialog(null, "No existen registros para guardar, seleccione una fecha válida.","Sin parámetros",JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        resp = archivo.showSaveDialog(archivo);
        if(resp == JFileChooser.APPROVE_OPTION){
            if(lista.size()>0){
                //[filas][columnas]
                int filas = lista.size()+6;
                String [][] entrada = new String[filas][15];
                entrada[0][1] = "Documento generado el "+process.imprimirFechaActual()+"";
                entrada[1][0] = "Empresa:";
                entrada[1][1] = empresa.getNombre();
                entrada[2][0] = "Sistema:";
                entrada[2][1] = "Optidata 2018";
                entrada[3][0] = "Soporte:";
                entrada[3][1] = "www.softdirex.cl";
                entrada[5][0] = "Fecha";
                entrada[5][1] = "Folio";
                entrada[5][2] = "Rut";
                entrada[5][3] = "Nombre";
                entrada[5][4] = "Dirección";
                entrada[5][5] = "Teléfono";
                entrada[5][6] = "Comuna";
                entrada[5][7] = "Ciudad";
                entrada[5][8] = "Total";
                entrada[5][9] = "Abono";
                entrada[5][10] = "Saldo";
                entrada[4][12] = "Datos";
                entrada[4][13] = "de";
                entrada[4][14] = "entrega";
                
                entrada[5][12] = "Fecha";
                entrada[5][13] = "Hora";
                entrada[5][14] = "Lugar";
                int contFilas = 6;
                for (Ficha temp : lista) {
                    for(int i = 0;i< 15; i++){
                        if(i==0)
                            entrada[contFilas][i] = ""+temp.getFecha();
                        else if(i == 1)
                            entrada[contFilas][i] = ""+temp.getId();
                        else if(i == 2)
                            entrada[contFilas][i] = temp.getCliente().getRut();
                        else if(i == 3)
                            entrada[contFilas][i] = temp.getCliente().getNombre();
                        else if(i == 4)
                            entrada[contFilas][i] = temp.getCliente().getDireccion();
                        else if(i == 5)
                            entrada[contFilas][i] = temp.getCliente().getTelefono();
                        else if(i == 6)
                            entrada[contFilas][i] = temp.getCliente().getComuna();
                        else if(i == 7)
                            entrada[contFilas][i] = temp.getCliente().getCiudad();
                        else if(i == 8)
                            entrada[contFilas][i] = ""+temp.getValorTotal();
                        else if(i == 9)
                            entrada[contFilas][i] = ""+(temp.getValorTotal()-temp.getSaldo());
                        else if(i == 10)
                            entrada[contFilas][i] = ""+temp.getSaldo();
                        else if(i == 12)
                            entrada[contFilas][i] = ""+temp.getFechaEntrega();
                        else if(i == 13)
                            entrada[contFilas][i] = ""+temp.getHoraEntrega();
                        else if(i == 14)
                            entrada[contFilas][i] = temp.getLugarEntrega();
                    }
                    contFilas++;
                }
                String ruta = String.valueOf(archivo.getSelectedFile().toString())+"-["+process.imprimirFechaActual()+"].xls";
                GuardarPlanilla saveXls = new GuardarPlanilla();
                saveXls.generarExcel(entrada, ruta);
                JOptionPane.showMessageDialog(null, "Archivo creado con exito.");
                return true;
            }
        }else if(resp == JFileChooser.CANCEL_OPTION){
            JOptionPane.showMessageDialog(null, "La operacion ha sido cancelada.");
        }
        return false;
    }

    public int obtenerVendedor(int id) throws SQLException, ClassNotFoundException {
         return load.obtenerVendedor(id);
    }

    

    

    
    
}
