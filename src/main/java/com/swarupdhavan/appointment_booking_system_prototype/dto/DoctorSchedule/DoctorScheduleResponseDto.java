package com.swarupdhavan.appointment_booking_system_prototype.dto.DoctorSchedule;

import com.swarupdhavan.appointment_booking_system_prototype.entity.Doctor;
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
public class DoctorScheduleResponseDto {
    private UUID id;
//    private Doctor doctor;
    private UUID DoctorId;
    private LocalDate availableDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private int slotDurationMinutes;
    private Boolean isAvailable = false;
    private Boolean isActive = true;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
