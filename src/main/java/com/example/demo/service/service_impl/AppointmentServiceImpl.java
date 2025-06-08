package com.example.demo.service.service_impl;

import com.example.demo.dto.Appointment;
import com.example.demo.dto.filter.AppointmentFilter;
import com.example.demo.dto.request_response.SearchResponse;
import com.example.demo.model.AppointmentEntity;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.service.AppointmentService;
import com.example.demo.specification.AppointmentSpecification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public SearchResponse<Appointment> search(AppointmentFilter filter) {
        List<Appointment> data = appointmentRepository.findAll(AppointmentSpecification.search(filter)).stream()
                .map(AppointmentEntity::getDto)
                .toList();

        return new SearchResponse<Appointment>(data, appointmentRepository.count(AppointmentSpecification.search(filter)));
    }
}
