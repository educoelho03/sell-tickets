package br.tech.tickets.repository;

import br.tech.tickets.domain.entity.PasswordResetToken;
import br.tech.tickets.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    PasswordResetToken findByToken(String token);
    PasswordResetToken findByUser(User user);
}
