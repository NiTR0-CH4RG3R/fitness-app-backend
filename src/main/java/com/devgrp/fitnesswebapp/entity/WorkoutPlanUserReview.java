package com.devgrp.fitnesswebapp.entity;

import com.devgrp.fitnesswebapp.entity.compositekeys.WorkoutPlanUserReviewKey;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

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
    private Integer rating;

    private String comment;

    @Column( nullable = false )
    private LocalDateTime createdDateTime = LocalDateTime.now();
}
