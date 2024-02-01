package com.devgrp.fitnesswebapp.util;

import com.devgrp.fitnesswebapp.dto.WorkoutPlanDTO;
import lombok.Data;

@Data
public class AddWorkoutPlan {
    private String userEmail;
    private WorkoutPlanDTO workoutPlanDTO;
}
