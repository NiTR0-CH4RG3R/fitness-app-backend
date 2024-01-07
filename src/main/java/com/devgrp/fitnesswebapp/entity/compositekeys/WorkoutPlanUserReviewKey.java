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
public class WorkoutPlanUserReviewKey implements Serializable {
    private Integer userId;
    private Integer workoutPlanId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkoutPlanUserReviewKey that = (WorkoutPlanUserReviewKey) o;
        return Objects.equals(userId, that.userId) && Objects.equals(workoutPlanId, that.workoutPlanId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, workoutPlanId);
    }
}