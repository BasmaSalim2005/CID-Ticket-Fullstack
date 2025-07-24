package com.basma.Demo1.notifications.smtp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(String toEmail,
                                String subject,
                                String body
    ) {
        log.info("email send Start");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ykh599759@gmail.com");
//        message.setTo("ykh599759@gmail.com");
        message.setTo(toEmail);
        body="Bonjour,\n \n"+body+ " \n \nBien cordialement,\nEquipe CID-Ticket";
        message.setText(body);
        message.setSubject("CID-Ticket - "+subject);
        mailSender.send(message);
        log.info("email send End");

    }
}
