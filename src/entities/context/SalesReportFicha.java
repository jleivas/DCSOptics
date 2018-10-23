/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.context;


import entities.ficha.Ficha;
import fn.GV;
import fn.OptionPane;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jlleivas
 */
public class SalesReportFicha {
    private class srTipoPago{
        private String name;
        private int monto;
        
        public srTipoPago(String name, int monto) {
            this.name = name;
            this.monto = monto;
        }
    }
    private int numVentas;
    private int montoVentas;
    private int montoAbonos;
    private int numLentes;
    private List<srTipoPago> listAbonos = new ArrayList<>();

    public SalesReportFicha() {
    }
    
    public SalesReportFicha(List<Object> lista) {
        for (Object object : lista) {
            if(object instanceof Ficha){
                if(((Ficha)object).getEstado() != 0){
                    Ficha ficha = (Ficha)object;
                    sumNumVentas();
                    if((ficha.getCerca()!=null) && !ficha.getCerca().getMarca().isEmpty())sumNumLentes();
                    if((ficha.getLejos()!=null) && !ficha.getLejos().getMarca().isEmpty())sumNumLentes();
                    int valTotal = ficha.getValorTotal()-ficha.getDescuento();
                    sumMontoVentas(valTotal);
                    sumMontoAbonos(valTotal-ficha.getSaldo());
                    addAbonos(GV.abonosListArrayFromFicha(ficha.getCod()));
                    validateAbonos(ficha.getCod());
                }
            }else{
                OptionPane.showMsg("Error de construcción", "No se pudo construir la entidad,\n"
                        + "verifique que la lista recibida por parámetros sea una instancia de la clase Ficha", 2);
            }
        }
    }
    
    public void addAbonos(Object[][] lAbonos){
        if(lAbonos != null){
            for (int i = 0; i < lAbonos.length; i++) {
                boolean create = true;
                for (srTipoPago util : listAbonos) {
                    if(util.name.equals(GV.abonosName(i, lAbonos))){
                        util.monto = util.monto + GV.abonosMonto(i, lAbonos);
                        create=false;
                    }
                }
                if(create){
                    listAbonos.add(new srTipoPago(GV.abonosName(i, lAbonos), GV.abonosMonto(i, lAbonos)));
                }
            }
        }
    }

    public void sumNumLentes(){
        setNumLentes(this.numLentes+1);
    }
    public void setNumLentes(int numLentes) {
        this.numLentes = numLentes;
    }

    public int getNumLentes() {
        return numLentes;
    }

    public void setMontoAbonos(int abonos) {
        this.montoAbonos = abonos;
    }

    public int getMontoAbonos() {
        return montoAbonos;
    }
    
    public void sumMontoAbonos(int cantidad){
        setMontoAbonos(this.montoAbonos+cantidad);
    }

    public void setMontoVentas(int montoVentas) {
        this.montoVentas = montoVentas;
    }

    public int getMontoVentas() {
        return montoVentas;
    }
    
    public void sumMontoVentas(int cantidad){
        setMontoVentas(this.montoVentas+cantidad);
    }

    public int getNumVentas() {
        return numVentas;
    }

    public void setNumVentas(int numVentas) {
        this.numVentas = numVentas;
    }
    
    public void sumNumVentas(){
        setNumVentas(this.numVentas+1);
    }
    
    private void validateAbonos(String idFicha){
        int abonos = 0;
        for (srTipoPago listAbono : listAbonos) {
            abonos = abonos + listAbono.monto;
        }
        if(abonos != getMontoAbonos()){
            OptionPane.showMsg("Debe revisar ventas", "Solicite una auditoría con su proveedor de software\n"
                    + "Ficha contiene errores: COD["+idFicha+"].", 3);
        }
    }
    
    public String toString(){
        DecimalFormat formateador = new DecimalFormat("###,###,###");
        String detalle = "";
        for (srTipoPago listAbono : listAbonos) {
            detalle = detalle + "ABONOS         " +GV.strToPrice(listAbono.monto)+"    ("+listAbono.name+")\n";
        }
        return  "------------------------------------------------ \n"+
                
                "CANTIDAD VENTAS   : " +formateador.format(getNumVentas())+"\n"+
                "LENTES VENDIDOS: "+formateador.format(getNumLentes())+"     \n"+
                "MONTO TOTAL VENTAS: " +GV.strToPrice(getMontoVentas())+"\n"+
                "------------------------------------------------\n"+
                "DETALLE DE ABONOS:\n"+
                "------------------------------------------------\n\n"+
                detalle
                +
                "------------------------------------------------ \n"+
                "TOTAL ABONOS   "+GV.strToPrice(getMontoAbonos())+"  \n\n";
    }
    
    public String toHtml(String title){
        DecimalFormat formateador = new DecimalFormat("###,###,###");
        String detalle = "";
        for (srTipoPago listAbono : listAbonos) {
            detalle = detalle + "<TR>\n" +
                    "                <TD>"+listAbono.name+"</TD> <TD style=\"text-align: right;\">" +GV.strToPrice(listAbono.monto)+"</TD>\n" +
                    "            </TR>\n";
        }
        return  "<p>"+title+"</p>\n" +
                "<TABLE BORDER CELLPADDING=10 CELLSPACING=0>\n" +
                "	<TR style=\"background-color: #D1F4EF;\">\n" +
                "		<TD><strong>Cantidad de ventas</strong></TD> <TD style=\"text-align: right;\"><strong>" +formateador.format(getNumVentas())+"</strong></TD>\n" +
                "    </TR>\n" +
                "    <TR style=\"background-color: #D1F4EF;\">\n" +
                "		<TD><strong>Lentes vendidos</strong></TD> <TD style=\"text-align: right;\"><strong>"+formateador.format(getNumLentes())+"</strong></TD>\n" +
                "    </TR>\n" +
                "    <TR style=\"background-color: #D1F4EF;\">\n" +
                "		<TD><strong>Monto total de ventas</strong></TD> <TD style=\"text-align: right;\"><strong>" +GV.strToPrice(getMontoVentas())+"</strong></TD>\n" +
                "	</TR>\n" +
                "	<TR style=\"background-color: #dddddd;\">\n" +
                "		<TH>Medio</TH> <TH>Abonos</TH>\n" +
                "	</TR>\n" +
                detalle+
                "    <TR style=\"background-color: #dddddd;\">\n" +
                "		<TD><strong>Total Abonos</strong></TD> <TD style=\"text-align: right;\"><strong>"+GV.strToPrice(getMontoAbonos())+"</strong></TD>\n" +
                "	</TR>\n" +
                "</TABLE>"
                + "<style>\n" +
                "table {\n" +
                "    font-family: arial, sans-serif;\n" +
                "    border-collapse: collapse;\n" +
                "    width: 100%;\n" +
                "}\n" +
                "\n" +
                "</style>";
    }
}
