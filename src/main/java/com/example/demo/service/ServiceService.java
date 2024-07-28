package com.example.demo.service;

import com.example.demo.dto.Service;

import java.util.List;

public interface ServiceService {

    List<Service> findBarberServices(String barberUuid);
    Service save(Service service);

}
