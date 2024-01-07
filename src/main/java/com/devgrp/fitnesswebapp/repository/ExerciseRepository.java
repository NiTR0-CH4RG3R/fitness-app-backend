package com.devgrp.fitnesswebapp.repository;

import com.devgrp.fitnesswebapp.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {
}
