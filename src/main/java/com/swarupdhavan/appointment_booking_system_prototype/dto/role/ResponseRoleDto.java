package com.swarupdhavan.appointment_booking_system_prototype.dto.role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ResponseRoleDto {
    private UUID id;
    private String name;
}
