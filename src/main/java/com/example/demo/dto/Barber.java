package com.example.demo.dto;
import com.example.demo.model.enums.BarberTitle;

import java.util.Optional;

public record Barber(String uuid,
                     String name,
                     BarberTitle barberTitle,
                     String mobile,
                     Optional<Photo> photo,
                     Optional<Long> photoId) {
    public static Barber of(String uuid, String name, BarberTitle barberTitle, String mobile){
        return new Barber(uuid, name, barberTitle, mobile, Optional.empty(), Optional.empty());
    }
}
