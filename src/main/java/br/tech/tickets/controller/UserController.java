package br.tech.tickets.controller;

import br.tech.tickets.domain.entity.User;
import br.tech.tickets.dto.UserDTO;
import br.tech.tickets.mapper.UserMapper;
import br.tech.tickets.service.MailService;
import br.tech.tickets.service.UserService;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final MailService mailService;

    public UserController(UserService userService, MailService mailService) {
        this.userService = userService;
        this.mailService = mailService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) throws MessagingException, UnsupportedEncodingException {
        User user = UserMapper.toEntity(userDTO);
        UserDTO createdUserDTO = UserMapper.toDto(user);
        userService.registerNewUser(userDTO);
        mailService.sendMailWithInline(user);
        return ResponseEntity.status(201).body(createdUserDTO);
    }
}
