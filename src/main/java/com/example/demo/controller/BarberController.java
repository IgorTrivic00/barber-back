package com.example.demo.controller;

import com.example.demo.dto.Barber;
import com.example.demo.model.UserEntity;
import com.example.demo.service.BarberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/barber")
public class BarberController {

    private static Logger logger = LoggerFactory.getLogger(BarberController.class);
    private final BarberService barberService;

    @Autowired
    public BarberController(BarberService barberService) {
        this.barberService = barberService;
    }

    @GetMapping("/find-all")
    public List<Barber> findAll(){
        return barberService.findAll();
    }

    @DeleteMapping("/{uuid}")
    public Barber deleteBarber(@PathVariable String uuid) {
        logger.debug("====================[DELETE BARBER]====================");
        return barberService.deleteBarber(uuid);
    }

    @PostMapping()
    public Barber addBarber(@RequestBody Barber barber, @RequestBody UserEntity user) {
        logger.debug("====================[ADD BARBER]====================");
        return barberService.save(barber,user);
    }

    @PutMapping()
    public Barber updateBarber( @RequestBody Barber barber) {
        logger.debug("====================[UPDATE BARBER]====================");
        return barberService.update(barber);
    }

    @GetMapping("/{uuid}")
    public Barber findByUuid(@PathVariable String uuid) {
        logger.debug("====================[UPDATE USER BY BARBER]====================");
        return barberService.findByUuid(uuid);
    }
}
