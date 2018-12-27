/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.opanel;

import entities.User;
import fn.GV;
import fn.Icons;
import fn.OptionPane;
import fn.globalValues.GlobalValuesVariables;

/**
 *
 * @author sdx
 */
public class OpanelReporteLentes extends javax.swing.JPanel {
    private static User stUser = GV.user();
    /**
     * Creates new form OpanelSelectDate
     */
    public OpanelReporteLentes() {
        initComponents();
        loadData();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCancelar = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtReporte = new javax.swing.JTextArea();

        setBackground(new java.awt.Color(255, 255, 255));

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Forward_Message_50px.png"))); // NOI18N
        btnCancelar.setToolTipText("Enviar reporte");
        btnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCancelarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCancelarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCancelarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnCancelarMousePressed(evt);
            }
        });

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/btn_Ok_50px.png"))); // NOI18N
        btnGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGuardarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGuardarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGuardarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnGuardarMousePressed(evt);
            }
        });

        txtReporte.setColumns(20);
        txtReporte.setRows(5);
        jScrollPane1.setViewportView(txtReporte);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCancelar)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardar)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancelar)
                    .addComponent(btnGuardar))
                .addGap(21, 21, 21))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseClicked
        OptionPane.showOptionPanel(new OpanelSelectAdminToSendReporteInventario(), "Enviar reporte de inventario");
    }//GEN-LAST:event_btnCancelarMouseClicked

    private void btnCancelarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseEntered
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icons.getEnteredIcon(btnCancelar.getIcon().toString()))));
    }//GEN-LAST:event_btnCancelarMouseEntered

    private void btnCancelarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseExited
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icons.getExitedIcon(btnCancelar.getIcon().toString()))));
    }//GEN-LAST:event_btnCancelarMouseExited

    private void btnCancelarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarMousePressed

    private void btnGuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMouseClicked
        OptionPane.closeOptionPanel();
    }//GEN-LAST:event_btnGuardarMouseClicked

    private void btnGuardarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMouseEntered
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icons.getEnteredIcon(btnGuardar.getIcon().toString()))));
    }//GEN-LAST:event_btnGuardarMouseEntered

    private void btnGuardarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMouseExited
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icons.getExitedIcon(btnGuardar.getIcon().toString()))));
    }//GEN-LAST:event_btnGuardarMouseExited

    private void btnGuardarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnCancelar;
    private javax.swing.JLabel btnGuardar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtReporte;
    // End of variables declaration//GEN-END:variables

    private void loadData() {
        int compra = GlobalValuesVariables.LENTES_COMPRA;
        int venta = GlobalValuesVariables.LENTES_VENTA;
        String reporte = 
                "REPORTE DE INVENTARIO\n"
                + "-----------------------\n"
                + "Stock total de lentes: "+GlobalValuesVariables.LENTES_STOCK+"\n\n"
                + "Lentes con stock bajo: "+GlobalValuesVariables.LENTES_STOCK_BAJO+"\n"
                + "Lentes con stock en cero: "+GlobalValuesVariables.LENTES_STOCK_CERO+"\n\n";
        String reporteAdmin = 
                "REPORTE MONETARIO\n"
                + "-----------------------\n"
                + "En inventario: "+GV.strToPrice(compra)+"\n"
                + "Monto de venta: "+GV.strToPrice(venta)+"\n"
                + "Retorno :"+GV.strToPrice((venta-compra));
        if(GV.tipoUserSuperAdmin()){
            txtReporte.setText(reporte+reporteAdmin);
        }else{
            txtReporte.setText(reporte);
        }
    }

    
}
