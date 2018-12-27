/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn.globalValues;

import fn.GV;
import fn.Zipper;
import fn.mail.Send;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author jlleivas
 */
public class GlobalValuesZipFiles {
    File zip;

    public static void zipperBackup(){
        boolean error = false;
        String BD_folder = "DB.zip";
        String local_folder = "local.zip";
        String reg_folder = "reg.zip";
        String rsp_folder = GV.dateToString(new Date(), "yyyyMMddhhmm")+"_rsp.zip";
        try {
            Zipper DB = new Zipper(new File(GV.directoryFilesPath()+File.separator+"RSP"+File.separator+BD_folder));
            DB.zip(new File("."+File.separator+"DB"));
            
            Zipper files = new Zipper(new File(GV.directoryFilesPath()+File.separator+"RSP"+File.separator+local_folder));
            files.zip(new File("."+File.separator+"files"+File.separator+"local.xml"));
            
            Zipper reg = new Zipper(new File(GV.directoryFilesPath()+File.separator+"RSP"+File.separator+reg_folder));
            reg.zip(new File("."+File.separator+"files"+File.separator+"reg.xml"));
            
            Zipper rsp = new Zipper(new File(GV.directoryFilesPath()+rsp_folder));
            rsp.zip(new File("."+File.separator+"files"+File.separator+"RSP"));
            
            
        } catch (FileNotFoundException e) {
            error = true;
            e.printStackTrace();
        } catch (IOException e) {
            error = true;
            e.printStackTrace();
        }
        if(!error && GV.isOnline()){
            Send backUp = new Send();
            backUp.sendFileMail(GV.directoryFilesPath()+rsp_folder);
        }
    }
}
