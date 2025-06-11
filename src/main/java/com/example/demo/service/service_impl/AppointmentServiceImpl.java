package com.example.demo.service.service_impl;

import com.example.demo.dto.Appointment;
import com.example.demo.dto.Slot;
import com.example.demo.dto.exception.ResourceNotFoundException;
import com.example.demo.dto.exception.SlotAlreadyAllocatedException;
import com.example.demo.model.*;
import com.example.demo.model.enums.SlotState;
import com.example.demo.repository.*;
import com.example.demo.service.AppointmentService;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final CustomerRepository customerRepository;
    private final BarberRepository barberRepository;
    private final SlotRepository slotRepository;
    private final ServiceRepository serviceRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository,
                                  CustomerRepository customerRepository,
                                  BarberRepository barberRepository,
                                  SlotRepository slotRepository,
                                  ServiceRepository serviceRepository) {
        this.appointmentRepository = appointmentRepository;
        this.customerRepository = customerRepository;
        this.barberRepository = barberRepository;
        this.slotRepository = slotRepository;
        this.serviceRepository = serviceRepository;
    }

    @Override
    public Appointment scheduleAppointment(Appointment appointment) {
        CustomerEntity customerEntity = customerRepository.findByUuid(appointment.customerUuid())
                .orElseThrow(() -> new ResourceNotFoundException("Korisnik ne postoji!"));

        BarberEntity barberEntity = barberRepository.findByUuid(appointment.barber().uuid())
                .orElseThrow(() -> new ResourceNotFoundException(("Barber ne postoji!")));

        SlotEntity slotEntity = slotRepository.findByUuid(appointment.slot().uuid())
                .orElseThrow(() -> new ResourceNotFoundException(("Korisnik ne postoji!")));

        ServiceEntity serviceEntity = serviceRepository.findByUuid(appointment.service().uuid())
                .orElseThrow(() -> new ResourceNotFoundException(("Usluga ne postoji!")));

        Slot slot = slotEntity.getDto();

        if(slot.slotState().equals(SlotState.ALLOCATED)){
            throw new SlotAlreadyAllocatedException("Nije moguce zakazati u ovom terminu!");
        }

        AppointmentEntity appointmentEntity = new AppointmentEntity(appointment);

        appointmentEntity.setBarber(barberEntity);
        appointmentEntity.setSlot(slotEntity);
        appointmentEntity.setCustomer(customerEntity);
        appointmentEntity.setService(serviceEntity);

        slotEntity.update(new Slot(slot.uuid(), slot.slotType(), SlotState.ALLOCATED, slot.start(), slot.end(), slot.barberUuid()));

        slotRepository.save(slotEntity);

        return appointmentRepository.save(appointmentEntity).getDto();
    }
}
