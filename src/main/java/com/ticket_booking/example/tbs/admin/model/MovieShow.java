package com.ticket_booking.example.tbs.admin.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "movieshow")
@Data
public class MovieShow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long showId;
    private LocalDateTime showStartTime;
    private LocalDateTime showEndTime;

    @ManyToOne
    @JoinColumn(name = "movieId")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "screenId")
    private Screen screen;
}
