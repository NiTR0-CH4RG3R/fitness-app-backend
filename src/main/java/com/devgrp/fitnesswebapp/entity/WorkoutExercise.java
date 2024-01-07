package com.devgrp.fitnesswebapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
class WorkoutExerciseKey implements Serializable {
    int workoutPlanId;
    int exerciseId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkoutExerciseKey that = (WorkoutExerciseKey) o;
        return Objects.equals(workoutPlanId, that.workoutPlanId) && Objects.equals(exerciseId, that.exerciseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(workoutPlanId, exerciseId);
    }

}

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkoutExercise {
    @EmbeddedId
    private WorkoutExerciseKey id;

    @ManyToOne
    @MapsId("workoutPlanId")
    @JoinColumn
    private WorkoutPlan workoutPlan;

    @ManyToOne
    @MapsId("exerciseId")
    @JoinColumn
    private Exercise exercise;

    @Column( nullable = false )
    private int repCount;

    @Column( nullable = false )
    private int setCount;

    @ElementCollection
    @CollectionTable
    private List<Integer> schedule;
}
