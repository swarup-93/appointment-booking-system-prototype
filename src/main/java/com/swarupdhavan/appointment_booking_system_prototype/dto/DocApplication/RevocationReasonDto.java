package com.swarupdhavan.appointment_booking_system_prototype.dto.DocApplication;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RevocationReasonDto {
    @NotBlank(message = "Revocation reason is required")
    @Size(max = 500, message = "Revocation reason cannot exceed 500 characters")
    private String revocationReason;
}
