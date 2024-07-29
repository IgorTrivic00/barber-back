package com.example.demo.model;

import com.example.demo.dto.Barber;
import com.example.demo.dto.Customer;
import jakarta.persistence.*;

import java.util.Optional;
import java.util.UUID;

@Entity
@Table(name = "customer")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;
    @Column(unique = true)
    private UUID uuid;
    private String name;
    private String mobile;
    @OneToOne
    @JoinColumn(
            name = "app_user_id"
    )
    private UserEntity userEntity;

    public CustomerEntity() {}

    public CustomerEntity(String name, String mobile, UserEntity userEntity) {
        this.name = name;
        this.userEntity = userEntity;
        this.uuid = UUID.randomUUID();
        this.mobile = mobile;
    }

    public CustomerEntity update(String name){
        this.name = name;
        return this;
    }

    public Customer getDto(){
        return new Customer(Optional.of(id), Optional.of(uuid), name, Optional.of(mobile), userEntity.getDto());
    }
}
