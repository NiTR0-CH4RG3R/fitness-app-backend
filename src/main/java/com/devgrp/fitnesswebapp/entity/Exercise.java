package com.devgrp.fitnesswebapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Exercise {
    @Id
    @GeneratedValue
    private Integer id;

    @Column( nullable = false )
    private String name;

    @Column( nullable = false )
    private String description;

    private String equipment;

    private String gifURL;

    @ElementCollection
    @CollectionTable
    private List<String> image;

    @ElementCollection
    @CollectionTable
    private List<String> targetMuscle;

    @OneToMany(mappedBy = "exercise")
    private List<WorkoutExercise> workoutPlans;

    @OneToMany(mappedBy = "exercise")
    private List<ExerciseUserReview> userReviews;

    @OneToMany(mappedBy = "exercise")
    private List<UserExerciseLog> userExerciseLogs;
}
