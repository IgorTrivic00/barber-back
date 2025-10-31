package com.example.demo.dto;

import java.io.File;
import java.util.Optional;
import java.util.UUID;

public record Photo(Long id,
                    String uuid,
                    String name,
                    String title,
                    String ownerUuid,
                    Optional<Integer> width,
                    Optional<Integer> height,
                    Optional<Long> size,
                    Optional<String> url,
                    Optional<String> contentType,
                    Optional<String> mediaType,
                    Optional<File> file) {

    public static Photo of(String name, String title, String ownerUuid){
        return new Photo(
                0L,
                UUID.randomUUID().toString(),
                name,
                title,
                ownerUuid,
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty());
    }
}
