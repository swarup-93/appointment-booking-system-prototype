package com.swarupdhavan.appointment_booking_system_prototype.dto.DoctorSchedule;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class AdminDoctorScheduleResponseDto {
    private UUID id;
    //    private Doctor doctor;
    private UUID DoctorId;
    private LocalDate availableDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private int slotDurationMinutes;
    private Boolean isAvailable;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
