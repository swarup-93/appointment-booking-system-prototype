package com.swarupdhavan.appointment_booking_system_prototype.service;

import com.swarupdhavan.appointment_booking_system_prototype.dto.DocApplication.*;
import com.swarupdhavan.appointment_booking_system_prototype.dto.doctor.DoctorRequestDto;
import com.swarupdhavan.appointment_booking_system_prototype.entity.Doctor;
import com.swarupdhavan.appointment_booking_system_prototype.entity.DoctorApplication;
import com.swarupdhavan.appointment_booking_system_prototype.entity.User;
import com.swarupdhavan.appointment_booking_system_prototype.enums.ApplicationStatus;
import com.swarupdhavan.appointment_booking_system_prototype.exception.ResourceNotFoundException;
import com.swarupdhavan.appointment_booking_system_prototype.mapper.ApplicationToDoctorMapper;
import com.swarupdhavan.appointment_booking_system_prototype.mapper.DocAppMapper;
import com.swarupdhavan.appointment_booking_system_prototype.repository.DocAppRepository;
import com.swarupdhavan.appointment_booking_system_prototype.repository.DoctorRepository;
import com.swarupdhavan.appointment_booking_system_prototype.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class DocAppService {

    private final DocAppRepository docAppRepository;
    private final DocAppMapper docAppMapper;
    private final UserService userService;
    private final UserRepository userRepository;
    private final DoctorService doctorService;
    private final ApplicationToDoctorMapper applicationToDoctorMapper;

    public DocAppService(
            DocAppRepository docAppRepository,
            DocAppMapper docAppMapper,
            UserService userService,
            UserRepository userRepository,
            DoctorService doctorService,
            ApplicationToDoctorMapper applicationToDoctorMapper
    ){
        this.docAppRepository = docAppRepository;
        this.docAppMapper = docAppMapper;
        this.userService = userService;
        this.userRepository = userRepository;
        this.doctorService = doctorService;
        this.applicationToDoctorMapper = applicationToDoctorMapper;
    }

    //to submit application
    @Transactional
    public DocAppResponseDto submitApplication(DocAppRequestDto requestDto) {

        UUID userId = UUID.fromString(SecurityContextHolder.getContext().getAuthentication().getName());

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

        DoctorApplication application = docAppMapper.mapToEntity(requestDto);
        application.setUser(user);
        docAppRepository.save(application);
        return docAppMapper.mapToDto(application);
    }

    //view application
    public DocAppResponseDto myApplication(UUID userId) {
        DoctorApplication doctorApplication = docAppRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("application not found"));

        return docAppMapper.mapToDto(doctorApplication);
    }

    // get all application (admin)
    public List<AdminDocAppResponseDto> getAllApplications(){
        List<DoctorApplication> allApplications = docAppRepository.findAll();
        return allApplications.stream()
                .map(docAppMapper::mapToAdminDto)
                .toList();
    }

    //get application by id (admin)
    public AdminDocAppResponseDto getApplicationById(UUID id) {
        DoctorApplication applicationById = docAppRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found"));

        return docAppMapper.mapToAdminDto(applicationById);
    }

    //approve the application status
    @Transactional
    public AdminDocAppResponseDto approveApplicationById(UUID id) {

        DoctorApplication applicationById = docAppRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found"));

        if (applicationById.getApplicationStatus() != ApplicationStatus.PENDING){
            throw new IllegalStateException("Only pending applications can be approved.");
        }

        //update application status
        applicationById.setApplicationStatus(ApplicationStatus.APPROVED);

        //get authenticated admin
        UUID userId = UUID.fromString(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName()
        );

        //set reviewer detail from my application
        applicationById.setReviewer(userService.getUserEntityById(userId));
        applicationById.setReviewedAt(LocalDateTime.now());

        //mapped doctor request with the application approved
        DoctorRequestDto doctorRequest = applicationToDoctorMapper.mapToDoctorRequest(applicationById);

        //creates the doctor record as it approved and sends user id with it
        doctorService.createDoctorRecord(doctorRequest, applicationById.getUser());

        //saves the application
        docAppRepository.save(applicationById);

        return docAppMapper.mapToAdminDto(applicationById);
    }

    // to reject the application
    @Transactional
    public AdminDocAppResponseDto rejectApplicationById(UUID id, RejectionRequestDto rejectionDto) {
        DoctorApplication applicationById = docAppRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found"));

        if (applicationById.getApplicationStatus() != ApplicationStatus.PENDING){
            throw new IllegalStateException("Only pending applications can be rejected.");
        }

        //update application status
        applicationById.setApplicationStatus(ApplicationStatus.REJECTED);

        //get authenticated admin
        UUID userId = UUID.fromString(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName()
        );

        //set reviewer detail
        applicationById.setReviewer(userService.getUserEntityById(userId));
        applicationById.setRejectionReason(rejectionDto.getRejectionReason());
        applicationById.setReviewedAt(LocalDateTime.now());
        docAppRepository.save(applicationById);

        return docAppMapper.mapToAdminDto(applicationById);
    }

    // todo: revoke the accepted application

    //change status to revoke
    //delete the doctor record but basis of what we gonna remove that
    //save the application

    @Transactional
    public AdminDocAppResponseDto revokeApplication(UUID id, RevocationReasonDto revocationDto){
        DoctorApplication applicationById = docAppRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found"));

        if (applicationById.getApplicationStatus() != ApplicationStatus.APPROVED){
            throw new IllegalStateException("Only Approved applications can be revoked.");
        }

        applicationById.setApplicationStatus(ApplicationStatus.REVOKED);

        //get authenticated admin
        UUID userId = UUID.fromString(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName()
        );

        applicationById.setReviewer(userService.getUserEntityById(userId));
        applicationById.setRevocationReason(revocationDto.getRevocationReason());
        applicationById.setRevokedAt(LocalDateTime.now());

        UUID applicantUserId = applicationById.getUser().getId();
        // todo need to implement the delete doctor record after revocation
        doctorService.deleteDoctorRecordByUserId(applicantUserId);

        //saves the updated application
        docAppRepository.save(applicationById);

        return docAppMapper.mapToAdminDto(applicationById);
    }


}
