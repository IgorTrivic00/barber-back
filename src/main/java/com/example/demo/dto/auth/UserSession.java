package com.example.demo.dto.auth;

public record UserSession(
        User user,
        String token
) {
}
