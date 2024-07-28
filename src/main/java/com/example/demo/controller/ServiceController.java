package com.example.demo.controller;

import com.example.demo.dto.Service;
import com.example.demo.service.ServiceService;
import com.example.demo.service.service_impl.AuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/service")
public class ServiceController {

    private static Logger log = Logger.getLogger(ServiceController.class.getName());

    private final ServiceService serviceService;

    @Autowired
    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping("/barber/{barberUuid}")
    public List<Service> findBarberServices(@PathVariable String barberUuid) {
        return serviceService.findBarberServices(barberUuid);
    }
}
