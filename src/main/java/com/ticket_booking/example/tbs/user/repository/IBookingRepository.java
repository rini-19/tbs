package com.ticket_booking.example.tbs.user.repository;

import com.ticket_booking.example.tbs.user.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookingRepository extends JpaRepository<Booking, Long> {
}
