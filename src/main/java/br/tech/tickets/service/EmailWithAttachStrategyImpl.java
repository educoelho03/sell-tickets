package br.tech.tickets.service;

import br.tech.tickets.domain.entity.Email;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class EmailWithAttachStrategyImpl implements EmailWithAttachStrategy {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public EmailWithAttachStrategyImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmailWithAttachment(Email email) throws MessagingException {
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
    }
}
