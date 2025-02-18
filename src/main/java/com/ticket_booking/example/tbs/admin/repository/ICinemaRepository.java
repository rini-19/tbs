package com.ticket_booking.example.tbs.admin.repository;

import com.ticket_booking.example.tbs.admin.model.Cinema;
import com.ticket_booking.example.tbs.location.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICinemaRepository extends JpaRepository<Cinema, Long> {
    List<Cinema> findCinemasByLocation(Location location);
}
