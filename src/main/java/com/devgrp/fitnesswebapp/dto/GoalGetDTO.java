package com.devgrp.fitnesswebapp.dto;

import com.devgrp.fitnesswebapp.entity.types.GoalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoalGetDTO {
    private int id;
    private GoalType type;
    private float achievementValue;
}
