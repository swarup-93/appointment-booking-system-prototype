package com.swarupdhavan.appointment_booking_system_prototype.repository;

import com.swarupdhavan.appointment_booking_system_prototype.entity.DoctorSchedule;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DoctorScheduleRepository extends JpaRepository<DoctorSchedule, UUID> {
    List<DoctorSchedule> findAllByDoctor_Id(UUID id);
}
