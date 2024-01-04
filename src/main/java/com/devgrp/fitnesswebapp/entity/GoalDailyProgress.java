package com.devgrp.fitnesswebapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoalDailyProgress {
    @Id
    private int goalID; // [TODO] : Again, make relevant foreign keys to this
    @Id
    private LocalDate date;

    private float achievedValue;
}
