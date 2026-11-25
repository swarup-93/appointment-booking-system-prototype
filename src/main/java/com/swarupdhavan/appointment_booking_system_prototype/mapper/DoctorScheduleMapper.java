package com.swarupdhavan.appointment_booking_system_prototype.mapper;

import com.swarupdhavan.appointment_booking_system_prototype.dto.DoctorSchedule.DoctorScheduleRequestDto;
import com.swarupdhavan.appointment_booking_system_prototype.dto.DoctorSchedule.DoctorScheduleResponseDto;
import com.swarupdhavan.appointment_booking_system_prototype.entity.DoctorSchedule;
import org.springframework.stereotype.Component;

@Component
public class DoctorScheduleMapper {

    public DoctorSchedule mapToEntity(DoctorScheduleRequestDto doctorScheduleRequestDto){
        DoctorSchedule doctorSchedule = new DoctorSchedule();
        doctorSchedule.setAvailableDate(doctorSchedule.getAvailableDate());
        doctorSchedule.setStartTime(doctorSchedule.getStartTime());
        doctorSchedule.setEndTime(doctorSchedule.getEndTime());
        doctorSchedule.setIsAvailable(doctorSchedule.getIsAvailable());
        return doctorSchedule;
    }

    public DoctorScheduleResponseDto mapToDto(DoctorSchedule doctorSchedule){
        DoctorScheduleResponseDto doctorScheduleResponseDto = new DoctorScheduleResponseDto();
        doctorScheduleResponseDto.setId(doctorSchedule.getId());
        doctorScheduleResponseDto.setDoctor(doctorSchedule.getDoctor());
        doctorScheduleResponseDto.setAvailableDate(doctorSchedule.getAvailableDate());
        doctorScheduleResponseDto.setStartTime(doctorSchedule.getStartTime());
        doctorScheduleResponseDto.setEndTime(doctorSchedule.getEndTime());
        doctorSchedule.setSlotDurationMinutes(doctorSchedule.getSlotDurationMinutes());
        doctorScheduleResponseDto.setIsAvailable(doctorSchedule.getIsAvailable());
        doctorScheduleResponseDto.setIsActive(doctorSchedule.getIsActive());
        doctorScheduleResponseDto.setCreatedAt(doctorSchedule.getCreatedAt());
        doctorSchedule.setUpdatedAt(doctorSchedule.getUpdatedAt());

        return doctorScheduleResponseDto;
    }
}
