package com.swarupdhavan.appointment_booking_system_prototype.mapper;

import com.swarupdhavan.appointment_booking_system_prototype.dto.DoctorSchedule.AdminDoctorScheduleResponseDto;
import com.swarupdhavan.appointment_booking_system_prototype.entity.DoctorSchedule;
import org.springframework.stereotype.Component;

@Component
public class AdminDoctorScheduleMapper {
    public AdminDoctorScheduleResponseDto mapToDto(DoctorSchedule doctorSchedule){
        AdminDoctorScheduleResponseDto dto = new AdminDoctorScheduleResponseDto();

        dto.setId(doctorSchedule.getId());
        dto.setDoctorId(doctorSchedule.getDoctor().getId());
        dto.setAvailableDate(doctorSchedule.getAvailableDate());
        dto.setEndTime(doctorSchedule.getEndTime());
        dto.setStartTime(doctorSchedule.getStartTime());
        dto.setSlotDurationMinutes(doctorSchedule.getSlotDurationMinutes());
        dto.setIsAvailable(doctorSchedule.getIsAvailable());
        dto.setIsActive(doctorSchedule.getIsActive());
        dto.setCreatedAt(doctorSchedule.getCreatedAt());
        dto.setUpdatedAt(doctorSchedule.getUpdatedAt());

        return dto;
    }
}
