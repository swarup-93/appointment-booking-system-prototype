package com.swarupdhavan.appointment_booking_system_prototype.dto.doctor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class DoctorResponseDto {
    private UUID id;
    private UUID userId;
    private String specialization;
    private String qualification;
    private String licenceNumber;
    private int experienceYear;
    private BigDecimal consultancyFees;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
