package br.tech.tickets.controller;

import br.tech.tickets.dto.ApiResponse;
import br.tech.tickets.service.PasswordResetService;
import jakarta.mail.MessagingException;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/password-reset")
public class PasswordResetController {

    private PasswordResetService passwordResetService;

    public PasswordResetController(PasswordResetService passwordResetService) {
        this.passwordResetService = passwordResetService;
    }

    @PostMapping("/forgot")
    public ResponseEntity<ApiResponse> forgotPassword(@RequestParam("email") String email) throws MessagingException {
        passwordResetService.sendPasswordResetEmail(email);
        return ResponseEntity.ok(new ApiResponse("Solicitação de alteração de senha enviada com sucesso", null));
    }

    public ResponseEntity<ApiResponse> resetPassword(@RequestParam("token") String token, Map<String, String> emailAndNewPassoword) {
        try {
            String email = emailAndNewPassoword.get("email");
            String password = emailAndNewPassoword.get("password");

            passwordResetService.changePassword(token, email, password);

            return ResponseEntity.ok(new ApiResponse("Senha alterada com sucesso", null));
        } catch (MessagingException exception){
            return ResponseEntity.badRequest().body(new ApiResponse("Erro ao alterar a senha: " + exception.getMessage(), null));
        }
    }
}
