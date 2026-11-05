package com.swarupdhavan.appointment_booking_system_prototype.service;

import com.swarupdhavan.appointment_booking_system_prototype.dto.user.RequestUserDto;
import com.swarupdhavan.appointment_booking_system_prototype.dto.user.ResponseUserDto;
import com.swarupdhavan.appointment_booking_system_prototype.entity.Role;
import com.swarupdhavan.appointment_booking_system_prototype.entity.User;
import com.swarupdhavan.appointment_booking_system_prototype.enums.AccountStatus;
import com.swarupdhavan.appointment_booking_system_prototype.exception.ResourceNotFound;
import com.swarupdhavan.appointment_booking_system_prototype.repository.RoleRepository;
import com.swarupdhavan.appointment_booking_system_prototype.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public UserService(PasswordEncoder passwordEncoder, RoleRepository roleRepository, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

// TODO : Move this inside the auth service
    public ResponseUserDto registerUser(RequestUserDto requestDto) {

        //TODO: Create mapper for dtos
        User user = new User();
        user.setUsername(requestDto.getUsername());

        user.setFullName(requestDto.getFullName());
        user.setEmail(requestDto.getEmail());
        user.setPhoneNumber(requestDto.getPhoneNumber());

        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());
        user.setPasswordHash(encodedPassword);

        user.setAccountStatus(AccountStatus.ACTIVE);

        Role role = roleRepository.findByName("ROLE_USER").orElseThrow(()-> new ResourceNotFound("Role with name:ROLE_USER not found"));

        user.getRoles().add(role);
        userRepository.save(user);

        ResponseUserDto responseUser = new ResponseUserDto();
        responseUser.setUsername(requestDto.getUsername());
        responseUser.setFullName(requestDto.getFullName());
        responseUser.setEmail(requestDto.getEmail());
        responseUser.setPhoneNumber(requestDto.getPhoneNumber());
        responseUser.setRole(role.getName());
        responseUser.setAccountStatus(AccountStatus.ACTIVE);

        return responseUser;
    }
}
