package com.example.demo.controller;

import com.example.demo.dto.Appointment;
import com.example.demo.dto.filter.AppointmentFilter;
import com.example.demo.dto.request_response.SearchResponse;
import com.example.demo.service.AppointmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/appointment")
public class AppointmentController {

    private static final Logger logger = LoggerFactory.getLogger(AppointmentController.class);

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/search")
    public SearchResponse<Appointment> search(@RequestBody AppointmentFilter filter){
        logger.debug("====================[SEARCH APPOINTMENT]====================]");
        return appointmentService.search(filter);
    }
}
