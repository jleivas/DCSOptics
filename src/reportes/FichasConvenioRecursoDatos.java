/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;

import entities.Convenio;
import entities.context.ConvenioJasperReport;
import entities.ficha.Ficha;
import fn.GV;
import fn.OptionPane;
import fn.globalValues.GlobalValuesVariables;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author sdx
 */
public class FichasConvenioRecursoDatos implements JRDataSource{
    private ConvenioJasperReport rp = null;
    private int currentIndex = -1;
    @Override
    public boolean next() throws JRException {
        return ++currentIndex < rp.getFichas().size();
    }
    
    public boolean addConvenio(Convenio convenio, String reportTitle, String reportSubtitle){
        if(convenio == null || convenio.getEstado() < 2){
            return false;
        }
        try {
            rp = new ConvenioJasperReport(convenio, reportTitle, reportSubtitle);
            rp.setReceptor(GlobalValuesVariables.getReceptorName(), 
                    GlobalValuesVariables.getReceptorDir(), 
                    GlobalValuesVariables.getReceptorCT1(), 
                    GlobalValuesVariables.getReceptorCT2());
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(FichasConvenioRecursoDatos.class.getName()).log(Level.SEVERE, null, ex);
            OptionPane.showMsg("Error en addConvenio(param1,param2,param3)", "Ha ocurrido un error inesperado\n"
                    + "al intentar cargar valores desde la Base de datos local", 3);
            rp = null;
            return false;
        }
        return true;
    }
    
    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        if(rp != null){
            if("companyName".equals(jrf.getName())){
                valor = rp.getCompanyName();
            }
            if("web".equals(jrf.getName())){
                valor = rp.getWeb();
            }
            if("dir".equals(jrf.getName())){
                valor = rp.getDir();
            }
            if("companyContacts".equals(jrf.getName())){
                valor = rp.getCompanyContacts();
            }
            if("idConvenio".equals(jrf.getName())){
                valor = rp.getConvenio().getId();
            }
            if("nextFechaCobro".equals(jrf.getName())){
                valor = GV.dateToString(rp.getNextFechaCobro(), "dd-mm-yyyy");
            }
            if("instName".equals(jrf.getName())){
                valor = rp.getCliente().getNombre();
            }
            if("instRut".equals(jrf.getName())){
                valor = rp.getCliente().getCod();
            }
            if("instMail".equals(jrf.getName())){
                valor = rp.getCliente().getEmail();
            }
            if("instPhone".equals(jrf.getName())){
                valor = rp.getCliente().getTelefono();
            }
            if("montoTotal".equals(jrf.getName())){
                valor = GV.strToPrice(rp.getResumen().getMontoTotal());
            }
            if("cuotas".equals(jrf.getName())){
                valor = rp.getResumen().getCuotas();
            }
            if("valorCuota".equals(jrf.getName())){
                valor = 0;
            }
            if("totalAbono".equals(jrf.getName())){
                valor = GV.strToPrice(rp.getResumen().getTotalAbono());
            }
            if("totalPendiente".equals(jrf.getName())){
                valor = GV.strToPrice(rp.getResumen().getTotalPendiente());
            }
            if("diasVencimiento".equals(jrf.getName())){
                valor = rp.getResumen().getDiasVencimiento();
            }
            if("item".equals(jrf.getName())){
                valor = currentIndex+1;
            }
            if("rut".equals(jrf.getName())){
                valor = ((Ficha)rp.getFichas().get(currentIndex)).getCliente().getCod();
            }
            if("nombre".equals(jrf.getName())){
                valor = ((Ficha)rp.getFichas().get(currentIndex)).getCliente().getNombre();
            }
            if("telefono".equals(jrf.getName())){
                valor = ((Ficha)rp.getFichas().get(currentIndex)).getCliente().getTelefono1();
            }
            if("total".equals(jrf.getName())){
                valor = GV.strToPrice((((Ficha)rp.getFichas().get(currentIndex)).getValorTotal()-((Ficha)rp.getFichas().get(currentIndex)).getDescuento()));
            }if("abono".equals(jrf.getName())){
                valor = GV.strToPrice((((Ficha)rp.getFichas().get(currentIndex)).getValorTotal()-((Ficha)rp.getFichas().get(currentIndex)).getDescuento())-((Ficha)rp.getFichas().get(currentIndex)).getSaldo());
            }
            if("cuota".equals(jrf.getName())){
                valor = GV.strToPrice(GV.roundPrice(((Ficha)rp.getFichas().get(currentIndex)).getSaldo()/rp.getConvenio().getCuotas()));
            }
            if("re_name".equals(jrf.getName())){
                valor = rp.getReceptor().getName();
            }
            if("re_dir".equals(jrf.getName())){
                valor = rp.getReceptor().getDir();
            }
            if("re_ct1".equals(jrf.getName())){
                valor = rp.getReceptor().getCt1();
            }
            if("re_ct2".equals(jrf.getName())){
                valor = rp.getReceptor().getCt2();
            }
        }
        
        return valor;
    }
    
}