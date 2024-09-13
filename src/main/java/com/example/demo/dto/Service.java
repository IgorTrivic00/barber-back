package com.example.demo.dto;

import java.time.Duration;
import java.util.Optional;
import java.util.UUID;
/*
public record Service(Optional<Long> id,
                      Optional<UUID> uuid,
                      String serviceName,
                      Duration duration,
                      Long price,
                      Optional<Barber> barber) {

    public Service(String serviceName, Duration duration, Long price, Barber barber){
        this(Optional.empty(), Optional.empty(), serviceName, duration, price, Optional.of(barber));
    }

}*/

import java.time.Duration;
import java.util.Optional;

public record Service(Optional<Long> id,
                      Optional<String> uuid,
                      String serviceName,
                      Duration duration,
                      Long price,
                      Optional<Barber> barber) {

    public Service(String serviceName, Duration duration, Long price, Barber barber){
        this(Optional.empty(), Optional.empty(), serviceName, duration, price, Optional.of(barber));
    }

}
