package br.tech.tickets.config;

import br.tech.tickets.domain.entity.Role;
import br.tech.tickets.domain.entity.User;
import br.tech.tickets.repository.RoleRepository;
import br.tech.tickets.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

// @Configuration
public class AdminUserConfiguration
        //implements CommandLineRunner
{
//
//     private RoleRepository roleRepository;
//     private UserRepository userRepository;
//     private BCryptPasswordEncoder passwordEncoder;
//
//     public AdminUserConfiguration(RoleRepository roleRepository, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
//         this.roleRepository = roleRepository;
//         this.userRepository = userRepository;
//         this.passwordEncoder = passwordEncoder;
//     }
//
//     @Override
//     @Transactional
//     public void run(String... args) throws Exception {
//
//         var roleAdmin = roleRepository.findByName(Role.Values.ADMIN.name());
//
//         var userAdmin = userRepository.findByUsername("admin");
//
//         userAdmin.ifPresentOrElse(
//                 user -> {
//                     System.out.println("admin ja existe");
//                 },
//                 () -> {
//                     var user = new User();
//                     user.setUsername("admin");
//                     user.setPassword(passwordEncoder.encode("123"));
//                     user.setRoles(List.of(roleAdmin));
//                     userRepository.save(user);
//                 }
//         );
//     }
}
