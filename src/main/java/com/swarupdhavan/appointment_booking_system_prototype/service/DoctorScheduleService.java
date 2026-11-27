package com.swarupdhavan.appointment_booking_system_prototype.service;

import com.swarupdhavan.appointment_booking_system_prototype.dto.DoctorSchedule.*;
import com.swarupdhavan.appointment_booking_system_prototype.entity.Doctor;
import com.swarupdhavan.appointment_booking_system_prototype.entity.DoctorSchedule;
import com.swarupdhavan.appointment_booking_system_prototype.exception.ResourceNotFoundException;
import com.swarupdhavan.appointment_booking_system_prototype.mapper.AdminDoctorScheduleMapper;
import com.swarupdhavan.appointment_booking_system_prototype.mapper.DoctorScheduleMapper;
import com.swarupdhavan.appointment_booking_system_prototype.repository.DoctorScheduleRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
public class DoctorScheduleService {

    private final DoctorService doctorService;
    private final DoctorScheduleRepository doctorScheduleRepository;
    private final DoctorScheduleMapper doctorScheduleMapper;
    private final AdminDoctorScheduleMapper adminDoctorScheduleMapper;

    public DoctorScheduleService(DoctorService doctorService,
                                 DoctorScheduleRepository doctorScheduleRepository,
                                 DoctorScheduleMapper doctorScheduleMapper,
                                 AdminDoctorScheduleMapper adminDoctorScheduleMapper
    ){
        this.doctorService = doctorService;
        this.adminDoctorScheduleMapper = adminDoctorScheduleMapper;
        this.doctorScheduleRepository = doctorScheduleRepository;
        this.doctorScheduleMapper = doctorScheduleMapper;
    }


    //get my schedules
    public List<DoctorScheduleResponseDto> getMySchedules() {

        //get authenticated user
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        User user = userService.getUserByUserName(username);
//        UUID userId = user.getId();

//        UUID doctorId = doctorService.getDoctorIdByUserId(userId);

        // gets schedules for the signed doctor
        List<DoctorSchedule> scheduleList = doctorScheduleRepository.findAllByDoctor_User_Username(username);

        return scheduleList.stream()
                .map(doctorScheduleMapper::mapToDto)
                .toList();
    }

    // get schedule by id for signed doctor
    public DoctorScheduleResponseDto getScheduleById(UUID id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        User user = userService.getUserByUserName(username);
//        UUID userId = user.getId();

//        UUID doctorId = doctorService.getDoctorIdByUserId(userId);

        DoctorSchedule doctorSchedule = doctorScheduleRepository.findByIdAndDoctor_User_Username(id, username)
                .orElseThrow(() ->
                            new ResourceNotFoundException("schedule not found")
                        );

        return doctorScheduleMapper.mapToDto(doctorSchedule);
    }

    //create schedule
    @Transactional
    public DoctorScheduleResponseDto createSchedule(DoctorScheduleRequestDto doctorScheduleRequest) {

        // gets the available date
        LocalDate availableDate = doctorScheduleRequest.getAvailableDate();

        // if available date is before today
        if (availableDate.isBefore(LocalDate.now())){
            throw new IllegalArgumentException("Schedule date cannot be in the past");
        }

        LocalTime startTime = doctorScheduleRequest.getStartTime();
        LocalTime endTime = doctorScheduleRequest.getEndTime();

        // if end time is before the start time
        if (!endTime.isAfter(startTime)) {
            throw new IllegalArgumentException("End time must be after start time");
        }

        // if the date is today's date it will validate the if time is after the start time or not
        if (availableDate.isEqual(LocalDate.now()) && !startTime.isAfter(LocalTime.now())) {
            throw new IllegalArgumentException("Schedule start time must be in future");
        }

        DoctorSchedule doctorSchedule = doctorScheduleMapper.mapToEntity(doctorScheduleRequest);

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Doctor doctor = doctorService.getDoctorByUsername(username);

        // to calculate the duration
        int slotDuration = (int) Duration.between(
                doctorScheduleRequest.getStartTime(),
                doctorScheduleRequest.getEndTime()
        ).toMinutes();

        doctorSchedule.setDoctor(doctor);
        doctorSchedule.setSlotDurationMinutes(slotDuration);
        DoctorSchedule doctorScheduleResponse = doctorScheduleRepository.save(doctorSchedule);

        return doctorScheduleMapper.mapToDto(doctorScheduleResponse);
    }


    // updates the record
    @Transactional
    public DoctorScheduleResponseDto updateSchedule(UUID id, UpdateDoctorScheduleRequest doctorScheduleUpdateRequest) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        DoctorSchedule doctorScheduleRecord = doctorScheduleRepository.findByIdAndDoctor_User_Username(id, username)
                                            .orElseThrow(() ->
                                                        new ResourceNotFoundException("Schedule not found")
                                                    );

        LocalDate originalDate = doctorScheduleRecord.getAvailableDate();

        // to update the available date
        if (doctorScheduleUpdateRequest.getAvailableDate() != null){
            LocalDate requestedDate = doctorScheduleUpdateRequest.getAvailableDate();

            if (requestedDate.isBefore(originalDate)){
                throw new IllegalArgumentException("You can not move schedule earlier");
            }

            doctorScheduleRecord.setAvailableDate(doctorScheduleUpdateRequest.getAvailableDate());
        }

        // to update the start time
        if (doctorScheduleUpdateRequest.getStartTime() != null){
            doctorScheduleRecord.setStartTime(doctorScheduleUpdateRequest.getStartTime());
        }

        // to update the end time
        if (doctorScheduleUpdateRequest.getEndTime() != null){
            doctorScheduleRecord.setEndTime(doctorScheduleUpdateRequest.getEndTime());
        }

        // to update the availability
        if (doctorScheduleUpdateRequest.getIsAvailable() != null){
            doctorScheduleRecord.setIsAvailable(doctorScheduleUpdateRequest.getIsAvailable());
        }

        if (doctorScheduleRecord.getAvailableDate().isBefore(LocalDate.now())){
            throw new IllegalArgumentException("Schedule date cannot be in past");
        }

        if (!doctorScheduleRecord.getStartTime()
                .isBefore(doctorScheduleRecord.getEndTime())) {
            throw new IllegalArgumentException(
                    "End time must be after start time"
            );
        }

        LocalDate availableDate = doctorScheduleRecord.getAvailableDate();
        LocalTime startTime = doctorScheduleRecord.getStartTime();
        if (availableDate.isEqual(LocalDate.now()) && !startTime.isAfter(LocalTime.now())){
            throw new IllegalArgumentException("Schedule start time should be in future");
        }

        int slotDuration = (int) Duration.between(
                doctorScheduleRecord.getStartTime(),
                doctorScheduleRecord.getEndTime()
        ).toMinutes();

        doctorScheduleRecord.setSlotDurationMinutes(slotDuration);

        return doctorScheduleMapper.mapToDto(doctorScheduleRecord);
    }

    // it is for the soft delete
    @Transactional
    public void deactivateScheduleById(UUID id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        DoctorSchedule doctorScheduleRecord = doctorScheduleRepository.findByIdAndDoctor_User_Username(id, username)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Schedule not found")
                );

        if (!doctorScheduleRecord.getIsActive()) {
            throw new IllegalStateException("Schedule is already inactive");
        }

        doctorScheduleRecord.setIsActive(false);
    }

    //updates the availability in schedule
    @Transactional
    public void updateScheduleAvailability(UUID id,UpdateScheduleAvailabilityDto requestDto) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        DoctorSchedule schedule = doctorScheduleRepository.findByIdAndDoctor_User_Username(id, username)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Schedule not found")
                );

        if (!schedule.getIsActive()) {
            throw new IllegalStateException("Cannot update availability of an inactive schedule");
        }

        schedule.setIsAvailable(requestDto.getIsAvailable());
    }

//  ----------------------------------------------------------------------------------------------------------------------
//   get all schedule (admin)

    public List<AdminDoctorScheduleResponseDto> getAllSchedule(){
        List<DoctorSchedule> doctorSchedules = doctorScheduleRepository.findAll();
        return doctorSchedules.stream()
                .map(adminDoctorScheduleMapper::mapToDto)
                .toList();
    }
}
