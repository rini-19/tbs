package com.ticket_booking.example.tbs.user.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long txnId;

    private String paymentStatus;
    private String paymentMethod;
    private LocalDateTime timeStamp;
    private long paymentTxnId;

    @OneToOne
    @JoinColumn(name = "bookingId")
    Booking booking;
}
