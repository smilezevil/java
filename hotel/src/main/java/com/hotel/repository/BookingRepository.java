package com.hotel.repository;

import com.hotel.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT b FROM Booking b JOIN FETCH b.guest JOIN FETCH b.room")
    List<Booking> findAllWithGuestsAndRooms();

    @Query("SELECT b FROM Booking b JOIN FETCH b.guest JOIN FETCH b.room WHERE b.id = :id")
    Optional<Booking> findByIdWithGuestsAndRooms(@Param("id") Long id);
}