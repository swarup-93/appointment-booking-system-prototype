package com.swarupdhavan.appointment_booking_system_prototype.service;

import com.swarupdhavan.appointment_booking_system_prototype.dto.DoctorSchedule.DoctorScheduleResponseDto;
import com.swarupdhavan.appointment_booking_system_prototype.entity.DoctorSchedule;
import com.swarupdhavan.appointment_booking_system_prototype.entity.User;
import com.swarupdhavan.appointment_booking_system_prototype.mapper.DoctorScheduleMapper;
import com.swarupdhavan.appointment_booking_system_prototype.repository.DoctorScheduleRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DoctorScheduleService {

    private final UserService userService;
    private final DoctorService doctorService;
    private final DoctorScheduleRepository doctorScheduleRepository;
    private final DoctorScheduleMapper doctorScheduleMapper;

    public DoctorScheduleService(UserService userService,
                                 DoctorService doctorService,
                                 DoctorScheduleRepository doctorScheduleRepository,
                                 DoctorScheduleMapper doctorScheduleMapper
    ){
        this.userService = userService;
        this.doctorService = doctorService;
        this.doctorScheduleRepository = doctorScheduleRepository;
        this.doctorScheduleMapper = doctorScheduleMapper;
    }


    //get my schedules
    public List<DoctorScheduleResponseDto> getMySchedules() {

        //get authenticated user
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUserName(username);
        UUID userId = user.getId();

        UUID doctorId = doctorService.getDoctorIdByUserId(userId);

        // gets schedules for the signed doctor
        List<DoctorSchedule> scheduleList = doctorScheduleRepository.findAllByDoctor_Id(doctorId);

        return scheduleList.stream()
                .map(doctorScheduleMapper::mapToDto)
                .toList();

    }
}
