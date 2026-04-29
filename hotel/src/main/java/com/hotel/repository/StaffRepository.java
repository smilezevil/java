package com.hotel.repository;

import com.hotel.model.Staff;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class StaffRepository {
    private final Map<Long, Staff> staffMap = new HashMap<>();
    private long nextId = 1;

    public List<Staff> findAll() {
        return new ArrayList<>(staffMap.values());
    }

    public Optional<Staff> findById(Long id) {
        return Optional.ofNullable(staffMap.get(id));
    }

    public Staff save(Staff staff) {
        if (staff.getId() == null) {
            staff.setId(nextId++);
        }
        staffMap.put(staff.getId(), staff);
        return staff;
    }

    public void deleteAll() {
        staffMap.clear();
    }

    public void deleteById(Long id) {
        staffMap.remove(id);
    }
}