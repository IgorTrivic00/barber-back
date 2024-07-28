package com.example.demo.controller;

import com.example.demo.dto.Barber;
import com.example.demo.service.BarberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/barber")
public class BarberController {

    private final BarberService barberService;

    @Autowired
    public BarberController(BarberService barberService) {
        this.barberService = barberService;
    }

    @GetMapping("/find-all")
    public List<Barber> findAll(){
        return barberService.findAll();
    }

}
