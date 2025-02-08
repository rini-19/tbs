package com.ticket_booking.example.tbs.user.model;

import lombok.Data;

@Data
public class ShowSeatDto {
    private long screenId;
    private double price;
    private long showId;
}
