//package configuration;
//
//import jakarta.enterprise.context.ApplicationScoped;
//import jakarta.enterprise.inject.Produces;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import java.util.Properties;
//
//@ApplicationScoped
//public class Emailconfiguration {
//
//    @Produces
//    @ApplicationScoped
//    public Session produceMailSession() {
//        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.port", "587");
//        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
//
//        return Session.getInstance(props, new javax.mail.Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication("@gmail.com", "izzo ubdk qyap xsjh");
//            }
//        });
//    }
//}
