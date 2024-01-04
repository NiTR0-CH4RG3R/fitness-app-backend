package com.devgrp.fitnesswebapp.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

public class WorkoutPlan {
    @Id
    @GeneratedValue
    private int id;
    private int duration;//in days
    private LocalDate createdDate;
    private boolean publicView;

}
