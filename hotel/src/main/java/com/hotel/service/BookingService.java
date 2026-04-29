package com.hotel.service;

import com.hotel.model.Booking;
import com.hotel.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Optional<Booking> updateBooking(Long id, Booking updatedBooking) {
        return bookingRepository.findById(id).map(booking -> {
            booking.setGuestId(updatedBooking.getGuestId());
            booking.setRoomId(updatedBooking.getRoomId());
            booking.setCheckIn(updatedBooking.getCheckIn());
            booking.setCheckOut(updatedBooking.getCheckOut());
            return bookingRepository.save(booking);
        });
    }

    public Optional<Booking> patchBooking(Long id, Booking patchedBooking) {
        return bookingRepository.findById(id).map(booking -> {
            if (patchedBooking.getGuestId() != null) booking.setGuestId(patchedBooking.getGuestId());
            if (patchedBooking.getRoomId() != null) booking.setRoomId(patchedBooking.getRoomId());
            if (patchedBooking.getCheckIn() != null) booking.setCheckIn(patchedBooking.getCheckIn());
            if (patchedBooking.getCheckOut() != null) booking.setCheckOut(patchedBooking.getCheckOut());
            return bookingRepository.save(booking);
        });
    }

    public void deleteAllBookings() {
        bookingRepository.deleteAll();
    }

    public boolean deleteBooking(Long id) {
        if (bookingRepository.findById(id).isPresent()) {
            bookingRepository.deleteById(id);
            return true;
        }
        return false;
    }
}