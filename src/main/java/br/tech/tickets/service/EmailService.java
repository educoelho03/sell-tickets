package br.tech.tickets.service;

import br.tech.tickets.domain.entity.Email;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    public String sendEmail(Email email){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(sender);
        message.setTo(email.getRecipient());
        message.setSubject(email.getSubject());
        message.setText(email.getBody());
        mailSender.send(message);

        return "Mail Sent Successfully...";
    }

    public String sendEmailWithAttachment(Email email) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper;

        helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(sender);
        helper.setTo(email.getRecipient());
        helper.setSubject(email.getSubject());
        helper.setText(email.getBody());

        FileSystemResource file = new FileSystemResource(new File(email.getAttachment()));
        helper.addAttachment(file.getFilename(), file);

        mailSender.send(mimeMessage);
        return "Mail Sent Successfully...";
    }

}
