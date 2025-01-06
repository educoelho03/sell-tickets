package br.tech.tickets.controller;

import br.tech.tickets.domain.entity.User;
import br.tech.tickets.dto.UserDTO;
import br.tech.tickets.mapper.UserMapper;
import br.tech.tickets.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> createUser(UserDTO userDTO) {
        User user = UserMapper.toEntity(userDTO);
        userService.createUser(user);
        UserDTO createdUserDTO = UserMapper.toDto(user);
        return ResponseEntity.status(201).body(createdUserDTO);
    }
}
