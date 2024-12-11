package com.example.demo.dto;

import java.time.Duration;

public record Service(String uuid,
                      String serviceName,
                      Duration duration,
                      Long price,
                      Barber barber) {

}
