package com.swarupdhavan.appointment_booking_system_prototype.entity;

import com.swarupdhavan.appointment_booking_system_prototype.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity

public class users {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID role_id;

    @Column(nullable = false, length = 200)
    private String fullName;

    @Column(nullable = false, length = 255, unique = true)
    private String email;
    private String passwordHash;

    @Column(nullable = false)
    private int phoneNumber;
    @Column(nullable = false)
    private AccountStatus accountStatus;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
