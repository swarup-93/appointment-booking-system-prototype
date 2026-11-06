package com.swarupdhavan.appointment_booking_system_prototype.service;

import com.swarupdhavan.appointment_booking_system_prototype.dto.patient.RequestPatientDto;
import com.swarupdhavan.appointment_booking_system_prototype.dto.patient.ResponsePatientDto;
import com.swarupdhavan.appointment_booking_system_prototype.dto.patient.UpdatePatientDto;
import com.swarupdhavan.appointment_booking_system_prototype.entity.Patient;
import com.swarupdhavan.appointment_booking_system_prototype.entity.User;
import com.swarupdhavan.appointment_booking_system_prototype.exception.ResourceNotFoundException;
import com.swarupdhavan.appointment_booking_system_prototype.mapper.PatientMapper;
import com.swarupdhavan.appointment_booking_system_prototype.repository.PatientRepository;
import com.swarupdhavan.appointment_booking_system_prototype.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PatientService {
private final PatientRepository patientRepository;
private final PatientMapper patientMapper;
private final UserRepository userRepository;

    public PatientService(PatientRepository patientRepository, PatientMapper patientMapper, UserRepository userRepository) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
        this.userRepository = userRepository;
    }

    //to create the new patient
    public ResponsePatientDto createPatientProfile(RequestPatientDto requestPatientDto) {
        UUID userId = UUID.fromString(SecurityContextHolder.getContext().getAuthentication().getName());

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

        if (patientRepository.findByUser_Id(userId).isPresent()) {
            throw new IllegalStateException(
                    "Patient profile already exists"
            );
        }

        Patient patient = patientMapper.mapToEntity(requestPatientDto);
        patientRepository.save(patient);

        return patientMapper.mapToDto(patient);
    }

    //to get the
    public ResponsePatientDto getPatientProfile(UUID patientId){
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(()->
                        new ResourceNotFoundException("Patient with the id "+ patientId +" not present")
                );
        return patientMapper.mapToDto(patient);
    }

    public ResponsePatientDto updatePatientProfile(UUID patientId, UpdatePatientDto updateRequest) {
        Patient patientRecord = patientRepository.findById(patientId)
                .orElseThrow(()-> new ResourceNotFoundException("Patient with the id "+ patientId +" not present"));

        if (updateRequest.getDateOfBirth() != null) {
            patientRecord.setDateOfBirth(updateRequest.getDateOfBirth());
        }

        if (updateRequest.getGender() != null) {
            patientRecord.setGender(updateRequest.getGender());
        }

        if (updateRequest.getAddress() != null) {
            patientRecord.setAddress(updateRequest.getAddress());
        }

        patientRepository.save(patientRecord);

        return patientMapper.mapToDto(patientRecord);
    }
}
