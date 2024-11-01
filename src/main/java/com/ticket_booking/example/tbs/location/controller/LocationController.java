package com.ticket_booking.example.tbs.location.controller;

import com.ticket_booking.example.tbs.location.model.Location;
import com.ticket_booking.example.tbs.location.model.LocationDto;
import com.ticket_booking.example.tbs.location.service.ILocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/city")
public class LocationController {

    @Autowired
    private ILocationService locationService;

    @PostMapping("/add")
    public ResponseEntity<?> addLocation(@RequestBody LocationDto locDto) {
        Location location = locationService.addLocation(locDto);
        if(location == null) {
            return new ResponseEntity<>("Error adding this location", HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(location, HttpStatus.CREATED);
        }
    }

    @GetMapping("/all-cities")
    ResponseEntity<?> getAllLocations() {
        List<Location> locations = locationService.getAllLocations();
        if(locations == null) {
            return new ResponseEntity<>("Error retrieving all locations", HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(locations, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getLocationById(@PathVariable("id") Long id) {
        Location location = locationService.getLocationById(id);
        if(location == null) {
            return new ResponseEntity<>("Error retrieving this location", HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(location, HttpStatus.OK);
        }
    }


}
