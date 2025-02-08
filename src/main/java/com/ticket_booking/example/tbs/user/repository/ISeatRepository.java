package com.ticket_booking.example.tbs.user.repository;

import com.ticket_booking.example.tbs.admin.model.Screen;
import com.ticket_booking.example.tbs.user.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findAllByScreen(Screen screen);
}
