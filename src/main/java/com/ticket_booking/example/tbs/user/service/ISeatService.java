package com.ticket_booking.example.tbs.user.service;

import com.ticket_booking.example.tbs.user.model.Booking;
import com.ticket_booking.example.tbs.user.model.Seat;
import com.ticket_booking.example.tbs.user.model.ShowSeat;
import com.ticket_booking.example.tbs.user.model.ShowSeatDto;

import java.util.List;

public interface ISeatService {
    // add seats
    List<Seat> addSeats(long screenId);

    // add show seats
    List<ShowSeat> addShowSeats(ShowSeatDto showSeatDto);

    // get seats by show id
    List<ShowSeat> getSeatsByShowId(long showId);

    // get seats by list of showseat ids
    List<ShowSeat> getSeatsByShowSeatIds(List<Long> showSeatsIds);

    // get seats by screen id
    List<Seat> getSeatsByScreenId(long screenId);

    //get seat by show seat Id
    ShowSeat getShowSeatById(long seatId);

    // book seats
    List<ShowSeat> bookSeats(Booking booking, List<Long>showSeatIds);

    // release seats
    void releaseSeats();
}
