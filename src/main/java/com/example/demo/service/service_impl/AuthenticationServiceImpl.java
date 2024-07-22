package com.example.demo.service.service_impl;

import com.example.demo.dto.auth.User;
import com.example.demo.dto.auth.UserSession;
import com.example.demo.model.UserEntity;
import com.example.demo.security.JwtService;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private static Logger log = Logger.getLogger(AuthenticationServiceImpl.class.getName());

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationServiceImpl(UserService userService,
                                     AuthenticationManager authenticationManager,
                                     JwtService jwtService,
                                     PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserSession login(User user) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.email(),
                        user.password()
                )
        );
        User user1 = userService.findByEmail(user.email());
        String token = jwtService.generateToken(new UserEntity(user1.email(), user1.password(), user1.userRole()));
        return new UserSession(user1, token);
    }

    @Override
    public User register(User user) {
        return userService.save(new User(user.email(), passwordEncoder.encode(user.password().trim()), user.userRole()));
    }
}
