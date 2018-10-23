/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn;

import dao.Dao;
import static fn.GV.fechaDiferencia;
import static fn.globalValues.GlobalValuesFunctions.licenciaComprobarValidez;
import fn.mail.Send;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import static java.util.concurrent.TimeUnit.SECONDS;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author sdx
 */
public class SubProcess {
    volatile static boolean ejecucion = true;
    volatile static boolean pause = false;
    private static String className="SubProcess";
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private static String defaultText = "";
    
    
    private static int TIME_MIN_COMPROBAR_ONLINE = 5;
    
    Dao load = new Dao();
    
    /**
     * Comprueba la conección ainternet cada 5 segundos
     */
    
    public static void isOnline(){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
                while(ejecucion){
                    if(!pause){
                        GV.chekOnline();
                    }
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SubProcess.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        });
    }
    
    public static void licenciaComprobarOnline(){
        GV.loadXmlOnline();
        licenciaComprobarValidez();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            try {
                while(true){
                    Thread.sleep(TIME_MIN_COMPROBAR_ONLINE*60000);
                    GV.loadXmlOnline();
                    if(fechaDiferencia(GV.strToDate(GV.expDate())) < 1){
                        licenciaComprobarValidez();
                    }
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(SubProcess.class.getName()).log(Level.SEVERE, null, ex);
                OptionPane.showMsg("Error al comprobar datos en línea", className+"\n"
                        + ex.getMessage(), 3);
            }
        });
    }
    
    public static void lblSyncStatus(JLabel txtTitle) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            String text = txtTitle.getText();
            if(!text.contains("%")){
                defaultText = text;
            }
            int porcentaje = 0;
                while(ejecucion){
                    porcentaje = GV.porcentaje();
                    if(porcentaje > 0){
                        txtTitle.setText("Sincronizando dependencias... ("+porcentaje+"%)");
                    }else{
                        txtTitle.setText(defaultText);
                    }
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SubProcess.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        });
    }
    public static void stopAll(){
        ejecucion = false;
    }
    
    public static void suspendConnectionOnline(){
        GV.isOnline(false);
        pause = true;
    }
    
    public static void activateConnectionOnline(){
        GV.isOnline(true);
        pause = false;
    }
    
    public static void report(String title, String message){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            Send mail = new Send();
            mail.sendReportMail(title, message);
        });
    }
    
    public static void isOnlineDeprecated() {
        GV.chekOnline();
        final Runnable beeper = new Runnable() {
          public void run() { 
              GV.chekOnline(); 
              System.out.println(GV.isOnline());
          }
        };
        final ScheduledFuture<?> beeperHandle =
            scheduler.scheduleAtFixedRate(beeper, 5, 5, SECONDS);
            scheduler.schedule(
                    new Runnable() {
                        public void run() { 
                            beeperHandle.cancel(true); 
                        }
                    }
                    , 60 * 60, SECONDS);
    }
    
    public static boolean ejecucion(){
        return ejecucion;
    }
    
    public static void SyncAll(){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
                GV.sincronizarTodo();
        });
    }
}
