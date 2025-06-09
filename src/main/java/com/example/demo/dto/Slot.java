package com.example.demo.dto;

import com.example.demo.model.enums.SlotState;
import com.example.demo.model.enums.SlotType;

import java.util.Date;
import java.util.Optional;

public record Slot(String uuid,
                   SlotType slotType,
                   SlotState slotState,
                   Date start,
                   Date end,
                   String barberUuid) {
}
