package com.example.demo.service;

import com.example.demo.dto.Barber;
import com.example.demo.dto.Service;
import com.example.demo.model.ServiceEntity;


import java.util.List;
import java.util.UUID;

public interface ServiceService {

    List<Service> findBarberServices(String uuid);

    Service save(Service service);

    List<Service> findAllServices();

    Service addService(Service service);

    Service deleteService(String uuid);

    Service updateService( Service service);

}
