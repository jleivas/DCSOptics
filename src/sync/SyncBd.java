/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sync;

/**
 *
 * @author sdx
 */
public interface SyncBd {
     public boolean add(Object object);
     public Object get(String objectId);
     public Object get(int objectId);
     public boolean addIndex(Object oldObject,Object newObject);
}
