package com.swarupdhavan.appointment_booking_system_prototype.dto.patient;

import com.swarupdhavan.appointment_booking_system_prototype.enums.Gender;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
public class UpdatePatientDto {
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    private Gender gender;

    @Size(max = 255, message = "Address cannot exceed 255 characters")
    private String address;
}
