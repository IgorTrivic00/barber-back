package com.example.demo.model;

import com.example.demo.dto.Slot;
import com.example.demo.model.enums.SlotState;
import com.example.demo.model.enums.SlotType;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "slot")
public class SlotEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;

    @Column(unique = true)
    private String uuid;

    @Enumerated(EnumType.STRING)
    private SlotType slotType;

    @Enumerated(EnumType.STRING)
    private SlotState slotState;

    @ManyToOne
    @JoinColumn(name = "barber_id")
    private BarberEntity barber;

    @Column(name = "slot_start")
    @Temporal(TemporalType.TIMESTAMP)
    private Date start;

    @Column(name = "slot_end")
    @Temporal(TemporalType.TIMESTAMP)
    private Date end;

    public SlotEntity(){}

    public SlotEntity(Slot slot){
        this.uuid = slot.uuid();
        update(slot);
    }

    public SlotEntity update(Slot slot) {
        this.slotState = slot.slotState();
        this.slotType = slot.slotType();
        this.start = slot.start();
        this.end = slot.end();
        return this;
    }

    public void setBarber(BarberEntity barber) {
        this.barber = barber;
    }

    public Slot getDto(){
        return new Slot(uuid, slotType, slotState, barber.getDto(), start, end);
    }
}
