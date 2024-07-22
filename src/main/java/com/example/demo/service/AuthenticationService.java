package com.example.demo.service;

import com.example.demo.dto.auth.User;
import com.example.demo.dto.auth.UserSession;

public interface AuthenticationService {

    UserSession login(User user);

    User register(User user);
}
