package com.swarupdhavan.appointment_booking_system_prototype.controller;

import com.swarupdhavan.appointment_booking_system_prototype.dto.DocApplication.DocAppRequestDto;
import com.swarupdhavan.appointment_booking_system_prototype.dto.DocApplication.DocAppResponseDto;
import com.swarupdhavan.appointment_booking_system_prototype.entity.User;
import com.swarupdhavan.appointment_booking_system_prototype.service.DocAppService;
import com.swarupdhavan.appointment_booking_system_prototype.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

//to add support bearer in doc
@SecurityRequirement(name = "bearerAuth")

@RestController
@RequestMapping("api/doctor-application")
public class DoctorApplicationController {

    private final DocAppService docAppService;
    private final UserService userService;

    public DoctorApplicationController(DocAppService docAppService, UserService userService){
        this.docAppService = docAppService;
        this.userService = userService;
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
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUserName(username);
        UUID userId = user.getId();

        DocAppResponseDto docAppResponse = docAppService.myApplication(userId);
        return ResponseEntity.status(HttpStatus.OK).body(docAppResponse);
    }
}
