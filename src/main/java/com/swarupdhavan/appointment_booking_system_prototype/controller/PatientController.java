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
import com.swarupdhavan.appointment_booking_system_prototype.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
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
        UUID patientId = UUID.fromString(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName()
        );

        ResponsePatientDto patientResponse = patientService.getPatientProfile(patientId);
        return ResponseEntity.status(HttpStatus.OK).body(patientResponse);
    }

    //update patient profile
    @PatchMapping("/me")
    ResponseEntity<ResponsePatientDto> updatePatientProfile(@RequestBody UpdatePatientDto updateRequest){
        UUID patientId = UUID.fromString(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName()
        );

        ResponsePatientDto responsePatient = patientService.updatePatientProfile(patientId,updateRequest);

        return ResponseEntity.status(HttpStatus.OK).body(responsePatient);
    }
}
