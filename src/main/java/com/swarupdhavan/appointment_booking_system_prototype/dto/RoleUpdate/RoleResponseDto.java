package com.swarupdhavan.appointment_booking_system_prototype.dto.RoleUpdate;

import com.swarupdhavan.appointment_booking_system_prototype.entity.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class RoleResponseDto {

    private UUID UserId;
    private Set<Role> roles = new HashSet<>();
    private String message;
}
