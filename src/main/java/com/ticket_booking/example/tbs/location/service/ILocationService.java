package com.ticket_booking.example.tbs.location.service;

import com.ticket_booking.example.tbs.location.model.Location;
import com.ticket_booking.example.tbs.location.model.LocationDto;

import java.util.List;

public interface ILocationService {
    Location addLocation(LocationDto locDto);
    Location updateLocation(LocationDto locDto);
    List<Location> getAllLocations();
    Location getLocationById(long id);
}
