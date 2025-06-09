package com.example.demo.dto;

import com.example.demo.model.enums.SlotState;
import com.example.demo.model.enums.SlotType;

import java.util.Date;

public record Slot(String uuid,
                   SlotType slotType,
                   SlotState slotState,
                   Barber barber,
                   Date start,
                   Date end) {
}
