package com.Back.BackEnd.repository;

import com.Back.BackEnd.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Recherche par username (ou memberId)
    Optional<User> findByUsername(String username);

    // Recherche par email
    Optional<User> findByEmail(String email);
}
