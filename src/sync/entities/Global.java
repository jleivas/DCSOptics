/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sync.entities;

import entities.Cliente;
import entities.Cristal;
import entities.Descuento;
import entities.Doctor;
import entities.Institucion;
import entities.Lente;
import entities.Oficina;
import entities.RegistroBaja;
import entities.TipoPago;
import entities.User;
import fn.GlobalValues;
import fn.Log;
import fn.date.Cmp;
import java.util.ArrayList;
import java.util.Date;
import sync.InterfaceSync;

/**
 *
 * @author sdx
 */
public class Global implements InterfaceSync{
    private static String className="Global";

    public boolean add(Object object) {
        Log.setLog(className,Log.getReg());
        if(object == null)
            return false;
        else{
            if(object instanceof User){
                for (User temp : GlobalValues.TMP_LIST_USERS) {
                    if(temp.getId()== ((User)object).getId()){
                        if(!Cmp.localIsNewOrEqual(temp.getLastUpdate(), ((User)object).getLastUpdate())){
                            int index = GlobalValues.TMP_LIST_USERS.indexOf(temp);
                            GlobalValues.TMP_LIST_USERS.add(index, (User)object);
                            GlobalValues.TMP_LIST_USERS.remove(index+1);
                            return true;
                        }else
                            return false;
                    }
                        
                }
                GlobalValues.TMP_LIST_USERS.add((User)object);
            }
            if(object instanceof Cristal){
                for (Cristal temp : GlobalValues.TMP_LIST_CRISTAL) {
                    if(temp.getId()== ((Cristal)object).getId()){
                        if(!Cmp.localIsNewOrEqual(temp.getLastUpdate(), ((Cristal)object).getLastUpdate())){
                            int index = GlobalValues.TMP_LIST_CRISTAL.indexOf(temp);
                            GlobalValues.TMP_LIST_CRISTAL.add(index, (Cristal)object);
                            GlobalValues.TMP_LIST_CRISTAL.remove(index+1);
                            return true;
                        }else
                            return false;
                    }
                        
                }
                GlobalValues.TMP_LIST_CRISTAL.add((Cristal)object);
            }
            if(object instanceof Descuento){
                for (Descuento temp : GlobalValues.TMP_LIST_DESCUENTO) {
                    if(temp.getId() == ((Descuento)object).getId()){
                        if(!Cmp.localIsNewOrEqual(temp.getLastUpdate(), ((Descuento)object).getLastUpdate())){
                            int index = GlobalValues.TMP_LIST_DESCUENTO.indexOf(temp);
                            GlobalValues.TMP_LIST_DESCUENTO.add(index, (Descuento)object);
                            GlobalValues.TMP_LIST_DESCUENTO.remove(index+1);
                            return true;
                        }else
                            return false;
                    }
                        
                }
                GlobalValues.TMP_LIST_DESCUENTO.add((Descuento)object);
            }
            if(object instanceof Cliente){
                for (Cliente temp : GlobalValues.TMP_LIST_CLIENTES) {
                    if(temp.getCod().equals(((Cliente)object).getCod())){
                        if(!Cmp.localIsNewOrEqual(temp.getLastUpdate(), ((Cliente)object).getLastUpdate())){
                            int index = GlobalValues.TMP_LIST_CLIENTES.indexOf(temp);
                            GlobalValues.TMP_LIST_CLIENTES.add(index, (Cliente)object);
                            GlobalValues.TMP_LIST_CLIENTES.remove(index+1);
                            return true;
                        }else
                            return false;
                    }
                        
                }
                GlobalValues.TMP_LIST_CLIENTES.add((Cliente)object);
            }
            if(object instanceof Doctor){
                for (Doctor temp : GlobalValues.TMP_LIST_DOCTORES) {
                    if(temp.getCod().equals(((Doctor)object).getCod())){
                        if(!Cmp.localIsNewOrEqual(temp.getLastUpdate(), ((Doctor)object).getLastUpdate())){
                            int index = GlobalValues.TMP_LIST_DOCTORES.indexOf(temp);
                            GlobalValues.TMP_LIST_DOCTORES.add(index, (Doctor)object);
                            GlobalValues.TMP_LIST_DOCTORES.remove(index+1);
                            return true;
                        }else
                            return false;
                    }
                        
                }
                GlobalValues.TMP_LIST_DOCTORES.add((Doctor)object);
            }
            if(object instanceof Oficina){
                for (Oficina temp : GlobalValues.TMP_LIST_OFICINAS) {
                    if(temp.getId() == ((Oficina)object).getId()){
                        if(!Cmp.localIsNewOrEqual(temp.getLastUpdate(), ((Oficina)object).getLastUpdate())){
                            int index = GlobalValues.TMP_LIST_OFICINAS.indexOf(temp);
                            GlobalValues.TMP_LIST_OFICINAS.add(index, (Oficina)object);
                            GlobalValues.TMP_LIST_OFICINAS.remove(index+1);
                            return true;
                        }else
                            return false;
                    }
                        
                }
                GlobalValues.TMP_LIST_OFICINAS.add((Oficina)object);
            }
        } 
        return true;
    }

    @Override
    public boolean update(Object objectParam) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getMaxId(Object type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Object> listar(String idParam, Object type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Object> listar(Date param, Object type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean exist(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /**
     * @param cod 
     * Cliente=>rut,
     * Cristal=>nombre,
     * Descuento=>nombre,
     * Doctor=>rut,
     * Lente=>cod,
     * Oficina=>nombre,
     * RegistroBaja=>cod,
     * User=>username
     * @param id
     * Institucion=>id,
     * TipoPago=>id
     * @param type
     * Tipo de clase que se desea retornar
     * @return Retorna la clase de tipo Object, luego sólo se debe parsear.
     */
    @Override
    public Object getElement(String cod, int id, Object type) {
        Log.setLog(className,Log.getReg());
        if(type instanceof Cliente){
            for (Cliente object : GlobalValues.TMP_LIST_CLIENTES) {
                if((object.getCod().toLowerCase()).equals(cod.toLowerCase()))
                    return object;
            }
        }
        if(type instanceof Cristal){
            for (Cristal object : GlobalValues.TMP_LIST_CRISTAL) {
                if((object.getNombre().toLowerCase()).equals(cod.toLowerCase()))
                    return object;
            }
        }
        if(type instanceof Descuento){
            for (Descuento object : GlobalValues.TMP_LIST_DESCUENTO) {
                if((object.getNombre().toLowerCase()).equals(cod.toLowerCase()))
                    return object;
            }
        }
        if(type instanceof Doctor){
            for (Doctor object : GlobalValues.TMP_LIST_DOCTORES) {
                if((object.getCod().toLowerCase()).equals(cod.toLowerCase()))
                    return object;
            }
        }
        if(type instanceof Institucion){
            for (Institucion object : GlobalValues.TMP_LIST_INSTITUCIONES) {
                if(object.getId() == id)
                    return object;
            }
        }
        if(type instanceof Lente){
            for (Lente object : GlobalValues.TMP_LIST_LENTES) {
                if((object.getCod().toLowerCase()).equals(cod.toLowerCase()))
                    return object;
            }
        }
        if(type instanceof Oficina){
            for (Oficina object : GlobalValues.TMP_LIST_OFICINAS) {
                if(object.getNombre().toLowerCase().equals(cod.toLowerCase()))
                    return object;
            }
        }
        if(type instanceof RegistroBaja){
            for (RegistroBaja object : GlobalValues.TMP_LIST_REGISTROS_BAJAS) {
                if(object.getCod().toLowerCase().equals(cod.toLowerCase()))
                    return object;
            }
        }
        if(type instanceof TipoPago){
            for (TipoPago object : GlobalValues.TMP_LIST_TIPOS_PAGO) {
                if(object.getId() == id)
                    return object;
            }
        }
        if(type instanceof User){
            for (User object : GlobalValues.TMP_LIST_USERS) {
                if((object.getUsername().toLowerCase()).equals(cod.toLowerCase()))
                    return object;
            }
        }
        return null;
    }
}
