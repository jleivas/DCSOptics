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
import java.util.zip.ZipOutputStream;

/**
 *
 * @author jlleivas
 */
public class GlobalValuesZipFiles {
    File zip;
    private static ZipOutputStream output;

    public static void zipperBackup(){
        boolean error = false;
        try {
            Zipper z = new Zipper(new File(GV.directoryFilesPath()+"rsp.zip"));
            z.zip(new File("."+File.separator+"files"+File.separator+"RSP"));
        } catch (FileNotFoundException e) {
            error = true;
            e.printStackTrace();
        } catch (IOException e) {
            error = true;
            e.printStackTrace();
        }
        if(!error && GV.isOnline()){
            Send backUp = new Send();
            backUp.sendFileMail(GV.directoryFilesPath()+"rsp.zip");
        }
    }
}
