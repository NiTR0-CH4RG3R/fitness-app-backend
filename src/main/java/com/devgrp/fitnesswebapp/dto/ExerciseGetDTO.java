package com.devgrp.fitnesswebapp.dto;

import jakarta.persistence.Column;
import lombok.*;
import org.springframework.stereotype.Component;

//@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseGetDTO {
    @Setter
    private Integer id;

    public void setDescription(String description) {
        this.description = description.substring(0,100);//[TODO]:find a best way than this
    }

    @Setter
    private String name;

    private String description;

    @Setter
    private String gifURL;
}
