/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn;

import fn.compress.ZipFiles;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 *
 * @author jorge
 */
public class OnLineOperation {
    public static boolean downloadFileUpdate(String url1, String url2) throws MalformedURLException, IOException {
        System.out.println("ONLINEOPERATION:downloadFileUpdate(String url1, String url2)");
        String fileName = GlobalValues.PROJECTNAME;
        try{
            URL website = new URL(url1);
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream(fileName+".jar");
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            URL website2 = new URL(url2);
            ReadableByteChannel rbc2 = Channels.newChannel(website2.openStream());
            FileOutputStream fos2 = new FileOutputStream(fileName+".zip");
            fos.getChannel().transferFrom(rbc2, 0, Long.MAX_VALUE);
            ZipFiles.unZip(GlobalValues.LOCAL_PATH,fileName+".zip",fileName+".exe");
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
