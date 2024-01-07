package com.devgrp.fitnesswebapp.repository;

import com.devgrp.fitnesswebapp.entity.WorkoutPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutPlanRepository extends JpaRepository<WorkoutPlan, Integer> {}
