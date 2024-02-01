package com.devgrp.fitnesswebapp.dto;

import com.devgrp.fitnesswebapp.entity.Goal;
import com.devgrp.fitnesswebapp.entity.User;
import com.devgrp.fitnesswebapp.entity.WorkoutPlan;
import com.devgrp.fitnesswebapp.entity.types.IssueType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IssueDTO {
    private IssueType type;
    private Integer goalId;
    private Integer workoutPlanId;
}
