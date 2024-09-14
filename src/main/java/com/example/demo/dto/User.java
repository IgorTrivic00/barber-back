package com.example.demo.dto;

import com.example.demo.model.enums.UserRole;
import java.time.LocalDateTime;
import java.util.Optional;

public record User(String uuid,
                   String email,
                   Optional<String> password,
                   UserRole userRole,
                   boolean locked,
                   boolean enabled) {

    public User(String uuid, String email, String password, UserRole userRole){
        this(uuid, email, Optional.of(password), userRole, false, true);
    }

    public User(String uuid, String email, UserRole userRole){
        this(uuid, email, Optional.empty(), userRole, false, true);
    }
}
