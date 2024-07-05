//package service;
//
//import jakarta.enterprise.context.ApplicationScoped;
//import jakarta.inject.Inject;
//
//
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//
//@ApplicationScoped
//public class EmailService {
//
//    @Inject
//    Session mailSession;
//
//    public void sendEmail(String to, String subject, String body) throws MessagingException {
//        MimeMessage message = new MimeMessage(mailSession);
//        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
//        message.setSubject(subject);
//        message.setText(body);
//
//        Transport.send(message);
//    }
//}
