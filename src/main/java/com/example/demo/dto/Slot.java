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
        return of(slot, SlotState.ALLOCATED);
    }

    public static Slot cancelSlot(Slot slot){
        return of(slot, SlotState.CANCELED);
    }

    public static Slot completeSlot(Slot slot){
        return of(slot, SlotState.COMPLETED);
    }

    private static Slot of(Slot slot, SlotState slotState){
        return new Slot(slot.uuid(), slot.slotType(), slotState, slot.start(), slot.end(), slot.barberUuid());
    }
}
