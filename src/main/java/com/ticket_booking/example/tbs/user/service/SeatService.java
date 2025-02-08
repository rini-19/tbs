package com.ticket_booking.example.tbs.user.service;

import com.ticket_booking.example.tbs.admin.model.MovieShow;
import com.ticket_booking.example.tbs.admin.model.Screen;
import com.ticket_booking.example.tbs.admin.service.IMovieShowService;
import com.ticket_booking.example.tbs.admin.service.IScreenService;
import com.ticket_booking.example.tbs.user.constants.SeatStatus;
import com.ticket_booking.example.tbs.user.model.Booking;
import com.ticket_booking.example.tbs.user.model.Seat;
import com.ticket_booking.example.tbs.user.model.ShowSeat;
import com.ticket_booking.example.tbs.user.model.ShowSeatDto;
import com.ticket_booking.example.tbs.user.repository.ISeatRepository;
import com.ticket_booking.example.tbs.user.repository.IShowSeatRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SeatService implements ISeatService{

    @Autowired
    ISeatRepository seatRepository;

    @Autowired
    IShowSeatRepository showSeatRepository;

    @Autowired
    IScreenService screenService;

    @Autowired
    IMovieShowService movieShowService;

    @Override
    public List<Seat> addSeats(long screenId) {
        try {
            Screen screen = screenService.getScreenById(screenId);

            if(screen == null) {
                throw new RuntimeException("Screen not found");
            }

            List<Seat> seats = new ArrayList<>();
            int totalSeats = screen.getTotalSeats();
            for(int seatNo = 1; seatNo <= totalSeats; seatNo++) {
                Seat seat = new Seat();
                seat.setSeatNumber(seatNo);
                seat.setScreen(screen);
                seats.add(seat);
            }
            seats = seatRepository.saveAll(seats);
            return seats;

        } catch (Throwable ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public List<ShowSeat> addShowSeats(ShowSeatDto showSeatDto) {
        try {
            List<Seat> seats = getSeatsByScreenId(showSeatDto.getScreenId());
            if(seats.isEmpty()) {
                throw new RuntimeException("seats not found");
            }
            MovieShow show = movieShowService.getShowById(showSeatDto.getShowId());
            List<ShowSeat> showSeats = new ArrayList<>();
            List<ShowSeat> finalShowSeats = showSeats;
            seats.forEach(seat -> {
                ShowSeat showSeat = new ShowSeat();
                showSeat.setSeat(seat);
                showSeat.setMovieShow(show);
                showSeat.setPrice(showSeatDto.getPrice());
                showSeat.setStatus(SeatStatus.AVAILABLE.toString());
                showSeat.setBooking(null);
                finalShowSeats.add(showSeat);
            });
            showSeats = showSeatRepository.saveAll(finalShowSeats);
            return showSeats;
        } catch (Throwable ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public List<ShowSeat> getSeatsByShowId(long showId) {
        try {
            MovieShow show = movieShowService.getShowById(showId);
            return showSeatRepository.findAllByMovieShow(show);
        } catch (Throwable ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    @Transactional
    public List<ShowSeat> getSeatsByShowSeatIds(List<Long> showSeatsIds) {
        try {
            List<ShowSeat> showSeats = showSeatRepository.findAllByIdWithLock(showSeatsIds);
            if(showSeats.isEmpty())
                throw new RuntimeException("can not retrieve show seats");
            return showSeats;
        } catch (Throwable ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public List<Seat> getSeatsByScreenId(long screenId) {
        try {
            Screen screen = screenService.getScreenById(screenId);
            return seatRepository.findAllByScreen(screen);
        } catch (Throwable ex) {
            log.error(ex.getMessage());
        }
        return List.of();
    }

    @Override
    public ShowSeat getShowSeatById(long seatId) {
        return showSeatRepository.findById(seatId).orElse(null);
    }

    @Override
    @Transactional
    public List<ShowSeat> bookSeats(Booking booking, List<Long> showSeatIds) {
        try {
            List<ShowSeat> bookedSeats = new ArrayList<>();
            showSeatIds.forEach( showSeatId -> {
                ShowSeat seat = showSeatRepository.findById(showSeatId).orElse(null);
                if(seat == null) {
                    throw new RuntimeException("invalid seat Id");
                } else {
                    seat.setBooking(booking);
                    seat.setStatus(SeatStatus.BOOKED.toString());
                    ShowSeat updatedSeat = showSeatRepository.save(seat);
                    bookedSeats.add(updatedSeat);
                }
            });
            return bookedSeats;
        } catch (Throwable ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public void releaseSeats() {
        LocalDateTime pastTime = LocalDateTime.now().minusMinutes(10);
        List<ShowSeat> expiredSeats = showSeatRepository.findAllExpiredSeats(pastTime);
        expiredSeats.forEach( seat -> {
            seat.setBooking(null);
            seat.setStatus(SeatStatus.AVAILABLE.toString());
            showSeatRepository.save(seat);
        });
    }
}
