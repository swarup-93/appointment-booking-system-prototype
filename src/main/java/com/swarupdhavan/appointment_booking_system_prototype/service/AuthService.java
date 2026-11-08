package com.swarupdhavan.appointment_booking_system_prototype.service;

import com.swarupdhavan.appointment_booking_system_prototype.dto.user.RequestUserDto;
import com.swarupdhavan.appointment_booking_system_prototype.dto.user.ResponseUserDto;
import com.swarupdhavan.appointment_booking_system_prototype.entity.Role;
import com.swarupdhavan.appointment_booking_system_prototype.entity.User;
import com.swarupdhavan.appointment_booking_system_prototype.exception.ResourceNotFoundException;
import com.swarupdhavan.appointment_booking_system_prototype.mapper.RegisterMapper;
import com.swarupdhavan.appointment_booking_system_prototype.repository.RoleRepository;
import com.swarupdhavan.appointment_booking_system_prototype.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RegisterMapper registerMapper;

    public AuthService(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository, RegisterMapper registerMapper) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.registerMapper = registerMapper;
    }


    public ResponseUserDto registerUser(RequestUserDto requestDto) {
        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());
        Role role = roleRepository.findByName("ROLE_USER").orElseThrow(()-> new ResourceNotFoundException("Role with name:ROLE_USER not found"));
        User RequstedUser = registerMapper.mapToEntity(requestDto,encodedPassword,role);
        userRepository.save(RequstedUser);
        return registerMapper.mapToDto(RequstedUser);
    }
}
