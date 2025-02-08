package com.ticket_booking.example.tbs.user.controller;

import com.ticket_booking.example.tbs.user.model.Seat;
import com.ticket_booking.example.tbs.user.model.SeatDto;
import com.ticket_booking.example.tbs.user.model.ShowSeat;
import com.ticket_booking.example.tbs.user.model.ShowSeatDto;
import com.ticket_booking.example.tbs.user.service.ISeatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("api/seat")
public class SeatController {

    @Autowired
    ISeatService seatService;

    @PostMapping("/add")
    public ResponseEntity<?> addSeats(@RequestBody SeatDto seatDto) {
        List<Seat> seats = seatService.addSeats(seatDto.getScreenId());
        if(seats.isEmpty()) {
            return new ResponseEntity<>("Error adding seats", HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(seats, HttpStatus.CREATED);
        }
    }

    @PostMapping("/add/show-seats")
    public ResponseEntity<?> addShowSeats(@RequestBody ShowSeatDto showSeatDto) {
        List<ShowSeat> seats = seatService.addShowSeats(showSeatDto);
        if(seats.isEmpty()) {
            return new ResponseEntity<>("Error adding show seats", HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(seats, HttpStatus.CREATED);
        }
    }

    @GetMapping("/show/{showId}")
    public ResponseEntity<?> getSeatsByShowId(@PathVariable long showId) {
        List<ShowSeat> seats = seatService.getSeatsByShowId(showId);
        if(seats.isEmpty()) {
            return new ResponseEntity<>("Error getting show seats", HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(seats, HttpStatus.OK);
        }
    }
}
