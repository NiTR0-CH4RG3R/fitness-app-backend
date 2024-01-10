package com.devgrp.fitnesswebapp.entity;

import com.devgrp.fitnesswebapp.entity.compositekeys.ExerciseUserReviewKey;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;




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
    private Integer rating;

    private String comment;

    @Column( nullable = false )
    private LocalDate createdDateTime;
}
