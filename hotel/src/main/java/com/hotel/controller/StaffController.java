package com.hotel.controller;

import com.hotel.dto.StaffDto;
import com.hotel.mapper.StaffMapper;
import com.hotel.model.Staff;
import com.hotel.service.StaffService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/staff")
@RequiredArgsConstructor
public class StaffController {
    private final StaffService staffService;
    private final StaffMapper staffMapper;

    @GetMapping
    public List<StaffDto> getAllStaff() {
        return staffService.getAllStaff().stream()
                .map(staffMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StaffDto> getStaffById(@PathVariable Long id) {
        return staffService.getStaffById(id)
                .map(staffMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public StaffDto createStaff(@Valid @RequestBody StaffDto staffDto) {
        Staff staff = staffMapper.toEntity(staffDto);
        return staffMapper.toDto(staffService.createStaff(staff));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StaffDto> updateStaff(@PathVariable Long id, @Valid @RequestBody StaffDto staffDto) {
        Staff staff = staffMapper.toEntity(staffDto);
        return staffService.updateStaff(id, staff)
                .map(staffMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<StaffDto> patchStaff(@PathVariable Long id, @RequestBody StaffDto staffDto) {
        Staff staff = staffMapper.toEntity(staffDto);
        return staffService.patchStaff(id, staff)
                .map(staffMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStaff(@PathVariable Long id) {
        if (staffService.deleteStaff(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllStaff() {
        staffService.deleteAllStaff();
        return ResponseEntity.ok().build();
    }
}