package com.ticket_booking.example.tbs.admin.repository;

import com.ticket_booking.example.tbs.admin.model.Movie;
import com.ticket_booking.example.tbs.admin.model.MovieShow;
import com.ticket_booking.example.tbs.admin.model.Screen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMovieShowRepository extends JpaRepository<MovieShow, Long> {
    List<MovieShow> findAllByMovieAndScreen(Movie movie, Screen screen);

    List<MovieShow> findAllByMovie(Movie movie);

    List<MovieShow> findAllByScreen(Screen screen);

    @Query(
            value = "select ms.* from `ticket-booking-system`.movieshow ms \n" +
                "join `ticket-booking-system`.screen s on s.screen_id = ms.screen_id \n" +
                "join `ticket-booking-system`.cinema c on c.cinema_id = s.cinema_id \n" +
                "where c.city_id = ?1", nativeQuery = true
    )
    List<MovieShow> findAllByLocation(Long cityId);

    @Query(
            value = "select ms.* from `ticket-booking-system`.movieshow ms \n" +
                    "join `ticket-booking-system`.screen s on s.screen_id = ms.screen_id \n" +
                    "where ms.movie_id = ?1 and s.cinema_id = ?2", nativeQuery = true
    )
    List<MovieShow> findAllByMovieIdAndCinemaId(Long movieId, Long cinemaId);
}
