package com.devgrp.fitnesswebapp.dto;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import lombok.Data;

import java.util.List;
@Data
public class ExerciseDTO {
    private Integer id;

    private String name;

    private String description;

    private String equipment;

    private String gifURL;

    private List<String> image;

    private List<String> targetMuscle;
}
