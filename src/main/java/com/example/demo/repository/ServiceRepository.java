package com.example.demo.repository;

import com.example.demo.dto.Service;
import com.example.demo.model.BarberEntity;
import com.example.demo.model.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {

    List<ServiceEntity> findByBarber(BarberEntity barberEntity);

}
