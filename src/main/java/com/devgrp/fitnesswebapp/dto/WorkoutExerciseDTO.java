package com.devgrp.fitnesswebapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutExerciseDTO {
    private Integer workoutPlanId;
    private Integer exerciseId;
    private Integer repCount;
    private Integer setCount;
    private Integer schedule;
}
