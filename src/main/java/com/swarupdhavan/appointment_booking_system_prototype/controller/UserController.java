package com.swarupdhavan.appointment_booking_system_prototype.controller;


import com.swarupdhavan.appointment_booking_system_prototype.dto.AccountStatus.RequestAccountStatusDto;
import com.swarupdhavan.appointment_booking_system_prototype.dto.AccountStatus.ResponseAccountStatusDto;
import com.swarupdhavan.appointment_booking_system_prototype.dto.RoleUpdate.RoleRequestDto;
import com.swarupdhavan.appointment_booking_system_prototype.dto.RoleUpdate.RoleResponseDto;
import com.swarupdhavan.appointment_booking_system_prototype.dto.user.ResponseUserDto;
import com.swarupdhavan.appointment_booking_system_prototype.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

//to add support bearer in doc
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("api/admin/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //get all
    @GetMapping
    ResponseEntity <List<ResponseUserDto>> getAllUsers(){
        List<ResponseUserDto> responseUser = userService.getAllUser();
        return ResponseEntity.status(HttpStatus.OK).body(responseUser);
    }

    //get by id
    @GetMapping("/{id}")
    ResponseEntity<ResponseUserDto> getUserById(@PathVariable UUID id){
        ResponseUserDto respone = userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(respone);
    }

    //update acc status (active/inactive/lock)
    @PatchMapping ("/update-status/{id}")
    ResponseEntity<ResponseAccountStatusDto> updateStatusById(@PathVariable UUID id, @Valid @RequestBody RequestAccountStatusDto request){
        ResponseAccountStatusDto response = userService.updateStatusById(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //to add new role in the set of roles
    @PatchMapping("/update-role/{id}")
    ResponseEntity<RoleResponseDto> updateRoleById(@PathVariable UUID id,@Valid @RequestBody RoleRequestDto roleRequest){
        RoleResponseDto response = userService.updateRoleById(id, roleRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/remove-role/{id}")
    ResponseEntity<RoleResponseDto> removeRoleById(@PathVariable UUID id,@Valid @RequestBody RoleRequestDto roleRequest){
        RoleResponseDto response = userService.removeRoleById(id, roleRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //to delete the user permanently
    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteUserById(@PathVariable UUID id){
        userService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
