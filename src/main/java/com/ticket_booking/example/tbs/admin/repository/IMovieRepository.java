package com.ticket_booking.example.tbs.admin.repository;

import com.ticket_booking.example.tbs.admin.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface IMovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findAllByReleaseDate(LocalDateTime releaseDate);
}
