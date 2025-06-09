package com.example.demo.dto.filter;

import com.example.demo.model.enums.SlotState;

import javax.swing.plaf.nimbus.State;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public record SlotFilter(Optional<List<String>> barberUuids,
                         Optional<Date> from,
                         Optional<List<SlotState>> states,
                         Optional<Date> to) {
}
