package com.example.demo.repository;

import com.example.demo.model.CustomerEntity;
import com.example.demo.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    Optional<CustomerEntity> findByUserEntity(UserEntity userEntity);
    Optional<CustomerEntity> findByUuid(String uuid);
}
