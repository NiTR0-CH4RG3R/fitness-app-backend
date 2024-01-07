package com.devgrp.fitnesswebapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
class WorkoutPlanUserReviewKey implements Serializable {
    private int userId;
    private int workoutPlanId;

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

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class WorkoutPlanUserReview {
    @EmbeddedId
    private WorkoutPlanUserReviewKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn
    private User user;

    @ManyToOne
    @MapsId("workoutPlanId")
    @JoinColumn
    private WorkoutPlan workoutPlan;

    @Column( nullable = false )
    private int rating;

    private String comment;

    @Column( nullable = false )
    private LocalDateTime createdDateTime = LocalDateTime.now();
}
