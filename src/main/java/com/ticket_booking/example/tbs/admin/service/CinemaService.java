package com.ticket_booking.example.tbs.admin.service;

import com.ticket_booking.example.tbs.admin.model.Cinema;
import com.ticket_booking.example.tbs.admin.model.CinemaDto;
import com.ticket_booking.example.tbs.admin.repository.ICinemaRepository;
import com.ticket_booking.example.tbs.location.model.Location;
import com.ticket_booking.example.tbs.location.service.ILocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CinemaService implements ICinemaService{

    @Autowired
    ICinemaRepository cinemaRepository;

    @Autowired
    ILocationService locationService;

    @Override
    public Cinema addCinema(CinemaDto cinemaDto) {
        try {
            Cinema cinema = new Cinema();
            Location location = locationService.getLocationById(cinemaDto.getCityId());
            cinema.setCinemaName(cinemaDto.getCinemaName());
            cinema.setPincode(cinemaDto.getPincode());
            cinema.setLocation(location);
            cinemaRepository.save(cinema);
            return cinema;
        } catch (Throwable ex) {
            log.error(ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Cinema> getAllCinemaByLocation(long cityId) {
        try {
            Location location = locationService.getLocationById(cityId);
            //            cinemaRepository.findAll().forEach(cinema -> {
//                if(cinema.getLocation().getCityId() == cityId) {
//                    cinemas.add(cinema);
//                }
//            });
            return cinemaRepository.findCinemasByLocation(location);
        } catch (Throwable ex) {
            log.error(ex.getMessage());
        }
        return List.of();
    }

    @Override
    public Cinema getCinema(long cinemaId) {
        return cinemaRepository.findById(cinemaId).orElse(null);
    }

    @Override
    public Cinema updateCinema(long cinemaId, CinemaDto cinemaDto) {
        try {
            Cinema cinema = getCinema(cinemaId);
            cinema.setCinemaName(cinemaDto.getCinemaName());
            cinema.setPincode(cinemaDto.getPincode());

            Location location = locationService.getLocationById(cinemaDto.getCityId());
            cinema.setLocation(location);

            cinemaRepository.save(cinema);
            return cinema;
        } catch (Throwable ex) {
            log.error(ex.getMessage());
        }
        return null;
    }

    @Override
    public void deleteCinema(long cinemaId) {
        try {
            cinemaRepository.deleteById(cinemaId);
        } catch (Throwable ex) {
            log.error(ex.getMessage());
        }
    }
}
