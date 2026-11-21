package com.swarupdhavan.appointment_booking_system_prototype.service;

import com.swarupdhavan.appointment_booking_system_prototype.dto.RoleUpdate.RoleRequestDto;
import com.swarupdhavan.appointment_booking_system_prototype.dto.patient.RequestPatientDto;
import com.swarupdhavan.appointment_booking_system_prototype.dto.patient.ResponsePatientDto;
import com.swarupdhavan.appointment_booking_system_prototype.dto.patient.UpdatePatientDto;
import com.swarupdhavan.appointment_booking_system_prototype.entity.Patient;
import com.swarupdhavan.appointment_booking_system_prototype.entity.Role;
import com.swarupdhavan.appointment_booking_system_prototype.entity.User;
import com.swarupdhavan.appointment_booking_system_prototype.exception.ResourceNotFoundException;
import com.swarupdhavan.appointment_booking_system_prototype.mapper.PatientMapper;
import com.swarupdhavan.appointment_booking_system_prototype.repository.PatientRepository;
import com.swarupdhavan.appointment_booking_system_prototype.repository.RoleRepository;
import com.swarupdhavan.appointment_booking_system_prototype.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private final UserRepository userRepository;
    private final UserService userService;

    public PatientService(PatientRepository patientRepository,
                          PatientMapper patientMapper,
                          UserRepository userRepository,
                          UserService userService) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    //to create the new patient profile
    @Transactional
    public ResponsePatientDto createPatientProfile(RequestPatientDto requestPatientDto) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUserName(username);
        UUID userId = user.getId();

        // to handle already available profile
        if (patientRepository.findByUser_Id(userId).isPresent()) {
            throw new IllegalStateException(
                    "Patient profile already exists"
            );
        }

        // adds patient role
        RoleRequestDto requestDto = new RoleRequestDto();
        requestDto.setName("ROLE_PATIENT");

        userService.updateRoleById(userId, requestDto);

        Patient patient = patientMapper.mapToEntity(requestPatientDto);
        patient.setUser(user);
        patientRepository.save(patient);

        return patientMapper.mapToDto(patient);
    }

    //update profile
    @Transactional
    public ResponsePatientDto updatePatientProfile(UUID userId, UpdatePatientDto updateRequest) {
        Patient patientRecord = patientRepository.findByUser_Id(userId)
                .orElseThrow(()-> new ResourceNotFoundException("Patient with the id "+ userId +" not present"));

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

    //get retrieve all patients
    public List<ResponsePatientDto> getAllPatients() {
        List<Patient> allPatient = patientRepository.findAll();

        return allPatient.stream()
                .map(patientMapper::mapToDto)
                .toList();
    }

    //get patient by id
    public ResponsePatientDto getPatientById(UUID id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("patient not found"));

        return patientMapper.mapToDto(patient);
    }

    public ResponsePatientDto getPatientByUserId(UUID id){
        Patient patient = patientRepository.findByUser_Id(id).orElseThrow(() ->
                new ResourceNotFoundException("Patient not found"));

        return patientMapper.mapToDto(patient);
    }

    public void deletePatientById(UUID id) {
        Patient patientToBeDeleted = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("patient not found"));

        patientRepository.delete(patientToBeDeleted);
    }
}
