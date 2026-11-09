package com.swarupdhavan.appointment_booking_system_prototype.service;

import com.swarupdhavan.appointment_booking_system_prototype.dto.AccountStatus.RequestAccountStatusDto;
import com.swarupdhavan.appointment_booking_system_prototype.dto.AccountStatus.ResponseAccountStatusDto;
import com.swarupdhavan.appointment_booking_system_prototype.dto.user.ResponseUserDto;
import com.swarupdhavan.appointment_booking_system_prototype.entity.User;
import com.swarupdhavan.appointment_booking_system_prototype.exception.ResourceNotFoundException;
import com.swarupdhavan.appointment_booking_system_prototype.mapper.AccountStatusMapper;
import com.swarupdhavan.appointment_booking_system_prototype.mapper.UserMapper;
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

    public UserService(UserRepository userRepository, UserMapper userMapper, AccountStatusMapper accountMapper) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.accountMapper = accountMapper;
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
                .orElseThrow(()-> new ResourceNotFoundException("User with id:"+id+" not found"));
        return userMapper.mapToDto(user);
    }

    @Transactional
    public ResponseAccountStatusDto updateStatusById(UUID id, @Valid RequestAccountStatusDto request) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User with id:"+id+" not found"));

        user.setAccountStatus(request.getAccountStatus());
        userRepository.save(user);

        return accountMapper.mapToDto(user,"account status updated to " + request.getAccountStatus() +" successfully");
    }

    //to hard delete the account from the db permanently
    @Transactional
    public void deleteUserById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User with id:"+id+" not found"));
        userRepository.deleteById(id);
    }

}
