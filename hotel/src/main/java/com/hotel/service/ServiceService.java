package com.hotel.service;

import com.hotel.model.Service;
import com.hotel.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceService {
    private final ServiceRepository serviceRepository;

    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }

    public Optional<Service> getServiceById(Long id) {
        return serviceRepository.findById(id);
    }

    public Service createService(Service service) {
        return serviceRepository.save(service);
    }

    public Optional<Service> updateService(Long id, Service updatedService) {
        return serviceRepository.findById(id).map(service -> {
            service.setName(updatedService.getName());
            service.setPrice(updatedService.getPrice());
            return serviceRepository.save(service);
        });
    }

    public Optional<Service> patchService(Long id, Service patchedService) {
        return serviceRepository.findById(id).map(service -> {
            if (patchedService.getName() != null) service.setName(patchedService.getName());
            if (patchedService.getPrice() != 0) service.setPrice(patchedService.getPrice());
            return serviceRepository.save(service);
        });
    }

    public void deleteAllServices() {
        serviceRepository.deleteAll();
    }

    public boolean deleteService(Long id) {
        if (serviceRepository.findById(id).isPresent()) {
            serviceRepository.deleteById(id);
            return true;
        }
        return false;
    }
}