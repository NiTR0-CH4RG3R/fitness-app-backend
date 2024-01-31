package com.devgrp.fitnesswebapp.util;

import com.devgrp.fitnesswebapp.dto.GoalDTO;
import com.devgrp.fitnesswebapp.entity.types.GoalType;
import lombok.Data;

@Data
public class GoalUserEmail{
    private String userEmail;
    private GoalDTO goalDTO;
    private GoalType goalType;
}
