package com.swarupdhavan.appointment_booking_system_prototype.dto.patient;

import com.swarupdhavan.appointment_booking_system_prototype.entity.User;
import com.swarupdhavan.appointment_booking_system_prototype.enums.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ResponsePatientDto {
    private UUID id;
    private UUID userID;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String address;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
