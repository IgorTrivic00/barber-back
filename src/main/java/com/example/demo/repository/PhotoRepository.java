package com.example.demo.repository;

import com.example.demo.model.PhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhotoRepository extends JpaRepository<PhotoEntity, Long> {
    Optional<PhotoEntity> findByIdAndOwnerUuid(Long id, String ownerUuid);

}
