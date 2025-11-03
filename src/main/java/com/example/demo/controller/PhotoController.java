package com.example.demo.controller;

import com.example.demo.dto.Photo;
import com.example.demo.service.PhotoService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/v1/content/photo")
public class PhotoController {

    private final static Logger logger = LoggerFactory.getLogger(PhotoController.class);

    private final PhotoService photoService;

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping("/{ownerUuid}/{id}")
    public ResponseEntity<?> getPhoto(@PathVariable String ownerUuid, @PathVariable Long id,
                                      HttpServletRequest request) throws IOException {
        logger.debug("====================[GET PHOTO]==================== {} {}", ownerUuid, id);
        Photo photo = photoService.getById(id, ownerUuid);
        return serveContent(photo);
    }

    private ResponseEntity<?> serveContent(Photo photo) throws IOException {
        File file = photo.file().orElseThrow();

        HttpHeaders httpHeaders = new HttpHeaders();

        final ContentDisposition.Builder builder = ContentDisposition.builder("attachment")
                .name(photo.title())
                .filename(photo.name(), Charset.forName("UTF-8"));

        photo.size().ifPresent(builder::size);
        photo.contentType().ifPresent(s -> {
            MediaType mt = MediaType.parseMediaType(s);
            httpHeaders.setContentType(mt);
        });

        httpHeaders.setContentDisposition(builder.build());
        httpHeaders.setCacheControl(CacheControl.maxAge(1, TimeUnit.DAYS));

        byte[] bytes = Files.readAllBytes(file.toPath());
        return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);
    }
}
