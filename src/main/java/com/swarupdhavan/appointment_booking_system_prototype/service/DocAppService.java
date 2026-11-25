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
    private final DoctorService doctorService;
    private final ApplicationToDoctorMapper applicationToDoctorMapper;

    public DocAppService(
            DocAppRepository docAppRepository,
            DocAppMapper docAppMapper,
            UserService userService,
            DoctorService doctorService,
            ApplicationToDoctorMapper applicationToDoctorMapper
    ){
        this.docAppRepository = docAppRepository;
        this.docAppMapper = docAppMapper;
        this.userService = userService;
        this.doctorService = doctorService;
        this.applicationToDoctorMapper = applicationToDoctorMapper;
    }

    //to submit application
    @Transactional
    public DocAppResponseDto submitApplication(DocAppRequestDto requestDto) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUserName(username);

        DoctorApplication application = docAppMapper.mapToEntity(requestDto);
        application.setApplicationStatus(ApplicationStatus.PENDING);
        application.setUser(user);
        docAppRepository.save(application);
        return docAppMapper.mapToDto(application);
    }

    //view application
    public DocAppResponseDto myApplication(UUID userId) {
        DoctorApplication doctorApplication = docAppRepository.findByUser_Id(userId)
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

    //approve the application status (admin)
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
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User admin = userService.getUserByUserName(username);

        //set reviewer detail from my application
        applicationById.setReviewer(admin);
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
        //finds application
        DoctorApplication applicationById = docAppRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found"));

        if (applicationById.getApplicationStatus() != ApplicationStatus.PENDING){
            throw new IllegalStateException("Only pending applications can be rejected.");
        }

        //update application status
        applicationById.setApplicationStatus(ApplicationStatus.REJECTED);

        //get authenticated admin
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User admin = userService.getUserByUserName(username);

        //set reviewer detail
        applicationById.setReviewer(admin);
        applicationById.setRejectionReason(rejectionDto.getRejectionReason());
        applicationById.setReviewedAt(LocalDateTime.now());
        docAppRepository.save(applicationById);

        return docAppMapper.mapToAdminDto(applicationById);
    }

    //change status to revoke
    //soft delete the doctor record based on the userId
    //save the application
    @Transactional
    public AdminDocAppResponseDto revokeApplication(UUID id, RevocationReasonDto revocationDto){

        //finds application
        DoctorApplication applicationById = docAppRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found"));

        if (applicationById.getApplicationStatus() != ApplicationStatus.APPROVED){
            throw new IllegalStateException("Only Approved applications can be revoked.");
        }

        applicationById.setApplicationStatus(ApplicationStatus.REVOKED);

        //get authenticated admin
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User admin = userService.getUserByUserName(username);

        applicationById.setReviewer(admin);
        applicationById.setRevocationReason(revocationDto.getRevocationReason());
        applicationById.setRevokedAt(LocalDateTime.now());

        UUID applicantUserId = applicationById.getUser().getId();

        // deactivate the doctor
        doctorService.deactivateDoctorRecordByUserId(applicantUserId);

        //saves the updated application
        docAppRepository.save(applicationById);

        return docAppMapper.mapToAdminDto(applicationById);
    }


}
