package com.swarupdhavan.appointment_booking_system_prototype.mapper;

import com.swarupdhavan.appointment_booking_system_prototype.dto.register.ResponseRegisterDto;
import com.swarupdhavan.appointment_booking_system_prototype.dto.user.RequestUserDto;
import com.swarupdhavan.appointment_booking_system_prototype.dto.user.ResponseUserDto;
import com.swarupdhavan.appointment_booking_system_prototype.entity.Role;
import com.swarupdhavan.appointment_booking_system_prototype.entity.User;
import com.swarupdhavan.appointment_booking_system_prototype.enums.AccountStatus;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Component
public class RegisterMapper {

    public User mapToEntity(RequestUserDto request, String encodedPassword, Role role) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPasswordHash(encodedPassword);
        user.setAccountStatus(AccountStatus.ACTIVE);
        user.getRoles().add(role);

        return user;
    }

    public ResponseRegisterDto mapToDto(User user) {
        ResponseRegisterDto response = new ResponseRegisterDto();

        response.setUsername(user.getUsername());
        response.setFullName(user.getFullName());
        response.setEmail(user.getEmail());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setRole(
                user.getRoles()
                        .stream()
                        .map(Role::getName)
                        .collect(Collectors.toSet())
        );
        response.setAccountStatus(user.getAccountStatus());
        response.setCreatedAt(user.getCreatedAt());
        response.setUpdatedAt(user.getUpdatedAt());

        return response;
    }
}
