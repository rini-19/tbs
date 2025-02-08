package com.ticket_booking.example.tbs.admin.service;

import com.ticket_booking.example.tbs.admin.model.MovieShow;
import com.ticket_booking.example.tbs.admin.model.MovieShowDto;

import java.util.List;

public interface IMovieShowService {
    // add show
    MovieShow addShow(MovieShowDto movieShowDto);

    // remove show
    void removeShow(long showId);

    // get show by id
    MovieShow getShowById(long showId);

    // get all shows by location
    List<MovieShow> getAllShowsByLocation(long cityId);

    // get all shows by movieId
    List<MovieShow> getAllShowsByMovieId(long movieId);

    // get all shows by screen Id
    List<MovieShow> getAllShowsByScreenId(long screenId);

    // get show by movie id and screen id
    List<MovieShow> getAllShowsByMovieIdAndScreenId(long movieId, long screenId);

    // get all shows by movie id and cinema id
    List<MovieShow> getAllShowsByMovieIdAndCinemaId(long movieId, long cinemaId);
}
