package com.devgrp.fitnesswebapp.dto;
import com.devgrp.fitnesswebapp.entity.WorkoutPlan;
import com.devgrp.fitnesswebapp.entity.types.UserType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String firstName;
    private String lastName;
    private UserType type =UserType.USER;
    private String email;
    private String password;
    private LocalDate dob;
    private String address;
    private String telephoneNo;
    private float weight;
    private String bloodGroup;
    private String healthIssues;
    private String emergencyContact;
    private boolean goalNotification ;
    private boolean workoutPlanNotification;
    private boolean otherNotification;
}
