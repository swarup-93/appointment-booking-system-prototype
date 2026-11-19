package com.swarupdhavan.appointment_booking_system_prototype.mapper;

import com.swarupdhavan.appointment_booking_system_prototype.dto.doctor.DoctorRequestDto;
import com.swarupdhavan.appointment_booking_system_prototype.dto.doctor.DoctorResponseDto;
import com.swarupdhavan.appointment_booking_system_prototype.entity.Doctor;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {
    public Doctor mapToEntity(DoctorRequestDto request){
        Doctor doctor = new Doctor();
        doctor.setSpecialization(request.getSpecialization());
        doctor.setQualification(request.getQualification());
        doctor.setLicenceNumber(request.getLicenceNumber());
        doctor.setExperienceYear(request.getExperienceYears());
//        doctor.setConsultancyFees(request.getConsultancyFees());

        return doctor;
    }

    public DoctorResponseDto mapToDto(Doctor doctor){
        DoctorResponseDto response = new DoctorResponseDto();
        response.setId(doctor.getId());
        response.setUserId(doctor.getUser().getId());
        response.setSpecialization(doctor.getSpecialization());
        response.setQualification(doctor.getQualification());
        response.setLicenceNumber(doctor.getLicenceNumber());
        response.setExperienceYear(doctor.getExperienceYear());
        response.setConsultancyFees(doctor.getConsultancyFees());
        response.setCreatedAt(doctor.getCreatedAt());
        response.setUpdatedAt(doctor.getUpdatedAt());

        return response;
    }
}

