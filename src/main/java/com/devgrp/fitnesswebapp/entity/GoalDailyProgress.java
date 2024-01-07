package com.devgrp.fitnesswebapp.entity;

import com.devgrp.fitnesswebapp.entity.compositekeys.GoalDailyProgressKey;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoalDailyProgress {
    @EmbeddedId
    private GoalDailyProgressKey id;

    @ManyToOne
    @MapsId("goalId")
    @JoinColumn
    private Goal goal;

    @Column( nullable = false )
    private float achievedValue;
}
