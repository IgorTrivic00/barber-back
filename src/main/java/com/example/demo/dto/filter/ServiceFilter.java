package com.example.demo.dto.filter;

import java.util.List;
import java.util.Optional;

public record ServiceFilter(Optional<List<String>> barberUuids,
                            Optional<List<String>> uuidsIn) {

    public static ServiceFilter of(List<String> barberUuids){
        return new ServiceFilter(Optional.of(barberUuids), Optional.empty());
    }

}
