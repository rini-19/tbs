package com.ticket_booking.example.tbs.user.repository;

import com.ticket_booking.example.tbs.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthRepository extends JpaRepository<User, Long> {
    User findByUserEmail(String emailId);
}
