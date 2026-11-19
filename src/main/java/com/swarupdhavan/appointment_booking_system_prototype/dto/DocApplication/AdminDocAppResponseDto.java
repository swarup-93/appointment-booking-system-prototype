package com.swarupdhavan.appointment_booking_system_prototype.dto.DocApplication;

import com.swarupdhavan.appointment_booking_system_prototype.enums.ApplicationStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class AdminDocAppResponseDto {
    private UUID id;
    private String medicalRegistrationNumber;
    private String specialization;
    private String qualification;
    private int experienceYear;
    private ApplicationStatus applicationStatus;
    private String rejectionReason;
    private String revocationReason;
    private String reviewedBy;
    private LocalDateTime revokedAt;
    private LocalDateTime reviewedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
