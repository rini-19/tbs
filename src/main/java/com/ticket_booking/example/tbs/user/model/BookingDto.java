package com.ticket_booking.example.tbs.user.model;

import lombok.Data;

import java.util.List;

@Data
public class BookingDto {
    List<Long> showSeatIds;
    long showId;
    long userId;
}
