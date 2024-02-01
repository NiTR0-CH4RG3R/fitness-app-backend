package com.devgrp.fitnesswebapp.repository;

import com.devgrp.fitnesswebapp.entity.Exercise;
import com.devgrp.fitnesswebapp.entity.WorkoutPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkoutPlanRepository extends JpaRepository<WorkoutPlan, Integer> {

}
