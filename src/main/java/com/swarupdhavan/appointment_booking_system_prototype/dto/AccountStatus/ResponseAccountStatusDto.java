package com.swarupdhavan.appointment_booking_system_prototype.dto.AccountStatus;

import com.swarupdhavan.appointment_booking_system_prototype.enums.AccountStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor

public class ResponseAccountStatusDto {
    private UUID UserId;
    private AccountStatus accountStatus;
    private String message;
}
