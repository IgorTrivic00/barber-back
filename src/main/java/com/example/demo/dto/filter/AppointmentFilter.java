package com.example.demo.dto.filter;

import com.example.demo.model.enums.AppointmentStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public record AppointmentFilter(Optional<LocalDateTime> startTime,
                                Optional<LocalDateTime> endTime,
                                Optional<AppointmentStatus> status,
                                Optional<List<String>> barberUuids) {
}
