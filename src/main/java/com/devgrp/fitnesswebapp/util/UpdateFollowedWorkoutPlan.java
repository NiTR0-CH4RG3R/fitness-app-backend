package com.devgrp.fitnesswebapp.util;

import com.devgrp.fitnesswebapp.dto.WorkoutPlanDTO;
import lombok.Data;

@Data
public class UpdateFollowedWorkoutPlan {
    private String userEmail;
    private WorkoutPlanDTO workoutPlanDTO;
}
