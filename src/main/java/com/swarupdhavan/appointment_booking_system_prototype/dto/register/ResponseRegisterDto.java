package com.swarupdhavan.appointment_booking_system_prototype.dto.register;

import com.swarupdhavan.appointment_booking_system_prototype.entity.Role;
import com.swarupdhavan.appointment_booking_system_prototype.enums.AccountStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ResponseRegisterDto {
    private UUID id;
    private String username;
    private String fullName;
    private String email;
    private String phoneNumber;
    private Set<String> role;
    private AccountStatus accountStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
