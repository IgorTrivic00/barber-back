package com.example.demo.dto;

import java.util.Optional;
import java.util.UUID;

public record Photo(String uuid,
                    String name,
                    String title,
                    Optional<Integer> width,
                    Optional<Integer> height,
                    Optional<Long> size,
                    Optional<String> url,
                    Optional<String> contentType,
                    Optional<String> mediaType) {

    public static Photo of(String name, String title){
        return new Photo(
                UUID.randomUUID().toString(),
                name,
                title,
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty());
    }
}
