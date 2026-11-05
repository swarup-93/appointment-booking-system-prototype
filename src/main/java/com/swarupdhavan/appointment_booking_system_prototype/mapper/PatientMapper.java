package com.swarupdhavan.appointment_booking_system_prototype.mapper;

import com.swarupdhavan.appointment_booking_system_prototype.dto.patient.RequestPatientDto;
import com.swarupdhavan.appointment_booking_system_prototype.dto.patient.ResponsePatientDto;
import com.swarupdhavan.appointment_booking_system_prototype.entity.Patient;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper {
    public Patient mapToEntity(RequestPatientDto requestPatientDto) {
        Patient patient = new Patient();
        patient.setDateOfBirth(requestPatientDto.getDateOfBirth());
        patient.setGender(requestPatientDto.getGender());
        patient.setAddress(requestPatientDto.getAddress());

        return patient;
    }

    public ResponsePatientDto mapToDto(Patient patient) {
        ResponsePatientDto responseDto = new ResponsePatientDto();
        responseDto.setId(patient.getId());
        responseDto.setUserID(patient.getUser().getId());
        responseDto.setDateOfBirth(patient.getDateOfBirth());
        responseDto.setGender(patient.getGender());
        responseDto.setAddress(patient.getAddress());
        responseDto.setCreatedAt(patient.getCreatedAt());
        responseDto.setUpdatedAt(patient.getUpdatedAt());
        return responseDto;
    }
}
