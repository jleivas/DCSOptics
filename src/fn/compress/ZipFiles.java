/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn.compress;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipInputStream;

/**
 *
 * @author jorge
 */
public class ZipFiles {
    public static void unZip(String uriPath, String archivoZIP, String archivoDescomprimido) {
		int BUFFER_SIZE = 1024;
		BufferedOutputStream bos = null;
		FileInputStream fis = null;
		ZipInputStream zipis = null;
		FileOutputStream fos = null;

		try {
			fis = new FileInputStream(uriPath+archivoZIP);
			zipis = new ZipInputStream(new BufferedInputStream(fis));
			if (zipis.getNextEntry() != null) {
				int len = 0;
				byte[] buffer = new byte[BUFFER_SIZE];
				fos = new FileOutputStream(uriPath+archivoDescomprimido);
				bos = new BufferedOutputStream(fos, BUFFER_SIZE);

				while ((len = zipis.read(buffer, 0, BUFFER_SIZE)) != -1)
					bos.write(buffer, 0, len);
				bos.flush();
			} else {
				throw new Exception("Zip Vacio");
			}
		} catch (Exception e) {
			System.out.println("Ocurrió un error al descomprimir");
		} finally {
			try{
				bos.close();
				zipis.close();
				fos.close();
				fis.close();
			}
			catch(Exception e){}
		}
   }
}
