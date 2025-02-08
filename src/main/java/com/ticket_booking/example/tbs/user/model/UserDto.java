package com.ticket_booking.example.tbs.user.model;

import lombok.Data;

@Data
public class UserDto {
    private String userName;
    private String userEmail;
    private long mobileNumber;
    private String password;
    private int pincode;
    long locationId;
    String role;
}
