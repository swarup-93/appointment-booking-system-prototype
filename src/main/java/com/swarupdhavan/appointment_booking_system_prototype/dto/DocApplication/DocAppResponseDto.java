package com.swarupdhavan.appointment_booking_system_prototype.dto.DocApplication;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class DocAppResponseDto {

    private UUID id;
    private String medicalRegistrationNumber;
    private String specialization;
    private String qualification;
    private int experienceYear;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
