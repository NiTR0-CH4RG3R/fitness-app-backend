package com.devgrp.fitnesswebapp.entity;

import com.devgrp.fitnesswebapp.common.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="User")
public class User {
    @Id
    @GeneratedValue
    private int id;
    private String firstName;
    private String lastName;
    private UserType type;
    private String email;
    private LocalDate dob;
    private String address;
    private String telephoneNo;
    private float weight;
    private String bloodGroup;
    private String healthIssues;
    private String emergencyContact;
    private WorkoutPlan followingWorkoutPlan;
    private boolean goalNotification;
    private boolean workoutPlanNotification;
    private boolean otherNotification;

}
