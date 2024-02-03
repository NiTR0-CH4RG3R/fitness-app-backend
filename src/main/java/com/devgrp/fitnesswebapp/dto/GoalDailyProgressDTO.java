package com.devgrp.fitnesswebapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoalDailyProgressDTO {
    private int goalID;
    private LocalDateTime date;
    private Float achievedValue;
}