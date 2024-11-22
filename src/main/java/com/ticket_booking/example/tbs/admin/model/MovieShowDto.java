package com.ticket_booking.example.tbs.admin.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MovieShowDto {
    private LocalDateTime showStartTime;
    private LocalDateTime showEndTime;
    private long movieId;
    private long screenId;
}
