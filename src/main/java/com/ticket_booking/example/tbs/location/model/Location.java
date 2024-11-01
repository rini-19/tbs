package com.ticket_booking.example.tbs.location.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "city")
@Data
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cityId;
    private String cityName;
    private String state;
}
