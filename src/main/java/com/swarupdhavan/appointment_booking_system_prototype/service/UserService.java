package com.swarupdhavan.appointment_booking_system_prototype.service;

import com.swarupdhavan.appointment_booking_system_prototype.dto.AccountStatus.RequestAccountStatusDto;
import com.swarupdhavan.appointment_booking_system_prototype.dto.AccountStatus.ResponseAccountStatusDto;
import com.swarupdhavan.appointment_booking_system_prototype.dto.RoleUpdate.RoleRequestDto;
import com.swarupdhavan.appointment_booking_system_prototype.dto.RoleUpdate.RoleResponseDto;
import com.swarupdhavan.appointment_booking_system_prototype.dto.user.ResponseUserDto;
import com.swarupdhavan.appointment_booking_system_prototype.entity.Role;
import com.swarupdhavan.appointment_booking_system_prototype.entity.User;
import com.swarupdhavan.appointment_booking_system_prototype.exception.ResourceNotFoundException;
import com.swarupdhavan.appointment_booking_system_prototype.mapper.AccountStatusMapper;
import com.swarupdhavan.appointment_booking_system_prototype.mapper.RoleUpdateMapper;
import com.swarupdhavan.appointment_booking_system_prototype.mapper.UserMapper;
import com.swarupdhavan.appointment_booking_system_prototype.repository.RoleRepository;
import com.swarupdhavan.appointment_booking_system_prototype.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AccountStatusMapper accountMapper;
    private final RoleRepository roleRepository;
    private final RoleUpdateMapper roleUpdateMapper;

    public UserService(
            UserRepository userRepository,
            UserMapper userMapper,
            AccountStatusMapper accountMapper,
            RoleRepository roleRepository,
            RoleUpdateMapper roleUpdateMapper) {

        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.accountMapper = accountMapper;
        this.roleRepository = roleRepository;
        this.roleUpdateMapper = roleUpdateMapper;

    }

    // get all users
    public List<ResponseUserDto> getAllUser() {
        List<User> allUsers = userRepository.findAll();

        return allUsers.stream()
                .map(userMapper::mapToDto)
                .toList();
    }

    // get users by id
    public ResponseUserDto getUserById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User not found"));
        return userMapper.mapToDto(user);
    }

    @Transactional
    public ResponseAccountStatusDto updateStatusById(UUID id, @Valid RequestAccountStatusDto request) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User not found"));

        user.setAccountStatus(request.getAccountStatus());
        userRepository.save(user);

        return accountMapper.mapToDto(user,"account status updated to " + request.getAccountStatus() +" successfully");
    }

    //to hard delete the account from the db permanently
    @Transactional
    public void deleteUserById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        userRepository.deleteById(id);
    }

    //to add new role
    @Transactional
    public RoleResponseDto updateRoleById(UUID id, RoleRequestDto roleRequest) {
        User userById = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Role role = roleRepository.findByName(roleRequest.getName())
                .orElseThrow(() -> new ResourceNotFoundException("role not found"));

        userById.getRoles().add(role);
        String message = "roles updated successfully";

        return roleUpdateMapper.mapToDto(userById, message);
    }

    // remove role by id
    public RoleResponseDto removeRoleById(UUID id, RoleRequestDto roleRequest) {
        User userById = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Role role = roleRepository.findByName(roleRequest.getName())
                .orElseThrow(() -> new ResourceNotFoundException("role not found"));

        if(!userById.getRoles().remove(role)){
            throw new ResourceNotFoundException("User does not have this role");
        }

        String message = "role has been deleted successfully";
        return roleUpdateMapper.mapToDto(userById,message);
    }
}
