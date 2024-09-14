package com.example.demo.dto;

import java.util.Optional;

public record Customer(String uuid,
                       String name,
                       Optional<String> mobile) {

    public Customer(String uuid, String name){
        this(uuid, name, Optional.empty());
    }

}
