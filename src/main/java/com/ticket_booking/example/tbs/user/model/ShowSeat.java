package com.ticket_booking.example.tbs.user.model;

import com.ticket_booking.example.tbs.admin.model.MovieShow;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "showseat")
@Data
public class ShowSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double price;
    private String status;

    @ManyToOne
    @JoinColumn(name = "showId")
    private MovieShow movieShow;

    @OneToOne
    @JoinColumn(name = "seatId")
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "bookingId")
    private Booking booking;
}
