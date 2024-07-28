package com.example.demo.controller;

import com.example.demo.dto.Barber;
import com.example.demo.dto.Customer;
import com.example.demo.dto.User;
import com.example.demo.dto.UserSession;
import com.example.demo.dto.request_response.LogoutRequest;
import com.example.demo.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register-customer")
    public Customer registerCustomer(@RequestBody Customer customer){
        return authenticationService.registerCustomer(customer);
    }

    @PostMapping("/register-barber")
    public Barber registerBarber(@RequestBody Barber barber){
        return authenticationService.registerBarber(barber);
    }

    @PostMapping("/login")
    public UserSession login(@RequestBody User user){
        return authenticationService.login(user);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody LogoutRequest logoutRequest){
        return new ResponseEntity<>("", HttpStatusCode.valueOf(200));
    }

}
