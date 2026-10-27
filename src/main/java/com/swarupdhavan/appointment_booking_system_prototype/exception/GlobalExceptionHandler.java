package com.swarupdhavan.appointment_booking_system_prototype.exception;

import com.swarupdhavan.appointment_booking_system_prototype.dto.exception.ExceptionResponseDto;
import com.swarupdhavan.appointment_booking_system_prototype.dto.exception.ValidationExceptionResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.method.MethodValidationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

//  for any kind of generic exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDto> handleException(
            Exception ex,
            HttpServletRequest request
    ){
        ExceptionResponseDto exceptionResponse = new ExceptionResponseDto(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                "something went wrong",
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exceptionResponse);
    }

//  runtime exception handler
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponseDto> handleRuntimeException(
            RuntimeException ex,
            HttpServletRequest request
    ){
        ExceptionResponseDto exceptionResponse = new ExceptionResponseDto(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exceptionResponse);
    }

//  custom exception (if resource not found)
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ExceptionResponseDto> handleResourceNotFound(
            ResourceNotFound ex,
            HttpServletRequest request
    ){
        ExceptionResponseDto exceptionResponse = new ExceptionResponseDto(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exceptionResponse);
    }

//  custom exception (if resource get duplicate)
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ExceptionResponseDto> handleResourceNotFound(
            DuplicateResourceException ex,
            HttpServletRequest request
    ){
        ExceptionResponseDto exceptionResponse = new ExceptionResponseDto(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(exceptionResponse);
    }

//   for invalid method like GET POST
    @ExceptionHandler(MethodValidationException.class)
    public ResponseEntity<ExceptionResponseDto> handleMethodValidationException(
            MethodValidationException ex,
            HttpServletRequest request
    ){
        ExceptionResponseDto exceptionResponse = new ExceptionResponseDto(
                LocalDateTime.now(),
                HttpStatus.METHOD_NOT_ALLOWED.value(),
                HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(exceptionResponse);
    }

//  for validation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionResponseDto> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ){
        Map<String,String> FieldErrors = new HashMap<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(error -> FieldErrors.put(error.getField(), error.getDefaultMessage()));

        ValidationExceptionResponseDto exceptionResponse = new ValidationExceptionResponseDto(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI(),
                FieldErrors
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(exceptionResponse);
    }
}
