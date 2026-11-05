package com.swarupdhavan.appointment_booking_system_prototype.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
