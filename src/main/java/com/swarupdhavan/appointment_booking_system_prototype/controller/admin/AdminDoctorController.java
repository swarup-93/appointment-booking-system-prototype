package com.swarupdhavan.appointment_booking_system_prototype.controller.admin;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//GET     /api/admin/doctors
//GET     /api/admin/doctors/{id}
//DELETE  /api/admin/doctors/{id}

//to add support bearer in doc
@SecurityRequirement(name = "bearerAuth")

@RestController
@RequestMapping("api/admin/patients")
public class AdminDoctorController {

    //get all doctors

}
