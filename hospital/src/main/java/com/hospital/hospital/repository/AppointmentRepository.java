package com.hospital.hospital.repository;

import com.hospital.hospital.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    List<Appointment> findByDoctorId(Long doctorId);
    List<Appointment> findByPatientId(Long patientId);

    static boolean existsByDoctorIdAndAppointmentTime(Long doctorId, LocalDateTime appointmentTime) {
        return false;
    }

}
