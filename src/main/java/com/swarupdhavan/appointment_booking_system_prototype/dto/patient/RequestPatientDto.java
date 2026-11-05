package com.swarupdhavan.appointment_booking_system_prototype.dto.patient;

import com.swarupdhavan.appointment_booking_system_prototype.enums.Gender;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class RequestPatientDto {

    //dateofbirth
    @NotNull(message = "Date of birth required")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    //gender
    @NotNull(message = "Gender is required")
    private Gender gender;

    //address
    @Size(max = 255, message = "Address cannot exceed 255 characters")
    private String address;
}
