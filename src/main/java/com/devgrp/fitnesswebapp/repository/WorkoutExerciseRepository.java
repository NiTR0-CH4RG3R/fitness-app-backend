package com.devgrp.fitnesswebapp.repository;

import com.devgrp.fitnesswebapp.entity.WorkoutExercise;
import com.devgrp.fitnesswebapp.entity.compositekeys.WorkoutExerciseKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutExerciseRepository extends JpaRepository<WorkoutExercise, WorkoutExerciseKey> {}
