package com.hotel.controller;

import com.hotel.dto.BookingDto;
import com.hotel.mapper.BookingMapper;
import com.hotel.model.Booking;
import com.hotel.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;
    private final BookingMapper bookingMapper;

    @GetMapping
    public List<BookingDto> getAllBookings() {
        return bookingService.getAllBookings().stream()
                .map(bookingMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDto> getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id)
                .map(bookingMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public BookingDto createBooking(@Valid @RequestBody BookingDto bookingDto) {
        Booking booking = bookingMapper.toEntity(bookingDto);
        return bookingMapper.toDto(bookingService.createBooking(booking));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingDto> updateBooking(@PathVariable Long id, @Valid @RequestBody BookingDto bookingDto) {
        Booking booking = bookingMapper.toEntity(bookingDto);
        return bookingService.updateBooking(id, booking)
                .map(bookingMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BookingDto> patchBooking(@PathVariable Long id, @RequestBody BookingDto bookingDto) {
        Booking booking = bookingMapper.toEntity(bookingDto);
        return bookingService.patchBooking(id, booking)
                .map(bookingMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        if (bookingService.deleteBooking(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllBookings() {
        bookingService.deleteAllBookings();
        return ResponseEntity.ok().build();
    }
}