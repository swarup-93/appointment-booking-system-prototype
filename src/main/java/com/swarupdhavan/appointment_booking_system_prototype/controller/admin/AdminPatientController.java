package com.swarupdhavan.appointment_booking_system_prototype.controller.admin;

import com.swarupdhavan.appointment_booking_system_prototype.dto.patient.ResponsePatientDto;
import com.swarupdhavan.appointment_booking_system_prototype.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


//GET     /api/admin/patients
//GET     /api/admin/patients/{id}
//DELETE  /api/admin/patients/{id}


@RestController
@RequestMapping("api/admin/patients")
public class AdminPatientController {

    private final PatientService patientService;

    public AdminPatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    //get all patients
    @GetMapping
    ResponseEntity<List<ResponsePatientDto>> getAllPatients(){
        List<ResponsePatientDto> response = patientService.getAllPatients();
        ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //get patient by id

    //hard delete patient
}
