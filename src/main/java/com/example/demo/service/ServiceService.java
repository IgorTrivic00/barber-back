package com.example.demo.service;

import com.example.demo.dto.Service;
import com.example.demo.model.ServiceEntity;


import java.util.List;
import java.util.UUID;

public interface ServiceService {

    List<Service> findBarberServices(String barberUuid);

    Service save(Service service);

    List<Service> findAllServices();

    Service addService(Service service, Long barberId);

    Service deleteService(String uuid);

    Service updateService(String uuid, Service service);

}
