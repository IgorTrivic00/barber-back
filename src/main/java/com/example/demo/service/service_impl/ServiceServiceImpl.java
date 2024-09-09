package com.example.demo.service.service_impl;

import com.example.demo.model.BarberEntity;
import com.example.demo.model.ServiceEntity;
import com.example.demo.repository.BarberRepository;
import com.example.demo.repository.ServiceRepository;
import com.example.demo.service.ServiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ServiceServiceImpl implements ServiceService {

    private static final Logger logger = LoggerFactory.getLogger(ServiceServiceImpl.class);

    private final ServiceRepository serviceRepository;
    private final BarberRepository barberRepository;

    @Autowired
    public ServiceServiceImpl(ServiceRepository serviceRepository,
                              BarberRepository barberRepository) {
        this.serviceRepository = serviceRepository;
        this.barberRepository = barberRepository;
    }

    @Override
    public List<com.example.demo.dto.Service> findBarberServices(String barberUuid) {
        BarberEntity barberEntity = barberRepository.findByUuid(UUID.fromString(barberUuid))
                .orElseThrow(() -> new IllegalArgumentException("Barber ne postoji!"));

        return serviceRepository.findByBarber(barberEntity).stream()
                .map(ServiceEntity::getDto)
                .collect(Collectors.toList());
    }

    @Override
    public com.example.demo.dto.Service save(com.example.demo.dto.Service service) {
        BarberEntity barberEntity = barberRepository.findById(service.barber().get().id().get())
                .orElseThrow(() -> new IllegalArgumentException("Barber ne postoji!"));
        ServiceEntity serviceEntity = new ServiceEntity(service.serviceName().trim(), service.duration(), service.price(), barberEntity);
        return serviceRepository.save(serviceEntity).getDto();
    }
}
