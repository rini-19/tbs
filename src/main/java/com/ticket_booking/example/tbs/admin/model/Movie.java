package com.ticket_booking.example.tbs.admin.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "movie")
@Data
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long movieId;
    private String movieTitle;
    private String plot;
    private String genre;
    private LocalDateTime releaseDate;
}
