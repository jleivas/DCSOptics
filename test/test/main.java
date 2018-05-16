/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Lente;
import fn.GlobalValues;
import java.sql.SQLException;
import java.util.Date;
import sync.Sync;
import sync.lente.GbVlLente;
import sync.lente.LcBdLente;
import sync.lente.RmBdLente;

/**
 *
 * @author jorge
 */
public class main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        GbVlLente gb = new GbVlLente();
        RmBdLente rm = new RmBdLente();
        LcBdLente lc = new LcBdLente();
        Lente l1 = new Lente("a1", "c0", "t1", "marca", "material", 0, 0, "descripcion", 0, 0, 0, 0, 0, 0, new Date());
        Lente l2 = new Lente("a2", "", null, "marca", "material", 0, 0, "descripcion", 0, 0, 0, 0, 0, 0, new Date());
        Sync.add(lc, rm, gb, l1, l1.getCodigo());
        Sync.add(lc, rm, gb, l2, l2.getCodigo());
        
        for(Lente aux : GlobalValues.TMP_LIST_LENTES){
            System.out.println(aux.toString());
        }
    }
}
