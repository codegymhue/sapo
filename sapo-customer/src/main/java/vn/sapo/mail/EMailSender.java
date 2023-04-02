package vn.sapo.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailMessage;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@Component
public class EMailSender{
    @Autowired
    private JavaMailSender emailSender;

    public void sendEmail(String toEmail, String subject, String body) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("sapoproject01@gmail.com");
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(text);
//        emailSender.send(message);
        try{
            final String fromEmail = "sapoproject01@gmail.com";
            // Mat khai email cua ban
            final String password = "hanlvpvfqagfegqj";
//            // dia chi email nguoi nhan
//            final String toEmail = "trantrung2751999@gmail.com";
//            final String subject = "Java Example Test";
//            final String body = "Hello Admin";
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
            props.put("mail.smtp.port", "587"); //TLS Port
            props.put("mail.smtp.auth", "true"); //enable authentication
            props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
            Authenticator auth = new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            };
            Session session = Session.getInstance(props, auth);
            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");
            msg.setFrom(new InternetAddress(fromEmail, "Sapo"));
            msg.setReplyTo(InternetAddress.parse(fromEmail, false));
            msg.setSubject(subject, "UTF-8");
            msg.setContent(body, "text/html; charset=UTF-8");
            msg.setSentDate(new Date());
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            Transport.send(msg);
            System.out.println("Gui mail thanh cong");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Gui mail that bai");
        }
    }
//    @Autowired
//    private MailSender mailSender;
//
//    @Autowired
//    private MailMessage mailMessage;

//    public void sendEmail(final String subject, final String message, final String[] emailAddresses) {
//        mailMessage.setSubject(subject);
//        mailMessage.setTo(emailAddresses);
//        mailMessage.setText(message);
//        try {
//            mailSender.send((SimpleMailMessage) mailMessage);
//            System.out.println("Email sending complete.");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
