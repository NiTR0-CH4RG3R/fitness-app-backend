package com.devgrp.fitnesswebapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
class GoalDailyProgressKey implements Serializable {
    int goalId;
    LocalDate date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoalDailyProgressKey that = (GoalDailyProgressKey) o;
        return Objects.equals(goalId, that.goalId) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(goalId, date);
    }
}

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
