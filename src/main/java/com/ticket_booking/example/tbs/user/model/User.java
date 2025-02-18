package com.ticket_booking.example.tbs.user.model;

import com.ticket_booking.example.tbs.location.model.Location;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    private String userName;
    private String userEmail;
    private long mobileNumber;
    private String password;
    private String role;
    private int pincode;

    @ManyToOne
    @JoinColumn(name = "cityId")
    private Location location;
}
