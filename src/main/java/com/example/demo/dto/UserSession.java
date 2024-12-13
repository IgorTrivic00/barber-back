package com.example.demo.dto;

import java.util.Optional;

public record UserSession(
        User user,
        Optional<Barber> barber,
        Optional<Customer> customer,
        String token
) {

    public UserSession(User user, Barber barber, String token){
        this(user, Optional.of(barber), Optional.empty(), token);
    }

    public UserSession(User user, Customer customer, String token){
        this(user, Optional.empty(), Optional.of(customer), token);
    }

    public UserSession(User user, String token){
        this(user, Optional.empty(), Optional.empty(), token);
    }

}
