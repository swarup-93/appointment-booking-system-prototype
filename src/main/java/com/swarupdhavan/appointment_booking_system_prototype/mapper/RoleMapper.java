package com.swarupdhavan.appointment_booking_system_prototype.mapper;

import com.swarupdhavan.appointment_booking_system_prototype.dto.role.RequestRoleDto;
import com.swarupdhavan.appointment_booking_system_prototype.dto.role.ResponseRoleDto;
import com.swarupdhavan.appointment_booking_system_prototype.entity.Role;

public class RoleMapper {
    public Role mapToEntity(RequestRoleDto dto){
        Role entity = new Role();
        entity.setName(entity.getName());
        return entity;
    }

    public ResponseRoleDto mapToDto(Role entity, String message) {
        ResponseRoleDto dto = new ResponseRoleDto();
        dto.setName(entity.getName());
        dto.setMessage(message);
        return dto;
    }
}
