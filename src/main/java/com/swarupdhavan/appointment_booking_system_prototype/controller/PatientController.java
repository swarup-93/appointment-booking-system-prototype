package com.swarupdhavan.appointment_booking_system_prototype.controller;

//basic api structure

//POST	/api/patient/profile	           Create patient profile
//GET	/api/patient/me	                   Get logged-in patient's profile
//PUT	/api/patient/me	                   Update own profile
//GET	/api/admin/                        patients	List all patients
//GET	/api/admin/patients/{id}	       Get patient details

import com.swarupdhavan.appointment_booking_system_prototype.dto.patient.RequestPatientDto;
import com.swarupdhavan.appointment_booking_system_prototype.dto.patient.ResponsePatientDto;
import com.swarupdhavan.appointment_booking_system_prototype.dto.patient.UpdatePatientDto;
import com.swarupdhavan.appointment_booking_system_prototype.entity.User;
import com.swarupdhavan.appointment_booking_system_prototype.service.PatientService;
import com.swarupdhavan.appointment_booking_system_prototype.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

//to add support bearer in doc
@SecurityRequirement(name = "bearerAuth")

@RestController
@RequestMapping("/api/patient")
public class PatientController {

    private final PatientService patientService;
    private final UserService userService;

    public PatientController(PatientService patientService, UserService userService) {
        this.patientService = patientService;
        this.userService = userService;
    }

    //onboarding for the profile
    @PostMapping("/creat-profile")
    ResponseEntity<ResponsePatientDto> createPatientProfile(@RequestBody RequestPatientDto patientRequest){
        ResponsePatientDto patientResponse = patientService.createPatientProfile(patientRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(patientResponse);
    }

    //retrieval of patient profile
    @GetMapping("/me")
    ResponseEntity<ResponsePatientDto> getPatientProfile() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUserName(username);
        UUID userId = user.getId();

        ResponsePatientDto patientResponse = patientService.getPatientByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(patientResponse);
    }

    //update patient profile
    @PatchMapping("/me")
    ResponseEntity<ResponsePatientDto> updatePatientProfile(@RequestBody UpdatePatientDto updateRequest){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUserName(username);
        UUID userId = user.getId();

        ResponsePatientDto responsePatient = patientService.updatePatientProfile(userId,updateRequest);

        return ResponseEntity.status(HttpStatus.OK).body(responsePatient);
    }
}
