package vn.sapo.mail;

import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailMessage;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;
@Component
public class MailConfig {
    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("sapoproject01@gmail.com");
        mailSender.setPassword("bfwwmfskyxitztid");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
//    @Bean
//    public MailSender mailSender() {
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//
//        mailSender.setHost("smtp.gmail.com");
//        mailSender.setPort(465);
//        mailSender.setUsername("sapoproject01@gmail.com");
//        mailSender.setPassword("hanlvpvfqagfegqj");
//
//        Properties javaMailProperties = new Properties();
//        javaMailProperties.put("mail.smtp.auth", true);
//        javaMailProperties.put("mail.smtp.starttls.enable", true);
//
//        mailSender.setJavaMailProperties(javaMailProperties);
//
//        return mailSender;
//    }
//
//    @Bean
//    public MailMessage mailMessage() {
//        return new SimpleMailMessage();
//    }
}
