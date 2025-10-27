package com.example.demo.model;

import com.example.demo.dto.Appointment;
import com.example.demo.model.enums.AppointmentState;
import jakarta.persistence.*;
import lombok.Setter;

@Entity
@Table(name = "appointment")
public class AppointmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String uuid;

    @ManyToOne
    @JoinColumn(name = "customer")
    @Setter
    private CustomerEntity customer;

    @ManyToOne
    @JoinColumn(name = "slot")
    @Setter
    private SlotEntity slot;

    @ManyToOne
    @JoinColumn(name = "barber")
    @Setter
    private BarberEntity barber;

    @ManyToOne
    @JoinColumn(name = "service")
    @Setter
    private ServiceEntity service;

    @Enumerated(EnumType.STRING)
    private AppointmentState appointmentState;

    public AppointmentEntity() {}

    public AppointmentEntity(Appointment appointment){
        this.uuid = appointment.uuid();
        update(appointment);
    }

    public AppointmentEntity update(Appointment appointment){
        this.appointmentState = appointment.appointmentState();
        return this;
    }

    public Appointment getDto(){
        return new Appointment(uuid, customer.getDto(), slot.getDto(), barber.getDto(), service.getDto(), appointmentState);
    }
}
