package com.example.demo.model;

import com.example.demo.dto.Service;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
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
    private String uuid;

    private String serviceName;

    private Duration duration;

    private Long price;

    @ManyToOne
    @JoinColumn(name = "barber_id")
    @Setter
    private BarberEntity barber;

    @ManyToOne
    @JoinColumn(name = "photo_id")
    @Setter
    private PhotoEntity photoEntity;

    public ServiceEntity() {}

    public ServiceEntity(Service service) {
        this.uuid = service.uuid();
        update(service);
    }

    public ServiceEntity update(Service service) {
        this.serviceName = service.serviceName();
        this.duration = service.duration();
        this.price = service.price();
        return this;
    }

    public Service getDto(){
        return new Service(
                uuid,
                serviceName,
                duration,
                price,
                barber.getDto(),
                photoEntity != null ? Optional.of(photoEntity.getId()) : Optional.empty(),
                Optional.empty());
    }
}
