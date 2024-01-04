package com.devgrp.fitnesswebapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkoutPlan {
    @Id
    @GeneratedValue
    private int id;
    private int duration;//in days
    private LocalDate createdDate;
    private boolean publicView;

}
