package com.devgrp.fitnesswebapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkoutPlan {
    @Id
    @GeneratedValue
    private Integer id;

    @Column( nullable = false )
    private String name ;

    @Column( nullable = false )
    private Integer duration;//in days

    @Column( nullable = false )
    private LocalDate createdDate = LocalDate.now();

    @Column( nullable = false )
    private boolean isPublic = false;

    @OneToMany(mappedBy = "workoutPlan")
    private List<WorkoutExercise> exercises;

    @OneToMany(mappedBy = "followingWorkoutPlan")
    private List<User> users;

    @OneToMany(mappedBy = "workoutPlan")
    private List<Issue> issues;

    @OneToMany(mappedBy = "workoutPlan")
    private List<Notification> notifications;

    @OneToMany(mappedBy = "workoutPlan")
    private List<WorkoutPlanUserReview> userReviews;

}
