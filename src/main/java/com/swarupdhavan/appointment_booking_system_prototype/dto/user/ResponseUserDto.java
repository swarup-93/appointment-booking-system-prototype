package com.swarupdhavan.appointment_booking_system_prototype.dto.user;

import com.swarupdhavan.appointment_booking_system_prototype.entity.Role;
import com.swarupdhavan.appointment_booking_system_prototype.enums.AccountStatus;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor

public class ResponseUserDto {
    private UUID id;
    private String username;
    private String fullName;
    private String email;
    private String phoneNumber;
    private Set<Role> role;
    private AccountStatus accountStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
