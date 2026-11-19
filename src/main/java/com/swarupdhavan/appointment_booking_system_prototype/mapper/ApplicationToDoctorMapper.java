package com.swarupdhavan.appointment_booking_system_prototype.mapper;

import com.swarupdhavan.appointment_booking_system_prototype.dto.doctor.DoctorRequestDto;
import com.swarupdhavan.appointment_booking_system_prototype.entity.DoctorApplication;
import org.springframework.stereotype.Component;

@Component
public class ApplicationToDoctorMapper {
    public DoctorRequestDto mapToDoctorRequest(DoctorApplication doctorApplication){

        DoctorRequestDto doctorRequest = new DoctorRequestDto();
        doctorRequest.setSpecialization(doctorApplication.getSpecialization());
        doctorRequest.setQualification(doctorApplication.getQualification());
        doctorRequest.setLicenceNumber(doctorApplication.getMedicalRegistrationNumber());
        doctorRequest.setExperienceYears(doctorApplication.getExperienceYears());

        return doctorRequest;
    }
}
