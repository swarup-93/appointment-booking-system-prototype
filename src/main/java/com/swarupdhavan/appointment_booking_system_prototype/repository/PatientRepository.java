package com.swarupdhavan.appointment_booking_system_prototype.repository;

import com.swarupdhavan.appointment_booking_system_prototype.entity.Patient;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@NullMarked
public interface PatientRepository extends JpaRepository<Patient, UUID> {

//    boolean existsByUser_Id(UUID userId);
    Optional<Patient> findByUser_Id(UUID userId);

    @EntityGraph(attributePaths = "user")
    List<Patient> findAll();
}

