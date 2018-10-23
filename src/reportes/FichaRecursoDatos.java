/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;

import entities.ficha.Ficha;
import fn.GV;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author sdx
 */
public class FichaRecursoDatos implements JRDataSource{
    private List<Ficha> thisFicha = new ArrayList<Ficha>();
    private String title="Reporte de recetas oftalmologicas";
    private String subtitle = GV.companyName();
    private int currentIndex = -1;
    @Override
    public boolean next() throws JRException {
        return ++currentIndex < thisFicha.size();
    }

    public void addTitle(String title, String subtitle){
        this.title = title;
        this.subtitle = subtitle;
    }
    
    public void addFicha(Ficha ficha){
        this.thisFicha.add(ficha);
    }
    
    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        if("title".equals(jrf.getName())){
            valor = title;
        }
        if("subtitle".equals(jrf.getName())){
            valor = subtitle;
        }
        if("fecha".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getFecha();
        }
        if("folio".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getCod();
        }
        if("rut".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getCliente().getCod();
        }
        if("nombre".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getCliente().getNombre();
        }
        if("direccion".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getCliente().getDireccion();
        }
        if("telefono".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getCliente().getTelefono1();
        }
        if("comuna".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getCliente().getComuna();
        }
        if("ciudad".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getCliente().getCiudad();
        }
        if("precio".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getValorTotal();
        }
        if("descuento".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getDescuento();
        }
        if("total".equals(jrf.getName())){
            valor = (thisFicha.get(currentIndex).getValorTotal()-thisFicha.get(currentIndex).getDescuento());
        }if("abono".equals(jrf.getName())){
            valor = (thisFicha.get(currentIndex).getValorTotal()-thisFicha.get(currentIndex).getDescuento())-thisFicha.get(currentIndex).getSaldo();
        }
        if("saldo".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getSaldo();
        }
        if("vendedor".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getUser().getNombre();
        }
        if("lugar".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getLugarEntrega();
        }
        if("fecha_entrega".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getFechaEntrega();
        }
        
        return valor;
    }
    
}
