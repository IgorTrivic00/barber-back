package com.example.demo.dto;

import java.util.Optional;
import java.util.UUID;

public record Customer(Optional<Long> id,
                       Optional<UUID> uuid,
                       String name,
                       Optional<String> mobile,
                       User user) {

    public Customer(String name, String mobile, User user){
        this(Optional.empty(), Optional.empty(), name, Optional.of(mobile), user);
    }

}
