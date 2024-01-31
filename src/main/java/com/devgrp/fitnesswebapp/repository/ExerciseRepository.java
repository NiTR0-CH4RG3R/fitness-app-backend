package com.devgrp.fitnesswebapp.repository;

import com.devgrp.fitnesswebapp.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {
    boolean existsByName(String name);

    Optional<Exercise> findByName(String name);
}
