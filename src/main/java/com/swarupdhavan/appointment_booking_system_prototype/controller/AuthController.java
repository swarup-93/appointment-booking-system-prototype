package com.swarupdhavan.appointment_booking_system_prototype.controller;

import com.swarupdhavan.appointment_booking_system_prototype.dto.login.LoginRequestDto;
import com.swarupdhavan.appointment_booking_system_prototype.dto.login.LoginResponseDto;
import com.swarupdhavan.appointment_booking_system_prototype.dto.user.RequestUserDto;
import com.swarupdhavan.appointment_booking_system_prototype.dto.user.ResponseUserDto;
import com.swarupdhavan.appointment_booking_system_prototype.service.AuthService;
import com.swarupdhavan.appointment_booking_system_prototype.service.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final AuthService authService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, AuthService authService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.authService = authService;

    }

    // register
    @PostMapping("/register")
    public ResponseEntity<ResponseUserDto> registerUser(@Valid @RequestBody RequestUserDto requestDto){
        ResponseUserDto responseDto = authService.registerUser(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    //login
    @PostMapping("/login")
    public LoginResponseDto login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(
                loginRequestDto.getUsername(),
                loginRequestDto.getPassword()
        );

        Authentication authentication = authenticationManager.authenticate(authenticationRequest);
        String token = jwtService.generateToken(authentication);

        return new LoginResponseDto(token);
    }
}
