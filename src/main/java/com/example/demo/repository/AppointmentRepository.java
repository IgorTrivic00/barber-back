package com.example.demo.repository;

import com.example.demo.model.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long>, JpaSpecificationExecutor<AppointmentEntity> {

    Optional<AppointmentEntity> findByUuid(String uuid);

}
