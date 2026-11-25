package com.swarupdhavan.appointment_booking_system_prototype.controller;

import com.swarupdhavan.appointment_booking_system_prototype.dto.DoctorSchedule.DoctorScheduleRequestDto;
import com.swarupdhavan.appointment_booking_system_prototype.dto.DoctorSchedule.DoctorScheduleResponseDto;
import com.swarupdhavan.appointment_booking_system_prototype.dto.DoctorSchedule.UpdateScheduleAvailabilityDto;
import com.swarupdhavan.appointment_booking_system_prototype.service.DoctorScheduleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

//POST	        /api/doctor/schedules	                Create an availability schedule
//GET	        /api/doctor/schedules	                Get all schedules belonging to the logged-in doctor
//GET	        /api/doctor/schedules/{id}	            Get a particular schedule
//PUT	        /api/doctor/schedules/{id}	            Update an existing schedule
//DELETE	    /api/doctor/schedules/{id}	            Delete a schedule
//PATCH	        /api/doctor/schedules/{id}              availability	Enable/disable a schedule

@RestController
@RequestMapping("api/doctor/schedules")
public class DoctorScheduleController {

    private final DoctorScheduleService doctorScheduleService;

    public DoctorScheduleController(DoctorScheduleService doctorScheduleService){
        this.doctorScheduleService = doctorScheduleService;
    }

    //Get all schedules belonging to the logged-in doctor
    @GetMapping
    ResponseEntity<List<DoctorScheduleResponseDto>> getMySchedules(){
        List<DoctorScheduleResponseDto> DoctorSchedule = doctorScheduleService.getMySchedules();
        return ResponseEntity.status(HttpStatus.OK).body(DoctorSchedule);
    }

    //Get a particular schedule
    @GetMapping("/{id}")
    ResponseEntity<DoctorScheduleResponseDto> getScheduleById(@PathVariable UUID id){
        DoctorScheduleResponseDto doctorScheduleResponse = doctorScheduleService.getScheduleById(id);
        return ResponseEntity.status(HttpStatus.OK).body(doctorScheduleResponse);
    }

    // create schedule
    @PostMapping
    ResponseEntity<DoctorScheduleResponseDto> createSchedule(@Valid @RequestBody DoctorScheduleRequestDto doctorScheduleRequest){
        DoctorScheduleResponseDto doctorScheduleResponse = doctorScheduleService.createSchedule(doctorScheduleRequest);
        return ResponseEntity.status(HttpStatus.OK).body(doctorScheduleResponse);
    }

    // update an existing schedule
    @PatchMapping("/{id}")
    ResponseEntity<DoctorScheduleResponseDto> updateSchedule(@PathVariable UUID id ,@Valid @RequestBody DoctorScheduleRequestDto doctorScheduleRequestDto){
        DoctorScheduleResponseDto doctorScheduleResponse = doctorScheduleService.updateSchedule(id,doctorScheduleRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(doctorScheduleResponse);
    }

    // to softly delete
    @DeleteMapping("/{id}")
    ResponseEntity<DoctorScheduleResponseDto> deactivateScheduleById(@PathVariable UUID id){
        doctorScheduleService.deactivateScheduleById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // set availability
    @PatchMapping("/{id}/availability")
    public ResponseEntity<Void> updateScheduleAvailability(@PathVariable UUID id,@Valid @RequestBody UpdateScheduleAvailabilityDto requestDto) {

        doctorScheduleService.updateScheduleAvailability(id, requestDto);

        return ResponseEntity.noContent().build();
    }
}
