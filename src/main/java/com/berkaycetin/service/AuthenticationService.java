package com.berkaycetin.service;

import com.berkaycetin.config.Role;
import com.berkaycetin.controller.UserRequest;
import com.berkaycetin.controller.UserResponse;
import com.berkaycetin.entities.User;
import com.berkaycetin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(UserRepository userRepository,
                                 JwtService jwtService,
                                 AuthenticationManager authenticationManager,
                                 PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    //  Kullanıcı kayıt
    public UserResponse save(User user) {
        // Eğer aynı username varsa hata fırlat
        if (userRepository.findByUserName(user.getUserName()).isPresent()) {
            throw new RuntimeException("Bu kullanıcı adı zaten mevcut!");
        }

        User newUser = new User();
        newUser.setUserName(user.getUserName());
        newUser.setPassword(passwordEncoder.encode(user.getPassword())); // Şifre hash
        newUser.setEmail(user.getEmail());
        newUser.setRole(Role.USER); // Varsayılan USER rolü

        userRepository.save(newUser);

        //  Token rol bilgisiyle birlikte oluşturuluyor
        String token = jwtService.generateToken(newUser);

        return new UserResponse(newUser.getUserName(), token);
    }

    //  Login
    public UserResponse auth(UserRequest userRequest) {
        // Kullanıcıyı authenticate et
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userRequest.getUserName(),
                        userRequest.getPassword()
                )
        );

        // Kullanıcıyı dbden bulma
        User user = userRepository
                .findByUserName(userRequest.getUserName())
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

        //  Token rol bilgisiyle oluşturuluyor
        String token = jwtService.generateToken(user);

        return new UserResponse(user.getUserName(), token);
    }
}
