package com.ticket_booking.example.tbs.user.controller;

import com.ticket_booking.example.tbs.user.model.BookingDto;
import com.ticket_booking.example.tbs.user.model.BookingInfo;
import com.ticket_booking.example.tbs.user.model.Transaction;
import com.ticket_booking.example.tbs.user.model.TransactionDto;
import com.ticket_booking.example.tbs.user.service.IBookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("api/book")
public class BookingController {

    @Autowired
    IBookingService bookingService;

    @PostMapping("/seats")
    public ResponseEntity<?> bookSeats(@RequestBody BookingDto bookingDto) {
        BookingInfo booking = bookingService.bookSeats(bookingDto);
        if(booking == null) {
            return new ResponseEntity<>("Error booking seats", HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(booking, HttpStatus.CREATED);
        }
    }

    @PostMapping("/transaction")
    public ResponseEntity<?> completeTransaction(@RequestBody TransactionDto transactionDto) {
        Transaction transaction = bookingService.completeTransaction(transactionDto);
        if(transaction == null) {
            return new ResponseEntity<>("Error while completing transaction", HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(transaction, HttpStatus.CREATED);
        }
    }
}