package com.example.demo.service.service_impl;

import com.example.demo.dto.auth.User;
import com.example.demo.model.UserEntity;
import com.example.demo.model.UserRole;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService {

    private static Logger log = Logger.getLogger(UserServiceImpl.class.getName());

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email.trim())
                .orElseThrow(() -> new IllegalArgumentException("Korisnik ne postoji!")).getDto();
    }

    @Override
    public User save(User user) {
        if(userRepository.findByEmail(user.email().trim()).isPresent()) {
            throw new IllegalArgumentException("Korisnik već postoji!");
        }

        UserEntity userEntity = new UserEntity(user.email().trim(), user.password(), UserRole.USER);
        return userRepository.save(userEntity).getDto();
    }
}
