package br.tech.tickets.service.impl;

import br.tech.tickets.domain.entity.Email;
import br.tech.tickets.service.interfaces.EmailStrategy;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailStrategyImpl implements EmailStrategy {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public EmailStrategyImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(Email email) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(sender);
        message.setTo(email.getRecipient());
        message.setSubject(email.getSubject());
        message.setText(email.getBody());
        mailSender.send(message);
    }
}
