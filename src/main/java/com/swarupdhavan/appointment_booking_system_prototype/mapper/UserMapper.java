package com.swarupdhavan.appointment_booking_system_prototype.mapper;

import com.swarupdhavan.appointment_booking_system_prototype.dto.user.RequestUserDto;
import com.swarupdhavan.appointment_booking_system_prototype.dto.user.ResponseUserDto;
import com.swarupdhavan.appointment_booking_system_prototype.entity.Role;
import com.swarupdhavan.appointment_booking_system_prototype.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapToEntity(RequestUserDto requst, Role role){
        User user = new User();

        user.setUsername(requst.getUsername());
        user.setFullName(requst.getFullName());
        user.setFullName(requst.getFullName());
        user.setPhoneNumber(requst.getPhoneNumber());
        user.setPasswordHash(requst.getPassword());
        user.getRoles().add(role);

        return user;
    }

    public ResponseUserDto mapToDto(User user){
        ResponseUserDto response = new ResponseUserDto();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setFullName(user.getFullName());
        response.setAccountStatus(user.getAccountStatus());
        response.setEmail(user.getEmail());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setRole(user.getRoles());
        response.setCreatedAt(user.getCreatedAt());
        response.setUpdatedAt(user.getUpdatedAt());

        return response;
    }
}
