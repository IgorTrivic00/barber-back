package com.example.demo.dto.filter;

import com.example.demo.model.enums.AppointmentState;

import java.util.List;
import java.util.Optional;

public record AppointmentFilter(Optional<List<String>> uuidsIn,
                                Optional<List<String>> customerUuids,
                                Optional<List<AppointmentState>> states) {
}
