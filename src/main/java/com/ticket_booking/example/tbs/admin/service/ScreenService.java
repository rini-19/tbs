package com.ticket_booking.example.tbs.admin.service;

import com.ticket_booking.example.tbs.admin.model.Cinema;
import com.ticket_booking.example.tbs.admin.model.Screen;
import com.ticket_booking.example.tbs.admin.model.ScreenDto;
import com.ticket_booking.example.tbs.admin.repository.IScreenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class ScreenService implements IScreenService{

    @Autowired
    IScreenRepository screenRepository;

    @Autowired
    ICinemaService cinemaService;

    @Override
    public List<Screen> addScreens(ScreenDto screenDto) {
        try {
            List<Screen> screens = new ArrayList<>();
            Cinema cinema = cinemaService.getCinema(screenDto.getCinemaId());
            List<Screen> finalScreens = screens;
            AtomicInteger cnt = new AtomicInteger(1);
            screenDto.getTotalSeats().forEach((screenSeats) -> {
                Screen screen = new Screen();
                screen.setCinema(cinema);
                int screenNumber = cnt.getAndIncrement();
                screen.setScreenNumber(screenNumber);
                screen.setTotalSeats(screenSeats);
                finalScreens.add(screen);
            });
            screens = screenRepository.saveAll(finalScreens);
            return screens;
        } catch (Throwable ex) {
            log.error(ex.getMessage(), ex);
        }
        return List.of();
    }

    @Override
    public List<Screen> getScreensByCinema(long cinemaId) {
        try {
            Cinema cinema = cinemaService.getCinema(cinemaId);
            return screenRepository.findAllByCinema(cinema);
        } catch (Throwable ex) {
            log.error(ex.getMessage(), ex);
        }
        return List.of();
    }

    @Override
    public Screen getScreenById(long id) {
        return screenRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteScreen(long screenId) {
        screenRepository.deleteById(screenId);
    }
}
