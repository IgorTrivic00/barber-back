package com.example.demo.repository;

import com.example.demo.model.BarberEntity;
import com.example.demo.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BarberRepository extends JpaRepository<BarberEntity, Long> {

    BarberEntity findByUserEntity(UserEntity userEntity);
    //Optional<BarberEntity> findByUuid(UUID uuid);
    Optional<BarberEntity> findById(Long id);
    Optional<BarberEntity> findByUuid(String uuid);

  //  BarberEntity findByBarber(BarberEntity barberEntity);
}
