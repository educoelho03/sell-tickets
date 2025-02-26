package br.tech.tickets.service.interfaces;

import br.tech.tickets.controller.dto.UserDTO;
import br.tech.tickets.domain.entity.User;

public interface UserInterface {

    void registerNewUser(UserDTO userDto);
    void updatePersonalInformation(UserDTO UserDto);
    void changePassword(User user, String newPassword);
    void validateFields(User user);
    boolean emailExists(String email);

}
