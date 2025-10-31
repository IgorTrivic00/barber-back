package com.example.demo.dto;

import java.time.Duration;
import java.util.Optional;

public record Service(String uuid,
                      String serviceName,
                      Duration duration,
                      Long price,
                      Barber barber,
                      Optional<Long> photoId,
                      Optional<Photo> photo) {

}
