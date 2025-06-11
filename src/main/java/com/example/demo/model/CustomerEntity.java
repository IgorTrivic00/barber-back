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

    @Setter
    private String name;

    @Setter
    private String mobile;

    @OneToOne
    @JoinColumn(
            name = "app_user_id"
    )
    private UserEntity userEntity;

    public CustomerEntity() {}

    public CustomerEntity(String uuid, String name, UserEntity userEntity) {
        this.name = name;
        this.userEntity = userEntity;
        this.uuid = uuid;
    }

    public CustomerEntity update(Optional<String> name, Optional<String> mobile){
        name.ifPresent(this::setName);
        mobile.ifPresent(this::setMobile);
        return this;
    }

    public Customer getDto(){
        return new Customer(uuid, name, Optional.ofNullable(mobile));
    }

    public String getUuid() {
        return uuid;
    }
}
