package com.swarupdhavan.appointment_booking_system_prototype.dto.doctor;

import com.swarupdhavan.appointment_booking_system_prototype.entity.User;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class DoctorRequestDto {
//    @NotNull(message = "User ID is required")
//    private User user;

    @NotBlank(message = "Specialization is required")
    @Size(max = 100, message = "Specialization cannot exceed 100 characters")
    private String specialization;

    @NotBlank(message = "Qualification is required")
    @Size(max = 150, message = "Qualification cannot exceed 150 characters")
    private String qualification;

    @NotBlank(message = "License number is required")
    @Size(max = 100, message = "License number cannot exceed 100 characters")
    private String licenceNumber;

    @NotNull(message = "Experience years is required")
    @PositiveOrZero(message = "Experience years cannot be negative")
    private Integer experienceYears;

//    @NotNull(message = "Consultancy fees are required")
//    @DecimalMin(value = "0.01", message = "Consultancy fees must be greater than zero")
//    @Digits(
//            integer = 8,
//            fraction = 2,
//            message = "Consultancy fees must have at most 8 integer digits and 2 decimal places"
//    )
//    private BigDecimal consultancyFees;
}
