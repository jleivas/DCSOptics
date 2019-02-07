/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewMac;

import dao.Dao;
import entities.ficha.Ficha;
import fn.Boton;
import fn.GV;
import javax.swing.table.DefaultTableModel;
import fn.Icons;
import fn.OptionPane;
import fn.globalValues.GlobalValuesFunctions;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author home
 */
public class VFichasMac extends javax.swing.JPanel {
    Boton boton = new Boton();
    Dao load= new Dao();
    
    private static final int COLUMNAS_TABLA = 7;
    TableRowSorter trs;
    DefaultTableModel modelo = new DefaultTableModel() {
           @Override
           public boolean isCellEditable(int fila, int columna) {
               return false; //Con esto conseguimos que la tabla no se pueda editar
           }
        };
    /**
     * Creates new form VClientes
     */
    public VFichasMac(int filter) throws SQLException, ClassNotFoundException {
        if(!ContentAdminMac.lblTitle.getText().toLowerCase().contains("ficha")){
            ContentAdminMac.lblTitle.setText("Fichas");
        }
        initComponents();       
        modelo.addColumn("Folio");
        modelo.addColumn("Fecha");
        modelo.addColumn("Rut cliente");
        modelo.addColumn("Nombre cliente");
        modelo.addColumn("Estado");
        modelo.addColumn("Total");
        modelo.addColumn("Vendedor");
        tblListar.setModel(modelo);
        cboFilterOptions.setSelectedIndex(filter);
        cboMostrar.setSelectedIndex(GV.getCboFichasFilterStatus());
        load();
        OptionPane.closeOptionPanel();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cboMostrar = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListar = new javax.swing.JTable();
        btnAbrir = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JLabel();
        btnRestaurar = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        cboFilterOptions = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        btnReloadFilter = new javax.swing.JLabel();
        btnReportSales = new javax.swing.JLabel();
        btnExportFichas = new javax.swing.JLabel();
        btnExportConvenio = new javax.swing.JLabel();
        btnDespacharTodo = new javax.swing.JLabel();
        btnSalesReport = new javax.swing.JLabel();
        btnExportExcel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Lista de registros", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Light", 0, 11))); // NOI18N

        cboMostrar.setFont(new java.awt.Font("Segoe UI Light", 1, 10)); // NOI18N
        cboMostrar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todas", "Pendientes", "Pagadas", "Entregadas", "Garantias", "Anuladas" }));
        cboMostrar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboMostrarItemStateChanged(evt);
            }
        });
        cboMostrar.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                cboMostrarCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        cboMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMostrarActionPerformed(evt);
            }
        });

        tblListar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Folio", "Fecha", "Rut cliente", "Nombre cliente", "Estado", "Total", "Vendedor"
            }
        ));
        jScrollPane1.setViewportView(tblListar);

        btnAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Eye_50px.png"))); // NOI18N
        btnAbrir.setToolTipText("Cargar");
        btnAbrir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAbrirMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAbrirMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAbrirMouseExited(evt);
            }
        });

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Trash_50px.png"))); // NOI18N
        btnEliminar.setToolTipText("Eliminar");
        btnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEliminarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEliminarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEliminarMouseExited(evt);
            }
        });

        btnRestaurar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Data_Backup_50px.png"))); // NOI18N
        btnRestaurar.setToolTipText("Restaurar");
        btnRestaurar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRestaurarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRestaurarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRestaurarMouseExited(evt);
            }
        });

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Search_Property_25px_1.png"))); // NOI18N

        txtBuscar.setToolTipText("Buscar");
        txtBuscar.setBorder(null);
        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });

        cboFilterOptions.setFont(new java.awt.Font("Segoe UI Light", 1, 11)); // NOI18N
        cboFilterOptions.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Por Día", "Por Fechas", "Por Cliente", "Por Vendedor", "Por Vendedor y Fecha", "Por Lugar y Fecha", "Por Convenios" }));
        cboFilterOptions.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboFilterOptionsItemStateChanged(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel1.setText("Mostrar");

        btnReloadFilter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Filter_25px.png"))); // NOI18N
        btnReloadFilter.setToolTipText("Filtrar");
        btnReloadFilter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnReloadFilterMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnReloadFilterMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnReloadFilterMouseEntered(evt);
            }
        });

        btnReportSales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Analyze_50px.png"))); // NOI18N
        btnReportSales.setToolTipText("Enviar reporte de ventas");
        btnReportSales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnReportSalesMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnReportSalesMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnReportSalesMouseEntered(evt);
            }
        });

        btnExportFichas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Report_Card_50px.png"))); // NOI18N
        btnExportFichas.setToolTipText("Exportar fichas");
        btnExportFichas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExportFichasMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnExportFichasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExportFichasMouseExited(evt);
            }
        });

        btnExportConvenio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Report_Cnv_50px.png"))); // NOI18N
        btnExportConvenio.setToolTipText("Exportar convenio");
        btnExportConvenio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExportConvenioMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnExportConvenioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExportConvenioMouseExited(evt);
            }
        });

        btnDespacharTodo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Move_Stock_50px.png"))); // NOI18N
        btnDespacharTodo.setToolTipText("Marcar todas como despachadas");
        btnDespacharTodo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDespacharTodoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDespacharTodoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDespacharTodoMouseExited(evt);
            }
        });

        btnSalesReport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Cash_Counter_50px.png"))); // NOI18N
        btnSalesReport.setToolTipText("Ver reporte de ventas");
        btnSalesReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSalesReportMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSalesReportMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSalesReportMouseExited(evt);
            }
        });

        btnExportExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_XLS_50px.png"))); // NOI18N
        btnExportExcel.setToolTipText("Generar Excel");
        btnExportExcel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExportExcelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnExportExcelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExportExcelMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 991, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(239, 239, 239)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cboFilterOptions, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(cboMostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnReloadFilter)
                .addGap(13, 13, 13))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btnAbrir)
                .addGap(18, 18, 18)
                .addComponent(btnEliminar)
                .addGap(18, 18, 18)
                .addComponent(btnRestaurar)
                .addGap(18, 18, 18)
                .addComponent(btnDespacharTodo)
                .addGap(109, 109, 109)
                .addComponent(btnReportSales)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSalesReport)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnExportExcel)
                .addGap(8, 8, 8)
                .addComponent(btnExportFichas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExportConvenio)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnReloadFilter, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboFilterOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(cboMostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnEliminar)
                            .addComponent(btnAbrir)
                            .addComponent(btnRestaurar)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnExportFichas)
                            .addComponent(btnExportConvenio)
                            .addComponent(btnSalesReport)
                            .addComponent(btnReportSales)
                            .addComponent(btnExportExcel)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDespacharTodo)))
                .addGap(36, 36, 36))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cboFilterOptions, cboMostrar});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAbrirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAbrirMouseClicked
        try{
            cWT();
            int fila = tblListar.getSelectedRow();
            String codigo = tblListar.getValueAt(fila, 0).toString();
            Ficha ficha = (Ficha)GV.buscarPorIdEnLista(codigo, GV.getFichas(), new Ficha());
            GV.setOpenFicha(ficha);
            boton.ficha();
            cDF();
        }catch(Exception e){
            OptionPane.showMsg("Seleccione un elemento en la tabla","Debe hacer clic sobre un elemento de la tabla,\n"
                    + "Luego presione el botón \"Ver\".",  2);
            cDF();
        }
    }//GEN-LAST:event_btnAbrirMouseClicked

    private void btnEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseClicked
        if(GV.tipoUserAdmin()){
            try{
                cWT();
                int fila = tblListar.getSelectedRow();
                String folio = tblListar.getValueAt(fila, 0).toString();
                if(OptionPane.getConfirmation("Eliminar registro", "¿Esta seguro que desea eliminar el folio "+folio+"?", 2)){
                    cWT();
                    if(load.delete(folio,0, new Ficha()))
                        OptionPane.showMsg("Anular Ficha", "La ficha ha sido anulada", 1);
                    else
                        OptionPane.showMsg("Anular Ficha", "No se pudo anular la ficha", 2);
                    cDF();
                }
                load();

                cDF();
            }catch(Exception e){
                OptionPane.showMsg("Seleccione Ficha","Error al cargar valores,\n"
                        + "es posible que no se haya seleccionado un registro:\n"
                        + "Debe hacer clic sobre un elemento de la tabla,\n"
                        + "Luego presione el botón \"Ver\".\n"
                        + "Otro posible error: el valor seleccionado no tiene un identificador válido.",2);
                cDF();
            }
        }else{
            GV.mensajeAccessDenied();
            cDF();
        }
    }//GEN-LAST:event_btnEliminarMouseClicked

    private void btnRestaurarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRestaurarMouseClicked
        OptionPane.showMsg("Funcion no disponible", "Esta opción no se encuentra disponible\n"
                + "debe generar una ficha nuevamente", 3);
    }//GEN-LAST:event_btnRestaurarMouseClicked

    private void btnAbrirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAbrirMouseEntered
        btnAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icons.getEnteredIcon(btnAbrir.getIcon().toString()))));
    }//GEN-LAST:event_btnAbrirMouseEntered

    private void btnAbrirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAbrirMouseExited
        btnAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icons.getExitedIcon(btnAbrir.getIcon().toString()))));
    }//GEN-LAST:event_btnAbrirMouseExited

    private void btnEliminarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseEntered
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icons.getEnteredIcon(btnEliminar.getIcon().toString()))));
    }//GEN-LAST:event_btnEliminarMouseEntered

    private void btnEliminarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseExited
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icons.getExitedIcon(btnEliminar.getIcon().toString()))));
    }//GEN-LAST:event_btnEliminarMouseExited

    private void btnRestaurarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRestaurarMouseEntered
        btnRestaurar.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icons.getEnteredIcon(btnRestaurar.getIcon().toString()))));
    }//GEN-LAST:event_btnRestaurarMouseEntered

    private void btnRestaurarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRestaurarMouseExited
        btnRestaurar.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icons.getExitedIcon(btnRestaurar.getIcon().toString()))));
    }//GEN-LAST:event_btnRestaurarMouseExited

    private void cboMostrarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboMostrarItemStateChanged
        
    }//GEN-LAST:event_cboMostrarItemStateChanged

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
        txtBuscar.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(final KeyEvent e) {
                trs.setRowFilter(RowFilter.regexFilter("(?i)"+txtBuscar.getText(), 0,1,2,3,4,5,7,8,9,10));
            }
            
        });
        trs = new TableRowSorter(modelo);
        tblListar.setRowSorter(trs);
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void cboFilterOptionsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboFilterOptionsItemStateChanged
       
    }//GEN-LAST:event_cboFilterOptionsItemStateChanged

    private void btnReloadFilterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReloadFilterMouseClicked
        openFilter();
    }//GEN-LAST:event_btnReloadFilterMouseClicked

    private void btnReloadFilterMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReloadFilterMouseEntered
        btnReloadFilter.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icons.getEnteredIcon(btnReloadFilter.getIcon().toString()))));
    }//GEN-LAST:event_btnReloadFilterMouseEntered

    private void btnReloadFilterMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReloadFilterMouseExited

        btnReloadFilter.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icons.getExitedIcon(btnReloadFilter.getIcon().toString()))));
    }//GEN-LAST:event_btnReloadFilterMouseExited

    private void btnReportSalesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportSalesMouseClicked
        cWT();
        GlobalValuesFunctions.enviarReporteVentas();
        cDF();
    }//GEN-LAST:event_btnReportSalesMouseClicked

    private void btnReportSalesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportSalesMouseEntered
        btnReportSales.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icons.getEnteredIcon(btnReportSales.getIcon().toString()))));
    }//GEN-LAST:event_btnReportSalesMouseEntered

    private void btnReportSalesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportSalesMouseExited
        btnReportSales.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icons.getExitedIcon(btnReportSales.getIcon().toString()))));
    }//GEN-LAST:event_btnReportSalesMouseExited

    private void btnExportFichasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExportFichasMouseClicked
        if(GV.licenciaExpirada()){
            GV.mensajeLicenceExpired();
        }else{
            if(GV.getFichas().size()>0){
                cWT();
                GV.printFichas(GV.getFichas());
                cDF();
            }else{
                GlobalValuesFunctions.fichasMensajeOperacionCanceladaPorTablaVacia();
            } 
        }
    }//GEN-LAST:event_btnExportFichasMouseClicked

    private void btnExportFichasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExportFichasMouseEntered
        btnExportFichas.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icons.getEnteredIcon(btnExportFichas.getIcon().toString()))));
    }//GEN-LAST:event_btnExportFichasMouseEntered

    private void btnExportFichasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExportFichasMouseExited
        btnExportFichas.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icons.getExitedIcon(btnExportFichas.getIcon().toString()))));
    }//GEN-LAST:event_btnExportFichasMouseExited

    private void btnExportConvenioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExportConvenioMouseClicked
        cWT();
        GlobalValuesFunctions.exportarConvenio();
        cDF();
    }//GEN-LAST:event_btnExportConvenioMouseClicked

    private void btnExportConvenioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExportConvenioMouseEntered
        btnExportConvenio.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icons.getEnteredIcon(btnExportConvenio.getIcon().toString()))));
    }//GEN-LAST:event_btnExportConvenioMouseEntered

    private void btnExportConvenioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExportConvenioMouseExited
        btnExportConvenio.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icons.getExitedIcon(btnExportConvenio.getIcon().toString()))));
    }//GEN-LAST:event_btnExportConvenioMouseExited

    private void btnDespacharTodoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDespacharTodoMouseClicked
        if(GV.tipoUserAdmin()){
            if(GV.getFichas().size() > 0){
                try {
                    cWT();
                    GV.fichasToDelivery(GV.getFichas());
                    cDF();
                    boton.fichas(GV.getCboFichasFilterData());
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(VFichasMac.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                GlobalValuesFunctions.fichasMensajeOperacionCanceladaPorTablaVacia();
            }
        }else{
            GV.mensajeAccessDenied();
        }
    }//GEN-LAST:event_btnDespacharTodoMouseClicked

    private void btnDespacharTodoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDespacharTodoMouseEntered
        btnDespacharTodo.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icons.getEnteredIcon(btnDespacharTodo.getIcon().toString()))));
    }//GEN-LAST:event_btnDespacharTodoMouseEntered

    private void btnDespacharTodoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDespacharTodoMouseExited
        btnDespacharTodo.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icons.getExitedIcon(btnDespacharTodo.getIcon().toString()))));
    }//GEN-LAST:event_btnDespacharTodoMouseExited

    private void btnSalesReportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalesReportMouseClicked
        verReporte();
    }//GEN-LAST:event_btnSalesReportMouseClicked

    private void btnSalesReportMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalesReportMouseEntered
        btnSalesReport.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icons.getEnteredIcon(btnSalesReport.getIcon().toString()))));
    }//GEN-LAST:event_btnSalesReportMouseEntered

    private void btnSalesReportMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalesReportMouseExited
        btnSalesReport.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icons.getExitedIcon(btnSalesReport.getIcon().toString()))));
    }//GEN-LAST:event_btnSalesReportMouseExited

    private void btnExportExcelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExportExcelMouseClicked
        if(GV.tipoUserAdmin()){
            GV.cursorWAIT(this);
            GV.excelExportFichas(GV.getFichas());
            GV.cursorDF(this);
        }else{
            GV.mensajeAccessDenied();
        }
    }//GEN-LAST:event_btnExportExcelMouseClicked

    private void btnExportExcelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExportExcelMouseEntered
        btnExportExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icons.getEnteredIcon(btnExportExcel.getIcon().toString()))));
    }//GEN-LAST:event_btnExportExcelMouseEntered

    private void btnExportExcelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExportExcelMouseExited
        btnExportExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource(Icons.getExitedIcon(btnExportExcel.getIcon().toString()))));
    }//GEN-LAST:event_btnExportExcelMouseExited

    private void cboMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMostrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboMostrarActionPerformed

    private void cboMostrarCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_cboMostrarCaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cboMostrarCaretPositionChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnAbrir;
    private javax.swing.JLabel btnDespacharTodo;
    private javax.swing.JLabel btnEliminar;
    private javax.swing.JLabel btnExportConvenio;
    private javax.swing.JLabel btnExportExcel;
    private javax.swing.JLabel btnExportFichas;
    private javax.swing.JLabel btnReloadFilter;
    private javax.swing.JLabel btnReportSales;
    private javax.swing.JLabel btnRestaurar;
    private javax.swing.JLabel btnSalesReport;
    public static javax.swing.JComboBox<String> cboFilterOptions;
    private javax.swing.JComboBox<String> cboMostrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tblListar;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables

    private void load(){
        btnExportConvenio.setVisible(false);
        int index = buildIndex(cboMostrar.getSelectedIndex());
        cargarDatos(index);
    }
    
    /**
     * 0:todas(-1),1:pendientes(1),2:pagadas(2),3:entregadas(3),4:garantias(4),5:eliminadas(0)
     * @param index
     * @return todas(-1),pendientes(1),pagadas(2),entregadas(3),garantias(4),eliminadas(0)
     */
    private int buildIndex(int index){
        int INDEX_ELIMINADOS = 5;
        int INDEX_TODOS = 0;
        index = (index==INDEX_TODOS)?-1:index;
        return (index==INDEX_ELIMINADOS)? 0:index;
    }
    
    public void cargarDatos(int status) {
        loadFilter();
        if(status == 0){
            btnRestaurar.setVisible(true);
            btnEliminar.setVisible(false);
            btnAbrir.setVisible(false);
            btnReportSales.setVisible(false);
        }else{
            btnRestaurar.setVisible(false);
            btnEliminar.setVisible(true);
            btnAbrir.setVisible(true);
            btnReportSales.setVisible(true);
        }
        try{
            GV.loadDataTable(modelo,COLUMNAS_TABLA);
            tblListar.updateUI();
            if(tblListar.getRowCount() == 0){
                GV.emptyTable(cboMostrar, txtBuscar, "Fichas");
            }
        }catch(Exception e){
            OptionPane.showMsg("Ocurrió un error inesperado", "Ocurrió un error inesperado al cargar valores en la tabla, ["+e.getMessage()+"]",3);
        }
    }
    
    private void cWT(){
        GV.cursorWAIT(this);
    }
    private void cDF(){
        GV.cursorDF(this);
    }
    
    private void verReporte(){
        cWT();
        GlobalValuesFunctions.fichasVerReporte();
        cDF();
    }
    
    private void openFilter(){
        GV.setCboFichasFilterData(cboFilterOptions.getSelectedIndex());
        GV.setCboFichasFilterStatus(cboMostrar.getSelectedIndex());
        GlobalValuesFunctions.fichasOpenFilter();
    }
    
    private void loadFilter(){
        GlobalValuesFunctions.fichasLoadFilter(btnExportConvenio);
    }
}
