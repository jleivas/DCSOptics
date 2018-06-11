/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn;

/**
 *
 * @author jorge
 */
public class Log {
    public static String getReg(){
        return "::"+new Exception().getStackTrace()[1].getMethodName();
    }

    public static void setLog(String className, String reg) {
        if(GlobalValues.MAIL_LOG.length() < 2)
            GlobalValues.MAIL_LOG = "Registro del sistema:\n"+className+reg;
        else
            GlobalValues.MAIL_LOG = GlobalValues.MAIL_LOG+"\n"+className+reg;
    }

    public static String getLog() {
        if(GlobalValues.MAIL_LOG.length() > 100)
            return "Registro del sistema:\n..."+GlobalValues.MAIL_LOG.substring(50);
        return GlobalValues.MAIL_LOG;
    }
}
