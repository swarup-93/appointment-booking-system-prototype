package com.swarupdhavan.appointment_booking_system_prototype.repository;

import com.swarupdhavan.appointment_booking_system_prototype.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DoctorRepository extends JpaRepository<Doctor, UUID> {

}
