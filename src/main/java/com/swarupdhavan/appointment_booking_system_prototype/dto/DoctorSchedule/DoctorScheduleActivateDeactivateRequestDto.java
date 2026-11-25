package com.swarupdhavan.appointment_booking_system_prototype.dto.DoctorSchedule;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DoctorScheduleActivateDeactivateRequestDto {

    @NotBlank(message = "isActive field can't be empty")
    private Boolean isActive = true;

}
