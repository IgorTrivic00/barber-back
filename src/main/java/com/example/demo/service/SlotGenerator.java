package com.example.demo.service;

import com.example.demo.dto.Slot;
import com.example.demo.model.enums.SlotState;
import com.example.demo.model.enums.SlotType;

import java.util.*;

public class SlotGenerator {

    public static List<Slot> generate(SlotType slotType, Date from, Date to){
        return generate(slotType, from, to, SlotState.FREE);
    }

    public static List<Slot> generate(SlotType slotType, Date from, Date to, SlotState state){
        Calendar calendar = getCalendar(from);

        List<Slot> slots = new ArrayList<>();

        do {
            Date slotStart = calendar.getTime();
            Date slotEnd = offset((Calendar) calendar.clone(), getDuration(slotType));

            if (slotEnd.after(to)) break;

            slots.add(new Slot(UUID.randomUUID().toString(),
                    slotType,
                    state,
                    slotStart,
                    slotEnd,
                    null));

            calendar.setTime(slotEnd);
        }
        while (true);

        return slots;
    }

    private static Calendar getCalendar(Date from) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(from);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);

        return calendar;
    }

    private static Date offset(Calendar calendar, int amount) {
        calendar.add(Calendar.MINUTE, amount);
        return calendar.getTime();
    }

    private static int getDuration(SlotType slotType){
        return switch (slotType) {
            case FIFTEEN_MINUTES -> 15;
            case THIRTY_MINUTES -> 30;
            case FORTY_MINUTES -> 40;
            case FORTY_FIVE_MINUTES -> 45;
            case FIFTY_MINUTES -> 50;
            case HOUR -> 60;
            case HOUR_AND_FIFTEEN -> 75;
        };
    }
}
