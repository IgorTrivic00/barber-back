package com.example.demo.service.service_impl;

import com.example.demo.dto.exception.ResourceNotFoundException;
import com.example.demo.dto.filter.ServiceFilter;
import com.example.demo.dto.request_response.SearchResponse;
import com.example.demo.model.BarberEntity;
import com.example.demo.model.ServiceEntity;
import com.example.demo.model.UserEntity;
import com.example.demo.repository.BarberRepository;
import com.example.demo.repository.ServiceRepository;
import com.example.demo.service.ServiceService;
import com.example.demo.specification.ServiceSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public com.example.demo.dto.Service addService(com.example.demo.dto.Service service) {
        BarberEntity barberEntity = barberRepository.findByUuid(service.barber().uuid())
                .orElseThrow(() -> new ResourceNotFoundException("Barber ne postoji!"));

        ServiceEntity serviceEntity = new ServiceEntity(service);
        serviceEntity.setBarber(barberEntity);

        return serviceRepository.save(serviceEntity).getDto();
    }

    @Override
    public com.example.demo.dto.Service updateService(com.example.demo.dto.Service service) {
        ServiceEntity serviceEntity = serviceRepository.findByUuid(service.uuid())
                .orElseThrow(() -> new ResourceNotFoundException("Usluga ne postoji!"));

        serviceEntity.update(service);
        return serviceRepository.save(serviceEntity).getDto();
    }

    @Override
    public SearchResponse<com.example.demo.dto.Service> search(ServiceFilter filter) {
        List<com.example.demo.dto.Service> data = serviceRepository.findAll(ServiceSpecification.search(filter)).stream()
                .map(ServiceEntity::getDto)
                .collect(Collectors.toList());

        return new SearchResponse<com.example.demo.dto.Service>(data, serviceRepository.count(ServiceSpecification.search(filter)));
    }

    @Override
    public SearchResponse<com.example.demo.dto.Service> findMyServices(UserEntity userEntity) {
        BarberEntity barberEntity = barberRepository.findByUserEntity(userEntity);
        return search(new ServiceFilter(List.of(barberEntity.getDto().uuid())));
    }

    @Override
    public com.example.demo.dto.Service deleteService(String uuid) {
        ServiceEntity serviceEntity = serviceRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Usluga ne postoji!"));

        serviceRepository.delete(serviceEntity);
        return serviceEntity.getDto();
    }




}
