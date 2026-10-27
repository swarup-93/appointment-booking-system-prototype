package com.swarupdhavan.appointment_booking_system_prototype.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class RequestUserDto {
    @NotBlank(message = "name could not be empty")
    @Size(max = 200, message = "size should not exceed 100 characters")
    private String firstName;

    @NotBlank(message = "email could not be empty")
    @Email(message = "please provide the valid email")
    @Size(max = 255 , message = "email can not exceed 255 characters")
    private String email;

    @NotBlank
    private String passwordHash;

    @NotBlank(message = "phone number could not be empty")
    @Pattern(
            regexp = "^\\+?[0-9]{10,15}$",
            message = "Please provide a valid phone number"
    )
    private int phoneNumber;
}
