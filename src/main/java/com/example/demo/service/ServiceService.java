package com.example.demo.service;

import com.example.demo.dto.Service;
import com.example.demo.model.ServiceEntity;


import java.util.List;

public interface ServiceService {

    List<Service> findBarberServices(String barberUuid);
    Service save(Service service);

     List<Service> findAllServices() ;

    Service addService(Service service, Long barberId);


    void deleteService(Long serviceId);

    Service updateService(Long serviceId, Service service);


}
