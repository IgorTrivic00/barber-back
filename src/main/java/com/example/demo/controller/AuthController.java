package com.example.demo.controller;

import com.example.demo.dto.Barber;
import com.example.demo.dto.Customer;
import com.example.demo.dto.User;
import com.example.demo.dto.UserSession;
import com.example.demo.dto.request_response.AuthenticationRequest;
import com.example.demo.dto.request_response.LogoutRequest;
import com.example.demo.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register-customer")
    public Customer registerCustomer(@RequestBody AuthenticationRequest request){
        logger.debug("====================[REGISTER CUSTOMER]====================]");
        return authenticationService.registerCustomer(request.customer()
                        .orElseThrow(() -> new IllegalArgumentException("Korisnik ne postoji")),
                request.user());
    }

    @PostMapping("/register-barber")
    public Barber registerBarber(@RequestBody AuthenticationRequest request) throws IOException {
        logger.debug("====================[REGISTER BARBER]====================]");
        return authenticationService.registerBarber(request.barber()
                        .orElseThrow(() -> new IllegalArgumentException("Korisnik ne postoji")),
                request.user());
    }

    @PostMapping("/login")
    public UserSession login(@RequestBody User user){
        logger.debug("====================[LOGIN USER]====================]");
        return authenticationService.login(user);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody LogoutRequest logoutRequest){
        return new ResponseEntity<>("", HttpStatusCode.valueOf(200));
    }

}
