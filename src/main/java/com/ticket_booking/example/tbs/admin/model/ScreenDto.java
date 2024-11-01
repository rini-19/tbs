package com.ticket_booking.example.tbs.admin.model;

import lombok.Data;

import java.util.List;

@Data
public class ScreenDto {
    private long cinemaId;
    private List<Integer> totalSeats;
}
