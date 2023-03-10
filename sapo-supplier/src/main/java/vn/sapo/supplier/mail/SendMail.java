//package vn.sapo.supplier.mail;
//
//import com.sun.jdi.connect.Transport;
//import org.apache.logging.log4j.message.Message;
//
//import javax.websocket.Session;
//import java.net.PasswordAuthentication;
//import java.util.Properties;
//
//public class SendMail {
//    public static void main(String[] args) {
//        String username = "nguyentanluong0503@gmail.com";
//        String password = "nguyentanluong12345";
//
//
//        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.port", "587");
//
//        Session session = Session.getInstance(props,
//                new javax.mail.Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(username, password);
//                    }
//                });
//
//        try {
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress("nguyentanluong0503@gmail.com"));
//            message.setRecipients(Message.RecipientType.TO,
//                    InternetAddress.parse("nguyentanluong0503@gmail.com"));
//            message.setSubject("");
//            message.setText("Hello");
//
//            Transport.send(message);
//
//            System.out.println("Email sent successfully");
//
//        }catch (MessagingException e){
//            throw new RuntimeException(e);
//        }
//    }
//
//}
