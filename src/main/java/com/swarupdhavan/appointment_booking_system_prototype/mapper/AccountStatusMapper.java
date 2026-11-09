package com.swarupdhavan.appointment_booking_system_prototype.mapper;

import com.swarupdhavan.appointment_booking_system_prototype.dto.AccountStatus.ResponseAccountStatusDto;
import com.swarupdhavan.appointment_booking_system_prototype.dto.user.ResponseUserDto;
import com.swarupdhavan.appointment_booking_system_prototype.entity.User;
import org.springframework.stereotype.Component;

@Component
public class AccountStatusMapper {
    public ResponseAccountStatusDto mapToDto(User user, String message){
        ResponseAccountStatusDto response = new ResponseAccountStatusDto();

        response.setUserId(user.getId());
        response.setAccountStatus(user.getAccountStatus());
        response.setMessage(message);

        return response;
    }

}
