/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comunicacion;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import Util.Email;


/**
 *
 * @author HP
 */
public class SendEmailThread implements Runnable{

    private Email email;
/*
    private final static String port_SMTP="25";
    private final static String protocol="smtp";
    private final static String host="mail.tecnoweb.org.bo";
    private final static String user="grupo05sa";
    private final static String mail="grupo05sa@tecnoweb.org.bo";
    private final static String mail_Password="grupo005grupo005";
  */  
    
    
 private final static String port_SMTP="465";
    private final static String protocol="smtp";
    private final static String host="smtp.googlemail.com";
    private final static String user="jakeli1997jcs@gmail.com";
    private final static String mail="jakeli1997jcs@gmail.com";
    private final static String mail_Password="kaly2022";
    
    public SendEmailThread(Email email){
        this.email= email;
    }

    @Override
    public void run() {
        Properties properties=new Properties();
        properties.put("mail.transport.protocol", protocol);
        
        
        /***/
    properties.setProperty("mail.smtp.password", mail_Password);
         properties.setProperty("mail.smtp.user", user);
       
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", port_SMTP);
        //properties.setProperty("mail.smtp.tls.enable", "true");//usando tecnoWeb
        properties.setProperty("mail.smtp.ssl.enable", "true");//usando Gmail
        properties.setProperty("mail.smtp.auth", "true");
     Session session;
        session = Session.getDefaultInstance(properties,new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(user,mail_Password);
            }
        });
        
        
        
        try{
            MimeMessage message= new MimeMessage(session);
            message.setFrom(new InternetAddress(mail));
            
            InternetAddress[] toAddresses = {new InternetAddress(email.getTo())};
            
            message.setRecipients(RecipientType.TO, toAddresses);
            message.setSubject(email.getSubject());
            
            Multipart multipart = new MimeMultipart("älternative");
            MimeBodyPart htmlPart= new MimeBodyPart();
            
            htmlPart.setContent(email.getMessage(),"text/html; charset = utf-8");
            multipart.addBodyPart(htmlPart);
            message.setContent(multipart);
            message.saveChanges();
            
            Transport.send(message);
        }     catch(NoSuchProviderException | AddressException ex){
                Logger.getLogger(SendEmailThread.class.getName()).log(Level.SEVERE,null,ex);
        }     catch(MessagingException ex){
                Logger.getLogger(SendEmailThread.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
}
