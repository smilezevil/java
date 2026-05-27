package com.hotel.controller;

import com.hotel.dto.StaffDto;
import com.hotel.mapper.StaffMapper;
import com.hotel.model.Staff;
import com.hotel.service.StaffService;
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
@RequestMapping("/staff")
@RequiredArgsConstructor
@Tag(name = "Персонал (Staff)", description = "Ендпоінти для управління внутрішньою інформацією про працівників готелю")
public class StaffController {
    private final StaffService staffService;
    private final StaffMapper staffMapper;

    @GetMapping
    @Operation(summary = "Отримати реєстр співробітників", description = "Повертає повний список персоналу та їхніх окладів")
    @ApiResponse(responseCode = "200", description = "Реєстр успішно сформовано")
    public List<StaffDto> getAllStaff() {
        return staffService.getAllStaff().stream()
                .map(staffMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Отримати досьє працівника за ID", description = "Повертає картку співробітника з інформацією про посаду та зарплату")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Співробітника успішно знайдено"),
            @ApiResponse(responseCode = "404", description = "Працівник з таким ID відсутній у штаті готелю", content = @Content)
    })
    public ResponseEntity<StaffDto> getStaffById(@PathVariable Long id) {
        return staffService.getStaffById(id)
                .map(staffMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Прийняти нового співробітника", description = "Додає нову анкету працівника до штатного розкладу.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Співробітника успішно внесено до штату"),
            @ApiResponse(responseCode = "400", description = "Помилка валідації анкетних даних", content = @Content)
    })
    public StaffDto createStaff(@Valid @RequestBody StaffDto staffDto) {
        Staff staff = staffMapper.toEntity(staffDto);
        return staffMapper.toDto(staffService.createStaff(staff));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Оновити картку співробітника повністю", description = "Повністю переписує всі дані співробітника за його ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Картку працівника успішно перезаписано"),
            @ApiResponse(responseCode = "400", description = "Некоректні вхідні валідаційні дані", content = @Content),
            @ApiResponse(responseCode = "404", description = "Працівника з таким ID не знайдено", content = @Content)
    })
    public ResponseEntity<StaffDto> updateStaff(@PathVariable Long id, @Valid @RequestBody StaffDto staffDto) {
        Staff staff = staffMapper.toEntity(staffDto);
        return staffService.updateStaff(id, staff)
                .map(staffMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Змінити окремі дані працівника", description = "Використовується, наприклад, для підвищення заробітної плати чи зміни посади.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Дані працівника успішно модифіковано"),
            @ApiResponse(responseCode = "404", description = "Працівника за таким ID не знайдено", content = @Content)
    })
    public ResponseEntity<StaffDto> patchStaff(@PathVariable Long id, @RequestBody StaffDto staffDto) {
        Staff staff = staffMapper.toEntity(staffDto);
        return staffService.patchStaff(id, staff)
                .map(staffMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Звільнити співробітника", description = "Видаляє запис про працівника з бази даних.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Співробітника успішно видалено зі штату"),
            @ApiResponse(responseCode = "404", description = "Працівника не знайдено", content = @Content)
    })
    public ResponseEntity<Void> deleteStaff(@PathVariable Long id) {
        if (staffService.deleteStaff(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    @Operation(summary = "Звільнити весь персонал", description = "Повністю очищає штат готелю.")
    @ApiResponse(responseCode = "200", description = "Штат готелю повністю розформовано")
    public ResponseEntity<Void> deleteAllStaff() {
        staffService.deleteAllStaff();
        return ResponseEntity.ok().build();
    }
}