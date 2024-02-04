package com.devgrp.fitnesswebapp.dto;

import com.devgrp.fitnesswebapp.entity.WorkoutPlan;
import com.devgrp.fitnesswebapp.entity.types.UserType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserUpdateDTO {
    private String firstName;
    private String lastName;
    private UserType type =UserType.USER;
    private String password;
    private LocalDate dob;
    private String address;
    private String telephoneNo;
    private float weight;
    private String bloodGroup;
    private String healthIssues;
    private String emergencyContact;
    private WorkoutPlan followingWorkoutPlan;
    private boolean goalNotification ;
    private boolean workoutPlanNotification;
    private boolean otherNotification;
}
