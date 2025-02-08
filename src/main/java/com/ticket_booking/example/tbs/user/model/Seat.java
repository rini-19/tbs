package com.ticket_booking.example.tbs.user.model;

import com.ticket_booking.example.tbs.admin.model.Screen;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "seat")
@Data
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seatId;

    private int seatNumber;

    @ManyToOne
    @JoinColumn(name = "screenId")
    private Screen screen;

}
