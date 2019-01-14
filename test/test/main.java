/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import fn.OptionPane;
import fn.globalValues.GlobalValuesDirectories;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author jorge
 */
public class main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        try {
            File directoryToZip = new File(GlobalValuesDirectories.getFilesPath());
            System.out.println(GlobalValuesDirectories.getFilesPath());
            List<File> fileList = new ArrayList<File>();
            System.out.println("---Getting references to all files in: " + directoryToZip.getCanonicalPath());
            getAllFiles(directoryToZip, fileList);
            directoryToZip = new File(GlobalValuesDirectories.getLocalPath() + File.separator + "DB");
            System.out.println("---Getting references to all files in: " + directoryToZip.getCanonicalPath());
            getAllFiles(directoryToZip, fileList);
            String pathBackUpZip = GlobalValuesDirectories.getLocalPath() + File.separator + "backUpFiles";
            File backUpFileZip = new File(pathBackUpZip);
            System.out.println("---Creating zip file");
            writeZipFile(backUpFileZip, fileList);
            System.out.println("---Done");

        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void getAllFiles(File dir, List<File> fileList) {
        try {
            File[] files = dir.listFiles();
            for (File file : files) {
                fileList.add(file);
                if (file.isDirectory()) {
                    System.out.println("directory:" + file.getCanonicalPath());
                    getAllFiles(file, fileList);
                } else {
                    System.out.println("     file:" + file.getCanonicalPath());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create a new directorie in the local path and return them, else return
     * null
     *
     * @param folderName
     * @return File or nulls
     */
    public static File createFolder(String folderName) {
        String path = GlobalValuesDirectories.getLocalPath() + File.separator + folderName;
        new File(path).mkdirs();
        return new File(path);
    }

    public static void writeZipFile(File directoryToZip, List<File> fileList) {

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
        String zipFilePath = file.getPath();//getCanonicalPath().substring(directoryToZip.getCanonicalPath().length() + 1,
                //file.getCanonicalPath().length());
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
