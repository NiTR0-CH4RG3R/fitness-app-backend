package com.devgrp.fitnesswebapp.entity;

import com.devgrp.fitnesswebapp.entity.compositekeys.UserExerciseLogKey;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Integer repCount;

    @Column( nullable = false )
    private Integer setCount;
}
