package com.example.demo.dto;

import com.example.demo.model.enums.UserRole;
import com.example.demo.utils.Validator;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public record User(Optional<Long> id,
                   String email,
                   Optional<String> password,
                   Optional<UUID> uuid,
                   UserRole userRole,
                   boolean locked,
                   boolean enabled,
                   Optional<LocalDateTime> createdAt,
                   Optional<LocalDateTime> updatedAt) {

    public User(Long id, String email, UUID uuid, UserRole userRole){
        this(Optional.of(id), email,Optional.empty(), Optional.of(uuid), userRole, false, true, Optional.empty(), Optional.empty());
    }

    public User(String email, String password, UserRole userRole){
        this(Optional.empty(), email, Optional.of(password), Optional.empty(), userRole, false, true, Optional.empty(), Optional.empty());
    }
}
