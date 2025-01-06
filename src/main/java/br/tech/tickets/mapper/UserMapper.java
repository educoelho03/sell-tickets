package br.tech.tickets.mapper;

import br.tech.tickets.domain.entity.User;
import br.tech.tickets.dto.UserDTO;

public class UserMapper {
    public static User toEntity(UserDTO dto) {
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());

        return user;
    }

    public static UserDTO toDto(User user) {
        UserDTO dto = new UserDTO();
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setCpf(user.getCpf());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());


        return dto;
    }
}
