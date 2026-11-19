package com.swarupdhavan.appointment_booking_system_prototype.service;

import com.swarupdhavan.appointment_booking_system_prototype.dto.doctor.DoctorRequestDto;
import com.swarupdhavan.appointment_booking_system_prototype.dto.doctor.DoctorResponseDto;
import com.swarupdhavan.appointment_booking_system_prototype.entity.Doctor;
import com.swarupdhavan.appointment_booking_system_prototype.exception.ResourceNotFoundException;
import com.swarupdhavan.appointment_booking_system_prototype.mapper.DoctorMapper;
import com.swarupdhavan.appointment_booking_system_prototype.repository.DoctorRepository;
import com.swarupdhavan.appointment_booking_system_prototype.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;
    private final UserRepository userRepository;

    public DoctorService(DoctorRepository doctorRepository, DoctorMapper doctorMapper, UserRepository userRepository){
        this.doctorRepository = doctorRepository;
        this.doctorMapper = doctorMapper;
        this.userRepository = userRepository;
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
    public void createDoctorRecord(DoctorRequestDto doctorRequestDto, UUID userId){
        Doctor doctor = doctorMapper.mapToEntity(doctorRequestDto);
        userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        doctorRepository.save(doctor);
    }
}
