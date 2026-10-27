package com.swarupdhavan.appointment_booking_system_prototype.controller;

import com.swarupdhavan.appointment_booking_system_prototype.dto.role.RequestRoleDto;
import com.swarupdhavan.appointment_booking_system_prototype.dto.role.ResponseRoleDto;
import com.swarupdhavan.appointment_booking_system_prototype.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    //dependency injections
    private final RoleService roleService;

    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    //add new role
    @PostMapping
    ResponseEntity<ResponseRoleDto> addRole(@Valid @RequestBody RequestRoleDto request){
        ResponseRoleDto Response = roleService.addRole(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(Response);
    }

    //get all the roles
    @GetMapping
    ResponseEntity<List<ResponseRoleDto>> getAllRoles(){
        List <ResponseRoleDto> responseRoleDtos = roleService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(responseRoleDtos);
    }

    //get role by id
    @GetMapping("/{id}")
    ResponseEntity<ResponseRoleDto> getRole(@PathVariable UUID id){
        ResponseRoleDto Response = roleService.getRoleById(id);
        return ResponseEntity.status(HttpStatus.OK).body(Response);
    }

    //delete role by id
    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteRole(@PathVariable UUID id){
        roleService.deleteRole(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("role deleted successfully");
    }
}
