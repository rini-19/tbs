package com.ticket_booking.example.tbs.admin.controller;

import com.ticket_booking.example.tbs.admin.model.Movie;
import com.ticket_booking.example.tbs.admin.model.MovieDto;
import com.ticket_booking.example.tbs.admin.service.IMovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@Slf4j
//@RequestMapping("api/movie")
public class MovieController {

    @Autowired
    IMovieService movieService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("api/movie/add")
    public ResponseEntity<?> addMovie(@RequestBody MovieDto movieDto) {
        Movie movie = movieService.addMovie(movieDto);
        if(movie != null) {
            return new ResponseEntity<>(movie, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Error adding movie", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("apiV1/movie/{date}")
    public ResponseEntity<?> getMovieByReleaseDate(@PathVariable String date) {
        List<Movie> movies = movieService.getMovies(date);
        if(movies != null && !movies.isEmpty()) {
            return new ResponseEntity<>(movies, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error retrieving movies", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("api/movie/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable long id) {
        movieService.deleteMovie(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
