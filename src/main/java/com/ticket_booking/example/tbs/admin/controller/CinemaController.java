package com.ticket_booking.example.tbs.admin.controller;

import com.ticket_booking.example.tbs.admin.model.Cinema;
import com.ticket_booking.example.tbs.admin.model.CinemaDto;
import com.ticket_booking.example.tbs.admin.service.ICinemaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
//@RequestMapping("api/cinema")
public class CinemaController {

    @Autowired
    private ICinemaService cinemaService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("api/cinema/add")
    public ResponseEntity<?> addCinema(@RequestBody CinemaDto cinemaDto) {
        Cinema cinema = cinemaService.addCinema(cinemaDto);
        if(cinema == null) {
            return new ResponseEntity<>("Error adding this cinema", HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(cinema, HttpStatus.CREATED);
        }
    }

    @GetMapping("apiV1/cinema/all-cinemas/{cityId}")
    public ResponseEntity<?> getAllCinemas(@PathVariable long cityId) {
        List<Cinema> cinemas = cinemaService.getAllCinemaByLocation(cityId);
        if(cinemas == null || cinemas.isEmpty()) {
            return new ResponseEntity<>("Error getting all cinemas", HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(cinemas, HttpStatus.OK);
        }
    }

    @GetMapping("apiV1/cinema/{cinemaId}")
    public ResponseEntity<?> getCinema(@PathVariable long cinemaId) {
        Cinema cinema = cinemaService.getCinema(cinemaId);
        if(cinema == null) {
            return new ResponseEntity<>("Error getting cinema", HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(cinema, HttpStatus.OK);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("api/cinema/{cinemaId}")
    public ResponseEntity<?> updateCinema(@RequestBody CinemaDto cinemaDto, @PathVariable long cinemaId) {
        Cinema cinema = cinemaService.updateCinema(cinemaId, cinemaDto);
        if(cinema == null) {
            return new ResponseEntity<>("Error updating cinema", HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(cinemaDto, HttpStatus.OK);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("api/cinema/{cinemaId}")
    public ResponseEntity<?> deleteCinema(@PathVariable long cinemaId) {
        cinemaService.deleteCinema(cinemaId);
        return new ResponseEntity<>("Delete cinema successfully", HttpStatus.OK);
    }
}
