package com.example.demo.service;

import com.example.demo.dto.Appointment;

import java.util.Optional;

public interface AppointmentService {
    Appointment scheduleAppointment(Appointment appointment);

    Optional<Appointment> findByUuid(String uuid);
}
