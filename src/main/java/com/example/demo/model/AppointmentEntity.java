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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer")
    private CustomerEntity customer;

    @Column(name = "customer_uuid")
    private String customerUuid;

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
        this.customerUuid = appointment.customerUuid();
        this.appointmentState = appointment.appointmentState();
        return this;
    }

    public Appointment getDto(){
        return new Appointment(uuid, customerUuid, slot.getDto(), barber.getDto(), service.getDto(), appointmentState);
    }

    public void setBarber(BarberEntity barber) {
        this.barber = barber;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customerUuid = customer.getUuid();
        this.customer = customer;
    }

    public void setSlot(SlotEntity slot) {
        this.slot = slot;
    }

    public void setService(ServiceEntity service) {
        this.service = service;
    }
}
