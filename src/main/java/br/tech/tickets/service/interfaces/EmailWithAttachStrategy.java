package br.tech.tickets.service.interfaces;

import br.tech.tickets.domain.entity.Email;
import jakarta.mail.MessagingException;

public interface EmailWithAttachStrategy {
    void sendEmailWithAttachment(Email email) throws MessagingException;
}
