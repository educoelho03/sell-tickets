package br.tech.tickets.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;

@Entity
@Getter
@Setter
public class PasswordResetToken {

    private static final int EXPIRATION = 60 * 30;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @OneToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    private Date expiryDate;

    public PasswordResetToken() {
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    public PasswordResetToken(User user, String token) {
        this.user = user; // associa um usuário ao token
        this.token = token; // define o token de redefinição de senha
        this.expiryDate = calculateExpiryDate(EXPIRATION); // cria um novo token com a data de expiração calculada
    }

    private Date calculateExpiryDate(final int expiryTimeInMinutes) {
        final Calendar cal = Calendar.getInstance(); // obtém uma instância do calendário atual
        cal.setTimeInMillis(new Date().getTime()); // define o tempo atual como o tempo do calendário
        cal.add(Calendar.MINUTE, expiryTimeInMinutes); // adiciona o tempo de expiração do token em minutos
        return new Date(cal.getTime().getTime()); // retorna a data com a expiração do token
    }
}
