package com.example.demo.service;

import com.example.demo.dto.Slot;
import com.example.demo.dto.request_response.SlotAllocationRequest;

import java.util.List;

public interface SlotService {
    List<Slot> allocateSlots(SlotAllocationRequest request);
}
