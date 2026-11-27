package com.swarupdhavan.appointment_booking_system_prototype.controller.admin;

import com.swarupdhavan.appointment_booking_system_prototype.dto.DoctorSchedule.AdminDoctorScheduleResponseDto;
import com.swarupdhavan.appointment_booking_system_prototype.service.DoctorScheduleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

//GET	    /api/admin-schedule	                            Fetch all doctors' schedules
//GET	    /api/admin-schedule/{id}	                    Fetch a particular schedule by ID
//GET	    /api/admin-schedule/doctor/{doctorId}	        Fetch schedules belonging to a particular doctor
//PATCH	    /api/admin-schedule/{id}/availability	        Enable or disable a schedule availability
//DELETE	/api/admin-schedule/{id}	                    deactivate a schedule

// adds bearer support to open api
@SecurityRequirement(name = "bearerAuth")

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

    // get by id
    @GetMapping("/{id}")
    ResponseEntity<AdminDoctorScheduleResponseDto> getScheduleById(UUID id){
        AdminDoctorScheduleResponseDto scheduleResponseDto = doctorScheduleService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(scheduleResponseDto);
    }

    // fetches the schedule belongs to particular doctor
    @GetMapping("/doctor/{id}")
    ResponseEntity<List<AdminDoctorScheduleResponseDto>> getSchedulesByDoctorId(@PathVariable UUID id){
        List<AdminDoctorScheduleResponseDto> scheduleResponseDto = doctorScheduleService.getSchedulesByDoctorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(scheduleResponseDto);
    }

    // enable or disable availability
    @PostMapping("/{id}/updateAvail-ability")
    ResponseEntity<AdminDoctorScheduleResponseDto> updateAvailabilityById(@PathVariable UUID id){
        AdminDoctorScheduleResponseDto updatedResponse = doctorScheduleService.updateAvailabilityById(id);
        return ResponseEntity.status(HttpStatus.OK).body(updatedResponse);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<AdminDoctorScheduleResponseDto> deactivateScheduleById(@PathVariable UUID id){
        AdminDoctorScheduleResponseDto deactivatedResponse = doctorScheduleService.AdminDeactivateScheduleById(id);
        return ResponseEntity.status(HttpStatus.OK).body(deactivatedResponse);
    }
}
