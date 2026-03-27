package com.st.auth.repository;

import com.st.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String username);
    Optional<User> findByEmail(String email);
    boolean existsByName(String username);
    boolean existsByEmail(String email);
}