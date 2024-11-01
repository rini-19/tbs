package com.ticket_booking.example.tbs.admin.repository;

import com.ticket_booking.example.tbs.admin.model.Cinema;
import com.ticket_booking.example.tbs.admin.model.Screen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IScreenRepository extends JpaRepository<Screen, Long> {
    List<Screen> findAllByCinema(Cinema cinema);
}
