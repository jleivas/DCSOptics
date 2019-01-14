/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn.globalValues;

import fn.GV;
import fn.mail.Send;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author jlleivas
 */
public class GlobalValuesZipFiles {
    File zip;

    public static void zipperBackup(){
        boolean error = false;
        String equipo = GV.equipo().substring(0,GV.equipo().indexOf("_"));
        String pathBackUpZip = GlobalValuesDirectories.getLocalPath() + File.separator + "backUpFiles_"+equipo;
        File backUpFileZip = new File(pathBackUpZip);
        try {
            File directoryToZip = new File(GlobalValuesDirectories.getFilesPath());
            List<File> fileList = new ArrayList<File>();
            getAllFiles(directoryToZip, fileList);
            directoryToZip = new File(GlobalValuesDirectories.getLocalPath() + File.separator + "DB");
            getAllFiles(directoryToZip, fileList);
            
            writeZipFile(backUpFileZip, fileList);

        } catch (Exception ex) {
            error = true;
            System.out.println(ex.getMessage());
            Logger.getLogger(GlobalValuesZipFiles.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(!error && GV.isOnline()){
            Send backUp = new Send();
            System.out.println(backUpFileZip.getPath());
            backUp.sendFileMail(backUpFileZip.getPath()+".zip");
        }
    }
    
    private static void getAllFiles(File dir, List<File> fileList) {
        File[] files = dir.listFiles();
        for (File file : files) {
            fileList.add(file);
            if (file.isDirectory()) {
                getAllFiles(file, fileList);
            }
        }
    }
    
    private static void writeZipFile(File directoryToZip, List<File> fileList) {

        try {
            FileOutputStream fos = new FileOutputStream(directoryToZip.getName() + ".zip");
            ZipOutputStream zos = new ZipOutputStream(fos);

            for (File file : fileList) {
                if (!file.isDirectory()) { // we only zip files, not directories
                    addToZip(directoryToZip, file, zos);
                }
            }

            zos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void addToZip(File directoryToZip, File file, ZipOutputStream zos) throws FileNotFoundException,
            IOException {

        FileInputStream fis = new FileInputStream(file);

        // we want the zipEntry's path to be a relative path that is relative
        // to the directory being zipped, so chop off the rest of the path
        String isFiles = File.separator+"files"+File.separator;
        String isBd = File.separator+"DB"+File.separator;
        String zipFilePath = file.getPath();
        if(file.getPath().contains(isFiles)){
            zipFilePath = zipFilePath.substring(zipFilePath.lastIndexOf(isFiles)+1,zipFilePath.length());
        }
        if(file.getPath().contains(isBd)){
            zipFilePath = zipFilePath.substring(zipFilePath.indexOf(isBd)+1, zipFilePath.length());
        }
        System.out.println(file.getPath()+": Writing '" + zipFilePath + "' to zip file");
        ZipEntry zipEntry = new ZipEntry(zipFilePath);
        zos.putNextEntry(zipEntry);

        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zos.write(bytes, 0, length);
        }

        zos.closeEntry();
        fis.close();
    }

}
