package br.tech.tickets.service.impl;

import br.tech.tickets.domain.entity.PasswordResetToken;
import br.tech.tickets.domain.entity.User;
import br.tech.tickets.repository.PasswordResetTokenRepository;
import br.tech.tickets.repository.UserRepository;
import br.tech.tickets.service.EmailService;
import br.tech.tickets.service.interfaces.PasswordResetInterface;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PasswordResetServiceImpl implements PasswordResetInterface {

    private UserRepository userRepository;
    private PasswordResetTokenRepository tokenRepository;
    private EmailService emailService;

    public PasswordResetServiceImpl(UserRepository userRepository, PasswordResetTokenRepository tokenRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.emailService = emailService;
    }

    @Override
    public void createTokenResetPassword(User user, String token) {
        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setUser(user);
        resetToken.setToken(token);
        tokenRepository.save(resetToken);
    }

    @Override
    public PasswordResetToken getPasswordResetToken(String token) {
        return tokenRepository.findByToken(token);
    }

    @Override
    public void sendPasswordResetEmail(String email) throws MessagingException {
        User user = userRepository.findByEmail(email);
        if(user == null) {
            throw new MessagingException("Invalid email");
        }

        String token = UUID.randomUUID().toString(); // gerando um novo token
        createTokenResetPassword(user, token);
        emailService.sendPasswordResetEmail(user.getEmail(), token);
    }

    @Override
    @Transactional
    public void changePassword(String token, String newPassword, String emailRequest) throws MessagingException {
        PasswordResetToken resetToken = tokenRepository.findByToken(token);
        if(resetToken == null) {
            throw new MessagingException("Invalid token");
        }

        User user = resetToken.getUser();
        if(user == null || !user.getEmail().equals(emailRequest)) {
            throw new MessagingException("Usuario nao encontrado ou email invalido");
        }

        user.setPassword(newPassword);
        userRepository.save(user);
        tokenRepository.delete(resetToken);
    }
}
