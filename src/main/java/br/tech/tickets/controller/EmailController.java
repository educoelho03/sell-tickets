package br.tech.tickets.controller;

import br.tech.tickets.domain.entity.Email;
import br.tech.tickets.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    private EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/sendEmail")
    public ResponseEntity<String> sendEmail(@RequestBody Email email) {
        try{
            emailService.sendEmail(email);
            return ResponseEntity.status(200).body("Email sent");
        } catch (Exception ex){
            return ResponseEntity.badRequest().body("Error while sending email");
        }
    }

    @PostMapping("/sendEmailWithAttachment")
    public ResponseEntity<String> sendEmailWithAttachment(@RequestBody Email email) {
        try {
            emailService.sendEmailWithAttachment(email);
            return ResponseEntity.status(200).body("Email sent.");
        } catch (Exception ex){
            return ResponseEntity.badRequest().body("Error while sending email");
        }
    }
}
