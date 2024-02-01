package com.devgrp.fitnesswebapp.util;

import com.devgrp.fitnesswebapp.dto.WorkoutPlanDTO;
import lombok.Data;

@Data
public class UpdateCreatedWorkoutPlan {
    private String userEmail;
    private int workoutPlanId;
    private WorkoutPlanDTO workoutPlanDTO;
}
