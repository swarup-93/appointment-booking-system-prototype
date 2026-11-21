package com.swarupdhavan.appointment_booking_system_prototype.repository;

import com.swarupdhavan.appointment_booking_system_prototype.entity.User;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@NullMarked
public interface UserRepository extends JpaRepository<User, UUID> {

    // to avoid the N + 1 query problem
    @EntityGraph(attributePaths = "roles")
    List<User> findAll();

    // to avoid the N + 1 query problem
    @EntityGraph(attributePaths = "roles")
    Optional<User> findByUsername(String username);
}
