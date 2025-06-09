package com.example.demo.model;

import com.example.demo.dto.Barber;
import com.example.demo.model.enums.BarberTitle;
import jakarta.persistence.*;

import java.util.Optional;

@Entity
@Table(name = "barber")
public class BarberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;

    @Column(unique = true)
    private String uuid;

    private String name;

    @Enumerated(EnumType.STRING)
    private BarberTitle barberTitle;

    @OneToOne
    @JoinColumn(name = "app_user_id")
    private UserEntity userEntity;

    public BarberEntity() {}

    public BarberEntity(String uuid, String name, BarberTitle barberTitle, UserEntity userEntity) {
        this.name = name;
        this.uuid = uuid;
        this.barberTitle = barberTitle;
        this.userEntity = userEntity;
    }

    public Barber getDto(){
        return new Barber(uuid, name, barberTitle);
    }

    public BarberEntity update(String name, BarberTitle barberTitle) {
        this.name = name;
        this.barberTitle = barberTitle;
        return this;
    }
}
