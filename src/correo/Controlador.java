/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correo;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


/**
 *
 * @author Lenovo G470
 */
public class Controlador {
    
    public boolean enviarCorreo(Correo c){
        try{
            Properties p = new Properties();
            p.put("mail.smtp.host", "smtp.gmail.com");
            p.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            p.setProperty("mail.smtp.starttls.enable", "true");
            p.setProperty("mail.smtp.port", "587");
            p.setProperty("mail.smtp.user", c.getUsuario());
            p.setProperty("mail.smtp.auth", "true");
            
            Session s = Session.getDefaultInstance(p,null);
            BodyPart text = new MimeBodyPart();
            text.setText(c.getMensaje());
            BodyPart adjunto=new MimeBodyPart();
            if(c.getRutaArchivo() != null){
            if(!c.getRutaArchivo().equals("")){
                
                adjunto.setDataHandler(new DataHandler(new FileDataSource(c.getRutaArchivo())));
                adjunto.setFileName(c.getNombreArchivo());
                
            }
            }
            MimeMultipart m = new MimeMultipart();
            m.addBodyPart(text);
            if(c.getRutaArchivo() != null){
            if(!c.getRutaArchivo().equals("")){
                m.addBodyPart(adjunto);
            }
            }
            
            MimeMessage mensaje = new MimeMessage(s);
            mensaje.setFrom(new InternetAddress(c.getUsuario()));
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(c.getDestino()));
            mensaje.setSubject(c.getAsunto());
            mensaje.setContent(m);
            Transport t = s.getTransport("smtp");
            try{
                t.connect(c.getUsuario(),c.getPass());
            }catch(MessagingException ex){
                System.out.println("Error en t.connect:"+ex);
            }
            
            
            t.sendMessage(mensaje, mensaje.getAllRecipients());
            
            t.close();
            return true;
        }catch(Exception ex){
            System.out.println("Error "+ex);
            return false;
        }
        
    }
}
