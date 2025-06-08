package com.example.demo.service;

import com.example.demo.dto.Appointment;
import com.example.demo.dto.filter.AppointmentFilter;
import com.example.demo.dto.request_response.SearchResponse;

public interface AppointmentService {
    SearchResponse<Appointment> search(AppointmentFilter filter);
}
