package com.ticket_booking.example.tbs.user.service;

import com.ticket_booking.example.tbs.admin.model.MovieShow;
import com.ticket_booking.example.tbs.admin.service.IMovieShowService;
import com.ticket_booking.example.tbs.user.constants.BookingStatus;
import com.ticket_booking.example.tbs.user.constants.PaymentStatus;
import com.ticket_booking.example.tbs.user.constants.SeatStatus;
import com.ticket_booking.example.tbs.user.model.*;
import com.ticket_booking.example.tbs.user.repository.IBookingRepository;
import com.ticket_booking.example.tbs.user.repository.ITransactionRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Book;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;

@Service
@Slf4j
@Data
public class BookingService implements IBookingService{

    @Autowired
    IBookingRepository bookingRepository;

    @Autowired
    IAuthService authService;

    @Autowired
    IMovieShowService movieShowService;

    @Autowired
    ISeatService seatService;

    @Autowired
    ITransactionRepository transactionRepository;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Override
    public BookingInfo bookSeats(BookingDto bookingDto) {
        try {
            List<ShowSeat> seats = seatService.getSeatsByShowSeatIds(bookingDto.getShowSeatIds());
            for(ShowSeat seat : seats) {
                if(!Objects.equals(seat.getStatus(), SeatStatus.AVAILABLE.toString())) {
                    log.error("Seats unavailable");
                    throw new RuntimeException("Seats unavailable");
                }
            }
            Booking booking = new Booking();
            booking.setBookingStatus(BookingStatus.PENDING.toString());

            User user = authService.getUserById(bookingDto.getUserId());
            if(user == null) {
                log.error("User ID not valid");
                throw new RuntimeException("User ID not valid");
            }
            booking.setUser(user);

            booking.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));

            MovieShow show = movieShowService.getShowById(bookingDto.getShowId());
            booking.setMovieShow(show);

            booking.setTotalPayment(calculateTotalPayment(bookingDto.getShowSeatIds()));

            Booking savedBooking = bookingRepository.save(booking);
            List<ShowSeat> bookedSeats = seatService.bookSeats(savedBooking, bookingDto.getShowSeatIds());

            BookingInfo bookingInfo = new BookingInfo();
            bookingInfo.setBooking(savedBooking);
            bookingInfo.setBookedSeats(bookedSeats);

            return bookingInfo;

        } catch (Throwable ex) {
            log.error(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }

    private double calculateTotalPayment(List<Long> showSeatIds) {
        return showSeatIds.stream().map(seatId -> seatService.getShowSeatById(seatId)).mapToDouble(ShowSeat::getPrice).sum();
    }

    @Override
    @Transactional
    public Transaction completeTransaction(TransactionDto transactionDto) {
        try {
            boolean seatsAvailable = validateSeatAvailability(transactionDto.getBookingId(), transactionDto.getShowSeatIds());
            if(!seatsAvailable) {
                throw new RuntimeException("seats unavailable");
            }
            Transaction transaction = new Transaction();
            Booking booking = getBookingById(transactionDto.getBookingId());

            transaction.setBooking(booking);
            transaction.setTimeStamp(LocalDateTime.now());
            transaction.setPaymentMethod(transactionDto.getPaymentMethod());
            transaction.setPaymentStatus(PaymentStatus.PENDING.toString());

            long txnId = makePayment();
            if(txnId == 0){
                transaction.setPaymentStatus(PaymentStatus.FAILED.toString());
                updateBookingStatus(transactionDto.getBookingId(), BookingStatus.FAILED.toString());
                transactionRepository.save(transaction);
                throw new RuntimeException("transaction failed");
            } else {
                transaction.setPaymentStatus(PaymentStatus.COMPLETED.toString());
                transaction.setPaymentTxnId(txnId);
                updateBookingStatus(transactionDto.getBookingId(), BookingStatus.BOOKED.toString());
                transaction = transactionRepository.save(transaction);
            }
            return transaction;
        } catch (Throwable ex) {
            log.error(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }

    private long makePayment() {
        return 0;
    }

    @Transactional
    private void updateBookingStatus(long bookingId, String status) {
        try {
            Booking booking = getBookingById(bookingId);
            booking.setBookingStatus(status);
            bookingRepository.save(booking);
        } catch (Throwable ex) {
            throw new RuntimeException("Error updating booking status");
        }
    }

    private boolean validateSeatAvailability(long bookingId, List<Long> showSeatIds) {
        boolean isValid = true;
        for (long seatId : showSeatIds) {
            ShowSeat seat = seatService.getShowSeatById(seatId);
            if (Objects.equals(seat.getStatus(), SeatStatus.AVAILABLE.toString()) || (Objects.equals(seat.getStatus(), SeatStatus.BOOKED.toString()) && seat.getBooking().getBookingId() != bookingId)) {
                isValid = false;
                break;
            }
        }
        return isValid;
    }

    @Override
    public Booking getBookingById(long bookingId) {
        return bookingRepository.findById(bookingId).orElse(null);
    }
}
