package com.clubconnect.clubconnect_backend.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Custom methods can be added here if needed
    List<User> findByRole(String role);
    List<User> findByNameContaining(String name);

    
}