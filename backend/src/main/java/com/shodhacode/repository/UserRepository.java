package com.shodhacode.repository;

import com.shodhacode.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// Repository for handling User entity operations
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}