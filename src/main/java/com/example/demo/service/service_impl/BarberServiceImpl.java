package com.example.demo.service.service_impl;

import com.example.demo.dto.Barber;
import com.example.demo.dto.User;
import com.example.demo.model.BarberEntity;
import com.example.demo.model.ServiceEntity;
import com.example.demo.model.UserEntity;
import com.example.demo.repository.BarberRepository;
import com.example.demo.repository.ServiceRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.BarberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BarberServiceImpl implements BarberService {

    private final BarberRepository barberRepository;
    private final UserRepository userRepository;


    @Autowired
    public BarberServiceImpl(BarberRepository barberRepository,
                             UserRepository userRepository) {
        this.barberRepository = barberRepository;
        this.userRepository = userRepository;

    }

    @Override
    public Barber save(Barber barber, UserEntity userEntity) {
        BarberEntity barberEntity = new BarberEntity(barber.name().trim(), barber.barberTitle(), userEntity);
        return barberRepository.save(barberEntity).getDto();
    }

    @Override
    public Barber findByUser(User user) {
        UserEntity userEntity = userRepository.findByEmail(user.email())
                .orElseThrow(() -> new UsernameNotFoundException("Korisnik ne postoji!"));
        return barberRepository.findByUserEntity(userEntity).getDto();
    }

    @Override
    public List<Barber> findAll() {
        return barberRepository.findAll().stream()
                .map(BarberEntity::getDto)
                .collect(Collectors.toList());
    }




}
