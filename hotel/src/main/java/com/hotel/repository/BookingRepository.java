package com.hotel.repository;

import com.hotel.model.Booking;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class BookingRepository {
    private final Map<Long, Booking> bookings = new HashMap<>();
    private long nextId = 1;

    public List<Booking> findAll() {
        return new ArrayList<>(bookings.values());
    }

    public Optional<Booking> findById(Long id) {
        return Optional.ofNullable(bookings.get(id));
    }

    public Booking save(Booking booking) {
        if (booking.getId() == null) {
            booking.setId(nextId++);
        }
        bookings.put(booking.getId(), booking);
        return booking;
    }

    public void deleteAll() {
        bookings.clear();
    }

    public void deleteById(Long id) {
        bookings.remove(id);
    }
}