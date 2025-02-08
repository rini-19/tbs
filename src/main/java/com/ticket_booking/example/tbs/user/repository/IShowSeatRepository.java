package com.ticket_booking.example.tbs.user.repository;

import com.ticket_booking.example.tbs.admin.model.MovieShow;
import com.ticket_booking.example.tbs.user.model.ShowSeat;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface IShowSeatRepository extends JpaRepository<ShowSeat, Long> {
    List<ShowSeat> findAllByMovieShow(MovieShow show);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "jakarta.persistence.lock.timeout", value = "0")})
    @Query(value = "select s from ShowSeat s " +
            "where s.id in :showSeatIds")
    List<ShowSeat> findAllByIdWithLock(@Param("showSeatIds") List<Long> showSeatIds);

    @Query(
            value = "select ss.* from `ticket-booking-system`.showseat ss \n" +
                    "join `ticket-booking-system`.booking b on b.booking_id = ss.booking_id \n" +
                    "where b.timestamp <= ?1 and b.booking_status not in ('BOOKED') ", nativeQuery = true
    )
    List<ShowSeat> findAllExpiredSeats(LocalDateTime pastTime);
}
