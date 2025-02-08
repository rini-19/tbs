package com.ticket_booking.example.tbs.user.model;

import lombok.Data;

import java.util.List;

@Data
public class TransactionDto {
    private String paymentMethod;
    private long bookingId;
    private List<Long> showSeatIds;
    private long userId;
}
