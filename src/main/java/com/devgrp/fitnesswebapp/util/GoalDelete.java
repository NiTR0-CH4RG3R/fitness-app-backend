package com.devgrp.fitnesswebapp.util;

import com.devgrp.fitnesswebapp.entity.types.GoalType;
import lombok.Data;

@Data
public class GoalDelete {
    private String useremail;
    private GoalType goalType;
}
