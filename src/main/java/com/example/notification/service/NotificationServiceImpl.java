package com.example.notification.service;

import com.example.notification.dto.NotificationRequest;
import com.example.notification.dto.NotificationResponse;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Properties;

@Service
public class NotificationServiceImpl implements NotificationService {

/*    @Autowired
    MailSender mailSender;*/

    @Override
    public Mono<ResponseEntity<NotificationResponse>> sendNotificationByMail(NotificationRequest notificationRequest) {
        final String senderEmail = "dodmahima@gmail.com";       // sender email
        final String password = "ygok dcih tewh gxop";             // use App Password or Gmail password
        final String recipientEmail = notificationRequest.getEmail();   // recipient email

        // 1. SMTP Server Configuration
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // 2. Create Session
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, password);
            }
        });

        try {
            // 3. Create Message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(recipientEmail)
            );
            message.setSubject("Book Reservation");
            message.setText("Hello,\n\nThis is to confirm your reservation of the book "+notificationRequest.getName()+" by the author "+notificationRequest.getAuthor().getName()+".");

            // 4. Send Message
            Transport.send(message);

            System.out.println("✅ Email sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return Mono.just(ResponseEntity.ok(new NotificationResponse()));
    }
}
