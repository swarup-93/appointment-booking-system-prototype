package com.swarupdhavan.appointment_booking_system_prototype.controller.admin;

import com.swarupdhavan.appointment_booking_system_prototype.dto.DoctorSchedule.AdminDoctorScheduleResponseDto;
import com.swarupdhavan.appointment_booking_system_prototype.service.DoctorScheduleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//GET	    /api/admin/schedules	                        Fetch all doctors' schedules
//GET	    /api/admin/schedules/{id}	                    Fetch a particular schedule by ID
//GET	    /api/admin/schedules/doctor/{doctorId}	        Fetch schedules belonging to a particular doctor
//PATCH	    /api/admin/schedules/{id}/availability	        Enable or disable a schedule
//DELETE	/api/admin/schedules/{id}	                    Delete a schedule

// adds bearer support to open api
@SecurityRequirement(name = "bearer")

@RestController
@RequestMapping("api/admin/doctor-schedule")
public class AdminDoctorScheduleController {
    private final DoctorScheduleService doctorScheduleService;

    public AdminDoctorScheduleController(DoctorScheduleService doctorScheduleService){
        this.doctorScheduleService = doctorScheduleService;
    }

    // get all doctors
    @GetMapping
    ResponseEntity<List<AdminDoctorScheduleResponseDto>> getAllSchedule(){
        List<AdminDoctorScheduleResponseDto> allSchedules = doctorScheduleService.getAllSchedule();
        return ResponseEntity.status(HttpStatus.OK).body(allSchedules);
    }
}
