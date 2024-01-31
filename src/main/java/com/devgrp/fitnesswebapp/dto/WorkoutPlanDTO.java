package com.devgrp.fitnesswebapp.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;

@Data
public class WorkoutPlanDTO {
    private String name ;
    private Integer duration;
    private LocalDate createdDate = LocalDate.now();
    private boolean isPublic = false;
}
