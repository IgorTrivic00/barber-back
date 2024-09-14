package com.example.demo.controller;

import com.example.demo.dto.Barber;
import com.example.demo.dto.Service;
import com.example.demo.service.ServiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping("/all")
    public List<Service> findAllServices() {
        logger.debug("====================[FIND ALL SERVICES]====================");
        return serviceService.findAllServices();
    }

    @PostMapping()
    public Service addService( @RequestBody Service service) {
        logger.debug("====================[ADD SERVICE]====================");
        return serviceService.addService(service);
    }

    @PutMapping()
    public Service updateService( @RequestBody Service service) {
        logger.debug("====================[UPDATE SERVICE]====================");
        return serviceService.updateService( service);
    }

    @DeleteMapping("/{uuid}")
    public Service deleteService(@PathVariable String uuid) {
        logger.debug("====================[DELETE SERVICE]====================");
        return serviceService.deleteService(uuid);
    }

}
