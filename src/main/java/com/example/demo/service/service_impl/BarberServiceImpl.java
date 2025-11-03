package com.example.demo.service.service_impl;

import ch.qos.logback.core.rolling.helper.FileStoreUtil;
import com.example.demo.dto.Barber;
import com.example.demo.dto.Photo;
import com.example.demo.dto.User;
import com.example.demo.dto.exception.ResourceNotFoundException;
import com.example.demo.model.BarberEntity;
import com.example.demo.model.PhotoEntity;
import com.example.demo.model.UserEntity;
import com.example.demo.repository.BarberRepository;
import com.example.demo.repository.PhotoRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.BarberService;
import com.example.demo.service.PhotoService;
import com.example.demo.utils.FileContentUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BarberServiceImpl implements BarberService {

    private static final Logger logger = LoggerFactory.getLogger(BarberServiceImpl.class);

    private final BarberRepository barberRepository;
    private final UserRepository userRepository;
    private final PhotoService photoService;
    private final PhotoRepository photoRepository;

    @Autowired
    public BarberServiceImpl(BarberRepository barberRepository,
                             UserRepository userRepository,
                             PhotoService photoService,
                             PhotoRepository photoRepository) {
        this.barberRepository = barberRepository;
        this.userRepository = userRepository;
        this.photoService = photoService;
        this.photoRepository = photoRepository;
    }

    @Override
    public Barber save(Barber barber, UserEntity userEntity) throws IOException {
        return save(barber, userEntity, null);
    }

    @Override
    public Barber save(Barber barber, UserEntity userEntity, byte[] photoBytes) throws IOException {
        BarberEntity barberEntity = new BarberEntity(Barber.of(barber.uuid(), barber.name().trim(), barber.barberTitle(), barber.mobile()));
        barberEntity.setUserEntity(userEntity);

        if(photoBytes != null){
            logger.debug("Photo for barber included, storing photo...");
            Photo photo = barber.photo().orElseThrow(() -> new IllegalArgumentException("No photo!"));
            photo = photoService.store("barber", photo.name(), barber.uuid(), new ByteArrayInputStream(photoBytes));
            PhotoEntity photoEntity = photoRepository
                    .findByIdAndOwnerUuid(photo.id(), photo.ownerUuid()).orElseThrow(() -> new IllegalArgumentException("No stored photo!"));
            barberEntity.setPhotoEntity(photoEntity);
        }

        return barberRepository.save(barberEntity).getDto();
    }

    @Override
    public Barber findByUser(User user) {
        UserEntity userEntity = userRepository.findByEmail(user.email())
                .orElseThrow(() -> new UsernameNotFoundException("Korisnik ne postoji!"));
        return barberRepository.findByUserEntity(userEntity)
                .orElseThrow(() -> new ResourceNotFoundException("Frizer ne postoji!"))
                .getDto();
    }

    @Override
    public List<Barber> findAll() {
        return barberRepository.findAll().stream()
                .map(BarberEntity::getDto)
                .collect(Collectors.toList());
    }

    @Override
    public Barber delete(String uuid) {
        BarberEntity barberEntity = barberRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Barber ne postoji!"));

        barberRepository.delete(barberEntity);
        return barberEntity.getDto();

    }

    @Override
    public Barber update(Barber barber) {
        BarberEntity barberEntity = barberRepository.findByUuid(barber.uuid())
                .orElseThrow(() -> new ResourceNotFoundException("Barber ne postoji!"));

       barberEntity.update(Barber.of(barber.uuid(), barber.name().trim(), barber.barberTitle(), barber.mobile()));
       return barberRepository.save(barberEntity).getDto();
    }

    @Override
    public Barber findByUuid(String uuid) {
       BarberEntity barberEntity = barberRepository.findByUuid(uuid)
               .orElseThrow(() -> new ResourceNotFoundException("Barber ne postoji!"));
        return barberEntity.getDto();
    }

}
