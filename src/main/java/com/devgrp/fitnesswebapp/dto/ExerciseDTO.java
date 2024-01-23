package com.devgrp.fitnesswebapp.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseDTO {

    private String name;

    private String description;

    private String equipment;

    private String gifURL;

    private List<String> image;

    private List<String> targetMuscle;
}
