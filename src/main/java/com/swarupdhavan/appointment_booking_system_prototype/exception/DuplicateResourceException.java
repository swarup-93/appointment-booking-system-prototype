package com.swarupdhavan.appointment_booking_system_prototype.exception;

public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String message) {
        super(message);
    }
}
