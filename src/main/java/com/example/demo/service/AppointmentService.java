package com.example.demo.service;

import com.example.demo.dto.Appointment;
import com.example.demo.dto.filter.AppointmentFilter;
import com.example.demo.dto.request_response.SearchResponse;
import com.example.demo.model.UserEntity;

import java.util.Optional;

public interface AppointmentService {
    Appointment scheduleAppointment(Appointment appointment);

    Optional<Appointment> findByUuid(String uuid);

    SearchResponse<Appointment> search(AppointmentFilter filter);

    SearchResponse<Appointment> findMyAppointments(UserEntity userEntity, AppointmentFilter filter);
}
