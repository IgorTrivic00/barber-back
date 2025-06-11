package com.example.demo.service.service_impl;

import com.example.demo.dto.Appointment;
import com.example.demo.dto.exception.ResourceNotFoundException;
import com.example.demo.dto.exception.SlotAlreadyAllocatedException;
import com.example.demo.model.AppointmentEntity;
import com.example.demo.model.BarberEntity;
import com.example.demo.model.CustomerEntity;
import com.example.demo.model.SlotEntity;
import com.example.demo.model.enums.SlotState;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.BarberRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.SlotRepository;
import com.example.demo.service.AppointmentService;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final CustomerRepository customerRepository;
    private final BarberRepository barberRepository;
    private final SlotRepository slotRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository,
                                  CustomerRepository customerRepository,
                                  BarberRepository barberRepository,
                                  SlotRepository slotRepository) {
        this.appointmentRepository = appointmentRepository;
        this.customerRepository = customerRepository;
        this.barberRepository = barberRepository;
        this.slotRepository = slotRepository;
    }

    @Override
    public Appointment scheduleAppointment(Appointment appointment) {
        CustomerEntity customerEntity = customerRepository.findByUuid(appointment.customerUuid())
                .orElseThrow(() -> new ResourceNotFoundException("Korisnik ne postoji!"));

        BarberEntity barberEntity = barberRepository.findByUuid(appointment.barber().uuid())
                .orElseThrow(() -> new ResourceNotFoundException(("Barber ne postoji!")));

        SlotEntity slotEntity = slotRepository.findByUuid(appointment.slot().uuid())
                .orElseThrow(() -> new ResourceNotFoundException(("Korisnik ne postoji!")));

        if(slotEntity.getDto().slotState().equals(SlotState.ALLOCATED)){
            throw new SlotAlreadyAllocatedException("Nije moguce zakazati u ovom terminu!");
        }

        AppointmentEntity appointmentEntity = new AppointmentEntity(appointment);

        appointmentEntity.setBarber(barberEntity);
        appointmentEntity.setSlot(slotEntity);
        appointmentEntity.setCustomer(customerEntity);

        return appointmentRepository.save(appointmentEntity).getDto();
    }
}
