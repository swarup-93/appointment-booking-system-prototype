package com.swarupdhavan.appointment_booking_system_prototype.service;

import com.swarupdhavan.appointment_booking_system_prototype.dto.doctor.DoctorRequestDto;
import com.swarupdhavan.appointment_booking_system_prototype.dto.doctor.DoctorResponseDto;
import com.swarupdhavan.appointment_booking_system_prototype.entity.Doctor;
import com.swarupdhavan.appointment_booking_system_prototype.entity.User;
import com.swarupdhavan.appointment_booking_system_prototype.exception.ResourceNotFoundException;
import com.swarupdhavan.appointment_booking_system_prototype.mapper.DoctorMapper;
import com.swarupdhavan.appointment_booking_system_prototype.repository.DoctorRepository;
import com.swarupdhavan.appointment_booking_system_prototype.repository.UserRepository;
import org.springframework.data.repository.core.support.RepositoryMethodInvocationListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;
    private final RepositoryMethodInvocationListener repositoryMethodInvocationListener;

    public DoctorService(DoctorRepository doctorRepository, DoctorMapper doctorMapper, RepositoryMethodInvocationListener repositoryMethodInvocationListener){
        this.doctorRepository = doctorRepository;
        this.doctorMapper = doctorMapper;
        this.repositoryMethodInvocationListener = repositoryMethodInvocationListener;
    }

    // to find all doctors (admin)
    public List<DoctorResponseDto> getAllDoctors(){
        List<Doctor> doctorResponse = doctorRepository.findAll();
        return doctorResponse
                .stream()
                .map(doctorMapper::mapToDto)
                .toList();
    }

    //create doctor record after approval (admin)
    public void createDoctorRecord(DoctorRequestDto doctorRequestDto, User user){
        Doctor doctor = doctorMapper.mapToEntity(doctorRequestDto);
        doctor.setUser(user);
        doctorRepository.save(doctor);
    }

    public void deactivateDoctorRecordByUserId(UUID applicantUserId){
        Doctor doctor = doctorRepository.findByUser_id(applicantUserId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Doctor not found")
                );

        doctor.setIsActive(false);
    }

    //get By id
    public DoctorResponseDto getDoctorById(UUID id) {
        Doctor doctorById = doctorRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("doctor not found")
        );
        return doctorMapper.mapToDto(doctorById);
    }
}
