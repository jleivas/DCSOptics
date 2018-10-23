/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;

import dao.Dao;
import entities.Convenio;
import entities.TipoPago;
import entities.context.ConvenioJasperReport;
import fn.GV;
import fn.OptionPane;
import fn.globalValues.GlobalValuesVariables;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author sdx
 */
public class CuotasConvenioRecursoDatos implements JRDataSource{
    private ConvenioJasperReport rp = null;
    private List<Object> listTipoPago = new ArrayList<>();
    private int currentIndex = -1;
    private int saldoTemporal = 0;
    @Override
    public boolean next() throws JRException {
        return ++currentIndex < rp.getConvenio().getCuotasConvenio().size();
    }
    
    public boolean addConvenio(Convenio convenio, String reportTitle, String reportSubtitle){
        try {
            Dao load = new Dao();
            if(convenio !=null || convenio.getEstado() < 2){
                rp = new ConvenioJasperReport(convenio, reportTitle, reportSubtitle);
                rp.setReceptor(GlobalValuesVariables.getReceptorName(), 
                        GlobalValuesVariables.getReceptorDir(), 
                        GlobalValuesVariables.getReceptorCT1(), 
                        GlobalValuesVariables.getReceptorCT2());
                saldoTemporal = rp.getResumen().getMontoTotal();
                listTipoPago = load.listar("0", new TipoPago());
                return true;
            }
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(CuotasConvenioRecursoDatos.class.getName()).log(Level.SEVERE, null, ex);
            OptionPane.showMsg("Error en addConvenio(param1,param2,param3)", "Ha ocurrido un error inesperado\n"
                    + "al intentar cargar valores desde la Base de datos local", 3);
            rp = null;
            return false;
        }
        return false;
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
                valor = "Ingrese rut o dni";
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
            if("valorCuota".equals(jrf.getName())){
                valor = GV.strToPrice(rp.getConvenio().getCuotasConvenio().get(currentIndex).getMonto());
            }
            if("saldoTemporal".equals(jrf.getName())){
                saldoTemporal = saldoTemporal - (rp.getConvenio().getCuotasConvenio().get(currentIndex).getMonto());
                valor = GV.strToPrice(saldoTemporal);
            }
            if("fechaCuota".equals(jrf.getName())){
                valor = GV.dateToString(rp.getConvenio().getCuotasConvenio().get(currentIndex).getFecha(),
                        "dd-mm-yyyy");
            }
            if("estadoCuota".equals(jrf.getName())){
                valor = getCuotaStatus(rp.getConvenio().getCuotasConvenio().get(currentIndex).getEstado());
            }if("fechaPago".equals(jrf.getName())){
                valor = getFechaPago(rp.getConvenio().getCuotasConvenio().get(currentIndex).getFechaPagado());
            }if("medio".equals(jrf.getName())){
                valor = getTipoPago(rp.getConvenio().getCuotasConvenio().get(currentIndex).getIdTipoPago());
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

    private String getCuotaStatus(int estado) {
        if(estado == GV.cuotaConvenioPagada()){
            return "Pagada";
        }
        return "Pendiente";
    }

    private String getFechaPago(Date fechaPagado) {
        if(GV.cuotasFechaPagoPendiente(fechaPagado)){
            return "No pagada";
        }
        return GV.dateToString(fechaPagado, "dd-mm-yyyy");
    }

    private String getTipoPago(int idTipoPago) {
        for (Object object : listTipoPago) {
            if(((TipoPago)object).getId() == idTipoPago){
                return ((TipoPago)object).getNombre();
            }
        }
        return null;
    }
    
}
