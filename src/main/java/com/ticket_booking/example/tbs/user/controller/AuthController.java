package com.ticket_booking.example.tbs.user.controller;

import com.ticket_booking.example.tbs.user.model.User;
import com.ticket_booking.example.tbs.user.model.UserDto;
import com.ticket_booking.example.tbs.user.service.IAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    IAuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
        User user = authService.registerUser(userDto);
        if(user == null) {
            return new ResponseEntity<>("Error in registering user", HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
    }
}
