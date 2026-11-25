package com.swarupdhavan.appointment_booking_system_prototype.dto.DoctorSchedule;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateScheduleAvailabilityDto {
    @NotNull
    private Boolean isAvailable;
}
