package com.ticket_booking.example.tbs.admin.service;

import com.ticket_booking.example.tbs.admin.model.Screen;
import com.ticket_booking.example.tbs.admin.model.ScreenDto;

import java.util.List;

public interface IScreenService {
    // add screens
    List<Screen> addScreens(ScreenDto screenDto);

    //get screens by cinema
    List<Screen> getScreensByCinema(long cinemaId);

    //get screen by id
    Screen getScreenById(long id);

    // remove screen
    void deleteScreen(long screenId);
}
