package com.devgrp.fitnesswebapp.dto;

import com.devgrp.fitnesswebapp.entity.Goal;
import com.devgrp.fitnesswebapp.entity.User;
import com.devgrp.fitnesswebapp.entity.WorkoutPlan;
import com.devgrp.fitnesswebapp.entity.types.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationGetDTO {
    private Integer id;
    private NotificationType type;
    private String message;
    private LocalDateTime createdDateTime;
    private Integer receiverId;
    private Integer goalId;
    private Integer workoutPlanId;
}