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

    public static Slot allocateSlot(Slot slot){
        return new Slot(slot.uuid(), slot.slotType(), SlotState.ALLOCATED, slot.start(), slot.end(), slot.barberUuid());
    }

    public static Slot cancelSlot(Slot slot){
        return new Slot(slot.uuid(), slot.slotType(), SlotState.CANCELED, slot.start(), slot.end(), slot.barberUuid());
    }

    public static Slot completeSlot(Slot slot){
        return new Slot(slot.uuid(), slot.slotType(), SlotState.COMPLETED, slot.start(), slot.end(), slot.barberUuid());
    }

}
