package com.devgrp.fitnesswebapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="ExerciseImages")
public class ExcerciseImages {
    private String exerciseID;
    private String URL;
}
