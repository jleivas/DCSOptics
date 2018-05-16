/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn;

import dao.ClienteDao;
import dao.InfoDao;
import entities.Cliente;
import entities.Info;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author home
 */
public class FnCliente {
    public ArrayList<Cliente> listar(String rutCliente) throws SQLException, ClassNotFoundException{
        ClienteDao load = new ClienteDao();
        return load.listar(rutCliente);
    }
    
    public ArrayList<Cliente> buscar(String valor,int estado) throws SQLException, ClassNotFoundException{
        ClienteDao load = new ClienteDao();
        return load.buscar(valor,estado);
    }
    
    public String guardar(Cliente cliente) throws SQLException, ClassNotFoundException{
        ClienteDao load = new ClienteDao();
        return load.agregar(cliente);
    }
    
    public Cliente cargar(String rut) throws SQLException, ClassNotFoundException{
        ClienteDao load = new ClienteDao();
        for (Cliente temp : load.listar(rut)) {
            if(temp.getRut().equals(rut))
                return temp;
        }
        return null;
    }
    
    public boolean modificar(Cliente cliente) throws SQLException, ClassNotFoundException{
        ClienteDao load = new ClienteDao();
        return load.modificar(cliente);
    }
    
    public boolean eliminar(String rut) throws SQLException, ClassNotFoundException{
        ClienteDao load = new ClienteDao();
        return load.eliminar(rut);
    }
    
    public boolean restaurar(String rut) throws SQLException, ClassNotFoundException{
        ClienteDao load = new ClienteDao();
        return load.restaurar(rut);
    }
    
    public boolean guardarCorreos() throws SQLException, ClassNotFoundException {
        ArrayList<Cliente> lista = listar("");
        String sexo = "Sin registrar";
        JFileChooser archivo = new JFileChooser();
        Fechas process = new Fechas();
        //DATOS DE LA EMPRESA
        InfoDao loadEmpresa = new InfoDao();
        Info empresa = new Info();
        empresa = loadEmpresa.cargar(1);//el id siempre sera uno
        //***************
        int resp;
        if(lista.size()<1){
            JOptionPane.showMessageDialog(null, "No existen registros para guardar.");
            return false;
        }
        resp = archivo.showSaveDialog(archivo);
        if(resp == JFileChooser.APPROVE_OPTION){
            if(lista.size()>0){
                //[filas][columnas]
                int filas = lista.size()+6;
                String [][] entrada = new String[filas][7];
                entrada[0][1] = "Documento generado el "+process.imprimirFechaActual()+"";
                entrada[1][0] = "Empresa:";
                entrada[1][1] = empresa.getNombre();
                entrada[2][0] = "Sistema:";
                entrada[2][1] = "Optidata 2018";
                entrada[3][0] = "Soporte:";
                entrada[3][1] = "www.softdirex.cl";
                entrada[5][0] = "Rut";
                entrada[5][1] = "Nombre";
                entrada[5][2] = "Comuna";
                entrada[5][3] = "Ciudad";
                entrada[5][4] = "Sexo";
                entrada[5][5] = "Teléfono";
                entrada[5][6] = "Correo";
                int contFilas = 6;
                for (Cliente temp : lista) {
                    for(int i = 0;i< 7; i++){
                        if(i==0)
                            entrada[contFilas][i] = temp.getRut();
                        else if(i == 1)
                            entrada[contFilas][i] = temp.getNombre();
                        else if(i == 2)
                            entrada[contFilas][i] = temp.getComuna();
                        else if(i == 3)
                            entrada[contFilas][i] = temp.getCiudad();
                        else if(i == 4){
                            if(temp.getSexo()==0)
                                sexo="Sin registrar";
                            if(temp.getSexo()==1)
                                sexo="Femenino";
                            if(temp.getSexo()==2)
                                sexo="Masculino";
                            entrada[contFilas][i] = sexo;
                        }
                        else if(i == 5)
                            entrada[contFilas][i] = temp.getTelefono();
                        else if(i == 6)
                            entrada[contFilas][i] = temp.getEmail();
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

    public boolean guardarBoolean(Cliente cliente) throws SQLException, ClassNotFoundException {
        ClienteDao load = new ClienteDao();
        return load.agregarBoolean(cliente);
    }

    public boolean guardarCorreosMorosos() throws SQLException, ClassNotFoundException {
        ArrayList<Cliente> lista = listarMorosos();
        String sexo = "Sin registrar";
        JFileChooser archivo = new JFileChooser();
        Fechas process = new Fechas();
        //DATOS DE LA EMPRESA
        InfoDao loadEmpresa = new InfoDao();
        Info empresa  = loadEmpresa.cargar(1);//el id siempre sera uno
        //***************
        int resp;
        if(lista.size()<1){
            JOptionPane.showMessageDialog(null, "No existen registros para guardar.");
            return false;
        }
        resp = archivo.showSaveDialog(archivo);
        if(resp == JFileChooser.APPROVE_OPTION){
            if(lista.size()>0){
                //[filas][columnas]
                int filas = lista.size()+6;
                String [][] entrada = new String[filas][7];
                entrada[0][1] = "Documento generado el "+process.imprimirFechaActual()+"";
                entrada[1][0] = "Empresa:";
                entrada[1][1] = empresa.getNombre();
                entrada[2][0] = "Sistema:";
                entrada[2][1] = "Optidata 2018";
                entrada[3][0] = "Soporte:";
                entrada[3][1] = "www.softdirex.cl";
                entrada[5][0] = "Rut";
                entrada[5][1] = "Nombre";
                entrada[5][2] = "Comuna";
                entrada[5][3] = "Ciudad";
                entrada[5][4] = "Sexo";
                entrada[5][5] = "Teléfono";
                entrada[5][6] = "Correo";
                int contFilas = 6;
                for (Cliente temp : lista) {
                    for(int i = 0;i< 7; i++){
                        if(i==0)
                            entrada[contFilas][i] = temp.getRut();
                        else if(i == 1)
                            entrada[contFilas][i] = temp.getNombre();
                        else if(i == 2)
                            entrada[contFilas][i] = temp.getComuna();
                        else if(i == 3)
                            entrada[contFilas][i] = temp.getCiudad();
                        else if(i == 4){
                            if(temp.getSexo()==0)
                                sexo="Sin registrar";
                            if(temp.getSexo()==1)
                                sexo="Femenino";
                            if(temp.getSexo()==2)
                                sexo="Masculino";
                            entrada[contFilas][i] = sexo;
                        }
                        else if(i == 5)
                            entrada[contFilas][i] = temp.getTelefono();
                        else if(i == 6)
                            entrada[contFilas][i] = temp.getEmail();
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

    private ArrayList<Cliente> listarMorosos() throws SQLException, ClassNotFoundException {
        ClienteDao load = new ClienteDao();
        return load.morosos();
    }

    public boolean guardarCorreosSinRetirar() throws SQLException, ClassNotFoundException {
        ArrayList<Cliente> lista = listarSinRetirar();
        String sexo = "Sin registrar";
        JFileChooser archivo = new JFileChooser();
        Fechas process = new Fechas();
        //DATOS DE LA EMPRESA
        InfoDao loadEmpresa = new InfoDao();
        Info empresa  = loadEmpresa.cargar(1);//el id siempre sera uno
        //***************
        int resp;
        if(lista.size()<1){
            JOptionPane.showMessageDialog(null, "No existen registros para guardar.");
            return false;
        }
        resp = archivo.showSaveDialog(archivo);
        if(resp == JFileChooser.APPROVE_OPTION){
            if(lista.size()>0){
                //[filas][columnas]
                int filas = lista.size()+6;
                String [][] entrada = new String[filas][7];
                entrada[0][1] = "Documento generado el "+process.imprimirFechaActual()+"";
                entrada[1][0] = "Empresa:";
                entrada[1][1] = empresa.getNombre();
                entrada[2][0] = "Sistema:";
                entrada[2][1] = "Optidata 2018";
                entrada[3][0] = "Soporte:";
                entrada[3][1] = "www.softdirex.cl";
                entrada[5][0] = "Rut";
                entrada[5][1] = "Nombre";
                entrada[5][2] = "Comuna";
                entrada[5][3] = "Ciudad";
                entrada[5][4] = "Sexo";
                entrada[5][5] = "Teléfono";
                entrada[5][6] = "Correo";
                int contFilas = 6;
                for (Cliente temp : lista) {
                    for(int i = 0;i< 7; i++){
                        if(i==0)
                            entrada[contFilas][i] = temp.getRut();
                        else if(i == 1)
                            entrada[contFilas][i] = temp.getNombre();
                        else if(i == 2)
                            entrada[contFilas][i] = temp.getComuna();
                        else if(i == 3)
                            entrada[contFilas][i] = temp.getCiudad();
                        else if(i == 4){
                            if(temp.getSexo()==0)
                                sexo="Sin registrar";
                            if(temp.getSexo()==1)
                                sexo="Femenino";
                            if(temp.getSexo()==2)
                                sexo="Masculino";
                            entrada[contFilas][i] = sexo;
                        }
                        else if(i == 5)
                            entrada[contFilas][i] = temp.getTelefono();
                        else if(i == 6)
                            entrada[contFilas][i] = temp.getEmail();
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

    private ArrayList<Cliente> listarSinRetirar() throws SQLException, ClassNotFoundException {
        ClienteDao load = new ClienteDao();
        return load.sinRetirar();
    }
}
