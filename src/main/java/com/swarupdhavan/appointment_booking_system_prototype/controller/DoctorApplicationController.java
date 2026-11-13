package com.swarupdhavan.appointment_booking_system_prototype.controller;

import com.swarupdhavan.appointment_booking_system_prototype.dto.DocApplication.DocAppRequestDto;
import com.swarupdhavan.appointment_booking_system_prototype.dto.DocApplication.DocAppResponseDto;
import com.swarupdhavan.appointment_booking_system_prototype.service.DocAppService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/doctor-application")
public class DoctorApplicationController {

    private final DocAppService docAppService;

    public DoctorApplicationController(DocAppService docAppService){
        this.docAppService = docAppService;
    }

    //create application
    @PostMapping("/create-application")
    ResponseEntity<DocAppResponseDto> submitApplication(@Valid @RequestBody DocAppRequestDto requestDto){
        DocAppResponseDto docAppResponse = docAppService.submitApplication(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(docAppResponse);
    }

    //my application
    @GetMapping("/me")
    ResponseEntity<DocAppResponseDto> myApplication(){

        //gets the id
        UUID UserId = UUID.fromString(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName()
        );
        DocAppResponseDto docAppResponse = docAppService.myApplication(UserId);
        return ResponseEntity.status(HttpStatus.OK).body(docAppResponse);
    }
}
