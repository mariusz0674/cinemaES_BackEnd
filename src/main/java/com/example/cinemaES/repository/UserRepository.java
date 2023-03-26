package com.example.cinemaES.repository;

import java.util.Optional;

import com.example.cinemaES.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);


}