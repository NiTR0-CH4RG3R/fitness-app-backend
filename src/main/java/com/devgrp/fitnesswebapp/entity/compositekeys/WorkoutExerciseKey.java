package com.devgrp.fitnesswebapp.entity.compositekeys;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class WorkoutExerciseKey implements Serializable {
    private Integer workoutPlanId;
    private Integer exerciseId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkoutExerciseKey that = (WorkoutExerciseKey) o;
        return Objects.equals(workoutPlanId, that.workoutPlanId) && Objects.equals(exerciseId, that.exerciseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(workoutPlanId, exerciseId);
    }

}