package br.tech.tickets.repository;

import br.tech.tickets.domain.entity.PasswordResetToken;
import br.tech.tickets.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    PasswordResetToken findByToken(String token);
    PasswordResetToken findByUser(User user);
}
