package com.swarupdhavan.appointment_booking_system_prototype.dto.DocApplication;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DocAppRequestDto {

    //medicalRegistrationNumber
    @NotBlank(message = "medical registration number can not be empty")
    @Size(max = 100, message = "medical registration number can not exceed 100")
    private String medicalRegistrationNumber;

    //specialization
    @NotBlank(message = "specialization field can not be empty")
    @Size(max=100, message = "Specialization can not exceed 100 character")
    private String specialization;

    //qualification
    @NotBlank(message = "qualification field can not be empty")
    @Size(max = 150, message = "Qualification can to exceed 150 character")
    private String qualification;

    //experienceYear
    @NotBlank(message = "experience field can not be empty")
    @Min(value = 0, message = "Experience year can not be negative")
    private int experienceYear;
}
