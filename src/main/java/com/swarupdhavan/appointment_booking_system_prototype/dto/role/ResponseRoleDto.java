package com.swarupdhavan.appointment_booking_system_prototype.dto.role;

import com.swarupdhavan.appointment_booking_system_prototype.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ResponseRoleDto {
    private UUID id;
    private String name;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String address;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
