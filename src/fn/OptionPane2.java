/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn;

import java.awt.BorderLayout;
import static view.OPanel.OpanelContent;

/**
 *
 * @author sdx
 */
public class OptionPane2 {
    private static int ancho = 549;
    private static int alto = 220;
    private static int locat = 5;
    
    public static void showPanel(javax.swing.JPanel p1, String title){
        GlobalValues.INFOPANEL.lblTitle.setText(title);
        GlobalValues.INFOPANEL.setVisible(true);
        p1.setSize(ancho, alto);
        p1.setLocation(locat, locat);
        OpanelContent.removeAll();
        OpanelContent.add(p1,BorderLayout.CENTER);
        OpanelContent.revalidate();
        OpanelContent.repaint();
    }

    public void closeOPanel() {
        GlobalValues.INFOPANEL.setVisible(false);
    }
}
