package com.example.demo.service;

import com.example.demo.dto.auth.User;

public interface UserService {

    User findByEmail(String email);

    User save(User user);
}
