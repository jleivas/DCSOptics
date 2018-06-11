/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sync;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author sdx
 */
public interface InterfaceSync {
     public boolean add(Object objectParam);
     public boolean update(Object objectParam);
     public int getMaxId(Object type);
     public ArrayList<Object> listar(String idParam, Object type);
     public ArrayList<Object> listar(Date param, Object type);
     public Object getElement(String cod,int id, Object type);
     public boolean exist(Object object);
}
