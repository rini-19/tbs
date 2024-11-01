package com.ticket_booking.example.tbs.admin.service;

import com.ticket_booking.example.tbs.admin.model.Movie;
import com.ticket_booking.example.tbs.admin.model.MovieDto;

import java.util.Date;
import java.util.List;

public interface IMovieService {

    //add movie
    Movie addMovie(MovieDto movieDto);

    //get all movies by release date
    List<Movie> getMovies(String releaseDate);

    //remove movie
    void deleteMovie(long movieId);
}
