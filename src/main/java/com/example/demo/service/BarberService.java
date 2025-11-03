package com.example.demo.service;

import com.example.demo.dto.Barber;
import com.example.demo.dto.User;
import com.example.demo.model.UserEntity;


import java.io.IOException;
import java.util.List;

public interface BarberService {

    Barber save(Barber barber, UserEntity userEntity) throws IOException;

    Barber save(Barber barber, UserEntity userEntity, byte[] photoBytes) throws IOException;

    Barber findByUser(User user);

    List<Barber> findAll();

    Barber delete(String uuid);

    Barber update( Barber barber);

    Barber findByUuid(String  uuid);

}
