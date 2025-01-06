package br.tech.tickets.service;

import br.tech.tickets.domain.entity.User;
import br.tech.tickets.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void createUser(User user) {
        validateFields(user);
        userRepository.save(user);
    }

    public void validateFields(User user) {
        Map<String, String> fieldsToValidate = new HashMap<>();
        fieldsToValidate.put("firstName", user.getFirstName());
        fieldsToValidate.put("lastName", user.getLastName());
        fieldsToValidate.put("cpf", user.getCpf());
        fieldsToValidate.put("email", user.getEmail());
        fieldsToValidate.put("password", user.getPassword());

        for(Map.Entry<String, String> field : fieldsToValidate.entrySet()){
            if(field.getValue() == null){
                throw new IllegalArgumentException(field.getKey() + " is required");
            }
        }
    }
}
