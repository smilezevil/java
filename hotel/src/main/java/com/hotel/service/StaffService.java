package com.hotel.service;

import com.hotel.model.Staff;
import com.hotel.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StaffService {
    private final StaffRepository staffRepository;

    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    public Optional<Staff> getStaffById(Long id) {
        return staffRepository.findById(id);
    }

    public Staff createStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    public Optional<Staff> updateStaff(Long id, Staff updatedStaff) {
        return staffRepository.findById(id).map(staff -> {
            staff.setName(updatedStaff.getName());
            staff.setPosition(updatedStaff.getPosition());
            staff.setSalary(updatedStaff.getSalary());
            return staffRepository.save(staff);
        });
    }

    public Optional<Staff> patchStaff(Long id, Staff patchedStaff) {
        return staffRepository.findById(id).map(staff -> {
            if (patchedStaff.getName() != null) staff.setName(patchedStaff.getName());
            if (patchedStaff.getPosition() != null) staff.setPosition(patchedStaff.getPosition());
            if (patchedStaff.getSalary() != null) staff.setSalary(patchedStaff.getSalary());
            return staffRepository.save(staff);
        });
    }

    public boolean deleteStaff(Long id) {
        if (staffRepository.existsById(id)) {
            staffRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void deleteAllStaff() {
        staffRepository.deleteAll();
    }
}