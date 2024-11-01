package com.ticket_booking.example.tbs.admin.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "languages")
@Data
public class Languages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long languageId;

    @ManyToOne
    @JoinColumn(name = "movieId")
    private Movie movie;
    private String languageName;
}
