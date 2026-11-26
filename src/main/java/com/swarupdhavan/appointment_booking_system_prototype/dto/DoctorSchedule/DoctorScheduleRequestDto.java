package com.swarupdhavan.appointment_booking_system_prototype.dto.DoctorSchedule;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class DoctorScheduleRequestDto {

    @NotNull(message = "available date can't be null")
    private LocalDate availableDate;

    @NotNull(message = "start time can't be null")
    private LocalTime startTime;

    @NotNull(message = "end time can't be null")
    private LocalTime endTime;
}
