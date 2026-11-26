package com.swarupdhavan.appointment_booking_system_prototype.controller.admin;


import com.swarupdhavan.appointment_booking_system_prototype.dto.doctor.DoctorResponseDto;
import com.swarupdhavan.appointment_booking_system_prototype.service.DoctorService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

//GET     /api/admin/doctors
//GET     /api/admin/doctors/{id}
//Patch  /api/admin/doctors/{id}  //to softly delete

//to add support bearer in doc
@SecurityRequirement(name = "bearerAuth")

@RestController
@RequestMapping("api/admin/doctor")
public class AdminDoctorController {

    public final DoctorService doctorService;

    public AdminDoctorController(DoctorService doctorService){
        this.doctorService = doctorService;
    }

    //get all doctors
    @GetMapping
    ResponseEntity<List<DoctorResponseDto>> getAllDoctor(){
        List<DoctorResponseDto> doctorResponse = doctorService.getAllDoctors();
        return ResponseEntity.status(HttpStatus.OK).body(doctorResponse);
    }

    //get doctor by id
    @GetMapping("/{id}")
    ResponseEntity<DoctorResponseDto> getDoctorById(@PathVariable UUID id){
        DoctorResponseDto doctorResponse = doctorService.getDoctorById(id);
        return ResponseEntity.status(HttpStatus.OK).body(doctorResponse);
    }

    //deactivate the doctor
    @PatchMapping("/{id}")
    ResponseEntity<DoctorResponseDto> deActivateDoctor(@PathVariable UUID id){
        doctorService.deactivateDoctorRecordByUserId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
