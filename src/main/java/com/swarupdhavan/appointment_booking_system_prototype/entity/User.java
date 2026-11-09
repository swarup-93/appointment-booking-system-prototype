package com.swarupdhavan.appointment_booking_system_prototype.entity;

import com.swarupdhavan.appointment_booking_system_prototype.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    //username
    @Column(nullable = false, unique = true, length = 50)
    private String username;

    //role
    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    //fullName
    @Column(nullable = false, length = 200)
    private String fullName;

    //email
    @Column(nullable = false, length = 255, unique = true)
    private String email;

    //passwordHash
    @Column(nullable = false)
    private String passwordHash;

    //phoneNumber
    @Column(nullable = false, length=20)
    private String phoneNumber;

    //acountStatus
    @Enumerated(EnumType.STRING)
    @Column(name = "account_status", nullable = false, length = 20)
    private AccountStatus accountStatus;

    //createdAt
    @Column(nullable = false)
    private LocalDateTime createdAt;

    //updatedAt
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
