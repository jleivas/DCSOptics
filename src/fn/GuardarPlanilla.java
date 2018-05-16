/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.WorkbookSettings;
import jxl.Workbook;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 *
 * @author Lenovo G470
 */
public class GuardarPlanilla {
    public void generarExcel(String[][] entrada, String ruta){
        try {
            WorkbookSettings conf = new WorkbookSettings();
            conf.setEncoding("ISO-8859-1");
        
            WritableWorkbook woorbook = Workbook.createWorkbook(new File(ruta),conf);
            
            WritableSheet sheet = woorbook.createSheet("Resultado", 0);
            
            WritableFont h = new WritableFont(WritableFont.TIMES, 10,WritableFont.NO_BOLD);
            WritableCellFormat hformat = new WritableCellFormat(h);
            
            WritableFont h2 = new WritableFont(WritableFont.TIMES, 12,WritableFont.BOLD);
            WritableCellFormat hformat2 = new WritableCellFormat(h2);
            
            for(int i =0; i < entrada.length; i++){//filas
                for(int j=0; j < entrada[i].length;j++){//columnas
                    try {
                        if(i==0 || i==5){
                            sheet.addCell(new jxl.write.Label(j, i, entrada[i][j],hformat2));
                        }else{
                            sheet.addCell(new jxl.write.Label(j, i, entrada[i][j],hformat));
                        }
                        
                    } catch (WriteException ex) {
                        Logger.getLogger(GuardarPlanilla.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            
            woorbook.write();
            try {
                woorbook.close();
            } catch (WriteException ex) {
                Logger.getLogger(GuardarPlanilla.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(GuardarPlanilla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
