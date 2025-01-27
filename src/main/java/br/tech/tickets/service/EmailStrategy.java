package br.tech.tickets.service;

import br.tech.tickets.domain.entity.Email;
import jakarta.mail.MessagingException;

public interface EmailStrategy {
    void sendEmail(Email email) throws MessagingException;
}