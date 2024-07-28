package com.example.demo.dto;

import com.example.demo.model.enums.BarberTitle;

import java.util.Optional;
import java.util.UUID;

public record Barber(Optional<Long> id,
                     Optional<UUID> uuid,
                     String name,
                     BarberTitle barberTitle,
                     User user) {

    public Barber(String name, BarberTitle barberTitle, User user){
        this(Optional.empty(), Optional.empty(), name, barberTitle, user);
    }

    public Barber(Long id){
        this(Optional.of(id), Optional.empty(), null, null, null);
    }
}
