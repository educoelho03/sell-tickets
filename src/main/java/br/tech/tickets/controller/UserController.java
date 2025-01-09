package br.tech.tickets.controller;

import br.tech.tickets.domain.entity.Email;
import br.tech.tickets.domain.entity.User;
import br.tech.tickets.dto.UserDTO;
import br.tech.tickets.mapper.UserMapper;
import br.tech.tickets.service.EmailService;
import br.tech.tickets.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final EmailService emailService;

    public UserController(UserService userService, EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        User user = UserMapper.toEntity(userDTO);
        UserDTO createdUserDTO = UserMapper.toDto(user);
        userService.registerNewUser(userDTO);

        Email email = new Email();
        email.setRecipient(userDTO.getEmail());
        email.setSubject("User created");
        email.setBody("Dear " + userDTO.getFirstName() + " " + userDTO.getLastName() + "\n\n Your user has been registered successfully.");
        emailService.sendEmail(email);
        return ResponseEntity.status(201).body(createdUserDTO);
    }
}
