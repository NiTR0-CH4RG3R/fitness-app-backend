package com.devgrp.fitnesswebapp.entity;

import com.devgrp.fitnesswebapp.common.GoalType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goal {
    @Id
    @GeneratedValue
    private int id;
    private GoalType type;
    private float achievementValue;

    // [TODO] : This is a reference to the User table. Goal and User have a one-to-many connections. Make these foreign keys.
    private int followedBy;
}
