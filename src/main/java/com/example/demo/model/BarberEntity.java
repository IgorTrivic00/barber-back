package com.example.demo.model;

import com.example.demo.dto.Barber;
import com.example.demo.model.enums.BarberTitle;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Entity
@Table(name = "barber")
public class BarberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;

    @Column(unique = true)
    @Getter
    private String uuid;

    private String name;

    @Enumerated(EnumType.STRING)
    private BarberTitle barberTitle;

    @OneToOne
    @JoinColumn(name = "app_user_id")
    @Setter
    private UserEntity userEntity;

    private String mobile;

    @ManyToOne
    @JoinColumn(name = "photo_id")
    @Setter
    private PhotoEntity photoEntity;

    public BarberEntity() {}

    public BarberEntity(Barber barber) {
        this.uuid = barber.uuid();
        update(barber);
    }

    public BarberEntity update(Barber barber) {
        this.name = barber.name();
        this.barberTitle = barber.barberTitle();
        this.mobile = barber.mobile();
        return this;
    }

    public Barber getDto(){
        return new Barber(
                uuid,
                name,
                barberTitle,
                mobile,
                Optional.empty(),
                photoEntity != null ? Optional.of(photoEntity.getId()) : Optional.empty()
        );
    }
}
