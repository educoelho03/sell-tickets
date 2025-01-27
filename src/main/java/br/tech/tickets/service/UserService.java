package br.tech.tickets.service;

import br.tech.tickets.domain.entity.User;
import br.tech.tickets.dto.UserDTO;
import br.tech.tickets.exception.EmailExistsException;
import br.tech.tickets.exception.SamePasswordException;
import br.tech.tickets.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerNewUser(UserDTO userDto) throws EmailExistsException {
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

    public void changePassword(User user, String newPassword) {
        if(passwordEncoder.matches(newPassword, user.getPassword())) {
            throw new SamePasswordException("Passwords must be different.");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
    }

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

    public boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

}
