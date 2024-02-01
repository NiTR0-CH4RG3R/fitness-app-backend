package com.devgrp.fitnesswebapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutPlanGetDTO {
    private Integer id;
    private String name ;
    private Integer duration;
    private LocalDate createdDate;
    private boolean isPublic;
}
