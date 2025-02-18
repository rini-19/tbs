package com.ticket_booking.example.tbs.admin.controller;

import com.ticket_booking.example.tbs.admin.model.MovieShow;
import com.ticket_booking.example.tbs.admin.model.MovieShowDto;
import com.ticket_booking.example.tbs.admin.service.IMovieShowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
//@RequestMapping("api/show")
public class MovieShowController {

    @Autowired
    IMovieShowService movieShowService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("api/show/add")
    ResponseEntity<?> addMovieShow(@RequestBody MovieShowDto movieShowDto) {
        MovieShow show = movieShowService.addShow(movieShowDto);
        if(show != null) {
            return new ResponseEntity<>(show, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Error adding show", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("apiV1/show/location/{id}")
    ResponseEntity<?> getShowsByLocation(@PathVariable long id) {
        List<MovieShow> movieShows = movieShowService.getAllShowsByLocation(id);
        if(!movieShows.isEmpty()) {
            return new ResponseEntity<>(movieShows, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error getting shows by location", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("apiV1/show/movie/{id}")
    ResponseEntity<?> getShowsByMovie(@PathVariable long id) {
        List<MovieShow> movieShows = movieShowService.getAllShowsByMovieId(id);
        if(!movieShows.isEmpty()) {
            return new ResponseEntity<>(movieShows, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error getting shows by movie id", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("apiV1/show/screen/{movieId}/{screenId}")
    ResponseEntity<?> getShowsByMovieIdAndScreenId(@PathVariable long movieId, @PathVariable long screenId) {
        List<MovieShow> movieShows = movieShowService.getAllShowsByMovieIdAndScreenId(movieId, screenId);
        if(!movieShows.isEmpty()) {
            return new ResponseEntity<>(movieShows, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error getting shows by movie id and screen id", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("apiV1/show/cinema/{movieId}/{cinemaId}")
    ResponseEntity<?> getShowsByMovieIdAndCinemaId(@PathVariable long movieId, @PathVariable long cinemaId) {
        List<MovieShow> movieShows = movieShowService.getAllShowsByMovieIdAndCinemaId(movieId, cinemaId);
        if(!movieShows.isEmpty()) {
            return new ResponseEntity<>(movieShows, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error getting shows by movie id and cinema id", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("api/show/{id}")
    ResponseEntity<?> removeShow(@PathVariable long id) {
        movieShowService.removeShow(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
