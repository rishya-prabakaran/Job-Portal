
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author admin
 */
public class JavaMailUtil {
    public static void sendMail(String recepient) throws MessagingException
    {
        System.out.println("Preparing to send email");
        Properties properties=new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");
        
        String myAccountEmail="rishyakp@gmail.com";
        String password="Rishya2000";
        
      /* Session session;
        session = Session.getInstance(properties,new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(myAccountEmail,password);
            }
        });*/
     Session session = Session.getDefaultInstance(properties,    
           new javax.mail.Authenticator() {    
           protected PasswordAuthentication getPasswordAuthentication() {    
           return new PasswordAuthentication(myAccountEmail,password);  
           }    
          }); 
     Message message=prepareMessage(session,myAccountEmail,recepient);
     Transport.send(message);
     System.out.println("Message sent successfully");
    }
    private static Message prepareMessage(Session session,String myAccountEmail,String recepient){
        try{
            Message message=new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(recepient));
            message.setSubject("Shortlisted for Internship");
            message.setText("\t\tYou are shortlisted for the Internship you applied."+
                    "The call for interview will be intimated sooner.\n\nThank you!!");
            return message;
        }
        catch(Exception ex){
            Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE,null,ex);
        }
        return null;
    }

 public static void main(String[] args) throws MessagingException{
     JavaMailUtil.sendMail("rishyakp@gmail.com");
        
        
    }
}