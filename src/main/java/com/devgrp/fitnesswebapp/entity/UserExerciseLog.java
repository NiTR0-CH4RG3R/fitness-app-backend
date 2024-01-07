package com.devgrp.fitnesswebapp.entity;

import jakarta.persistence.*;
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
class UserExerciseLogKey implements Serializable {
    int userId;
    int exerciseId;
    LocalDateTime dateTime;

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

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserExerciseLog {
    @EmbeddedId
    private UserExerciseLogKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn
    private User user;

    @ManyToOne
    @MapsId("exerciseId")
    @JoinColumn
    private Exercise exercise;

    @Column( nullable = false )
    private int repCount;

    @Column( nullable = false )
    private int setCount;
}
