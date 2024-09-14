package com.example.demo.dto.request_response;

import com.example.demo.dto.Barber;
import com.example.demo.dto.Customer;
import com.example.demo.dto.User;

import java.util.Optional;

public record AuthenticationRequest(User user,
                                    Optional<Barber> barber,
                                    Optional<Customer> customer) {

    public AuthenticationRequest(User user, Barber barber) {
        this(user, Optional.of(barber), Optional.empty());
    }

    public AuthenticationRequest(User user, Customer customer) {
        this(user, Optional.empty(), Optional.of(customer));
    }
}
