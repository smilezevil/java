package com.hotel.controller;

import com.hotel.dto.ServiceDto;
import com.hotel.mapper.ServiceMapper;
import com.hotel.model.Service;
import com.hotel.service.ServiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/services")
@RequiredArgsConstructor
public class ServiceController {
    private final ServiceService serviceService;
    private final ServiceMapper serviceMapper;

    @GetMapping
    public List<ServiceDto> getAllServices() {
        return serviceService.getAllServices().stream()
                .map(serviceMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceDto> getServiceById(@PathVariable Long id) {
        return serviceService.getServiceById(id)
                .map(serviceMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ServiceDto createService(@Valid @RequestBody ServiceDto serviceDto) {
        Service service = serviceMapper.toEntity(serviceDto);
        return serviceMapper.toDto(serviceService.createService(service));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceDto> updateService(@PathVariable Long id, @Valid @RequestBody ServiceDto serviceDto) {
        Service service = serviceMapper.toEntity(serviceDto);
        return serviceService.updateService(id, service)
                .map(serviceMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ServiceDto> patchService(@PathVariable Long id, @RequestBody ServiceDto serviceDto) {
        Service service = serviceMapper.toEntity(serviceDto);
        return serviceService.patchService(id, service)
                .map(serviceMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        if (serviceService.deleteService(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllServices() {
        serviceService.deleteAllServices();
        return ResponseEntity.ok().build();
    }
}