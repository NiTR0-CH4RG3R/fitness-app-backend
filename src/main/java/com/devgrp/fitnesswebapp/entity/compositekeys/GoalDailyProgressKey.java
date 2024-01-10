package com.devgrp.fitnesswebapp.entity.compositekeys;

import jakarta.persistence.Embeddable;
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
public class GoalDailyProgressKey implements Serializable {
    private Integer goalId;
    private LocalDate date;

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