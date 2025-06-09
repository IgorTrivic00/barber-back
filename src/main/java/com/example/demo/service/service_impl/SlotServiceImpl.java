package com.example.demo.service.service_impl;

import com.example.demo.dto.Slot;
import com.example.demo.dto.exception.ResourceNotFoundException;
import com.example.demo.dto.request_response.SlotAllocationRequest;
import com.example.demo.model.BarberEntity;
import com.example.demo.model.SlotEntity;
import com.example.demo.repository.BarberRepository;
import com.example.demo.repository.SlotRepository;
import com.example.demo.service.SlotGenerator;
import com.example.demo.service.SlotService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlotServiceImpl implements SlotService {

    private final SlotRepository slotRepository;
    private final BarberRepository barberRepository;

    public SlotServiceImpl(SlotRepository slotRepository,
                           BarberRepository barberRepository) {
        this.slotRepository = slotRepository;
        this.barberRepository = barberRepository;
    }

    @Override
    public List<Slot> allocateSlots(SlotAllocationRequest request) {
        BarberEntity barberEntity = barberRepository.findByUuid(request.barberUuid())
                .orElseThrow(() -> new ResourceNotFoundException("Barber ne postoji!"));

        List<Slot> allocatedSlots = SlotGenerator.generate(request.slotType(), request.from(), request.to());

        List<SlotEntity> slotEntities = allocatedSlots.stream()
                .map(SlotEntity::new)
                .peek(slotEntity -> slotEntity.setBarber(barberEntity))
                .toList();

        return slotRepository.saveAll(slotEntities).stream()
                .map(SlotEntity::getDto)
                .toList();
    }
}
