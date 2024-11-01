package com.ticket_booking.example.tbs.admin.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class MovieDto {
    private String movieTitle;
    private String plot;
    private String genre;
    private LocalDateTime releaseDate;
}