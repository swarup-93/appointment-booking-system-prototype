package com.swarupdhavan.appointment_booking_system_prototype.mapper;

import com.swarupdhavan.appointment_booking_system_prototype.dto.DoctorSchedule.DoctorScheduleRequestDto;
import com.swarupdhavan.appointment_booking_system_prototype.dto.DoctorSchedule.DoctorScheduleResponseDto;
import com.swarupdhavan.appointment_booking_system_prototype.entity.DoctorSchedule;
import org.springframework.stereotype.Component;

@Component
public class DoctorScheduleMapper {

    public DoctorSchedule mapToEntity(DoctorScheduleRequestDto doctorScheduleRequestDto){
        DoctorSchedule doctorSchedule = new DoctorSchedule();
        doctorSchedule.setAvailableDate(doctorScheduleRequestDto.getAvailableDate());
        doctorSchedule.setStartTime(doctorScheduleRequestDto.getStartTime());
        doctorSchedule.setEndTime(doctorScheduleRequestDto.getEndTime());
        return doctorSchedule;
    }

    public DoctorScheduleResponseDto mapToDto(DoctorSchedule doctorSchedule){
        DoctorScheduleResponseDto doctorScheduleResponseDto = new DoctorScheduleResponseDto();
        doctorScheduleResponseDto.setId(doctorSchedule.getId());
        doctorScheduleResponseDto.setDoctorId(doctorSchedule.getDoctor().getId());
        doctorScheduleResponseDto.setAvailableDate(doctorSchedule.getAvailableDate());
        doctorScheduleResponseDto.setStartTime(doctorSchedule.getStartTime());
        doctorScheduleResponseDto.setEndTime(doctorSchedule.getEndTime());
        doctorScheduleResponseDto.setSlotDurationMinutes(doctorSchedule.getSlotDurationMinutes());
        doctorScheduleResponseDto.setIsAvailable(doctorSchedule.getIsAvailable());
        doctorScheduleResponseDto.setIsActive(doctorSchedule.getIsActive());
        doctorScheduleResponseDto.setCreatedAt(doctorSchedule.getCreatedAt());
        doctorScheduleResponseDto.setUpdatedAt(doctorSchedule.getUpdatedAt());

        return doctorScheduleResponseDto;
    }
}
