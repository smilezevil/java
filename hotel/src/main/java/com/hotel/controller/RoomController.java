package com.hotel.controller;

import com.hotel.dto.RoomDto;
import com.hotel.mapper.RoomMapper;
import com.hotel.model.Room;
import com.hotel.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;
    private final RoomMapper roomMapper;

    @GetMapping
    public List<RoomDto> getAllRooms() {
        return roomService.getAllRooms().stream()
                .map(roomMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDto> getRoomById(@PathVariable Long id) {
        return roomService.getRoomById(id)
                .map(roomMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public RoomDto createRoom(@Valid @RequestBody RoomDto roomDto) {
        Room room = roomMapper.toEntity(roomDto);
        return roomMapper.toDto(roomService.createRoom(room));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomDto> updateRoom(@PathVariable Long id, @Valid @RequestBody RoomDto roomDto) {
        Room room = roomMapper.toEntity(roomDto);
        return roomService.updateRoom(id, room)
                .map(roomMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RoomDto> patchRoom(@PathVariable Long id, @RequestBody RoomDto roomDto) {
        Room room = roomMapper.toEntity(roomDto);
        return roomService.patchRoom(id, room)
                .map(roomMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
        if (roomService.deleteRoom(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllRooms() {
        roomService.deleteAllRooms();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/available")
    public List<RoomDto> getAvailableRooms() {
        return roomService.getAvailableRooms().stream()
                .map(roomMapper::toDto)
                .toList();
    }
}