package com.ticket_booking.example.tbs.admin.service;

import com.ticket_booking.example.tbs.admin.model.Cinema;
import com.ticket_booking.example.tbs.admin.model.CinemaDto;

import java.util.List;

public interface ICinemaService {

    // add cinema
    Cinema addCinema(CinemaDto cinemaDto);

    // get all cinema by locationid
    List<Cinema> getAllCinemaByLocation(long cityId);

    // get cinema by id
    Cinema getCinema(long cinemaId);

    // update cinema
    Cinema updateCinema(long cinemaId, CinemaDto cinemaDto);

    //delete cinema
    void deleteCinema(long cinemaId);
}
