package com.example.demo.model;
import com.example.demo.dto.Customer;
import jakarta.persistence.*;
import lombok.Setter;

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
    private String uuid;

    private String name;

    @Setter
    private String mobile;

    @OneToOne
    @JoinColumn(
            name = "app_user_id"
    )
    private UserEntity userEntity;

    public CustomerEntity() {}

    public CustomerEntity(String name, UserEntity userEntity) {
        this.name = name;
        this.userEntity = userEntity;
        this.uuid = UUID.randomUUID().toString();
    }

    public CustomerEntity(String name, String mobile, UserEntity userEntity) {
        this.name = name;
        this.userEntity = userEntity;
        this.uuid = UUID.randomUUID().toString();
        this.mobile = mobile;
    }

    public CustomerEntity update(String name, Optional<String> mobile){
        this.name = name;
        mobile.ifPresent(this::setMobile);
        return this;
    }

    public Customer getDto(){
        return new Customer(Optional.ofNullable(id), Optional.ofNullable(uuid), name, Optional.ofNullable(mobile), userEntity.getDto());
    }
}