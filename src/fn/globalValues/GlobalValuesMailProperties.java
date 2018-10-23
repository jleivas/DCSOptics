/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn.globalValues;

import static fn.GV.getStr;

/**
 *
 * @author jlleivas
 */
public class GlobalValuesMailProperties {
    /* Mail */
    public static String MAIL_ADDRES = "sdx.respaldo.bd@gmail.com";
    public static String MAIL_PASS= "qwpzedzqucvpyjzt";
    public static String MAIL_REPORT= "softdirex@gmail.com";
    public static String MAIL_LOG = "";
    
    public static String getMailSystemName() {
        return getStr(MAIL_ADDRES).toLowerCase();
    }
    
    public static String getMailSystemPass() {
        return getStr(MAIL_PASS);
    }
    
    public static String getMailReport(){
        return getStr(MAIL_REPORT).toLowerCase();
    }
    
    public static String getMailLog(){
        return getStr(MAIL_LOG);
    }
    
    public static void setMailLog(String className, String mailLog){
        className = getStr(className);
        mailLog = getStr(mailLog);
        if(MAIL_LOG.length() < 2)
            MAIL_LOG = "Registro del sistema:\n"+className+mailLog;
        else
            MAIL_LOG = MAIL_LOG+"\n"+className+mailLog;
    }
}
