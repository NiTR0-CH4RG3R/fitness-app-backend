package com.devgrp.fitnesswebapp.util;

import com.devgrp.fitnesswebapp.dto.GoalDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoalUser {
    private String userEmail;
    private GoalDTO goalDTO;
}
