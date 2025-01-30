package br.tech.tickets.controller;

import br.tech.tickets.domain.entity.Email;
import br.tech.tickets.domain.entity.User;
import br.tech.tickets.controller.dto.UserDTO;
import br.tech.tickets.mapper.UserMapper;
import br.tech.tickets.service.impl.EmailStrategyImpl;
import br.tech.tickets.service.impl.UserServiceImpl;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserServiceImpl userService;
    private final EmailStrategyImpl emailStrategy;
    // private final EmailService emailService;


    public UserController(UserServiceImpl userService, EmailStrategyImpl emailStrategy) {
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

    @PutMapping("/updateInfos")
    public ResponseEntity<Void> updatePersonalInfoUser(@RequestBody UserDTO userDTO) {
        userService.updatePersonalInformation(userDTO);
        return ResponseEntity.status(204).build();
    }

    // @PutMapping("/updatePassword")
    // public ResponseEntity<Void> updateUserPassword(@RequestBody UserDTO userDTO, String newPassword){
    //     User userObj = UserMapper.toEntity(userDTO);
    //     userService.changePassword(userObj, newPassword);
    //     return ResponseEntity.status(204).build();
    // }


}
