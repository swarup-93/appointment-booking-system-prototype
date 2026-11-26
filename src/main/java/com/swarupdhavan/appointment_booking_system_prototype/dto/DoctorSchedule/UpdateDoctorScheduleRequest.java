package com.swarupdhavan.appointment_booking_system_prototype.dto.DoctorSchedule;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class UpdateDoctorScheduleRequest {
    private LocalDate availableDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private Boolean isAvailable;
}
