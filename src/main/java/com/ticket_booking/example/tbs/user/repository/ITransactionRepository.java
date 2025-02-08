package com.ticket_booking.example.tbs.user.repository;

import com.ticket_booking.example.tbs.user.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITransactionRepository extends JpaRepository<Transaction, Long> {
}
