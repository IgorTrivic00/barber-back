package com.example.demo.model;

import com.example.demo.dto.Service;
import jakarta.persistence.*;

import java.time.Duration;
import java.util.Optional;
import java.util.UUID;

@Entity
@Table(name = "service")
public class ServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;
    @Column(unique = true)
    private UUID uuid;
    private String serviceName;
    private Duration duration;
    private Long price;
    @ManyToOne
    @JoinColumn(name = "barber_id")
    private BarberEntity barber;

    public ServiceEntity() {}

    public ServiceEntity(String serviceName, Duration duration, Long price, BarberEntity barber) {
        this.uuid = UUID.randomUUID();
        this.serviceName = serviceName;
        this.duration = duration;
        this.price = price;
        this.barber = barber;
    }
    public ServiceEntity update(String serviceName, Duration duration, Long price) {
        this.serviceName = serviceName;
        this.duration = duration;
        this.price = price;
        return this;
    }
    public Service getDto(){
        return new Service(Optional.ofNullable(id), Optional.of(uuid), serviceName, duration, price, Optional.ofNullable(barber.getDto()));
    }

}
