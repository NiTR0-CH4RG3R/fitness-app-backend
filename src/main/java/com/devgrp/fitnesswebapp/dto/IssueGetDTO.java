package com.devgrp.fitnesswebapp.dto;

import com.devgrp.fitnesswebapp.entity.types.IssueType;

import java.time.LocalDateTime;

public class IssueGetDTO {
    private Integer id;
    private IssueType type;
    private Integer goalId;
    private Integer workoutPlanId;
    private Integer createdById;
    private LocalDateTime createdDateTime;
}
