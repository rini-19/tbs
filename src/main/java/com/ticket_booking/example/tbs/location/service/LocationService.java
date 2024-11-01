package com.ticket_booking.example.tbs.location.service;

import com.ticket_booking.example.tbs.location.model.Location;
import com.ticket_booking.example.tbs.location.model.LocationDto;
import com.ticket_booking.example.tbs.location.repository.LocationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LocationService implements ILocationService {
    @Autowired
    private LocationRepository locationRepository;

    @Override
    public Location addLocation(LocationDto locDto) {
        try {
            Location location = new Location();
            location.setCityName(locDto.getCityName());
            location.setState(locDto.getState());
            locationRepository.save(location);
            return location;
        } catch (Throwable e) {
            log.error(e.getMessage());
        }

        return null;
    }

    @Override
    public Location updateLocation(LocationDto locDto) {
        return null;
    }

    @Override
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    @Override
    public Location getLocationById(long id) {
        return locationRepository.findById(id).orElse(null);
    }
}
