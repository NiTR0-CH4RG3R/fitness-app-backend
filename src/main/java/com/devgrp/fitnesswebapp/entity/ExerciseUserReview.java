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
class ExerciseUserReviewKey implements Serializable {
    int userId;
    int exerciseId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExerciseUserReviewKey that = (ExerciseUserReviewKey) o;
        return Objects.equals(userId, that.userId) && Objects.equals(exerciseId, that.exerciseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, exerciseId);
    }
}

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExerciseUserReview {
    @EmbeddedId
    private ExerciseUserReviewKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn
    private User user;

    @ManyToOne
    @MapsId("exerciseId")
    @JoinColumn
    private Exercise exercise;

    @Column( nullable = false )
    private int rating;

    private String comment;

    @Column( nullable = false )
    private LocalDate createdDateTime;
}
