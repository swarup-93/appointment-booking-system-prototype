package com.swarupdhavan.appointment_booking_system_prototype.controller.admin;

import com.swarupdhavan.appointment_booking_system_prototype.dto.DocApplication.AdminDocAppResponseDto;
import com.swarupdhavan.appointment_booking_system_prototype.dto.DocApplication.RejectionRequestDto;
import com.swarupdhavan.appointment_booking_system_prototype.service.DocAppService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

//GET     /api/admin/doctor-applications
//GET     /api/admin/doctor-applications/{id}
//PATCH   /api/admin/doctor-applications/{id}/approve
//PATCH   /api/admin/doctor-applications/{id}/reject
//PATCH   /api/admin/doctor-applications/{id}/revoke

@RestController
@RequestMapping("api/admin/DoctorApplication")
public class AdminDoctorApplicationController {
    private final DocAppService docAppService;

    public AdminDoctorApplicationController(DocAppService docAppService){
        this.docAppService = docAppService;
    }

    //get all applications
    @GetMapping
    ResponseEntity<List<AdminDocAppResponseDto>> getAllApplications(){
        List<AdminDocAppResponseDto> response = docAppService.getAllApplications();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //get application by id
    @GetMapping("/{id}")
    ResponseEntity<AdminDocAppResponseDto> getApplicationById(@PathVariable UUID id){
        AdminDocAppResponseDto response = docAppService.getApplicationById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/{id}/approve")
    ResponseEntity<AdminDocAppResponseDto> approveApplicationById(@PathVariable UUID id){
        AdminDocAppResponseDto response = docAppService.approveApplicationById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/{id}/reject")
    ResponseEntity<AdminDocAppResponseDto> rejectApplicationById(@PathVariable UUID id,@Valid @RequestBody RejectionRequestDto rejectionDto){
        AdminDocAppResponseDto response = docAppService.rejectApplicationById(id, rejectionDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
