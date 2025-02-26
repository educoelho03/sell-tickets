package br.tech.tickets.service.impl;

import br.tech.tickets.controller.dto.UserDTO;
import br.tech.tickets.domain.entity.User;
import br.tech.tickets.exception.EmailExistsException;
import br.tech.tickets.exception.SamePasswordException;
import br.tech.tickets.repository.UserRepository;
import br.tech.tickets.service.interfaces.UserInterface;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserInterface {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerNewUser(UserDTO userDto) {
        if(emailExists(userDto.getEmail())) {
            throw new EmailExistsException("There is an account with that email adress:" + userDto.getEmail());
        }

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setEmail(userDto.getEmail());
        user.setCpf(userDto.getCpf());
        user.setPhone(userDto.getPhone());

        validateFields(user);
        userRepository.save(user);
    }

    @Override
    public void updatePersonalInformation(UserDTO userDto) {
        Optional<User> userOptional = userRepository.findByUsername(userDto.getUsername());

        userOptional.ifPresent(user -> {
            user.setCpf(userDto.getCpf());
            user.setEmail(userDto.getEmail());
            user.setPhone(userDto.getPhone());

            userRepository.save(user);
        });
    }

    @Override
    public void changePassword(User user, String newPassword) {
        if(passwordEncoder.matches(newPassword, user.getPassword())) {
            throw new SamePasswordException("Passwords must be different.");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
    }

    @Override
    public void validateFields(User user) {
        Map<String, String> fieldsToValidate = new HashMap<>();
        fieldsToValidate.put("firstName", user.getUsername());
        fieldsToValidate.put("cpf", user.getCpf());
        fieldsToValidate.put("email", user.getEmail());
        fieldsToValidate.put("password", user.getPassword());

        for(Map.Entry<String, String> field : fieldsToValidate.entrySet()){
            if(field.getValue() == null){
                throw new IllegalArgumentException(field.getKey() + " is required");
            }
        }
    }

    @Override
    public boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }
}
