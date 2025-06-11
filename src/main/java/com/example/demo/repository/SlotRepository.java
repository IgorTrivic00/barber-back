package com.example.demo.repository;

import com.example.demo.model.BarberEntity;
import com.example.demo.model.SlotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SlotRepository extends JpaRepository<SlotEntity, Long>, JpaSpecificationExecutor<SlotEntity> {
    Optional<SlotEntity> findByUuid(String uuid);
}
