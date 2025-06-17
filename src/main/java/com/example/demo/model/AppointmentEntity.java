package com.example.demo.model;

import com.example.demo.dto.Appointment;
import com.example.demo.model.enums.AppointmentState;
import jakarta.persistence.*;

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
    private CustomerEntity customer;

    @OneToOne
    @JoinColumn(name = "slot")
    private SlotEntity slot;

    @ManyToOne
    @JoinColumn(name = "barber")
    private BarberEntity barber;

    @ManyToOne
    @JoinColumn(name = "service")
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

    public void setBarber(BarberEntity barber) {
        this.barber = barber;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public void setSlot(SlotEntity slot) {
        this.slot = slot;
    }

    public void setService(ServiceEntity service) {
        this.service = service;
    }
}
