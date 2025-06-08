package com.example.demo.model;

import com.example.demo.dto.Appointment;
import com.example.demo.model.enums.AppointmentStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Table(name = "appointment")
public class AppointmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;

    @Column(unique = true)
    private String uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "barber_id")
    private BarberEntity barber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id")
    private ServiceEntity service;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    public AppointmentEntity() {}

    public AppointmentEntity(Appointment appointment){
        this.uuid = appointment.uuid();
        update(appointment);
    }

    public AppointmentEntity update(Appointment appointment){
        this.status = appointment.status();
        this.startTime = appointment.startTime();
        this.endTime = appointment.endTime();
        return this;
    }

    public Appointment getDto(){
        return new Appointment(
                uuid,
                startTime,
                endTime,
                barber.getDto(),
                status,
                Optional.ofNullable(service.getDto()),
                Optional.ofNullable(customer.getDto())
        );
    }

    public void setBarber(BarberEntity barber) {
        this.barber = barber;
    }

    public void setService(ServiceEntity service) {
        this.service = service;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }
}
