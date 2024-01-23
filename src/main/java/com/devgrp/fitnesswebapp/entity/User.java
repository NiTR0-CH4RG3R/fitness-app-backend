package com.devgrp.fitnesswebapp.entity;

import com.devgrp.fitnesswebapp.entity.types.UserType;
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
public class User {
    @Id
    @GeneratedValue
    private Integer id;

    @Column( nullable = false )
    private String firstName;

    @Column( nullable = false )
    private String lastName;

    @Column( nullable = false )
    private UserType type = UserType.USER;


    @Column( nullable = false ,unique = true)
    private String email;

    @Column( nullable = false )
    private String password;

    @Column( nullable = false )
    private LocalDate dob;

    private String address;

    @Column( nullable = false )
    private String telephoneNo;

    @Column( nullable = false )
    private float weight;

    private String bloodGroup;

    private String healthIssues;

    @Column( nullable = false )
    private String emergencyContact;

    @ManyToOne
    @JoinColumn
    private WorkoutPlan followingWorkoutPlan;

    @Column( nullable = false )
    private boolean goalNotification = true;

    @Column( nullable = false )
    private boolean workoutPlanNotification = true;

    @Column( nullable = false )
    private boolean otherNotification = true;

    @OneToMany(mappedBy = "user")
    private List<ExerciseUserReview> exerciseReviews;

    @OneToMany(mappedBy = "user")
    private List<WorkoutPlanUserReview> workoutPlanReviews;

    @OneToMany(mappedBy = "followedBy")
    private List<Goal> goals;

    @OneToMany(mappedBy = "createdBy")
    private List<Issue> issues;

    @OneToMany(mappedBy = "receiver")
    private List<Notification> notifications;

    @OneToMany(mappedBy = "user")
    private List<UserExerciseLog> userExerciseLogs;

    @OneToMany(mappedBy = "user")
    private List<UserLoggingLog> userLoggingLogs;
}
