package com.swarupdhavan.appointment_booking_system_prototype.mapper;

import com.swarupdhavan.appointment_booking_system_prototype.dto.DocApplication.DocAppRequestDto;
import com.swarupdhavan.appointment_booking_system_prototype.dto.DocApplication.DocAppResponseDto;
import com.swarupdhavan.appointment_booking_system_prototype.entity.DoctorApplication;
import org.springframework.stereotype.Component;

@Component
public class DocAppMapper {

    public DoctorApplication mapToEntity(DocAppRequestDto request){
        DoctorApplication docApp = new DoctorApplication();

        docApp.setMedicalRegistrationNumber(request.getMedicalRegistrationNumber());
        docApp.setSpecialization(request.getSpecialization());
        docApp.setQualification(request.getQualification());
        docApp.setExperienceYears(request.getExperienceYear());

        return docApp;
    }

    public DocAppResponseDto mapToDto(DoctorApplication docApp){
        DocAppResponseDto response = new DocAppResponseDto();

        response.setId(docApp.getId());
        response.setMedicalRegistrationNumber(docApp.getMedicalRegistrationNumber());
        response.setSpecialization(docApp.getSpecialization());
        response.setQualification(docApp.getQualification());
        response.setExperienceYear(docApp.getExperienceYears());
        response.setCreatedAt(docApp.getCreatedAt());
        response.setUpdatedAt(docApp.getUpdatedAt());

        return response;
    }
}
