package com.swarupdhavan.appointment_booking_system_prototype.service;

import com.swarupdhavan.appointment_booking_system_prototype.dto.role.RequestRoleDto;
import com.swarupdhavan.appointment_booking_system_prototype.dto.role.ResponseRoleDto;
import com.swarupdhavan.appointment_booking_system_prototype.entity.Role;
import com.swarupdhavan.appointment_booking_system_prototype.exception.ResourceNotFound;
import com.swarupdhavan.appointment_booking_system_prototype.mapper.RoleMapper;
import com.swarupdhavan.appointment_booking_system_prototype.repository.RoleRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper mapper;

    //DI
    public RoleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
        this.mapper = new RoleMapper();
    }

    // get role by id
    public ResponseRoleDto getRoleById(UUID id) {
        Role response = roleRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFound("Role with id "+id+" not found"));

        String message = "Role retrieved successfully";

        return mapper.mapToDto(response, message);
    }

    // get all the role
    public List<ResponseRoleDto> getAll() {
        List<Role> allRoles = roleRepository.findAll();
        return allRoles.stream()
                .map(role -> mapper.mapToDto(role, "Roles fetched successfully"))
                .toList();
    }

    // add new role
    public ResponseRoleDto addRole(@Valid RequestRoleDto request) {
        Role role = mapper.mapToEntity(request);
        Role RoleResponse = roleRepository.save(role);
        String message = "Role added successfully";

        return mapper.mapToDto(RoleResponse, message);
    }

    // delete existing role
    public void deleteRole(UUID id) {
        roleRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFound("Role with id "+id+" not found"));

        roleRepository.deleteById(id);
    }
}
