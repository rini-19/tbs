package com.ticket_booking.example.tbs.admin.controller;

import com.ticket_booking.example.tbs.admin.model.Screen;
import com.ticket_booking.example.tbs.admin.model.ScreenDto;
import com.ticket_booking.example.tbs.admin.service.IScreenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
//@RequestMapping("api/screen")
public class ScreenController {

    @Autowired
    private IScreenService screenService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("api/screen/add")
    ResponseEntity<?> addScreens(@RequestBody ScreenDto screenDto) {
        List<Screen> screens = screenService.addScreens(screenDto);
        if(!screens.isEmpty()) {
            return new ResponseEntity<>(screens, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Error adding screens", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("apiV1/screen/cinema/{cinemaId}")
    ResponseEntity<?> getScreens(@PathVariable("cinemaId") long cinemaId) {
        List<Screen> screens = screenService.getScreensByCinema(cinemaId);
        if(!screens.isEmpty()) {
            return new ResponseEntity<>(screens, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error getting screens by Cinema", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("api/screen/{screenId}")
    ResponseEntity<?> deleteScreen(@PathVariable("screenId") long screenId) {
        screenService.deleteScreen(screenId);
        return new ResponseEntity<>("Screen Deleted", HttpStatus.OK);
    }
}
