package com.example.demo.service.service_impl;

import com.example.demo.dto.Slot;
import com.example.demo.dto.exception.ResourceNotFoundException;
import com.example.demo.dto.filter.SlotFilter;
import com.example.demo.dto.request_response.SearchResponse;
import com.example.demo.dto.request_response.SlotAllocationRequest;
import com.example.demo.model.BarberEntity;
import com.example.demo.model.SlotEntity;
import com.example.demo.repository.BarberRepository;
import com.example.demo.repository.SlotRepository;
import com.example.demo.service.SlotGenerator;
import com.example.demo.service.SlotService;
import com.example.demo.specification.SlotSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Override
    public SearchResponse<Slot> search(SlotFilter filter) {
        Specification<SlotEntity> search = SlotSpecification.search(filter);
        List<Slot> data = slotRepository.findAll(search).stream()
                .map(SlotEntity::getDto)
                .toList();

        return new SearchResponse<>(data, slotRepository.count(search));
    }
}
