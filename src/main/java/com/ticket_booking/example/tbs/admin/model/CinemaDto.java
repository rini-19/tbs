package com.ticket_booking.example.tbs.admin.model;

import lombok.Data;

@Data
public class CinemaDto {
    private String cinemaName;
    private int pincode;
    private long cityId;
}
