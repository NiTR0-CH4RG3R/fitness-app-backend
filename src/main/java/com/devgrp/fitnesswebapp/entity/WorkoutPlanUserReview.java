package com.devgrp.fitnesswebapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutPlanUserReview {

    // [TODO] : Add proper references to these fields
    @Id
    private int userID;
    @Id
    private int workoutPlanID;

    private int rating;

    private String comment;

    private LocalDateTime createdDateTime;
}
