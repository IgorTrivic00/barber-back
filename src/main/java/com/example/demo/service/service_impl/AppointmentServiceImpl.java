package com.example.demo.service.service_impl;

import com.example.demo.dto.Appointment;
import com.example.demo.dto.Slot;
import com.example.demo.dto.exception.ResourceNotFoundException;
import com.example.demo.dto.exception.SlotAlreadyAllocatedException;
import com.example.demo.dto.filter.AppointmentFilter;
import com.example.demo.dto.request_response.SearchResponse;
import com.example.demo.model.*;
import com.example.demo.model.enums.SlotState;
import com.example.demo.model.enums.UserRole;
import com.example.demo.repository.*;
import com.example.demo.service.AppointmentService;
import com.example.demo.specification.AppointmentSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public Appointment scheduleAppointment(Appointment appointment) {
        CustomerEntity customerEntity = customerRepository.findByUuid(appointment.customer().uuid())
                .orElseThrow(() -> new ResourceNotFoundException("Korisnik ne postoji!"));

        BarberEntity barberEntity = barberRepository.findByUuid(appointment.barber().uuid())
                .orElseThrow(() -> new ResourceNotFoundException(("Barber ne postoji!")));

        SlotEntity slotEntity = slotRepository.findByUuid(appointment.slot().uuid())
                .orElseThrow(() -> new ResourceNotFoundException(("Termin ne postoji!")));

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

        slotEntity.update(Slot.allocateSlot(slot));

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
        if(userEntity.getUserRole().equals(UserRole.CUSTOMER)){
            CustomerEntity customerEntity = customerRepository.findByUserEntity(userEntity)
                    .orElseThrow(() -> new ResourceNotFoundException("Korisnik ne postoji!"));
            return search(new AppointmentFilter(filter.uuidsIn(), Optional.of(List.of(customerEntity.getUuid())), Optional.empty(), filter.states()));
        }else if(userEntity.getUserRole().equals(UserRole.BARBER)){
            BarberEntity barberEntity = barberRepository.findByUserEntity(userEntity)
                    .orElseThrow(() -> new ResourceNotFoundException("Frizer ne postoji!"));
            return search(new AppointmentFilter(filter.uuidsIn(), Optional.empty(), Optional.of(List.of(barberEntity.getUuid())), filter.states()));
        }
        throw new RuntimeException("Error");
    }

    @Override
    @Transactional
    public Appointment cancelAppointment(Appointment appointment) {
        AppointmentEntity appointmentEntity = appointmentRepository.findByUuid(appointment.uuid())
                .orElseThrow(() -> new ResourceNotFoundException("Termin ne postoji!"));

        SlotEntity slotEntity = slotRepository.findByUuid(appointment.slot().uuid())
                .orElseThrow(() -> new ResourceNotFoundException(("Termin ne postoji!")));

        Slot slot = slotEntity.getDto();

        if(!slot.slotState().equals(SlotState.ALLOCATED)){
            throw new SlotAlreadyAllocatedException("Nije moguce otkazati termin!");
        }

        slotEntity.update(Slot.cancelSlot(slot));
        slotRepository.save(slotEntity);

        appointmentEntity.update(Appointment.cancelAppointment(appointment));
        return appointmentRepository.save(appointmentEntity).getDto();
    }

    @Override
    @Transactional
    public Appointment completeAppointment(Appointment appointment) {
        AppointmentEntity appointmentEntity = appointmentRepository.findByUuid(appointment.uuid())
                .orElseThrow(() -> new ResourceNotFoundException("Termin ne postoji!"));

        SlotEntity slotEntity = slotRepository.findByUuid(appointment.slot().uuid())
                .orElseThrow(() -> new ResourceNotFoundException(("Termin ne postoji!")));

        Slot slot = slotEntity.getDto();

        if(!slot.slotState().equals(SlotState.ALLOCATED)){
            throw new SlotAlreadyAllocatedException("Nije moguce završiti termin!");
        }

        slotEntity.update(Slot.completeSlot(slot));
        slotRepository.save(slotEntity);

        appointmentEntity.update(Appointment.completeAppointment(appointment));
        return appointmentRepository.save(appointmentEntity).getDto();
    }
}
