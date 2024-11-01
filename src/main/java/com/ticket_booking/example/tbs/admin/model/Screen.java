package com.ticket_booking.example.tbs.admin.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "screen")
@Data
public class Screen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long screenId;
    private int screenNumber;

    @ManyToOne
    @JoinColumn(name = "cinemaId")
    private Cinema cinema;
    private int totalSeats;
}
