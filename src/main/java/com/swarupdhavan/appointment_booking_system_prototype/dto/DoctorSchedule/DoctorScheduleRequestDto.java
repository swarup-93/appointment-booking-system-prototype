package com.swarupdhavan.appointment_booking_system_prototype.dto.DoctorSchedule;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class DoctorScheduleRequestDto {

    @NotBlank(message = "available date can't be null")
    private LocalDate availableDate;

    @NotBlank(message = "start time can't be null")
    private LocalTime startTime;

    @NotBlank(message = "end time can't be null")
    private LocalTime endTime;

    @NotBlank(message = "Availability status is required")
    private Boolean isAvailable = false;
}
