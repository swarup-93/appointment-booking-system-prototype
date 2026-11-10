package com.swarupdhavan.appointment_booking_system_prototype.dto.RoleUpdate;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoleRequestDto {
    @NotBlank(message = "role name can not be empty")
    private String name;
}
