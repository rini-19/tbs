package com.ticket_booking.example.tbs.admin.service;

import com.ticket_booking.example.tbs.admin.model.Movie;
import com.ticket_booking.example.tbs.admin.model.MovieShow;
import com.ticket_booking.example.tbs.admin.model.MovieShowDto;
import com.ticket_booking.example.tbs.admin.model.Screen;
import com.ticket_booking.example.tbs.admin.repository.IMovieShowRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MovieShowService implements IMovieShowService{

    @Autowired
    IMovieShowRepository movieShowRepository;

    @Autowired
    IMovieService movieService;

    @Autowired
    IScreenService screenService;

    @Override
    public MovieShow addShow(MovieShowDto movieShowDto) {
        try {
            MovieShow show = new MovieShow();
            Movie movie = movieService.getMovie(movieShowDto.getMovieId());
            Screen screen = screenService.getScreenById(movieShowDto.getScreenId());
            if(movie == null || screen == null) {
                log.error("movie or screen not found");
                return null;
            }
            List<MovieShow> shows = getAllShowsByScreenId(movieShowDto.getScreenId()).stream().toList();
            for (MovieShow movieShow : shows) {
                if((movieShowDto.getShowStartTime().isAfter(movieShow.getShowStartTime()) && movieShowDto.getShowStartTime().isBefore(movieShow.getShowEndTime())) || movieShowDto.getShowStartTime().isEqual(movieShow.getShowStartTime()) || (movieShowDto.getShowEndTime().isAfter(movieShow.getShowStartTime()) && movieShowDto.getShowEndTime().isBefore(movieShow.getShowEndTime())) || movieShowDto.getShowEndTime().isEqual(movieShow.getShowEndTime()) ) {
                    log.error("Slot not available");
                    return null;
                }
            }
            
            show.setShowStartTime(movieShowDto.getShowStartTime());
            show.setShowEndTime(movieShowDto.getShowEndTime());
            show.setMovie(movie);
            show.setScreen(screen);
            movieShowRepository.save(show);
            return show;
        } catch (Throwable ex) {
            log.error(ex.getMessage());
        }
        return null;
    }

    @Override
    public void removeShow(long showId) {
        try {
            movieShowRepository.deleteById(showId);
        } catch (Throwable ex) {
            log.error(ex.getMessage());
        }
    }

    @Override
    public MovieShow getShowById(long showId) {
        try {
            return movieShowRepository.findById(showId).orElse(null);
        } catch (Throwable ex) {
            log.error(ex.getMessage());
        }
        return null;
    }

    @Override
    public List<MovieShow> getAllShowsByLocation(long cityId) {
        try {
            return movieShowRepository.findAllByLocation(cityId);
        } catch (Throwable ex) {
            log.error(ex.getMessage());
        }
        return List.of();
    }

    @Override
    public List<MovieShow> getAllShowsByMovieId(long movieId) {
        try {
            Movie movie = movieService.getMovie(movieId);
            return movieShowRepository.findAllByMovie(movie);
        } catch (Throwable ex) {
            log.error(ex.getMessage());
        }
        return List.of();
    }

    @Override
    public List<MovieShow> getAllShowsByScreenId(long screenId) {
        try {
            Screen screen = screenService.getScreenById(screenId);
            return movieShowRepository.findAllByScreen(screen);
        } catch (Throwable ex) {
            log.error(ex.getMessage());
        }
        return List.of();
    }

    @Override
    public List<MovieShow> getAllShowsByMovieIdAndScreenId(long movieId, long screenId) {
        try {
            Movie movie = movieService.getMovie(movieId);
            Screen screen = screenService.getScreenById(screenId);
            return movieShowRepository.findAllByMovieAndScreen(movie, screen);
        } catch (Throwable ex) {
            log.error(ex.getMessage());
        }
        return List.of();
    }

    @Override
    public List<MovieShow> getAllShowsByMovieIdAndCinemaId(long movieId, long cinemaId) {
        try {
           return movieShowRepository.findAllByMovieIdAndCinemaId(movieId, cinemaId);
        } catch (Throwable ex) {
            log.error(ex.getMessage());
        }
        return List.of();
    }
}
