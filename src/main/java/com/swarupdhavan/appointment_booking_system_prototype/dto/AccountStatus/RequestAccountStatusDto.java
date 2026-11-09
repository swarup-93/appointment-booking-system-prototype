package com.swarupdhavan.appointment_booking_system_prototype.dto.AccountStatus;

import com.swarupdhavan.appointment_booking_system_prototype.enums.AccountStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class RequestAccountStatusDto {
    @NotNull(message = "Account status is required")
    private AccountStatus accountStatus;
}
