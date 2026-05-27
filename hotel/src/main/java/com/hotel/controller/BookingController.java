package com.hotel.controller;

import com.hotel.dto.BookingDto;
import com.hotel.mapper.BookingMapper;
import com.hotel.model.Booking;
import com.hotel.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
@Tag(name = "Бронювання (Bookings)", description = "Ендпоінти для управління процесом замовлення та бронювання кімнат")
public class BookingController {
    private final BookingService bookingService;
    private final BookingMapper bookingMapper;

    @GetMapping
    @Operation(summary = "Отримати всі бронювання", description = "Повертає список усіх бронювань (використовує оптимізований метод JOIN FETCH для уникнення проблеми N+1)")
    @ApiResponse(responseCode = "200", description = "Список замовлень успішно сформовано")
    public List<BookingDto> getAllBookings() {
        return bookingService.getAllBookings().stream()
                .map(bookingMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Знайти бронювання за ID", description = "Повертає деталі конкретного замовлення за його ідентифікатором")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Бронювання успішно знайдено"),
            @ApiResponse(responseCode = "404", description = "Замовлення з таким ID відсутнє", content = @Content)
    })
    public ResponseEntity<BookingDto> getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id)
                .map(bookingMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Створити нове бронювання", description = "Оформлює бронювання кімнати на певного гостя із зазначенням дат проживання.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Бронювання успішно підтверджено"),
            @ApiResponse(responseCode = "400", description = "Помилка валідації дат або ідентифікаторів", content = @Content)
    })
    public BookingDto createBooking(@Valid @RequestBody BookingDto bookingDto) {
        Booking booking = bookingMapper.toEntity(bookingDto);
        return bookingMapper.toDto(bookingService.createBooking(booking));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Повністю змінити замовлення", description = "Повністю оновлює інформацію про кімнату, гостя чи дати проживання.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Бронювання успішно оновлено"),
            @ApiResponse(responseCode = "400", description = "Некоректні вхідні параметри структури JSON", content = @Content),
            @ApiResponse(responseCode = "404", description = "Бронювання з таким ID не знайдено", content = @Content)
    })
    public ResponseEntity<BookingDto> updateBooking(@PathVariable Long id, @Valid @RequestBody BookingDto bookingDto) {
        Booking booking = bookingMapper.toEntity(bookingDto);
        return bookingService.updateBooking(id, booking)
                .map(bookingMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Частково змінити параметри бронювання", description = "Дозволяє змінити лише окремі аспекти замовлення, наприклад, продовжити дату виїзду (check-out).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Зміни в замовлення успішно внесені"),
            @ApiResponse(responseCode = "404", description = "Замовлення не знайдено", content = @Content)
    })
    public ResponseEntity<BookingDto> patchBooking(@PathVariable Long id, @RequestBody BookingDto bookingDto) {
        Booking booking = bookingMapper.toEntity(bookingDto);
        return bookingService.patchBooking(id, booking)
                .map(bookingMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Скасувати/видалити бронювання", description = "Видаляє запис про бронювання готелю з бази даних.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Бронювання успішно анульовано"),
            @ApiResponse(responseCode = "404", description = "Бронювання з таким ID не знайдено", content = @Content)
    })
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        if (bookingService.deleteBooking(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    @Operation(summary = "Очистити історію всіх бронювань", description = "Видаляє абсолютно всі замовлення з таблиці.")
    @ApiResponse(responseCode = "200", description = "Всі записи бронювань успішно видалено")
    public ResponseEntity<Void> deleteAllBookings() {
        bookingService.deleteAllBookings();
        return ResponseEntity.ok().build();
    }
}