package com.ticket_booking.example.tbs.user.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "notification_medium")
@Data
public class NotificationMedium {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long mediumId;

    private String channel;

    @ManyToOne
    @JoinColumn(name = "userId")
    User user;
}
