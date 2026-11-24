package com.swarupdhavan.appointment_booking_system_prototype.controller;

import com.swarupdhavan.appointment_booking_system_prototype.dto.DoctorSchedule.DoctorScheduleRequestDto;
import com.swarupdhavan.appointment_booking_system_prototype.dto.DoctorSchedule.DoctorScheduleResponseDto;
import com.swarupdhavan.appointment_booking_system_prototype.entity.DoctorSchedule;
import com.swarupdhavan.appointment_booking_system_prototype.service.DoctorScheduleService;
import com.swarupdhavan.appointment_booking_system_prototype.service.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//POST	        /api/doctor/schedules	                Create an availability schedule
//GET	        /api/doctor/schedules	                Get all schedules belonging to the logged-in doctor
//GET	        /api/doctor/schedules/{id}	            Get a particular schedule
//PUT	        /api/doctor/schedules/{id}	            Update an existing schedule
//DELETE	    /api/doctor/schedules/{id}	            Delete a schedule
//PATCH	        /api/doctor/schedules/{id}/             availability	Enable/disable a schedule

@RestController
@RequestMapping("api/doctor/schedules")
public class DoctorScheduleController {

    private final DoctorScheduleService doctorScheduleService;

    public DoctorScheduleController(DoctorScheduleService doctorScheduleService){
        this.doctorScheduleService = doctorScheduleService;
    }

    @PostMapping
    ResponseEntity<List<DoctorScheduleResponseDto>> getMySchedules(){
        List<DoctorScheduleResponseDto> DoctorSchedule = doctorScheduleService.getMySchedules();
        return ResponseEntity.status(HttpStatus.OK).body(DoctorSchedule);
    }
}
