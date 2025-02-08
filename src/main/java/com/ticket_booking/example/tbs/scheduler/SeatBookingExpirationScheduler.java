package com.ticket_booking.example.tbs.scheduler;

import com.ticket_booking.example.tbs.user.service.ISeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SeatBookingExpirationScheduler {

    @Autowired
    ISeatService seatService;

    @EventListener(ApplicationReadyEvent.class)
    @Scheduled(fixedRate = 600000)
    public void releaseSeats() {
        seatService.releaseSeats();
    }
}
