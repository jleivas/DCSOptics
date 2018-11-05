/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.init;

import entities.User;
import fn.Boton;
import fn.GV;
import fn.Icons;
import fn.OptionPane;
import fn.globalValues.GlobalValuesFunctions;
import fn.globalValues.GlobalValuesVariables;
import fn.globalValues.GlobalValuesXmlFiles;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.ContentAdmin;
import viewMac.ContentAdminMac;

/**
 *
 * @author Lenovo G470
 */
public class Acceso extends javax.swing.JFrame {

    Boton boton = new Boton();
    String title = GV.projectName()+" "+GV.version();
    /**
     * Creates new form Acceso
     */
    
    public Acceso() {
        initComponents();
        txtUser.setText(GV.username());
        int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
//        this.setBounds((ancho / 2) - (this.getWidth() / 2), (alto / 2) - (this.getHeight() / 2), 450, 300);
        this.setLocationRelativeTo(null);
        //Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/logo.png"));
        //setIconImage(icon);
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/icon.png"));
        setIconImage(icon);
        this.setTitle(title);
        this.titulo.setText(title);
        btnHelp.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnHelp = new javax.swing.JLabel();
        btnEnter = new javax.swing.JLabel();
        btnSyncronize17 = new javax.swing.JLabel();
        btnSyncronize16 = new javax.swing.JLabel();
        titulo = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        txtUser = new javax.swing.JTextField();
        logoSDXERP = new javax.swing.JLabel();
        fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Help_25px.png"))); // NOI18N
        btnHelp.setToolTipText("Ayuda");
        btnHelp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHelpMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHelpMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHelpMouseExited(evt);
            }
        });
        getContentPane().add(btnHelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 310, -1, 30));

        btnEnter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Enter_32px.png"))); // NOI18N
        btnEnter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEnterMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEnterMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEnterMouseExited(evt);
            }
        });
        getContentPane().add(btnEnter, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 310, -1, -1));

        btnSyncronize17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_User_Male_32px.png"))); // NOI18N
        btnSyncronize17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSyncronize17MouseClicked(evt);
            }
        });
        getContentPane().add(btnSyncronize17, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, -1, -1));

        btnSyncronize16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Password_32px.png"))); // NOI18N
        btnSyncronize16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSyncronize16MouseClicked(evt);
            }
        });
        getContentPane().add(btnSyncronize16, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 260, -1, -1));

        titulo.setFont(new java.awt.Font("Aharoni", 0, 10)); // NOI18N
        titulo.setForeground(new java.awt.Color(255, 255, 255));
        titulo.setText("Dcs Optics v 4.1.6");
        getContentPane().add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 340, -1, -1));

        txtPass.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        txtPass.setForeground(new java.awt.Color(51, 51, 51));
        txtPass.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPassActionPerformed(evt);
            }
        });
        txtPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPassKeyPressed(evt);
            }
        });
        getContentPane().add(txtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 270, 142, -1));

        txtUser.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        txtUser.setForeground(new java.awt.Color(51, 51, 51));
        txtUser.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(txtUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 230, 142, -1));

        logoSDXERP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icono.png"))); // NOI18N
        getContentPane().add(logoSDXERP, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 190, 220));

        fondo.setBackground(new java.awt.Color(255, 255, 255));
        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondo.jpg"))); // NOI18N
        getContentPane().add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -5, 401, 370));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPassActionPerformed

    private void btnSyncronize16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSyncronize16MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSyncronize16MouseClicked

    private void btnSyncronize17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSyncronize17MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSyncronize17MouseClicked

    private void btnEnterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEnterMouseClicked
        entrar();
    }//GEN-LAST:event_btnEnterMouseClicked

    private void btnEnterMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEnterMouseEntered
        btnEnter.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icons.getEnteredIcon(btnEnter.getIcon().toString()))));
    }//GEN-LAST:event_btnEnterMouseEntered

    private void btnEnterMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEnterMouseExited
        btnEnter.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icons.getExitedIcon(btnEnter.getIcon().toString()))));
    }//GEN-LAST:event_btnEnterMouseExited

    private void txtPassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPassKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            entrar();
        }
    }//GEN-LAST:event_txtPassKeyPressed

    private void btnHelpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHelpMouseClicked
        OptionPane.showMsg("Sugerencia de clave", GlobalValuesFunctions.helpUserPassAccessDenied(), 1);
    }//GEN-LAST:event_btnHelpMouseClicked

    private void btnHelpMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHelpMouseEntered
        btnHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icons.getEnteredIcon(btnHelp.getIcon().toString()))));
    }//GEN-LAST:event_btnHelpMouseEntered

    private void btnHelpMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHelpMouseExited
        btnHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icons.getExitedIcon(btnHelp.getIcon().toString()))));
    }//GEN-LAST:event_btnHelpMouseExited

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Acceso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Acceso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Acceso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Acceso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Acceso().setVisible(true);
            }
        });
    }
            
    private void entrar(){
        if(GlobalValuesVariables.getIntentosAcceso() >= 2){
            btnHelp.setVisible(true);
        }
        String user = txtUser.getText();
        String pass = txtPass.getText();
        User usu;
            usu=GV.validar(user,pass);
            if(usu!=null){
                try {
                    this.setVisible(false);
                    GV.username(usu.getUsername());
                    GV.setUser(usu);
                    GlobalValuesXmlFiles.crearRegistroLocal();
                    ContentAdmin principalAdmin;
                    ContentAdminMac principalAdminMac;
                    if(GV.isWindowsOs()){
                        principalAdmin = new ContentAdmin();
                        principalAdmin.setVisible(true);
                    }else{
                        principalAdminMac = new ContentAdminMac();
                        principalAdminMac.setVisible(true);
                    }
                    
                    this.dispose();
                } catch (SQLException | ClassNotFoundException ex) {
                    try {
                        Logger.getLogger(Acceso.class.getName()).log(Level.SEVERE, null, ex);
                        OptionPane.showMsg("Error inesperado", "Ha ocurrido un error interno al iniciar el sistema", 3);
                        Thread.sleep(5000);
                        this.dispose();
                    } catch (InterruptedException ex1) {
                        Logger.getLogger(Acceso.class.getName()).log(Level.SEVERE, null, ex1);
                        OptionPane.showMsg("Error inesperado", "Ha ocurrido un error interno al iniciar el sistema:\n"
                                + ex.getMessage(), 3);
                    }
                }
            }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnEnter;
    private javax.swing.JLabel btnHelp;
    private javax.swing.JLabel btnSyncronize16;
    private javax.swing.JLabel btnSyncronize17;
    private javax.swing.JLabel fondo;
    private javax.swing.JLabel logoSDXERP;
    private javax.swing.JLabel titulo;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
