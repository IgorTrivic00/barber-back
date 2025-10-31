package com.example.demo.service;

import com.example.demo.dto.Photo;

import java.io.IOException;
import java.io.InputStream;

public interface PhotoService {
    Photo store(String rootFolder, String originalName, String ownerUuid, InputStream photoStream) throws IOException;

    Photo getById(Long id, String ownerUuid);

}
