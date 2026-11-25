package com.swarupdhavan.appointment_booking_system_prototype.service;

import com.swarupdhavan.appointment_booking_system_prototype.dto.DoctorSchedule.DoctorScheduleRequestDto;
import com.swarupdhavan.appointment_booking_system_prototype.dto.DoctorSchedule.DoctorScheduleResponseDto;
import com.swarupdhavan.appointment_booking_system_prototype.dto.DoctorSchedule.UpdateScheduleAvailabilityDto;
import com.swarupdhavan.appointment_booking_system_prototype.entity.Doctor;
import com.swarupdhavan.appointment_booking_system_prototype.entity.DoctorSchedule;
import com.swarupdhavan.appointment_booking_system_prototype.exception.ResourceNotFoundException;
import com.swarupdhavan.appointment_booking_system_prototype.mapper.DoctorScheduleMapper;
import com.swarupdhavan.appointment_booking_system_prototype.repository.DoctorScheduleRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DoctorScheduleService {

    private final DoctorService doctorService;
    private final DoctorScheduleRepository doctorScheduleRepository;
    private final DoctorScheduleMapper doctorScheduleMapper;

    public DoctorScheduleService(DoctorService doctorService,
                                 DoctorScheduleRepository doctorScheduleRepository,
                                 DoctorScheduleMapper doctorScheduleMapper
    ){
        this.doctorService = doctorService;
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
        DoctorSchedule doctorSchedule = doctorScheduleMapper.mapToEntity(doctorScheduleRequest);

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        User user = userService.getUserByUserName(username);
//        UUID userId = user.getId();

//        UUID doctorId = doctorService.getDoctorIdByUserId(userId);
        Doctor doctor = doctorService.getDoctorByUsername(username);

        doctorSchedule.setDoctor(doctor);
        DoctorSchedule doctorScheduleResponse = doctorScheduleRepository.save(doctorSchedule);

        return doctorScheduleMapper.mapToDto(doctorScheduleResponse);
    }


    // updates the record
    @Transactional
    public DoctorScheduleResponseDto updateSchedule(UUID id,DoctorScheduleRequestDto doctorScheduleRequestDto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        DoctorSchedule doctorScheduleRecord = doctorScheduleRepository.findByIdAndDoctor_User_Username(id, username)
                                            .orElseThrow(() ->
                                                        new ResourceNotFoundException("Schedule not found")
                                                    );

        if (doctorScheduleRequestDto.getAvailableDate() != null){
            doctorScheduleRecord.setAvailableDate(doctorScheduleRequestDto.getAvailableDate());
        }

        if (doctorScheduleRequestDto.getStartTime() != null){
            doctorScheduleRecord.setStartTime(doctorScheduleRequestDto.getStartTime());
        }

        if (doctorScheduleRequestDto.getEndTime() != null){
            doctorScheduleRecord.setEndTime(doctorScheduleRequestDto.getEndTime());
        }

        if (doctorScheduleRequestDto.getIsAvailable() != null){
            doctorScheduleRecord.setIsAvailable(doctorScheduleRequestDto.getIsAvailable());
        }

        if (!doctorScheduleRecord.getStartTime()
                .isBefore(doctorScheduleRecord.getEndTime())) {
            throw new IllegalArgumentException(
                    "Start time must be before end time"
            );
        }

//        doctorScheduleRepository.save(doctorScheduleRecord);

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
}
