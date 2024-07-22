package com.example.demo.dto.auth;

import com.example.demo.model.UserRole;

import java.time.LocalDateTime;
import java.util.UUID;

public record User(Long id,
                   String email,
                   String password,
                   UUID uuid,
                   UserRole userRole,
                   boolean locked,
                   boolean enabled,
                   LocalDateTime createdAt,
                   LocalDateTime updatedAt) {

    public User(Long id, String email, UUID uuid, UserRole userRole){
        this(id, email, null, uuid, userRole, false, true, null, null);
    }

    public User(String email, String password, UserRole userRole){
        this(null, email, password, null, userRole, false, true, null, null);
    }
}
