package com.example.demo.model;

import com.example.demo.dto.Photo;
import jakarta.persistence.*;

import java.util.Optional;

@Entity
@Table(name = "photo")
public class PhotoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;

    @Column(unique = true)
    private String uuid;

    private String name;

    private String title;

    private long size;

    private String url;

    private int width;

    private int height;

    private String contentType;

    private String mediaType;

    public PhotoEntity(){
    }

    public PhotoEntity(Photo photo){
        this.uuid = photo.uuid();
        update(photo);
    }

    public PhotoEntity update(Photo photo) {
        this.title = photo.title();
        this.name = photo.name();
        this.size = photo.size().orElse(0L);
        this.url = photo.url().orElse(null);
        this.width = photo.width().orElse(0);
        this.height = photo.height().orElse(0);
        this.contentType = photo.contentType().orElse(null);
        this.mediaType = photo.mediaType().orElse(null);
        return this;
    }

    public Photo getDto(){
        return new Photo(
                uuid,
                name,
                title,
                Optional.of(width),
                Optional.of(height),
                Optional.of(size),
                Optional.ofNullable(url),
                Optional.ofNullable(contentType),
                Optional.ofNullable(mediaType)
        );
    }

}
