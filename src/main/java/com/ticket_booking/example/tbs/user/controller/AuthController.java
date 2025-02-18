package com.ticket_booking.example.tbs.user.controller;

import com.ticket_booking.example.tbs.security.service.JwtService;
import com.ticket_booking.example.tbs.user.model.User;
import com.ticket_booking.example.tbs.user.model.UserDto;
import com.ticket_booking.example.tbs.user.service.IAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
//@RequestMapping("apiV1/auth")
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

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

    @PostMapping("/login")
    public String login(@RequestBody User user) throws UsernameNotFoundException {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserEmail(),
                    user.getPassword()));
            Object val = authentication.getAuthorities();
            log.info("auth", val);
        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException("Username or password is incorrect.");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserEmail());
        if (userDetails != null) {
            log.info("Login successful.");
            String s = jwtService.generateToken(userDetails.getUsername());
            log.info(s);
            return s;
        }
        return null;
    }
}
