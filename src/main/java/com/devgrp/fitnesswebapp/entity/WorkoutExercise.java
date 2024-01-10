package com.devgrp.fitnesswebapp.entity;

import com.devgrp.fitnesswebapp.entity.compositekeys.WorkoutExerciseKey;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

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
    private Integer repCount;

    @Column( nullable = false )
    private Integer setCount;

    @ElementCollection
    @CollectionTable
    private List<Integer> schedule;
}
