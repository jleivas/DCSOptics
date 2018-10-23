/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn.globalValues;

import java.awt.Cursor;
import java.awt.Frame;
import javax.swing.JPanel;

/**
 *
 * @author jlleivas
 */
public class GlobalValuesCursor {
    private static boolean CURSOR_DEFAULT = true;
    
    public static void cursorDF(){
        Frame.getFrames()[0].setCursor(Cursor.getDefaultCursor());
        CURSOR_DEFAULT = true;
    }
    
    public static void cursorWAIT(){
        Frame.getFrames()[0].setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        CURSOR_DEFAULT = false;
    }
    
    public static void cursorDF(JPanel panel){
        panel.setCursor(Cursor.getDefaultCursor());
        CURSOR_DEFAULT = true;
    }
    
    public static void cursorWAIT(JPanel panel){
        panel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        CURSOR_DEFAULT = false;
    }
}
