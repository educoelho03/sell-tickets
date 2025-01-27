package br.tech.tickets.controller;

import br.tech.tickets.domain.entity.Email;
import br.tech.tickets.domain.entity.User;
import br.tech.tickets.controller.dto.UserDTO;
import br.tech.tickets.mapper.UserMapper;
import br.tech.tickets.service.EmailStrategyImpl;
import br.tech.tickets.service.UserService;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    private final EmailStrategyImpl emailStrategy;
    // private final EmailService emailService;


    public UserController(UserService userService, EmailStrategyImpl emailStrategy) {
        this.userService = userService;
        this.emailStrategy = emailStrategy;
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) throws MessagingException {
        User user = UserMapper.toEntity(userDTO);
        UserDTO createdUserDTO = UserMapper.toDto(user);
        userService.registerNewUser(userDTO);

        Email email = new Email();
        email.setRecipient(userDTO.getEmail());
        email.setSubject("User created");
        email.setBody("Dear " + userDTO.getUsername() + "\n\n Your user has been registered successfully.");
        emailStrategy.sendEmail(email);

        return ResponseEntity.status(201).body(createdUserDTO);
    }
}
