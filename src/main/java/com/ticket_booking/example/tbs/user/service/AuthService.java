package com.ticket_booking.example.tbs.user.service;

import com.ticket_booking.example.tbs.location.model.Location;
import com.ticket_booking.example.tbs.location.service.ILocationService;
import com.ticket_booking.example.tbs.user.model.User;
import com.ticket_booking.example.tbs.user.model.UserDto;
import com.ticket_booking.example.tbs.user.repository.IAuthRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService implements IAuthService {
    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);

    @Autowired
    ILocationService locationService;

    @Autowired
    IAuthRepository authRepository;

    @Override
    public User registerUser(UserDto userDto) {
        try {
            Location location = locationService.getLocationById(userDto.getLocationId());
            User user = new User();
            user.setUserName(userDto.getUserName());
            user.setUserEmail(userDto.getUserEmail());
            user.setPincode(userDto.getPincode());
            user.setLocation(location);
            user.setMobileNumber(userDto.getMobileNumber());
            user.setPincode(userDto.getPincode());
            user.setPassword(encoder.encode(userDto.getPassword()));
            user.setRole(userDto.getRole());
            return authRepository.save(user);
        } catch (Throwable ex) {
            log.error(ex.getMessage());
        }
        return null;
    }

    @Override
    public User getUser(String emailId) {
        return authRepository.findByUserEmail(emailId);
    }

    @Override
    public User getUserById(long userId) {
        return authRepository.findById(userId).orElse(null);
    }
}
