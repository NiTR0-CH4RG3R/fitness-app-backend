package com.devgrp.fitnesswebapp.repository;

import com.devgrp.fitnesswebapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);
    void deleteByEmail(String email);

    Optional<User> findUserByEmail(String email);

}
