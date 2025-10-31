package com.example.demo.service.service_impl;

import com.example.demo.dto.Photo;
import com.example.demo.model.PhotoEntity;
import com.example.demo.repository.PhotoRepository;
import com.example.demo.service.FileStorageService;
import com.example.demo.service.PhotoService;
import com.example.demo.utils.FileContentUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class PhotoServiceImpl implements PhotoService {

    private final FileStorageService storageService;
    private final PhotoRepository photoRepository;

    public PhotoServiceImpl(FileStorageService storageService,
                            PhotoRepository photoRepository) {
        this.storageService = storageService;
        this.photoRepository = photoRepository;
    }

    @Override
    public Photo store(String rootFolder, String originalName, InputStream photoStream) throws IOException {
        String filePath = FileContentUtils.getFilePath(null, rootFolder, originalName);
        Photo photo = Photo.of(originalName, originalName);
        storageService.importStream(photoStream, filePath);
        File photoFile = storageService.getFile(filePath);

        try {
            BufferedImage bufferedImage = ImageIO.read(photoFile);
            String contentType = Optional.ofNullable(Files.probeContentType(photoFile.toPath()))
                    .orElseGet(() -> URLConnection.guessContentTypeFromName(photoFile.getName()));
            String mediaType = contentType != null ? MediaType.parseMediaType(contentType).getType() : null;

            Photo photo1 = new Photo(photo.uuid(),
                    photo.name(),
                    photo.title(),
                    Optional.of(bufferedImage.getWidth()),
                    Optional.of(bufferedImage.getHeight()),
                    Optional.of(Files.size(photoFile.toPath())),
                    Optional.of(photoFile.getAbsolutePath()),
                    Optional.ofNullable(contentType),
                    Optional.ofNullable(mediaType)
            );
            PhotoEntity photoEntity = new PhotoEntity(photo1);
            return photoRepository.save(photoEntity).getDto();
        } catch (Exception exc) {
            try {
                Files.deleteIfExists(photoFile.toPath());
            } catch (Exception ignored) {
            }
            throw exc;
        }
    }


}
