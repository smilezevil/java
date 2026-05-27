package com.hotel.controller;

import com.hotel.dto.ServiceDto;
import com.hotel.mapper.ServiceMapper;
import com.hotel.model.Service;
import com.hotel.service.ServiceService;
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
@RequestMapping("/services")
@RequiredArgsConstructor
@Tag(name = "Послуги (Services)", description = "Ендпоінти для ведення прейскуранту додаткових готельних послуг")
public class ServiceController {
    private final ServiceService serviceService;
    private final ServiceMapper serviceMapper;

    @GetMapping
    @Operation(summary = "Отримати прейскурант послуг", description = "Повертає список усіх доступних додаткових сервісів готелю")
    @ApiResponse(responseCode = "200", description = "Прейскурант успішно завантажено")
    public List<ServiceDto> getAllServices() {
        return serviceService.getAllServices().stream()
                .map(serviceMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Отримати інформацію про послугу за ID", description = "Шукає назву та ціну додаткової послуги")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Послугу успішно знайдено"),
            @ApiResponse(responseCode = "404", description = "Такої послуги не існує в системі", content = @Content)
    })
    public ResponseEntity<ServiceDto> getServiceById(@PathVariable Long id) {
        return serviceService.getServiceById(id)
                .map(serviceMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Додати нову послугу", description = "Вносить нову позицію (наприклад, 'Масаж', 'Доставка в номер') до списку послуг.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Послугу успішно внесено до бази"),
            @ApiResponse(responseCode = "400", description = "Помилка валідації параметрів послуги", content = @Content)
    })
    public ServiceDto createService(@Valid @RequestBody ServiceDto serviceDto) {
        Service service = serviceMapper.toEntity(serviceDto);
        return serviceMapper.toDto(serviceService.createService(service));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Повне перевизначення послуги", description = "Оновлює одночасно назву та вартість послуги за її ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Послугу успішно оновлено"),
            @ApiResponse(responseCode = "400", description = "Помилка валідації вхідних даних", content = @Content),
            @ApiResponse(responseCode = "404", description = "Послугу з таким ID не знайдено", content = @Content)
    })
    public ResponseEntity<ServiceDto> updateService(@PathVariable Long id, @Valid @RequestBody ServiceDto serviceDto) {
        Service service = serviceMapper.toEntity(serviceDto);
        return serviceService.updateService(id, service)
                .map(serviceMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Часткове редагування послуги", description = "Дозволяє змінити, наприклад, лише ціну чинної послуги.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Параметри послуги успішно змінені"),
            @ApiResponse(responseCode = "404", description = "Послугу не знайдено", content = @Content)
    })
    public ResponseEntity<ServiceDto> patchService(@PathVariable Long id, @RequestBody ServiceDto serviceDto) {
        Service service = serviceMapper.toEntity(serviceDto);
        return serviceService.patchService(id, service)
                .map(serviceMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Вилучити послугу з прейскуранту", description = "Видаляє послугу з бази даних.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Послугу успішно вилучено"),
            @ApiResponse(responseCode = "404", description = "Послугу з таким ID не знайдено", content = @Content)
    })
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        if (serviceService.deleteService(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    @Operation(summary = "Очистити список усіх послуг", description = "Повністю видаляє всі послуги з готельної бази.")
    @ApiResponse(responseCode = "200", description = "Всі послуги успішно ліквідовані")
    public ResponseEntity<Void> deleteAllServices() {
        serviceService.deleteAllServices();
        return ResponseEntity.ok().build();
    }
}