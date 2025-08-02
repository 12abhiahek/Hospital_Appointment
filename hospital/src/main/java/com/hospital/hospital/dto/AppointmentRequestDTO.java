package com.hospital.hospital.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentRequestDTO {
    private Long doctorId;
    private Long patientId;
    private LocalDateTime appointmentTime;
}
