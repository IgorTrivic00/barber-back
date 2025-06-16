package com.example.demo.service.service_impl;

import com.example.demo.dto.Appointment;
import com.example.demo.dto.Slot;
import com.example.demo.dto.exception.ResourceNotFoundException;
import com.example.demo.dto.exception.SlotAlreadyAllocatedException;
import com.example.demo.dto.filter.AppointmentFilter;
import com.example.demo.dto.request_response.SearchResponse;
import com.example.demo.model.*;
import com.example.demo.model.enums.SlotState;
import com.example.demo.repository.*;
import com.example.demo.service.AppointmentService;
import com.example.demo.specification.AppointmentSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public Optional<Appointment> findByUuid(String uuid) {
        return appointmentRepository.findByUuid(uuid)
                .map(AppointmentEntity::getDto);
    }

    @Override
    public SearchResponse<Appointment> search(AppointmentFilter filter) {
        Specification<AppointmentEntity> specification = AppointmentSpecification.search(filter);
        List<Appointment> data = appointmentRepository.findAll(specification).stream()
                .map(AppointmentEntity::getDto)
                .toList();
        return new SearchResponse<>(data, appointmentRepository.count(specification));
    }

    @Override
    public SearchResponse<Appointment> findMyAppointments(UserEntity userEntity, AppointmentFilter filter) {
        CustomerEntity customerEntity = customerRepository.findByUserEntity(userEntity);
        return search(new AppointmentFilter(filter.uuidsIn(), Optional.of(List.of(customerEntity.getUuid())), filter.states()));
    }
}
