package com.ticket_booking.example.tbs.user.service;

import com.ticket_booking.example.tbs.user.model.*;

public interface IBookingService {

    // book seats
    BookingInfo bookSeats(BookingDto bookingDto);

    // confirm seats
    Transaction completeTransaction(TransactionDto transactionDto);

    //get booking by Id
    Booking getBookingById(long bookingId);
}
