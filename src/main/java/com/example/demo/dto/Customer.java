package com.example.demo.dto;

import java.util.Optional;
import java.util.UUID;

public record Customer(Optional<Long> id,
                       Optional<UUID> uuid,
                       String name,
                       User user) {

    public Customer(String name, User user){
        this(Optional.empty(), Optional.empty(), name, user);
    }

}
