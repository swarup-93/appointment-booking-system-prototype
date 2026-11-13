package com.swarupdhavan.appointment_booking_system_prototype.service;

import com.swarupdhavan.appointment_booking_system_prototype.dto.DocApplication.DocAppRequestDto;
import com.swarupdhavan.appointment_booking_system_prototype.dto.DocApplication.DocAppResponseDto;
import com.swarupdhavan.appointment_booking_system_prototype.entity.DoctorApplication;
import com.swarupdhavan.appointment_booking_system_prototype.exception.ResourceNotFoundException;
import com.swarupdhavan.appointment_booking_system_prototype.mapper.DocAppMapper;
import com.swarupdhavan.appointment_booking_system_prototype.repository.DocAppRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DocAppService {

    private final DocAppRepository docAppRepository;
    private final DocAppMapper docAppMapper;

    public DocAppService(DocAppRepository docAppRepository, DocAppMapper docAppMapper){
        this.docAppRepository = docAppRepository;
        this.docAppMapper = docAppMapper;
    }

    //to submit application
    @Transactional
    public DocAppResponseDto submitApplication(DocAppRequestDto requestDto) {
        DoctorApplication application = docAppMapper.mapToEntity(requestDto);
        docAppRepository.save(application);
        return docAppMapper.mapToDto(application);
    }

    //view application
    public DocAppResponseDto myApplication(UUID userId) {
        DoctorApplication doctorApplication = docAppRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("application not found"));

        return docAppMapper.mapToDto(doctorApplication);

    }
}
