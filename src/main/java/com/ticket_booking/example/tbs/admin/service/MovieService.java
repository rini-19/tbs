package com.ticket_booking.example.tbs.admin.service;

import com.ticket_booking.example.tbs.admin.model.Movie;
import com.ticket_booking.example.tbs.admin.model.MovieDto;
import com.ticket_booking.example.tbs.admin.repository.IMovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class MovieService implements IMovieService{

    @Autowired
    IMovieRepository movieRepository;

    @Override
    public Movie addMovie(MovieDto movieDto) {
        try {
            Movie movie = new Movie();
            movie.setMovieTitle(movieDto.getMovieTitle());
            movie.setPlot(movieDto.getPlot());
            movie.setReleaseDate(movieDto.getReleaseDate());
            movie.setGenre(movieDto.getGenre());
            movieRepository.save(movie);
            return movie;
        }catch (Throwable ex) {
            log.error(ex.getMessage());
        }
        return null;
    }

    @Override
    public Movie getMovie(long movieId) {
        try {
            return movieRepository.findById(movieId).orElse(null);
        } catch (Throwable ex) {
            log.error(ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Movie> getMovies(String date) {
        try {
            LocalDate formattedDate = LocalDate.parse(date);

            // Convert to LocalDateTime with time components
            LocalDateTime releaseDate = formattedDate.atTime(0, 0, 0, 0);
            List<Movie> movies = movieRepository.findAllByReleaseDate(releaseDate);
            return movies;
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
        return List.of();
    }

    @Override
    public void deleteMovie(long movieId) {
        try {
            movieRepository.deleteById(movieId);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }
}
