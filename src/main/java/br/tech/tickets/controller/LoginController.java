package br.tech.tickets.controller;

import br.tech.tickets.controller.dto.LoginRequest;
import br.tech.tickets.controller.dto.LoginResponse;
import br.tech.tickets.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/api")
public class LoginController {

    //private final JwtEncoder encoder;
    //private final UserRepository userRepository;
    //private final BCryptPasswordEncoder passwordEncoder;
//
    //public LoginController(JwtEncoder encoder, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
    //    this.encoder = encoder;
    //    this.userRepository = userRepository;
    //    this.passwordEncoder = passwordEncoder;
    //}
//
    //@PostMapping("/login")
    //public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
    //    var user = userRepository.findByEmail(loginRequest.email());
//
    //    if(user == null || !user.isLoginCorrect(loginRequest, passwordEncoder)) {
    //        throw new BadCredentialsException("Invalid email or password");
    //    }
//
    //    var now = Instant.now();
    //    var expiresIn = 300L;
//
    //    var claims = JwtClaimsSet.builder()
    //            .issuer("MyBackend")
    //            .subject(user.getEmail())
    //            .issuedAt(now)
    //            .expiresAt(now.plusSeconds(expiresIn))
    //            .build();
//
    //    var jwtValue = encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    //    return ResponseEntity.ok(new LoginResponse(jwtValue, expiresIn));
    //}

}
