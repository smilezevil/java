package com.hotel.controller;

import com.hotel.dto.GuestDto;
import com.hotel.model.Guest;
import com.hotel.service.GuestService;
import com.hotel.mapper.GuestMapper;
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
@RequestMapping("/guests")
@RequiredArgsConstructor
@Tag(name = "Гості (Guests)", description = "Ендпоінти для управління даними гостей готелю")
public class GuestController {
    private final GuestService guestService;
    private final GuestMapper guestMapper;

    @GetMapping
    @Operation(summary = "Отримати всіх гостей", description = "Повертає повний список зареєстрованих гостей готелю")
    @ApiResponse(responseCode = "200", description = "Список гостей успішно отримано")
    public List<GuestDto> getAllGuests() {
        return guestService.getAllGuests().stream()
                .map(guestMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Знайти гостя за ID", description = "Повертає дані конкретного гостя за його унікальним ідентифікатором")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Гостя успішно знайдено"),
            @ApiResponse(responseCode = "404", description = "Гостя з таким ID не знайдено", content = @Content)
    })
    public ResponseEntity<GuestDto> getGuestById(@PathVariable Long id) {
        return guestService.getGuestById(id)
                .map(guestMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Реєстрація нового гостя", description = "Додає нового гостя до бази. Перевіряє правильність унікальності email та телефону.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Гостя успішно зареєстровано"),
            @ApiResponse(responseCode = "400", description = "Помилка валідації (наприклад, некоректний формат email)", content = @Content)
    })
    public GuestDto createGuest(@Valid @RequestBody GuestDto guestDto) {
        Guest guest = guestMapper.toEntity(guestDto);
        return guestMapper.toDto(guestService.createGuest(guest));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Повністю оновити дані гостя", description = "Замінює всі поля в профілі гостя за його ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Дані гостя успішно оновлено"),
            @ApiResponse(responseCode = "400", description = "Некоректні вхідні дані", content = @Content),
            @ApiResponse(responseCode = "404", description = "Гостя з таким ID не знайдено", content = @Content)
    })
    public ResponseEntity<GuestDto> updateGuest(@PathVariable Long id, @Valid @RequestBody GuestDto guestDto) {
        Guest guest = guestMapper.toEntity(guestDto);
        return guestService.updateGuest(id, guest)
                .map(guestMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Частково оновити дані гостя", description = "Дозволяє змінити лише окремі поля (наприклад, тільки номер телефону).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Дані успішно модифіковано"),
            @ApiResponse(responseCode = "404", description = "Гостя з таким ID не знайдено", content = @Content)
    })
    public ResponseEntity<GuestDto> patchGuest(@PathVariable Long id, @RequestBody GuestDto guestDto) {
        Guest guest = guestMapper.toEntity(guestDto);
        return guestService.patchGuest(id, guest)
                .map(guestMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Видалити гостя", description = "Видаляє профіль гостя з бази даних за його ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Гостя успішно видалено"),
            @ApiResponse(responseCode = "404", description = "Гостя з таким ID не знайдено", content = @Content)
    })
    public ResponseEntity<Void> deleteGuest(@PathVariable Long id) {
        if (guestService.deleteGuest(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    @Operation(summary = "Видалити всіх гостей", description = "Повністю очищає таблицю гостей.")
    @ApiResponse(responseCode = "200", description = "Всі гості успішно видалені з бази даних")
    public ResponseEntity<Void> deleteAllGuests() {
        guestService.deleteAllGuests();
        return ResponseEntity.ok().build();
    }
}