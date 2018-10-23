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
        GV.setMailLog(className,reg);
    }

    public static String getLog() {
        if(GV.mailLog().length() > 100)
            return "Registro del sistema:\n..."+GV.mailLog().substring(50);
        return GV.mailLog();
    }
}
