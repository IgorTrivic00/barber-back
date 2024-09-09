package com.example.demo.controller;

import com.example.demo.dto.Service;
import com.example.demo.service.ServiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/service")
public class ServiceController {

    private static Logger logger = LoggerFactory.getLogger(ServiceController.class);

    private final ServiceService serviceService;

    @Autowired
    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping("/barber/{barberUuid}")
    public List<Service> findBarberServices(@PathVariable String barberUuid) {
        logger.debug("====================[FIND BARBER SERVICES]====================]");
        return serviceService.findBarberServices(barberUuid);
    }
}
