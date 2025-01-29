package br.tech.tickets.controller;

import br.tech.tickets.controller.dto.ApiResponse;
import br.tech.tickets.service.PasswordResetService;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/passwords")
public class PasswordResetController {

    private PasswordResetService passwordResetService;

    public PasswordResetController(PasswordResetService passwordResetService) {
        this.passwordResetService = passwordResetService;
    }

    @PostMapping("/forgot")
    public ResponseEntity<ApiResponse> forgotPassword(@RequestParam("email") String email) throws MessagingException {
        passwordResetService.sendPasswordResetEmail(email);
        return ResponseEntity.status(200).body(new ApiResponse("Password change request sent successfully", null));
    }

    @PutMapping("/reset")
    public ResponseEntity<ApiResponse> resetPassword(@RequestParam("token") String token, @RequestParam Map<String, String> emailAndNewPassoword) {
        try {
            String email = emailAndNewPassoword.get("email");
            String password = emailAndNewPassoword.get("password");

            passwordResetService.changePassword(token, email, password);

            return ResponseEntity.status(200).body(new ApiResponse("Password successfully changed", null));
        } catch (MessagingException exception){
            return ResponseEntity.status(400).body(new ApiResponse("Error to change password: " + exception.getMessage(), null));
        }
    }
}
