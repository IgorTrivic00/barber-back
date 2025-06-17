package com.example.demo.dto;

import com.example.demo.model.enums.AppointmentState;

public record Appointment(String uuid,
                          Customer customer,
                          Slot slot,
                          Barber barber,
                          Service service,
                          AppointmentState appointmentState) {
}
