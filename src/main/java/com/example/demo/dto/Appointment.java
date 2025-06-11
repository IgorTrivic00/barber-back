package com.example.demo.dto;

import com.example.demo.model.enums.AppointmentState;

public record Appointment(String uuid,
                          String customerUuid,
                          Slot slot,
                          Barber barber,
                          Service service,
                          AppointmentState appointmentState) {
}
