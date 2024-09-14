package com.example.demo.dto;
import com.example.demo.model.enums.BarberTitle;

public record Barber(String uuid,
                     String name,
                     BarberTitle barberTitle) {

}
