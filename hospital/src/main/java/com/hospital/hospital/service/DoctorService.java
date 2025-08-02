package com.hospital.hospital.service;

import com.hospital.hospital.entity.DoctorList;
import com.hospital.hospital.repository.DoctorListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    @Autowired
    private DoctorListRepository doctorRepository;

    // Get all doctors
    public List<DoctorList> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // Get doctor by ID
    public Optional<DoctorList> getDoctorById(Long id) {
        return doctorRepository.findById(id);
    }

    // Add a new doctor
    public DoctorList addDoctor(DoctorList doctor) {
        return doctorRepository.save(doctor);
    }

    // Update existing doctor
    public DoctorList updateDoctor(Long id, DoctorList updatedDoctor) {
        return doctorRepository.findById(id)
                .map(existing -> {
                    existing.setName(updatedDoctor.getName());
                    existing.setSpecialization(updatedDoctor.getSpecialization());
                    return doctorRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
    }

    // Delete a doctor
    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }
}
