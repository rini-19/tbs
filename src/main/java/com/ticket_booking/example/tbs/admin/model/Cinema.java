package com.ticket_booking.example.tbs.admin.model;

import com.ticket_booking.example.tbs.location.model.Location;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cinema")
@Data
public class Cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cinemaId;
    private String cinemaName;

    @Column(unique = true, nullable = false)
    private int pincode;

    @ManyToOne
    @JoinColumn(name = "cityId")
    private Location location;
}
