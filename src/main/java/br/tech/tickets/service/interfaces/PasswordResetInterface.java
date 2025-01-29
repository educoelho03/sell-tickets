package br.tech.tickets.service.interfaces;

import br.tech.tickets.domain.entity.PasswordResetToken;
import br.tech.tickets.domain.entity.User;
import jakarta.mail.MessagingException;

public interface PasswordResetInterface {
    void createTokenResetPassword(User user, String token);
    PasswordResetToken getPasswordResetToken(String token);
    void sendPasswordResetEmail(String email) throws MessagingException;
    void changePassword(String token, String newPassword, String emailRequest) throws MessagingException;
}
