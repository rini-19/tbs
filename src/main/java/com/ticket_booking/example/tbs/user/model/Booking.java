package com.ticket_booking.example.tbs.user.model;


import com.ticket_booking.example.tbs.admin.model.MovieShow;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "booking")
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookingId;

    private String bookingStatus;
    private double totalPayment;
    private Timestamp timestamp;

    @ManyToOne
    @JoinColumn(name = "showId")
    MovieShow movieShow;

    @ManyToOne
    @JoinColumn(name = "userId")
    User user;
}
