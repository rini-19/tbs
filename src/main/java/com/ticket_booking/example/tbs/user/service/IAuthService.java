package com.ticket_booking.example.tbs.user.service;

import com.ticket_booking.example.tbs.user.model.User;
import com.ticket_booking.example.tbs.user.model.UserDto;

public interface IAuthService {

    // register user
    User registerUser (UserDto userDto);

    // get user
    User getUser (String emailId);

    // get user by Id
    User getUserById(long userId);
}
