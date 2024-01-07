package com.devgrp.fitnesswebapp.entity.compositekeys;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserExerciseLogKey implements Serializable {
    private Integer userId;
    private Integer exerciseId;
    private LocalDateTime dateTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserExerciseLogKey that = (UserExerciseLogKey) o;
        return Objects.equals(userId, that.userId) && Objects.equals(exerciseId, that.exerciseId) && Objects.equals(dateTime, that.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, exerciseId, dateTime);
    }
}