/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;

import entities.context.SalesFichaJasperReport;
import fn.GV;
import fn.OptionPane;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author jlleivas
 */
public class SalesFichaRecursoDatos  implements JRDataSource{
    private SalesFichaJasperReport sf;
    private int currentIndex = -1;
    public static int columns = 4;
    
    public static int indexVendedor = 0;
    public static int indexTotalVentas = 1;
    public static int indexNombreAbono = 2;
    public static int indexTotalAbonado = 3;
    
    private String[][] resumen;

    @Override
    public boolean next() throws JRException {
        return ++currentIndex < resumen.length;
    }

    public boolean noGenerated(){
        return (sf.getFilas() == 0);
    }
    
    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        if(sf != null){
            if("companyName".equals(jrf.getName())){
                valor = sf.getCompanyName();
            }
            if("web".equals(jrf.getName())){
                valor = sf.getWeb();
            }
            if("dir".equals(jrf.getName())){
                valor = sf.getDir();
            }
            if("companyContacts".equals(jrf.getName())){
                valor = sf.getCompanyContacts();
            }
            if("titleFilter".equals(jrf.getName())){
                valor = sf.getTitleFilter();
            }
            if("userFullName".equals(jrf.getName())){
                valor = GV.user().getNombre();
            }
            if("montoTotal".equals(jrf.getName())){
                valor = GV.strToPrice(sf.getMontoTotal());
            }
            if("totalAbono".equals(jrf.getName())){
                valor = GV.strToPrice(sf.getMontoTotalAbonado());
            }
            if("totalPendiente".equals(jrf.getName())){
                valor = GV.strToPrice(sf.getMontoTotalPendiente());
            }
            if("vendedor".equals(jrf.getName())){
                valor = resumen[currentIndex][indexVendedor];
            }
            if("totalVentas".equals(jrf.getName())){
                String precio = GV.strToPrice(GV.strToNumber(resumen[currentIndex][indexTotalVentas]));
                valor = (precio.equals("$ 0"))?"":precio;
            }
            if("nombreAbono".equals(jrf.getName())){
                valor = resumen[currentIndex][indexNombreAbono];
            }
            if("totalAbonado".equals(jrf.getName())){
                valor = GV.strToPrice(GV.strToNumber(resumen[currentIndex][indexTotalAbonado]));
            }
        }
        
        return valor;
    }
    
    public void createReport(List<Object> fichas,String titleFilter,String companyName,
                                String web,String dir,String companyContacts){
        sf = new SalesFichaJasperReport(fichas, titleFilter, companyName, web, dir, companyContacts);
        if(sf.getFilas() == 0){
            OptionPane.showMsg("No se pudo generar el reporte", "No existen montos válidos para calcular", 2);
            return;
        }
        resumen  = new String[sf.getFilas()][columns];
        int i = 0;
        int header = 0;
        for (SalesFichaJasperReport.Vendedor vendedor : sf.getVendedores()) {
            resumen[i][indexVendedor]=vendedor.getFullName();
            resumen[i][indexTotalVentas]=""+vendedor.getTotalVentas();
            header = i;
            for (SalesFichaJasperReport.DetalleVentas detalle : vendedor.getDetalle()) {
                if(i > header){
                    resumen[i][indexVendedor]=vendedor.getFullName();
                    resumen[i][indexTotalVentas]="";
                }
                resumen[i][indexNombreAbono]=detalle.getDetalle();
                resumen[i][indexTotalAbonado]=""+detalle.getMonto();
                i++;
            }
        }
        int j = 0;
        for (i = i; i < sf.getFilas(); i++) {
            resumen[i] = sf.getResumenAbonos()[j];
            j++;
        }
    }
}
