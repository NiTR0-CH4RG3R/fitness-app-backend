package com.devgrp.fitnesswebapp.entity;

import com.devgrp.fitnesswebapp.common.IssueType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Issue {
    @Id
    @GeneratedValue
    private int id;

    private IssueType type;

    // TODO : Make foreign keys
    // NOTE : both goal and workout plan are optional
    private int goalID;
    private int workoutPlanID;
    private int createdBy; // TODO : This is a reference to the User

    private LocalDateTime createdDateTime;
}
