package com.swarupdhavan.appointment_booking_system_prototype.controller.admin;

import com.swarupdhavan.appointment_booking_system_prototype.dto.patient.ResponsePatientDto;
import com.swarupdhavan.appointment_booking_system_prototype.repository.PatientRepository;
import com.swarupdhavan.appointment_booking_system_prototype.service.PatientService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


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
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //get patient by id
    @GetMapping("/{id}")
    ResponseEntity<ResponsePatientDto> getPatientById(@PathVariable UUID id){
        ResponsePatientDto response = patientService.getPatientById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //hard delete patient
    @DeleteMapping("/{id}")
    ResponseEntity<ResponsePatientDto> deletePatientById(@PathVariable UUID id){
        patientService.deletePatientById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
