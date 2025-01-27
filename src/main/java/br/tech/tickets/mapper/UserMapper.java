package br.tech.tickets.mapper;

import br.tech.tickets.domain.entity.User;
import br.tech.tickets.dto.UserDTO;

public class UserMapper {
    public static User toEntity(UserDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());

        return user;
    }

    public static UserDTO toDto(User user) {
        UserDTO dto = new UserDTO();
        dto.setUsername(user.getUsername());
        dto.setCpf(user.getCpf());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());


        return dto;
    }
}
