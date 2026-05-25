package com.hotel.service;

import com.hotel.model.Booking;
import com.hotel.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRED)
public class BookingService {
    private final BookingRepository bookingRepository;

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Booking> getAllBookings() {
       return bookingRepository.findAllWithGuestsAndRooms();
    }

    //@Transactional(readOnly = true)
    //public List<Booking> getAllBookings() {
       // return bookingRepository.findAll();
    //}

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Optional<Booking> updateBooking(Long id, Booking updatedBooking) {
        return bookingRepository.findById(id).map(booking -> {
            booking.setGuest(updatedBooking.getGuest());
            booking.setRoom(updatedBooking.getRoom());
            booking.setCheckIn(updatedBooking.getCheckIn());
            booking.setCheckOut(updatedBooking.getCheckOut());
            booking.setServices(updatedBooking.getServices());
            return bookingRepository.save(booking);
        });
    }

    public Optional<Booking> patchBooking(Long id, Booking patchedBooking) {
        return bookingRepository.findById(id).map(booking -> {
            if (patchedBooking.getGuest() != null) booking.setGuest(patchedBooking.getGuest());
            if (patchedBooking.getRoom() != null) booking.setRoom(patchedBooking.getRoom());
            if (patchedBooking.getCheckIn() != null) booking.setCheckIn(patchedBooking.getCheckIn());
            if (patchedBooking.getCheckOut() != null) booking.setCheckOut(patchedBooking.getCheckOut());
            if (patchedBooking.getServices() != null) booking.setServices(patchedBooking.getServices());
            return bookingRepository.save(booking);
        });
    }

    public boolean deleteBooking(Long id) {
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void deleteAllBookings() {
        bookingRepository.deleteAll();
    }
}