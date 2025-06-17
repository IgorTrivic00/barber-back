package com.example.demo.dto;

import com.example.demo.model.enums.AppointmentState;

public record Appointment(String uuid,
                          Customer customer,
                          Slot slot,
                          Barber barber,
                          Service service,
                          AppointmentState appointmentState) {

    public static Appointment cancelAppointment(Appointment appointment){
        return new Appointment(appointment.uuid(), appointment.customer(), appointment.slot(), appointment.barber(), appointment.service(), AppointmentState.CANCELLED);
    }

    public static Appointment completeAppointment(Appointment appointment){
        return new Appointment(appointment.uuid(), appointment.customer(), appointment.slot(), appointment.barber(), appointment.service(), AppointmentState.COMPLETED);
    }

}
