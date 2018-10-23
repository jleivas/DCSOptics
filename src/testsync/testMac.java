/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testsync;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

/**
 *
 * @author jlleivas
 */
public class testMac {
    public static String getMacAddress() throws IOException {
        String os = System.getProperty("os.name");
        if (os.startsWith("Windows")) {
        return WindowParserMac(windowsRunIpConfigCommand());
        } else if (os.startsWith("Linux")) {
        return linuxParseMacAddress(linuxRunIfConfigCommand());
        } else {
        throw new IOException("Sistema operativo desconocido: " + os);
        }
    }
    private final static String linuxParseMacAddress(String ipConfigResponse) {
        StringTokenizer tokenizer = new StringTokenizer(ipConfigResponse, "\n");
        String line = null;
        String[] lastline = null;

        while (tokenizer.hasMoreTokens()) {
            line = tokenizer.nextToken().trim();
            if (line.contains("HWaddr")) {
                lastline = line.split("HWaddr");
                String[] tmp = lastline[1].trim().split(":");
                if (lastline[1].trim().length() == 17 && tmp.length == 6) {
                    return lastline[1];
                }
            }
        }
        return "MAC no Válida";
    }

    private final static String linuxRunIfConfigCommand() throws IOException {
        Process p = Runtime.getRuntime().exec("ifconfig");
        InputStream stdoutStream = new BufferedInputStream(p.getInputStream());

        StringBuffer buffer = new StringBuffer();
        for (;;) {
            int c = stdoutStream.read();
            if (c == -1) {
                break;
            }
            buffer.append((char) c);
        }
        String outputText = buffer.toString();

        stdoutStream.close();

        return outputText;
    }

//WINDOWS—————————————————————————–
    private final static String windowsRunIpConfigCommand() throws IOException {
        Process p = Runtime.getRuntime().exec("ipconfig /all");
        InputStream stdoutStream = new BufferedInputStream(p.getInputStream());
        StringBuffer buffer = new StringBuffer();
        for (;;) {
            int c = stdoutStream.read();
            if (c == -1) {
                break;
            }
            buffer.append((char) c);
        }
        String outputText = buffer.toString();
        stdoutStream.close();
        return outputText;
    }

    public final static String WindowParserMac(String ipConfigResponse) throws UnknownHostException {

        StringTokenizer tokenizer = new StringTokenizer(ipConfigResponse, "\n");
        String line = null;
        String[] lastline = null;
        while (tokenizer.hasMoreTokens()) {
            line = tokenizer.nextToken().trim();
            if (line.contains("-")) {
                lastline = line.split(":");
                String[] tmp = lastline[1].trim().split("-");
                if (lastline[1].trim().length() == 17 && tmp.length == 6) {
                    return lastline[1];
                }
            }
        }
        return "MAC no Válida";
    }
}
