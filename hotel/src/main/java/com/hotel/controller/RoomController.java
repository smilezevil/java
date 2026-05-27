package com.hotel.controller;

import com.hotel.dto.RoomDto;
import com.hotel.mapper.RoomMapper;
import com.hotel.model.Room;
import com.hotel.service.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
@Tag(name = "Кімнати (Rooms)", description = "Ендпоінти для управління кімнатами готелю")
public class RoomController {
    private final RoomService roomService;
    private final RoomMapper roomMapper;

    @GetMapping
    @Operation(summary = "Отримати всі кімнати", description = "Повертає сторінку зі списком усіх кімнат готелю (з пагінацією)")
    @ApiResponse(responseCode = "200", description = "Список успішно отримано")
    public Page<RoomDto> getAllRooms(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return roomService.getAllRooms(pageable).map(roomMapper::toDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Знайти кімнату за ID", description = "Повертає дані конкретної кімнати, якщо вона існує")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Кімнату успішно знайдено"),
            @ApiResponse(responseCode = "404", description = "Кімнату з таким ID не знайдено", content = @Content)
    })
    public ResponseEntity<RoomDto> getRoomById(@PathVariable Long id) {
        return roomService.getRoomById(id)
                .map(roomMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Створити нову кімнату", description = "Додає нову кімнату до бази даних. Дані проходять валідацію.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Кімнату успішно створено"),
            @ApiResponse(responseCode = "400", description = "Помилка валідації вхідних даних (наприклад, від'ємна ціна)", content = @Content)
    })
    public RoomDto createRoom(@Valid @RequestBody RoomDto roomDto) {
        Room room = roomMapper.toEntity(roomDto);
        return roomMapper.toDto(roomService.createRoom(room));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Повністю оновити кімнату", description = "Оновлює всі поля кімнати за її ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Кімнату успішно оновлено"),
            @ApiResponse(responseCode = "400", description = "Помилка валідації вхідних даних", content = @Content),
            @ApiResponse(responseCode = "404", description = "Кімнату з таким ID не знайдено", content = @Content)
    })
    public ResponseEntity<RoomDto> updateRoom(@PathVariable Long id, @Valid @RequestBody RoomDto roomDto) {
        Room room = roomMapper.toEntity(roomDto);
        return roomService.updateRoom(id, room)
                .map(roomMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Частково оновити кімнату", description = "Оновлює лише передані поля кімнати (наприклад, змінити тільки статус доступності).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Кімнату успішно частково оновлено"),
            @ApiResponse(responseCode = "404", description = "Кімнату з таким ID не знайдено", content = @Content)
    })
    public ResponseEntity<RoomDto> patchRoom(@PathVariable Long id, @RequestBody RoomDto roomDto) {
        Room room = roomMapper.toEntity(roomDto);
        return roomService.patchRoom(id, room)
                .map(roomMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Видалити кімнату", description = "Видаляє кімнату з бази даних за її ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Кімнату успішно видалено"),
            @ApiResponse(responseCode = "404", description = "Кімнату з таким ID не знайдено", content = @Content)
    })
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
        if (roomService.deleteRoom(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    @Operation(summary = "Видалити всі кімнати", description = "Повністю очищає таблицю кімнат.")
    @ApiResponse(responseCode = "200", description = "Всі кімнати успішно видалено")
    public ResponseEntity<Void> deleteAllRooms() {
        roomService.deleteAllRooms();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/available")
    @Operation(summary = "Отримати доступні кімнати", description = "Повертає список лише тих кімнат, які доступні для заселення.")
    @ApiResponse(responseCode = "200", description = "Список доступних кімнат успішно отримано")
    public List<RoomDto> getAvailableRooms() {
        return roomService.getAvailableRooms().stream()
                .map(roomMapper::toDto)
                .toList();
    }
}