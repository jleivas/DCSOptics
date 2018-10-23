package fn.print;


import java.io.File;
import javax.print.Doc;
import java.io.IOException;
import javax.print.DocFlavor;
import javax.print.SimpleDoc;
import java.io.FileInputStream;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.swing.JFileChooser;
import javax.print.PrintException;
import javax.print.PrintServiceLookup;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.print.attribute.HashPrintRequestAttributeSet;


/**
 * @web http://www.jc-mouse.net
 * @author Mouse
 */
public class jcPrint {

    private JFileChooser fileChooser;
    private FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagen","jpg","gif","png");
    private FileInputStream fileInputStream = null;
    private File file=null;

   public jcPrint(){}

   public boolean Abrir()
    {
     fileChooser = new JFileChooser();
       fileChooser.setFileFilter(filter);       
       int result = fileChooser.showOpenDialog(null);
       if ( result == JFileChooser.APPROVE_OPTION ){
            try {
                this.fileInputStream = new FileInputStream( fileChooser.getSelectedFile() );
                this.file = fileChooser.getSelectedFile();
                return true;
            } catch (IOException ex) {
               System.out.println("Error al abrir archivo " + ex);
            }
        }
       return false;
    }

   public String getPathImage()
   {
        return this.file.toString();
   }

   //funcion que imprime una imagen almacenda en "fileInputStream"
    public void Imprimir()
    {
        if( this.fileInputStream != null )
        {
        try {
            
            //atributos de la impresora
            PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
            pras.add(new Copies(1));   
            //Impresora configurada del sistema
            PrintService ps = PrintServiceLookup.lookupDefaultPrintService();

            DocPrintJob docPrintJob = ps.createPrintJob();
            
            Doc doc = new SimpleDoc(fileInputStream, DocFlavor.INPUT_STREAM.GIF, null);
            docPrintJob.print(doc, pras);
            fileInputStream.close();
        } catch (PrintException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }  finally {
            try {
                fileInputStream.close();
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
        }
    }
}