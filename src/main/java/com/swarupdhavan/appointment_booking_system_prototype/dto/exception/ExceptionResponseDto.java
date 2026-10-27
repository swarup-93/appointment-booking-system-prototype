package com.swarupdhavan.appointment_booking_system_prototype.dto.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ExceptionResponseDto {
    private LocalDateTime timestamp;
    private int statusCode;
    private String error;
    private String message;
    private String path;
}
