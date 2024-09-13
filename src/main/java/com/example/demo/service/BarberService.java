package com.example.demo.service;

import com.example.demo.dto.Barber;
import com.example.demo.dto.Service;
import com.example.demo.dto.User;
import com.example.demo.model.UserEntity;


import java.util.List;
import java.util.Optional;

public interface BarberService {

    Barber save(Barber barber, UserEntity userEntity);

    Barber findByUser(User user);

    List<Barber> findAll();

    Barber deleteBarber(String uuid);


    Barber update( Barber barber);

    //Barber updateUserByBarber(String uuid,  Barber barber, String useruuid);

    Barber findByBarber(String  uuid);

}
