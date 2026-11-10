package com.swarupdhavan.appointment_booking_system_prototype.mapper;

import com.swarupdhavan.appointment_booking_system_prototype.dto.RoleUpdate.RoleResponseDto;
import com.swarupdhavan.appointment_booking_system_prototype.entity.User;
import org.springframework.stereotype.Component;

@Component
public class RoleUpdateMapper {
    public RoleResponseDto mapToDto(User user, String message){
        RoleResponseDto response = new RoleResponseDto();

        response.setUserId(user.getId());
        response.setRoles(user.getRoles());
        response.setMessage(message);

        return response;
    }
}
