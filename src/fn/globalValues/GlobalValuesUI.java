/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn.globalValues;

import javax.swing.JOptionPane;
import view.opanel.MPanel;
import view.opanel.OPanel;

/**
 *
 * @author jlleivas
 */
public class GlobalValuesUI {
    /* Joption Pane del sistema */
    public static MPanel INFOPANEL = new MPanel();
    public static OPanel OPTIONPANEL = new OPanel();
    public static String PANELTITLE ="";
    public static int MSG_STATUS=JOptionPane.ABORT;
    public static String ICON_INFO = "/icons/show_info_50px.png";
    public static String ICON_WARN = "/icons/show_warning_50px.png";
    public static String ICON_ERROR = "/icons/show_error_50px.png";
    
    public static int getMsgStatus(){
        return MSG_STATUS;
    }
    
    public static void setMsgStatus(int statusMsg){
        switch(statusMsg){
            case 1:
                MSG_STATUS = (MSG_STATUS!=JOptionPane.WARNING_MESSAGE && MSG_STATUS!=JOptionPane.ERROR_MESSAGE)?JOptionPane.INFORMATION_MESSAGE:MSG_STATUS;
                break;
            case 2: 
                MSG_STATUS = (MSG_STATUS!=JOptionPane.ERROR_MESSAGE)?JOptionPane.WARNING_MESSAGE:MSG_STATUS;
                break;
            case 3:
                MSG_STATUS = JOptionPane.ERROR_MESSAGE;
                break;
            default:
                MSG_STATUS = JOptionPane.ABORT;
                break;
        }
    }
    
    public static String iconInfo(){
        return ICON_INFO;
    }
    
    public static String iconWarn(){
        return ICON_WARN;
    }
    
    public static String iconError(){
        return ICON_ERROR;
    }
    
    public static OPanel opanel(){
        return OPTIONPANEL;
    }
    
    public static MPanel mpanel(){
        return INFOPANEL;
    }
}
