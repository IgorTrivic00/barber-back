package com.example.demo.service.service_impl;

import com.example.demo.dto.Barber;
import com.example.demo.dto.User;
import com.example.demo.dto.exception.ResourceNotFoundException;
import com.example.demo.model.BarberEntity;
import com.example.demo.model.UserEntity;
import com.example.demo.repository.BarberRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.BarberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
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
        BarberEntity barberEntity = new BarberEntity(barber.uuid(), barber.name().trim(), barber.barberTitle(), userEntity);
        return barberRepository.save(barberEntity).getDto();
    }

    @Override
    public Barber findByUser(User user) {
        UserEntity userEntity = userRepository.findByEmail(user.email())
                .orElseThrow(() -> new UsernameNotFoundException("Korisnik ne postoji!"));
        return barberRepository.findByUserEntity(userEntity)
                .orElseThrow(() -> new ResourceNotFoundException("Frizer ne postoji!"))
                .getDto();
    }

    @Override
    public List<Barber> findAll() {
        return barberRepository.findAll().stream()
                .map(BarberEntity::getDto)
                .collect(Collectors.toList());
    }

    @Override
    public Barber deleteBarber(String uuid) {
        BarberEntity barberEntity = barberRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Barber ne postoji!"));

        barberRepository.delete(barberEntity);
        return barberEntity.getDto();

    }

    @Override
    public Barber update( Barber barber) {
    BarberEntity barberEntity = barberRepository.findByUuid(barber.uuid())
                .orElseThrow(() -> new ResourceNotFoundException("Barber ne postoji!"));

       barberEntity.update(barber.name().trim(),barber.barberTitle());
        return barberRepository.save(barberEntity).getDto();

    }

    @Override
    public Barber findByUuid(String uuid) {
       BarberEntity barberEntity = barberRepository.findByUuid(uuid)
               .orElseThrow(() -> new ResourceNotFoundException("Barber ne postoji!"));
        return barberEntity.getDto();
    }

}
