package com.swarupdhavan.appointment_booking_system_prototype.repository;

import com.swarupdhavan.appointment_booking_system_prototype.dto.DoctorSchedule.AdminDoctorScheduleResponseDto;
import com.swarupdhavan.appointment_booking_system_prototype.entity.Doctor;
import com.swarupdhavan.appointment_booking_system_prototype.entity.DoctorSchedule;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DoctorScheduleRepository extends JpaRepository<DoctorSchedule, UUID> {
    List<DoctorSchedule> findAllByDoctor_User_Username(String username);

    DoctorSchedule findByIdAndDoctorId(UUID id, UUID doctorId);

    Optional<DoctorSchedule> findByIdAndDoctor_User_Username(
            UUID id,
            String username
    );

    List<DoctorSchedule> findAllByDoctor_Id(UUID doctorId);

//    Optional<DoctorSchedule> findByIdAndDoctor_User_Username(UUID id,String username);
}
