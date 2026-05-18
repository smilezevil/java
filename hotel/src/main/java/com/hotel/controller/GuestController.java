package com.hotel.controller;

import com.hotel.dto.GuestDto;
import com.hotel.model.Guest;
import com.hotel.service.GuestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.hotel.mapper.GuestMapper;

@RestController
@RequestMapping("/guests")
@RequiredArgsConstructor
public class GuestController {
    private final GuestService guestService;
    private final GuestMapper guestMapper;

    @GetMapping
    public List<GuestDto> getAllGuests() {
        return guestService.getAllGuests().stream()
                .map(guestMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuestDto> getGuestById(@PathVariable Long id) {
        return guestService.getGuestById(id)
                .map(guestMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public GuestDto createGuest(@Valid @RequestBody GuestDto guestDto) {
        Guest guest = guestMapper.toEntity(guestDto);
        return guestMapper.toDto(guestService.createGuest(guest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GuestDto> updateGuest(@PathVariable Long id, @Valid @RequestBody GuestDto guestDto) {
        Guest guest = guestMapper.toEntity(guestDto);
        return guestService.updateGuest(id, guest)
                .map(guestMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GuestDto> patchGuest(@PathVariable Long id, @RequestBody GuestDto guestDto) {
        Guest guest = guestMapper.toEntity(guestDto);
        return guestService.patchGuest(id, guest)
                .map(guestMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuest(@PathVariable Long id) {
        if (guestService.deleteGuest(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllGuests() {
        guestService.deleteAllGuests();
        return ResponseEntity.ok().build();
    }
}