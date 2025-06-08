package com.example.demo.dto;

import com.example.demo.model.enums.AppointmentStatus;

import java.time.LocalDateTime;
import java.util.Optional;

public record Appointment(String uuid,
                          LocalDateTime startTime,
                          LocalDateTime endTime,
                          Barber barber,
                          AppointmentStatus status,
                          Optional<Service> service,
                          Optional<Customer> customer) {
}
