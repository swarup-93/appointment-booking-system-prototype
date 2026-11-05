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
    //fullname
    @NotBlank(message = "full name could not be empty")
    @Size(max = 200, message = "size should not exceed 100 characters")
    private String fullName;

    //username
    @NotBlank(message = "Username cannot be empty")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "Username can only contain letters, numbers, and underscores")
    private String username;

    //email
    @NotBlank(message = "email could not be empty")
    @Email(message = "please provide the valid email")
    @Size(max = 255 , message = "email can not exceed 255 characters")
    private String email;

    //password
    @NotBlank
    private String password;

    //phonenumber
    @NotBlank(message = "phone number could not be empty")
    @Pattern(
            regexp = "^\\+?[0-9]{10,15}$",
            message = "Please provide a valid phone number"
    )
    private String phoneNumber;
}
