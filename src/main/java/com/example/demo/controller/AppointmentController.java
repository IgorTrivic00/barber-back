package com.example.demo.controller;

import com.example.demo.dto.Appointment;
import com.example.demo.dto.exception.ResourceNotFoundException;
import com.example.demo.dto.filter.AppointmentFilter;
import com.example.demo.dto.request_response.SearchResponse;
import com.example.demo.model.UserEntity;
import com.example.demo.service.AppointmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/appointment")
public class AppointmentController {

    private static final Logger logger = LoggerFactory.getLogger(AppointmentController.class);

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/schedule")
    public Appointment scheduleAppointment(@RequestBody Appointment appointment) {
        logger.debug("====================[SCHEDULE APPOINTMENT]====================]");
        return appointmentService.scheduleAppointment(appointment);
    }


    @GetMapping("/uuid/{uuid}")
    public Appointment findByUuid(@PathVariable String uuid){
        logger.debug("====================[FIND APPOINTMENT BY UUID]====================");
        return appointmentService.findByUuid(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Termin ne postoji!"));
    }

    @PostMapping("/my-appointments")
    public SearchResponse<Appointment> findMyAppointments(@RequestBody AppointmentFilter filter,
                                                          @AuthenticationPrincipal UserEntity userEntity){
        logger.debug("====================[FIND MY APPOINTMENTS]====================");
        return appointmentService.findMyAppointments(userEntity, filter);
    }

    @PostMapping("/search")
    public SearchResponse<Appointment> search(@RequestBody AppointmentFilter filter){
        logger.debug("====================[SEARCH APPOINTMENT]====================");
        return appointmentService.search(filter);
    }

}
