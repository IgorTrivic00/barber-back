package com.example.demo.controller;

import com.example.demo.dto.Service;
import com.example.demo.service.ServiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/all")
    public List<Service> findAllServices() {
        logger.debug("====================[FIND ALL SERVICES]====================");
        return serviceService.findAllServices();
    }

//    i za sve ovo ne mora da ti vraca response entity moze samo da ti vrati record Service
//    uradicu samo na prvu metodu ti mozes za ostale posle obrisi komentar

//    promeni da se salje uuid a ne id frizera
    @PostMapping("/{barberId}/service")
    public Service addService(@PathVariable Long barberId, @RequestBody Service service) {
        return serviceService.addService(service, barberId);
    }

    //    promeni da se salje uuid a ne id usluge
    @PutMapping("/service/{serviceId}")
    public ResponseEntity<Service> updateService(@PathVariable Long serviceId,
                                                 @RequestBody Service service) {
        logger.debug("====================[UPDATE SERVICE]====================");
        Service updatedService = serviceService.updateService(serviceId, service);
        return ResponseEntity.ok(updatedService);
    }

    //    promeni da se salje uuid a ne id usluge
    @DeleteMapping("/service/{serviceId}")
    public ResponseEntity<Void> deleteService(@PathVariable Long serviceId) {
        logger.debug("====================[DELETE SERVICE]====================");
        serviceService.deleteService(serviceId);
        return ResponseEntity.ok().build();
    }

}
