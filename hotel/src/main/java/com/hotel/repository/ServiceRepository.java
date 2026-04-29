package com.hotel.repository;

import com.hotel.model.Service;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class ServiceRepository {
    private final Map<Long, Service> services = new HashMap<>();
    private long nextId = 1;

    public List<Service> findAll() {
        return new ArrayList<>(services.values());
    }

    public Optional<Service> findById(Long id) {
        return Optional.ofNullable(services.get(id));
    }

    public Service save(Service service) {
        if (service.getId() == null) {
            service.setId(nextId++);
        }
        services.put(service.getId(), service);
        return service;
    }

    public void deleteAll() {
        services.clear();
    }

    public void deleteById(Long id) {
        services.remove(id);
    }
}