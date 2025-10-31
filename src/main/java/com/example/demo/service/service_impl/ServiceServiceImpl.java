package com.example.demo.service.service_impl;

import com.example.demo.dto.Photo;
import com.example.demo.dto.exception.ResourceNotFoundException;
import com.example.demo.dto.filter.ServiceFilter;
import com.example.demo.dto.request_response.SearchResponse;
import com.example.demo.model.BarberEntity;
import com.example.demo.model.PhotoEntity;
import com.example.demo.model.ServiceEntity;
import com.example.demo.model.UserEntity;
import com.example.demo.repository.BarberRepository;
import com.example.demo.repository.PhotoRepository;
import com.example.demo.repository.ServiceRepository;
import com.example.demo.service.PhotoService;
import com.example.demo.service.ServiceService;
import com.example.demo.specification.ServiceSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceServiceImpl implements ServiceService {

    private static final Logger logger = LoggerFactory.getLogger(ServiceServiceImpl.class);

    private final ServiceRepository serviceRepository;
    private final BarberRepository barberRepository;
    private final PhotoService photoService;
    private final PhotoRepository photoRepository;

    @Autowired
    public ServiceServiceImpl(ServiceRepository serviceRepository,
                              BarberRepository barberRepository,
                              PhotoService photoService,
                              PhotoRepository photoRepository) {
        this.serviceRepository = serviceRepository;
        this.barberRepository = barberRepository;
        this.photoService = photoService;
        this.photoRepository = photoRepository;
    }

    @Override
    public com.example.demo.dto.Service addService(com.example.demo.dto.Service service, byte[] bytes) throws IOException {
        BarberEntity barberEntity = barberRepository.findByUuid(service.barber().uuid())
                .orElseThrow(() -> new ResourceNotFoundException("Barber ne postoji!"));

        ServiceEntity serviceEntity = new ServiceEntity(service);
        serviceEntity.setBarber(barberEntity);

        if(bytes.length != 0){
            logger.debug("Photo for service included, storing photo...");
            Photo photo = service.photo().orElseThrow(() -> new IllegalArgumentException("No photo!"));
            photo = photoService.store("service", photo.name(), service.uuid(), new ByteArrayInputStream(bytes));
            PhotoEntity photoEntity = photoRepository
                    .findByIdAndOwnerUuid(photo.id(), photo.ownerUuid()).orElseThrow(() -> new IllegalArgumentException("No stored photo!"));
            serviceEntity.setPhotoEntity(photoEntity);
        }

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
        BarberEntity barberEntity = barberRepository.findByUserEntity(userEntity)
                .orElseThrow(() -> new ResourceNotFoundException("Frizer ne postoji!"));
        return search(new ServiceFilter(List.of(barberEntity.getUuid())));
    }

    @Override
    public com.example.demo.dto.Service deleteService(String uuid) {
        ServiceEntity serviceEntity = serviceRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Usluga ne postoji!"));

        serviceRepository.delete(serviceEntity);
        return serviceEntity.getDto();
    }

}
