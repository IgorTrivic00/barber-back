package com.example.demo.dto.request_response;

import com.example.demo.model.enums.SlotType;

import java.util.Date;

public record SlotAllocationRequest(String barberUuid,
                                    Date from,
                                    Date to,
                                    SlotType slotType) {
}
