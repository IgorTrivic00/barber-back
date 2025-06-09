package com.example.demo.dto.filter;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public record SlotFilter(Optional<List<String>> barberUuids,
                         Optional<Date> from,
                         Optional<Date> to) {
}
