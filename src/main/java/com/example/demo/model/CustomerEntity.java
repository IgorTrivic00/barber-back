package com.example.demo.model;
import com.example.demo.dto.Customer;
import jakarta.persistence.*;
import lombok.Getter;
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
    @Getter
    private String uuid;

    private String name;

    @OneToOne
    @JoinColumn(
            name = "app_user_id"
    )
    @Setter
    private UserEntity userEntity;

    private String mobile;

    public CustomerEntity() {}

    public CustomerEntity(Customer customer) {
        this.uuid = customer.uuid();
        update(customer);
    }

    public CustomerEntity update(Customer customer){
        this.name = customer.name();
        this.mobile = customer.mobile();
        return this;
    }

    public Customer getDto(){
        return new Customer(uuid, name, mobile);
    }
}
