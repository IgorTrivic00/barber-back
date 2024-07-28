package com.example.demo.repository;

import com.example.demo.model.BarberEntity;
import com.example.demo.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarberRepository extends JpaRepository<BarberEntity, Long> {

    BarberEntity findByUserEntity(UserEntity userEntity);
}
