package com.swarupdhavan.appointment_booking_system_prototype.dto.role;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestRoleDto {

    @NotBlank(message = "role name can not be empty")
    private String name;
}
